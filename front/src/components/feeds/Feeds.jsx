import './feeds.css';

// Components................................
import Feed from './Feed';
import { useUser } from '../../context/UserContext';
import { useState } from 'react';
import PostService from '../../services/PostService';


// Facke Apis................................
// import HomeFeedData from '../../FackApis/HomeFeedData';

export default function Feeds({ feeds, stompClient }){

    const { userId } = useUser().user;

    const handleFeedDelete = async (postId, imgUrl) => {
        if (imgUrl != "") {
            try {
                const response = await fetch(`https://api.cloudinary.com/v1_1/dade42bjv/image/destroy`, {
                    method: "POST",
                    body: JSON.stringify({
                        public_id: imgUrl,
                        api_key: "436915484695172",
                    }),
                    headers: {
                        "Content-Type": "application/json",
                    },
                });
        
                const data = await response.json();
                console.log("Respuesta de la eliminación:", data);
        
                if (data.result === "ok") {
                    console.log("Imagen eliminada con éxito");
                    // Aquí podrías actualizar el estado para reflejar que la imagen fue eliminada, si es necesario
                } else {
                    console.error("Error al eliminar la imagen:", data);
                }
            } catch (error) {
                console.error("Error al hacer la solicitud de eliminación:", error);
            }
        }
        PostService.deletePostById(postId);
        console.log("Post Deleted");
        stompClient.send("/app/message", {}, JSON.stringify({ senderName: "name", message: "Post Deleted"}));
    } 

    return (
        <div className='feeds'>
            {
                feeds.map(fed=>(
                    <Feed
                        fed={fed} key={fed.id}
                        userId={userId}
                        handleFeedDelete={handleFeedDelete}
                        stompClient={stompClient}
                     />
                ))
            }
        </div>
    )
}