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
            <v-text-field
              label="Name"
              :rules="nameRules"
              v-model="personName"
            ></v-text-field>
            <v-text-field
              label="Phone number"
              :rules="phoneRules"
              v-model="personPhoneNumber"
            ></v-text-field>
          </v-card-text>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>


<script>
export default {
  name: 'AddPersonScreen',
  data () {
    return {
      tabId: this.$route.params.id,
      personName: '',
      personPhoneNumber: '',
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
    }
  }
}
</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
