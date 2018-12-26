// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import 'iview/dist/styles/iview.css'
import iView from 'iview'
import store from './framework/store'
import 'conifg'
import './framework/components/index.js'
import './framework/directive/index.js'
import AMap from 'vue-amap'
/* eslint-disable no-new */
Vue.use(AMap)
AMap.initAMapApiLoader({
  key: 'YOUR_KEY',
  plugin: ['']
})
Vue.use(iView)
let root = new Vue({
  router,
  store,
  template: '<App/>',
  components: { App }
})
/* 非框架级tab挂载vue示例 end */
root.$mount('#app')
/* 非框架级tab挂载vue示例 end */
