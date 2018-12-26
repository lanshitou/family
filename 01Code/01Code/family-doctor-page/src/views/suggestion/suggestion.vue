/**
 * @Title : 投诉建议
 * @Description : 
 * @author : TiuWeb
 */
<template>
  <div>
    <div class="layout-content" style="height:95%; overflow:auto;">
      <div class="layout-content-main">
        <div class='columnBox menu'>
          <div class='filterTitle'>状&#8195;&#8195;态</div>
          <Select v-model="queyParams.searchParams.status" style="width:80px;" placeholder="全部" >
                  <Option v-for="item in statu" :value="item.value" :key="item.value">{{ item.label }}</Option>
            </Select>
          <div class='filterTitle'>类&#8195;&#8195;别</div>
          <Select v-model="queyParams.searchParams.category" style="width:80px;" placeholder="全部" >
                  <Option v-for="item in type" :value="item.value" :key="item.value">{{ item.label }}</Option>
            </Select>
          <div class='filterTitle'>
            <Input v-model="queyParams.searchParams.keyWords" icon="ios-search" placeholder="请输入关键字搜索" style="width: 200px"></Input>
          </div>
          <i-button type="primary" @click.native="query">查询</i-button>
        </div>
        <Table  :data="tableDatas" :columns="tableColumns"  border/>
          <div class="pages-block">
            <div class="pages-right">
               <Page :total="total" :page-size="queyParams.rows" @on-page-size-change="doPageSizeChange" @on-change="change" show-sizer show-total ref="tablePage" show-elevator></Page>
            </div>
          </div>
      </div>
    </div>
  </div>
</template>
<script>
import {
  findSuggestionPage
} from '../../api/api'
export default {
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
          status: '',
          category: ''
        }
      },
      statu: [
        {
          value: '',
          label: '全部'
        },
        {
          value: '0',
          label: '未解决'
        },
        {
          value: '1',
          label: '回复中'
        },
        {
          value: '2',
          label: '已解决'
        },
        {
          value: '3',
          label: '已评价'
        }
      ],
      type: [
        {
          value: '',
          label: '全部'
        },
        {
          value: '1',
          label: '投诉'
        },
        {
          value: '2',
          label: '建议'
        },
        {
          value: '3',
          label: '咨询'
        },
        {
          value: '4',
          label: '其他'
        }
      ],
      // 详情list
      detailList: [],
      tableDatas: [],
      tableColumns: [
        {
          title: '类别',
          align: 'center',
          sortable: true,
          key: 'category',
          render: (h, params) => {
            var date = this.changeType(params.row.category)
            return h('div', [
              h('span', date)
            ])
          }
        },
        {
          title: '提交时间',
          align: 'center',
          key: 'createdate',
          sortable: true
        },
        {
          title: '用户名',
          align: 'center',
          key: 'createuser'
        },
        {
          title: '接受机构',
          align: 'center',
          key: 'orgname'
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
          key: 'status',
          render: (h, params) => {
            var date = this.changeStatu(params.row.status)
            return h('div', [
              h('span', date)
            ])
          }
        },
        {
          title: '操作',
          key: 'action',
          width: '90',
          align: 'center',
          render: (h, params) => {
            let showReply = params.row.status === 0 | params.row.status === 1 ? 'inline' : 'none'
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
                      this.$router.push({name: 'suggestion/suggestion_detail', params: {id: params.row.id}})
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
                    icon: 'compose'
                  },
                  style: {
                    marginLeft: '5px',
                    display: showReply
                  },
                  on: {
                    click: () => {
                      this.$router.push({name: 'suggestion/suggestion_reply', params: {id: params.row.id}})
                    }
                  }
                }),
                h('div', {
                  slot: 'content'
                }, [
                  h('div', [
                    h('div', '回复')
                  ])
                ])
              ])
            ])
          }
        }
      ]
    }
  },
  created: function () {
    this.getTableDatas(this.queyParams)
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
    // 新增
    add () {
      this.$router.push({path: '/suggestion_add'})
    },
    // 查询按钮
    query () {
      this.$refs.tablePage.currentPage = 1
      this.queyParams.page = 0
      this.queyParams.row = 10
      this.getTableDatas(this.queyParams)
    },
  // 分页查询公用方法
    getTableDatas: function (params) {
      let vm = this
      vm.$Loading.start()
      findSuggestionPage(params).then((res) => {
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
    },
    // 类别转换
    changeType (category) {
      var type = ''
      if (category === 1) {
        type = '投诉'
      }
      if (category === 2) {
        type = '建议'
      }
      if (category === 3) {
        type = '咨询'
      }
      if (category === 4) {
        type = '其他'
      }
      return type
    },
    // 转换状态
    changeStatu (publishstatus) {
      var status = ''
      if (publishstatus === 0) {
        status = '未解决'
      }
      if (publishstatus === 1) {
        status = '回复中'
      }
      if (publishstatus === 2) {
        status = '已解决'
      }
      if (publishstatus === 3) {
        status = '已评价'
      }
      return status
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
.menu {
  flex-direction: row;
  align-items: center;
}
.columnBox {
  display: -webkit-flex;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
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
  padding: 5px;
}
.layout-content {
  min-height: 78%;
  margin-top: 0px;
  overflow: hidden;
  background: #fff;
  border-radius: 4px;
}
</style>
