import './comments.css';

// Facke Apis................................
import CommentData from '../../FackApis/CommetData';
import CurrentUserData from '../../FackApis/CurrentUserData';
import CommentService from '../../services/CommentService';

import { Link } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { useUser } from '../../context/UserContext';

import DefaultProfileImage from '../../assets/img/defaultProfilePicture.png';

export default function Comments({ postId }){
    const [comments, setComments] = useState([]);

    const listComments = () => {
        CommentService.getPostComments(postId).then(response => {
            setComments(response.data);
        });
    }

    useEffect(() => {
        console.log(postId);
        listComments();
    }, [])

    const { userId, hasImage } = useUser().user;

    const defaultComment = {
        text: '',
        postId: postId,
        userId: userId,
    };

    const [comment, setComment] = useState(defaultComment);

    const handleSubmit = (e) => {
        e.preventDefault();
        CommentService.postComment(comment).then(response => {
            listComments();
        });
        setComment(defaultComment);
    }

    const onChange = (e) => {
        const { value } = e.target;
        const newComment = {...comment, text: value}
        console.log("Comment:",newComment)
        setComment(newComment);
    }

    return (
        <div className='comments'>
            <div className="writebox">
                <form action="#">
                    <div className="user">
                        { hasImage == 'yes'?
                            <img src={`https://res.cloudinary.com/dade42bjv/image/upload/f_auto,q_auto/profile${userId}-image`} alt='' />
                            :
                            <img src={DefaultProfileImage}/>
                        } 
                        <input onChange={onChange} value={comment.text} type='text' placeholder='Escribe tu comentario...' />
                        <button onClick={handleSubmit} className="btn btn-primary">Enviar</button>
                    </div>
                </form>
            </div>
            {
                comments.map(comment=>(
                    <Link to={`/profile/${comment.user.id}`}>
                        <div className="user" key={comment.id}>
                            { comment.user.hasImage == 'yes' ?
                                <img src={`https://res.cloudinary.com/dade42bjv/image/upload/f_auto,q_auto/profile${comment.user.id}-image`} alt="" />
                                    :
                                <img src={DefaultProfileImage}/>
                            } 
                            <div>
                                <h5>{comment.user.name}</h5>
                                <p>{comment.text}</p>
                            </div>
                            <small>1h</small>
                        </div>
                    </Link>
                ))
            }
        </div>        
    )
} 