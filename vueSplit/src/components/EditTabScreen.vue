<template>
  <div>
    <v-toolbar app>
      <v-btn icon @click="goBack">
        <v-icon>fa-angle-left</v-icon>
    </v-btn>
    </v-toolbar>
    <v-container grid-list-md text-xs-center>
      <h1 class="display-2">Edit Tab: {{ this.tab.title }}</h1>
      <div v-for="receipt in tabReceipts(this.tab)">
        <v-layout row wrap>
          <v-flex xs12>
            <a @click="">
              <!-- Add component for one single receipt here -->
              <h3>{{ receipt.title }}</h3>
            </a>
            <v-btn @click="editReceipt(receipt)">Edit</v-btn>
            <v-btn @click="deleteReceipt(receipt)">Delete</v-btn>
          </v-flex>
        </v-layout>
      </div>
      <input v-model='receiptTitle' placeholder="Receipt title">
      <v-btn @click="addReceipt">Add new receipt</v-btn>
    </v-container>
  </div>
</template>

<script>
// Add mapGetters from Vuex
import { mapGetters } from 'vuex'
export default {
  name: 'EditTabScreen',
  data () {
    return {
      receiptTitle: '',
      purchases: ['1', '2', '3'],
      persons: ['1', '2', '3'],
      tabId: this.$route.params.id
    }
  },
  created: function () {
    this.tab = this.$store.getters.tabById(this.$route.params.id)
  },
  methods: {
    goBack: function () {
      this.$router.back()
    },
    addReceipt: function () {
      this.$store.dispatch('addReceipt', {
        title: this.receiptTitle,
        purchases: this.purchases,
        persons: this.persons,
        tabId: this.tabId
      })
    },
    editReceipt: function (receipt) {
      this.$store.dispatch('editReceipt', {
        title: 'TEST',
        purchases: this.purchases,
        tabId: this.tabId,
        id: receipt.id
      })
    },
    deleteReceipt: function (receipt) {
      this.$store.dispatch('deleteReceipt', receipt)
    }
  },
  computed: mapGetters([
    'tabReceipts'
  ])
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
