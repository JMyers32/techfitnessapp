<template>
  <div class="teamDisplay">
  
    <team-display/>
  </div>
</template>

<script>
import TeamDisplay from '../components/TeamDisplay.vue'
export default {
  components: { TeamDisplay },

}
</script>

<style scoped>

.teamDisplay{
  width:100vw;
  height:100vh;
}
h2{
 text-align: center;
}

</style>