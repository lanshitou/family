/**
 * @title: 签约医生信息分页展示
 * @description: 签约医生信息分页展示，修改删除签约医生信息
 * @author: TiuWeb
*/
<template>
  <div>
    <div class="layout-content" style="height:90%; overflow:auto;">
      <div class="layout-content-main">
        <div class='columnBox menu'>
          <div class='filterTitle'>
            <Input v-model="queyParams.searchParams.keyWords" placeholder="请输入关键字搜索" style="width: 200px"></Input>
            <Button type="primary" icon="ios-search" style="margin-left:10px" @click="query">查询</Button>
          </div>
        </div>
        <div>
          <div style="background:#f3f3f3;padding:2px">
            <Button-group>
              <Button type="primary" style="margin:1px" @click="add">新增</Button>
            </Button-group>
          </div>
          <Table :data="listData" @on-selection-change="select" :columns="tableColumns"border>
          </Table>
          <div class="pages-block">
            <div class="pages-right">
              <Page ref="tablePage" :total="total" :page-size="queyParams.rows" @on-page-size-change="doPageSizeChange" @on-change="change" show-sizer show-total show-elevator></Page>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import {
  getDocotrPage, delDocotr
} from '../../api/api'
export default {
  data () {
    return {
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
      detailsModel: false,
      updateModel: false,
      listData: [],
      updateData: {
        id: '',
        doctorname: '',
        sex: '',
        age: '',
        doctorscertificate: '',
        paccount: '',
        telephone: '',
        email: '',
        medicalinstitutionaddress: '',
        doctordesc: ''
      },
      showData: {
        doctorname: '',
        sex: '',
        age: '',
        doctorscertificate: '',
        paccount: '',
        telephone: '',
        email: '',
        medicalinstitutionaddress: ''
      },
      ruleValidate: {
        paccount: [
          { required: true, message: '账号不能为空', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '邮箱不能为空', trigger: 'blur' },
          { type: 'email', message: '邮箱格式不正确', trigger: 'blur' }
        ],
        medicalinstitutionaddress: [
          { required: true, message: '医疗机构地址不能为空', trigger: 'blur' }
        ],
        doctordesc: [
          { required: true, message: '医生简介不能为空', trigger: 'blur' }
        ]
      },
      tableColumns: [
        {
          title: '操作',
          key: 'action',
          width: '120',
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
        },
        {
          title: '姓名',
          align: 'center',
          key: 'doctorname'
        },
        {
          title: '性别',
          align: 'center',
          key: 'sex',
          render: (h, params) => {
            var date = this.changeSex(params.row.sex)
            return h('div', [
              h('span', date)
            ])
          }
        },
        {
          title: '年龄',
          align: 'center',
          key: 'age'
        },
        {
          title: '类别',
          align: 'center',
          key: 'professionallevelname'
        },
        {
          title: '所属医疗机构',
          align: 'center',
          key: 'institutionname'
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
      var nsex = 0
      if (sex === 0) {
        nsex = '未知的性别'
      } else if (sex === 1) {
        nsex = '男'
      } else if (sex === 2) {
        nsex = '女'
      } else if (sex === 9) {
        nsex = '未说明的性别'
      }
      return nsex
    },
    // 添加医生
    add () {
      this.$router.push({ path: 'doctors_add' })
    },
    // 查询数据
    query () {
      this.getTableDatas(this.queyParams)
    },
    // 分页查询公用方法
    getTableDatas: function (params) {
      let vm = this
      vm.$Loading.start()
      getDocotrPage(params).then((res) => {
        vm.$Loading.finish()
        this.$refs.tablePage.currentPage = 1
        vm.total = res.data.total
        vm.listData = res.data.rows
      })
    },
    // 打开查看详情弹出框
    show (index) {
      var id = this.listData[index].id
      this.$router.push({ name: 'doctors/check', params: { id: id } })
    },
    // 关闭查看详情弹出框
    closeDetailsModel () {
      this.detailsModel = false
    },
    // 修改
    update (id) {
      this.$router.push({ name: 'doctors/edit', params: { id: id } })
    },
    // 删除
    remove (id) {
      var th = this
      this.$Modal.confirm({
        title: '医生信息',
        content: `是否删除此记录`,
        onOk: function () {
          let para = { id: id }
          th.$Loading.start()
          delDocotr(para).then((res) => {
            if (res.status === 200) {
              th.$Message.success('删除成功!')
              th.getTableDatas(th.queyParams)
            } else {
              th.$Message.error('删除失败！')
            }
          })
        }
      })
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
