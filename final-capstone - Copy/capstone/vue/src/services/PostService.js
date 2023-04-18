import axios from 'axios';

export default {

  getPostsFromUser(){
    return axios.get('/posts/user');
  },
  getAllPosts(){
    return axios.get('posts');
  },
  getSpecificPost(id){
   return axios.get(`/posts/${id}`);
  },
  createPost(post){
    return axios.post('/posts',post);
  },
  updatePost(post){
    return axios.put('/posts',post);
  },
  deletePost(id){
    return axios.delete(`/posts/${id}`);
  }

}