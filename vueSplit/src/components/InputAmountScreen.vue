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
            <!-- TODO: Iterate over all people added to the recipt and generate list items -->
            <v-list-tile>
              <v-list-tile-content>
                <v-list-tile-title>Person's name:</v-list-tile-title>
              </v-list-tile-content>
              <v-list-tile-action>
                <v-form v-model="validFields[0]">
                  <v-text-field
                    v-on="{ blur: validate }"
                    label="Amount"
                    full-width
                    :rules="inputRules"
                    v-model="amounts[0]"
                  ></v-text-field>
                </v-form>
             </v-list-tile-action>
            </v-list-tile>
            <v-divider></v-divider>
            <v-list-tile>
              <v-list-tile-content>
                <v-list-tile-title>Person's name:</v-list-tile-title>
              </v-list-tile-content>
              <v-list-tile-action>
                <v-form v-model="validFields[1]">
                  <v-text-field
                    v-on="{ blur: validate }"
                    label="Amount"
                    full-width
                    :rules="inputRules"
                    v-model="amounts[1]"
                  ></v-text-field>
                </v-form>
             </v-list-tile-action>
            </v-list-tile>
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
      amounts: [null, null],
      validFields: [false, false],
      color: 'grey',
      valid: false,
      inputRules: [
        () => !!this.amounts[0] || 'This field is required',
        v => /^\d+(\.\d+)?$/.test(v) || 'Field can only contain numbers'
      ]
    }
  },
  methods: {
    // TODO: Submit data properly
    submit: function () {
      if (this.valid) {
        // Submit
      } else {
        // handle?
      }
      console.log(this.amounts)
      console.log(this.valid)
    },
    isTrue: function (value) {
      return value
    },
    validate: function () {
      this.valid = this.validFields.every(this.isTrue)
      if (this.valid) {
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
