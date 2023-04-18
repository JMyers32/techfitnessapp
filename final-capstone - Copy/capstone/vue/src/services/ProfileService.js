import axios from 'axios';

export default {

  getProfile(){
    return axios.get('/profile');
  },
  getAllProfiles(){
    return axios.get('profile/all');
  },
  createProfile(profile){
    return axios.post('/profile',profile);
  },
  updateProfile(profile){
    return axios.put('/profile',profile);
  },
  deleteProfile(){
    return axios.delete('/profile');
  }

}