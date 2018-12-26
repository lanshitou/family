<template>
  <div class="index">
    <Table :context="self" :data="listData" @on-selection-change="select" :columns="tableColumns"  border></Table>
    <div class="pages-block">
      <div class="pages-right">
        <Page :total="total" :page-size="rows" @on-change="change"  show-total show-elevator show-sizer></Page>
      </div>
    </div>
    <Modal v-model="modal1" title="申请改期" @on-ok="saveDate">
      <div class='filterTitle'>选择最新服务计划时间：</div>
      <Date-picker format="yyyy-MM-dd HH:mm"  v-model="changeTime" type="datetime" placeholder="选择日期和时间" style="width: 200px"></Date-picker>
    </Modal>
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
  getPlanListPage, updatePlanPage, doctorPage, upadtePlan
} from '../../../api/api'
import expandRow from './expand_row.vue'
export default {
  props: [
    'searchInfo'
  ],
  components: {
    expandRow
  },
  data () {
    return {
      self: this,
      modal1: false,
      selections: [],
      total: 0,
      page: 1,
      rows: 10,
      changeTime: '',
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
        status: 2
      },
      updateModel: false,
      doctorlistData: [],
      // 那一行要修改的下标
      whichlineindex: 521,
      doctorselections: [],
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
      updateData: {
        applyName: '',
        indentCard: 0,
        applySignType: ''
      },
      updateIndex: 0,
      tableColumns: [
        {
          title: '操作',
          key: 'action',
          width: 250,
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
                    icon: 'ios-alarm '
                  },
                  style: {
                    margin: '5px'
                  },
                  on: {
                    click: () => {
                      vm.remind(params.row.id)
                    }
                  }
                }),
                h('div', {
                  slot: 'content'
                }, [
                  h('div', [
                    h('div', '发送提醒')
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
                    icon: 'ios-timer'
                  },
                  style: {
                    margin: '5px'
                  },
                  on: {
                    click: () => {
                      vm.changeDate(params.index)
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
                    icon: 'ios-telephone'
                  },
                  style: {
                    margin: '5px'
                  },
                  on: {
                    click: () => {
                      this.$router.push({ name: 'appointment', params: {identitynumber: params.row.identitynumber} })
                    }
                  }
                }),
                h('div', {
                  slot: 'content'
                }, [
                  h('div', [
                    h('div', '签约服务预约')
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
                    icon: 'android-exit'
                  },
                  style: {
                    margin: '5px'
                  },
                  on: {
                    click: () => {
                      this.$router.push({ name: 'referral/add', params: {id: params.row.id} })
                    }
                  }
                }),
                h('div', {
                  slot: 'content'
                }, [
                  h('div', [
                    h('div', '转诊预约')
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
        },
        {
          title: '计划服务时间',
          align: 'center',
          width: 180,
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
          width: 180,
          key: 'membername'
        },
        {
          title: '本次医疗人员',
          align: 'center',
          width: 180,
          key: 'doctorname'
        },
        {
          title: '本次医疗服务内容',
          align: 'center',
          width: 180,
          key: 'servicecategoryname'
        },
        {
          title: '本次医疗服务方式',
          align: 'center',
          width: 180,
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
          width: 180,
          key: 'contactnumber'
        },
        {
          title: '联系电话',
          key: 'residenttelphone',
          width: 180,
          align: 'center'

        },
        {
          title: '地址',
          key: 'residentaddress',
          width: 180,
          align: 'center'

        }
      ]
    }
  },
  watch: {
    searchInfo: function () {
      if (this.searchInfo[0].status === 2) {
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
    // 打开更换医生弹出框
    changeDoctor (index) {
      this.whichlineindex = index
      let para = {
        servcie_item_detail_identity: 1
      }
      this.$Loading.start()
      doctorPage(para).then((res) => {
        this.$Loading.finish()
        this.doctorlistData = res.data
      })
      this.updateModel = true
    },
    // 更换医生保存
    ok () {
      let self = this
      var id = 0
      var name = ''
      self.doctorselections.forEach(function (element) {
        id = element.id // 弹出框当前选中的医生的id
        name = element.doctorname // 弹出框当前选中的医生的name
      }, this)
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
    // 确认执行(之前状态已过期)
    execute (index) {
      this.whichlineindex = index
      this.modal1 = true // 打开申请改期弹出框
      this.changeTime = this.selections[0].expectservicedate
    },
    // 改期保存
    saveDate () {
      var params = {
        id: this.listData[this.whichlineindex].id,
        status: 1,
        expectservicedate: this.changeTime // 当前选择的时间
      }
      updatePlanPage(params).then((res) => {
        if (res.status === 200) {
          this.$Message.success('申请改期成功')
          this.change(this.page)
          this.whichlineindex = 521
        } else {
          this.$Message.error('申请改期失败')
        }
      })
    },
    // 发送提醒
    remind () {
      this.$Notice.success({
        title: '发送提醒',
        desc: '提醒发送成功！'
      })
    },
    // 申请改期
    changeDate (index) {
      this.whichlineindex = index
      this.modal1 = true // 打开申请改期弹出框
      this.changeTime = this.listData[this.whichlineindex].expectservicedate
    },
    // 签约服务预约
    appointment () {
      this.$router.push({ path: '../appointment' })
    },
    // 转诊预约
    referral () {
      this.$router.push({ path: '../referral' })
    },
    doShowMsg (index) {
      this.$Modal.info({
        title: '用户信息',
        content: `姓名：${this.listData[index].applyName}<br>身份证号：${this.listData[index].indentCard}<br>签约类型：${this.listData[index].applySignType}`
      })
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
      this.rows = size
      this.selections = []
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
