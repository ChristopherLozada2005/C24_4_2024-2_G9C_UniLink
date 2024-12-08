
//Components................................
import Stories from '../../components/stories/Stories';
import AddPost from '../../components/addPost/AddPost';
import Feeds from '../../components/feeds/Feeds';
import { useEffect, useState } from 'react';
import PostService from '../../services/PostService';


export default function Home(){

    const [feeds, setFeeds] = useState([]);

    const listFeeds = () => {
        PostService.getAllPosts().then(response => {
            setFeeds(response.data);
            console.log(response.data);
        });
    };

    const addNewPost = (newPost) => {
        PostService.createPost(newPost).then(() => {
            listFeeds();
        });
    };

    useEffect(() => {
        listFeeds();
    }, []);

    useEffect(() => {
        listFeeds();
    }, [feeds]);

    return (
        <>
        <Stories />
        <AddPost onAddPost={addNewPost}/>
        <Feeds feeds={feeds}/>
        </>
    )
}