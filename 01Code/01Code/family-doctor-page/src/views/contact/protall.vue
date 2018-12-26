<template>
  <div class="index">
    <Table heigth="200px" :context="self" :data="listData" @on-selection-change="select" :columns="tableColumns"  border
    ></Table>
    <div class="pages-block">
      <div class="pages-right ">
        <Page heigth="200px" :total="total" :page-size="rows" @on-page-size-change="doPageSizeChange" @on-change="change" show-sizer  show-total
           show-elevator></Page>
      </div>
    </div>
  </div>
</template>

<script>
import {
   getContactPage, updateContactByKey
} from '../../api/api'
export default {
  props: [
    'searchInfo'
  ],
  data () {
    return {
      self: this,
      selections: [],
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
        sort: ''
      },
      total: 0,
      page: 1,
      // 每页显示几条
      rows: 10,
      listData: [],
      updateModel: false,
      updateData: {
        applyName: '',
        indentCard: 0,
        applySignType: ''
      },
      updateIndex: 0,
      tableColumns: [
        {
          title: '操作',
          width: '250',
          align: 'center',
          render: (h, params) => {
            // 待审批有审批
            var shenpi = params.row.applystatus === 0 ? 'inline' : 'none'
            // 待审批有删除
            var shanchu = params.row.applystatus === 0 ? 'inline' : 'none'
            // 已生效有解除
            var jiechu = params.row.applystatus === 1 ? 'inline' : 'none'
            // 待审批和已生效有修改
            var xiugai = params.row.applystatus === 0 || params.row.applystatus === 1 ? 'inline' : 'none'
            // 已生效和已结束有服务计划
            var fuwujiahua = params.row.applystatus === 1 || params.row.applystatus === 2 || params.row.applystatus === 3 ? 'inline' : 'none'
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
                    icon: 'ios-search-strong'
                  },
                  style: {
                    margin: '5px'
                  },
                  on: {
                    click: () => {
                      vm.doShowMsg(params.row.id)
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
                    icon: 'person'
                  },
                  style: {
                    margin: '5px'
                  },
                  on: {
                    click: () => {
                      vm.doShowRecord(params.row.identitynumber)
                    }
                  }
                }),
                h('div', {
                  slot: 'content'
                }, [
                  h('div', [
                    h('div', '个人健康档案')
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
                    icon: 'checkmark'
                  },
                  style: {
                    margin: '5px',
                    display: shenpi
                  },
                  on: {
                    click: () => {
                      this.$router.push({name: 'contact/exam', params: {id: params.row.id}})
                    }
                  }
                }),
                h('div', {
                  slot: 'content'
                }, [
                  h('div', [
                    h('div', '审批')
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
                    icon: 'compose'
                  },
                  style: {
                    margin: '5px',
                    display: xiugai
                  },
                  on: {
                    click: () => {
                      this.$router.push({name: 'contact/edit', params: {id: params.row.id}})
                    }
                  }
                }),
                h('div', {
                  slot: 'content'
                }, [
                  h('div', [
                    h('div', '修改')
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
                    icon: 'ios-unlocked'
                  },
                  style: {
                    margin: '5px',
                    display: jiechu
                  },
                  on: {
                    click: () => {
                      this.$router.push({name: 'contact/over', params: {id: params.row.id}})
                    }
                  }
                }),
                h('div', {
                  slot: 'content'
                }, [
                  h('div', [
                    h('div', '解除')
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
                    icon: 'trash-a'
                  },
                  style: {
                    margin: '5px',
                    display: shanchu
                  },
                  on: {
                    click: () => {
                      this.remove(params.row.id)
                    }
                  }
                }),
                h('div', {
                  slot: 'content'
                }, [
                  h('div', [
                    h('div', '删除')
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
                    icon: 'earth'
                  },
                  style: {
                    margin: '5px',
                    display: fuwujiahua
                  },
                  on: {
                    click: () => {
                      this.$router.push({name: 'appointment/plan', params: {id: params.row.id}})
                    }
                  }
                }),
                h('div', {
                  slot: 'content'
                }, [
                  h('div', [
                    h('div', '服务计划')
                  ])
                ])
              ])
            ])
          }
        },
        {
          title: '签约单号',
          align: 'center',
          key: 'contactnumber'
        },
        {
          title: '申请日期',
          key: 'createdate',
          sortable: true,
          align: 'center'
        },
        {
          title: '已服务次数',
          align: 'center',
          key: 'servicecount',
          render: (h, params) => {
            let test = params.row.servicecount === '' || params.row.servicecount === undefined || params.row.servicecount === null ? '' : params.row.servicecount
            return h('span', test)
          }
        },
        {
          title: '状态',
          align: 'center',
          sortable: true,
          key: 'applystatus',
          render: (h, params) => {
            let test = params.row.applystatus === 0 ? '待审批' : params.row.applystatus === 1 ? '已生效' : params.row.applystatus === 2 ? '到期解除' : params.row.applystatus === 3 ? '提前解除' : ''
            return h('span', test)
          }
        },
        {
          title: '签约居民',
          align: 'center',
          key: 'applyname'
        },
        {
          title: '身份证号',
          align: 'center',
          key: 'identitynumber'
        },
        {
          title: '签约类型',
          align: 'center',
          key: 'signtype',
          render: (h, params) => {
            let test = params.row.signtype === 0 ? '个人' : '家庭'
            return h('span', test)
          }
        },
        {
          title: '拟申请机构',
          align: 'center',
          key: 'institutionname'
        },
        {
          title: '拟申请医生团队',
          align: 'center',
          key: 'teamname'
        }
      ]
    }
  },
  watch: {
    searchInfo: function () {
      if (this.searchInfo[0].status === 0) {
        console.info('===' + JSON.stringify(this.searchInfo[0]))
        this.searchInfos.beginDate = this.searchInfo[0].beginDate
        this.searchInfos.endDate = this.searchInfo[0].endDate
        this.searchInfos.recently = this.searchInfo[0].recently
        this.searchInfos.keyWords = this.searchInfo[0].keyWords
        this.searchInfos.sort = this.searchInfo[0].sort
        this.searchInfos.applySignType = this.searchInfo[0].applySignType
        let para = {
          page: 1,
          rows: this.rows,
          order: 'create_date',
          // 排序
          sort: this.searchInfos.sort,
          searchParams: this.searchInfos
        }
        this.$Loading.start()
        getContactPage(para).then((res) => {
          this.$Loading.finish()
          this.total = res.data.total
          this.page = res.data.pageNum
          this.listData = res.data.rows
        })
      }
    }
  },
  created: function () {
    let para = {
      page: this.page,
      rows: this.rows,
      searchParams: this.searchInfos
    }
    this.$Loading.start()
    getContactPage(para).then((res) => {
      this.$Loading.finish()
      // console.log(res.data)
      this.total = res.data.total
      this.page = res.data.pageNum
      this.listData = res.data.rows
    })
  },
  methods: {
    select (selection) {
      this.selections = selection
      // console.log(selection)
    },
   // 查看单个信息
    doShowMsg (id) {
      this.$router.push({name: 'contact/detail', params: {id: id}})
    },
    // 个人健康档案
    doShowRecord (id) {
      this.$router.push({name: 'health/record', params: {id: id}})
    },
    remove (id) {
      var ths = this
      this.$Modal.confirm({
        title: '用户信息',
        content: `是否删除此记录`,
        onOk: function () {
          ths.$Loading.start()
          let para = {
            id: id,
            deletesign: 1
          }
          updateContactByKey(para).then((res) => {
            if (res.status === 200) {
              ths.$Loading.start()
              ths.$Message.info('删除成功！')
              // 成功之后重新差一次
              ths.change(ths.page)
            }
          })
        }
      })
    },
    // 点击分页页码
    change (page) {
      let para = {
        page: page,
        rows: this.rows,
        order: 'create_date',
        // 排序
        sort: this.searchInfos.sort,
        searchParams: this.searchInfos
      }
      this.$Loading.start()
      getContactPage(para).then((res) => {
        if (res.status === 200) {
          this.$Loading.finish()
          this.total = res.data.total
          this.listData = res.data.rows
        }
      })
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
    // 改变当前显示的行数
    doPageSizeChange: function (size) {
      this.rows = size
      let para = {
        page: 1,
        rows: this.rows,
        order: 'create_date',
        // 排序
        sort: this.searchInfos.sort,
        searchParams: this.searchInfos
      }
      this.$Loading.start()
      getContactPage(para).then((res) => {
        this.$Loading.finish()
        this.total = res.data.total
        this.page = res.data.pageNum
        this.listData = res.data.rows
      })
    }
  }
}
</script>
<style   scoped lang="less" >
    .lable-title {
        text-align: center;
    }
    .pages-block {
        margin: 0px;
        overflow: hidden;
        margin-top: 10px;
        margin-left: 1px;
        margin-bottom: 10px;
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
    /* .ivu-select-selected-value {
      width: 80px;
    } */
    .success-row td{
      background-color: white;
      color: red;
    }
    .invalid-row td{
    background-color: white;
    color: silver;
    }

</style>

