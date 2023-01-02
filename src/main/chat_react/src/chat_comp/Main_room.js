import axios from "axios";
import { useEffect, useState } from "react";
import Cookies from 'js-cookie';


function Main_room()
{

    let[uuid,set_uuid] = useState("");
    let[securety,set_secure]=useState(false);

    // useEffect(()=>{
    //     axios.get('http://localhost:8080/uuid_info/' + uuid)
    //             .then((response) => { response.data })
    //             .catch(error => console.log(error))
    // },[uuid]);

    useEffect(() => {
        if (securety === true) {
            return
        }
        let uuid_v = Cookies.get("JSESSIONID");
        set_uuid(uuid_v);
        if (uuid !== "") {
            set_secure(true);
        }
    }, [Cookies.get("JSESSIONID")]);

    useEffect(()=>{
        axios.get('http://localhost:8080/uuid_info/'+uuid)
        .then((response) => { let id = response.data; })
        .catch(error => console.log(error))    },[securety])
}

export default Main_room;