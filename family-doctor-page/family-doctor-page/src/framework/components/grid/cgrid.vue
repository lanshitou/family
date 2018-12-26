<template>
  <div class="climb-grid">
    <!--<Spin size="large" v-if="loading" class="cgrid-load"></Spin>-->
    <table class='table ivu-table' :class="classes">
      <thead>
        <tr>
          <th v-for='item in columns'>{{item.title}}</th>
        </tr>
      </thead> 
      <tbody>
        <template v-if="async">
          <tr v-for='(item, index) in initItems' v-show="show(item)">
            <td v-for="(c, idx) in columns">
              <template v-if="idx < 1">
                <i v-if="item.level > 1" class="ms-tree-space" v-for="i in item.level-1"></i>
                <i v-if="item.hasSub" class="fa c-toggle-btn" :class="{'fa-minus':item.expanded,'fa-plus':!item.expanded }" @click="toggle(index,item)"></i>
              </template>
              {{item[c.key]}}
            </td>
          </tr>
        </template>
        <template v-else>
          <tr v-for='(item, index) in initItems' v-show="show(item)">
            <td v-for="(c, idx) in columns">
              <template v-if="idx < 1">
                <i v-if="item.level > 1" class="ms-tree-space" v-for="i in item.level-1"></i>
                <i v-if="item.children" class="fa c-toggle-btn" :class="{'fa-minus':item.expanded,'fa-plus':!item.expanded }" @click="toggle(index,item)"></i>
              </template>
              {{item[c.key]}}
            </td>
          </tr>
        </template>
      </tbody>
    </table>
    <div class="info" v-if="initItems.length < 1">
      <p class="text-center">暂无数据</p>
    </div>
  </div>
</template>
<script>
import Vue from 'vue'
// import {apiBaseConfig} from '../../api'
export default {
  name: 'treeGrid',
  data: function () {
    return {
      initItems: [],
      loading: false,
      tempRows: 0,
      currentIndex: null,
      currentLevel: null
    }
  },
  props: {
    data: {
      type: Array,
      default: function () {
        return []
      }
    },
    columns: {
      type: Array,
      default: function () {
        return []
      }
    },
    async: {
      type: Boolean,
      default: false
    },
    asyncData: {
      type: Array,
      default: function () {
        return []
      }
    },
    border: {
      type: Boolean,
      default: false
    },
    size: {
      validator (value) {
        return ['small', 'large', 'default'].includes(value)
      }
    },
    small: {
      type: Boolean,
      default: false
    }
  },
  computed: {
    classes: function () {
      return [
        {
          'ivu-table-border': this.border,
          [`ivu-table-${this.size}`]: !!this.size
        }
      ]
    }
  },
  watch: {
    data: {
      handler () {
        let vm = this
        if (vm.async) {
          vm.initData(vm.data, 1, null, vm.initItems)
        }
      },
      deep: true
    },
    asyncData: {
      handler () {
        let vm = this
        if (vm.async) {
          vm.insertSubDatas(vm.initItems[vm.currentIndex], vm.currentIndex, vm.asyncData, vm.currentLevel)
        }
      },
      deep: true
    }
  },
  created: function () {
    let me = this
    if (me.async) {
    }
    if (me.data) {
      me.initData(me.data, 1, null, me.initItems)
    }
  },
  methods: {
    initData: function (items, level, parent, container) {
      let me = this
      items.forEach(function (item, index) {
        item = Object.assign({}, item, {'parent': parent, 'level': level})
        if ((typeof item.expanded) === 'undefined') {
          item = Object.assign({}, item, { 'expanded': false })
        }
        if ((typeof item.hasSub) === 'undefined') {
          item = Object.assign({}, item, { 'hasSub': true })
        }
        if ((typeof item.show) === 'undefined') {
          item = Object.assign({}, item, { 'isShow': false })
        }
        item = Object.assign({}, item, {'load': item.expanded})
        container.push(item)
        if (item.children && !me.async) {
          me.initData(item.children, level + 1, item, me.initItems)
        }
      })
    },
    show: function (item) {
      return ((item.level === 1) || (item.parent && item.parent.expanded && item.isShow))
    },
    toggle: function (index, item) {
      let vm = this
      vm.currentLevel = item.level + 1
      if (vm.async) {
        if (!item.children) {
          vm.currentIndex = index
          item.hasSub = false
          vm.$emit('on-async', item)
        }
        if (item.children) {
          vm.toggleSub(index, item, vm.currentLevel)
        }
      } else {
        vm.toggleSub(index, item, vm.currentLevel)
      }
    },
    open: function (index, item) {
      let me = this
      if (item.children) {
        open(index, item.children)
      }
      me.tempRows = 0
      function open (index, items) {
        items.forEach(function (child, childIndex) {
          me.initItems[index + childIndex + 1].isShow = true
          if (child.children) {
            open(index + me.tempRows + childIndex + 1, child.children)
            me.tempRows += child.children.length
          }
        })
      }
    },
    close: function (index, item) {
      let me = this
      if (item.children) {
        close(index, item.children)
      }
      me.tempRows = 0
      function close (idex, items) {
        items.forEach(function (child, childIndex) {
          me.initItems[idex + childIndex + 1].isShow = false
          if (child.children && !item.expanded) {
            close(idex + me.tempRows + childIndex + 1, child.children)
            me.tempRows += child.children.length
          }
        })
      }
    },
    insertSubDatas: function (item, index, datas, level) {
      let vm = this
      item.hasSub = true
      let container = []
      vm.initData(datas, item.level + 1, item, container)
      item.children = container
      vm.initItems.splice(index + 1, 0, ...container)
      vm.toggleSub(index, item, level)
    },
    toggleSub: function (index, item, level) {
      let vm = this
      if (item.children) {
        if (item.expanded) {
          item.expanded = !item.expanded
          vm.close(index, item)
        } else {
          item.expanded = !item.expanded
          if (item.load) {
            vm.open(index, item)
          } else {
            item.load = true
            let count = 0
            item.children.forEach(function (child, childIndex) {
              if (vm.initItems[index + childIndex + count + 1]) {
                Vue.set(vm.initItems[index + childIndex + count + 1], 'parent', item)
                Vue.set(vm.initItems[index + childIndex + count + 1], 'level', vm.currentLevel)
                Vue.set(vm.initItems[index + childIndex + count + 1], 'isShow', true)
                Vue.set(vm.initItems[index + childIndex + count + 1], 'load', false)
                Vue.set(vm.initItems[index + childIndex + count + 1], 'expanded', false)
                Vue.set(vm.initItems[index + childIndex + count + 1], 'hasSub', true)
              }
              if (child.children) {
                count = child.children.length
              }
            })
          }
        }
      }
    }
  }
}
</script>
