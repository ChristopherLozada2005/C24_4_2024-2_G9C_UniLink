import React from "react";
import { Navigate, Outlet } from "react-router-dom";

const PublicRoute = ({ children }) => {
  const isAuthenticated = !!document.cookie
    .split("; ")
    .find(row => row.startsWith("authToken="));

  return isAuthenticated ? <Navigate to="/" /> : children || <Outlet />;
};

export default PublicRoute;
