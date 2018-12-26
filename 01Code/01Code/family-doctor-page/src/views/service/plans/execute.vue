<template>
  <div class="index">
    <Table :context="self" :data="listData" @on-selection-change="select" :columns="tableColumns"  border></Table>
    <div class="pages-block">
      <div class="pages-right">
        <Page ref="tablePage" :total="total" :page-size="rows" @on-change="change" @on-page-size-change="doPageSizeChange" show-total show-elevator show-sizer></Page>
      </div>
    </div>
    <Modal v-model="modal1" title="申请改期" @on-ok="saveDate" @on-cancel="cancelDate">
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
  getPlanListPage, upadtePlan, doctorPage
} from '../../../api/api'
export default {
  props: [
    'searchInfo'
  ],
  data () {
    return {
      self: this,
      modal1: false,
      remindFlag: true,
      selections: [],
      doctorselections: [],
      // 那一行要修改的下标
      whichlineindex: 521,
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
        status: 1
      },
      updateModel: false,
      doctorlistData: [],
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
      changeTime: '',
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
                    icon: 'ios-alarm'
                  },
                  style: {
                    margin: '5px'
                  },
                  on: {
                    click: () => {
                      // remind
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
                    icon: 'clipboard'
                  },
                  style: {
                    margin: '5px'
                  },
                  on: {
                    click: () => {
                      vm.$router.push({ name: 'inputConclusion', params: {id: params.row.id, medicalteamidentity: params.row.medicalteamidentity} })
                    }
                  }
                }),
                h('div', {
                  slot: 'content'
                }, [
                  h('div', [
                    h('div', '录入结论')
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
                    h('div', '申请改期')
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
          sortable: true,
          width: 180,
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
      if (this.searchInfo[0].status === 1) {
        this.searchInfos.beginDate = this.searchInfo[0].beginDate
        this.searchInfos.endDate = this.searchInfo[0].endDate
        this.searchInfos.recently = this.searchInfo[0].recently
        this.searchInfos.keyWords = this.searchInfo[0].keyWords
        this.searchInfos.sort = this.searchInfo[0].sort
        let para = {
          page: 1,
          rows: this.rows,
          order: 'expect_service_date',
          sort: this.searchInfos.sort,
          searchParams: this.searchInfos
        }
        this.$Loading.start()
        getPlanListPage(para).then((res) => {
          this.$Loading.finish()
          this.total = res.data.total
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
    // 发送提醒
    remind () {
      if (this.remindFlag) {
        this.$Notice.success({
          title: '发送提醒',
          desc: '提醒发送成功'
        })
        this.remindFlag = false
        let that = this
        setTimeout(function () { that.remindFlag = true }, 86400000)
      } else {
        this.$Notice.warning({
          title: '发送提醒',
          desc: '24小时内不能重复发送提醒'
        })
      }
    },
    // 申请改期
    changeDate (index) {
      this.whichlineindex = index
      this.modal1 = true // 打开申请改期弹出框
      this.changeTime = this.listData[this.whichlineindex].expectservicedate
    },
    // 改期保存
    saveDate () {
      var params = {
        id: this.listData[this.whichlineindex].id,
        expectservicedate: this.changeTime // 当前选择的时间
      }
      upadtePlan(params).then((res) => {
        if (res.status === 200) {
          this.$Message.success('申请改期成功')
          this.change(this.page)
        } else {
          this.$Message.error('申请改期失败')
        }
      })
      this.whichlineindex = 521
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
      this.changeTime = newDate
      return newDate
    },
    // 改期取消
    cancelDate () {
      this.$Message.info('取消改期！')
    },
    doShowMsg (index) {
      this.$Modal.info({
        title: '用户信息',
        content: `姓名：${this.listData[index].applyName}<br>身份证号：${this.listData[index].indentCard}<br>签约类型：${this.listData[index].applySignType}`
      })
    },
    change (page) {
      this.selections = []
      let para = {
        page: page,
        rows: this.rows,
        order: 'expect_service_date',
        sort: this.searchInfos.sort,
        searchParams: this.searchInfos
      }
      this.$Loading.start()
      getPlanListPage(para).then((res) => {
        this.$Loading.finish()
        this.total = res.data.total
        this.listData = res.data.rows
        this.selections = []
      })
    },
    // 改变当前显示的行数
    doPageSizeChange: function (size) {
      this.rows = size
      this.selections = []
      let para = {
        page: 1,
        rows: size,
        order: 'expect_service_date',
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
  height: 360px;
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
