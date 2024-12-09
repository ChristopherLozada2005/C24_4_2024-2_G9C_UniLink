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

import UserService from '../../services/UserService';
import DefaultProfileImage from '../../assets/img/defaultProfilePicture.png';


export default function Chatbox(){

    const { userId, name, hasImage, username } = useUser().user;

    const [userReceiver, setUserReceiver] = useState({});

    const { receiverId } = useParams();

    const [privateChats, setPrivateChats] = useState(new Map());
    const [message, setMessage] = useState('');

    const stompClient = useRef(null);
    const socket = useRef(null);

    const truncUsername = () => {
        if (userReceiver != null && userReceiver.username != null) {
            return `@${userReceiver.username.split('@')[0]}`;
        }
    }

    const truncSenderName = (senderName) => {
        if (senderName != null && senderName != null) {
            return `@${senderName.split('@')[0]}`;
        }
    }

    const getUserReceiver = () => {
        console.log(receiverId);
        UserService.getUserProfile(receiverId).then(response => {
            console.log("Response ----> ", response.data);
            setUserReceiver(response.data);
        })
    }

    useEffect(() => {
        getUserReceiver();
    }, []);


    useEffect(() => {
        console.log(username);
        console.log(userReceiver);
        if (!username) return;
        if (!userReceiver) return;
        socket.current = new SockJS("http://localhost:8080/ws");
        stompClient.current = Stomp.over(socket.current);
        stompClient.current.connect({}, () => {
            stompClient.current.subscribe(`/user/${username}/private`, function(payload) {
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
                    senderName: username,
                    receiverName: "",
                    message: "",
                    status: 'JOIN',
                }
            ))
            privateChats.set(userReceiver.username, []);
            setPrivateChats(new Map(privateChats));
        });
        return () => {
            if (stompClient.current) {
                stompClient.current.disconnect();
            }
        };
    }, [userReceiver]);

    const sendMessage = (e) => {
        console.log(privateChats);
        e.preventDefault();
        if (stompClient.current) {
            const chatMessage = {
                senderName: username,
                receiverName: userReceiver.username,
                message: message,
                status: 'MESSAGE',
            }
            if(username !== receiverId) {
                privateChats.get(userReceiver.username).push(chatMessage);
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
            { userReceiver.hasImage == 'yes'?
                <img src={`https://res.cloudinary.com/dade42bjv/image/upload/f_auto,q_auto/profile${userReceiver.id}-image`} alt='' />
                    :
                <img src={DefaultProfileImage}/>
            } 
                <div className="user-name">
                    <h3>{userReceiver.name}</h3>
                    <h5>{truncUsername()}</h5>
                </div>
            </div>
            {privateChats.size !== 0 && (
            <div className="chat-box-center">
                {[...privateChats.get(userReceiver.username)].map((chat, index) => {
                if (userReceiver.username !== chat.senderName) {
                    return (
                    <li key={index} className="bubble bubble-emisor">
                        {truncSenderName(chat.senderName)}: {chat.message}
                    </li>
                    );
                }
                return (
                    <li key={index} className="bubble bubble-receptor">
                        {truncSenderName(chat.senderName)}: {chat.message}
                    </li>
                );
                })}
            </div>
            )}
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
