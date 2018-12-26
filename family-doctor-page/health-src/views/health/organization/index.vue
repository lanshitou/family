<template>
  <div class="layout-body">
    <div class="layout-body-breadcrumb">
    <Breadcrumb>
      <BreadcrumbItem href="object">
        <Icon type="ios-home-outline"></Icon> 保健机构
      </BreadcrumbItem>
    </Breadcrumb>
  </div>
    <div class="layout-head">
    <Row>
      <Col span="5">
      <div class="layout-head-title" >类&#8195;型</div>
        <Select v-model="queyParams.searchParams.type" style="padding-top:5px;width:150px;float:left" placeholder="全部">
          <Option value="0" label="全部">
            <span>全部</span>
            <span style="float:right;color:#ccc"></span>
          </Option>
          <Option value="1" label="保健管理机构">
            <span>保健管理机构</span>
          </Option>
          <Option value="2" label="基地医院">
            <span>基地医院</span>
          </Option>
          <Option value="3" label="社区服务中心">
            <span>社区服务中心</span>
          </Option>
        </Select>
      </Col>
      <Col span="5">
      <div class="layout-head-title" >专&#8195;长</div>
        <Select v-model="queyParams.searchParams.speciality" style="padding-top:5px;width:150px;float:left" placeholder="全部">
          <Option value="1" label="全科">
            <span>全科</span>
          </Option>
          <Option value="2" label="内科">
            <span>内科</span>
          </Option>
          <Option value="3" label="外科">
            <span>外科</span>
          </Option>
          <Option value="3" label="儿科">
            <span>儿科</span>
          </Option>
        </Select>
      </Col>
      <Col span="5">
      <div class="layout-head-title" >状&#8195;态</div>
        <Select v-model="queyParams.searchParams.status" style="padding-top:5px;width:150px;float:left" placeholder="全部">
          <Option value="1" label="已启用">
            <span>已启用</span>
          </Option>
          <Option value="2" label="已启用">
            <span>已注销</span>
          </Option>
        </Select>
      </Col>
      <Col span="8" push="1">
        <div class='layout-head-title'>关键字</div>
        <Input v-model="queyParams.searchParams.keywords"  style="width:150px" placeholder="请输入关键字查询"></Input>
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
      params: 'organization_add',
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
          speciality: '1',
          status: '1',
          keywords: ''
        }
      },
      dataList: [
        {
          name: '陕西省人民医院',
          code: '611042',
          jibie: '三甲',
          person: '张萨',
          phone: '13864576215',
          zhuanchang: '口腔',
          address: '陕西省西安市丈八道51号',
          status: '已启用'
        },
        {
          name: '西安高新医院',
          code: '610012',
          jibie: '三甲',
          person: '李思',
          phone: '15896458547',
          zhuanchang: '泌尿科',
          address: '西安市丈八六路路街道41号',
          status: '已启用'
        },
        {
          name: '西京医院',
          code: '614832',
          jibie: '三甲',
          person: '王尔',
          phone: '18945721847',
          zhuanchang: '耳鼻喉科',
          address: '西安市丈八四路街道22号',
          status: '已注销'
        }
      ],
      columns1: [
        {
          title: '机构名称',
          key: 'name'
        },
        {
          title: '机构代码',
          key: 'code'
        },
        {
          title: '级别',
          key: 'jibie'
        },
        {
          title: '联系人',
          key: 'person'
        },
        {
          title: '电话',
          key: 'phone'
        },
        {
          title: '专长',
          key: 'zhuanchang'
        },
        {
          title: '地址',
          key: 'address'
        },
        {
          title: '状态',
          key: 'status'
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
      this.$router.push({name: 'organization/edit', params: {name: this.dataList[index].person, address: this.dataList[index].address}})
    },
    // 注销
    remove (index) {
      var th = this
      th.$Modal.confirm({
        title: '保健机构信息',
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
  },
  mounted () {
    // window.location.reload()
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