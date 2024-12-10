import axios from "axios";

const API = axios.create({
    baseURL: "http://ec2-3-140-207-211.us-east-2.compute.amazonaws.com:8000/api/",
    headers: {
        'Content-Type': 'application/json'
    },
});

// Usuarios
export const getUsuarios = () => API.get("usuarios/");
export const getUsuario = (id) => API.get(`usuarios/${id}/`);
export const createUsuario = (data) => API.post("usuarios/", data);
export const updateUsuario = (id, data) => API.put(`usuarios/${id}/`, data);
export const deleteUsuario = (id) => API.delete(`usuarios/${id}/`);

// Publicaciones
export const getPublicaciones = () => API.get("publicaciones/");
export const getPublicacion = (id) => API.get(`publicaciones/${id}/`);
export const deletePublicacion = (id) => API.delete(`publicaciones/${id}/`);

export const getComentarios = () => API.get("comentarios/");
export const getComentario = () => API.get(`comentarios/${id}/`);
export const deleteComentario = (id) => API.delete(`comentarios/${id}/`);