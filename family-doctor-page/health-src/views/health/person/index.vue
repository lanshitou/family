<template>
  <div class="layout-body">
    <div class="layout-body-breadcrumb">
    <Breadcrumb>
      <BreadcrumbItem href="object">
        <Icon type="android-contacts"></Icon> 保健人员
      </BreadcrumbItem>
    </Breadcrumb>
  </div>
    <div class="layout-head">
    <Row>
      <Col span="8">
      <div class="layout-head-title" >机&#8195;构</div>
        <Select v-model="queyParams.searchParams.org" style="padding-top:5px;width:150px;float:left" placeholder="全部">
          <Option value="0" label="全部">
            <span>全部</span>
            <span style="float:right;color:#ccc"></span>
          </Option>
          <Option value="1" label="未知">
            <span>未知</span>
          </Option>
          <Option value="2" label="省保健局">
            <span>省保健局</span>
          </Option>
          <Option value="3" label="市保健局">
            <span>市保健局</span>
          </Option>
          <Option value="4" label="省人民医院">
            <span>省人民医院</span>
          </Option>
          <Option value="5" label="交大二院">
            <span>交大二院</span>
          </Option>
        </Select>
      </Col>
      <Col span="8" pull="3">
      <div class="layout-head-title" >类&#8195;别</div>
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
          <Option value="3" label="联系人员">
            <span>联系人员</span>
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
      params: 'person_add',
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
          jibie: '副省',
          sex: '男',
          phone: '029-23997400',
          depart: '人大',
          address: '陕西省西安市'
        },
        {
          name: '李斯',
          idcard: '61042519880123122',
          jibie: '省级',
          sex: '男',
          phone: '029-2376004',
          depart: '省保健局',
          address: '陕西省西安市'
        }
      ],
      columns1: [
        {
          title: '姓名',
          key: 'name'
        },
        {
          title: '身份证',
          key: 'idcard'
        },
        {
          title: '类别',
          key: 'jibie'
        },
        {
          title: '性别',
          key: 'sex'
        },
        {
          title: '电话',
          key: 'phone'
        },
        {
          title: '单位',
          key: 'depart'
        },
        {
          title: '单位地址',
          key: 'address'
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
                    h('div', '注销')
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
      this.$router.push({name: 'person/edit', params: {name: this.dataList[index].name}})
    },
    // 注销
    remove (index) {
      var th = this
      th.$Modal.confirm({
        title: '保健人员信息',
        content: `是否注销此记录`,
        onOk: function () {
          th.dataList.splice(index, 1)
          th.$Message.success('注销成功!')
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