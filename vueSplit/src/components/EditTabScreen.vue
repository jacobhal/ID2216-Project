<template>
  <div>
    <v-toolbar app flat>
      <v-btn icon @click="goBack">
        <v-icon>fa-angle-left</v-icon>
      </v-btn>
    </v-toolbar>
    <v-container grid-list-md text-xs-center>
      <h1 class="display-2">Edit Tab</h1>
      <h2 class="subheading">{{ this.tab.title }}</h2>
      <div v-for="receipt in this.$store.getters.tabReceipts(this.tab)">
        <v-layout row wrap>
          <v-flex xs12>
            <v-card class="tabCard">
              <v-card-title>
                <a @click="" class="center-text">
                  <!-- Add component for one single receipt here -->
                  <h3>{{ receipt.title }}, Price: {{ receipt.totalPrice }}</h3>
                </a>
              </v-card-title>
              <v-btn @click="editReceipt(receipt.id)">Edit</v-btn>
              <v-btn @click="deleteReceipt(receipt)">Delete</v-btn>
            </v-card>
          </v-flex>
        </v-layout>
      </div>
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
      tabId: this.$route.params.id
    }
  },
  created: function () {
    this.tab = this.$store.getters.tabById(this.tabId)
  },
  methods: {
    goBack: function () {
      this.$router.back()
    },
    editReceipt: function (receiptId) {
      this.$router.push({ path: `/inputEdit/${receiptId}/` })
    },
    deleteReceipt: function (receipt) {
      this.$store.dispatch('deleteReceipt', receipt)
    }
  },
  computed: mapGetters([
    'tabReceipts',
    'tabById',
    'personById'])
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
