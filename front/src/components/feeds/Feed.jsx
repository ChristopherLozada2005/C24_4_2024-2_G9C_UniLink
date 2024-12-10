import './feeds.css';
import { Link } from 'react-router-dom';

// Components................................
import Comments from '../comments/Comments';

// Font Awesome Icon................................
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faListDots, faHeart, faComment, faShare, faTrashCan, faTrash } from '@fortawesome/free-solid-svg-icons';

// States.........................
import { useState } from 'react';

import { useUser } from '../../context/UserContext';

import DefaultProfileImage from '../../assets/img/defaultProfilePicture.png';


export default function Feed({fed, handleFeedDelete, stompClient}) {

    const {userId} = useUser().user;

    let [openCommet, setOpenComment] = useState(false);

    const CommentHandeler =()=>{
        setOpenComment(!openCommet)
    }
    
    const getTime = (pubDate) => {
        const date1 = new Date();
        const date2 = new Date(pubDate);
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
                <Link to={`/profile/${fed.usersito.id}`}>
                    <div className="user">
                        { fed.usersito.hasImage == 'yes'?
                            <img src={`https://res.cloudinary.com/dade42bjv/image/upload/f_auto,q_auto/profile${fed.usersito.id}-image`} alt="User" />
                            :
                            <img src={DefaultProfileImage}/>
                        }
                        <div>
                            <h5>@{fed.usersito.name}</h5>
                            <small>{getTime(fed.pubDate)}</small>  
                        </div>                              
                    </div>
                </Link>
                <div className='right-buttons'>
                    {fed.usersito.id == userId &&
                    <>
                        <FontAwesomeIcon className='trash-icon' onClick={() => {handleFeedDelete(fed.id, fed.imageUrl)}} icon={faTrashCan} />
                    </>
                    }
                    <FontAwesomeIcon icon={faListDots} />
                </div>
            </div>
            <div className="mid-content">
                <p>{fed.title}</p>
                <p>{fed.description}</p>
                { fed.hasImage == 'yes'?
                    <img className="image-container" src={fed.imageUrl}/>
                    :
                    <></>
                }
            </div>
            <div className="bottom-content">
                <div className="action-item">
                    <span><FontAwesomeIcon color='red' icon={faHeart} /> Me gusta</span>
                </div>
                <div className="action-item" onClick={CommentHandeler}>
                    { fed.commentCount == 0 &&
                        <span><FontAwesomeIcon icon={faComment} /> Comentarios</span>
                    }
                    { fed.commentCount == 1 &&
                        <span><FontAwesomeIcon icon={faComment} /> {fed.commentCount} Comentario</span>
                    }
                    { fed.commentCount > 1 &&
                        <span><FontAwesomeIcon icon={faComment} /> {fed.commentCount} Comentarios</span>
                    }
                </div>
                <div className="action-item">
                    <span><FontAwesomeIcon icon={faShare} /> compartir</span>
                </div>
            </div>
            {openCommet && <Comments postId={fed.id} getTime={getTime} stompClient={stompClient}/>}
        </div>
    );
}