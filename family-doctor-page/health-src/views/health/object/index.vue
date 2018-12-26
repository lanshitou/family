<template>
  <div class="layout-body">
  <div class="layout-body-breadcrumb">
    <Breadcrumb>
      <BreadcrumbItem href="object">
        <Icon type="ios-people"></Icon> 保健对象
      </BreadcrumbItem>
    </Breadcrumb>
  </div>
    <div class="layout-head">
    <Row>
      <Col span="8">
      <div class="layout-head-title" >机&#8195;构</div>
        <Select v-model="queyParams.searchParams.org" style="padding-top:5px;width:150px;float:left" placeholder="全部">
          <Option value="0" label="人大">
            <span>人大</span>
            <span style="float:right;color:#ccc"></span>
          </Option>
          <Option value="1" label="省委">
            <span>省委</span>
          </Option>
          <Option value="2" label="省政委">
            <span>省政委</span>
          </Option>
          <Option value="3" label="财政厅">
            <span>财政厅</span>
          </Option>
          <Option value="4" label="卫计委">
            <span>卫计委</span>
          </Option>
        </Select>
      </Col>
      <Col span="8" pull="3">
      <div class="layout-head-title" >状&#8195;态</div>
        <Select v-model="queyParams.searchParams.status"  style="padding-top:5px;width:150px;float:left" placeholder="全部">
          <Option value="1" label="全部">
            <span>全部</span>
          </Option>
          <Option value="2" label="生效">
            <span>生效</span>
          </Option>
          <Option value="3" label="封存">
            <span>封存</span>
          </Option>
        </Select>
      </Col>
      <Col span="8" push="1">
        <div class='layout-head-title'>关键字</div>
        <Input v-model="queyParams.searchParams.keyworks" style="width:150px" placeholder="请输入关键字查询"></Input>
        <Button icon="ios-search" style="background:#5e87b0;color:#fff;margin-left:8px" @click="query">查询</Button>
      </Col>
    </Row>
    </div>
    <div class="layout-content">
      <Table border :columns="columns" :data="dataList"></Table>
        <div class="pages-block">
        <div class="pages-right">
          <Page :total="total" :page-size="rows" @on-page-size-change="doPageSizeChange" @on-change="change" show-sizer show-total
          ref="tablePage" show-elevator></Page>
        </div>
      </div>
      <Modal v-model="accreditModel" width="360">
        <p slot="header" style="color:#f60;text-align:center">
            <Icon type="information-circled"></Icon>
            <span>授权确认</span>
        </p>
        <div style="text-align:center">
          <Form :model="accreditForm">
             <Form-item label="保健对象姓名：" style="margin:0px">
              <Row type="flex" justify="start">
                <Col>{{accreditForm.name}}</Col>
              </Row>
             </Form-item>
          </Form>
          <Form ref="formDynamic" :model="formDynamic" :label-width="80">
        <FormItem
            v-for="(item, index) in formDynamic.items"
            :key="index"
            :label="'服务人员 ' + (index + 1)"
            :prop="'items.' + index + '.value'"
            :rules="{required: true, message: '服务人员 ' + (index + 1) +' 不能为空', trigger: 'blur'}">
            <Row>
                <Col span="10">
                  <Select v-model="item.value" placeholder="请选择">
                    <Option value="省保健局">省保健局</Option>
                    <Option value="交大一院">交大一院</Option>
                    <Option value="交大二院">交大二院</Option>
                    <Option value="省人民医院">省人民医院</Option>
                  </Select>
                </Col>
                <Col span="10">
                  <Select  v-model="item.value2" placeholder="请选择">
                    <Option value="张三">张三</Option>
                    <Option value="李四">李四</Option>
                    <Option value="王二">王二</Option>
                    <Option value="老赵">老赵</Option>
                  </Select>
                </Col>
                <Col span="3">
                  <Button type="ghost" @click="handleRemove(index)">删除</Button>
                </Col>
            </Row>
        </FormItem>
        <FormItem>
          <Row>
            <Col span="12">
              <Button type="dashed" long @click="handleAdd" icon="plus-round">增加</Button>
            </Col>
          </Row>
        </FormItem>
    </Form>
        </div>
        <div slot="footer" style="text-align:center">
          <Button style="background:#5e87b0;color:#fff;">确认</Button>
          <Button style="background:#5e87b0;color:#fff;" @click="cancel()">取消</Button>
        </div>
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
      params: 'object_add',
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
          status: '1',
          keywords: ''
        }
      },
      dataList: [
        {
          name: '张萨',
          idcard: '610425198504251124',
          jibie: '副省',
          sex: '男',
          phone: '029-23997400',
          depart: '人大',
          address: '西安市锦业路',
          status: '封存'
        },
        {
          name: '李思',
          idcard: '610425198804251124',
          jibie: '省级',
          sex: '男',
          phone: '029-2376004',
          depart: '省保健局',
          address: '西安市丈八街道',
          status: '封存'
        }
      ],
      accreditModel: false, // 授权弹出框
      accreditForm: {
        name: '张三'
      }, // 授权form
      formDynamic: {
        items: [
          {
            value: '省保健局',
            value2: '李四'
          }
        ]
      },
      columns: [
        {
          title: '姓名',
          key: 'name'
        },
        {
          title: '身份证',
          key: 'idcard'
        },
        {
          title: '级别',
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
          title: '状态',
          key: 'status'
        },
        {
          title: '操作',
          key: 'action',
          width: '160',
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
                    icon: 'social-buffer'
                  },
                  style: {
                    marginLeft: '5px'
                  },
                  on: {
                    click: () => {
                      this.sealupforsafekeeping()
                    }
                  }
                }),
                h('div', {
                  slot: 'content'
                }, [
                  h('div', [
                    h('div', '封存')
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
                    icon: 'close-circled'
                  },
                  style: {
                    marginLeft: '5px'
                  },
                  on: {
                    click: () => {
                      this.deblocking()
                    }
                  }
                }),
                h('div', {
                  slot: 'content'
                }, [
                  h('div', [
                    h('div', '解封')
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
                    icon: 'ios-search'
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
                    icon: 'person'
                  },
                  style: {
                    marginLeft: '5px'
                  },
                  on: {
                    click: () => {
                      this.accredit(params.index)
                    }
                  }
                }),
                h('div', {
                  slot: 'content'
                }, [
                  h('div', [
                    h('div', '授权')
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
    handleAdd () {
      this.formDynamic.items.push({
        value: '',
        value2: ''
      })
    },
    handleRemove (index) {
      this.formDynamic.items.splice(index, 1)
    },
    // 查询
    query: function () {
      alert('11111111')
    },
    // 修改
    update (index) {
      this.$router.push({name: 'object/edit', params: {name: this.dataList[index].name}})
    },
    // 封存
    sealupforsafekeeping: function () {
      this.$Modal.confirm({
        title: '保健对象信息',
        content: `是否封存此记录`,
        onOk: function () {
          this.$Message.success('封存成功!')
        },
        onCancel: function () {
          this.$Message.success('取消')
        }
      })
    },
    // 解封
    deblocking: function () {
      this.$Modal.confirm({
        title: '保健对象信息',
        content: `是否解封此记录`,
        onOk: function () {
          this.$Message.success('解封成功!')
        },
        onCancel: function () {
          this.$Message.success('取消')
        }
      })
    },
    // 授权
    accredit: function (index) {
      this.accreditForm.name = this.dataList[index].name
      this.accreditModel = true
    },
    // 授权取消
    cancel: function () {
      this.accreditModel = false
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
  text-align:left;
  margin-left:10px;
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