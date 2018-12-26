<template>
    <div class="layout-body">
      <div class="layout-body-breadcrumb">
      <Breadcrumb>
        <BreadcrumbItem href="object">
          <Icon type="person"></Icon> 用户管理
        </BreadcrumbItem>
      </Breadcrumb>
    </div>
      <div class="layout-head">
      <Row>
        <Col span="8">
        <div class="layout-head-title" >状&#8195;态</div>
          <Select v-model="queyParams.searchParams.org" style="padding-top:5px;width:150px;float:left" placeholder="全部">
            <Option value="0" label="全部">
              <span>全部</span>
              <span style="float:right;color:#ccc"></span>
            </Option>
            <Option value="2" label="启用">
              <span>启用</span>
            </Option>
            <Option value="3" label="禁用">
              <span>禁用</span>
            </Option>
          </Select>
        </Col>
        <Col span="8" pull="3">
        <div class="layout-head-title" >角&#8195;色</div>
          <Select v-model="queyParams.searchParams.type" style="padding-top:5px;width:150px;float:left" placeholder="全部">
            <Option value="1" label="全部">
              <span>全部</span>
            </Option>
            <Option value="2" label="保健服务人员">
              <span>保健服务人员</span>
            </Option>
            <Option value="3" label="保健管理人员">
              <span>保健管理人员</span>
            </Option>
            <Option value="4" label="联系人员">
              <span>机构联系人员</span>
            </Option>
            <Option value="5" label="保健对象">
              <span>保健对象</span>
            </Option>
          </Select>
        </Col>
        <Col span="8" push="1">
          <div class='layout-head-title'>关键字</div>
          <Input v-model="queyParams.searchParams.keywords" style="width:150px" placeholder="请输入关键字查询"></Input>
          <Button icon="ios-search" style="background:#5e87b0;color:#fff;margin-left:8px" @click="query">查询</Button>
        </Col>
      </Row>
      </div>
      <div class="layout-content">
        <Table border :columns="columns1" :data="dataList"></Table>
          <div class="pages-block">
          <div class="pages-right">
            <Page :total="total" :page-size="rows" @on-page-size-change="doPageSizeChange" @on-change="change" show-sizer show-total
            ref="tablePage" show-elevator></Page>
          </div>
        </div>
      </div>
      <v-button :params="params">新增</v-button>
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
        params: 'users_add',
        total: 0,
        page: 1,
        rows: 10,
        queyParams: {
          sort: 'desc',
          order: 'create_date',
          page: 0,
          rows: 10,
          searchParams: {
            org: '0',
            type: '1',
            keywords: ''
          }
        },
        dataList: [
          {
            name: '张萨',
            idcard: '61042519870123154',
            jibie: '保健服务人员',
            sex: '男',
            phone: '029-23997400',
            depart: '禁用',
            address: '陕西省西安市'
          },
          {
            name: '李斯',
            idcard: '61042519880123122',
            jibie: '保健服务人员',
            sex: '男',
            phone: '029-2376004',
            depart: '启用',
            address: '陕西省西安市'
          }
        ],
        columns1: [
          {
            title: 'ID',
            type: 'index',
            width: 60,
            align: 'center'
          },
          {
            title: '用户名',
            key: 'name'
          },
          {
            title: '密码',
            key: 'idcard'
          },
          {
            title: '角色',
            key: 'jibie'
          },
          {
            title: '电话',
            key: 'phone'
          },
          {
            title: '状态',
            key: 'depart'
          },
          {
            title: '操作',
            key: 'action',
            width: '120',
            align: 'center',
            render: (h, params) => {
              let statu = params.row.depart === '启用' ? '禁用' : '启用'
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
                      h('div', '编辑')
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
                      h('div', statu)
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
    },
    methods: {
      query: function () {
        alert('11111111')
      },
      update (index) {
        this.$router.push({name: 'users/edit', params: {name: this.dataList[index].name}})
      },
      // 注销
      remove (index) {
        var th = this
        var statu = ''
        if (th.dataList[index].depart === '启用') {
          statu = '禁用'
        } else {
          statu = '启用'
        }
        th.$Modal.confirm({
          title: '确定' + statu + '此用户',
          content: '是否确定' + statu + '此用户',
          onOk: function () {
            th.$Message.success(statu + '成功!')
            th.dataList[index].depart = statu
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
    font-size:36px;
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