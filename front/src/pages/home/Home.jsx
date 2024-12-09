
//Components................................
import Stories from '../../components/stories/Stories';
import AddPost from '../../components/addPost/AddPost';
import Feeds from '../../components/feeds/Feeds';
import { useEffect, useRef, useState } from 'react';
import PostService from '../../services/PostService';
import SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';
import { useUser } from '../../context/UserContext';


export default function Home(){

    const stompClient1 = useRef(null);
    const socket1 = useRef(null);

    const [isChecked, setIsChecked] = useState(true);

    const { name } = useUser().user;

    const [feeds, setFeeds] = useState([]);

    const listFeeds = () => {
        let category = "";
        if (isChecked) {
            category = "Curricular";
        } else {
            category = "Extracurricular";
        }
        PostService.getAllPostsByCategory(category).then(response => {
            setFeeds(response.data);
            console.log(response.data);
        });
    };

    const addNewPost = (newPost) => {
        PostService.createPost(newPost).then(() => {
            if (newPost.status == "Curricular") {
                setIsChecked(true);
            } 
            if (newPost.status == "Extracurricular") {
                setIsChecked(false);
            }
            stompClient1.current.send("/app/message", {}, JSON.stringify({ senderName: name, message: "POST-CREATED"}));
        });
    };

    useEffect(() => {
        listFeeds();
    }, [isChecked]);

    useEffect(() => {
        listFeeds();
    }, []);

    useEffect(() => {
        socket1.current = new SockJS("http://localhost:8080/ws2");
        stompClient1.current = Stomp.over(socket1.current);
        stompClient1.current.connect({}, () => {
        stompClient1.current.subscribe('/chatroom/public', () => {
            console.log("Message on feeds")
            listFeeds();
        })
        stompClient1.current.send("/app/message", {}, JSON.stringify({ senderName: name, message: "CONNECTED"}));
    })
    }, [name]);


    return (
        <>
        {/* 
        <Stories />
        */}
        <AddPost
            onAddPost={addNewPost}
            isChecked={isChecked}
            setIsChecked={setIsChecked}
        />
        <Feeds feeds={feeds} stompClient={stompClient1.current}/>
        </>
    )
}