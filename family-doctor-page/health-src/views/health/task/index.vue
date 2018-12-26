<template>
    <div class="layout-body">
      <div class="layout-body-breadcrumb">
        <Breadcrumb>
          <BreadcrumbItem href="object">
            <Icon type="card"></Icon> 保健任务
          </BreadcrumbItem>
        </Breadcrumb>
      </div>
      <div class="layout-head">
      <Row>
        <Col span="8">
        <div class="layout-head-title" >类&#8195;型</div>
          <Select v-model="queyParams.searchParams.type" style="padding-top:5px;width:150px;float:left" placeholder="全部">
            <Option value="0" label="全部">
              <span>全部</span>
              <span style="float:right;color:#ccc"></span>
            </Option>
            <Option value="1" label="日常医疗">
              <span>日常医疗</span>
            </Option>
            <Option value="2" label="体检">
              <span>体检</span>
            </Option>
            <Option value="3" label="会诊">
              <span>会诊</span>
            </Option>
            <Option value="4" label="住院登记">
              <span>住院登记</span>
              <span style="float:right;color:#ccc"></span>
            </Option>
            <Option value="5" label="病情报告">
              <span>病情报告</span>
            </Option>
            <Option value="6" label="省外医疗记录">
              <span>省外医疗记录</span>
            </Option>
            <Option value="7" label="重大活动">
              <span>重大活动</span>
            </Option>
            <Option value="8" label="其他">
              <span>其他</span>
            </Option>
          </Select>
        </Col>
        <Col span="8" pull="3">
        <div class="layout-head-title" >发布状态</div>
          <Select v-model="queyParams.searchParams.status" style="padding-top:5px;width:150px;float:left" placeholder="全部">
            <Option value="1" label="全部">
              <span>全部</span>
            </Option>
            <Option value="2" label="已回复">
              <span>已回复</span>
            </Option>
            <Option value="3" label="待回复">
              <span>待回复</span>
            </Option>
            <Option value="4" label="已撤销">
              <span>已撤销</span>
            </Option>
          </Select>
        </Col>
        <Col span="8" pull="6">
          <div class='layout-head-title'>接受机构</div>
          <Select v-model="queyParams.searchParams.org" style="padding-top:5px;width:150px;float:left" placeholder="全部">
            <Option value="1" label="全部">
              <span>全部</span>
            </Option>
            <Option value="2" label="交大口腔">
              <span>交大口腔</span>
            </Option>
            <Option value="3" label="交大一院">
              <span>交大一院</span>
            </Option>
            <Option value="4" label="交大二院">
              <span>交大二院</span>
            </Option>
          </Select>
        </Col>
      </Row>
      </div>
      <div class="layout-content">
        <Table border :columns="columns1" :data="data1"></Table>
        <div class="pages-block">
          <div class="pages-right">
            <Page :total="total" :page-size="rows" @on-page-size-change="doPageSizeChange" @on-change="change" show-sizer show-total
            ref="tablePage" show-elevator></Page>
          </div>
        </div>
        <v-button :params="params">新增</v-button>
      </div>
    </div>
  </template>
  <script>
  import button from '../../../components/health/add_btn'
  export default {
    components: {
      'v-button': button
    },
    data () {
      return {
        params: 'task_add',
        total: 0,
        page: 1,
        rows: 10,
        queyParams: {
          sort: 'desc',
          order: 'create_date',
          page: 0,
          rows: 10,
          searchParams: {
            type: '0',
            status: '2',
            org: '1',
            keywords: ''
          }
        },
        columns1: [
          {
            title: '任务标题',
            key: 'title'
          },
          {
            title: '类型',
            key: 'type'
          },
          {
            title: '内容摘要',
            key: 'content'
          },
          {
            title: '发布机构',
            key: 'institutionSend'
          },
          {
            title: '发布人',
            key: 'people'
          },
          {
            title: '发布日期',
            key: 'time'
          },
          {
            title: '发布状态',
            key: 'statu'
          },
          {
            title: '接收机构',
            key: 'institution'
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
                      icon: 'search'
                    },
                    style: {
                      marginLeft: '5px'
                    },
                    on: {
                      click: () => {
                        this.check(params.id)
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
                      h('div', '撤销')
                    ])
                  ])
                ])
              ])
            }
          }
        ],
        data1: [
          {
            title: '关于体检通知',
            type: '体检',
            content: '体检健康预防',
            institutionSend: '省保健局',
            people: '交大',
            time: '2017-11-3',
            statu: '已回复',
            institution: '交大口腔'
          },
          {
            title: '关于体检通知',
            type: '体检',
            content: '体检健康检验',
            institutionSend: '省保健局',
            people: '交大',
            time: '2017-11-4',
            statu: '待回复',
            institution: '交大口腔'
          },
          {
            title: '关于体检通知',
            type: '体检',
            content: '体检健康通知',
            institutionSend: '省保健局',
            people: '交大',
            time: '2017-11-5',
            statu: '已撤销',
            institution: '交大口腔'
          },
          {
            title: '关于体检通知',
            type: '体检',
            content: '体检健康结果',
            institutionSend: '省保健局',
            people: '交大',
            time: '2017-11-6',
            statu: '已回复',
            institution: '交大口腔'
          }
        ]
      }
    },
    methods: {
      add () {
        this.$router.push({name: 'task/add'})
      },
      check () {
        this.$router.push({name: 'task/detail'})
      },
      // 注销
      remove (index) {
        var th = this
        th.$Modal.confirm({
          title: '确定要撤销任务',
          content: `关于体检的通知吗？`,
          onOk: function () {
            th.data1.splice(index, 1)
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
  .layout-body{
    padding:5px;
    background: #f8f8f9;
  }
  .layout-body-breadcrumb{
  margin-left:10px;
  text-align:left;
}
  .layout-head{
    margin:10px;
  }
  .layout-head-title{
    float:left;
    padding:10px 12px;
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