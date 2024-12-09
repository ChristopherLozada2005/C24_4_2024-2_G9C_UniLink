
//Components................................
import AddPost from '../../components/addPost/AddPost';
import UserProfile from '../../components/userProfile/UserProfile';
import Feeds from '../../components/feeds/Feeds';
import PostService from '../../services/PostService';
import UserService from '../../services/UserService';
import { useEffect, useRef, useState } from 'react';
import { useParams } from 'react-router-dom';
import { useUser } from '../../context/UserContext';
import SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';
import FriendshipService from '../../services/FriendshipService';

export default function Profile(){

    const [feeds, setFeeds] = useState([]);
    const [user, setUser] = useState({});
    const { profileId } = useParams();
    const { userId, name } = useUser().user;

    const [isChecked, setIsChecked] = useState(true);

    const [ friendStatus, setFriendStatus ] = useState({});

    const fetchFriendStatus = async () => {
        if (!userId) return;
        try {
            const response = await FriendshipService.getFriendStatus(userId, profileId);
            setFriendStatus(response.data)
            console.log("STATUS => ", response.data);
        } catch (error) {
            console.error('Error fetching friendship status:', error);
        }
    };
    
    const listFeeds = () => {
        PostService.getUserPosts(profileId).then(response => {
            setFeeds(response.data);
        })
    }

    const getUser = () => {
        console.log(profileId);
        UserService.getUserProfile(profileId).then(response => {
            console.log("Response ----> ", response.data);
            setUser(response.data);
        })
    }

    const addNewPost = (newPost) => {
        PostService.createPost(newPost).then(() => {
            stompClientProfile.current.send("/app/message", {}, JSON.stringify({ senderName: "name", message: "WW"}));
        });
    };

    const stompClientProfile = useRef(null);
    const socketProfile = useRef(null);

    useEffect(() => {
        socketProfile.current = new SockJS("http://localhost:8080/ws4");
        stompClientProfile.current = Stomp.over(socketProfile.current);
        stompClientProfile.current.connect({}, () => {
            stompClientProfile.current.subscribe('/chatroom/public', () => {
            console.log("Message === FRIEND STATUS")
            fetchFriendStatus();
            listFeeds();
        })
        stompClientProfile.current.send("/app/message", {}, JSON.stringify({ senderName: "name", message: "WW"}));
    })
    }, [name]);


    if (!profileId) {
        return (<></>);
    }

    useEffect(() => {
        listFeeds();
    }, [isChecked]);

    useEffect(() => {
        getUser();
        listFeeds();
    }, [profileId])

    return (
        <>
        <UserProfile
            stompClientProfile={stompClientProfile.current}
            user={user} profileId={profileId}
            friendStatus={friendStatus}
            setFriendStatus={setFriendStatus}
            fetchFriendStatus={fetchFriendStatus}
        />

        {profileId == userId &&
            <AddPost
                onAddPost={addNewPost}
                isChecked={isChecked}
                setIsChecked={setIsChecked}
            />
        }
        <Feeds
            feeds={feeds}
            stompClient={stompClientProfile.current}
        />
        </>
    )
}