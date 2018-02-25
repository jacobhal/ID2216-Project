<template>
  <v-container align-center>
    <v-layout row>
      <v-flex xs12>
        <h1 class="display-3 text-xs-center">Add Receipt</h1>
        <p class="subheading text-xs-center">Select people who made purchases</p>
      </v-flex>
    </v-layout>
    <v-layout row>
      <v-flex xs12>
        <v-card>
          <v-list>
            <!-- Add new person list item -->
            <v-list-tile avatar>
             <v-list-tile-content>
               <v-list-tile-title>Add New Person</v-list-tile-title>
             </v-list-tile-content>
             <v-list-tile-avatar>
               <v-icon>person_add</v-icon>
             </v-list-tile-avatar>
           </v-list-tile>
          </v-list>
          <v-divider></v-divider>
          <!-- Recent people list items -->
          <v-list subheader>
            <v-subheader>Already on this tab</v-subheader>
            <!-- TODO: Iterate over all people in tab and add list item for each -->
            <v-list-tile>
             <v-list-tile-content>
               <v-list-tile-title>Name of person</v-list-tile-title>
             </v-list-tile-content>
             <v-list-tile-action>
              <v-checkbox v-model="people[0]"></v-checkbox>
            </v-list-tile-action>
           </v-list-tile>
           <v-list-tile>
            <v-list-tile-content>
              <v-list-tile-title>Name of person</v-list-tile-title>
            </v-list-tile-content>
            <v-list-tile-action>
             <v-checkbox v-model="people[1]"></v-checkbox>
           </v-list-tile-action>
          </v-list-tile>
          </v-list>
        </v-card>
      </v-flex>
    </v-layout>
    <v-layout row align-content-center justify-center>
      <v-btn id="nextButton" color="red" large fab dark>
        <v-icon>navigate_next</v-icon>
      </v-btn>
    </v-layout>
  </v-container>
</template>

<script>
export default {
  name: 'AddReceiptScreen',
  data () {
    return {
      people: [false, false]
    }
  },
  methods: {
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#nextButton {
  margin-top: 10%
}
</style>
