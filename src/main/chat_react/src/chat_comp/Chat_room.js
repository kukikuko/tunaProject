// src/main/frontend/src/App.js

import React, { useEffect, useState } from 'react';
import axios from 'axios';
import "../App.css";
import Chat from "./Chat.js"
import 'bootstrap/dist/css/bootstrap.min.css';
import ReactDOMServer from 'react-dom/server'
import { useParams } from 'react-router-dom';



function Chat_room({member_code,uuid}) {
    const chat_code  = useParams().chatcode;
    let [message_str, set_str] = useState('');
    let [cur_message_code, set_message_code] = useState("0");
    let [chat_title,set_title]=useState("로딩중...");
    let [ani_effect,set_effect]=useState(false);
    let [connect_on,set_connect]=useState(true);





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

        let str = datas.split("\0");
        let i = 0;



        for (i = 0; i < str.length - 1; i += 6) {
            let message_data = { message_contents: "", img_src: "", message_code: "", ballon_size: "", time: "", is_my: "", ani: "" }

            message_data.message_contents = str[i + 2];
       
            try{
                message_data.img_src = await find_imgsrc(str[i + 4]);
            }catch(error)
            {

            }
            message_data.message_code = str[i];
            let time_str =  str[i + 3].split("T");
            message_data.time =time_str[1];
            message_data.is_my = (str[i+1]==member_code ? true:false );
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

            add_chat(message_data);
            //벌룬 사이즈 
            //93 173 277
        
            document.getElementById("button"+str[i]).addEventListener("click",(event)=>{
                if (window.confirm("해당 메세지를 신고 하시겠습니까?"))
            {
                axios.postForm("http://localhost:8080/api/message/notify",{messageCode: event.target.value, doNotifyUser: member_code});
                alert("신고가 접수 되었습니다");
                document.getElementById("content"+event.target.value).innerText="신고된 메세지";
                document.getElementById("content"+event.target.value).className="chat c_Width2";
                event.target.innerText="신고됨";
                event.target.disabled=true;
                document.getElementById("ballon"+event.target.value).src="/Chat_balloon/A chat_balloon/A chat_balloon2.png";
                document.getElementById("ballon"+event.target.value).className="chat_balloon_S";
            }});
        }
            set_message_code(str[i - 6]);
            if(ani_effect==false)
        {
            set_effect(true);
        }
       

    }
 

     useEffect( ()=>{

        if(member_code=="")
        {
            return;
        }

        let x =false;

    if(connect_on)
    {
        const timer = setInterval(() => {

            console.log("go")
            axios.get('http://localhost:8080/api/message/get/' + chat_code + "/" + cur_message_code+"/"+uuid)
            .then((response) => {  init_chat(response.data);   })
            .catch(error => console.log(error))
            .finally(()=>{
                
                if(!x)
                {
                    x=true;

                    document.getElementById("messages").scroll({
                    top: document.getElementById("messages").scrollHeight,
                    behavior: 'auto'
                    });
                }

                axios.get('http://localhost:8080/api/chat/check/'+chat_code+'/'+uuid)
                .then((response) => 
                { 
                    console.log(response.data);
                    if(response.data=="F")
                    {
                        //상대방이 채팅창에서 나가셨습니다
                        //더이상 메세지를 보낼수 없습니다.
                         
                        set_connect(false);
                        alert("상대가 떠났습니다 \n더 이상 메세지를 보낼 수 없습니다");
                        clearInterval(timer)
                        axios.get('http://localhost:8080/api/message/get/' + chat_code + "/" + cur_message_code+"/"+uuid)
                        .then((response) => {  init_chat(response.data);   })
                        .catch(error => console.log(error))

                    }
                   
                   })
                .catch(error => console.log(error))
               
                })
            
          
   
            }, 200);
            return ()=> clearInterval(timer);

    }

    }, [cur_message_code,member_code,uuid,connect_on]);

    useEffect(() => {
       
        axios.get('http://localhost:8080/api/chat_title/find/' + chat_code)
                .then((response) => { set_title(response.data == "" ? "제목":response.data);    })
                .catch(error => console.log(error))

        
        document.getElementById("exit_btn").addEventListener("click",()=>{
            console.log("클릭 체크");
            axios.get('http://localhost:8080/api/chat/exit/' + chat_code+"/"+uuid)
                .then((response) => { if(response.data=="ok"){
                    window.close();   
                } })
                .catch(error => console.log(error))

        })
        
        window.addEventListener('submit', (event) => {

            if((event.target.id!="exit_btn")&&!connect_on)
            {
                event.preventDefault();
            }

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

    },[member_code,uuid,connect_on]);

    return (
        <div id="chat_room">
            <div id="up_bar">
                <p id="title">{chat_title}</p>
                <button className='exit_ui' id="exit_btn"></button>
                <img  className="exit_ui" id='exit_image' src="/UI_img/exit.png"/>
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
                    <input type="hidden" value={uuid} name="uuid" ></input>
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