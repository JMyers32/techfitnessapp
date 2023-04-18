<template>
  <v-app>
  
    <v-main>
      <nav-panel/>
      <router-view/>
    </v-main>
  </v-app>
</template>

<script>
import NavPanel from './components/NavPanel.vue';

export default {
  components: { NavPanel },
  name: 'App',

  data: () => ({
    //
  }),
};
</script>
