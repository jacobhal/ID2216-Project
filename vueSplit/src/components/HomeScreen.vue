<template>
  <div>
    <v-toolbar app>
      <v-btn icon @click="openDrawer = !openDrawer">
        <v-icon>list</v-icon>
    </v-btn>
    </v-toolbar>
    <v-navigation-drawer
      app
      temporary
      v-model="openDrawer"
      absolute
    >
      <v-list>
        <v-list-tile>
          <v-list-tile-content>
            <v-icon>list</v-icon>
            <v-list-tile-title>test</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
      </v-list>
    </v-navigation-drawer>
    <v-container fluid>

    </v-container>
  </div>
</template>

<script>
export default {
  name: 'HomeScreen',
  data () {
    return {
      openDrawer: false
    }
  },
  methods: {}
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
