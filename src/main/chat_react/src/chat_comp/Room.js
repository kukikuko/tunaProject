import { useEffect } from "react";
import "../App.css";

function Room({data}) {
let alert_cir;
let alert_txt;
if(data.count!=0)
{
    alert_cir=  "alert_circle";
     alert_txt = data.count;
}



return(
    // <div className="roombox" onClick={go} >
    // <div className="roombox" onClick='location.href="http://localhost:3000/chat/2"' >
    <>      
    <div className={alert_cir}>{alert_txt}</div>
   <div className="roombox" id={"room"+data.chatCode}>

        <p className="noselect titles">{data.title+"("+data.nick+")"}</p>
        <p className="noselect lastM">{data.lastMessage}</p>

    </div>
    </>
);
}
export default Room;