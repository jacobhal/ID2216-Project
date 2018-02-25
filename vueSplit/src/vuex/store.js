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
    {id: '1', title: 'Tentapub', purchases: ['1', '2'], persons: ['1', '2'], totalPrice: 100, tabId: '1'},
    {id: '2', title: 'Bowling', purchases: ['3', '4'], persons: ['3', '1'], totalPrice: 200, tabId: '1'},
    {id: '3', title: 'Cypern', purchases: ['5', '6'], persons: ['2', '3'], totalPrice: 300, tabId: '2'},
    {id: '4', title: 'Kinamuren schleeeeee', purchases: ['7', '8'], persons: ['1', '2'], tabId: '2'}
  ],
  activeReceipt: {},
  purchases: [
    {id: '1', person: '1', price: 100, receiptId: '1'},
    {id: '2', person: '2', price: 200, receiptId: '1'},
    {id: '3', person: '3', price: 300, receiptId: '2'},
    {id: '4', person: '1', price: 101, receiptId: '2'},
    {id: '5', person: '2', price: 201, receiptId: '3'},
    {id: '6', person: '3', price: 301, receiptId: '3'},
    {id: '7', person: '1', price: 102, receiptId: '4'},
    {id: '8', person: '2', price: 202, receiptId: '4'}
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
  GET_RECEIPT (state, receipt) {
    state.activeReceipt = receipt
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
      totalPrice: totalPrice,
      tabId: payload.tabId
    }
    state.receipts.push(newReceipt)
  },
  ADD_PURCHASE (state, payload) {
    const newPurchase = {
      id: uuidv4(),
      person: payload.person,
      price: payload.price,
      receiptId: payload.receiptId
    }
    state.purchases.push(newPurchase)
  },
  ADD_PERSON (state, payload) {
    const newPerson = {
      id: uuidv4(),
      name: payload.name,
      phoneNumber: payload.phoneNumber
    }
    state.persons.push(newPerson)
  },
  DELETE_TAB (state, tab) {
    var tabs = state.tabs
    tabs.splice(tabs.indexOf(tab), 1)
  },
  DELETE_RECEIPT (state, receipt) {
    var receipts = state.receipts
    receipts.splice(receipts.indexOf(receipt), 1)
  },
  DELETE_PURCHASE (state, purchase) {
    var purchases = state.purchases
    purchases.splice(purchases.indexOf(purchase), 1)
  },
  DELETE_PERSON (state, person) {
    var persons = state.persons
    persons.splice(persons.indexOf(person), 1)
  },
  EDIT_TAB (state, tab) {
    var index = state.tabs.findIndex(currentTab => currentTab.id === tab.id)
    state.tabs.splice(index, 1, tab)
  },
  EDIT_RECEIPT (state, receipt) {
    var index = state.receipts.findIndex(currentReceipt => currentReceipt.id === receipt.id)
    state.receipts.splice(index, 1, receipt)
  },
  EDIT_PURCHASE (state, purchase) {
    var index = state.purchases.findIndex(currentPurchase => currentPurchase.id === purchase.id)
    state.purchases.splice(index, 1, purchase)
  },
  EDIT_PERSON (state, person) {
    var index = state.persons.findIndex(currentPerson => currentPerson.id === person.id)
    state.tabs.splice(index, 1, person)
  },
  TOGGLE_TAB (state, tab) {
    tab.running = !tab.running
  },
  ADD_PERSON_TO_RECEIPT (state, payload) {
    var receiptIndex = state.receipts.findIndex(currentReceipt => currentReceipt.id === payload.receiptId)
    var receipt = state.receipts[receiptIndex].persons.push(payload.personId)
    state.receipts.splice(receiptIndex, 1, receipt)
  },
  ADD_PERSON_TO_TAB (state, payload) {
    var tabIndex = state.tabs.findIndex(currentTab => currentTab.id === payload.tabId)
    var tab = state.tabs[tabIndex].persons.push(payload.personId)
    state.tabs.splice(tabIndex, 1, tab)
  },
  DELETE_PERSON_FROM_RECEIPT (state, payload) {
    var receiptIndex = state.receipts.findIndex(currentReceipt => currentReceipt.id === payload.receiptId)
    var receipt = state.receipts[receiptIndex]
    if (receiptIndex !== undefined) {
      var personIndex = receipt.persons.indexOf(payload.personId)
    }
    if (personIndex !== undefined) {
      receipt.persons.splice(personIndex, 1)
    }
  },
  DELETE_PERSON_FROM_TAB (state, payload) {
    var tabIndex = state.tabs.findIndex(currentTab => currentTab.id === payload.tabId)
    var tab = state.tabs[tabIndex]
    if (tabIndex !== undefined) {
      var personIndex = tab.persons.indexOf(payload.personId)
    }
    if (personIndex !== undefined) {
      tab.persons.splice(personIndex, 1)
    }
  }
}

