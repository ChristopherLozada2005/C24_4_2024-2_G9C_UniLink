import './addPost.css';
import DefaultProfileImage from '../../assets/img/defaultProfilePicture.png';

// Facke Api...........................
import CurrentUserData from '../../FackApis/CurrentUserData'

// Font Awesome Icon................................
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSmile, faTags, faImage, faVideo } from '@fortawesome/free-solid-svg-icons';
import { useEffect, useState } from 'react';
import { useUser } from '../../context/UserContext';

export default function AddPost({ onAddPost }){

    const { userId, hasImage } = useUser().user;

    const [post, setPost] = useState({userId: userId, description: "", hasImage: 'no'});
    const [file, setFile] = useState(null);

    const submitHandler = (e) => {
        e.preventDefault();
        console.log('Post',post);
        if (file != null) {
            setPost({...post, hasFile: 'yes'})
        }
        onAddPost(post);
        setPost({ userId: userId, description: "", hasImage: 'no' });
    }

    const onChange = (e) => {
        const { value } = e.target;
        setPost((prevPost) => ({ ...prevPost, description: value }));
        console.log(post);
    }

    const fileChangeHandler = (e) => {
        const selectedFile = e.target.files[0];
        console.log("Archivo seleccionado:", selectedFile);
        setPost({...post, hasImage: 'yes'})
        setFile('yes');
    }

    useEffect(() => {
        setPost({ userId: userId, description: "", hasImage: 'no'});
    }, [userId]);

    return (
        <form className='postForm'>
            <div className="user form-top">
                { hasImage == 'yes'?
                    <img src={`https://res.cloudinary.com/dade42bjv/image/upload/f_auto,q_auto/profile${userId}-image`} alt='' />
                        :
                    <img src={DefaultProfileImage}/>
                } 
                <input onChange={onChange} value={post.description} type='text' placeholder='¿Qué estás pensando?' />
                <button onClick={submitHandler} type="submit" className='btn btn-primary'>Publicar</button>
            </div>
            <div className="post-categories">
                <label htmlFor="file">
                    <input onChange={fileChangeHandler} type="file" id='file' />
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