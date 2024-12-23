import './nav.css'
import { Link } from "react-router-dom";

// Fake Api Data.............
import CurrentUser from "../../FackApis/CurrentUserData";

// Components.............
import DarkMoode from "../darkmod/DarkMoode";

// FontAwesome Icon.............
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faBars, faBell, faEnvelope, faHome, faSearch, faUser } from "@fortawesome/free-solid-svg-icons";
import { useUser } from '../../context/UserContext';

import DefaultProfileImage from '../../assets/img/defaultProfilePicture.png';


export default function Nav(){

    const { userId, name, hasImage } = useUser().user;

    return (
        <nav>
            <div className="nav-container">

    {/* .........................NavAria Left ..........................*/}
                <div className="nav-left">
                    <Link to="/">
                    <h3 className="logo">UniLink</h3>
                    </Link>
                    <Link to='/'>
                        <FontAwesomeIcon icon={faHome}/>
                    </Link>
                    <Link to={`/profile/${userId}`}>
                        <FontAwesomeIcon icon={faUser}/>
                    </Link>
                    <div className="Nav-Serchbar">
                        <FontAwesomeIcon icon={faSearch} />
                        <input type="search" />
                    </div>
                </div>

    {/* .........................NavAria Right ..........................*/}
                <div className="nav-right">
                    <Link to="/chatbox/id">
                        <FontAwesomeIcon icon={faEnvelope} />
                    </Link>
                    <Link to="/notification">
                        <FontAwesomeIcon icon={faBell} />
                    </Link>
                    <DarkMoode />
                    <Link to="/">
                        <FontAwesomeIcon icon={faBars} />
                    </Link>
                    <div className="user">
                        { hasImage == 'yes'?
                            <img src={`https://res.cloudinary.com/dade42bjv/image/upload/f_auto,q_auto/profile${userId}-image`} alt="User" />
                            :
                            <img src={DefaultProfileImage}/>
                        }
                        <h4>{name}</h4>
                    </div>
                </div>
            </div>
        </nav>
    )
}