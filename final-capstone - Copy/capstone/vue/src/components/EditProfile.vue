<template>
  <div>
<h1>Edit Profile</h1>
<form @submit.prevent="editProfile">
    <label for="displayName">Display Name: </label>
    <input type="text" name="displayName" id="displayName" v-model="profile.displayName">
    <label for="profilePicture">Profile Picture URL: </label>
    <input type="text" name="profilePicture" id="profilePicture" v-model="profile.profilePicture">
    <button type="submit">Update Profile</button>
</form>
  </div>
</template>

<script>
import ProfileService from '../services/ProfileService.js';
export default {
name:'edit-profile',
data(){
    return{
        profile:{
            
        }
    }
},
created(){
ProfileService.getProfile().then((response)=>{
    this.profile=response.data;
})
},
methods:{
    editProfile(){
       ProfileService.updateProfile(this.profile).then((response)=>{
        if(response.status==200){
            alert("Success! Profile updated.")
            this.$router.go();
        }
       })
    }
}
}
</script>

<style>

</style>