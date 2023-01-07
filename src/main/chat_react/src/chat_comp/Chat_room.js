// src/main/frontend/src/App.js

import React, { useEffect, useState } from 'react';
import axios from 'axios';
import "../App.css";
import Chat from "./Chat.js"
import 'bootstrap/dist/css/bootstrap.min.css';
import ReactDOMServer from 'react-dom/server'
import { useParams } from 'react-router-dom';



function Chat_room({member_code}) {
    const chat_code  = useParams().chatcode;
    let [message_str, set_str] = useState('');
    let [cur_message_code, set_message_code] = useState("0");
    let [chat_title,set_title]=useState("로딩중...");
    let [ani_effect,set_effect]=useState(false);




     function  find_imgsrc (img_code) 
     {
        if(img_code==0)
        {
            return "";
        }
        return new Promise((resolve)=>{
        axios.get('http://localhost:8080/api/image/get/'+img_code)
        .then((response) =>{resolve(response.data)})
        .catch(error => console.log(error))});
    }

    let add_chat = (message_data) => {

        let message_comp = ReactDOMServer.renderToString(<Chat data={message_data} />);
        document.getElementById("messages").insertAdjacentHTML('beforeend', message_comp);
        document.getElementById("messages").insertAdjacentHTML('beforeend', "<br/>");
    };

    async function init_chat  (datas)
     {
        //메세지 코드에따라
        if (datas === "") {
            return;
        }
        console.log("데이타 보기:" + datas);

        let str = datas.split("\0");
        let i = 0;


        axios.get('http://localhost:8080/api/chat_title/find/' + chat_code)
                .then((response) => { set_title(response.data == "" ? "제목":response.data); console.log(response.data);   })
                .catch(error => console.log(error))


        for (i = 0; i < str.length - 1; i += 6) {
            let message_data = { message_contents: "", img_src: "", message_code: "", ballon_size: "", time: "", is_my: "", ani: "" }

            message_data.message_contents = str[i + 2];
       
            try{
                message_data.img_src = await find_imgsrc(str[i + 4]);
            }catch(error)
            {

            }
             console.log(message_data.img_src);
            message_data.message_code = str[i];
            let time_str =  str[i + 3].split("T");
            message_data.time =time_str[1];
            message_data.is_my = (str[i+1]==member_code ? true:false );
            console.log("받아온 크기"+str[i + 5])
            if (str[i + 1] === member_code) {
                message_data.is_my = true;
            }
            message_data.ani = ani_effect;

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
        
            document.getElementById("button"+str[i]).addEventListener("click",(event)=>{axios.post("http://localhost:8080/api/message/notify",{MessageCode:event.target.value, ChatCode:chat_code,doNotifyUser:member_code})});
        }
            set_message_code(str[i - 6]);
            if(ani_effect==false)
        {
            set_effect(true);
        }
       

    }
 

    useEffect(()=>{

        if(member_code=="")
        {
            return;
        }

        console.log("check")
        const timer = setInterval(() => {
            axios.get('http://localhost:8080/api/message/get/' + chat_code + "/" + cur_message_code)
                .then((response) => { init_chat(response.data);   })
                .catch(error => console.log(error))
        }, 100);
        
        document.getElementById("messages").scroll({
            top: document.getElementById("messages").scrollHeight,
            behavior: 'auto'
          });
         
        return ()=> clearInterval(timer);

    }, [cur_message_code,member_code]);

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

    },[member_code]);

    return (
        <div id="chat_room">
            <div id="up_bar">
                <p id="title">{chat_title}</p>
            </div>
            <p className="hide" id="lengthcalc">{message_str}</p>

            <div id="messages">
            </div>
            <div id="bottom_bar">
                <img id="send" src="/UI_img/send_icon.png" alt="보내기 아이콘 없음" />
                <img id="add_img" src="/UI_img/photo.png" alt="사진 아이콘 없음" />

                <form  method="get" onSubmit={(event)=>{window.open("http://localhost:3000/"+chat_code,'_blank','width=350,height=300')}}>
                <button id="imgup_btn" type="submit"></button>
                </form>


                <form action="http://localhost:8080/api/message/up" method="post" id="myForm">
                    <input id="text_box" type="text" name="message"  maxLength='105'></input>
                    <input id="text_length" type="hidden" name="px_size" ></input>
                    <input type="hidden" value={member_code} name="member_code" ></input>
                    <input type="hidden" value={chat_code} name="chat_code" ></input>
                    <input type="hidden" value={0} name="image_code" ></input>


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