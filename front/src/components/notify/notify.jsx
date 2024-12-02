import './notify.css';
import { Link } from 'react-router-dom';

// FackApis..............
import NotifyData from "../../FackApis/NotifyData";

export default function Notify() {
    return (
        <div className='Notifications'>
            <div className='notify-top'></div>
            <div className='border-div'></div>
            {
                NotifyData.map(notify => (
                        <div className='notification'>
                            <div className='icon'>
                                <img src={notify.img} alt="" />
                            </div>
                            <div className='notify-body'>
                                <h5>{notify.title}</h5>
                                <p>{notify.description}</p>
                            </div>
                        </div>
                ))
            }
        </div>
    );
}
