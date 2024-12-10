import './rightBar.css';

// Components............
import Message from "../message/Message";
import FirendReqe from '../firendReqe/FirendReqe';
import { useEffect, useRef, useState } from 'react';
import SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';
import FriendshipService from '../../services/FriendshipService';
import { useUser } from '../../context/UserContext';

export default function RightBar(){

    const stompClientRightBar = useRef(null);
    const socketRightBar = useRef(null);

    const { name } = useUser().user;
    const { userId } = useUser().user;
    
    const [ friendRequests, setFriendRequests ] = useState([]);
    const [ friendList, setFriendList ] = useState([]);

    const fetchFriendRequests = async () => {
        if (!userId) return;
        try {
            const response = await FriendshipService.getFriendRequests(userId);
            setFriendRequests(response.data);
            console.log(response.data);
        } catch (error) {
            console.error('Error fetching friend requests:', error);
        }
    };

    const fetchFriendList = async () => {
        if (!userId) return;
        try {
            const response = await FriendshipService.getFriendList(userId);
            setFriendList(response.data);
            console.log(response.data);
        } catch (error) {
            console.error('Error fetching friend list:', error);
        }
    };

    useEffect(() => {
        fetchFriendRequests();
        fetchFriendList();
    }, [userId]);

    useEffect(() => {
        socketRightBar.current = new SockJS(`${import.meta.env.VITE_API_URL}/ws3`);
        stompClientRightBar.current = Stomp.over(socketRightBar.current);
        stompClientRightBar.current.connect({}, () => {
            stompClientRightBar.current.subscribe('/chatroom/public', () => {
                console.log("Message on RIGHT BAR")
                fetchFriendRequests();
                fetchFriendList();
            })
        stompClientRightBar.current.send("/app/message", {}, JSON.stringify({ senderName: "name", message: "LOADED = RIGHT BAR"}));
        })
    }, [name]);

    return (
        <div className='rightBar'>
            <div className='rightbar-container'>
                <Message friendList={friendList}/>
                <FirendReqe friendRequests={friendRequests} stompClient={stompClientRightBar.current}/>
            </div>
        </div>
    )
}