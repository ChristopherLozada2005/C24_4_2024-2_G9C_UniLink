import { useEffect, useState } from "react";
import DefaultProfileImage from "../assets/img/defaultProfilePicture.png"
import { useParams } from "react-router-dom";
import { getUsuario } from "../services/api";

const UsuarioData = () => {
  const { id } = useParams();

  const initUser = {
    id: "",
    email: "",
    nombre: "",
    apellido: "",
    descripcion: "",
    edad: "",
    genero: "",
    fecha_registro: "",
    tiene_imagen: "",
  };

  const [userData, setUserData] = useState(initUser);

  useEffect(() => {
    const fetchUsuario = async () => {
      try {
        const response = await getUsuario(id);
        setUserData(response.data);
        console.log(response.data);
      } catch (error) {
        console.error("Error fetching usuario:", error);
      }
    };
    fetchUsuario();
  }, [id]);

  return (
    <div className="usuario-data-box">
      <h1>Informacion del Usuario</h1>
      <div className="info">
        <div>
          {userData.tiene_imagen === 'yes' ? (
            <img
              className="user-profile-image"
              src={`https://res.cloudinary.com/dade42bjv/image/upload/f_auto,q_auto/profile${userData.id}-image`}
              alt="User Profile"
            />
          ) : (
            <img className="user-profile-image" src={DefaultProfileImage} alt="Default Profile" />
          )}
        </div>
        <div>
          <p><strong>Id:</strong> {userData.id}</p>
          <p><strong>Email:</strong> {userData.email}</p>
          <p><strong>Nombre:</strong> {userData.nombre}</p>
          <p><strong>Apellido:</strong> {userData.apellido}</p>
          <p><strong>Descripción:</strong> {userData.descripcion}</p>
          <p><strong>Edad:</strong> {userData.edad}</p>
          <p><strong>Género:</strong> {userData.genero}</p>
          <p><strong>Fecha de Registro:</strong> {new Date(userData.fecha_registro).toLocaleDateString()}</p>
        </div>
      </div>
    </div>
  );
};

export default UsuarioData;