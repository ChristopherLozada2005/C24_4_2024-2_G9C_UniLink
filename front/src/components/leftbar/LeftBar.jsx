import './leftBar.css';
import { Link } from 'react-router-dom';

// Components..............
import CurrentUser from "../../FackApis/CurrentUserData";
import { faBars, faBell, faEnvelope, faHome, faSearch, faUser } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";


// Icon Image.............
import Firend from "../../assets/icon/1.png"
import Message from "../../assets/icon/7.png"
import { useUser } from '../../context/UserContext';

export default function LeftBar(){

    const { userId } = useUser().user;

    return (
        <div className='leftBar'>
            <div className='left-container'>
                <div className='menu'>
                    <Link to={`/profile/${userId}`}>
                        <div className='items'>
                        <FontAwesomeIcon icon={faUser}/>
                            <h4>Perfil</h4>
                        </div>
                    </Link>

                    <Link to='/'>
                        <div className='items'>
                        <FontAwesomeIcon icon={faHome}/>
                            <h4>Home</h4>
                        </div>
                    </Link>
                    
                    <Link to='/chatbox/id'>
                        <div className='item'>
                            <img src={Message} alt='' />
                            <h4>Message</h4>
                        </div>
                    </Link>  

                    <Link to='/notification'>
                        <div className='items'>
                            <FontAwesomeIcon icon={faBell} />
                            <h4>Notifications</h4>
                        </div>
                    </Link>                                    
                </div>                    
            </div>
        </div>
    )
}