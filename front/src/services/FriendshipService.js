import axiosInstance from "./axiosConfig";

const FRIENDSHIP_BASE_URL = "/friendship";

class FriendshipService {
  getFriendRequests(userId) {
    const status = "RECEIVED"
    return axiosInstance.get(`${FRIENDSHIP_BASE_URL}/status/${userId}/${status}`)
  }
  getFriendList(userId) { 
    const status = "ACCEPTED"
    return axiosInstance.get(`${FRIENDSHIP_BASE_URL}/status/${userId}/${status}`);
  }
  
  getFriendStatus(localUserId, secondUserId) {
    return axiosInstance.get(`${FRIENDSHIP_BASE_URL}/${localUserId}/${secondUserId}`);
  }

  createFriendship(body) {
    return axiosInstance.post(FRIENDSHIP_BASE_URL, body);
  }

  updateFriendshipStatus(friendshipId) {
    return axiosInstance.put(`${FRIENDSHIP_BASE_URL}/status/${friendshipId}`);
  }

  deleteFriendship(friendshipId) {
    return axiosInstance.delete(`${FRIENDSHIP_BASE_URL}/${friendshipId}`)
  }
}

export default new FriendshipService();
