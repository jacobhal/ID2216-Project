import Vue from 'vue'
import Router from 'vue-router'
import WelcomeScreen from '@/components/WelcomeScreen'
import HomeScreen from '@/components/HomeScreen'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'WelcomeScreen',
      component: WelcomeScreen
    },
    {
      path: '/jacob',
      name: 'HomeScreen',
      component: HomeScreen
    }
  ]
})
