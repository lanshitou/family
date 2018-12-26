<template>
    <div class="index">
        <div style="background:#eee;padding: 1px;text-align:right">
            <Button-group>
                <Button type="primary" @click.native="add">添加</Button>
            </Button-group>
        </div>
            <Table :context="self" :data="listData" @on-selection-change="select" :columns="tableColumns" stripe border :row-class-name="rowClassName"></Table>
            <div class="pages-block">
                <div class="pages-right">
                    <Page :total="total" :page-size="pageSize" @on-page-size-change="doPageSizeChange" @on-change="change" show-sizer show-total :current="page" show-elevator></Page>
                </div>
            </div>
    <!--信息编辑摸态框-->
    <!--<Modal v-model="updateModel" title="信息编辑" class="update-model">
        <Row justify="center" class="row">
            <Col span="6" class="lable-title">姓名</Col>
            <Col span="14">
            <input v-model="updateData.name" placeholder="请输入姓名">
            </Input>
            </Col>
        </Row>
        <Row justify="center" class="row">
            <Col span="6" class="lable-title">年龄</Col>
            <Col span="14">
            <input v-model="updateData.age" placeholder="请输入年龄">
            </Input>
            </Col>
        </Row>
        <Row justify="center" class="row">
            <Col span="6" class="lable-title">地址</Col>
            <Col span="14">
            <input v-model="updateData.address" placeholder="请输入地址">
            </Input>
            </Col>
        </Row>
    </Modal>-->
  </div>
  
</template>

