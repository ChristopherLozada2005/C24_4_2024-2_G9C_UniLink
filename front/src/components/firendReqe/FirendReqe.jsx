import './firendReqe.css';
import { Link } from 'react-router-dom';

// FackApis..............
import FirendReqData from '../../FackApis/FirendReqData';

import DefaultProfileImage from '../../assets/img/defaultProfilePicture.png';


import { useUser } from '../../context/UserContext';
import FriendshipService from '../../services/FriendshipService';
import { useEffect, useState } from 'react';

export default function FirendReqe({ friendRequests, stompClient }){

    const acceptHandler = async (friendshipId) => {
        try {
            const response = await FriendshipService.updateFriendshipStatus(friendshipId);
            console.log('FriendRequest updated: ', response.data);
            stompClient.send("/app/message", {}, JSON.stringify({ senderName: "name", message: "FR-MOD"}));
        } catch (error) {
            console.error('Error updating friend request:', error);
        }
    };

    const deleteHandler = async (friendshipId) => {
        try {
            const response = await FriendshipService.deleteFriendship(friendshipId);
            console.log('FriendRequest deleted: ', response.data);
            stompClient.send("/app/message", {}, JSON.stringify({ senderName: "name", message: "FR-MOD"}));
        } catch (error) {
            console.error('Error deleting friend request:', error);
        }
    };

    return(
        <div className='Firend-Requests'> 
            <h4>Friends Requests</h4>

            {
                friendRequests.map(friendRq=>(
                    <div className='request'>
                        <Link to={`/profile/${friendRq.secondUserId}`}>
                        <div className='info' key={friendRq.id}>
                            <div className='user'>
                                { friendRq.secondUserHasImage == 'yes'?
                                    <img src={`https://res.cloudinary.com/dade42bjv/image/upload/f_auto,q_auto/profile${friendRq.secondUserId}-image`} alt='' />
                                        :
                                    <img src={DefaultProfileImage}/>
                                } 
                                <h5>{friendRq.secondUserName}</h5>
                            </div>
                            <div className='info-name'>                                
                                <p>{"2 amigos en común"}</p>
                            </div>
                        </div>
                        </Link>

                        <div className="action">
                            <button onClick={() => {acceptHandler(friendRq.id)}} className='btn btn-primary'>Accept</button>
                            <button onCanPlay={() => {deleteHandler(friendRq.id)}}  className='btn btn-red'>Delete</button>
                        </div>
                    </div>
                ))
            }
        </div>
    )
}