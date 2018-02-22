<template>
  <div>
    <v-toolbar app>
      <v-btn icon @click="goBack">
        <v-icon>fa-angle-left</v-icon>
    </v-btn>
    </v-toolbar>
    <v-container grid-list-md text-xs-center>
      <h1 class="display-2">All Tabs</h1>
      <a v-for="tab in getTabs">
        <v-layout row wrap>
          <v-flex xs12>
            <!-- Add component for one single tab here -->
            <h5>{{ tab.title }}</h5>
          </v-flex>
          <v-flex xs12>
            <!-- Add component for one single tab here -->
          </v-flex>
        </v-layout>
      </a>
      <v-btn @click="addTab('Test')">Add new tab</v-btn>
    </v-container>
  </div>
</template>

<script>
// Add mapGetters from Vuex
import { mapGetters, mapActions, mapMutations } from 'vuex'
export default {
  name: 'AllTabs',
  data () {
    return {
    }
  },
  methods: {
    goBack: function () {
      this.$router.back()
    },
    ...mapMutations([
      'addTab'
    ])
  },
  // Since Vuex stores are reactive, the simplest way to "retrieve" state from it is simply returning some
  // store state from within a computed property:
  computed: mapGetters([
    'getTabs'
  ])
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
