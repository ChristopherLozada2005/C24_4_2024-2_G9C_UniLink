import axiosInstance from "./axiosConfig";

const POST_BASE_URL = "/posts";

class PostService {
  getAllPosts() {
    return axiosInstance.get(POST_BASE_URL);
  }
  getAllPostsByCategory(category) {
    return axiosInstance.get(`${POST_BASE_URL}/category/${category}`);
  }
  getUserPosts(userId) {
    return axiosInstance.get(`${POST_BASE_URL}/user/${userId}`);
  }
  getUserPostsByCategory(userId, category) {
    return axiosInstance.get(`${POST_BASE_URL}/category/${userId}/${category}`);
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