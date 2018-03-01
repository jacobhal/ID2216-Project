<template>
  <v-container>
    <v-toolbar app flat>
      <v-btn icon @click="goBack">
        <v-icon>fa-angle-left</v-icon>
      </v-btn>
    </v-toolbar>
    <v-layout row text-xs-center>
      <v-flex xs12>
        <h1 class="display-3">Edit Receipt</h1>
        <p class="subheading">
          Edit the purchases on the tab below
        </p>
      </v-flex>
    </v-layout>
    <v-layout row text-xs-center>
      <v-flex xs12>
        <v-card>
          <v-list>
            <template v-for="(person, index) in this.$store.getters.receiptPersons(this.receipt)">
              <v-list-tile>
                <v-list-tile-content>
                  <v-list-tile-title>{{ person.name }}</v-list-tile-title>
                </v-list-tile-content>
                <v-list-tile-action>
                  <v-form v-model="validFields[index]">
                    <v-text-field
                    label="Amount"
                    full-width
                    append-icon="delete"
                    v-bind:value="getPurchaseOfPerson(index)"
                    :rules="inputRules"
                    v-model="amounts[index]"
                    ></v-text-field>
                  </v-form>
                </v-list-tile-action>
              </v-list-tile>
              <v-divider v-if="index < (persons.length-1)"></v-divider>
            </template>
          </v-list>
        </v-card>
      </v-flex>
    </v-layout>
    <v-layout row align-content-center justify-center>
      <v-btn class="submitContainer" color="red" large fab dark @click="submit">
        <v-icon>check</v-icon>
      </v-btn>
    </v-layout>
  </v-container>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  name: 'InputEditScreen',
  data () {
    return {
      receiptId: this.$route.params.receiptId,
      receiptTitle: '',
      tabId: null,
      persons: [],
      purchases: [],
      allFieldsValid: false,
      inputRules: [
        // () => !!this.amounts[0] || 'This field is required',
        v => /^\d+(\.\d+)?$/.test(v) || 'Field can only contain numbers'],
      amounts: [], // Inputted amount, tied to form
      validFields: [] // boolean for if input field is valid
    }
  },
  created: function () {
    this.receipt = this.$store.getters.receiptById(this.$route.params.receiptId)
    this.tabId = this.receipt.tabId
    for (var i = 0; i < this.receipt.persons.length; i++) {
      // Fill amounts with null since nothing has been inputted yet. Needed for v-model
      this.amounts.push(null)
      // Fill validFields with false since all are invalid at first. needed for v-model
      this.validFields.push(false)
    }
    this.purchases = this.$store.getters.receiptPurchases(this.receipt)
  },
  methods: {
    goBack: function () {
      this.$router.back()
    },
    getPurchaseOfPerson: function (index) {
      return this.purchases[index].price
    },
    submit: function () {
      this.allFieldsValid = this.validFields.every(this.isTrue)
      if (this.allFieldsValid) {
        // Edit purchases
        for (var j = 0; j < this.purchases.length; j++) {
          var purchaseId = this.purchases[j].id
          var personId = this.purchases[j].id
          var price = this.amounts[j]
          this.editPurchase(purchaseId, personId, price)
        }
        this.editReceipt()
        this.$router.push({ name: 'EditTabScreen', params: { id: this.tabId } })
      } else {
        // TODO: handle
      }
    },
    isTrue: function (value) {
      return value
    },
    editPurchase: function (purchaseId, personId, price) {
      this.$store.dispatch('editPurchase', {
        id: purchaseId,
        person: personId,
        price: price,
        receiptId: this.receiptId
      })
    },
    editReceipt: function () {
      this.$store.dispatch('editReceipt', {
        title: this.receipt.title,
        id: this.$route.params.receiptId,
        persons: this.persons,
        totalPrice: this.receipt.totalPrice,
        tabId: this.tabId
      })
    },
    add: function (a, b) {
      return a + b
    },
    computed: mapGetters([
      'receiptPersons',
      'personById'])
  }
}
</script>

<style scoped>
.submitContainer {
  margin-top: 10%
}
</style>
