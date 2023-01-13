// src/main/frontend/src/App.js
import React, { useEffect ,useState} from 'react';
import axios from 'axios';
import "./App.css";
import Chat_room from "./chat_comp/Chat_room.js"
import Main_room from "./chat_comp/Main_room.js"
import Image_upload from "./chat_comp/Image_upload.js"

import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Cookies from 'js-cookie';

function App() {

    let[securety,set_secure]=useState(false);
    let[id,set_id]=useState("");
    let[UUID_v,set_UUID]=useState("");

    useEffect(() => {
        if (securety === true) {
            return
        }
        set_UUID(Cookies.get("JSESSIONID"));
        axios.get('http://localhost:8080/api/uuid_info/'+ Cookies.get("JSESSIONID"))
        .then((response) => { set_id(response.data); })
        .catch(error => console.log(error))  
        
       
            set_secure(true);
        
    }, [Cookies.get("JSESSIONID")]);




    return (
        <div>
        <BrowserRouter>
                <Routes>
                <Route  path="/chat/:chatcode"  element={ <Chat_room member_code={id} uuid={UUID_v}/>}></Route>
                
                <Route  path="/chat/main"  element={ <Main_room member_code={id} uuid={UUID_v}/>}></Route>
                 <Route path="/:chatcode" element={<Image_upload uuid={UUID_v}/>}></Route>

                </Routes>
            </BrowserRouter>
        </div>
        //            백엔드에서 가져온 데이터입니다 : 
        //            <form action="http://localhost:8080/chat/up"  target="invisible"  method="post" id="fileForm" enctype="multipart/form-data">

        //<form action="http://localhost:8080/chat/up" method="post" id="myForm">
    );
}
export default App;