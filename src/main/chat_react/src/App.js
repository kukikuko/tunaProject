// src/main/frontend/src/App.js
import React, { useEffect ,useState} from 'react';
import axios from 'axios';
import "./App.css";
import Chat_room from "./chat_comp/Chat_room.js"
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';


function App() {





    return (
        <div>
        <BrowserRouter>
				<Routes>
                <Route  path="/" element={ <Chat_room/>}></Route>
                
               

                </Routes>
			</BrowserRouter>
        </div>
        //            백엔드에서 가져온 데이터입니다 : 
        //            <form action="http://localhost:8080/chat/up"  target="invisible"  method="post" id="fileForm" enctype="multipart/form-data">

        //<form action="http://localhost:8080/chat/up" method="post" id="myForm">
    );
}
export default App;