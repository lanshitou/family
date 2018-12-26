/**
 * @title: 签约服务项目详情
 * @description: 签约服务项目信息详情
 * @author: TiuWeb
*/
<template>
  <div class="index">
    <div style="margin-top:20px;margin-left:50px">
      <div class="layout-content-main">
        <div class='columnBox menu'>
          <div>
            <Form :label-width="150" label-position="right"  :model="updateData" >
              <Row>
                <Col span="10">
                <Form-item label="签约服务项目名称" prop="servicename">
                  <Input readonly v-model="updateData.servicename" placeholder="请输入服务项目名称" style="width:250px"></Input>
                </Form-item>
                </Col>
                <Col span="12" push="1">
                <Form-item label="服务奖励" prop="servicereward">
                  <Input readonly v-model="updateData.servicereward" style="width:80px"></Input>元/次
                </Form-item>
                </Col>
                <Col span="2" push="5">
                  <Button type="primary" style="float:right" @click="back">返回</Button>
                </Col>
              </Row>
              <Row>
                <Col span="10">
                <Form-item label="是否是基础服务">
                  <Radio-group v-model="isfoundationM" >
                    {{isfoundationM === 1 ? '是':'否'}}
                  </Radio-group>
                </Form-item>
                </Col>
                <Col span="14" push="1">
                <Form-item label="服务方式">
                  <Radio-group v-model="updateData.isappointment">
                    {{updateData.isappointment === 0 ? '上门服务':'预约就诊'}}
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
                      <Input readonly v-model="updateData.servicemesure" placeholder="次" style="width:80px;padding-right:10px"></Input>/
                    </Form-item>
                    </Col>
                    <Col span="10">
                    <Form-item prop="periodidentity" style="margin:0px">
                      <Select   v-model="updateData.periodidentity" placeholder="请选择服务计划" style="width:154px">
                        <Option disabled v-for="item in servicePeriodList" :value="item.codeinfovalue" :key="item.codeinfovalue">{{ item.codeinfoname }}</Option>
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
                      <Input readonly v-model="updateData.doctormesure" placeholder="次" style="width:80px"></Input>人/次
                    </Form-item>
                    </Col>
                    <Col span="10">
                    <Form-item prop="itemRole" style="margin:0px">
                      <Select  placeholder="请选择所需角色" v-model="selectRoles" multiple style="width:206px">
                        <Option disabled v-for="item in doctorroles" :value="item.id" :key="item.value">{{ item.rolename }}</Option>
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
                  <Select v-model="updateData.medicalequipmentidentity" placeholder="请选择医疗设备" style="width:250px" >
                    <Option disabled v-for="item in equipment" :value="item.codeinfovalue" :key='item.codeinfovalue'>{{ item.codeinfoname }}</Option>
                  </Select>
                </Form-item>
                </Col>
                <Col span="12" pull="1">
                <Form-item label="预计费用" prop="servicecharge">
                  <Row>
                    <Col span="8">
                    <Form-item style="margin:0px">
                      <Input readonly v-model="updateData.servicecharge" style="width:80px"></Input>元
                    </Form-item>
                    </Col>
                    <Col span="10">
                    <Form-item style="margin:0px">
                      <Select  placeholder="请选择支付频率" style="width:232px" v-model="updateData.servicechargefrequencyidentity">
                        <Option disabled v-for="item in paymentList" :value="item.codeinfovalue" :key="item.codeinfovalue">{{ item.codeinfoname }}</Option>
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
                    <quill-editor disabled="disabled"  v-model="updateData.detail" style="width:730px;height:180px" :options="editorOption">
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
  getAllRoles, findItemByKey, findServicePeriod, findPaymentFrequency, findEquipment
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
      total: 0,
      pageSize: 10,
      itemId: '',
      selectRoles: [],
      doctorroles: [], // 医生所有角色
      servicePeriodList: [], // 计划服务周期
      paymentList: [], // 费用支付频率
      equipment: [], // 医疗设备
      isfoundationM: 0, // 是否是基础服务V-MODEL
      listData: [],
      updateData: {
        servicecategoryid: '', // 服务类别外键
        servicecategoryname: '', // 签约服务项目类别名称
        servicename: '', // 服务名称
        detail: '', // 服务详情
        equipment: 0, // 医疗设备校验所需
        servicemesure: 0, // 服务计划计量
        servicemesureunit: '次', // 服务计划计量单位
        doctormesure: 0, // 所需医疗人员计量
        doctormesureunit: '人/次', // 所需医疗人员计量单位:人/次
        itemRole: [],
        medicalequipmentidentity: '', // 医疗设备外键
        medicalequipmentname: '随诊箱', // 医疗设备名称
        servicecharge: '', // 预计费用
        servicechargeunit: '元', // 预计费用单位:元
        servicechargefrequencyidentity: '', // 费用支付频率外键
        chargefrequencyname: '', // 计费频率名称
        servicereward: 0, // 服务奖励
        isfoundation: true, // 是否是基础服务
        isappointment: 0, // 服务方式
        periodidentity: '', // 服务计划周期外键:0每年,1每月,2每周
        peroidname: '' // 服务周期名称
      }
    }
  },
  created: function () {
    this.itemId = this.$route.path.substring(13) // 截取url后的参数
    this.$Loading.start()
    getAllRoles().then((res) => {
      this.$Loading.finish()
      this.doctorroles = res.data
    })
    let para = {
      id: this.itemId
    }
    this.$Loading.start()
    findItemByKey(para).then((res) => { // 根据id查询项目
      this.$Loading.finish()
      this.updateData = res.data
      if (res.data.isfoundation) {
        this.isfoundationM = 1
      } else {
        this.isfoundationM = 0
      }
      var change = res.data.itemRole
      change.forEach(function (element) {
        this.selectRoles.push(
          element.medicalteamroleidentity
        )
      }, this)
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
    // 返回
    back () {
      this.$router.push({ path: 'items' })
    }
  }
}
</script>
<style scoped lang="less">
.index {
  height: 90%;
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
}ss
.editor {
  width: 400px;
  height: 220px;
}
</style>
