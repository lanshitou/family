import axios from 'axios'
import {getStorageValue} from '../framework/common/storage'
// import vm from '../main.js'
import Vue from 'vue'
const vm = new Vue()
function createAxios (baseUrl) {
  let instance = axios.create({
    baseURL: baseUrl,
    headers: {
      'usertoken': getStorageValue('local', 'user').usertoken,
      'userid': getStorageValue('local', 'user').id
      // 'userid': '37911018F4C34EC7A01E4338E8F764BC',
      // 'usertoken': '32FBAE5EE94243F39EAC3D89B930B8F5'
    }
  })
  instance.interceptors.request.use(
    function (config) {
      vm.$Spiner.start()
      // Do something before request is sent
      return config
    },
    function (error) {
      vm.$Spiner.finish()
      // Do something with request error
      return Promise.reject(error)
    }
  )
  instance.interceptors.response.use(
    function (res) {
      vm.$Spiner.finish()
      return res
    },
    function (res) {
      console.info('******************************')
      vm.$Spiner.finish()
      vm.$Message.warning(res.response.data.message, 5)
      if (res.response.data.code === '0') {
        if (res.response.data.message.indexOf('重新登录') > 0) {
          window.location.href = global.loginHost + '/login'
        }
      }
      return Promise.reject(res)
    }
  )
  return instance
}
// 单点登录系统地址为下面配置的bseURL
var apiSSOBaseConfig = createAxios(global.apiSSOBaseConfigURL)
var apiBaseConfig = createAxios(global.apiBaseConfigURL)

export {
  apiSSOBaseConfig,
  apiBaseConfig
}