// Actions: event handlers that manipulate the state ASYNCHRONOUSLY
const actions = {
  /**
   * TAB ACTIONS
   */
  // This method can be used to observe changes on the activeTab element in the state
  getTab ({commit}, tab) { commit('GET_TAB', tab) },
  // This method takes an object {title: <TITLE>, title: <TITLE>,
  // receipts: <RECEIPT_ID_ARRAY>, persons: <PERSON_ID_ARRAY>, running: <BOOLEAN>}
  addTab ({commit}, payload) { commit('ADD_TAB', payload) },
  // This method takes an object {id: <ID>, title: <TITLE>, title: <TITLE>,
  // receipts: <RECEIPT_ID_ARRAY>, persons: <PERSON_ID_ARRAY>, running: <BOOLEAN>}
  deleteTab ({commit}, tab) { commit('DELETE_TAB', tab) },
  // This method takes an object {id: <ID>, title: <TITLE>, title: <TITLE>,
  // receipts: <RECEIPT_ID_ARRAY>, persons: <PERSON_ID_ARRAY>, running: <BOOLEAN>}
  editTab ({commit}, tab) { commit('EDIT_TAB', tab) },
  // This method takes an object {id: <ID>, title: <TITLE>, title: <TITLE>,
  // receipts: <RECEIPT_ID_ARRAY>, persons: <PERSON_ID_ARRAY>, running: <BOOLEAN>}
  toggleTab ({commit}, tab) { commit('TOGGLE_TAB', tab) },
  /**
   * RECEIPT ACTIONS
   */
  // This method can be used to observe changes on the activeReceipt element in the state
  getReceipt ({commit}, tab) { commit('GET_RECEIPT', tab) },
  // This method takes an object {title: <TITLE>, purchases: <PURCHASE_ID_ARRAY>,
  // persons: <PERSON_ID_ARRAY>, totalPrice: <TOTALPRICE>, tabId: <TAB_ID>}
  addReceipt ({commit}, payload) {
    commit('ADD_RECEIPT', payload)
    // We need to update the relations for the new receipt
    var addedReceipt = state.receipts[state.receipts.length - 1]
    var tab = state.tabs.find(tab => tab.id === payload.tabId)
    tab.receipts.push(addedReceipt.id)
    commit('EDIT_TAB', tab)
  },
  // This method takes an object {id: <ID>, title: <TITLE>, purchases: <PURCHASE_ID_ARRAY>,
  // persons: <PERSON_ID_ARRAY>, totalPrice: <TOTALPRICE>, tabId: <TAB_ID>}
  // OBS: Has to be an exact match for the object to be deleted
  deleteReceipt ({commit}, receipt) {
    commit('DELETE_RECEIPT', receipt)
    var tab = state.tabs.find(tab => tab.id === receipt.tabId)
    if (tab !== undefined) {
      var receiptIndex = tab.receipts.indexOf(receipt.id)
      tab.receipts.splice(receiptIndex, 1)
    }
  },
  // This method takes an object {id: <ID>, title: <TITLE>, purchases: <PURCHASE_ID_ARRAY>,
  // persons: <PERSON_ID_ARRAY>, totalPrice: <TOTALPRICE>, tabId: <TAB_ID>}
  editReceipt ({commit}, receipt) { commit('EDIT_RECEIPT', receipt) },
  /**
   * PURCHASE ACTIONS
   */
  // This method takes an object {person: <PERSON_ID>, price: <PRICE>, receiptId: <RECEIPT_ID>}
  addPurchase ({commit}, payload) {
    commit('ADD_PURCHASE', payload)
    // We need to update the relations for the new purchase
    var addedPurchase = state.purchases[state.purchases.length - 1]
    var receipt = state.receipts.find(receipt => receipt.id === payload.receiptId)
    receipt.purchases.push(addedPurchase.id)
    receipt.totalPrice += payload.price
    commit('EDIT_RECEIPT', receipt)
  },
  // This method takes an object {id: <ID>, person: <PERSON_ID>, price: <PRICE>, receiptId: <RECEIPT_ID>}
  // OBS: Has to be an exact match for the object to be deleted
  deletePurchase ({commit}, purchase) {
    commit('DELETE_PURCHASE', purchase)
    var receipt = state.receipts.find(receipt => receipt.id === purchase.receiptId)
    if (receipt !== undefined) {
      var purchaseIndex = receipt.purchases.indexOf(purchase.id)
      receipt.purchases.splice(purchaseIndex, 1)
    }
  },
  // This method takes an object {id: <ID>, person: <PERSON_ID>, price: <PRICE>, receiptId: <RECEIPT_ID>}
  editPurchase ({commit}, purchase) { commit('EDIT_PURCHASE', purchase) },
  /**
   * PERSON ACTIONS
   */
  // This method takes an object {name: <NAME>, phoneNumber: <NUMBER>}
  addPerson ({commit}, payload) { commit('ADD_PERSON', payload) },
  // This method takes an object {id: <ID>, name: <NAME>, phoneNumber: <NUMBER>}
  // OBS: Has to be an exact match for the object to be deleted
  deletePerson ({commit}, person) { commit('DELETE_PERSON', person) },
  // This method takes an object {personId: <ID>, tabId: <ID>}
  deletePersonFromTab ({commit}, person) { commit('DELETE_PERSON_FROM_TAB', person) },
  // This method takes an object {personId: <ID>, receiptId: <ID>}
  deletePersonFromReceipt ({commit}, person) { commit('DELETE_PERSON_FROM_RECEIPT', person) },
  // This method takes an object {personId: <ID>, tabId: <ID>}
  addPersonToTab ({commit}, person) { commit('ADD_PERSON_TO_TAB', person) },
  // This method takes an object {personId: <ID>, receiptId: <ID>}
  addPersonToReceipt ({commit}, person) { commit('ADD_PERSON_TO_RECEIPT', person) },
  // This method takes an object {id: <ID>, name: <NAME>, phoneNumber: <NUMBER>}
  editPerson ({commit}, person) { commit('EDIT_PERSON', person) }
}

