import './feeds.css';

// Components................................
import Feed from './Feed';
import { useUser } from '../../context/UserContext';
import { useState } from 'react';
import PostService from '../../services/PostService';


// Facke Apis................................
// import HomeFeedData from '../../FackApis/HomeFeedData';

export default function Feeds({ feeds, stompClient }){

    const { userId } = useUser().user;

    const handleFeedDelete = (postId) => {
        PostService.deletePostById(postId);
        console.log("Post Deleted");
        stompClient.send("/app/message", {}, JSON.stringify({ senderName: "name", message: "Post Deleted"}));
    } 

    return (
        <div className='feeds'>
            {
                feeds.map(fed=>(
                    <Feed
                     fed={fed} key={fed.id}
                     userId={userId}
                     handleFeedDelete={handleFeedDelete}
                     stompClient={stompClient}
                     />
                ))
            }
        </div>
    )
}