<template>
  <div>
    <v-container fluid>
      <h1 class="display-2 center">{{ this.tab.title }}</h1>
      <p class="headline center">Add receipts by clicking the +</p>
    </v-container>

    <v-container grid-list-md text-xs-center push-down>
      <v-layout row wrap>
        <v-flex xs6>
          <v-btn fab dark large color="red">
            <v-icon dark>list</v-icon>
          </v-btn>
        </v-flex>
        <v-flex xs6>
          <v-btn fab large dark color="red" >
            <v-icon dark >add</v-icon>
          </v-btn>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  name: 'HomeScreen',
  props: ['id'],
  data () {
    return {
      dialog3: false
    }
  },
  created: function () {
    this.tab = this.$store.getters.tabById(this.id)
  },
  methods: {
    routeToAddReceiptScreen: function () {
      this.$router.push({ name: 'AddReceiptScreen', params: { id: this.id } })
    }
  },
  computed: mapGetters([
    'tabById'
  ])
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.center{text-align:center;}
.push-down{
  margin-top: 70%;
}
</style>