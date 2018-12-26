/**
 * @Title : 签约居民首页
 * @Description : 
 * @author : TiuWeb
 */
<template>
  <div class="" >
    <div class="layout-content"  style="height:90%; overflow:auto;">
      <div class="layout-content-main">
        <div class='columnBox menu'>
          <div class='filterTitle'>
          <Input v-model="queyParams.searchParams.keyWords" icon="ios-search" placeholder="请输入关键字搜索" style="width: 200px"></Input>
          </div>
          <i-button  type="primary"  @click.native="query">查询</i-button>
        </div>
        <Table :data="listData"  :columns="tableColumns"  border>
        </Table>
        <div class="pages-block">
          <div class="pages-right">
              <Page ref="tablePage" :total="total" :page-size="queyParams.rows" @on-page-size-change="doPageSizeChange" @on-change="change" show-sizer show-total show-elevator></Page>
          </div>
        </div>
      </div>
      </div>
  </div>
</template>
<script>
import {
  findContactResidentPage
} from '../../../api/api'
export default {
  data () {
    return {
      total: 0,
      queyParams: {
        sort: 'desc',
        order: 'enable_date',
        page: 0,
        rows: 10,
        searchParams: {
          keyWords: ''
        }
      },
      update: '',
      modal1: false,
      listData: [],
      tableColumns: [
        {
          title: '签约生效日期',
          align: 'center',
          key: 'enabledate',
          sortable: true
        },
        {
          title: '居民姓名',
          align: 'center',
          key: 'applyname'
        },
        {
          title: '身份证号',
          align: 'center',
          key: 'identitynumber'
        },
        {
          title: '联系电话',
          align: 'center',
          key: 'telephone'
        },
        {
          title: '签约单号',
          align: 'center',
          key: 'contactnumber'
        },
        {
          title: '签约类型',
          align: 'center',
          key: 'signtype',
          render: (h, params) => {
            var type = params.row.signtype === 0 ? '个人' : '家庭'
            return h('div', type)
          }
        },
        {
          title: '签约医生',
          align: 'center',
          key: 'teamdoctorname'
        }
      ]
    }
  },
  created: function () {
    this.getTableDatas(this.queyParams)
  },
  methods: {
     // 查询数据
    query () {
      this.getTableDatas(this.queyParams)
    },
    // 分页查询公用方法
    getTableDatas: function (params) {
      let vm = this
      vm.$Loading.start()
      findContactResidentPage(params).then((res) => {
        this.$Loading.finish()
        this.$refs.tablePage.currentPage = 1
        this.total = res.data.total
        this.listData = res.data.rows
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
