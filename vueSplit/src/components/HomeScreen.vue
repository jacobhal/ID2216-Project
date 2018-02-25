<template>
  <div>
    <v-container fluid>
      <h1 class="display-2 center">{{ this.tabTitle }}</h1>
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
export default {
  name: 'HomeScreen',
  props: {
    tabTitle: {
      type: String,
      required: true
    }
  },
  data () {
    return {
      dialog3: false
    }
  },
  methods: {}
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.center{text-align:center;}
.push-down{
  margin-top: 70%;
}
</style>