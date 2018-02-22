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
      <v-toolbar flat>
        <v-list>
          <v-list-tile>
            <v-list-tile-title class="title">
              Menu
            </v-list-tile-title>
          </v-list-tile>
        </v-list>
      </v-toolbar>
      <v-list two-line>
        <v-list-tile @click="routeToWelcomeScreen">
          <v-list-tile-action>
            <v-icon>home</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <v-list-tile-title>Home</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>

        <v-list-tile @click="routeToAllTab">
          <v-list-tile-action>
            <v-icon>assignment</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <v-list-tile-title>Tabs</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
      </v-list>
    </v-navigation-drawer>
  </div>
</template>

<script>
export default {
  name: 'NavDrawer',
  data () {
    return {
      openDrawer: false,
      pathToAllTabs: '/allTabs',
      pathToWelcomeScreen: '/'
    }
  },
  methods: {
    routeToAllTab: function () {
      if (this.$route.name !== 'AllTabs') {
        this.$router.push(this.pathToAllTabs)
      } else {
        this.openDrawer = false
      }
    },

    routeToWelcomeScreen: function () {
      if (this.$route.name !== 'WelcomeScreen') {
        this.$router.push(this.pathToWelcomeScreen)
      } else {
        this.openDrawer = false
      }
    }

  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
