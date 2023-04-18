<template>
    <div class="page">
     <img v-if="isLoading" src="../assets/harambe-gorilla.gif" alt="">
     <table class="teams" v-else>
      <tr>
        <th>Team Name</th>
        <th>Wins </th>
        <th>Losses </th>
        <th>Ties </th>
        <th>Total Points</th>
        <th>Points Againts</th>
      </tr>
      <tr v-for="team in teams" :key="team.id">
      <td>{{ team.location }} {{ team.nickname }}</td>
      <td>{{ team.record.overall.wins }}</td>
      <td>{{ team.record.overall.losses }}</td>
      <td>{{ team.record.overall.ties }}</td>
      <td>{{ team.record.overall.pointsFor }}</td>
      <td>{{ team.record.overall.pointsAgainst }}</td>
      </tr>
     </table>
    </div>
  </template>
  
  <script>
  import espnService from '../services/EspnService.js'
  export default {
    name:'team-display',
   data(){
    return{
      teams:[],
      isLoading:true,
    }
   },
  
   computed:{
     
   },
  
   created(){
   setTimeout(()=>{
    this.isLoading=false
   },1000)
    espnService.getTeams(1136454).then(teams=>{
      this.teams=teams;
    });
  
   },

   methods:{
    
   }
  }
  
  
  
  </script>
  
  <style scoped>


   .teams{
    font-family:sans-serif;
    border-collapse: collapse;
    margin:25px 0;
    font-size: 0.9em;
    font-weight: bold;
    min-width:400px;
    box-shadow: 0 0 20px rgba(0,0,0,0.15);
    margin-left: auto;
    margin-right:auto;


    
   }

   table{
    box-shadow: 10px 10px 5px #ccc;
   }
   

table, th, td{
  border: 1px solid;
}

th,td{
  padding:12px 15px;
}

   td{
    text-align: left;
   }
   th{
    background-color: #009879;
    color:#ffffff;
    text-align: left;
   }
   tbody, tr{
    border-bottom:1px solid #dddddd;
   }
   tbody, tr:nth-of-type(even){
    background-color: #f3f3f3;
   }

   tr:nth-of-type(odd){
    background-color:grey;
   }
   tbody, tr:last-of-type{
    border-bottom: 2px solid #009879;
   }
   tbody tr.active-row{
    font-weight: bold;
    color:#009879;
   }

 img{
  display: block;
  margin:0 auto;
  padding-top: 10vh;
  border-radius:70%;
 }
  
  </style>