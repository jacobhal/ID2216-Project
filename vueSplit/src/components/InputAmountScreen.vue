<template>
  <div>
    <v-container v-if="!successfullyAdded">
      <v-toolbar app flat>
        <v-btn icon @click="goBack">
          <v-icon>fa-angle-left</v-icon>
        </v-btn>
      </v-toolbar>
      <v-layout row>
        <v-flex xs12>
          <h1 class="display-3 text-xs-center">Amount</h1>
          <p class="subheading text-xs-center">
            Please input the title of the receipt and the <b>total</b>
          amount each person paid</p>
        </v-flex>
      </v-layout>
      <v-layout row align-center>
        <v-flex xs12>
          <v-card>
            <v-list>
              <v-list-tile>
                <v-list-tile-content>
                  <v-list-tile-title>Title of receipt</v-list-tile-title>
                </v-list-tile-content>
                <v-list-tile-action>
                  <v-form v-model="validTitle">
                    <v-text-field
                    v-on="{ blur: validate }"
                    label="Title"
                    full-width
                    :rules="titleRules"
                    v-model="receiptTitle"
                    ></v-text-field>
                  </v-form>
                </v-list-tile-action>
              </v-list-tile>
            </v-list>
          </v-card>
        </v-flex>
      </v-layout>
      <v-layout id="secondCard" row align-center>
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
        <v-btn class="submitContainer" v-bind:color="color" large fab dark @click="submit">
          <v-icon>check</v-icon>
        </v-btn>
      </v-layout>
    </v-container>
    <v-container v-if="successfullyAdded" class="text-xs-center">
      <v-icon size="30vw" transition="fade-transition" color="green">check_circle</v-icon>
      <p class="display-1" transition="fade-transition">Receipt successfully added!</p>
      <v-btn @click="routeToHome" dark color="green">Continue
        <v-icon right>navigate_next</v-icon>
      </v-btn>
    </v-container>
  </div>
</template>


<script>
export default {
  name: 'InputAmountScreen',
  data () {
    return {
      tabId: this.$route.params.tabId,
      persons: [], // people on this receipt
      amounts: [], // Inputted amount, tied to form
      validFields: [], // boolean for if input field is valid
      validTitle: false,
      color: 'grey',
      allFieldsValid: false,
      receiptTitle: '',
      inputRules: [
      () => !!this.amounts[0] || 'This field is required',
      v => /^\d+(\.\d+)?$/.test(v) || 'Field can only contain numbers'
      ],
      titleRules: [
      () => this.receiptTitle.length > 0 || 'Must have a title, e.g. "Dinner before movie"'
      ],
      successfullyAdded: false
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
  },
  methods: {
    goBack: function () {
      this.$router.back()
    },
    submit: function () {
      if (this.allFieldsValid && this.validTitle) {
        // First we add a new receipt
        this.addReceipt()
        // Get ID of the added receipt
        const tab = this.$store.getters.tabById(this.tabId)
        // Add purchases to receipt
        for (var i = 0; i < this.persons.length; i++) {
          if(this.persons[i] !== undefined) {
            this.addPurchase(this.persons[i].id, parseFloat(this.amounts[i]), this.$store.state.addedReceiptId)
            this.addPersonToTab(this.persons[i].id)
            this.addPersonToReceipt(this.persons[i].id, this.$store.state.addedReceiptId)
          }
        }
        this.successfullyAdded = true
      } else {
        alert('All fields must be filled in')
      }
    },
    isTrue: function (value) {
      return value
    },
    routeToHome: function () {
      this.$router.push({ path: `/home/${this.tabId}` })
    },
    validate: function () {
      this.allFieldsValid = this.validFields.every(this.isTrue)
      if (this.allFieldsValid && this.validTitle) {
        this.color = 'red'
      } else {
        this.color = 'grey'
      }
    },
    addPersonToTab: function (personId) {
      this.$store.dispatch('addPersonToTab', {
        personId: personId,
        tabId: this.tabId
      })
    },
    addPersonToReceipt: function (personId, receiptId) {
      this.$store.dispatch('addPersonToReceipt', {
        personId: personId,
        receiptId: receiptId
      })
    },
    addReceipt: function () {
      const peopleIds = this.$route.params.peopleIds.split(',')
      this.$store.dispatch('addReceipt', {
        title: this.receiptTitle,
        purchases: [],
        persons: peopleIds,
        tabId: this.tabId
      })
    },
    addPurchase: function (personId, price, receiptId) {
      this.$store.dispatch('addPurchase', {
        person: personId,
        price: price,
        receiptId: receiptId
      })
    }
  }
}
</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.submitContainer {
  margin-top: 10%
}
#secondCard {
  margin-top: 10%
}
</style>
