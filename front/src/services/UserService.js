import axiosInstance from "./axiosConfig";

const POST_BASE_URL = "/users";

class UserService {
  getUserProfile(userId) {
    return axiosInstance.get(`${POST_BASE_URL}/${userId}`);
  }
  getUserProfileByUsername(username) {
    return axiosInstance.get(`${POST_BASE_URL}/username/${username}`);
  }
}

export default new UserService();