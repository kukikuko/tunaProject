import { useEffect, useState } from "react";
import axios from 'axios';
import "../App.css";
import Room from "./Room.js"
import ReactDOMServer from 'react-dom/server'


function Main_room({member_code,uuid})
{
    let [cur_chat_code,set_chat_code] = useState("0");


    function  find_newInfo (chatCode) 
    {
       if(chatCode==0)
       {
           return "";
       }
       return new Promise((resolve)=>{
        axios.get('http://localhost:8080/api/chat/new_info/' + chatCode+"/"+uuid)
        .then((response) =>{resolve(response.data)})
       .catch(error => console.log(error))
    });
   }

    async function init_main(datas){
        let str = datas.split("\0");
        let i=0;
        for(i=0;i<str.length-1;i+=3)
        {
          let val = await find_newInfo(str[i+1]);
            let str2 = val.split("\0");


            let room_data = { title: str[i], chatCode: str[i+1], nick: str[i+2],lastMessage: str2[0],count: str2[1]}
            let room_comp = ReactDOMServer.renderToString(<Room data={room_data} />);
            document.getElementById("rooms").insertAdjacentHTML('beforeend', room_comp);
            document.getElementById("rooms").insertAdjacentHTML('beforeend', "<br/>");

            let link = "http://localhost:3000/chat/"+str[i+1];

            document.getElementById("room"+str[i+1]).addEventListener('click',()=>{window.location.assign(link)})
            
        }
        if(str[i-2]==undefined)
        {
            return;
        }
        set_chat_code(str[i-2]);

    }

    useEffect(()=>{
       
        const timer = setInterval(() => {
        if(cur_chat_code==undefined)
        {
            return;
        }
        else{
            console.log(cur_chat_code);
            axios.get('http://localhost:8080/api/chat/find/' + uuid+"/"+cur_chat_code)
            .then((response) => { init_main(response.data); })
            .catch(error => console.log(error))
        }
       
        },200);

        return ()=> clearInterval(timer);
    },[member_code,cur_chat_code,uuid])

    return(
            <div id="rooms">
                
            </div>
        
    );

    // useEffect(()=>{
    //     axios.get('http://localhost:8080/uuid_info/' + uuid)
    //             .then((response) => { response.data })
    //             .catch(error => console.log(error))
    // },[uuid]);

   
}

export default Main_room;