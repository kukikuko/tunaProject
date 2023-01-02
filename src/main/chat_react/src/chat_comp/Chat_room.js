// src/main/frontend/src/App.js

import React, { useEffect, useState } from 'react';
import axios from 'axios';
import "../App.css";
import Chat from "./Chat.js"
import 'bootstrap/dist/css/bootstrap.min.css';
import ReactDOMServer from 'react-dom/server'



function Chat_room({chat_code,member_code}) {
    let [message_str, set_str] = useState('');
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
            console.log("받아온 크기"+str[i + 5])
            if (str[i + 1] === member_code) {
                message_data.is_my = true;
            }
            message_data.ani = false;

            if (str[i + 5] <= 53) {
                
                message_data.ballon_size = 1;
            }
            else if (str[i + 5] <= 133) {
                message_data.ballon_size = 2;
            }
            else {
                message_data.ballon_size = 2 + Math.ceil(str[i + 5] / 237);
               
            }
            console.log("계산된 크기"+message_data.ballon_size)

            add_chat(message_data);
            //벌룬 사이즈 
            //93 173 277
        }
        set_message_code(str[i - 6]);
    };

 

    useEffect(()=>{
        
        const timer = setInterval(() => {
            axios.get('http://localhost:8080/message/get/' + chat_code + "/" + cur_message_code)
                .then((response) => { init_chat(response.data); })
                .catch(error => console.log(error))
        }, 1000);

        
        return ()=> clearInterval(timer);

    }, [cur_message_code]);

    useEffect(() => {
      

        window.addEventListener('submit', (event) => {

            if (document.getElementById("text_box").value === "") {
                event.preventDefault();
            }
            else if(document.getElementById("text_box").value.length>105)
            {
                event.preventDefault();
            }
            else {
                set_str(document.getElementById("text_box").value);
                document.getElementById("text_length").value=document.getElementById("lengthcalc").clientWidth
                setTimeout(() => {
                    document.getElementById("text_box").value = "";
                    set_str("");
                }, 5);
            }
        });

    });

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
                <form action="http://localhost:8080/message/up" method="post" id="myForm">
                    <input id="text_box" type="text" name="message"  maxLength='105'></input>
                    <input id="text_length" type="hidden" name="px_size" ></input>
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