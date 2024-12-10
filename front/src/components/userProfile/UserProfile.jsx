import './userProfile.css';
import { Link } from 'react-router-dom';

// Facke Apis................................
import CurrentUserData from '../../FackApis/CurrentUserData';
import DefaultProfileImage from '../../assets/img/defaultProfilePicture.png';

// Fontawesome icon...................
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faFeed, faLink, faMessage } from '@fortawesome/free-solid-svg-icons';
import { useUser } from '../../context/UserContext';
import { useEffect, useRef, useState } from 'react';

import FriendshipService from '../../services/FriendshipService';
import SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';

export default function UserProfile({ user, profileId, stompClientProfile, friendStatus, setFriendStatus, fetchFriendStatus }){

    const { userId }  = useUser().user;
    
    const deleteHandler = async (friendId) => {
        try {
            const response = await FriendshipService.deleteFriendship(friendId);
            stompClientProfile.send("/app/message", {}, JSON.stringify({ senderName: "name", message: "CHANGE (PROFILE): FRIEND STATUS"}));
            fetchFriendStatus();
            console.log('FriendRequest deleted: ', response.data);
        } catch (error) {
            console.error('Error deleting friend request:', error);
        }
    };

    const handleFriendRequest = async (userId, profileId) => {
        try {
            const body = {
                firstUserId: userId,
                secondUserId: profileId,
            }
            const response = await FriendshipService.createFriendship(body);
            setFriendStatus(response.data);
            stompClientProfile.send("/app/message", {}, JSON.stringify({ senderName: "name", message: "CHANGE (PROFILE): FRIEND STATUS"}));
            fetchFriendStatus();
        } catch (error) {
            console.error('Error deleting friend request:', error);
        }
    }

    const acceptHandler = async (friendshipId) => {
        try {
            const response = await FriendshipService.updateFriendshipStatus(friendshipId);
            console.log('FriendRequest updated: ', response.data);
            stompClientProfile.send("/app/message", {}, JSON.stringify({ senderName: "name", message: "CHANGE (PROFILE): FRIEND STATUS"}));
            fetchFriendStatus();
        } catch (error) {
            console.error('Error updating friend request:', error);
        }
    };

    useEffect(() => {
        fetchFriendStatus();
    }, [userId]);

    const truncUsername = () => {
        if (user != null && user.username != null) {
            return `@${user.username.split('@')[0]}`;
        }
    }

    return (
        <div className='userProfile'>
            <div className="cover-photos">
                <img src={CurrentUserData.map(user=>(user.CoverPhoto))} alt='' />
            </div>
            <div className="profile-info">
                { user.hasImage == 'yes'?
                    <img src={`https://res.cloudinary.com/dade42bjv/image/upload/f_auto,q_auto/profile${user.id}-image`} alt='' />
                        :
                    <img src={DefaultProfileImage}/>
                } 
                <div className="user-name">
                    <h3>{user.name}</h3>
                    <h5>{truncUsername()}</h5>
                </div>
                <div className="profile-button">
                    {userId !== user.id &&
                    <>
                        <Link to={`/chatbox/${user.id}`}>
                        <button className='btn btn-primary'>
                            <FontAwesomeIcon icon={faMessage} />
                        </button>
                        </Link>


                        { friendStatus.status == 'ACCEPTED' &&
                            <button onClick={() => {deleteHandler(friendStatus.id)}} className='btn btn-primary'>
                                Eliminar
                            </button>
                        }
                        { friendStatus.status == 'RECEIVED' &&
                            <button onClick={() => {acceptHandler(friendStatus.id)}} className='btn btn-primary'>
                                <FontAwesomeIcon icon={faFeed} /> Aceptar Solicitud
                            </button>
                        }
                        { friendStatus.status == 'PENDING' &&
                            <button className='btn btn-primary'>
                                Solicitud Enviada
                            </button>
                        }
                        { friendStatus == "" &&
                            <button onClick={() => {handleFriendRequest(userId, profileId)}} className='btn btn-primary'>
                                <FontAwesomeIcon icon={faFeed} /> Enviar Solicitud
                            </button>
                        }
                        
                        <button className='btn'>
                            <FontAwesomeIcon icon={faLink} />
                        </button>

                    </>
                    }
                </div>
                <p className='bio'>
                    {user.description}
                </p>
            </div>
        </div>
    )
}