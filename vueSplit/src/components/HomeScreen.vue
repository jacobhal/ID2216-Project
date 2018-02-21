<template>
  <v-container fluid>
    <div>
    <v-btn color="success" @click="func">{{msg}}</v-btn>
  </div>
  </v-container>
</template>

<script>
export default {
  name: 'HomeScreen',
  data () {
    return {
      msg: 'Press me!'
    }
  },
  methods: {
    func: function () {
      this.msg = 'AHHAHAHAAHAHA'
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
