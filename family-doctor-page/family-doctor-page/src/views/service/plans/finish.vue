<template>
  <div class="index">
    <Table :data="listData" @on-selection-change="select" :columns="tableColumns"  border></Table>
    <div class="pages-block">
      <div class="pages-right">
        <Page :total="total" :page-size="rows" @on-change="change"  show-total show-elevator show-sizer></Page>
      </div>
    </div>
  </div>
</template>
<script>
import {
  getPlanListPage
} from '../../../api/api'
export default {
  props: [
    'searchInfo'
  ],
  data () {
    return {
      selections: [],
      total: 0,
      page: 1,
      rows: 10,
      listData: [],
      // 查询条件的存放
      searchInfos: {
        // 开始时间
        beginDate: '',
        // 结束时间
        endDate: '',
        // 最近
        recently: '',
        // 关键字
        keyWords: '',
        // 排序
        sort: '',
        status: 3
      },
      updateData: {
        applyName: '',
        indentCard: 0,
        applySignType: ''
      },
      updateIndex: 0,
      tableColumns: [
        {
          title: '服务时间',
          align: 'center',
          sortable: true,
          key: 'updatedate',
          render: (h, params) => {
            var date = this.renderDate(params.row.updatedate) // 获取到当前列的服务时间字段毫秒数
            return h('div', [
              h('span', date)
            ])
          }
        },
        {
          title: '服务对象',
          align: 'center',
          key: 'membername'
        },
        {
          title: '服务单号',
          align: 'center',
          key: 'servicenumber'
        },
        {
          title: '本次医疗人员',
          align: 'center',
          key: 'doctorname'
        },
        {
          title: '本次医疗服务内容',
          align: 'center',
          key: 'servicecategoryname'
        },
        {
          title: '本次医疗服务方式',
          align: 'center',
          key: 'servicecategoryname',
          render: (h, params) => {
            var date = params.row.isappointment === true ? '预约就诊' : '上门服务'
            return h('div', [
              h('span', date)
            ])
          }
        },
        {
          title: '签约单号',
          align: 'center',
          key: 'contactnumber'
        },
        {
          title: '联系电话',
          key: 'residenttelphone',
          align: 'center'
        },
        {
          title: '地址',
          key: 'residentaddress',
          align: 'center'
        },
        {
          title: '操作',
          key: 'action',
          width: 80,
          align: 'center',
          render: (h, params) => {
            let vm = this
            return h('div', [
              h('Tooltip', {
                props: {
                  placement: 'top'
                }
              }, [
                h('Button', {
                  props: {
                    type: 'ghost',
                    size: 'small',
                    shape: 'circle',
                    icon: 'ios-search'
                  },
                  style: {
                    margin: '5px'
                  },
                  on: {
                    click: () => {
                      vm.evaluate(params.row.id)
                    }
                  }
                }),
                h('div', {
                  slot: 'content'
                }, [
                  h('div', [
                    h('div', '详情')
                  ])
                ])
              ])
            ])
          }
        }
      ]
    }
  },
  watch: {
    searchInfo: function () {
      if (this.searchInfo[0].status === 3) {
        this.searchInfos.beginDate = this.searchInfo[0].beginDate
        this.searchInfos.endDate = this.searchInfo[0].endDate
        this.searchInfos.recently = this.searchInfo[0].recently
        this.searchInfos.keyWords = this.searchInfo[0].keyWords
        this.searchInfos.sort = this.searchInfo[0].sort
        let para = {
          page: 1,
          rows: this.rows,
          order: 'expect_service_date',
          // 排序
          sort: this.searchInfos.sort,
          searchParams: this.searchInfos
        }
        this.$Loading.start()
        getPlanListPage(para).then((res) => {
          this.$Loading.finish()
          this.total = res.data.total
          this.page = res.data.page
          this.listData = res.data.rows
        })
      }
    }
  },
  created: function () {
  },
  methods: {
     // 毫秒数转换为标准时间
    renderDate (date) {
      var commonTime = new Date(date)
      var time = this.formatDate(commonTime) // 标准时间转换为指定格式
      return time
    },
    // 日期格式转换
    formatDate (date) {
      var y = date.getFullYear()
      var m = date.getMonth() + 1
      m = m < 10 ? '0' + m : m
      var d = date.getDate()
      d = d < 10 ? ('0' + d) : d
      var h = date.getHours()
      var minute = date.getMinutes()
      minute = minute < 10 ? ('0' + minute) : minute
      var newDate = y + '-' + m + '-' + d + ' ' + h + ':' + minute
      return newDate
    },
    change (row) {
      this.selections = []
      let para = {
        page: row,
        rows: this.rows,
        order: 'expect_service_date',
        // 排序
        sort: this.searchInfos.sort,
        searchParams: this.searchInfos
      }
      this.$Loading.start()
      getPlanListPage(para).then((res) => {
        this.$Loading.finish()
        this.total = res.data.total
        this.page = res.data.page
        this.listData = res.data.rows
      })
    },
    // 改变当前显示的行数
    doPageSizeChange: function (size) {
      this.selections = []
      this.rows = size
      let para = {
        page: 1,
        rows: this.rows,
        order: 'expect_service_date',
        // 排序
        sort: this.searchInfos.sort,
        searchParams: this.searchInfos
      }
      this.$Loading.start()
      getPlanListPage(para).then((res) => {
        this.$Loading.finish()
        this.total = res.data.total
        this.$refs.tablePage.currentPage = 1
        this.listData = res.data.rows
      })
    },
    // 评价
    evaluate (id) {
      this.$router.push({ name: 'evaluate', params: {id: id} })
    },
    select (selection) {
      this.selections = selection
    }
  }
}
</script>
<style scoped lang="less">
.index {
    height: 90%;
  overflow: auto;
  .lable-title {
    text-align: center;
  }
  .pages-block {
    margin-top: 10px;
    text-align: center;
    .pages-right {
      vertical-align: middle;
    }
  }
  .update-model .row {
    display: flex;
    align-items: center;
    margin-bottom: 10px;
  }
}
</style>
