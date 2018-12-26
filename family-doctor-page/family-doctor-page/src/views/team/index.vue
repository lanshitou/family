/**
 * @title: 团队信息分页展示
 * @description: 团队信息分页分页展示，修改团队信息，查看团队成员
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
          <Table :data="listData" @on-selection-change="select" :columns="tableColumns"  border>
          </Table>
          <div class="pages-block">
            <div class="pages-right">
              <Page ref="tablePage" :total="total" :page-size="queyParams.rows" @on-page-size-change="doPageSizeChange" @on-change="change" show-sizer show-total show-elevator></Page>
            </div>
          </div>
        </div>
        <!--团队信息查看弹出框-->
        <Modal v-model="teamModel" title="团队信息查看" class="update-model">
          <Table border :columns="teamColumns" :data="teamList" @on-selection-change="select" stripe border></Table>
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
import button from '../../components/add_btn'
import {
  getTeamsMember, getTeamsPage, delTeams
} from '../../api/api'
export default {
  components: {
    'v-button': button
  },
  data () {
    return {
      params: 'teams_add',
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
      teamModel: false,
      updateTeamModel: false,
      selections: [],
      listData: [],
      teamList: [],
      teamColumns: [
        {
          title: '姓名',
          align: 'center',
          key: 'doctorname'
        },
        {
          title: '角色名称',
          align: 'center',
          key: 'doctorrolename'
        }
      ],
      tableColumns: [
        {
          title: '团队编号',
          align: 'center',
          key: 'teamnumber'
        },
        {
          title: '团队名称',
          align: 'center',
          key: 'teamname'
        },
        {
          title: '团队组成',
          align: 'center',
          render: (h, params) => {
            return h('div', [
              h('Button', {
                props: {
                  type: 'primary',
                  size: 'small',
                  icon: 'ios-search'
                },
                on: {
                  click: () => {
                    this.showTeam(params.index)
                  }
                }
              }, '查看团队成员')
            ])
          }
        },
        {
          title: '签约份数',
          align: 'center',
          key: 'contactcount'
        },
        {
          title: '好评率',
          align: 'center',
          key: 'goodReputation'
        },
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
                    h('div', '可提供签约服务')
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
                      this.update(params.index)
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
    // 查询数据
    query () {
      this.getTableDatas(this.queyParams)
    },
    // 分页查询公用方法
    getTableDatas: function (params) {
      let vm = this
      vm.$Loading.start()
      getTeamsPage(params).then((res) => {
        this.$Loading.finish()
        this.$refs.tablePage.currentPage = 1
        this.total = res.data.total
        this.listData = res.data.rows
      })
    },
    // 关闭对话框
    close () {
      this.teamModel = false
    },
    // 查看团队成员
    showTeam (index) {
      var id = this.listData[index].id
      let para = {
        medicalteamidentity: id
      }
      getTeamsMember(para).then((res) => {
        this.teamList = res.data
      })
      this.teamModel = true
    },
    // 查看可提供签约服务
    show (index) {
      var id = this.listData[index].id
      this.$router.push({ name: 'teams/service', params: { id: id } })
    },
    // 修改团队成员
    update (index) {
      var id = this.listData[index].id
      this.$router.push({ name: 'teams/edit', params: { id: id } })
    },
    // 删除
    remove (id) {
      var th = this
      th.$Modal.confirm({
        title: '团队成员信息',
        content: `是否删除此记录`,
        onOk: function () {
          let para = { id: id }
          th.$Loading.start()
          delTeams(para).then((res) => {
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
