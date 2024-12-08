import axiosInstance from "./axiosConfig";

const POST_BASE_URL = "/posts";

class PostService {
  getAllPosts() {
    return axiosInstance.get(POST_BASE_URL);
  }
  getUserPosts(userId) {
    return axiosInstance.get(`${POST_BASE_URL}/user/${userId}`);
  }
  getUserPostsByUsername(username) {
    return axiosInstance.get(`${POST_BASE_URL}/username/${username}`);
  }
  createPost(post) {
    return axiosInstance.post(POST_BASE_URL, post);
  }
  deletePostById(postId) {
    return axiosInstance.delete(`${POST_BASE_URL}/${postId}`);
  }
}

export default new PostService();