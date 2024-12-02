import axiosInstance from "./axiosConfig";

const POST_BASE_URL = "/comments";

class CommentService {
  getPostComments(postId) {
    return axiosInstance.get(`${POST_BASE_URL}/post/${postId}`);
  }
  postComment(comment) {
    return axiosInstance.post(`${POST_BASE_URL}`, comment)
  }
}

export default new CommentService();