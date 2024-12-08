import './feeds.css';
import { Link } from 'react-router-dom';

// Components................................
import Comments from '../comments/Comments';

// Font Awesome Icon................................
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faListDots, faHeart, faComment, faShare, faTrashCan } from '@fortawesome/free-solid-svg-icons';

// States.........................
import { useState } from 'react';

import FeedImage3 from '../../assets/img/feed3.jpg'
import { useUser } from '../../context/UserContext';

import DefaultProfileImage from '../../assets/img/defaultProfilePicture.png';
import PostService from '../../services/PostService';


export default function Feed({fed}) {

    const { userId } = useUser().user;

    // States Discuse.........................
    let [openCommet, setOpenComment] = useState(false);
    const CommentHandeler =()=>{
        setOpenComment(!openCommet)
    }

    const handleDelete = (postId) => {
        PostService.deletePostById(postId);
        console.log("Post Deleted");
    } 

    

    const getPostTime = () => {
        const date1 = new Date();
        const date2 = new Date(fed.pubDate);
        const differenceInMs = date1 - date2;

        const differenceInDays = differenceInMs / (1000 * 60 * 60 * 24);
        const differenceInHours = differenceInMs / (1000 * 60 * 60);
        const differenceInMinutes = differenceInMs / (1000 * 60);
        // const differenceInSeconds = differenceInMs / 1000;

        let time = '';
        if (differenceInMs >= 1000 * 60) {
            if(differenceInMs >= 1000 * 60 * 2) {
                time = `Hace ${Math.floor(differenceInMinutes)} minutos`
            } else {
                time = `Hace 1 minuto`;
            }
        } else {
            time = `Hace menos de 1 minuto`;
        }

        if (differenceInMs >= 1000 * 60 * 60) {
            if(differenceInMs >= 1000 * 60 * 60 * 2) {
                time = `Hace ${Math.floor(differenceInHours)} horas`;
            } else {
                time = `Hace 1 hora`;
            }
        }
        return time
    }

    return (
        <div className='feed' key={fed.userid}>
            <div className="top-content">
                <Link to={`/profile/${fed.user.id}`}>
                    <div className="user">
                        { fed.user.hasImage == 'yes'?
                            <img src={`https://res.cloudinary.com/dade42bjv/image/upload/f_auto,q_auto/profile${fed.user.id}-image`} alt="User" />
                            :
                            <img src={DefaultProfileImage}/>
                        }
                        <div>
                            <h5>@{fed.user.name}</h5>
                            <small>{getPostTime()}</small>  
                        </div>                              
                    </div>
                </Link>
                <span>
                    {fed.user.id == userId &&
                        <button onClick={() => handleDelete(fed.id)}>
                            <FontAwesomeIcon icon={faTrashCan} />
                        </button>
                    }
                    <FontAwesomeIcon icon={faListDots} />
                </span>
            </div>
            <div className="mid-content">
                <p>{fed.title}</p>
                <p>{fed.description}</p>
                { fed.hasImage == 'yes'?
                    <img className="image-container" src={`https://res.cloudinary.com/dade42bjv/image/upload/q_auto,f_auto,w_800/feed${fed.id}.jpg`}/>
                    :
                    <></>
                }
            </div>
            <div className="bottom-content">
                <div className="action-item">
                    <span><FontAwesomeIcon icon={faHeart} /> 14 Me gusta</span>
                </div>
                <div className="action-item" onClick={CommentHandeler}>
                    <span><FontAwesomeIcon icon={faComment} /> 2 Comentarios</span>
                </div>
                <div className="action-item">
                    <span><FontAwesomeIcon icon={faShare} /> 1 compartido</span>
                </div>
            </div>
            {openCommet && <Comments postId={fed.id}/>}
        </div>
    );
}