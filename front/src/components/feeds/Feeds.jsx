import './feeds.css';

// Components................................
import Feed from './Feed';


// Facke Apis................................
// import HomeFeedData from '../../FackApis/HomeFeedData';

export default function Feeds({ feeds }){
    return (
        <div className='feeds'>
            {
                feeds.map(fed=>(
                    <Feed fed={fed} key={fed.id} />
                ))
            }
        </div>
    )
}