import './message.css';
import { Link } from 'react-router-dom';

//FackApis..............
import MessageData from "../../FackApis/MessageData";

import DefaultProfileImage from '../../assets/img/defaultProfilePicture.png';

// FontAwesome Icon............
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEdit, faSearch } from '@fortawesome/free-solid-svg-icons';
import FriendshipService from '../../services/FriendshipService';
import { useUser } from '../../context/UserContext';
import { useEffect, useState } from 'react';

export default function Message({ friendList }){

    const truncUsername = (username) => {
        return `@${username.split('@')[0]}`;
    }

    return (
        <div className='Meassages'>
            <div className='message-top'>
                <h4>Message</h4>
                <FontAwesomeIcon icon={faEdit} />
            </div>
            <div className='message-search'>
                <FontAwesomeIcon icon={faSearch} />
                <input type='search' placeholder='Search Message' />
            </div>
            <div className='border-div'></div>
            {
                friendList.map(mess=>(
                    <Link to={`/chatbox/${mess.secondUserId}`}>
                        <div className='message' key={mess.id}>
                            <div className='user'>
                                { mess.secondUserHasImage == 'yes'?
                                    <img src={`https://res.cloudinary.com/dade42bjv/image/upload/f_auto,q_auto/profile${mess.secondUserId}-image`} alt='' />
                                        :
                                    <img src={DefaultProfileImage}/>
                                } 
                                <div className='green-activate'></div>
                            </div>
                            <div className='message-body'>
                                <h5>{mess.secondUserName}</h5>
                                <p>{truncUsername(mess.secondUserUsername)}</p>
                            </div>
                        </div>
                    </Link>
                ))
            }
        </div>
    )
}