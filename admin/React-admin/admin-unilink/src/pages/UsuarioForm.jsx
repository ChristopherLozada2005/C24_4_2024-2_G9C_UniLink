import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { createUsuario } from "../services/api";
import bcrypt from 'bcryptjs';

const UsuarioForm = () => {
    const [nombre, setNombre] = useState("");
    const [email, setEmail] = useState("");
    const [nonHashContraseña, setNonHasContraseña] = useState("");
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        const salt = bcrypt.genSaltSync(12);
        const contraseña = bcrypt.hashSync(nonHashContraseña, salt);
        try {
            await createUsuario({ nombre, email, contraseña });
            alert("Usuario creado con éxito.");
            setNombre("");
            setEmail("");
            setNonHasContraseña("");
        } catch (error) {
            console.error("Error creando usuario:", error);
        }
    };

    const handleBack = () => {
        navigate("/");
    };

    return (
        <div className="usuarios-form">
            <h1>Crear Usuario</h1>
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
                        value={nonHashContraseña}
                        onChange={(e) => setNonHasContraseña(e.target.value)}
                    />
                </div>
                <div>
                    <button className="button-1" type="submit">Guardar</button>
                    <button type="button" onClick={handleBack}>Regresar</button>
                </div>
            </form>
        </div>
    );
};

export default UsuarioForm;