import Vue from 'vue'
import Router from 'vue-router'
import WelcomeScreen from '@/components/WelcomeScreen'
import HomeScreen from '@/components/HomeScreen'
import AllTabs from '@/components/AllTabs'
import AddReceiptScreen from '@/components/AddReceiptScreen'

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
      name: 'AllTabs',
      component: AllTabs
    },
    {
      path: '/home',
      name: 'HomeScreen',
      component: HomeScreen
    },
    {
      path: '/addReceipt',
      name: 'AddReceiptScreen',
      component: AddReceiptScreen
    }
  ]
})
