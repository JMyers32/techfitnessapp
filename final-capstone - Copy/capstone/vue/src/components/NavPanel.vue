<template>
  <div>
    <nav>
    <h1>12ADOH <img class="harambePic" src="../assets/harambe-meme-pic.jpg" alt=""></h1>  
    <router-link id="home" v-bind:to="{name:'home'}"><h3>Home</h3></router-link>
    <router-link id="teams" v-bind:to="{name:'teams'}">
    <h3>Current Team List</h3></router-link>
    <h3>Air Grievances</h3>
    <router-link id="champ" v-bind:to="{name:'champ'}"> <h3>Champion</h3></router-link>
    <router-link v-bind:to="{ name: 'login' }" v-if="this.$store.state.token == ''"><h3>Login</h3></router-link>
    <router-link v-bind:to="{ name: 'logout' }" v-if="this.$store.state.token != ''"><h3>Logout</h3></router-link>
  </nav>
  </div>
</template>

<script>
export default {
name:'NavPanel'
}
</script>

<style scoped>

h1{
    display:flex;
    align-items: center;
    justify-content: left;
}
.harambePic{
    width:75px;
    height:75px;
    border-radius:50%;
    float:left;
    margin-left:10px;
    
}
nav{
    display: flex;
    justify-content:space-evenly;
    align-items: center;
    background-color:#f0f0f0;
    text-decoration: none!important;
}
nav h3{
 display: inline-block;
 color:#333;
 text-decoration: none!important;
 font-size: 18px;
 margin-right:10px;
}
nav h3:hover{
    color:#00f;
}

#home, #teams, #champ{
    text-decoration: none;
}



</style>