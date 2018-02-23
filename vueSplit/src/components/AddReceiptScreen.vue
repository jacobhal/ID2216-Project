<template>
    <v-container>
      <v-layout row>
        <v-flex xs12>
          <h1 class="display-3 text-xs-center">Add Receipt</h1>
          <p class="subheading text-xs-center">Select people who made purchases</p>
        </v-flex>
      </v-layout>
      <v-layout id="persons">
        <v-flex xs3>
        <!-- TODO: Align text with avatar -->
          <v-btn fab dark large color="red">
            <v-icon dark>person_add</v-icon>
          </v-btn>
        </v-flex>
        <!-- TODO: Iterate over the people and add button template for each -->
        <v-flex xs3>
          <v-btn fab large color="red">
            <span class="white--text subheading">Alexa...</span>
          </v-btn>
        </v-flex>
        <v-flex xs3>
          <v-btn fab large color="red">
            <span class="white--text subheading">Jacob</span>
          </v-btn>
        </v-flex>
        <v-flex xs3>
          <v-btn fab large color="red">
            <span class="white--text">Jacob</span>
          </v-btn>
        </v-flex>
        <v-flex xs3>
          <v-btn fab large color="red">
            <span class="white--text">Jacob</span>
          </v-btn>
        </v-flex>
        <v-flex xs3>
          <v-btn fab large color="red">
            <span class="white--text">Jacobbb</span>
          </v-btn>
        </v-flex>
      </v-layout>
    </v-container>
</template>

<script>
export default {
  name: 'AddReceiptScreen',
  data () {
    return {
      avatarSize: 70,
      selectedAvatarSize: 60
    }
  },
  methods: {}
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#persons {
  overflow-x: scroll
}
.personAvatar {
    margin-right: 2%
}
</style>
