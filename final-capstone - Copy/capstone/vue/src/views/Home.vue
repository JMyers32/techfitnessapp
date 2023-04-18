<template>
  <div>
    <div v-if="isLoading">
      <img id="gorilla" src="../assets/harambe-gorilla.gif" alt="">
    </div>
    <div id="home" v-else>

    </div>
  </div>
</template>

<script>

// @ is an alias to /src


export default {
  name: 'home',
  data(){
    return{
      isLoading:true
    }
  },
  components: {
    
    
  },
  created(){
    setTimeout(()=>{
      this.isLoading=false;
    },1000)
  }
}
</script>
<style scoped>

#gorilla{
  display: block;
  margin:0 auto;
  padding-top: 10vh;
  border-radius:70%;
 }


</style>
