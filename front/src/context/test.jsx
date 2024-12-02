import React, { useState } from 'react';

const AppContext = React.createContext();
const { Provider } = AppContext;

function AppProvider({children}) {
  const [userId, setUserId] = useState(localStorage.usuario);
  function login(userId) {
    setUserId(data.userId);
    localStorage.usuario = data.userId;
  }

  function logout() {
    setUsuario(null),
    localStorage.removeItem('usuario');
  }

  return (
    <Provider value={{user, login, logout}}>
      {children}
    </Provider>
  )
}

export { AppProvider, AppContext }