// Getters: used access data in the store from within our Vue components
const getters = {
  /**
   * TAB GETTERS
   */
  // Get all tabs
  tabs: state => state.tabs,
  // Get a tab by id
  tabById: (state) => (id) => { return state.tabs.find(tab => tab.id === id) },
  // Get all running tabs
  runningTabs: state => { return state.tabs.filter(tab => tab.running) },
  // Get all awaiting payment tabs
  awaitingPaymentTabs: state => { return state.tabs.filter(tab => !tab.running) },
  // Get all receipts of this tab
  tabReceipts: (state) => (tab) => {
    return tab.receipts.map(receiptId => state.receipts.find(receipt => receipt.id === receiptId))
  },
  // Get all persons of this tab
  tabPersons: (state) => (tab) => {
    return tab.persons.map(personId => state.persons.find(person => person.id === personId))
  },
  /**
   * RECEIPT GETTERS
   */
  // Get all receipts
  receipts: state => state.receipts,
  // Get a receipt by id
  receiptById: (state) => (id) => { return state.receipts.find(receipt => receipt.id === id) },
  // Get all purchases of this receipt
  receiptPurchases: (state) => (receipt) => {
    return receipt.purchases.map(purchaseId => state.purchases.find(purchase => purchase.id === purchaseId))
  },
  // Get all persons of this receipt
  receiptPersons: (state) => (receipt) => {
    return receipt.persons.map(personId => state.persons.find(person => person.id === personId))
  },
  // Get the tab that this receipt is associated with
  receiptTab: (state) => (receipt) => { return state.tabs.find(tab => tab.id === receipt.tabId) },
  /**
   * PURCHASE GETTERS
   */
  // Get all purchases
  purchases: state => state.purchases,
  // Get a purchase by id
  purchaseById: (state) => (id) => { return state.purchases.find(purchase => purchase.id === id) },
  // Get the person that this purchase is associated with
  purchasePerson: (state) => (purchase) => {
    return purchase.persons.map(personId => state.persons.find(person => person.id === personId))
  },
  // Get the receipt that this purchase is associated with
  purchaseReceipt: state => (purchase) => {
    return state.receipts.find(receipt => receipt.id === purchase.receiptId)
  },
  /**
   * PERSON GETTERS
   */
  // Get all persons
  persons: state => state.persons,
  // Get a person by id
  personById: (state) => (id) => { return state.persons.find(person => person.id === id) }
}

// create the Vuex instance by combining the state and mutations objects
// then export the Vuex store for use by our components
export default new Vuex.Store({
  state,
  mutations,
  actions,
  getters
})
