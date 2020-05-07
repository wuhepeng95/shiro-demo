// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'

// 引入elementUI
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

// 引入axios
import { AXIOS } from './components/http-common'

Vue.use(ElementUI)

Vue.prototype.$axios = AXIOS

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: {App},
  template: '<App/>'
})

// token验证
router.beforeEach((to, from, next) => {
  if (to.matched.some(record => record.meta.requireLogin)) {
    if (localStorage.getItem('token')) {
      next()
    } else {
      console.log('无token 未登录')
      next({
        // 区分大小写
        path: '/login'
      })
    }
  } else {
    next()
  }
})
