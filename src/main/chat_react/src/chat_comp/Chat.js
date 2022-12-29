
import React, {useEffect, useState} from 'react';
//import axios from 'axios';
import "../App.css";


function Chat({data}) {


    let set_message ="chat c_Width";
    let set_ballon="chat_balloon_";
    let set_message2 = "";
    let set_ballon2 = "";
    let ballon_img_src ="";
    let btn_class = "btn_ui";


    if(data.is_my)
    {
        set_message2="my_message"
        set_ballon2="my_balloon"

        ballon_img_src="Chat_balloon/" + "B" +" chat_balloon/" + "B" +" chat_balloon"+ data.ballon_size +".png"

        btn_class="btn_ui_my"
    }
    else
    {
        ballon_img_src="Chat_balloon/" + "A" +" chat_balloon/" + "A" +" chat_balloon"+ data.ballon_size +".png"
        data.time += " 신고 | 복사"

    }

    if(data.ani)
    {
        btn_class= "chat_ani "+btn_class;
        set_message= "chat_ani "+set_message;
        set_ballon= "chat_ani "+set_ballon;
    }
    


    if(data.ballon_size<4)
    {
        set_message+=(data.ballon_size+" "+set_message2);
        set_ballon+=("S "+set_ballon2);
    }
    else
    {
        set_message+=("3 word_break "+set_message2);
        set_ballon+=("L "+set_ballon2);
    }

    return(
        <>
            <p className={set_message}>{data.message_contents}</p>
            <img className={set_ballon} src={ballon_img_src}></img>
            <br/>
            <p className={btn_class}>{data.time}</p>

        </>
    );
}
export default Chat;