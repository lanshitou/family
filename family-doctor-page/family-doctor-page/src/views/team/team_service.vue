/**
 * @title: 团队可提供签约服务分页展示
 * @description: 签约服务分页展示，添加删除可提供的签约服务
 * @author: TiuWeb
*/
<template>
  <div>
    <div class="layout-content-main">
      <Row class="tree">
        <Col span="4">
        <Card style="height:525px;margin-right:1px">
          <Tree :data="baseData" :multiple="multiple" @on-select-change="selectItem" ></Tree>
        </Card>
        </Col>
        <Col span="20">
        <Card style="height:525px;overflow:auto" dis-hover>
          <div style="background:#f3f3f3;text-align:right">
            <Button type="primary"  @click="back">返回</Button>
          </div>
          <button class="c-table-btn user-head" @click="add">新增</button>
          <!--选择服务项目构弹出框-->
          <Modal v-model="addModel" title="签约服务项目信息" width="800" @on-ok="saveItems">
              <Select placeholder="请选择服务类别" style="width:200px;margin-bottom:10px" @on-change="changeI" :label-in-value="true">
                <Option v-for="item in itemsType" :value="item.codeinfovalue" :key='item.codeinfovalue'>{{ item.codeinfoname }}
                </Option>
              </Select>
            <Table :data="itemList" :columns="itemsColumns" @on-selection-change="select"  border>
            </Table>
          </Modal>
          <Table :total="total" :data="listData"  :columns="tableColumns" border></Table>
        </Card>
        </Col>
      </Row>
    </div>
  </div>
