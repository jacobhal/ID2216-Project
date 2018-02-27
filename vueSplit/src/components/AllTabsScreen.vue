<template>
  <div>
    <v-toolbar app>
      <v-btn icon @click="goBack">
        <v-icon>fa-angle-left</v-icon>
      </v-btn>
    </v-toolbar>
    <v-container grid-list-md text-xs-center>
      <h1 class="display-2">All Tabs</h1>
      <div v-for="tab in tabs" :key="tab.id">
        <v-layout row wrap>
          <v-flex v-if="matchesCurrentStatus(tab.running)" xs12>
            <!-- Add component for one single tab here -->
            <a @click="routeToEditTabScreen(tab.id)">
              <h3>{{ tab.title }}</h3>
            </a>
            <v-btn @click="deleteTab(tab)">Delete</v-btn>
            <v-btn @click="toggleTab(tab)">Toggle status</v-btn>
          </v-flex>
        </v-layout>
      </div>
    </v-container>
    <v-bottom-nav 
    absolute 
    :value="true" 
    :active.sync="e1" 
    color="transparent"
    full-width
    id="bottom-nav"
    >
    <v-btn flat color="red darken-2" value="running">
      <span>Running</span>
      <v-icon>lock_open</v-icon>
    </v-btn>
    <v-btn flat color="red darken-2" value="awaiting_payment">
      <span>Awaiting payment</span>
      <v-icon>lock_outline</v-icon>
    </v-btn>
  </v-bottom-nav>
</div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  name: 'AllTabsSceen',
  data () {
    return {
      e1: 'running',
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
    deleteTab (tab) {
      this.$store.dispatch('deleteTab', tab)
    },
    toggleTab (tab) {
      this.$store.dispatch('toggleTab', tab)
    },
    matchesCurrentStatus: function (tabStatus) {
      var status = ''
      if (tabStatus) {
        status = 'running'
      } else {
        status = 'awaiting_payment'
      }
      if (this.e1 === status) {
        return true
      }
      return false
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
#bottom-nav {
  margin: 0 !important;
  text-align: center;
}

#bottom-nav > v-btn {
  text-align: center;
  margin: 0;
}

v-icon {
  margin: auto;
  text-align: center;
}

</style>
