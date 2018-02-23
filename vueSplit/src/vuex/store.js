import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

// The State Tree: an object that contains all the application level state
const state = {
  tabs: [{title: 'TestTab', status: 'Running'}, {title: 'TestTab2', status: 'Running'}],
  activeTab: {}
}

// Mutators: event handlers that manipulate the state SYNCHRONOUSLY
const mutations = {
  addTab (state, payLoad) {
    // TODO: Remove test tab
    const newTab = {
      title: payLoad,
      status: 'Running'
    }
    state.tabs.push(newTab)
  }
}

// Actions: event handlers that manipulate the state ASYNCHRONOUSLY
/*
const actions = {

}
*/

// Getters: used access data in the store from within our Vue components
const getters = {
  // ES6 object arrow function
  getTabs: state => state.tabs
}

// create the Vuex instance by combining the state and mutations objects
// then export the Vuex store for use by our components
export default new Vuex.Store({
  state,
  mutations,
  getters
})
