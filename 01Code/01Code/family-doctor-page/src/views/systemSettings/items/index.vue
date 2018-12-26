/**
 * @title: 签约服务项目分页展示
 * @description: 签约服务项目分页展示
 * @author: TiuWeb
*/
<template>
  <div>
    <div class="layout-content-main">
      <Row class="tree">
        <Col span="4">
        <Card style="height:525px" dis-hover>
          <Tree :data="baseData" :multiple="multiple" @on-select-change="selectItem"></Tree>
        </Card>
        </Col>
        <Col span="20">
        <Card style="height:525px;overflow:auto" dis-hover>
          <div style="background:#f3f3f3">
            <Button type="primary" @click="add">新增</Button>
            <!--<Button type="primary" @click="remove">删除</Button>-->
          </div>
          <Table :total="total" :data="listData" @on-selection-change="select" :columns="tableColumns"  border></Table>
          <div class="pages-block">
            <div class="pages-right">
              <Page ref="tablePage" :total="total" :page-size="pageSize" @on-change="change" @on-page-size-change="doPageSizeChange" show-sizer show-total show-elevator></Page>
            </div>
          </div>
        </Card>
        </Col>
      </Row>
    </div>
  </div>
</template>
<script>
import {
  findServiceType, findItemPage, deleteItem
} from '../../../api/api'
export default {
  data () {
    return {
      total: 0,
      pageSize: 10,
      ItemTypeId: '',
      multiple: false,
      updateModel: false,
      listData: [],
      selections: [],
      // 详情list
      detailList: [],
      selectionTable: [],
      baseData: [{
        title: '签约服务类别',
        id: '',
        expand: true,
        children: []
      }],
      updateData: {
        servicename: '', // 服务名称
        detail: '', // 服务详情
        servicemesure: '', // 服务计划计量
        servicemesureunit: '', // 服务计划计量单位
        doctormesure: '', // 所需医疗人员计量
        doctormesureunit: '', // 所需医疗人员计量单位:人/次
        medicalequipmentidentity: '', // 医疗设备外键
        medicalequipmentname: '', // 医疗设备名称
        servicecharge: '', // 预计费用
        servicechargeunit: '', // 预计费用单位:元
        servicechargefrequencyidentity: '', // 费用支付频率外键
        chargefrequencyname: '', // 计费频率名称
        servicereward: '', // 服务奖励
        periodidentity: '', // 服务计划周期外键:0每年,1每月,2每周
        peroidname: '' // 服务周期名称
      },
      tableColumns: [
        {
          title: '操作',
          key: 'action',
          align: 'center',
          width: '130',
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
                      this.show(params.row.id)
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
          title: '项目名称',
          align: 'center',
          key: 'servicename',
          width: '150'
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
                title: '详细内容',
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
          width: '130',
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
            return h('div', [
              h('div', params.row.doctormesure + params.row.doctormesureunit)
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
          width: '90',
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
    this.$Loading.start()
    findServiceType().then((res) => {
      this.$Loading.finish()
      for (var i = 0; i < res.data.length; i++) {
        if (i === 0) {
          this.baseData[0].children.push({
            title: res.data[0].codeinfoname,
            id: res.data[0].codeinfovalue,
            selected: true
          })
          var id = this.baseData[0].children[0].id
          this.selections = [this.baseData[0].children[0]]
          this.ItemTypeId = id
          let para = {
            page: 1,
            rows: 10,
            sort: 'desc',
            order: 'create_date',
            searchParams: {
              servicecategoryid: id
            }
          }
          this.$Loading.start()
          findItemPage(para).then((res) => { // 根据签约类别id查询属于该类别的服务项目
            this.$Loading.finish()
            this.total = res.data.total
            this.$refs.tablePage.currentPage = 1
            this.listData = res.data.rows
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
  },
  methods: {
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
    // 添加
    add () {
      if (this.selections.length === 0) {
        this.$Message.error('请选择一条服务类别！')
      } else if (this.selections.length !== 1) {
        this.$Message.error('只能选择一条服务类别！')
      } else {
        var id = this.selections[0].id
        this.$router.push({ name: 'items/add', params: { id: id } })
      }
    },
    // 选择签约服务类别
    selectItem (name) {
      if (name) {
        this.selections = name
        var id = name[0].id
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
          this.listData = res.data.rows
          this.zhenglixiangqing()
        })
      }
    },
    // 详情页面
    show (id) {
      this.$router.push({ name: 'items/detail', params: { id: id } })
    },
    // 修改页面
    update (id) {
      this.$router.push({ name: 'items/edit', params: { id: id } })
    },
    // 选择table
    select (select) {
      this.selectionTable = select
    },
    // 删除
    remove (id) {
      var th = this
      th.$Modal.confirm({
        title: '签约服务项目信息',
        content: `是否删除此记录`,
        onOk: function () {
          let para = { id: id }
          th.$Loading.start()
          deleteItem(para).then((res) => {
            if (res.status === 200) {
              th.$Message.success('删除成功!')
              let para = {
                page: 1,
                rows: 10,
                searchParams: {
                  servicecategoryid: th.ItemTypeId
                }
              }
              th.$Loading.start()
              findItemPage(para).then((res) => { // 根据签约类别id查询属于该类别的服务项目
                th.$Loading.finish()
                th.total = res.data.total
                th.listData = res.data.rows
                th.$refs.tablePage.currentPage = 1
                this.zhenglixiangqing()
              })
            } else {
              th.$Message.error('删除失败！')
            }
          })
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
        this.zhenglixiangqing()
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
    }
  }
}
</script>
<style scoped lang="less">
.layout-content-main {
  padding: 5px;
}

</style>

