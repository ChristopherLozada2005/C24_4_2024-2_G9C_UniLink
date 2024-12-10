import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { getUsuarios, deleteUsuario } from "../services/api";

const UsuariosList = () => {
    const [usuarios, setUsuarios] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        fetchUsuarios();
    }, []);

    const fetchUsuarios = async () => {
        try {
            const response = await getUsuarios();
            setUsuarios(response.data);
        } catch (error) {
            console.error("Error fetching usuarios:", error);
        }
    };

    const handleDelete = async (id) => {
        if (window.confirm("¿Estás seguro de eliminar este usuario?")) {
            try {
                await deleteUsuario(id);
                fetchUsuarios();
            } catch (error) {
                console.error("Error deleting usuario:", error);
            }
        }
    };

    const handleEdit = (id) => {
        navigate(`/editar-usuario/${id}`);
    };

    const handleCreate = () => {
        navigate("/crear-usuario");
    };

    const handleUserInfo = (userId) => {
        navigate(`/data-usuario/${userId}`)
    }

    return (
        <div className="usuarios-box">
            <h1>Lista de Usuarios</h1>
            <button onClick={handleCreate}>Crear Usuario</button>
            <table className="table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Email</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    {usuarios.map((usuario) => (
                        <tr key={usuario.id}>
                            <td>
                                <button onClick={() => handleUserInfo(usuario.id)}>
                                    {usuario.id}
                                </button>
                            </td>
                            <td>{usuario.nombre}</td>
                            <td>{usuario.email}</td>
                            <td>
                                <button className="button-1" onClick={() => handleDelete(usuario.id)}>Eliminar</button>
                                <button onClick={() => handleEdit(usuario.id)}>Editar</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default UsuariosList;