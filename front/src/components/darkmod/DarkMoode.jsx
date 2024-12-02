import './darkMood.css';

// FontAwesomeIcon.............
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faLightbulb } from "@fortawesome/free-solid-svg-icons";


export default function DarkTheme(){

    const DarkHandele = () => {
        document.querySelector('body').classList.toggle('darkmood');
    }

    return (
        <div className='dark-mode-icon'>
            <FontAwesomeIcon icon={faLightbulb} onClick={DarkHandele} />
        </div>
    )
}