import './userProfile.css';
import { Link } from 'react-router-dom';

// Facke Apis................................
import CurrentUserData from '../../FackApis/CurrentUserData';

// Fontawesome icon...................
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faFeed, faLink, faMessage } from '@fortawesome/free-solid-svg-icons';
import { useUser } from '../../context/UserContext';

export default function UserProfile({ user }){

    const { userId }  = useUser();

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
                <img src={CurrentUserData.map(user=>(user.ProfieImage))} alt='' />
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
                        <button className='btn btn-primary'>
                            <FontAwesomeIcon icon={faFeed} /> sígueme
                        </button>
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