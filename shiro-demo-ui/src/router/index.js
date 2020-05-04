import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import Index from '../components/Index'

Vue.use(Router)

export default new Router({
  routes: [
    /* page */
    {
      path: '/login',
      // name: 'Login',
      component: Login,
      meta: {
        requireLogin: false
      }
    },
    {
      path: '/',
      // name: 'index',
      component: Index,
      meta: {
        requireLogin: true
      }
    }
  ]
})