<script>
const initPage = {
  page: 1,
  pageSize: 1
}
import {
    getUserListPage, removeContact
} from '../../api/api'
var appData = require('./table_list1.json')
export default {
  props: [
    'searchInfo'
  ],
  data () {
    return {
      self: this,
      selections: [],
      total: 0,
      page: 1,
      pageSize: 1,
      listData: appData,
      updateModel: false,
      updateData: {
        applyName: '',
        indentCard: 0,
        applySignType: ''
      },
      updateIndex: 0,
      tableColumns: [
        {
          type: 'selection',
          width: 60,
          key: 'id',
          align: 'center'
        },
        {
          title: '签约居民',
          align: 'center',
          key: 'applyName'
        },
        {
          title: '身份证号',
          align: 'center',
          key: 'indentCard'
        },
        {
          title: '签约类型',
          align: 'center',
          key: 'applySignType'
        },
        {
          title: '申请日期',
          key: 'applyDate',
          align: 'center'

        },
        {
          title: '拟申请机构',
          key: 'applyInstitutions',
          ellipsis: true,
          align: 'center'
        },
        {
          title: '拟申请团队',
          key: 'applyDoctorTeam',
          align: 'center'
        },
        {
          title: '状态',
          key: 'status',
          align: 'center',
          render (row, column, index) {
            let test = `${row.status === '1' ? '已生效' : row.status === '0' ? '待审批' : '已结束'}`
            return test
          }
        },
        {
          title: '操作',
          width: 200,
          key: 'action',
          align: 'center',
          render (row, column, index) {
            let l = `<i-button size="small" @click="doShowMsg(${row.id})" type="primary">详情</i-button>
                    <Dropdown  placement="left-start"  style="margin-left: 10px">
                    <a href="javascript:void(0)">
                    <Icon type="arrow-down-b"></Icon></a>
                    <Dropdown-menu slot="list">`
            // 带审批的按钮
            let temp = row.status === '0' ? `
            <Dropdown-item><i-button @click="edit(${row.id})" :long="true" size="small" type="primary">修改</i-button></Dropdown-item>
            <Dropdown-item><i-button @click="doMsg(${row.status})" :long="true" size="small" type="primary">审批</i-button></Dropdown-item>
            <Dropdown-item><i-button @click="del(${row.id})" :long="true" size="small" type="primary">删除</i-button></Dropdown-item>
            ` : ``
            // 生效的按钮
            let sess = row.status === '1' ? `
            <Dropdown-item><i-button @click="relieve(${row.id})" :long="true" size="small" type="primary">解除</i-button></Dropdown-item>
            ` : ``
            let end = `<Dropdown-item><i-button @click="doShowRecord(${row.id})" :long="true" size="small" type="primary">个人健康档案</i-button></Dropdown-item>
            </Dropdown-menu></Dropdown>`
            return l + temp + sess + end
          }
        }
      ]
    }
  },
  watch: {
    searchInfo: function () {
      console.info('===' + JSON.stringify(this.searchInfo[0]))
      let para = {
        page: 1,
        pageSize: 1,
        queryParams: this.searchInfo[0]
      }
      this.$Loading.start()
      getUserListPage(para).then((res) => {
        this.$Loading.finish()
        this.total = res.data.total
        this.page = res.data.pageNum
        // this.listData = res.data.list
      })
    }
  },
  created: function () {
    let para = {
      page: 1,
      pageSize: 1
    }
    this.$Loading.start()
    getUserListPage(para).then((res) => {
      this.$Loading.finish()
      this.total = res.data.total
      this.page = res.data.pageNum
      this.listData = res.data.list
    })
  },
  methods: {
    rowClassName (row, index) {
      if (row.status === '0') {
        return 'success-row'
      } else if (row.status === '2') {
        return 'invalid-row'
      }
      return ''
    },
    instance (type, title, content) {
      switch (type) {
        case 'info':
          this.$Modal.info({
            title: title,
            content: content
          })
          break
        case 'success':
          this.$Modal.success({
            title: title,
            content: content
          })
          break
        case 'warning':
          this.$Modal.warning({
            title: title,
            content: content
          })
          break
        case 'error':
          this.$Modal.error({
            title: title,
            content: content
          })
          break
      }
    },
    search () {
      getUserListPage(initPage).then((res) => {
        this.$Loading.finish()
        this.total = res.data.total
        this.page = res.data.pageNum
        this.listData = res.data.list
      })
    },
    // 解除
    relieve (id) {
      this.$router.push({path: 'over', params: {id: id}})
    },
    add () {
      this.$router.push({ path: 'add' })
    },
    edit (id) {
      this.$router.push({path: 'edit', params: {id: id}})
    },
    del () {
      let self = this
      this.$Modal.confirm({
        title: '签约协议信息',
        content: `是否删除选中记录`,
        onOk: function () {
          this.$Loading.start()
          var ids = []
          self.selections.forEach(function (element) {
            ids.push(element.id)
          }, this)
          removeContact(ids.join(',')).then((res) => {
            if (res.status === 200) {
              let queryParas = {
                page: this.page,
                pageSize: this.pageSize
              }
              getUserListPage(queryParas).then((response) => {
                this.$Loading.finish()
                this.total = response.data.total
                this.page = response.data.pageNum
                this.listData = response.data.list
              })
            } else {
              this.$Message.error('删除失败！')
            }
          })
        }
      })
    },
    select (selection) {
      this.selections = selection
    },
   // 查看单个信息
    doShowMsg (id) {
      this.$router.push({path: 'personal_protocol', params: {id: id}})
    },
    // 审批
    doMsg (id) {
      this.$router.push({path: 'exam', params: {id: id}})
    },
    // 个人健康档案
    doShowRecord (id) {
      this.$router.push({path: 'record', params: {id: id}})
    },
    mockTableData () {
      let para = {
        page: 1,
        pageSize: 1
      }
      this.$Loading.start()
      getUserListPage(para).then((res) => {
        this.$Loading.finish()
        this.total = res.data.total
        this.page = res.data.pageNum
        this.listData = res.data.list
      })
    },
    show (index) {
      this.$Modal.info({
        title: '用户信息',
        content: `姓名：${this.listData[index].name}<br>年龄：${this.listData[index].age}<br>地址：${this.listData[index].addr}`
      })
    },
    remove (index) {
      // let self = this
      this.$Modal.confirm({
        title: '用户信息',
        content: `是否删除此记录`,
        onOk: function () {
          this.$Loading.start()
          // let para = { id: index }
          // 少了removeUser方法
        }
      })
    },
    change (row) {
      // 这里直接更改了模拟的数据，真实使用场景应该从服务端获取数据
      console.log(row)
      let para = {
        page: row,
        pageSize: this.pageSize
      }
      this.$Loading.start()
      getUserListPage(para).then((res) => {
        this.$Loading.finish()
        this.total = res.data.total
        this.page = res.data.pageNum
        this.listData = res.data.list
      })
    },
    // 改变当前显示的行数
    doPageSizeChange: function (size) {
      this.pageSize = size
      let para = {
        page: this.page,
        pageSize: size
      }
      this.$Loading.start()
      getUserListPage(para).then((res) => {
        this.$Loading.finish()
        this.total = res.data.total
        this.page = res.data.pageNum
        this.listData = res.data.list
      })
    }
  },
  mounted () {
    this.mockTableData()
  }
}
</script>

<style  scoped lang="less">
  .index {
    .lable-title {
        text-align: center;
    }
    .pages-block {
        margin: 0px;
        overflow: hidden;
        margin-top: 10px;
        margin-bottom: 10px;
        padding-left: 40%;
        .pages-right {
            vertical-align: middle;
        }
    }
    .update-model .row {
        display: flex;
        align-items: center;
        margin-bottom: 10px;
    }
    .success-row td{
      background-color: lightgray;
      color: red;
    }
    .invalid-row td{
    background-color: white;
    color: silver;
    }
}
</style>

