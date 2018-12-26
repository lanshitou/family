<template>
  <div >
    <h1>xlsx-table</h1>
    <climb-excel @on-select-file="handleSelectedFile">
      请选择一个excel文件导入
    </climb-excel>
    <p class="mt10 mb10">
      <Button v-for="it in btns" type="primary" @click="switchTab(it)" class="mr10">{{it}}</Button>
    </p>
    <p class="mt10 mb10">
      <!--目前只支持-->
      注：单元格要求单一表格（即无合并表格）
    </p>
    <Table v-if="tableViewState" small :columns="columns" :data="data" ></Table>
  </div>
</template>

<script>
export default {
  data () {
    return {
      columns: [],
      data: [],
      excel: '',
      tableViewState: false,
      btns: []
    }
  },
  props: {
    info: {
      type: Boolean,
      default: true
    }
  },
  methods: {
    handleSelectedFile (convertedData) {
      // console.log(convertedData)
      this.excel = convertedData
      this.tableViewState = false
      let _keys = Object.keys(convertedData)
      console.info(_keys)
      this.btns = _keys
      // this.$forceUpdate()
      // this.initData(convertedData)
    },
    switchTab (key) {
      let vm = this
      vm.initData(vm['excel'][key])
      vm.tableViewState = true
    },
    initData (ed) {
      // s
      let vm = this
      let _data = []
      let _columns = []
      ed.header.forEach(function (element, index, v) {
        // element
        _columns.push({
          key: v[index],
          title: element
        })
      })
      if (vm.info) {
        _columns.push({
          key: 'actions',
          title: '操作',
          render: (h, params) => {
            return h('div', [
              h('Button', {
                props: {
                  type: 'primary',
                  size: 'small'
                },
                style: {
                  marginRight: '5px'
                },
                on: {
                  click: () => {
                    this.show(params.index)
                  }
                }
              }, '查看')
              // h('Button', {
              //   props: {
              //     type: 'error',
              //     size: 'small'
              //   },
              //   on: {
              //     click: () => {
              //       this.remove(params.index)
              //     }
              //   }
              // }, '删除')
            ])
          }
        })
      }
      ed.body.forEach(function (element, index, v) {
        _data.push(element)
      })
      vm.columns = [..._columns]
      vm.data = [..._data]
    },
    show (index) {
      let vm = this
      let str = ''
      for (let p in vm.data[index]) {
        str += `<p class="excel-info"><span class="excel-label">${p}:</span> ${vm.data[index][p]}</p>`
      }
      this.$Modal.info({
        title: '用户信息',
        width: '600',
        content: str,
        render: (h, params) => {
          return h('div', {
            domProps: {
              innerHTML: str
            },
            attrs: {
              class: 'excel-cont'
            }
          })
        }
      })
    },
    remove (index) {
      this.data6.splice(index, 1)
    }
  }
}
</script>

<style lang="scss">
  .excel-cont{
    margin-top: 30px;
    padding-left: 26px;
    .excel-info{
      font-size: 14px;
      line-height: 36px;
      .excel-label {
        display: inline-block;
        min-width: 80px;
        margin-right: 6px;
      }
    }
  }
</style>
