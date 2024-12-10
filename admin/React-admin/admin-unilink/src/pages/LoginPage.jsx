import { useNavigate } from "react-router-dom"

const LoginPage = () => {

  const navigate = useNavigate();


  const handleLogin = () => {
    navigate("/usuarios")
  }

  return (
    <div className="usuarios-form">
      <h1>Login</h1>
      <form>
          <div className="input">
              <label>Email:</label>
              <input
                  type="email"
              />
          </div>
          <div className="input">
              <label>Contraseña:</label>
              <input
                  type="password"
              />
          </div>
          <div>
              <button type="button" onClick={handleLogin}>Ingresar</button>
          </div>
      </form>
    </div>
  )
}

export default LoginPage;