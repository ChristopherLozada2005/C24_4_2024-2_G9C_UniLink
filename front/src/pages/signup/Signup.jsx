import React, { useState } from 'react';
import Logo from '../../assets/icon/logo.png';
import LoginService from '../../services/LoginService';
import './signup.css';
import { useNavigate } from 'react-router-dom';

const Signup = () => {

    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        name: '',
        lastName: '',
        age: '',
        gender: '',
        username: '',
        password: '',
        aceptaTerminos: false
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        console.log(name, value)
        setFormData({...formData, [name]: value});
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        LoginService.register(formData)
        // Handle signup logic here
        console.log('Signup attempted with:', formData);
        alert("Usuario Registrado: ", formData.username);
        navigate("/login");
    };

    return (
        <div className="login-container">
        <div className="login-card">
            <div className="logo-wrapper">
            <img src={Logo} alt="UniLink Logo" className="logo" />
            </div>
            <button className="button button-outline">
            <svg xmlns="http://www.w3.org/2000/svg" x="0px" y="0px" width="22" height="22" viewBox="0 0 48 48">
                <path fill="#fbc02d" d="M43.611,20.083H42V20H24v8h11.303c-1.649,4.657-6.08,8-11.303,8c-6.627,0-12-5.373-12-12	s5.373-12,12-12c3.059,0,5.842,1.154,7.961,3.039l5.657-5.657C34.046,6.053,29.268,4,24,4C12.955,4,4,12.955,4,24s8.955,20,20,20	s20-8.955,20-20C44,22.659,43.862,21.35,43.611,20.083z"></path><path fill="#e53935" d="M6.306,14.691l6.571,4.819C14.655,15.108,18.961,12,24,12c3.059,0,5.842,1.154,7.961,3.039	l5.657-5.657C34.046,6.053,29.268,4,24,4C16.318,4,9.656,8.337,6.306,14.691z"></path><path fill="#4caf50" d="M24,44c5.166,0,9.86-1.977,13.409-5.192l-6.19-5.238C29.211,35.091,26.715,36,24,36	c-5.202,0-9.619-3.317-11.283-7.946l-6.522,5.025C9.505,39.556,16.227,44,24,44z"></path><path fill="#1565c0" d="M43.611,20.083L43.595,20L42,20H24v8h11.303c-0.792,2.237-2.231,4.166-4.087,5.571	c0.001-0.001,0.002-0.001,0.003-0.002l6.19,5.238C36.971,39.205,44,34,44,24C44,22.659,43.862,21.35,43.611,20.083z"></path>
            </svg>
            Iniciar Sesión con google
            </button>
            <button className="button button-outline">
            <svg className="button-icon" viewBox="0 0 24 24">
                <path d="M20 4H4c-1.1 0-2 .9-2 2v12c0 1.1.9 2 2 2h16c1.1 0 2-.9 2-2V6c0-1.1-.9-2-2-2zm0 4l-8 5-8-5V6l8 5 8-5v2z" />
            </svg>
            Iniciar Sesión con correo electrónico
            </button>
            <div className="divider">
            <span>o</span>
            </div>
            <form onSubmit={handleSubmit}>
            <input
                type="text"
                name="name"
                placeholder="Nombre"
                defaultValue={formData.name}
                onChange={handleChange}
                className="input"
                required
            />
            <input
                type="text"
                name="lastName"
                placeholder="Apellido"
                defaultValue={formData.lastName}
                onChange={handleChange}
                className="input"
                required
            />
            <input
                type="number"
                name="age"
                placeholder="Edad"
                defaultValue={formData.age}
                onChange={handleChange}
                className="input"
                required
            />
            <select
                name="gender"
                defaultValue={formData.gender}
                onChange={handleChange}
                className="input"
                required
            >
                <option value="">Género</option>
                <option value="masculino">Masculino</option>
                <option value="femenino">Femenino</option>
                <option value="otro">Otro</option>
            </select>
            <input
                type="email"
                name="username"
                placeholder="Correo"
                defaultValue={formData.username}
                onChange={handleChange}
                className="input"
                required
            />
            <input
                type="password"
                name="password"
                placeholder="Contraseña"
                defaultValue={formData.password}
                onChange={handleChange}
                className="input"
                required
            />
            <div className="checkbox-container">
                <input
                type="checkbox"
                name="aceptaTerminos"
                id="aceptaTerminos"
                checked={formData.aceptaTerminos}
                onChange={handleChange}
                required
                />
                <label htmlFor="aceptaTerminos">
                Acepto las políticas y términos de privacidad
                </label>
            </div>
            <button type="submit" className="button button-primary">
                Continue
            </button>
            </form>
            <p className="text-center text-sm text-gray">
            ¿Tienes una cuenta? <a href="/login" className="link">Iniciar Sesión</a>
            </p>
        </div>
        </div>
    );
};

export default Signup;