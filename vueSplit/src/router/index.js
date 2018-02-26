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
      path: '/home/:id',
      name: 'HomeScreen',
      component: HomeScreen,
      props: true
    },
    {
      path: '/editTab/:id',
      name: 'EditTabScreen',
      component: EditTabScreen
    },
    {
      path: '/addReceipt/:id',
      name: 'AddReceiptScreen',
      component: AddReceiptScreen,
      props: true
    },
    {
      path: '/inputAmount/:tabId/:peopleIds', // :peopleIds are comma separated values of the selected ids e.g. 1,5,6
      name: 'InputAmountScreen',
      component: InputAmountScreen
    }
  ]
})
