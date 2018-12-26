<template>
  <div class="index">
    <Table :context="self" tablePage :data="listData" @on-selection-change="select" :columns="tableColumns"  border></Table>
    <div class="pages-block">
      <div class="pages-right">
        <Page ref="tablePage" :total="total" :page-size="rows" @on-change="change" @on-page-size-change="doPageSizeChange" show-sizer show-total show-elevator ></Page>
      </div>
    </div>
  <!--信息编辑弹出框-->
      <Modal v-model="updateModel" title="更换医生" class="update-model" @on-ok="ok">
        <Form :label-width="80">
          <Table @on-selection-change="doctorselect" border :columns="columns7" :data="doctorlistData"></Table>
        </Form>
      </Modal>
  </div>
</template>
<script>
import {
  getPlanListPage, updatePlanPage, upadtePlan
  // , doctorPage
  , findOptionalDoctor
} from '../../../api/api'
export default {
  props: [
    'searchInfo'
  ],
  data () {
    return {
      self: this,
      selections: [],
      doctorselections: [],
      // 那一行要修改的下标
      whichlineindex: 521,
      total: 0,
      page: 1,
      rows: 10,
      columns7: [
        {
          type: 'selection',
          width: 60,
          key: 'id',
          align: 'center'
        },
        {
          title: '角色',
          key: 'doctorrolename'
        },
        {
          title: '团队成员姓名',
          key: 'doctorname'
        },
        {
          title: '角色职责',
          key: 'roleDuty'
        }
      ],
      listData: [],
      doctorlistData: [],
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
        status: 0
      },
      updateModel: false,
      updateData: {
        applyName: '',
        indentCard: 0,
        applySignType: ''
      },
      updateIndex: 0,
      tableColumns: [
        {
          title: '计划服务时间',
          align: 'center',
          sortable: true,
          key: 'expectservicedate',
          render: (h, params) => {
            var date = this.renderDate(params.row.expectservicedate) // 获取到当前列的服务时间字段毫秒数
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
          title: '签约单号',
          align: 'center',
          key: 'contactnumber'
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
            var date = params.row.isappointment === 1 ? '预约就诊' : params.row.isappointment === 0 ? '上门服务' : ''
            return h('div', [
              h('span', date)
            ])
          }
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
          width: 120,
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
                    icon: 'android-done'
                  },
                  style: {
                    margin: '5px'
                  },
                  on: {
                    click: () => {
                      vm.execute(params.row.id)
                    }
                  }
                }),
                h('div', {
                  slot: 'content'
                }, [
                  h('div', [
                    h('div', '确认执行')
                  ])
                ])
              ]),
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
                    icon: 'android-contacts'
                  },
                  style: {
                    margin: '5px'
                  },
                  on: {
                    click: () => {
                      this.changeDoctor(params.index)
                    }
                  }
                }),
                h('div', {
                  slot: 'content'
                }, [
                  h('div', [
                    h('div', '更换医生')
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
      if (this.searchInfo[0].status === 0) {
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
          this.listData = res.data.rows
          this.$refs.tablePage.currentPage = 1
          // console.log(this.listData)
        })
      }
    }
  },
  created: function () {
    this.$Loading.start()
    let para = {
      page: 1,
      rows: this.rows,
      searchParams: this.searchInfos
    }
    getPlanListPage(para).then((res) => {
      this.$Loading.finish()
      this.total = res.data.total
      this.listData = res.data.rows
      // console.log(this.listData)
    })
  },
  methods: {
    // 打开更换医生弹出框
    changeDoctor (index) {
      // console.log(this.listData[index])
      this.whichlineindex = index
      let para = {
        serviceidentify: this.listData[index].serviceidentify,
        medicalteamidentity: this.listData[index].medicalteamidentity
      }
      this.$Loading.start()
      findOptionalDoctor(para).then((res) => {
        this.$Loading.finish()
        this.doctorlistData = res.data
      })
      this.updateModel = true
    },
    // 更换医生保存
    ok () {
      let self = this
      var id = ''
      var name = ''
      self.doctorselections.forEach(function (element) {
        id = element.id // 弹出框当前选中的医生的id
        name = element.doctorname // 弹出框当前选中的医生的name
      }, this)
      console.log(id === null)
      if (id !== '') {
        var params = {
          id: this.listData[this.whichlineindex].id, // 签约服务id
          doctoridentity: id, // 签约服务医生外键
          doctorname: name // 签约服务医生姓名
        }
        upadtePlan(params).then((res) => {
          if (res.status === 200) {
            this.$Message.success('更换医生成功')
            this.change(this.page)
          } else {
            this.$Message.error('更换医生失败')
          }
        })
        this.whichlineindex = 521
      }
    },
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
    // 确认执行
    execute (id) {
      var params = {
        id: id,
        status: 1
      }
      updatePlanPage(params).then((res) => {
        if (res.status === 200) {
          let para = {
            page: 1,
            rows: this.rows,
            order: 'expect_service_date',
            // 排序
            sort: this.searchInfos.sort,
            searchParams: this.searchInfos
          }
          getPlanListPage(para).then((res) => { // 重新渲染页面
            this.$Loading.finish()
            this.total = res.data.total
            this.listData = res.data.rows
            this.$refs.tablePage.currentPage = 1
            this.selections = []
          })
          this.$Message.success('确认执行成功')
        } else {
          this.$Message.error('确认执行失败')
        }
      })
    },
    doShowMsg (index) {
      this.$Modal.info({
        title: '用户信息',
        content: `姓名：${this.listData[index].applyName}<br>身份证号：${this.listData[index].indentCard}<br>签约类型：${this.listData[index].applySignType}`
      })
    },
    // 惦记页面事件
    change (page) {
      // console.log(this.searchInfos)
      this.selections = []
      let para = {
        page: page,
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
        this.listData = res.data.rows
      })
    },
     // 改变当前显示的行数
    doPageSizeChange: function (size) {
      this.rows = size
      let para = {
        page: this.page,
        rows: size,
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
    select (selection) {
      this.selections = selection
    },
    doctorselect (selection) {
      this.doctorselections = selection
    }
  }
}
</script>
<style scoped lang="less">
.index {
  height: 550px;
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
