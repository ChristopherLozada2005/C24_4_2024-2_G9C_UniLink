import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { getUsuario, updateUsuario } from "../services/api";

const EditarUsuario = () => {
    const { id } = useParams();
    const navigate = useNavigate();
    const [nombre, setNombre] = useState("");
    const [email, setEmail] = useState("");
    const [contraseña, setContraseña] = useState("");

    useEffect(() => {
        const fetchUsuario = async () => {
            try {
                const response = await getUsuario(id);
                setNombre(response.data.nombre);
                setEmail(response.data.email);
            } catch (error) {
                console.error("Error fetching usuario:", error);
            }
        };
        fetchUsuario();
    }, [id]);

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await updateUsuario(id, { nombre, email, contraseña });
            alert("Usuario actualizado con éxito.");
            navigate("/usuarios");
        } catch (error) {
            console.error("Error updating usuario:", error);
        }
    };

    const handleBack = () => {
        navigate("/usuarios");
    };

    return (
        <div className="usuarios-form">
            <h1>Editar Usuario</h1>
            <form onSubmit={handleSubmit}>
                <div className="input">
                    <label>Nombre:</label>
                    <input
                        type="text"
                        value={nombre}
                        onChange={(e) => setNombre(e.target.value)}
                    />
                </div>
                <div className="input">
                    <label>Email:</label>
                    <input
                        type="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                    />
                </div>
                <div className="input">
                    <label>Contraseña:</label>
                    <input
                        type="password"
                        value={contraseña}
                        onChange={(e) => setContraseña(e.target.value)}
                    />
                </div>
                <button className="button-1" type="submit">Actualizar</button>
                <button type="button" onClick={handleBack}>Regresar</button>
            </form>
        </div>
    );
};

export default EditarUsuario;