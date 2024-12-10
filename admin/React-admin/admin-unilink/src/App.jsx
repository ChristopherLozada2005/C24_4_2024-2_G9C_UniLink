import React from "react";
import { BrowserRouter as Router, Route, Routes, useLocation } from "react-router-dom";
import UsuariosList from "./pages/UsuariosList";
import UsuarioForm from "./pages/UsuarioForm";
import UsuarioData from "./pages/UsuarioData";
import EditarUsuario from "./pages/EditarUsuario";
import PublicacionesList from "./pages/PublicacionesList";
import ComentariosList from "./pages/ComentariosList";
import LoginPage from "./pages/LoginPage";
import Navbar from "./components/Navbar";

import LayoutConNavbar from "./pages/LayoutConNavbar";
import LayoutSinNavbar from "./pages/LayoutSinNavbar";

const App = () => {
    return (
      <Router>
        <Navbar />
        <div className="body">
          <Routes>
            <Route path="/" element={<LoginPage />} />
            <Route path="/usuarios" element={<UsuariosList />} />
            <Route path="/crear-usuario" element={<UsuarioForm />} />
            <Route path="/editar-usuario/:id" element={<EditarUsuario />} />
            <Route path="/data-usuario/:id" element={<UsuarioData />} />
            <Route path="/publicaciones" element={<PublicacionesList />} />
            <Route path="/comentarios" element={<ComentariosList />} />
          </Routes>
        </div>
      </Router>
    );
  };

export default App;