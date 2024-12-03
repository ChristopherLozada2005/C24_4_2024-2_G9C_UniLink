import { createContext, useContext, useEffect, useState } from 'react';
import Cookies from 'js-cookie';
import { jwtDecode } from 'jwt-decode';

const UserContext = createContext();

export function UserProvider({ children }) {
  const [user, setUser] = useState({});

  const getUserFromToken = () => {
    const token = Cookies.get("authToken");
    if (token) {
      try {
        const decodedToken = jwtDecode(token);
        const userData = {
          userId: decodedToken.userId,
          username: decodedToken.sub,
          name: decodedToken.userName,
          hasImage: decodedToken.hasImage,
        }
        return userData;
      } catch (error) {
        console.error("Error al decodificar el token:", error);
        return null;
      }
    }
    return null;
  };

  useEffect(() => {
    const userData = getUserFromToken();
    console.log("Token decodificado:", Cookies.get("authToken"));
    console.log("Usuario obtenido del token: ", userData);
    setUser(userData);
  }, []);

  return (
    <UserContext.Provider value={{ user, setUser }}>
      {children}
    </UserContext.Provider>
  );
}

export function useUser() {
  return useContext(UserContext);
}
