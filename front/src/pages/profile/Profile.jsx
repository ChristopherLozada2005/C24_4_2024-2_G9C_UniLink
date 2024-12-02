
//Components................................
import AddPost from '../../components/addPost/AddPost';
import UserProfile from '../../components/userProfile/UserProfile';
import Feeds from '../../components/feeds/Feeds';
import PostService from '../../services/PostService';
import UserService from '../../services/UserService';
import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { useUser } from '../../context/UserContext';

export default function Profile(){

    const [feeds, setFeeds] = useState([]);
    const [user, setUser] = useState({});
    const { profileId } = useParams();
    const { userId } = useUser();

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
            listFeeds();
        });
    };

    if (!profileId) {
        return (<></>);
    }

    useEffect(() => {
        getUser();
        listFeeds();
    }, [])

    return (
        <>
        <UserProfile user={user}/>
        {profileId == userId &&
            <AddPost onAddPost={addNewPost}/>
        }
        <Feeds feeds={feeds}/>
        </>
    )
}