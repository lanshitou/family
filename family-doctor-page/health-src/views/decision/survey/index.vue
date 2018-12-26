<template>
  <div class="layout-body">
    <div class="layout-body-breadcrumb">
    <Breadcrumb>
      <BreadcrumbItem href="object">
        <Icon type="android-contacts"></Icon>满意度调查
      </BreadcrumbItem>
    </Breadcrumb>
  </div>
    <div class="layout-head">
    <Row>
      <Col span="8">
      <div class="layout-head-title" >机&#8195;构</div>
        <Select  style="padding-top:5px;width:150px;float:left" placeholder="全部">
          <Option value="" label="全部">
            <span>全部</span>
            <span style="float:right;color:#ccc"></span>
          </Option>
          <Option value="1" label="保健局">
            <span>保健局</span>
          </Option>
          <Option value="2" label="省中医">
            <span>省中医</span>
          </Option>
          <Option value="3" label="省人医">
            <span>省人医</span>
          </Option>
        </Select>
      </Col>
      <Col span="8" pull="3">
      <div class="layout-head-title" >类&#8195;型</div>
        <Select  style="padding-top:5px;width:150px;float:left" placeholder="全科">
          <Option value="" label="全科">
            <span>全科</span>
          </Option>
          <Option value="2" label="满意度">
            <span>满意度</span>
          </Option>
          <Option value="3" label="医风医德">
            <span>医风医德</span>
          </Option>
          <Option value="3" label="投诉建议">
            <span>投诉建议</span>
          </Option>
        </Select>
      </Col>
      <Col span="8" push="1">
        <div class='layout-head-title'>关键字</div>
        <Input  style="width:150px" placeholder="请输入关键字查询"></Input>
        <Button type="primary" icon="ios-search" style="margin-left:8px" @click="query">查询</Button>
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
  </div>
</template>
<script>
export default {
  data () {
    return {
      params: 'person_add',
      total: 0,
      page: 1,
      rows: 10,
      dataList: [
        {
          name: '赵平',
          jigou: '保健局',
          person: '周莹',
          type: '满意度',
          content: '体检(五星)',
          time: '2017-08-08 10:23'
        },
        {
          name: '张凡',
          jigou: '保健局',
          person: '李星',
          type: '满意度',
          content: '诊察',
          time: '2017-09-01 12:11'
        },
        {
          name: '李然',
          jigou: '保健局',
          person: '李伟',
          type: '满意度',
          content: '体检',
          time: '2017-09-11 15:50'
        },
        {
          name: '王尔',
          jigou: '保健局',
          person: '赵琦',
          type: '满意度',
          content: '体检',
          time: '2017-10-01 00:00'
        }
      ],
      columns1: [
        {
          title: '姓名',
          key: 'name'
        },
        {
          title: '机构',
          key: 'jigou'
        },
        {
          title: '服务人员',
          key: 'person'
        },
        {
          title: '类型',
          key: 'type'
        },
        {
          title: '内容',
          key: 'content'
        },
        {
          title: '发布时间',
          key: 'time'
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
                      this.update()
                    }
                  }
                }),
                h('div', {
                  slot: 'content'
                }, [
                  h('div', [
                    h('div', '回复')
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
    update () {
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
