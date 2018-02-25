<template>
  <v-container>
    <v-layout row>
      <v-flex xs12>
        <h1 class="display-3 text-xs-center">Amount</h1>
        <p class="subheading text-xs-center">Please input the <b>total</b>
          amount each person paid in this receipt</p>
      </v-flex>
    </v-layout>
    <v-layout row align-center>
      <v-flex xs12>
        <v-card>
          <v-list two-line>
            <template v-for="(person, index) in persons">
              <v-list-tile>
                <v-list-tile-content>
                  <v-list-tile-title>{{ person.name }}</v-list-tile-title>
                </v-list-tile-content>
                <v-list-tile-action>
                  <v-form v-model="validFields[index]">
                    <v-text-field
                      v-on="{ blur: validate }"
                      label="Amount"
                      full-width
                      :rules="inputRules"
                      v-model="amounts[index]"
                    ></v-text-field>
                  </v-form>
               </v-list-tile-action>
              </v-list-tile>
              <v-divider v-if="index < persons.length-1"></v-divider>
            </template>
          </v-list>
        </v-card>
      </v-flex>
    </v-layout>
    <!-- Submit button, only red when all input fields are valid -->
    <v-layout row align-content-center justify-center>
      <v-btn id="submitButton" v-bind:color="color" large fab dark @click="submit">
        <v-icon>check</v-icon>
      </v-btn>
    </v-layout>
  </v-container>
</template>


<script>
export default {
  // TODO: Take in persons from previous page
  name: 'InputAmountScreen',
  data () {
    return {
      tabId: this.$route.params.tabId,
      persons: [],
      amounts: [],
      validFields: [],
      color: 'grey',
      allFieldsValid: false,
      inputRules: [
        () => !!this.amounts[0] || 'This field is required',
        v => /^\d+(\.\d+)?$/.test(v) || 'Field can only contain numbers'
      ]
    }
  },
  created: function () {
    // Get people who have been selected for this receipt
    const peopleIds = this.$route.params.peopleIds.split(',')
    for (let id of peopleIds) {
      const person = this.$store.getters.personById(id)
      this.persons.push(person)
      // Fill amounts with null since nothing has been inputted yet. Needed for v-model
      this.amounts.push(null)
      // Fill validFields with false since all are invalid at first. needed for v-model
      this.validFields.push(false)
    }
    // console.log(this.persons)
    // console.log(this.amounts)
    // console.log(this.validFields)
  },
  methods: {
    // TODO: Submit data properly
    submit: function () {
      if (this.allFieldsValid) {
        // Submit
      } else {
        // handle?
      }
    },
    isTrue: function (value) {
      return value
    },
    validate: function () {
      this.allFieldsValid = this.validFields.every(this.isTrue)
      if (this.allFieldsValid) {
        this.color = 'red'
      } else {
        this.color = 'grey'
      }
    }
  }
}
</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#submitButton {
  margin-top: 10%
}
</style>
