<template>
  <div>
    <v-container fluid text-xs-center>
      <h1 class="display-2">{{ this.tab.title }}</h1>
      <p class="subheading">
        Add receipts by clicking the plus button or see receipts on this tab with the list button
      </p>
      <div v-if="this.tab.running === true">
        <v-btn  @click="dialog3 = true">Close this tab</v-btn>
        <v-layout row wrap grid-list-xs text-xs-center push-down>
          <v-flex xs6>
            <v-btn @click="routeToEditTabScreen"  fab dark large color="red">
              <v-icon dark>list</v-icon>
            </v-btn>
          </v-flex>
          <v-flex xs6>
            <v-btn @click="routeToAddReceiptScreen" fab large dark color="red" >
              <v-icon dark >add</v-icon>
            </v-btn>
          </v-flex>
        </v-layout>
      </div>
      <v-alert v-else type="info" :value="true">
        This tab has been closed
      </v-alert>
    </v-container>

    <v-dialog v-model="dialog3" max-width="500px">
      <v-card>
        <v-card-title>
          <span>Create new Tab</span>
          <v-spacer></v-spacer>
        </v-card-title>
        <v-card-text>
          Are you sure you want to close this tab?
          This will send SMS notifications to all the people in this tab and prevent further changes.
        </v-card-text>

        <v-card-actions>
          <v-btn color="primary" flat @click.stop="dialog3 = false">Cancel</v-btn>
          <v-spacer></v-spacer>
          <v-btn color="green darken-1" flat @click="closeTab">Confirm</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <v-snackbar
    :timeout="timeout"
    :bottom="true"
    :multi-line="mode === 'multi-line'"
    v-model="snackbar"
    >
    Tab successfully closed
    <v-btn flat color="pink" @click.native="snackbar = false">Close</v-btn>
  </v-snackbar>

</div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  name: 'HomeScreen',
  props: ['id'],
  data () {
    return {
      dialog3: false,
      snackbar: false,
      mode: 'multi-line',
      timeout: 1500
    }
  },
  created: function () {
    this.tab = this.$store.getters.tabById(this.id)
  },
  methods: {
    routeToAddReceiptScreen: function () {
      this.$router.push({ name: 'AddReceiptScreen', params: { id: this.id } })
    },
    routeToEditTabScreen: function () {
      this.$router.push({ name: 'EditTabScreen', params: { id: this.id } })
    },
    closeTab: function () {
      this.dialog3 = false
      this.snackbar = true
      this.$store.dispatch('toggleTab', this.tab)
      // TODO: Send SMS to people in the tab
    }
  },
  computed: mapGetters([
    'tabById'])
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#text {
  margin-top: 40%
}
.push-down{
  margin-top: 50%;
}
</style>
