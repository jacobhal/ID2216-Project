<template>
  <v-container>
    <v-layout row>
      <v-flex xs12>
        <h1 class="display-3 text-xs-center">VueSplit</h1>
        <p class="subheading text-xs-center">Please split all the bills. We love splittng bills, it's the best</p>
      </v-flex>
    </v-layout>
    <v-layout id="buttons" row align-content-center justify-center>
      <v-flex xs3>
        <v-btn
          class="text-xs-center"
          dark
          fab
          large
          color="red"
          @click="routeToHomeScreen"
        >
          <v-icon>add</v-icon>
        </v-btn>
      </v-flex>
    </v-layout>
  </v-container>
</template>


<script>
export default {
  name: 'WelcomeScreen',
  data () {
    return {
      pathToAllTabs: '/allTabs',
      pathToHomeScreen: '/home'
    }
  },
  methods: {
    routeToAllTab: function () {
      this.$router.push(this.pathToAllTabs)
    },
    routeToHomeScreen: function () {
      this.$router.push(this.pathToHomeScreen)
    }
  }
}
</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1 {
  margin-top: 40%
}
#buttons {
  margin-top: 50%
}
</style>
