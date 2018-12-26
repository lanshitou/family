/**
 * @Title : 疾病趋势分析
 * @Description :
 * @author : TiuWeb
 */
<template>
  <div style="height:100%;overflow:auto">
    <div>
      <Row>
        <Col span="1" style="margin-top:5px">姓名</Col>
        <Col span="2"><Input placeholder="请输入..." ></Input></Col>
        <Col span="1" style="margin-top:5px">职级</Col>
        <Col span="4">
          <Select v-model="model1" style="width:150px">
            <Option v-for="item in modellist1" :value="item.value" :key="item.value">{{ item.label }}</Option>
          </Select>
        </Col>
        <Col span="1" style="margin-top:5px">类型</Col>
        <Col span="4">
          <Select v-model="model2" style="width:150px">
            <Option v-for="item in modellist2" :value="item.value" :key="item.value">{{ item.label }}</Option>
          </Select>
        </Col>
        </Col>
        <Col span="2">
          <Button type="primary" icon="ios-search" style="margin-left:8px">查询</Button>
        </Col>
      </Row>
      <div style="margin-top:30px">
       <Table stripe  :columns="columns1" :data="data1"></Table>
        <div class="pages-block">
        <div class="pages-right">
          <Page :total="total" :page-size="rows" @on-page-size-change="doPageSizeChange" @on-change="change" show-sizer show-total
          ref="tablePage" show-elevator></Page>
        </div>
      </div>
      </div>
    </div>
    <Modal v-model="modelplan" title="新增随访计划" @on-ok="ok" @on-cancel="cancel">
      <Form :label-width="130" >
        <Row>
          <Col span="12">
            <Form-item label="保健卡号" style="margin:0px" >
              {{card}}
            </Form-item>
          </Col>
          <Col span="12">
            <Form-item label="姓名" style="margin:0px" >
             {{name}}
            </Form-item>
          </Col>
        </Row>
        <Row>
          <Col span="12">
            <Form-item label="单位" style="margin:0px" >
              {{company}}
            </Form-item>
          </Col>
          <Col span="12">
            <Form-item label="职级" style="margin:0px" >
            {{zhiji}}
            </Form-item>        
          </Col>
        </Row>
        <Row>
          <Col span="12">
            <Form-item label="职称" style="margin:0px"  >
              {{zhicheng}}
            </Form-item>
          </Col>
          <Col span="12" >
            <Form-item label="随访类型" style="margin:0px" >
              {{type}}
            </Form-item>  
          </Col>
        </Row>
        <Form-item label="随访方式" style="margin:0px" >
          <RadioGroup v-model="radio">
            <Radio label="短信">短信</Radio>
            <Radio label="电话">电话</Radio>
          </RadioGroup>
        </Form-item>
        <Form-item label="计划随访时间" style="margin:0px" >
         <DatePicker type="date" placeholder="请选择日期" placement="top" style="width: 200px"></DatePicker>
        </Form-item>
        <Form-item label="备注" style="margin:0px" >
        <Input type="textarea" :rows="4" placeholder="请输入..."></Input>
        </Form-item>
      </Form>
    </Modal>
    <Modal v-model="modelsee" title="查看随访计划" @on-ok="ok">
      <Form :label-width="130" >
        <Row>
          <Col span="12">
            <Form-item label="保健卡号" style="margin:0px" >
              {{card}}
            </Form-item>
          </Col>
          <Col span="12">
            <Form-item label="姓名" style="margin:0px" >
             {{name}}
            </Form-item>
          </Col>
        </Row>
        <Row>
          <Col span="12">
            <Form-item label="单位" style="margin:0px" >
              {{company}}
            </Form-item>
          </Col>
          <Col span="12">
            <Form-item label="职级" style="margin:0px" >
            {{zhiji}}
            </Form-item>        
          </Col>
        </Row>
        <Row>
          <Col span="12">
            <Form-item label="职称" style="margin:0px"  >
              {{zhicheng}}
            </Form-item>
          </Col>
          <Col span="12" >
            <Form-item label="随访类型" style="margin:0px" >
              {{type}}
            </Form-item>  
          </Col>
        </Row>
        <Form-item label="随访方式" style="margin:0px" >
          <RadioGroup v-model="radio">
            <Radio label="短信">短信</Radio>
            <Radio label="电话">电话</Radio>
          </RadioGroup>
        </Form-item>
        <Form-item label="计划随访时间" style="margin:0px" >
          {{time1}}
         </Form-item>
        <Form-item label="备注" style="margin:0px" >
          {{beizhu1}}
        </Form-item>
      </Form>
      <div slot="footer" >
        <Button type="primary" @click="cancel">关闭</Button>
      </div>
    </Modal>
    <Modal v-model="modeledit" title="编辑随访计划" @on-ok="ok" @on-cancel="cancel">
      <Form :label-width="130" >
        <Row>
          <Col span="12">
            <Form-item label="保健卡号" style="margin:0px" >
              {{card}}
            </Form-item>
          </Col>
          <Col span="12">
            <Form-item label="姓名" style="margin:0px" >
             {{name}}
            </Form-item>
          </Col>
        </Row>
        <Row>
          <Col span="12">
            <Form-item label="单位" style="margin:0px" >
              {{company}}
            </Form-item>
          </Col>
          <Col span="12">
            <Form-item label="职级" style="margin:0px" >
            {{zhiji}}
            </Form-item>        
          </Col>
        </Row>
        <Row>
          <Col span="12">
            <Form-item label="职称" style="margin:0px"  >
              {{zhicheng}}
            </Form-item>
          </Col>
          <Col span="12" >
            <Form-item label="随访类型" style="margin:0px" >
              {{type}}
            </Form-item>  
          </Col>
        </Row>
        <Form-item label="随访方式" style="margin:0px" >
          <RadioGroup v-model="radio">
            <Radio label="短信">短信</Radio>
            <Radio label="电话">电话</Radio>
          </RadioGroup>
        </Form-item>
        <Form-item label="计划随访时间" style="margin:0px" >
         <DatePicker v-model="time1" type="date" placeholder="请选择日期" placement="top" style="width: 200px"></DatePicker>
        </Form-item>
        <Form-item label="备注" style="margin:0px" >
        <Input v-model="beizhu1" type="textarea" :rows="4" placeholder="请输入..."></Input>
        </Form-item>
      </Form>
    </Modal>
  </div>
