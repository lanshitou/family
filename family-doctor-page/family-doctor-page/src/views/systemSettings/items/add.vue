/**
 * @title: 签约服务项目新增
 * @description: 新增签约服务项目信息
 * @author: TiuWeb
*/
<template>
  <div class="index">
    <div style="margin-top:20px;margin-left:50px">
      <div class="layout-content-main">
        <div class='columnBox menu'>
          <div>
            <Form :label-width="150" label-position="right" ref="formItem" :model="formItem" :rules="formValidate">
              <Row>
                <Col span="12">
                  <Form-item label="签约服务项目名称" prop="servicename">
                    <Input v-model="formItem.servicename" placeholder="请输入服务项目名称" style="width:250px"></Input>
                  </Form-item>
                </Col> 
                <Col span="12" pull="1">
                  <Form-item label="服务奖励" prop="servicereward">
                    <Input v-model="formItem.servicereward" style="width:80px"></Input>元/次
                  </Form-item>
                </Col>
              </Row>
              <Row>
                <Col span="10">
                  <Form-item label="是否是基础服务">
                    <Radio-group v-model="isfoundationM">
                      <Radio label="1">是</Radio>
                      <Radio label="0">否</Radio>
                    </Radio-group>
                  </Form-item>
                </Col>
                <Col span="14" push="1">
                  <Form-item label="服务方式">
                    <Radio-group v-model="formItem.isappointment">
                      <Radio label="0">上门服务</Radio>
                      <Radio label="1">预约就诊</Radio>
                    </Radio-group>
                  </Form-item>
                </Col>
              </Row>
              <Row>
                <Col span="12">
                <Form-item label="服务计划" prop="servicemesure">
                  <Row>
                    <Col span="8">
                      <Form-item style="margin:0px">
                        <Input v-model="formItem.servicemesure" placeholder="次" style="width:80px;padding-right:10px"></Input>/
                      </Form-item>
                    </Col>
                    <Col span="10">
                      <Form-item prop="periodidentity" style="margin:0px">
                        <Select v-model="formItem.periodidentity" placeholder="请选择服务计划" style="width:154px" @on-change="changePlan" :label-in-value="true">
                          <Option v-for="item in servicePeriodList" :value="item.codeinfovalue" :key="item.codeinfovalue">{{ item.codeinfoname }}</Option>
                        </Select>
                      </Form-item>
                    </Col>
                  </Row>
                </Form-item>
                </Col>
                <Col span="12" pull="1">
                  <Form-item label="所需医疗人员" prop="doctormesure">
                    <Row>
                      <Col span="10">
                        <Form-item style="margin:0px">
                          <Input v-model="formItem.doctormesure" placeholder="次" style="width:80px"></Input>人/次
                        </Form-item>
                      </Col>
                      <Col span="10">
                        <Form-item prop="itemRole" style="margin:0px">
                          <Select placeholder="请选择所需角色" v-model="selectRoles" multiple style="width:206px" @on-change="changeR" :label-in-value="true">
                            <Option v-for="item in doctorroles" :value="item.id" :key="item.value">{{ item.rolename }}</Option>
                          </Select>
                        </Form-item>
                      </Col>
                    </Row>
                  </Form-item>
                </Col>
              </Row>
              <Row>
                <Col span="12">
                  <Form-item label="所需医疗设备" prop="medicalequipmentidentity">
                    <Select v-model="formItem.medicalequipmentidentity" placeholder="请选择医疗设备" style="width:250px" @on-change="changeE" :label-in-value="true">
                      <Option v-for="item in equipment" :value="item.codeinfovalue" :key='item.codeinfovalue'>{{ item.codeinfoname }}</Option>
                    </Select>
                  </Form-item>
                </Col>
                <Col span="12" pull="1">
                  <Form-item label="预计费用" prop="servicecharge">
                    <Row>
                      <Col span="8">
                        <Form-item style="margin:0px">
                          <Input v-model="formItem.servicecharge" style="width:80px"></Input>元
                        </Form-item>
                      </Col>
                      <Col span="10">
                        <Form-item style="margin:0px">
                          <Select placeholder="请选择支付频率" style="width:232px" v-model="formItem.servicechargefrequencyidentity" @on-change="changePayment" :label-in-value="true">
                            <Option v-for="item in paymentList" :value="item.codeinfovalue" :key="item.codeinfovalue">{{ item.codeinfoname }}</Option>
                          </Select>
                        </Form-item>
                      </Col>
                    </Row>
                  </Form-item>
                </Col>
              </Row>
              <Row>
                <Col span="24">
                  <Form-item label="签约服务项目详细内容" prop="detail">
                    <div class="editor">
                      <quill-editor v-model="formItem.detail" style="width:730px;height:180px" :options="editorOption" >
                        <div id="toolbar" slot="toolbar">
                          <button class="ql-bold">Bold</button>
                          <button class="ql-italic">Italic</button>
                          <select class="ql-size">
                            <option value="small"></option>
                            <option selected></option>
                            <option value="large"></option>
                            <option value="huge"></option>
                          </select>
                          <button class="ql-script" value="sub"></button>
                          <button class="ql-script" value="super"></button>
                        </div>
                      </quill-editor>
                    </div>
                  </Form-item>
                </Col>
              </Row>
              <Form-item style="margin:10px">
                <Button type="primary" @click="saveForm('formItem')">提交</Button>
                <Button type="primary" style="margin-left: 8px" @click="back">返回</Button>
              </Form-item>
            </Form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import {
  quillEditor
} from 'vue-quill-editor'
import {
  getAllRoles, findItemByCon, insertItem, findServicePeriod, findPaymentFrequency, findEquipment
} from '../../../api/api'
export default {
  components: {
    quillEditor
  },
  data () {
    return {
      name: 'custom-toolbar-example',
      editorOption: {
        modules: {
          toolbar: '#toolbar'
        }
      },
      itemsId: 0,
      selectRoles: [],
      doctorroles: [], // 医生所有角色
      servicePeriodList: [], // 计划服务周期
      paymentList: [], // 费用支付频率
      equipment: [], // 医疗设备
      isfoundationM: 1, // 是否是基础服务V-MODEL
      formItem: {
        servicecategoryid: '', // 服务类别外键
        servicecategoryname: '', // 签约服务项目类别名称
        servicename: '', // 服务名称
        payment: 0, // 费用支付频率校验所需
        equipment: 0, // 医疗设备校验所需
        detail: '', // 服务详情
        servicemesure: 0, // 服务计划计量
        servicemesureunit: '次', // 服务计划计量单位
        doctormesure: 0, // 所需医疗人员计量
        doctormesureunit: '人/次', // 所需医疗人员计量单位:人/次
        itemRole: [],
        medicalequipmentidentity: '', // 医疗设备外键
        medicalequipmentname: '', // 医疗设备名称
        servicecharge: '', // 预计费用
        servicechargeunit: '元', // 预计费用单位:元
        servicechargefrequencyidentity: '', // 费用支付频率外键
        chargefrequencyname: '', // 计费频率名称
        servicereward: 0, // 服务奖励
        isfoundation: 0, // 是否是基础服务
        isappointment: 0, // 服务方式
        periodidentity: '', // 服务计划周期外键:0每年,1每月,2每周
        peroidname: '' // 服务周期名称
      },
      // 校验
      formValidate: {
        servicename: [
          { required: true, message: '服务名称不能为空', trigger: 'blur' },
          { type: 'string', max: 25, message: '服务名称不能超过50个字符', trigger: 'blur' }
        ],
        detail: [
          { required: true, message: '项目详细内容不能为空', trigger: 'blur' }
        ],
        servicemesure: [
          { required: true, message: '服务计划不能为空', trigger: 'blur' }
        ],
        periodidentity: [
          { required: true, message: '计划周期不能为空', trigger: 'blur' }
        ],
        itemRole: [
          { required: true, type: 'array', min: 1, message: '至少选择一个角色', trigger: 'change' }
        ],
        doctormesure: [
          { required: true, message: '所需医疗人员不能为空', trigger: 'blur' }
        ],
        payment: [
          { required: true, type: 'number', message: '支付频率不能为空', trigger: 'blur' }
        ],
        medicalequipmentidentity: [
          { required: true, message: '医疗设备不能为空', trigger: 'change' }
        ],
        servicecharge: [
          { required: true, message: '预计费用不能为空', trigger: 'blur' }
        ],
        servicereward: [
          { required: true, message: '服务奖励不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  created: function () {
    this.itemsId = this.$route.path.substring(10) // 截取url后的参数
    this.$Loading.start()
    getAllRoles().then((res) => {
      this.$Loading.finish()
      this.doctorroles = res.data
    })
    let para = {
      servicecategoryid: this.itemId
    }
    this.$Loading.start()
    findItemByCon(para).then((res) => { // 根据id查询项目
      this.formItem.servicecategoryid = this.itemId
      this.formItem.servicecategoryname = res.data[0].servicecategoryname
    })
    // 查询服务计划周期
    findServicePeriod().then((res) => {
      this.servicePeriodList = res.data
    })
    // 查询费用支付频率信息
    findPaymentFrequency().then((res) => {
      this.paymentList = res.data
    })
    // 查询医疗设备信息
    findEquipment().then((res) => {
      this.equipment = res.data
    })
  },
  methods: {
    onEditorBlur (editor) {
      // console.log('editor blur!', editor)
    },
    onEditorFocus (editor) {
      // console.log('editor focus!', editor)
    },
    onEditorReady (editor) {
      // console.log('editor ready!', editor)
    },
    customButtonClick () {
      alert(`You can custom the button and listen click event to do something...\n你可以定义一些自定义按钮做你想做的事，如上传图片至第三方存储...等等`)
    },
    // 返回
    back () {
      this.$router.push({ path: 'items' })
    },
    // 角色select选择器选中的Option变化时触发
    changeR (change) {
      var tempRoles = []
      change.forEach(function (element) {
        tempRoles.push({
          rolename: element.label,
          medicalteamroleidentity: element.value
        })
      }, this)
      this.formItem.itemRole = tempRoles
    },
    // 计划服务周期select选择器选中的Option变化时触发
    changePlan (change) {
      this.formItem.peroidname = change.label
    },
    // 支付频率select选择器选中的Option变化时触发
    changePayment (change) {
      this.formItem.chargefrequencyname = change.label
    },
    // 医疗设备select选择器选中的Option变化时触发
    changeE (change) {
      this.formItem.medicalequipmentname = change.label
    },
    // 保存数据
    saveForm (formItem) {
      this.$refs['formItem'].validate((valid) => {
        if (valid) {
          if (this.isfoundationM === 0) {
            this.formItem.isfoundation = false
          } else {
            this.formItem.isfoundation = true
          }
          this.formItem.servicecategoryid = this.itemsId
          insertItem(this.formItem).then((res) => {
            if (res.status === 200) {
              this.$Message.success('添加成功!')
              this.$router.push({ path: '/items' })
            } else {
              this.$Message.error('添加失败!')
            }
          })
        } else {
          this.$Message.error('签约服务项目信息验证失败!')
        }
      })
    }
  }
}
</script>
<style scoped lang="less">
.index {
  height: 80%;
  overflow: auto;
}

.filterTitle {
  font-size: 14px;
  color: #1c2438;
  padding: 10px 12px;
}

.menu {
  flex-direction: row;
  align-items: center;
}

.columnBox {
  display: -webkit-flex;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
}

.pages-block {
  margin: 0px;
  overflow: hidden;
  margin-top: 10px;
  margin-bottom: 10px;
  text-align: center;
  .pages-right {
    vertical-align: middle;
  }
}

.layout-content-main {
  padding: 5px;
}

.editor {
  width: 400px;
  height: 220px;
}
</style>
