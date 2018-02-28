<template>
  <v-container align-center text-xs-center>
    <v-toolbar app flat>
      <v-btn icon @click="goBack">
        <v-icon>fa-angle-left</v-icon>
    </v-btn>
    </v-toolbar>
    <v-layout row>
      <v-flex xs12>
        <h1 class="display-3 text-xs-center">Add Person</h1>
          <p class="subheading text-xs-center">Input person's name and phone number</p>
      </v-flex>
    </v-layout>
    <v-layout row>
      <v-flex xs12>
        <v-card>
          <v-card-text>
            <v-form v-model="valid">
              <v-text-field
                label="Name"
                :rules="nameRules"
                v-model="personName"
                v-on="{ blur: updateNextButtonColor }"
              ></v-text-field>
              <v-text-field
                label="Phone number"
                :rules="phoneRules"
                v-model="personPhoneNumber"
                v-on="{ blur: updateNextButtonColor }"
              ></v-text-field>
            </v-form>
          </v-card-text>
        </v-card>
      </v-flex>
    </v-layout>
    <!-- Submit button, only red when all input fields are valid -->
    <v-layout row align-content-center justify-center>
      <v-btn class="submitContainer" v-bind:color="color" large fab dark @click="submit">
        <v-icon>check</v-icon>
      </v-btn>
    </v-layout>
  </v-container>
</template>


<script>
import { mapGetters } from 'vuex'
export default {
  name: 'AddPersonScreen',
  data () {
    return {
      tabId: this.$route.params.id,
      personName: '',
      personPhoneNumber: '',
      color: 'grey',
      valid: false,
      nameRules: [
        () => this.personName.length > 0 || 'Field is required'
      ],
      phoneRules: [
        v => /^\d+$/.test(v) || 'Field can only contain numbers'
      ]
    }
  },
  methods: {
    goBack: function () {
      this.$router.back()
    },
    updateNextButtonColor: function () {
      if (this.valid) {
        this.color = 'red'
      } else {
        this.color = 'grey'
      }
    },
    submit: function () {
      if (this.valid) {
        // First add the person to the store
        this.addPerson()
        // Then add the person to the tab
        const personId = this.persons.slice(-1)[0].id // Need to get id of the person added
        this.addPersonToTab(personId)
        // Person has been added, return to AddReceiptScreen
        this.$router.push({ name: 'AddReceiptScreen', params: { id: this.tabId } })
      } else {
        alert('You must input a valid name and phone number') // TODO: Handle this better
      }
    },
    addPerson: function () {
      this.$store.dispatch('addPerson', {
        name: this.personName,
        phoneNumber: this.personPhoneNumber
      })
    },
    addPersonToTab: function (pId) {
      this.$store.dispatch('addPersonToTab', {
        tabId: this.tabId,
        personId: pId
      })
    }
  },
  computed: {
    ...mapGetters([
      'persons',
      'tabById'
    ])
  }
}
</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.submitContainer {
  margin-top: 10%
}
</style>