</template>
<script>
export default {
  data () {
    return {
      card: '10000',
      name: '',
      company: '',
      zhiji: '',
      zhicheng: '',
      type: '',
      radio: '短信',
      total: 0,
      page: 1,
      rows: 10,
      time1: '2017-11-02 10:45',
      beizhu1: '随访详情',
      modelplan: false,
      modelsee: false,
      modeledit: false,
      modellist1: [
        {
          value: '全部',
          label: '全部'
        },
        {
          value: '省级',
          label: '省级'
        },
        {
          value: '厅级',
          label: '厅级'
        }
      ],
      model1: '全部',
      modellist2: [
        {
          value: '已读',
          label: '已读'
        },
        {
          value: '未读',
          label: '未读'
        }
      ],
      model2: '已读',
      columns1: [
        {
          title: '姓名',
          key: 'name'
        },
        {
          title: '单位',
          key: 'address'
        },
        {
          title: '职级',
          key: 'zhiji'
        },
        {
          title: '职称',
          key: 'zhichen'
        },
        {
          title: '随访类型',
          key: 'suifang'
        },
        {
          title: '随访方式',
          key: 'fangshi'
        },
        {
          title: '计划时间',
          key: 'time'
        },
        {
          title: '操作',
          key: 'action',
          width: '180',
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
                    icon: 'clipboard'
                  },
                  style: {
                    marginLeft: '5px'
                  },
                  on: {
                    click: () => {
                      this.plan(params.index)
                    }
                  }
                }),
                h('div', {
                  slot: 'content'
                }, [
                  h('div', [
                    h('div', '计划')
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
                      this.see(params.index)
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
      ],
      data1: [
        {
          name: '赵平',
          address: '省保健局',
          zhiji: '会计',
          zhichen: '注册会计',
          suifang: '住院',
          fangshi: '电话',
          time: '2016-10-03'
        },
        {
          name: '张凡',
          address: '省保健局',
          zhiji: '财务',
          zhichen: '中级',
          suifang: '住院',
          fangshi: '电话',
          time: '2016-12-03'
        },
        {
          name: '张起',
          address: '省保健局',
          zhiji: '人事',
          zhichen: '高级',
          suifang: '住院',
          fangshi: '短信',
          time: '2017-10-03'
        },
        {
          name: '李尔',
          address: '省保健局',
          zhiji: '秘书',
          zhichen: '中级',
          suifang: '住院',
          fangshi: '电话',
          time: '2017-04-03'
        }
      ]
    }
  },
  methods: {
    ok () {
      this.$Message.info('Clicked ok')
    },
    cancel () {
      this.modelplan = false
      this.modelsee = false
      this.modeledit = false
    },
    plan (index) {
      // console.log(index)
      this.name = this.data1[index].name
      this.company = this.data1[index].address
      this.zhiji = this.data1[index].zhiji
      this.zhicheng = this.data1[index].zhichen
      this.type = this.data1[index].fangshi
      this.modelplan = true
    },
    see (index) {
      this.name = this.data1[index].name
      this.company = this.data1[index].address
      this.zhiji = this.data1[index].zhiji
      this.zhicheng = this.data1[index].zhichen
      this.type = this.data1[index].fangshi
      this.modelsee = true
    },
    update (index) {
      this.name = this.data1[index].name
      this.company = this.data1[index].address
      this.zhiji = this.data1[index].zhiji
      this.zhicheng = this.data1[index].zhichen
      this.type = this.data1[index].fangshi
      this.modeledit = true
    },
    // 删除
    remove (index) {
      var th = this
      th.$Modal.confirm({
        title: '随访计划信息',
        content: `是否删除此记录`,
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
