import Vue from 'vue'
import Vuex from 'vuex'
import uuidv4 from 'uuid/v4'

Vue.use(Vuex)

// The State Tree: an object that contains all the application level state
const state = {
  tabs: [
    {id: '1', title: 'TestTab1', receipts: ['1', '2'], persons: ['1', '2', '3'], running: true},
    {id: '2', title: 'TestTab2', receipts: ['3', '4'], persons: ['1', '2', '3'], running: false}
  ],
  activeTab: {},
  receipts: [
    {id: '1', title: 'Tentapub', purchases: ['1', '2'], totalPrice: 100},
    {id: '2', title: 'Bowling', purchases: ['3', '4'], totalPrice: 200},
    {id: '3', title: 'Cypern', purchases: ['5', '6'], totalPrice: 300},
    {id: '4', title: 'Kinamuren schleeeeee', purchases: ['7', '8']}
  ],
  activeReceipt: {},
  purchases: [
    {id: '1', person: 1, price: 100},
    {id: '2', person: 2, price: 200},
    {id: '3', person: 3, price: 300},
    {id: '4', person: 1, price: 101},
    {id: '5', person: 2, price: 201},
    {id: '6', person: 3, price: 301},
    {id: '7', person: 1, price: 102},
    {id: '8', person: 2, price: 202}
  ],
  persons: [
    {id: '1', name: 'Jacob', phoneNumber: '0761337123'},
    {id: '2', name: 'Nedo', phoneNumber: '0731111222'},
    {id: '3', name: 'Malte', phoneNumber: '0702222333'}
  ]
}

// Mutators: event handlers that manipulate the state SYNCHRONOUSLY
const mutations = {
  GET_TAB (state, tab) {
    state.activeTab = tab
  },
  ADD_TAB (state, payload) {
    const newTab = {
      id: uuidv4(),
      title: payload.title,
      receipts: payload.receipts,
      persons: payload.persons,
      running: true
    }
    state.tabs.push(newTab)
  },
  DELETE_TAB (state, tab) {
    var tabs = state.tabs
    tabs.splice(tabs.indexOf(tab), 1)
  },
  EDIT_TAB (state, tab) {
    var index = state.tabs.findIndex(currentTab => currentTab.id === tab.id)
    state.tabs.splice(index, 1, tab)
  },
  TOGGLE_TAB (state, tab) {
    tab.running = !tab.running
  },
  GET_RECEIPT (state, receipt) {
    state.activeReceipt = receipt
  },
  ADD_RECEIPT (state, payload) {
    var totalPrice = 0
    var purchases = state.purchases.filter(purchase => payload.purchases.includes(purchase.id))
    for (var i = purchases.length - 1; i >= 0; i--) {
      totalPrice += purchases[i].price
    }
    const newReceipt = {
      id: uuidv4(),
      title: payload.title,
      purchases: payload.purchases,
      totalPrice: totalPrice
    }
    state.receipts.push(newReceipt)
  },
  DELETE_RECEIPT (state, receipt) {
    var receipts = state.receipts
    receipts.splice(receipts.indexOf(receipt), 1)
  },
  EDIT_RECEIPT (state, receipt) {
    var index = state.receipts.findIndex(currentReceipt => currentReceipt.id === receipt.id)
    state.receipts.splice(index, 1, receipt)
  }
}

// Actions: event handlers that manipulate the state ASYNCHRONOUSLY
const actions = {
  // Tab actions
  getTab ({commit}, tab) { commit('GET_TAB', tab) },
  addTab ({commit}, payload) { commit('ADD_TAB', payload) },
  deleteTab ({commit}, tab) { commit('DELETE_TAB', tab) },
  editTab ({commit}, tab) { commit('DELETE_TAB', tab) },
  toggleTab ({commit}, tab) { commit('TOGGLE_TAB', tab) },
  // Receipt actions
  getReceipt ({commit}, tab) { commit('GET_RECEIPT', tab) },
  addReceipt ({commit}, payload) {
    commit('ADD_RECEIPT', payload)
    // We need to update the relations for the new receipt
    // var tab = state.tabs.find(tab => tab.id === payload.tabId)
    // tab.purchases.push(<NEW-RECEIPT-ID>)
    // commit('EDIT_TAB', tab)
  },
  editReceipt ({commit}, receipt) { commit('EDIT_RECEIPT', receipt) }
}

// Getters: used access data in the store from within our Vue components
const getters = {
  // Get things related to tabs
  tabs: state => state.tabs,
  tabById: (state) => (id) => { return state.tabs.find(tab => tab.id === id) },
  runningTabs: state => { return state.tabs.filter(tab => tab.running) },
  awaitingPaymentTabs: state => { return state.tabs.filter(tab => !tab.running) },
  tabReceipts: (state) => (tab) => {
    return tab.receipts.map(receiptId => state.receipts.find(receipt => receipt.id === receiptId))
  },
  tabPersons: (state) => (tab) => {
    return tab.persons.map(personId => state.persons.find(person => person.id === personId))
  },
  // Get things related to receipts
  receipts: state => state.receipts,
  receiptPurchases: (state) => (receipt) => {
    return receipt.purchases.map(purchaseId => state.purchases.find(purchase => purchase.id === purchaseId))
  },
  // Get things related to purchases
  purchases: state => state.purchases,
  purchasePerson: (state) => (purchase) => {
    return purchase.persons.map(personId => state.persons.find(person => person.id === personId))
  },
  // Get things related to persons
  persons: state => state.persons
}

// create the Vuex instance by combining the state and mutations objects
// then export the Vuex store for use by our components
export default new Vuex.Store({
  state,
  mutations,
  actions,
  getters
})
