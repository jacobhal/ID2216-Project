<template>
  <div>
    <v-toolbar app flat>
      <v-btn icon @click="goBack">
        <v-icon>fa-angle-left</v-icon>
    </v-btn>
    </v-toolbar>
    <v-container grid-list-md text-xs-center>
      <h1 class="display-2">All Tabs</h1>
      <div v-for="tab in tabs" :key="tab.id">
        <v-layout row wrap>
          <v-flex xs12>
              <!-- Add component for one single tab here -->
            <a @click="routeToEditTabScreen(tab.id)">
              <h3>{{ tab.title }} ({{ tab.running ? 'Running' : 'Closed' }})</h3>
            </a>
            <v-btn @click="deleteTab(tab)">Delete</v-btn>
            <v-btn @click="toggleTab(tab)">Toggle status</v-btn>
          </v-flex>
        </v-layout>
      </div>
      <input v-model='tabTitle' placeholder="Tab title">
      <v-btn @click="addTab">Add new tab</v-btn>
    </v-container>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  name: 'AllTabsSceen',
  data () {
    return {
      tabTitle: '',
      persons: ['1', '2', '3'],
      receipts: ['2', '3', '4']
    }
  },
  methods: {
    goBack: function () {
      this.$router.back()
    },
    routeToEditTabScreen: function (id) {
      this.$router.push({name: 'EditTabScreen', params: { id: id }})
    },
    // Store methods
    getTab (e) {
      this.$store.dispatch('getTab', e.target.value)
    },
    addTab () {
      this.$store.dispatch('addTab', {
        title: this.tabTitle,
        persons: this.persons,
        receipts: this.receipts,
        running: true
      })
    },
    deleteTab (tab) {
      this.$store.dispatch('deleteTab', tab)
    },
    toggleTab (tab) {
      this.$store.dispatch('toggleTab', tab)
    }
  },
  computed: {
    ...mapGetters([
      'tabs'
    ])
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
