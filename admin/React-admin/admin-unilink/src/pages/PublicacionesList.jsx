import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { getPublicaciones, deletePublicacion } from "../services/api";

const PublicacionesList = () => {
    const [publicaciones, setPublicaciones] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        fetchPublicaciones();
    }, []);

    const fetchPublicaciones = async () => {
        try {
            const response = await getPublicaciones();
            setPublicaciones(response.data);
        } catch (error) {
            console.error("Error fetching publicaciones:", error);
        }
    };

    const handleDelete = async (id) => {
        if (window.confirm("¿Estás seguro de eliminar esta publicación?")) {
            try {
                await deletePublicacion(id);
                fetchPublicaciones();
            } catch (error) {
                console.error("Error deleting publicacion:", error);
            }
        }
    };

    const handleUserInfo = (userId) => {
        navigate(`/data-usuario/${userId}`)
    }

    const formatteDate = (datew) => {
        const date = new Date(datew);
        return `${date.getFullYear()}-${(date.getMonth() + 1).toString().padStart(2, '0')}-${date.getDate().toString().padStart(2, '0')} ${date.getHours().toString().padStart(2, '0')}:${date.getMinutes().toString().padStart(2, '0')}:${date.getSeconds().toString().padStart(2, '0')}`;
    }

    return (
        <div className="publicaciones-box">
            <h1>Lista de Publicaciones</h1>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Título</th>
                        <th>Descripción</th>
                        <th>Fecha Publicacion</th>
                        <th>Usuario</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    {publicaciones.map((publicacion) => (
                        <tr key={publicacion.id}>
                            <td>{publicacion.id}</td>
                            <td>{publicacion.titulo}</td>
                            <td>{publicacion.descripcion}</td>
                            <td>{formatteDate(publicacion.fecha_publicacion)}</td>
                            <td>
                                <button onClick={() => handleUserInfo(publicacion.usuario)}>
                                    {publicacion.usuario}
                                </button>
                            </td>
                            <td>
                                <button onClick={() => handleDelete(publicacion.id)}>Eliminar</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default PublicacionesList;