import Vue from 'vue'
import Router from 'vue-router'
import WelcomeScreen from '@/components/WelcomeScreen'
import HomeScreen from '@/components/HomeScreen'
import AddReceiptScreen from '@/components/AddReceiptScreen'
import AllTabsScreen from '@/components/AllTabsScreen'
import EditTabScreen from '@/components/EditTabScreen'
import InputAmountScreen from '@/components/InputAmountScreen'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'WelcomeScreen',
      component: WelcomeScreen
    },
    {
      path: '/allTabs',
      name: 'AllTabsScreen',
      component: AllTabsScreen
    },
    {
      path: '/home',
      name: 'HomeScreen',
      component: HomeScreen
    },
    {
      path: '/editTab/:id',
      name: 'EditTabScreen',
      component: EditTabScreen
    },
    {
      path: '/addReceipt',
      name: 'AddReceiptScreen',
      component: AddReceiptScreen
    },
    {
      path: '/inputAmount',
      name: 'InputAmountScreen',
      component: InputAmountScreen
    }
  ]
})
