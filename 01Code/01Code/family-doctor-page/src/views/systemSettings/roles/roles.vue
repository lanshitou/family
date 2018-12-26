/**
 * @Title : 团队角色
 * @Description : 
 * @author : TiuWeb
 */
<template>
  <div>
    <div class="layout-content" style="height:95%; overflow:auto;">
      <div class="layout-content-main">
        <div style="background:#eee;padding: 1px;text-align:left">
          <Button-group>
            <Button type="primary" style="margin:1px" @click.native="add">新增</Button>
          </Button-group>
        </div>
        <Table :data="listData" :columns="tableColumns" @on-selection-change="select"  border>
        </Table>
        <div class="pages-block">
          <div class="pages-right">
            <Page :total="total" :page-size="pageSize" @on-page-size-change="doPageSizeChange" @on-change="change" show-sizer show-total
              ref="tablePage" show-elevator></Page>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import {
  getAllRolesPage, delRoles
} from '../../../api/api'
export default {
  data () {
    return {
      total: 0,
      page: 0,
      pageSize: 10,
      selections: [],
      listData: [],
      tableColumns: [
        {
          title: '角色名称',
          align: 'center',
          key: 'rolename'
        },
        {
          title: '角色职责',
          align: 'center',
          key: 'jobdescription'
        },
        {
          title: '任职资格/标准',
          align: 'center',
          key: 'jobqualification'
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
                    icon: 'compose'
                  },
                  on: {
                    click: () => {
                      this.$router.push({name: 'roles/roles_edit', params: {id: params.row.id}})
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
            //   h('Tooltip', {
            //     props: {
            //       placement: 'top'
            //     }
            //   }, [
            //     h('Button', {
            //       props: {
            //         type: 'ghost',
            //         size: 'small',
            //         shape: 'circle',
            //         icon: 'trash-a'
            //       },
            //       style: {
            //         marginLeft: '5px'
            //       },
            //       on: {
            //         click: () => {
            //           this.remove(params.row.id)
            //         }
            //       }
            //     }),
            //     h('div', {
            //       slot: 'content'
            //     }, [
            //       h('div', [
            //         h('div', '删除')
            //       ])
            //     ])
            //   ])
            ])
          }
        }
      ]
    }
  },
  created: function () {
    let para = {
      page: this.page,
      rows: this.rows,
      sort: 'desc',
      order: 'create_date'
    }
    this.$Loading.start()
    getAllRolesPage(para).then((res) => {
      this.$Loading.finish()
      this.total = res.data.total
      this.listData = res.data.rows
    })
  },
  methods: {
    // 改变当前显示的行数
    doPageSizeChange: function (size) {
      this.selections = []
      this.pageSize = size
      let para = {
        page: this.page,
        rows: size
      }
      this.$Loading.start()
      getAllRolesPage(para).then((res) => {
        this.$Loading.finish()
        this.total = res.data.total
        this.data = res.data.rows
      })
    },
    change (row) {
      let para = {
        page: row,
        rows: this.pageSize
      }
      this.$Loading.start()
      getAllRolesPage(para).then((res) => {
        this.$Loading.finish()
        this.total = res.data.total
        this.listData = res.data.rows
      })
    },
    select (selection) {
      this.selections = selection
    },
    // 新增
    add () {
      this.$router.push({name: 'roles/roles_add'})
    },
    // 删除
    remove (id) {
      var th = this
      th.$Modal.confirm({
        title: '角色信息',
        content: `是否删除此记录`,
        onOk: function () {
          let para = { id: id }
          th.$Loading.start()
          delRoles(para).then((res) => {
            if (res.status === 200) {
              let para = {
                page: th.page,
                rows: th.rows
              }
              th.$Loading.start()
              getAllRolesPage(para).then((res) => {
                th.$Loading.finish()
                th.total = res.data.total
                th.listData = res.data.rows
              })
              th.$Message.success('删除成功!')
            } else {
              th.$Message.error('删除失败！')
            }
          })
        }
      })
    }
  }
}
</script>
<style scoped lang="less">
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
  margin-top: 10px;
  overflow: hidden;
  background: #fff;
  border-radius: 4px;
}
</style>
