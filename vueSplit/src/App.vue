<template>
  <v-app>
    <nav-drawer></nav-drawer>
    <v-content>
      <v-container fluid>
        <router-view></router-view>
      </v-container>
  </v-content>
  </v-app>
</template>

<script>
import NavDrawer from '@/components/NavDrawer'
export default {
  data () {
    return {
      openDrawer: false
    }
  },
  components: {
    NavDrawer
  },
  name: 'App'
}
</script>
