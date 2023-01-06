import "../App.css";
import { useParams } from 'react-router-dom';
import axios from 'axios';
import React, { useEffect, useState } from 'react';


function Image_upload({member_code}) {
    const chat_code  = useParams().chatcode;



    useEffect(()=>{

        document.getElementById("submit_btn").addEventListener("click",()=>{
            var frm = new FormData();
            var imgfileF = document.getElementById("chatImage");
            frm.append("chatImage", imgfileF.files[0]);

            axios.post('http://localhost:8080/api/image/up', frm, {
                headers: {
                  'Content-Type': 'multipart/form-data'
                }
              })
              .then((response) => {
                document.getElementById("img_code").value=response.data;
                console.log(response.data);
                document.getElementById("myForm").submit();
              })
              .catch((error) => {
                // 예외 처리
              })
        
        })

    
    },[])
   
    function preview(e)
    {
      document.getElementById("previewimg").src = URL.createObjectURL(e.target.files[0]);
      document.getElementById("previewimg").onload= function(){
        URL.revokeObjectURL(document.getElementById("previewimg").src)
      }

    }

    function move_init(event)
    {
      document.getElementById("previewimg").style.transform="translate(0px,"+ ((window.innerHeight-(document.getElementById("previewimg").scrollHeight+30))/2)+"px)";
    }

    return(
        <div>

                <input type="file" name="chatImage" accept="image/*" multiple id="chatImage"  onChange={(event)=>preview(event)}></input>
                <button id="submit_btn">전송</button>  
                <img id="previewimg" onLoad={(event)=>{move_init(event)}} />

            <form action="http://localhost:8080/api/message/up" method="post" id="myForm">
                    <input id="text_box" type="hidden" name="message" value="image"  maxLength='105'></input>
                    <input id="text_length" type="hidden" name="px_size"  value="0"></input>
                    <input type="hidden" value={member_code} name="member_code" ></input>
                    <input type="hidden" value={chat_code} name="chat_code" ></input>
                    <input type="hidden" name="image_code" id="img_code" ></input>
                </form>
        </div>
    );
}


export default Image_upload;