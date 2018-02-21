<template>
  <div>
    <v-toolbar app>
      <v-btn icon @click="goBack">
        <v-icon>fa-angle-left</v-icon>
    </v-btn>
    </v-toolbar>
    <v-container grid-list-md text-xs-center>
      <h1 class="display-2">All Tabs</h1>
      <v-layout row wrap>
        <v-flex xs12>
          <!-- Add component for one single tab here -->
        </v-flex>
        <v-flex xs12>
          <!-- Add component for one single tab here -->
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
export default {
  name: 'AllTabs',
  data () {
    return {
    }
  },
  methods: {
    goBack: function () {
      this.$router.back()
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
