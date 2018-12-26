import Vue from 'vue'
import Router from 'vue-router'
import routes from './router.config'
import iView from 'iview'
import 'iview/dist/styles/iview.css' // 使用 CSS
Vue.use(iView)
Vue.use(Router)

export default new Router({
  routes
})
