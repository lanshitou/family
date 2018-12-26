<template>
    <div class="layout-body">
      <div class="layout-body-breadcrumb">
      <Breadcrumb>
        <BreadcrumbItem href="object">
          <Icon type="ios-paper"></Icon> 角色管理
        </BreadcrumbItem>
      </Breadcrumb>
    </div>
      <div class="layout-head">
      </div>
      <div class="layout-content">
        <Table border :columns="columns1" :data="dataList"></Table>
          <div class="pages-block">
          <div class="pages-right">
            <Page :total="total" :page-size="rows" @on-page-size-change="doPageSizeChange" @on-change="change" show-sizer show-total
            ref="tablePage" show-elevator></Page>
          </div>
        </div>
        <Modal
        v-model="model"
        title="编辑角色"
        @on-ok="ok"
        @on-cancel="cancel">
        <span>角色名称:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span ><Input v-model="rolename"style="width: 400px"></Input><br><br>
            <span>角色职能： &nbsp;  </span>
        <Input v-model="role" style="width: 400px"></Input>
    </Modal>
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
        model: false,
        rolename: '',
        role: '',
        params: 'roles_add',
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
            name: '保健服务人员',
            jibie: '保健服务人员为保健对象提供服务'
          },
          {
            name: '保健管理人员',
            jibie: '管理分配保健服务人员'
          },
          {
            name: '保健对象',
            jibie: '保健服务对象'
          },
          {
            name: '机构联系人',
            jibie: '联系具体保健对象'
          }
        ],
        columns1: [
          {
            title: '角色ID',
            type: 'index',
            width: 200
          },
          {
            title: '角色名称',
            key: 'name'
          },
          {
            title: '角色职能',
            key: 'jibie'
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
      ok () {},
      cancel () {},
      update (index) {
        this.model = true
        this.rolename = this.dataList[index].name
        this.role = this.dataList[index].jibie
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