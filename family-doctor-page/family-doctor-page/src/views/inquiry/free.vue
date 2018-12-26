/**
 * @Title : 问诊记录指定问诊
 * @Description : 
 * @author : TiuWeb
 */
<template>
  <div class="">
    <Table :data="tableDatas" :columns="tableColumns"  border>
    </Table>
    <div class="pages-block">
      <div class="pages-right">
        <Page :total="total" :page-size="queyParams.rows" @on-page-size-change="doPageSizeChange" @on-change="change" show-sizer
          show-total ref="tablePage" show-elevator></Page>
      </div>
    </div>
  </div>
</template>

<script>
import {
  findInquirytPage
} from '../../api/api'
export default {
  props: [
    'searchInfo'
  ],
  data () {
    return {
      total: 0,
      queyParams: {
        sort: 'desc',
        order: 'create_date',
        page: 0,
        rows: 10,
        searchParams: {
          keyWords: '',
          isappoint: false,
          inquirystatus: ''
        }
      },
      // 详情list
      detailList: [],
      tableDatas: [],
      tableColumns: [
        {
          title: '问诊时间',
          key: 'createdate',
          align: 'center',
          sortable: true
        },
        {
          title: '用户名',
          align: 'center',
          key: 'loginname'
        },
        {
          title: '签约/非签约居民',
          align: 'center',
          key: 'iscontact',
          render: (h, params) => {
            var date = this.changeApply(params.row.iscontact) // 获取到当前列的状态
            return h('div', [
              h('span', date)
            ])
          }
        },
        {
          title: '病情类别',
          align: 'center',
          key: 'patientscategoryname'
        },
        {
          title: '摘要',
          align: 'center',
          key: 'content',
          render: (h, params) => {
            return h('Poptip', {
              props: {
                trigger: 'hover',
                title: '内容摘要',
                placement: 'left-start'
              }
            }, [
              h('Tag', params.row.content.substring(0, 10)),
              h('div', {
                slot: 'content'
              }, [
                h('ul', this.detailList[params.index].split.map(item => {
                  return h('li', {
                    style: {
                      textAlign: 'start',
                      padding: '4px'
                    }
                  }, item)
                }))
              ])
            ])
          }
        },
        {
          title: '状态',
          align: 'center',
          key: 'inquirystatus',
          render: (h, params) => {
            var date = this.changeStatu(params.row.inquirystatus) // 获取到当前列的状态
            return h('div', [
              h('span', date)
            ])
          }
        },
        {
          title: '操作',
          key: 'action',
          width: 70,
          align: 'center',
          render: (h, params) => {
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
                  on: {
                    click: () => {
                      this.doShowMsg(params.row.id)
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
        // {
        //   title: '专业匹配度',
        //   align: 'center',
        //   key: 'matched'
        // }
      ]
    }
  },
  created: function () {
    this.getTableDatas(this.queyParams)
  },
  watch: {
    searchInfo: function () {
      this.$refs.tablePage.currentPage = 1
      this.queyParams.page = 0
      this.queyParams.row = 10
      // console.info('===' + JSON.stringify(this.searchInfo[0]))
      this.queyParams.searchParams.keyWords = this.searchInfo[0].keyWords
      this.queyParams.searchParams.inquirystatus = this.searchInfo[0].inquirystatus
      this.getTableDatas(this.queyParams)
    }
  },
  methods: {
    // 整理详情
    zhenglixiangqing () {
      this.detailList = []
      if (this.tableDatas !== null && this.tableDatas.length > 0) {
        for (var i = 0; i < this.tableDatas.length; i++) {
          var a = this.tableDatas[i].content
          var details = a.replace(/<.*?>/ig, '')
          this.detailList.push({
            split: [
              details.substring(0, 30),
              details.substring(30, 60),
              details.substring(60, 90),
              details.substring(90, 120),
              details.substring(120, 150),
              details.substring(150, 180),
              details.substring(180, 210),
              details.substring(210, 240),
              details.substring(240, 270),
              details.substring(270, 300)
            ]
          })
        }
      }
    },
    // 是否签约
    changeApply (iscontact) {
      var nsex = ''
      if (iscontact === false) {
        nsex = '否'
      } else if (iscontact === true) {
        nsex = '是'
      }
      return nsex
    },
    //  状态
    changeStatu (inquirystatus) {
      var nsex = ''
      if (inquirystatus === 0) {
        nsex = '未回复'
      } else if (inquirystatus === 1) {
        nsex = '回复中'
      } else if (inquirystatus === 2) {
        nsex = '已解决'
      } else if (inquirystatus === 3) {
        nsex = '已评价'
      }
      return nsex
    },
   // 查看单个信息
    doShowMsg (id) {
      this.$router.push({name: 'inquiry/inquiry_detail', params: {id: id}})
    },
    // 分页查询公用方法
    getTableDatas: function (params) {
      let vm = this
      vm.$Loading.start()
      findInquirytPage(params).then((res) => {
        vm.$Loading.finish()
        vm.total = res.data.total
        vm.tableDatas = res.data.rows
        vm.zhenglixiangqing()
      })
    },
    // 点击分页查询
    change (page) {
      this.queyParams.page = page
      this.getTableDatas(this.queyParams)
    },
    // 改变当前显示的行数
    doPageSizeChange: function (size) {
      this.queyParams.rows = size
      this.getTableDatas(this.queyParams)
    }
  }
}
</script>
<style scoped lang="less">
.filterTitle {
  font-size: 12px;
  color: #1c2438;
  padding: 10px 12px;
}
.pages-block {
  margin: 0px;
  overflow: hidden;
  margin-top: 10px;
  margin-bottom: 140px;
  text-align: center;
  .pages-right {
    vertical-align: middle;
  }
}
.layout {
  border: 1px solid #d7dde4;
  background: #f5f7f9;
  position: relative;
  border-radius: 4px;
  overflow: hidden;
}
.layout-breadcrumb {
  padding: 10px 15px 0;
}
.layout-content-main {
  padding: 10px;
}
.layout-content {
  min-height: 78%;
  margin-top: 15px;
  overflow: hidden;
  background: #fff;
  border-radius: 4px;
}
</style>