</template>
<script>
import {
 findServiceType, findItemPage, getFuWuByTeamsKey, findTeamsByKey, insertTeamItem, delItem //, findServiceType
} from '../../api/api'
export default {
  data () {
    return {
      total: 0,
      pageSize: 10,
      ItemTypeId: '',
      teamId: '',
      teamName: '',
      addModel: false,
      multiple: false,
      updateModel: false,
      itemList: [],
      itemsType: [],
      listData: [],
      selections: [],
      selectionItem: [],
      selectionTable: [],
      // 详情list
      detailList: [],
      baseData: [{
        title: '签约服务类别',
        id: '',
        expand: true,
        disabled: true,
        children: []
      }],
      tableColumns: [
        {
          title: '项目名称',
          align: 'center',
          key: 'servicename'
        },
        {
          title: '详细内容',
          align: 'center',
          key: 'detail',
          render: (h, params) => {
            let a = params.row.detail
            let details = a.replace(/<.*?>/ig, '')
            return h('Poptip', {
              props: {
                trigger: 'hover',
                title: '',
                placement: 'right-start'
              }
            }, [
              h('Tag', details.substring(0, 20)),
              h('div', {
                slot: 'content'
              }, [
                h('ul', this.detailList[params.index].split.map(item => {
                  return h('li', {
                    style: {
                      textAlign: 'center',
                      padding: '4px'
                    }
                  }, item)
                }))
              ])
            ])
          }
        },
        {
          title: '服务计划',
          align: 'center',
          key: 'servicemesure',
          width: '150',
          render: (h, params) => {
            // 获取到当前列的服务周期
            var periodidentity = this.renderPeriod(params.row.periodidentity)
            // 获取当前列服务方式
            var isappointment = this.renderAppointment(params.row.isappointment)
            return h('div', [
              h('div', params.row.servicemesure + params.row.servicemesureunit + '/' + periodidentity + ' ' + isappointment)
            ])
          }
        },
        {
          title: '医疗人员',
          align: 'center',
          key: 'doctormesure',
          width: '200',
          render: (h, params) => {
            // 获取到当前列的医疗人员角色
            var itemRole = this.renderItemRole(params.row.itemRole)
            return h('div', [
              h('div', params.row.doctormesure + params.row.doctormesureunit + itemRole)
            ])
          }
        },
        {
          title: '医疗设备',
          align: 'center',
          key: 'medicalequipmentname'
        },
        {
          title: '预计收费',
          align: 'center',
          key: 'servicecharge',
          render: (h, params) => {
            // 获取到当前列的费用支付频率外键
            var servicechargefrequencyidentity = this.renderChargefrequency(params.row.servicechargefrequencyidentity)
            return h('div', [
              h('div', params.row.servicecharge + params.row.servicechargeunit + '/' + servicechargefrequencyidentity)
            ])
          }
        },
        {
          title: '服务奖励',
          align: 'center',
          key: 'servicereward',
          render: (h, params) => {
            return h('div', [
              h('div', params.row.servicereward + '元/' + '次')
            ])
          }
        },
        {
          title: '操作',
          key: 'action',
          align: 'center',
          width: '70',
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
                    icon: 'trash-a'
                  },
                  style: {
                    marginLeft: '5px'
                  },
                  on: {
                    click: () => {
                      this.deleteItem(params.row.id)
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
      ],
      itemsColumns: [
        {
          type: 'selection',
          width: 60,
          key: 'id',
          align: 'center'
        },
        {
          title: '项目名称',
          align: 'center',
          key: 'servicename',
          width: '100',
          render: (h, params) => {
            return h('Poptip', {
              props: {
                trigger: 'hover',
                title: '',
                placement: 'bottom'
              }
            }, [
              h('Tag', params.row.servicename.substring(0, 10)),
              h('div', {
                slot: 'content'
              }, [
                h('div', [
                  h('div', params.row.servicename.substring(0, 30))
                ])
              ])
            ])
          }
        },
        {
          title: '详细内容',
          align: 'center',
          key: 'detail',
          render: (h, params) => {
            let a = params.row.detail
            let details = a.replace(/<.*?>/ig, '')
            return h('Poptip', {
              props: {
                trigger: 'hover',
                title: '',
                placement: 'right-start'
              }
            }, [
              h('Tag', details.substring(0, 20)),
              h('div', {
                slot: 'content'
              }, [
                h('ul', this.detailList[params.index].split.map(item => {
                  return h('li', {
                    style: {
                      textAlign: 'center',
                      padding: '4px'
                    }
                  }, item)
                }))
              ])
            ])
          }
        },
        {
          title: '服务计划',
          align: 'center',
          key: 'servicemesure',
          width: '120',
          render: (h, params) => {
            // 获取到当前列的服务周期
            var periodidentity = this.renderPeriod(params.row.periodidentity)
            // 获取当前列服务方式
            var isappointment = this.renderAppointment(params.row.isappointment)
            return h('div', [
              h('div', params.row.servicemesure + params.row.servicemesureunit + '/' + periodidentity + ' ' + isappointment)
            ])
          }
        },
        {
          title: '医疗人员',
          align: 'center',
          key: 'doctormesure',
          render: (h, params) => {
            // 获取到当前列的医疗人员角色
            var itemRole = this.renderItemRole(params.row.itemRole)
            return h('div', [
              h('div', params.row.doctormesure + params.row.doctormesureunit + itemRole)
            ])
          }
        },
        {
          title: '医疗设备',
          align: 'center',
          key: 'medicalequipmentname'
        },
        {
          title: '预计收费',
          align: 'center',
          key: 'servicecharge',
          render: (h, params) => {
            // 获取到当前列的费用支付频率外键
            var servicechargefrequencyidentity = this.renderChargefrequency(params.row.servicechargefrequencyidentity)
            return h('div', [
              h('div', params.row.servicecharge + params.row.servicechargeunit + '/' + servicechargefrequencyidentity)
            ])
          }
        },
        {
          title: '服务奖励',
          align: 'center',
          key: 'servicereward',
          render: (h, params) => {
            return h('div', [
              h('div', params.row.servicereward + '元/' + '次')
            ])
          }
        }
      ]
    }
  },
  created: function () {
    this.teamId = this.$route.path.substring(14) // 截取url后的参数
    let para = {
      id: this.teamId
    }
    // 根据团队id查出团队名称
    findTeamsByKey(para).then((res) => {
      this.teamName = res.data.teamname
    })
    this.$Loading.start()
    findServiceType().then((res) => {
      this.selectionItem = res.data[0]
      for (var i = 0; i < res.data.length; i++) {
        if (i === 0) {
          this.baseData[0].children.push({
            title: res.data[0].codeinfoname,
            id: res.data[0].codeinfovalue,
            selected: true
          })
          var id = this.baseData[0].children[0].id
          this.ItemTypeId = id
          let para = {
            servicecategoryid: this.selectionItem.codeinfovalue,
            teamidentify: this.teamId
          }
          this.$Loading.start()
          getFuWuByTeamsKey(para).then((res) => { // 根据签约类别id查询属于该类别的服务项目
            this.$Loading.finish()
            this.listData = res.data
            this.zhenglixiangqing()
          })
        } else {
          this.baseData[0].children.push({
            title: res.data[i].codeinfoname,
            id: res.data[i].codeinfovalue,
            checked: false
          })
        }
      }
    })
    this.$Loading.start()
    findServiceType().then((res) => {
      this.$Loading.finish()
      this.itemsType = res.data
    })
  },
  methods: {
    // 返回
    back () {
      this.$router.push({ path: '/teams' })
    },
    // 返回服务方式
    renderAppointment (id) {
      if (id !== '' || id !== null) {
        var name = ''
        if (id === 0) {
          name = '上门服务'
        } if (id === 1) {
          name = '预约就诊'
        }
        return name
      }
    },
    // 返回服务周期名称
    renderPeriod (id) {
      if (id) {
        var name = ''
        if (id === '0') {
          name = '年'
        } if (id === '1') {
          name = '月'
        } if (id === '2') {
          name = '周'
        }
        return name
      } else {
        return ''
      }
    },
    // 返回医护人员角色名称
    renderItemRole (itemRole) {
      var name = ''
      for (var i = 0; i < itemRole.length; i++) {
        if (i === 0) {
          name = ' ' + itemRole[i].rolename + ','
        } if (itemRole.length === 1) {
          name = ' ' + itemRole[i].rolename
        } if (i === itemRole.length - 1 && i !== 0) {
          name = name + itemRole[i].rolename
        }
      }
      return name
    },
    // 返回支付频率名称
    renderChargefrequency (id) {
      if (id) {
        var name = ''
        if (id === '0') {
          name = '次'
        } if (id === '1') {
          name = '月'
        } if (id === '2') {
          name = '季'
        } if (id === '3') {
          name = '年'
        }
        return name
      } else {
        return ''
      }
    },
    // 打开添加弹出框
    add () {
      this.addModel = true
    },
    // 选择服务类别
    changeI (change) {
      var id = change.value
      let para = {
        page: 1,
        rows: 10,
        searchParams: {
          servicecategoryid: id
        }
      }
      this.$Loading.start()
      findItemPage(para).then((res) => { // 根据签约类别id查询属于该类别的服务项目
        this.$Loading.finish()
        this.total = res.data.total
        this.itemList = res.data.rows
        this.detailList = []
        // 整理详情
        if (this.listData !== null && this.itemList.length > 0) {
          for (var i = 0; i < this.itemList.length; i++) {
            var a = this.itemList[i].detail
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
      })
    },
      // 整理详情
    zhenglixiangqing () {
      this.detailList = []
      if (this.listData !== null && this.listData.length > 0) {
        for (var i = 0; i < this.listData.length; i++) {
          var a = this.listData[i].detail
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
    // 保存
    saveItems () {
      let th = this
      let para = {
        servcieitemidentify: th.selections[0].id,
        servcieitemname: th.selections[0].servicename,
        teamidentify: th.teamId,
        teamname: th.teamName
      }
      th.$Loading.start()
      insertTeamItem(para).then((res) => {
        if (res.status === 200) {
          th.$Message.success('添加成功!')
          th.$Loading.start()
          // 清空树的菜单
          this.baseData[0].children = []
          // 查询服务类别
          findServiceType().then((res) => {
            this.selectionItem = res.data[0]
            for (var i = 0; i < res.data.length; i++) {
              if (i === 0) {
                // 树的第一个节点默认选中
                this.baseData[0].children.push({
                  title: res.data[0].codeinfoname,
                  id: res.data[0].codeinfovalue,
                  selected: true
                })
                var id = this.baseData[0].children[0].id
                this.ItemTypeId = id
                // 根据服务类别和团队id查询该团队可提供服务类别的服务项目
                let para = {
                  servicecategoryid: this.selectionItem.codeinfovalue,
                  teamidentify: this.teamId
                }
                this.$Loading.start()
                // 根据签约类别id查询属于该类别的服务项目
                getFuWuByTeamsKey(para).then((res) => {
                  this.$Loading.finish()
                  this.listData = res.data
                })
              } else {
                this.baseData[0].children.push({
                  title: res.data[i].codeinfoname,
                  id: res.data[i].codeinfovalue,
                  checked: false
                })
              }
            }
          })
        } else {
          th.$Message.error('添加失败!')
        }
      })
    },
    // 选择签约服务类别
    selectItem (name) {
      if (name) {
        // 团队服务分类查询参数
        let para = {
          servicecategoryid: name[0].id,
          teamidentify: this.teamId
        }
        this.$Loading.start()
        // 根据签约类别id查询属于该类别的服务项目
        getFuWuByTeamsKey(para).then((res) => {
          this.$Loading.finish()
          this.listData = res.data
          this.zhenglixiangqing()
        })
      }
    },
    // 删除当前服务项目
    deleteItem (id) {
      let th = this
      let para = {
        servcieitemidentify: id,
        teamidentify: th.teamId
      }
      delItem(para).then((res) => {
        if (res.status === 200) {
          th.$Message.success('删除成功!')
          // 清空树的菜单
          this.baseData[0].children = []
          // 查询服务类别
          findServiceType().then((res) => {
            this.selectionItem = res.data[0]
            for (var i = 0; i < res.data.length; i++) {
              if (i === 0) {
                // 树的第一个节点默认选中
                this.baseData[0].children.push({
                  title: res.data[0].codeinfoname,
                  id: res.data[0].codeinfovalue,
                  selected: true
                })
                var id = this.baseData[0].children[0].id
                this.ItemTypeId = id
                // 根据服务类别和团队id查询该团队可提供服务类别的服务项目
                let para = {
                  servicecategoryid: this.selectionItem.codeinfovalue,
                  teamidentify: this.teamId
                }
                this.$Loading.start()
                // 根据签约类别id查询属于该类别的服务项目
                getFuWuByTeamsKey(para).then((res) => {
                  this.$Loading.finish()
                  this.listData = res.data
                  this.zhenglixiangqing()
                })
              } else {
                this.baseData[0].children.push({
                  title: res.data[i].codeinfoname,
                  id: res.data[i].codeinfovalue,
                  checked: false
                })
              }
            }
          })
        } else {
          th.$Message.error('删除失败!')
        }
      })
    },
    // 改变当前第几页
    change (row) {
      var th = this
      let para = {
        page: row,
        rows: th.pageSize,
        searchParams: {
          servicecategoryid: th.ItemTypeId
        }
      }
      th.$Loading.start()
      findItemPage(para).then((res) => { // 根据签约类别id查询属于该类别的服务项目
        th.$Loading.finish()
        th.total = res.data.total
        th.listData = res.data.rows
      })
    },
    // 改变每页显示多少条
    doPageSizeChange: function (size) {
      var th = this
      th.pageSize = size
      let para = {
        page: th.page,
        rows: size,
        searchParams: {
          servicecategoryid: th.ItemTypeId
        }
      }
      th.$Loading.start()
      findItemPage(para).then((res) => { // 根据签约类别id查询属于该类别的服务项目
        th.$Loading.finish()
        th.total = res.data.total
        th.listData = res.data.rows
        this.zhenglixiangqing()
      })
    },
    // 选择可提供的签约服务
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

.index {
  padding: 2px;
  margin-top: 5px;
  margin-bottom: 10PX;
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
  padding: 10px;
}

.layout-content {
  min-height: 78%;
  margin-top: 15px;
  overflow: hidden;
  background: #fff;
  border-radius: 4px;
}

.layout-content-two {
  margin-top: 15px;
  overflow: hidden;
  background: #fff;
  border-radius: 4px;
}
.c-table-btn {
  position: fixed;
  right: 18px;
  bottom: 18px;
  z-index: 99;
  width: 86px;
  height: 86px;
  font-size: 18px;
  background-color: #2d8cf0;
  color: #ffffff;
  border-radius: 50%;
  border: none;
  outline: none;
}
.user-head {
  cursor: pointer;
  display: block;
  float: left;
  /*padding: 0 10px;*/
  /*background-color: #80a0c1;*/
  .userPic {
    float: left;
    height: 30px;
    width: 30px;
    border-radius: 100%;
    margin: 10px 5px 10px 0;
  }
  .closePic {
    &:hover {
      /*color: #efefef;*/
      font-size: 28px;
      transform: rotateZ(360deg);
    }
    float: left;
    height: 24px;
    width: 24px;
    font-size: 24px;
    border-radius: 100%;
    margin: 13px 5px 11px;
    /*color: #b9c5d2;*/
    transition: all .3s ease;
  }
}
</style>

