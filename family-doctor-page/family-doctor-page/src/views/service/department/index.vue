/**
 * @Title : 科室维护首页
 * @Description : 
 * @author : TiuWeb
 */
<template>
  <div>
    <div class="layout-content"  style="height:95%; overflow:auto;">
      <div class="layout-content-main">
        <div class='columnBox menu'>
          <div class='filterTitle'>
          <Input v-model="queyParams.searchParams.keyWords" icon="ios-search" placeholder="请输入关键字搜索" style="width: 200px"></Input>
          </div>
          <i-button  type="primary" @click.native="query">查询</i-button>
        </div>
           <v-button :params="params">新增</v-button>
          <Table  :data="tableDatas" :columns="tableColumns"  border>
          </Table>
          <div class="pages-block">
            <div class="pages-right">
               <Page :total="total" :page-size="queyParams.rows" @on-page-size-change="doPageSizeChange" @on-change="change" show-sizer show-total ref="tablePage" show-elevator></Page>
            </div>
          </div>
      </div>
      <div class="layout-content-main">
          
      </div>
      <!--信息查看弹出框-->
        <Modal v-model="detailsModel" title="科室信息查看" class="update-model" ok-text="关闭" cancel-text="">
          <Form :model="showData" :label-width="100">
            <Card>
              <Row>
                <Col span="24">
                  <Form-item label="科室名称:" style="margin:0px">
                    {{showData.officesname}}
                  </Form-item>
                </Col>
                <Col span="24">
                  <Form-item label="关联病情关键字:" style="margin:0px">
                    {{showData.patientskeyword}}
                  </Form-item>
                </Col>
                <Col span="24">
                  <Form-item label="性别条件:" style="margin:0px">
                    {{showData.sex}}
                  </Form-item>
                </Col>
                <Col span="24">
                  <Form-item label="年龄条件:" style="margin:0px">
                    {{showData.ageconditionexpression}}
                  </Form-item>
                </Col>
              </Row>
              <Row>
                <Col span="24">
                <Form-item  label="备注:" style="margin:0px">
                  {{showData.medicalofficesdesc}}
                </Form-item>
                </Col>
              </Row>
            </Card>
          </Form>
        </Modal>
    </div>
  </div>
</template>
<script>
import button from '../../../components/add_btn'
import {
   getOfficesPage, delOffices
} from '../../../api/api'
export default {
  components: {
    'v-button': button
  },
  data () {
    return {
      params: 'department_add',
      total: 0,
      queyParams: {
        sort: 'desc',
        order: 'create_date',
        page: 0,
        rows: 10,
        searchParams: {
          keyWords: ''
        }
      },
      detailsModel: false,
      showData: {
        id: '',
        officesname: '',
        patientskeyword: '',
        sex: '',
        ageconditionexpression: '',
        medicalofficesdesc: ''
      },
      tableDatas: [],
      tableColumns: [
        {
          title: '科室名称',
          align: 'center',
          key: 'officesname',
          sortable: true
        },
        {
          title: '关联病情关键字',
          align: 'center',
          key: 'patientskeyword'
        },
        {
          title: '性别条件',
          align: 'center',
          key: 'sex',
          render: (h, params) => {
            var date = this.changeSex(params.row.sex) // 获取到当前列的性别
            return h('div', [
              h('span', date)
            ])
          }
        },
        {
          title: '年龄条件',
          align: 'center',
          key: 'ageconditionexpression',
          sortable: true
        },
        {
          title: '备注',
          align: 'center',
          key: 'medicalofficesdesc'
        },
        {
          title: '操作',
          key: 'action',
          width: 120,
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
                      this.show(params.index)
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
                    marginLeft: '5px'
                  },
                  on: {
                    click: () => {
                      this.$router.push({name: 'department/department_edit', params: {id: params.row.id}})
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
                    icon: 'trash-a'
                  },
                  style: {
                    marginLeft: '5px'
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
    // 判断性别
    changeSex (sex) {
      var nsex = 1
      if (sex === 1) {
        nsex = '男'
      } else if (sex === 2) {
        nsex = '女'
      } else if (sex === 10) {
        nsex = '不限'
      }
      return nsex
    },
    // 点击分页查询
    change (page) {
      // console.log(page)
      this.queyParams.page = page
      // this.$refs.tablePage.currentPage = 1
      this.getTableDatas(this.queyParams)
    },
    // 改变当前显示的每页大小
    doPageSizeChange: function (size) {
      this.queyParams.rows = size
      // this.$refs.tablePage.currentPage = 1
      this.getTableDatas(this.queyParams)
    },
    // 查看详情
    show (index) {
      this.showData.id = this.tableDatas[index].id
      this.showData.officesname = this.tableDatas[index].officesname
      this.showData.patientskeyword = this.tableDatas[index].patientskeyword
      this.showData.sex = this.changeSex(this.tableDatas[index].sex)
      this.showData.ageconditionexpression = this.tableDatas[index].ageconditionexpression
      this.showData.medicalofficesdesc = this.tableDatas[index].medicalofficesdesc
      this.detailsModel = true
    },
    // 删除
    remove (id) {
      var vm = this
      vm.$Modal.confirm({
        title: '科室信息',
        content: `是否删除此记录`,
        onOk: function () {
          console.log(id)
          let para = { id: id }
          vm.$Loading.start()
          delOffices(para).then((res) => {
            vm.query()
            vm.$Message.success('删除成功！')
          })
        }
      })
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
      getOfficesPage(params).then((res) => {
        vm.$Loading.finish()
        vm.total = res.data.total
        vm.tableDatas = res.data.rows
      })
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
