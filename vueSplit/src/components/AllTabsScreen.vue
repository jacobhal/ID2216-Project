<template>
  <div>
    <v-container grid-list-md text-xs-center>
      <h1 class="display-2">Tabs</h1>
      <h2 class="subheading">{{ e1 }}</h2>
      <v-layout row wrap align-center v-for="tab in tabs" :key="tab.id">
        <v-flex v-if="matchesCurrentStatus(tab.running)" xs12>
          <v-card class="tabCard">
            <!-- Add component for one single tab here -->
            <v-card-title>
              <a class="center-text" @click="routeToHomeScreen(tab.id)">{{ tab.title }}</a>
            </v-card-title>
            <v-btn @click="deleteTab(tab)">Delete</v-btn>
            <v-btn @click="toggleTab(tab)">Toggle status</v-btn>
          </v-card>
        </v-flex>
      </v-layout>
    </v-container>
    <v-bottom-nav 
    absolute 
    :value="true" 
    :active.sync="e1" 
    color="transparent"
    full-width
    id="bottom-nav"
    >
    <v-btn flat color="red darken-2" value="Running">
      <span>Running</span>
      <v-icon>lock_open</v-icon>
    </v-btn>
    <v-btn flat color="red darken-2" value="Awaiting payment">
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
      e1: 'Running',
      tabTitle: ''
    }
  },
  methods: {
    goBack: function () {
      this.$router.back()
    },
    routeToHomeScreen: function (id) {
      this.$router.push({name: 'HomeScreen', params: { id: id }})
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
        status = 'Running'
      } else {
        status = 'Awaiting payment'
      }
      if (this.e1 === status) {
        return true
      }
      return false
    }
  },
  computed: {
    ...mapGetters([
      'tabs'])
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
