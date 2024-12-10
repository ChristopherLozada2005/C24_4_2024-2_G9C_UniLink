import { Link, NavLink } from "react-router-dom";
import Logo from "../assets/img/logo.png"

const Navbar = () => {

  return (
    <div className="navbar">
      <div className="top">
        <h1>UniLink ADMIN</h1>
        <img src={Logo} alt="" />
      </div>
      <div className="buttons">
        <NavLink to={"/usuarios"} activeClassName="active-link">
          <button>Usuarios</button>
        </NavLink>
        <NavLink to={"/publicaciones"} activeClassName="active-link">
          <button>Publicaciones</button>
        </NavLink>
        <NavLink to={"/comentarios"} activeClassName="active-link">
          <button>Comentarios</button>
        </NavLink>
        <NavLink to={"/"} activeClassName="active-link">
          <button>Salir</button>
        </NavLink>
      </div>
    </div>
  )
}

export default Navbar;