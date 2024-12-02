import './notification.css';

import Notify from '../../components/notify/notify';

export default function Notification() {
    return (
        <div className="notification-page">
            <h2 className="notification-title">Notifications</h2>
            <Notify />
        </div>
    );
}
