<template>
  <v-app>
    <nav-drawer></nav-drawer>
    <v-content>
      <router-view></router-view>
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
