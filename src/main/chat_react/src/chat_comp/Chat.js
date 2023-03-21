
//import axios from 'axios';
import "../App.css";


function Chat({data}) {


    let set_message ="chat c_Width";
    let set_ballon="chat_balloon_";
    let set_message2 = "";
    let set_ballon2 = "";
    let ballon_img_src ="";
    let time_class = "time_ui";
    let button_class = "button_ui"


    if(data.is_my)
    {
        set_message2="my_message"
        set_ballon2="my_balloon"

        if(data.img_src=="")
        {
            ballon_img_src="/Chat_balloon/" + "B" +" chat_balloon/" + "B" +" chat_balloon"+ data.ballon_size +".png"
        }
        else
        {
            ballon_img_src="http://localhost:8080/posts/images/"+data.img_src;
        }

        time_class="time_ui_my"
        button_class = "button_ui_my"

    }
    else
    {
        if(data.img_src=="")
        {
        ballon_img_src="/Chat_balloon/" + "A" +" chat_balloon/" + "A" +" chat_balloon"+ data.ballon_size +".png"
        }
        else
        {
            ballon_img_src="http://localhost:8080/posts/images/"+data.img_src;

        }

    }

    if(data.ani)
    {
        time_class= "chat_ani "+time_class;
        set_message= "chat_ani "+set_message;
        set_ballon= "chat_ani "+set_ballon;
    }
    

    if(data.img_src!="")
    {
        set_ballon="image_file "+set_ballon2;
        data.message_contents="";
    }
    else if (data.ballon_size<4)
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
            <p id={"content"+data.message_code} className={set_message}>{data.message_contents}</p>
            <img id={"ballon"+data.message_code} className={set_ballon} src={ballon_img_src}></img>
            <br/>
            <p className={time_class}>{data.time}</p>
            <button value={data.message_code}  className={button_class} id={"button"+data.message_code}>신고</button>

        </>
    );
}
export default Chat;