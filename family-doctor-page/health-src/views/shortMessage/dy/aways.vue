<template>
    <div class="layout-body">
        <div class="layout-content">
            <Table border :columns="columns" :data="dataList"></Table>
            <div class="pages-block">
                <div class="pages-right">
                    <Page :total="total" :page-size="rows" @on-page-size-change="doPageSizeChange" @on-change="change" show-sizer show-total
                        ref="tablePage" show-elevator></Page>
                </div>
            </div>
        </div>
        <Modal title="短语详情" v-model="modelcheck" ok-text='关闭' cancel-text=' ' @on-ok="handleSend" :mask-closable="false">
            <p>类型:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{code}}</p><br><br>
            <p>短语内容： &nbsp; {{context}}</p><br><br>
        </Modal>
        <Modal title="转发短信" v-model="modeledit" ok-text='提交' :mask-closable="false">
            <span>类型:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span ><Input v-model="code"style="width: 200px"></Input><br><br>
              
            <span>短信内容： &nbsp; </span>
            <Input v-model="context" style="width: 200px"></Input><br><br>
        </Modal>
        <!-- <v-button :params="params">新增</v-button> -->
    </div>
</template>
<script>
import button from '../../../components/add_btn'
var organizationData = require('../../../../static/data/data2.json')
export default {
  components: {
    'v-button': button
  },
  data () {
    return {
      area: '群体',
      code: '',
      type: '',
      context: '',
      modeledit: false,
      modelcheck: false,
      params: 'dy_add',
      total: 0,
      page: 1,
      rows: 10,
      dataList: [],
      columns: [
        {
          title: '序号',
          key: 'name'
        },
        {
          title: '类型',
          key: 'num'
        },
        {
          title: '短信内容',
          key: 'contect'
        },
        {
          title: '发送人',
          key: 'people'
        },
        {
          title: '发送范围',
          key: 'area'
        },
        {
          title: '发送时间',
          key: 'time'
        },
        {
          title: '操作',
          key: 'action',
          width: '150',
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
                    type: 'primary',
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
              ]),
              h('Tooltip', {
                props: {
                  placement: 'top'
                }
              }, [
                h('Button', {
                  props: {
                    type: 'success',
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
                    h('div', '转发')
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
                    type: 'error',
                    size: 'small',
                    shape: 'circle',
                    icon: 'close'
                  },
                  style: {
                    marginLeft: '5px'
                  },
                  on: {
                    click: () => {
                      this.remove(params.index)
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
    this.dataList = organizationData
  },
  methods: {
    handleSend () {
    },
    // 查看
    check (index) {
      this.code = this.dataList[index].num
      this.type = this.dataList[index].contect
      this.context = this.dataList[index].address
      this.modelcheck = true
      // var th = this
      // th.$Modal.confirm({
      //   title: '短信详情',
      //   content: `编号：${this.dataList[index].name}<br>类型：${this.dataList[index].age}<br>自动恢复内容：${this.dataList[index].address}`,
      //   onOk: function () {
      //     // th.dataList.splice(index, 1)
      //     th.$Message.success('发送成功!')
      //   },
      //   onCancel: function () {
      //     th.$Message.success('取消')
      //   }
      // })
    },
    // 修改
    update (index) {
      this.code = this.dataList[index].num
      this.type = this.dataList[index].contect
      this.context = this.dataList[index].address
      this.modeledit = true
    },
    // 注销
    remove (index) {
      var th = this
      th.$Modal.confirm({
        title: '短信点播信息',
        content: `是否删除此记录`,
        onOk: function () {
          th.dataList.splice(index, 1)
          th.$Message.success('删除成功!')
        },
        onCancel: function () {
          th.$Message.success('取消')
        }
      })
    },
    // 点击分页页码
    change (page) {
    },
    // 改变当前显示的行数
    doPageSizeChange: function (size) {
      this.rows = size
    }
  }
}
</script>
<style>
    .layout-body {
        padding: 5px;
        background: #f8f8f9;
    }

    .layout-body-breadcrumb {
        text-align: left;
        margin-left: 10px;
        font-size: 36px;
    }

    .layout-head {
        margin: 10px;
    }

    .layout-head-title {
        float: left;
        padding: 10px 12px;
    }

    .pages-block {
        margin: 0px;
        overflow: hidden;
        margin-top: 10px;
        margin-left: 1px;
        margin-bottom: 140px;
        text-align: center;
    }

    .pages-right {
        vertical-align: middle;
    }
</style>
              