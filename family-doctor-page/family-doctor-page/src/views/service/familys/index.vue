/**
 * @title: 居民家庭分页展示
 * @description: 居民家庭分页展示
 * @author: TiuWeb
*/
<template>
  <div>
    <div class="layout-content" style="height:90%; overflow:auto;">
      <div class="layout-content-main">
        <div class='columnBox menu'>
          <div class='filterTitle'>
            <Input v-model="queyParams.searchParams.keyWords" icon="ios-search" placeholder="请输入关键字搜索" style="width: 200px"></Input>
            <i-button type="primary" icon="ios-search" style="margin-left:10px" @click="query">查询</i-button>
          </div>
        </div>
        <div>
          <Table :data="listData" @on-selection-change="select" :columns="tableColumns" border>
          </Table>
          <div class="pages-block">
            <div class="pages-right">
              <Page ref="tablePage" :total="total" :page-size="queyParams.rows" @on-page-size-change="doPageSizeChange" @on-change="change" show-sizer show-total show-elevator></Page>
            </div>
          </div>
        </div>
        <!--查看弹框-->
        <Modal v-model="lookModel" title="居民家庭信息" width="700" class="update-model">
          <Table :data="seeListData" :columns="seeTableColumns" border>
          </Table>
          <div slot="footer">
            <Button type="primary" @click="close">关闭</Button>
          </div>
        </Modal>
      </div>
    </div>
    <v-button :params="params">新增</v-button>
  </div>
</template>
<script>
import button from '../../../components/add_btn'
import {
  getFamilysPage, findFamilyByKey
} from '../../../api/api'
export default {
  components: {
    'v-button': button
  },
  data () {
    return {
      params: 'familys_add',
      total: 0,
      queyParams: {
        sort: '',
        order: '',
        page: 0,
        rows: 10,
        searchParams: {
          keyWords: ''
        }
      },
      addAndUpdateModel: false,
      lookModel: false, // 查看
      formDynamic: {
        items: [
          {
            identitynumber: '', // 家庭成员的身份证编号
            healthidentity: '', // 居民健康档案外键
            membername: '', // 成员姓名
            membership: 0, // 家庭成员身份
            sex: '',
            age: '',
            address: '',
            archivesnumber: '', // 居民健康档案编号
            ismaster: 0 // 是否是户主
          }
        ]
      },
      listData: [], // 所有家庭列表
      seeListData: [], // 查看家庭详情列表
      tableColumns: [
        {
          title: '家庭编号',
          align: 'center',
          key: 'familynumber'
        },
        {
          title: '户主姓名',
          align: 'center',
          key: 'membername'
        },
        {
          title: '户主身份证号',
          align: 'center',
          width: '170',
          key: 'identitynumber'
        },
        {
          title: '地址',
          align: 'center',
          key: 'address'
        },
        {
          title: '家庭成员人数',
          align: 'center',
          key: 'membrcount'
        },
        {
          title: '签约人数',
          align: 'center',
          key: 'contactcount'
        },
        {
          title: '创建日期',
          align: 'center',
          width: '160',
          key: 'createdate'
        },
        {
          title: '操作',
          key: 'action',
          width: '90',
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
                      this.show(params.row.id)
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
                    marginLeft: '5px'
                  },
                  on: {
                    click: () => {
                      this.update(params.row.id)
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
              ])
            ])
          }
        }
      ],
      seeTableColumns: [
        {
          title: '姓名',
          align: 'center',
          key: 'membername'
        },
        {
          title: '户主身份证号',
          align: 'center',
          key: 'identitynumber'
        },
        {
          title: '性别',
          align: 'center',
          key: 'sex'
        },
        {
          title: '年龄',
          align: 'center',
          key: 'age'
        },
        {
          title: '成员身份',
          align: 'center',
          key: 'membership'
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
      getFamilysPage(params).then((res) => {
        vm.$Loading.finish()
        this.$refs.tablePage.currentPage = 1
        vm.total = res.data.total
        vm.listData = res.data.rows
      })
    },
    handleAdd () {
      this.familyModel.items.push({
        code: '',
        name: '',
        sex: '',
        age: '',
        relation: ''
      })
    },
    // 修改
    update (id) {
      this.$router.push({ name: 'familys/edit', params: { id: id } })
    },
    // 查看详情
    show (id) {
      this.lookModel = true
      // 根据家庭编号查询成员
      this.$Loading.start()
      let para = {
        id: id
      }
      findFamilyByKey(para).then((res) => {
        this.$Loading.finish()
        this.seeListData = res.data.members
        res.data.members.forEach(function (element) {
          if (element.membership === 0) {
            element.membership = '户主'
          }
          if (element.membership === 1) {
            element.membership = '本人'
          }
          if (element.membership === 2) {
            element.membership = '父亲'
          }
          if (element.membership === 3) {
            element.membership = '母亲'
          }
          if (element.membership === 4) {
            element.membership = '爷爷'
          }
          if (element.membership === 5) {
            element.membership = '奶奶'
          }
          if (element.membership === 6) {
            element.membership = '妻子'
          }
          if (element.membership === 7) {
            element.membership = '丈夫'
          }
          if (element.membership === 8) {
            element.membership = '儿子'
          }
          if (element.membership === 9) {
            element.membership = '女儿'
          }
          if (element.sex === 1) {
            element.sex = '男'
          }
          if (element.sex === 2) {
            element.sex = '女'
          }
        }, this)
      })
    },
    // 关闭查看对话框
    close () {
      this.lookModel = false
    },
    handleRemove (index, dataIndex) {
      this.data[dataIndex].familyItems.splice(index, 1)
    },
    handleSelect (index) {
      // 查询ID 回显家庭信息
      alert(index)
      if (index === '') {
        this.familyModel = ''
      } else {
        this.$Modal.confirm({
          title: '',
          content: '<p>此人尚未办理居民健康档案，无法调取相关个人基本信息。</p><p>是否现在为其新增居民健康档案？</p>',
          onOk: () => {
            this.$Message.info('点击了确定')
            this.$router.push({ path: 'personal_record' })
          },
          onCancel: () => {
            this.$Message.info('点击了取消')
          }
        })
      }
    },
    change (page) {
      this.queyParams.page = page
      this.getTableDatas(this.queyParams)
    },
    // 改变当前显示的行数
    doPageSizeChange: function (size) {
      this.queyParams.rows = size
      this.getTableDatas(this.queyParams)
    },
    select (selection) {
      this.selections = selection
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
  margin-bottom: 10px;
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
.layout-content-main {
  padding: 5px;
}

.layout-content {
  min-height: 78%;
  overflow: hidden;
  background: #fff;
  border-radius: 4px;
}

.layout-copy {
  text-align: center;
  padding: 10px 0 20px;
  color: #9ea7b4;
}
</style>
