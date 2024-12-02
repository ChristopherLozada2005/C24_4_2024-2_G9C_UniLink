import './chatbox.css';

// Components................................
import Stories from '../../components/stories/Stories'

// Facke Apis................................
import CurrentUserData from '../../FackApis/CurrentUserData';

// Fontawesome icon...................
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faArrowAltCircleRight, faFileAlt } from '@fortawesome/free-solid-svg-icons';
import { useEffect, useState, useRef } from 'react';
import SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';
import { useUser } from '../../context/UserContext';
import { useParams } from 'react-router-dom';


export default function Chatbox(){

    const { userId } = useUser();
    const { receiverId } = useParams();

    const [privateChats, setPrivateChats] = useState(new Map());
    const [message, setMessage] = useState('');

    const stompClient = useRef(null);
    const socket = useRef(null);


    useEffect(() => {
        if (!userId) return;
        socket.current = new SockJS("http://localhost:8080/ws");
        stompClient.current = Stomp.over(socket.current);
        stompClient.current.connect({}, () => {
            stompClient.current.subscribe(`/user/${userId}/private`, function(payload) {
                let payloadData = JSON.parse(payload.body);
                console.log("Received >>> ", payloadData)
                if (privateChats.get(payloadData.senderName)) {
                    privateChats.get(payloadData.senderName).push(payloadData);
                    setPrivateChats(new Map(privateChats));
                } else {
                    let list = [];
                    list.push(payloadData);
                    privateChats.set(payloadData.senderName, list);
                    setPrivateChats(new Map(privateChats));
                }
            })
            stompClient.current.send("/app/private-message", {}, JSON.stringify(
                {
                    senderName: userId,
                    receiverName: "",
                    message: "",
                    status: 'JOIN',
                }
            ))
            privateChats.set(receiverId, []);
            setPrivateChats(new Map(privateChats));
        })
        return () => {
            if (stompClient.current) {
                stompClient.current.disconnect();
            }
        };
    }, [userId]);

    const sendMessage = (e) => {
        console.log(privateChats);
        e.preventDefault();
        if (stompClient.current) {
            const chatMessage = {
                senderName: userId,
                receiverName: receiverId,
                message: message,
                status: 'MESSAGE',
            }
            if(userId !== receiverId) {
                privateChats.get(receiverId).push(chatMessage);
                setPrivateChats(new Map(privateChats));
            }
            stompClient.current.send("/app/private-message", {}, JSON.stringify(chatMessage));
            setMessage('');
        } else {
            "stompClient not defined";
        }
    }

    const handleMessage = (event) => {
        const { value } = event.target;
        setMessage(value);
    }

    return (
        <>
        <Stories />
        <div className="chat-box">
            <div className="chat-box-top">
            <img src={CurrentUserData.map(user=>(user.ProfieImage))} alt='' />
                <div className="user-name">
                    <h3>{CurrentUserData.map(user=>(user.name))}</h3>
                    <h5>{CurrentUserData.map(user=>(user.username))}</h5>
                </div>
            </div>
            {privateChats.size !== 0 &&
            <div className='chat-box-center'>
                {[...privateChats.get(receiverId)].map((chat, index) => (
                    <li key={index}>
                        {chat.message}
                    </li>
                ))}   
            </div>
            }
            <div className="chat-box-bottom">
                <form action="#"> 
                    <input value={message} onChange={handleMessage} type="text" placeholder="Escribe tu mensaje..." />
                    <button onClick={sendMessage} className='btn btn-primary'>
                        <FontAwesomeIcon icon={faArrowAltCircleRight} />
                    </button>
                    <label className='btn btn-primary' htmlFor='Cfile'>
                        <FontAwesomeIcon icon={faFileAlt} />
                        <input type='flile' id='Cfile' />
                    </label>
                </form>
            </div>
        </div>
        </>
    )
}
