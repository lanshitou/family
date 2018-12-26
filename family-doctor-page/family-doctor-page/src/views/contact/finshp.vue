<template>
  <div class="index">
    <div style="background:#f3f3f3;padding:2px;text-align:right" >
      <Button-group>
      </Button-group>
    </div>
    <Table :context="self" :data="listData" @on-selection-change="select" :columns="tableColumns"  border
      ></Table>
    <div class="pages-block">
      <div class="pages-right">
        <Page :total="total" :page-size="rows" @on-page-size-change="doPageSizeChange" @on-change="change"  show-total show-sizer
          :current="page" show-elevator></Page>
      </div>
    </div>
  </div>

</template>

<script>
import {
    getContactPage
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
        applystatus: '2,3',
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
      rows: 10,
      pageSize: 1,
      listData: [],
      updateModel: false,
      updateData: {
        applyName: '',
        indentCard: 0,
        applySignType: '',
        applyNo: '',
        applySignDate: '',
        count: ''
      },
      updateIndex: 0,
      tableColumns: [
        {
          title: '签约单号',
          align: 'center',
          key: 'contactnumber'
        },
        {
          title: '身份证号',
          align: 'center',
          key: 'identitynumber'
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
        },
        {
          title: '服务次数',
          align: 'center',
          key: 'servicecount',
          render: (h, params) => {
            let test = params.row.servicecount === '' || params.row.servicecount === undefined || params.row.servicecount === null ? '' : params.row.servicecount
            return h('span', test)
          }
        },
        {
          title: '结束日期',
          align: 'center',
          sortable: true,
          key: 'updatedate'
        },
        {
          title: '签约居民',
          align: 'center',
          key: 'applyname'
        },
        {
          title: '签约类型',
          align: 'center',
          key: 'signtype',
          render: (h, params) => {
            let test = params.row.signtype === 1 ? '个人' : '家庭'
            return h('span', test)
          }
        },
        {
          title: '状态',
          align: 'center',
          key: 'applystatus',
          render: (h, params) => {
            let test = params.row.applystatus === 0 ? '待审批' : params.row.applystatus === 1 ? '已生效' : params.row.applystatus === 2 ? '到期解除' : params.row.applystatus === 3 ? '提前解除' : ''
            return h('span', test)
          }
        },
        {
          title: '操作',
          key: 'action',
          width: 150,
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
                    icon: 'earth'
                  },
                  style: {
                    margin: '5px'
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
        }
      ]
    }
  },
  watch: {
    searchInfo: function () {
      if (this.searchInfo[0].status === 3) {
        // console.info('===' + JSON.stringify(this.searchInfo[0]))
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
  },
  methods: {
    select (selection) {
      this.selections = selection
    },
   // 查看单个信息
    doShowMsg (id) {
      this.$router.push({name: 'contact/detail', params: {id: id}})
    },
    // 个人健康档案
    doShowRecord (id) {
      this.$router.push({path: 'health_record', params: {id: id}})
    },
    change (row) {
      // 这里直接更改了模拟的数据，真实使用场景应该从服务端获取数据
      // console.log(row)
      let para = {
        page: row,
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
    }
    .pages-right {
            vertical-align: middle;
        }
    .update-model .row {
        display: flex;
        align-items: center;
        margin-bottom: 10px;
    }
    .success-row td{
      background-color: white;
      color: red;
    }
    .invalid-row td{
    background-color: white;
    color: silver;
    }
</style>

