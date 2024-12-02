import axiosInstance from "./axiosConfig";

const LOGIN_BASE_URL = "/login"
const REGISTER_BASE_URL = "/register"

class LoginService {
  login(credentials) {
    return axiosInstance.post(LOGIN_BASE_URL, credentials)
  }
  register(userDetails) {
    return axiosInstance.post(REGISTER_BASE_URL, userDetails)
  }
}

export default new LoginService()