import Vue from 'vue'
import Vuex from 'vuex'
import slidemenu from './modules/sidemenu'
import user from './modules/user'
import count from './modules/count'
import subsysvuex from '../../store'
Vue.use(Vuex)

const sysvuex = new Vuex.Store({
  // actions,
  // getters,
  // state,
  modules: {
    slidemenu,
    user,
    count,
    ...subsysvuex
  }
})

// 创建store实例
export default sysvuex
