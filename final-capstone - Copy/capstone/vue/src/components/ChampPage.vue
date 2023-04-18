<template>
  <div>
    <div v-if="isLoading">
    <img  id="harambe" src="../assets/harambe-gorilla.gif" alt="">
</div>
<div v-else id="champ">
   <h1>Champion</h1>
   <img src="../assets/Dom.png" alt="">
   <p>Dom has unfortunately won the 2022 12ADOH championship. It was all luck and totally not deserved. </p>
</div>
  </div>
</template>

<script>
export default {
 name:'champ-page',
 data(){
    return{
        team:[],
        isLoading:true
    }
 },
 created(){
    setTimeout(()=>{
     this.isLoading=false;
    },1000)
    
    
 }

}
</script>

<style scoped>
#harambe{
  display: block;
  margin:0 auto;
  padding-top: 10vh;
  border-radius:70%;
 }

 #champ h1{
  display:flex;
  align-content: center;
  justify-content: center;
 }

 #champ img{
    display:block;
    margin:0 auto;
    clip-path:polygon(0 0, 100% 0, 100% 75%, 50% 100%, 0 75%);
 }

</style>