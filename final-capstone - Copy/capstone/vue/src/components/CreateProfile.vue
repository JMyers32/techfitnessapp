<template>
  <div>
   <h1>Create A Profile</h1>
   <form @submit.prevent="createProfile(this.profile)">
    <label for="displayName">Display Name: </label>
    <input type="text" name="displayName" id="displayName" v-model="this.profile.displayName">
    <label for="profilePicture">Profile Picture URL: </label>
    <input type="text" name="profilePicture" id="profilePicture" v-model="this.profile.profilePicture">
    <button type="submit">Create Profile</button>
   </form>
  </div>
</template>

<script>
import profileService from '../services/ProfileService.js'
export default {
    name:'create-profile',
  data(){
    return{
    profile:{}
  }
},
methods:{
    createProfile(profile){
        profileService.createProfile(profile).then((response)=>{
            if(response.status==200){
                alert('Profile created');
            }
        })
    }
}

}


</script>

<style>

</style>