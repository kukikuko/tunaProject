// src/main/frontend/src/App.js

import React, { useEffect, useState } from 'react';
import axios from 'axios';
import "../App.css";
import Chat from "./Chat.js"
import 'bootstrap/dist/css/bootstrap.min.css';
import ReactDOMServer from 'react-dom/server'
import Cookies from 'js-cookie';



function Chat_room() {

    let [check, set_check] = useState(false);
    let [member_code, set_member] = useState("0");
    let [chat_code, set_chat] = useState("0");
    let [securety, set_secure] = useState(false);

    let [message_str, set_str] = useState('');
    let [px_size, set_px_size] = useState(0);
    let [cur_message_code, set_message_code] = useState("0");

    let find_imgsrc = (img_code) => {
        // axios.get('http://localhost:8080/img/find_src/'+img_code)
        // .then((response) => {return response.data})
        // .catch(error => console.log(error))
        //////////////////////////////////////////////////////////////////////////////
        return null
    }

    let add_chat = (message_data) => {

        let message_comp = ReactDOMServer.renderToString(<Chat data={message_data} />);
        document.getElementById("messages").insertAdjacentHTML('beforeend', message_comp);
        document.getElementById("messages").insertAdjacentHTML('beforeend', "<br/>");
    };

    let init_chat = (datas) => {
        //메세지 코드에따라
        if (datas === "") {
            return;
        }
        console.log("데이타 보기:" + datas);

        let str = datas.split("\0");
        let i = 0;

        for (i = 0; i < str.length - 1; i += 6) {
            let message_data = { message_contents: "", img_src: "", message_code: "", ballon_size: "", time: "", is_my: "", ani: "" }

            message_data.message_contents = str[i + 2];
            message_data.img_src = find_imgsrc(str[i + 4]);
            message_data.message_code = str[i];
            let time_str =  str[i + 3].split("T");
            message_data.time =time_str[1];
            message_data.is_my = false;
            if (str[i + 1] === member_code) {
                message_data.is_my = true;
            }
            message_data.ani = false;

            if (str[i + 5] <= 93) {
                message_data.ballon_size = 1;
            }
            else if (str[i + 5] <= 173) {
                message_data.ballon_size = 2;
            }
            else {
                message_data.ballon_size = 2 + (str[i + 5] / 277);
                if (str[i + 5] % 277 !== 0) {
                    message_data.ballon_size++;
                }
            }

            add_chat(message_data);
            //벌룬 사이즈 
            //93 173 277
        }
        set_message_code(str[i - 6]);
    };

    useEffect(() => {
        if (securety === true) {
            return
        }
        const m = Cookies.get("member_code");
        const c = Cookies.get("chat_code");
        set_member(m);
        set_chat(c);
        set_check(true);
        if (member_code !== "0") {
            set_secure(true);
        }
    }, [Cookies.get("member_code")]);

    useEffect(()=>{
        
        const timer = setInterval(() => {
            console.log('http://localhost:8080/chat/get/' + chat_code + "/" + cur_message_code);
            axios.get('http://localhost:8080/chat/get/' + chat_code + "/" + cur_message_code)
                .then((response) => { init_chat(response.data); })
                .catch(error => console.log(error))
        }, 1000);

        
        return ()=> clearInterval(timer);

    }, [cur_message_code, chat_code]);

    useEffect(() => {
      

        window.addEventListener('submit', (event) => {
            if (document.getElementById("text_box").value === "") {
                event.preventDefault();
            }
            else {

                set_str(document.getElementById("text_box").value);
                set_px_size(document.getElementById("lengthcalc").clientWidth);
                console.log("px_size="+px_size);
                console.log("real_pixel_size="+document.getElementById("lengthcalc").clientWidth);
                event.preventDefault();

                setTimeout(() => {
                    document.getElementById("text_box").value = "";
                    set_str("");
                    set_px_size(0);
                }, 10);
            }
        });

    },[check]);

    return (
        <div id="chat_room">
            <div id="up_bar">
                <p id="title">sadjsaidji</p>
            </div>
            <p className="hide" id="lengthcalc">{message_str}</p>

            <div id="messages">
            </div>
            <div id="bottom_bar">
                <img id="send" src="UI_img/send_icon.png" alt="보내기 아이콘 없음" />
                <img id="add_img" src="UI_img/photo.png" alt="사진 아이콘 없음" />
                <form action="http://localhost:8080/chat/up" method="post" id="myForm">
                    <input id="text_box" type="text" name="message"></input>
                    <input type="hidden" value={px_size} name="px_size" ></input>
                    <input type="hidden" value={member_code} name="member_code" ></input>
                    <input type="hidden" value={chat_code} name="chat_code" ></input>


                    <button id="send_btn" type="submit" ></button>
                </form>
            </div>

        </div>
        //            백엔드에서 가져온 데이터입니다 : 
        //            <form action="http://localhost:8080/chat/up"  target="invisible"  method="post" id="fileForm" enctype="multipart/form-data">

        //<form action="http://localhost:8080/chat/up" method="post" id="myForm">
    );
}
export default Chat_room;