/**
 * @title: 系统消息信息
 * @description: 系统消息
 * @author: TiuWeb
*/
<template>
  <div>
    <div class="layout-content" style="height:95%; overflow:auto;">
      <div class="layout-content-main">
        <div class='columnBox menu'>
          <div class='filterTitle'>类&#8195;&#8195;别</div>
          <Select v-model="queyParams.searchParams.category" style="width:100px;" placeholder="全部" @on-change="changeM" :label-in-value="true">
                <Option v-for="item in categorys" :value="item.id">{{ item.category }}</Option>
          </Select>
          <div class='filterTitle'>
            <Input v-model="queyParams.searchParams.keyWords" icon="ios-search" placeholder="请输入关键字搜索" style="width: 200px"></Input>
          </div>
          <i-button type="primary"  @click.native="query">查询</i-button>
        </div>
          <Table  :data="tableDatas" :columns="tableColumns"  border :row-class-name="rowClassName"/>
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
  findremindpage, findremindAll
} from '../../../api/api'
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
          category: ''
        }
      },
      categorys: [],
      tableColumns: [
        {
          title: '类别',
          align: 'center',
          width: '100',
          key: 'category',
          render: (h, params) => {
            var date = this.changetype(params.row.category)
            return h('div', [
              h('span', date)
            ])
          }
        },
        {
          title: '消息内容',
          align: 'center',
          key: 'content'
        },
        {
          title: '时间',
          align: 'center',
          width: '200',
          key: 'createdate'
        },
        {
          title: '操作',
          key: 'action',
          align: 'center',
          width: '80',
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
                  style: {
                    marginLeft: '5px'
                  },
                  on: {
                    click: () => {
                      this.check(params.index)
                    }
                  }
                }),
                h('div', {
                  slot: 'content'
                }, [
                  h('div', [
                    h('div', '查看')
                  ])
                ])
              ])
            ])
          }
        }
      ],
      tableDatas: []
    }
  },
  created: function () {
    this.getTableDatas(this.queyParams)
    findremindAll().then((response) => {
      var option = {
        category: '全部',
        id: ''
      }
      this.categorys.push(option)
      for (var i = 0; i < response.data.length; i++) {
        this.categorys.push(response.data[i])
      }
    })
  },
  methods: {
    // 行样式  未读消息0
    rowClassName (row, index) {
      if (this.tableDatas[index].status === false) {
        return 'tableRow'
      }
    },
    // 查看消息详情
    check (index) {
      var category = this.tableDatas[index].category
      var id = this.tableDatas[index].msgidentity
      if (category === 1) {
        this.$router.push({name: 'inquiry/inquiry_detail', params: {id: id}})
      }
      if (category === 2) {
        this.$router.push({name: 'inquiry/inquiry_detail', params: {id: id}})
      }
      if (category === 3) {
        this.$router.push({name: 'suggestion/suggestion_reply', params: {id: id}})
      }
      if (category === 4) {
        this.$router.push({name: 'notice/notice_detail', params: {id: id}})
      }
      if (category === 5) {
        this.$router.push({name: 'contact/detail', params: {id: id}})
      }
      if (category === 6) {
        this.$router.push({name: 'message/plan_change', params: {id: id}})
      }
      if (category === 7) {
        this.$router.push({name: 'evaluate', params: {id: id}})
      }
    },
    // 转换类别
    changetype (category) {
      var type = ''
      if (category === 1) {
        type = '居民问诊'
      }
      if (category === 2) {
        type = '问诊评论'
      }
      if (category === 3) {
        type = '投诉建议'
      }
      if (category === 4) {
        type = '通知公告'
      }
      if (category === 5) {
        type = '签约申请'
      }
      if (category === 6) {
        type = '计划更改'
      }
      if (category === 7) {
        type = '服务执行'
      }
      return type
    },
    // select选择器选中的Option变化时触发
    changeM (change) {
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
      findremindpage(params).then((res) => {
        vm.$Loading.finish()
        vm.total = res.data.total
        vm.tableDatas = res.data.rows
        // console.log(res.data)
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
.ivu-table .tableRow td{
    color: red;
}
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
.index {
  padding: 2px;
  margin-top: 5px;
  margin-bottom: 10PX;
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
