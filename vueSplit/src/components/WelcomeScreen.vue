<template>
  <div>
    <v-container>
      <v-layout row>
        <v-flex xs12>
          <h1 class="display-2 text-xs-center">VueSplit</h1>
          <p class="subheading text-xs-center">Splitting the bill has never been easier</p>
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
          @click.stop="startTimer">
          <v-icon>add</v-icon>
        </v-btn>
      </v-flex>
    </v-layout>
  </v-container>

  <v-dialog v-model="dialog3" max-width="500px">
    <v-card>
      <v-card-title>
        <span>Create new Tab</span>
        <v-spacer></v-spacer>
      </v-card-title>
      <v-card-text>
        Almost ready to go, but first, give the tab a name.
        <v-container grid-list-md>
          <v-layout wrap>
            <v-flex xs12 sm6 md4>
              <v-text-field ref="tabTitleTextField" v-model="tabTitle" label="Title of the new tab" required></v-text-field>
            </v-flex>
          </v-layout>
        </v-container>
      </v-card-text>

      <v-card-actions>
        <v-btn color="primary" flat @click.stop="dialog3=false">Cancel</v-btn>
        <v-spacer></v-spacer>
        <v-btn color="green darken-1" flat @click="createNewTabAndRouteToHomeScreen">Save</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>


</div>

</template>


<script>
export default {
  name: 'WelcomeScreen',
  data () {
    return {
      tabTitle: '',
      dialog3: false
    }
  },
  methods: {
    addTab: function () {
      this.$store.dispatch('addTab', {
        title: this.tabTitle,
        receipts: [],
        persons: [],
        running: true
      })
    },
    createNewTabAndRouteToHomeScreen: function () {
      this.addTab()
      var tabId = this.$store.state.addedTabId
      this.$router.push({ name: 'HomeScreen', params: { id: tabId } })
    },
    startTimer () {
      this.dialog3 = true
      this.$nextTick(() => {
        this.$refs.tabTitleTextField.focus()
      })
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
  text-align: center;
  margin-top: 50%
}
</style>
