<template>
  <div>
    
    <v-img class="pic" v-if="profile.profilePicture == ''" src="../assets/generic profile pic.webp" alt="User Profile Picture"></v-img>
    <v-img  class="pic" v-if="profile.profilePicture !== ''" :src="profile.profilePicture" alt="User Profile Picture"></v-img>
 
    <table>
      <tr>
        <th> Display Name  </th>
      </tr>
      <tr>
        <td>{{ profile.displayName }}</td>
      </tr>
    </table>
    <button v-if="profile=null" @click="createProfileView">Create Profile</button>
    <button v-else @click="editProfileView" >Update Profile</button>
  </div>
</template>

<script>

import ProfileService from '../services/ProfileService'
export default {
  
name:'profile',
data(){
  return{
    profile:{}
  }
},
created(){
ProfileService.getProfile().then((response)=>{
  this.profile=response.data;
})
},
methods:{
  editProfileView(){
    this.$router.push('/profile/update')
  },
  createProfileView(){
    this.$router.push('/profile/create')
  }
}
}
</script>

<style>

</style>