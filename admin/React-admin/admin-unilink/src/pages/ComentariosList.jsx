import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { getComentario, deleteComentario, getComentarios } from "../services/api";

const ComentariosList = () => {
    const [comentarios, setComentarios] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        fetchComentarios();
    }, []);

    const fetchComentarios = async () => {
        try {
            const response = await getComentarios();
            console.log(response.data)
            setComentarios(response.data);
        } catch (error) {
            console.error("Error fetching comentarios:", error);
        }
    };

    const handleDelete = async (id) => {
        if (window.confirm("¿Estás seguro de eliminar este comentario?")) {
            try {
                await deleteComentario(id);
                fetchComentarios();
            } catch (error) {
                console.error("Error deleting comentario:", error);
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
            <h1>Lista de Comentarios</h1>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Texto</th>
                        <th>Fecha Comentario</th>
                        <th>Usuario</th>
                        <th>Publicacion</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    {comentarios.map((comentario) => (
                        <tr key={comentario.id}>
                            <td>{comentario.id}</td>
                            <td>{comentario.texto}</td>
                            <td>{formatteDate(comentario.fecha_comentario)}</td>
                            <td>
                                <button onClick={() => handleUserInfo(comentario.usuario)}>
                                    {comentario.usuario}
                                </button>
                            </td>
                            <td>{comentario.publicacion}</td>
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

export default ComentariosList;