import Vue from 'vue'
import Router from 'vue-router'
import WelcomeScreen from '@/components/WelcomeScreen'
import HomeScreen from '@/components/HomeScreen'
import AllTabs from '@/components/AllTabsScreen'

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
      component: AllTabs
    },
    {
      path: '/home',
      name: 'HomeScreen',
      component: HomeScreen
    }
  ]
})
