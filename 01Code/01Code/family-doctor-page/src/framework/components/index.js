import Vue from 'vue'
import Grid from './grid/index.js'
import GlobalSpiner from './spiner'
import Spiner from './spiner/spiner.vue'
import Excel from './excel/excel.vue'
import Badge from './badge/badge.vue'
import Panel from './panel/panel.vue'
import Schedule from './schedule/schedule.vue'

const climb = {
  grid: Grid,
  spiner: Spiner,
  excel: Excel,
  badge: Badge,
  panel: Panel,
  schedule: Schedule
}

// console.info(Vue)
Object.keys(climb).forEach(key => {
  Vue.component('climb-' + key, climb[key])
})
Vue.prototype.$Spiner = GlobalSpiner
