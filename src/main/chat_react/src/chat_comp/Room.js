import "../App.css";

function Room({data}) {





return(
    // <div className="roombox" onClick={go} >
    // <div className="roombox" onClick='location.href="http://localhost:3000/chat/2"' >
    <div className="roombox" id={"room"+data.chatCode}>
        <p className="noselect">{data.title}</p>
    </div>
);
}
export default Room;