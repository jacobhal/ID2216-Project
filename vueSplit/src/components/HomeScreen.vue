<template>
  <div>
    <v-container fluid text-xs-center>
      <h1 class="display-2">{{ this.tab.title }}</h1>
      <p class="subheading">
        Add receipts by clicking the plus button or see receipts on this tab with the list button
      </p>
      <div v-if="this.tab.running === true">
        <v-btn  @click="dialog3 = true">Close this tab</v-btn>
        <v-btn  color="green darken-1" @click="closeTab">TMP</v-btn>
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
      // this.$store.dispatch('toggleTab', this.tab)
      var tabPersons = this.tab.persons
      var personCosts = {}
      // Create object to hold costs for all persons in this tab
      for (var k = 0; k < tabPersons.length; k++) {
        personCosts[tabPersons[k]] = 0
      }

      var tabReceipts = this.tabReceipts(this.tab)
      // Loop through all receipts in this tab
      for (var i = 0; i < tabReceipts.length; i++) {
        var currentReceipt = tabReceipts[i]
        // Loop through all purchases in this receipt
        for (var j = 0; j < currentReceipt.purchases.length; j++) {
          var currentPurchase = this.purchaseById(currentReceipt.purchases[j])
          personCosts[currentPurchase.person] += currentPurchase.price
        }
      }

      // Calculate total cost of tab
      var totalCost = 0
      for (var p1 in personCosts) {
        totalCost += personCosts[p1]
      }

      // Get number of people in tab
      var nrOfPersons = Object.keys(personCosts).length

      // Calculate expense per person
      var expensePerPerson = totalCost / nrOfPersons

      // Update personCosts with how much they owe or are owed by the central pool
      for (var p2 in personCosts) {
        personCosts[p2] = expensePerPerson - personCosts[p2]
      }

      // Persons with positive balance have money left to pay
      // Persons with negative balance are owed money
      // Sort the persons by lowest debts first
      var positiveBalance = []
      var negativeBalance = []
      for (var p3 in personCosts) {
        if (personCosts[p3] >= 0) {
          positiveBalance.push([p3, personCosts[p3]])
        } else {
          negativeBalance.push([p3, personCosts[p3]])
        }
      }
      positiveBalance.sort(function (a, b) { return a[1] - b[1] })
      negativeBalance.sort(function (a, b) { return a[1] - b[1] })

      for (var p4 in personCosts) {
        personCosts[p4] = false
      }

      while (positiveBalance.length > 0 && negativeBalance.length > 0) {
        var leastPositive = positiveBalance[0][1]
        var leastNegative = Math.abs(negativeBalance[0][1])
        var leastPositivePerson = positiveBalance[0][0]
        var leastNegativePerson = negativeBalance[0][0]
        if (leastPositive > leastNegative) {
          negativeBalance.splice(0, 1)
          positiveBalance.splice(0, 1, [leastPositivePerson, leastPositive - leastNegative])
          console.log(this.personById(leastPositivePerson).name + ' pays ' + leastNegative + ' kr to ' + this.personById(leastNegativePerson).name)
          // TODO: SMS, Person positiveBalance[0][0] should pay a sum of leastNegative to negativeBalance[0][0]
        } else if (leastNegative > leastPositive) {
          positiveBalance.splice(0, 1)
          negativeBalance.splice(0, 1, [leastNegativePerson, leastNegative - leastPositive])
          console.log(this.personById(leastPositivePerson).name + ' pays ' + leastPositive + ' kr to ' + this.personById(leastNegativePerson).name)
          // TODO: SMS, Person positiveBalance[0][0] should pay a sum of leastPositive to negativeBalance[0][0]
        } else {
          positiveBalance.splice(0, 1)
          negativeBalance.splice(0, 1)
          console.log(this.personById(leastPositivePerson).name + ' pays ' + leastPositive + ' kr to ' + this.personById(leastNegativePerson).name)
          // TODO: SMS, Person positiveBalance[0][0] should pay a sum of leastPositive to negativeBalance[0][0]
        }
      }
    }
  },
  computed: mapGetters([
    'tabById',
    'tabReceipts',
    'personById',
    'purchaseById'])
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
