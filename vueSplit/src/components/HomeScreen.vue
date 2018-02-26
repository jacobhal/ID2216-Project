<template>
  <div>
    <v-container fluid text-xs-center id="text">
      <h1 class="display-2">{{ this.tab.title }}</h1>
      <p class="subheading">
        Add receipts by clicking the plus button or see receipts on this tab with the list button
      </p>
    </v-container>

    <v-container grid-list-xs text-xs-center push-down>
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
#text {
  margin-top: 40%
}
.push-down{
  margin-top: 50%;
}
</style>
