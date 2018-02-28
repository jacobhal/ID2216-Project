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
            <template v-for="(person, index) in persons">
              <v-list-tile>
                <v-list-tile-content>
                  <v-list-tile-title>{{ person.name }}</v-list-tile-title>
                </v-list-tile-content>
                <v-list-tile-action>
                  <v-form>
                    <v-text-field
                      label="Amount"
                      full-width
                      append-icon="delete"
                      :value="getPurchaseOfPerson(person.id).price"
                      :rules="inputRules"
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
      <v-btn class="submitContainer" color="red" large fab dark @click="">
        <v-icon>check</v-icon>
      </v-btn>
    </v-layout>
  </v-container>
</template>

<script>
export default {
  name: 'InputEditScreen',
  data () {
    return {
      receiptId: this.$route.params.receiptId,
      persons: [],
      purchases: [],
      inputRules: [
        // () => !!this.amounts[0] || 'This field is required',
        v => /^\d+(\.\d+)?$/.test(v) || 'Field can only contain numbers'
      ]
    }
  },
  created: function () {
    const receipt = this.$store.getters.receiptById(this.receiptId)
    this.persons = this.$store.getters.receiptPersons(receipt)
    this.purchases = this.$store.getters.receiptPurchases(receipt)
  },
  methods: {
    goBack: function () {
      this.$router.back()
    },
    getPurchaseOfPerson: function (personId) {
      const ret = this.purchases.filter(purchase => personId === purchase.person)
      return ret[0]
    }
  }
}
</script>

<style scoped>
.submitContainer {
  margin-top: 10%
}
</style>
