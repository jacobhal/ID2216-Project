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
            <!-- TODO: @click route to add person screen -->
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
            <template v-for="(person, index) in persons">
              <v-list-tile>
               <v-list-tile-content>
                 <v-list-tile-title>{{ person.name }}</v-list-tile-title>
               </v-list-tile-content>
               <v-list-tile-action>
                <v-checkbox v-model="peopleSelected[index].selected"></v-checkbox>
              </v-list-tile-action>
             </v-list-tile>
            </template>
          </v-list>
        </v-card>
      </v-flex>
    </v-layout>
    <!-- TODO: @click Navigate to InputAmountScreen and send selected people as params.
      Check that atleast one person was selected. -->
    <v-layout row align-content-center justify-center>
      <v-btn @click="goToInput" id="nextButton" color="red" large fab dark>
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
      tabId: this.$route.params.id,
      peopleSelected: [] // contains objects {id: personID, selected: boolean}
    }
  },
  created: function () {
    // Get people on this tab
    const tab = this.$store.getters.tabById(this.$route.params.id)
    this.persons = this.$store.getters.tabPersons(tab)
    // Each person on the tab is not selected at first
    for (let person of this.persons) {
      const obj = {id: person.id, selected: false}
      this.peopleSelected.push(obj)
    }
  },
  methods: {
    goToInput: function () {
      var params = ''
      for (var i = 0; i < this.peopleSelected.length; i++) {
        if (this.peopleSelected[i].selected) {
          if (params === '') {
            params += this.peopleSelected[i].id
          } else {
            params += ','
            params += this.peopleSelected[i].id
          }
        }
      }
      if (params === '') {
        alert('You must select atleast one person') // TODO: handle this better
      } else {
        this.$router.push({ path: `/inputAmount/${this.tabId}/${params}` })
      }
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#nextButton {
  margin-top: 10%
}
</style>
