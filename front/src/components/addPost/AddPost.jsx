import './addPost.css';


// Facke Api...........................
import CurrentUserData from '../../FackApis/CurrentUserData'

// Font Awesome Icon................................
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSmile, faTags, faImage, faVideo } from '@fortawesome/free-solid-svg-icons';
import { useEffect, useState } from 'react';
import { useUser } from '../../context/UserContext';

export default function AddPost({ onAddPost }){

    const { userId } = useUser();

    const [post, setPost] = useState({userId: userId, description: ""});

    const submitHandler = (e) => {
        e.preventDefault();
        console.log('Post',post);
        onAddPost(post);
        setPost({ userId: userId, description: "" });;
    }

    const onChange = (e) => {
        const { value } = e.target;
        setPost((prevPost) => ({ ...prevPost, description: value }));
        console.log(post);
    }

    useEffect(() => {
        setPost({ userId: userId, description: "" });
    }, [userId]);

    return (
        <form className='postForm'>
            <div className="user form-top">
                <img src={CurrentUserData.map(user=>(user.ProfieImage))} alt='' />
                <input onChange={onChange} value={post.description} type='text' placeholder='¿Qué estás pensando?' />
                <button onClick={submitHandler} type="submit" className='btn btn-primary'>Publicar</button>
            </div>
            <div className="post-categories">
                <label htmlFor="file">
                    <input type="file" id='file' />
                    <span><FontAwesomeIcon icon={faImage} /> Fotos</span>
                </label>
                <label htmlFor="file">
                    <input type="file" id='file' />
                    <span><FontAwesomeIcon icon={faVideo} /> Videos</span>
                </label>
                <span><FontAwesomeIcon icon={faTags} /> Etiquetar</span>
                <span><FontAwesomeIcon icon={faSmile} /> Sentimientos</span>
            </div>
        </form>
    )
}