<template>
  <div>
    <div style=" height:90%;overflow:auto;">
        <Form ref="formModel" :model="formModel" :label-width="130">
          <fieldset class="divcss">
          <legend style="font-weight:bold;font-size:15px;padding: 1px;text-align:left">
            申请信息
          </legend>
          <Row>
            <Col span="6">
              <Form-item label="申请人姓名"  prop="applyName">
                {{formModel.applyname}}
              </Form-item>
            </Col>
            <Col span="6">
              <Form-item label="年龄"  prop="age">
                {{formModel.age}}
              </Form-item>
            </Col>
            <Col span="6">
            <Form-item label="身份证号码"  prop="identCard">
              {{formModel.identitynumber}}
            </Form-item>
            </Col>
            <Col span="6">
              <Form-item label="性别"  prop="age">
                {{formModel.sex === 0 ? '未知性别' : formModel.sex === 1 ? '男' : formModel.sex === 2 ? '女' : '未说明性别'}}
              </Form-item>
            </Col>
          </Row>
          <Row>
            <Col span="6">
            <Form-item label="家庭档案号" prop="residentfamilynumber">
              {{formModel.residentfamilynumber}}
            </Form-item>
            </Col>
            <Col span="6">
            <Form-item label="联系电话" prop="telephone">
              <Input v-model="formModel.telephone" placeholder="联系电话" ></Input>
            </Form-item>
            </Col>
            <Col span="10">
            <Form-item label="家庭地址" prop="address">
              <Input v-model="formModel.address" placeholder="家庭地址" ></Input>
            </Form-item>
            </Col>
          </Row>
          </fieldset>
        </Form>
      <Form ref="servicesModel" :model="servicesModel" :label-width="130">
          <fieldset class="divcss">
          <legend style="font-weight:bold;font-size:15px;padding: 1px;text-align:left">
            签约明细
          </legend>
          <Row>
            <Col span="6">
            <Form-item label="签约单号" prop="contactnumber">
              {{formModel.contactnumber}}
            </Form-item>
            </Col>
            <Col span="6">
            <Form-item label="申请日期" prop="applyDate">
              {{tblResidentContactRecord.applydate}}
            </Form-item>
            </Col>
            <Col span="6">
            <Form-item label="签约生效日期">
              {{tblResidentContactRecord.enabledate}}
            </Form-item>
            </Col>
            <Col span="6">
            <Form-item label="医疗机构" prop="institutionname">
              {{formModel.institutionname}}
            </Form-item>
            </Col>
          </Row>
          <Row>
            <Col span="12">
            <Form-item  label="医生团队" prop="teamname">
              <Span v-for="item in formModel.tblFamilyDoctorTeamRoles"> {{item.doctorname}}&nbsp;&nbsp; </Span>
            </Form-item>
            </Col>

            <Col span="6">
            <Form-item label="签约类型">
              {{formModel.signtype === 0 ? '个人' : '家庭'}}
            </Form-item>
            </Col>
            <Col span="6">
            <Form-item label="签约状态">
              {{formModel.applystatus === 0 ? '待审批' : formModel.applystatus === 1 ? '已生效' : formModel.applystatus === 2 ? '到期结束' : formModel.applystatus === 3 ? '提前结束' : ''}}
            </Form-item>
            </Col>
          </Row>
          </fieldset>
        </Form>
        <Form ref="servicesModel" :model="servicesModel" :label-width="130">
            <table  class="gridtable" :columns="serviceColumn" :data="serviceItems">
              <tr>
                <th v-for="items in serviceColumn">{{items.title}}</th>
              </tr>
              <tr v-for="(items, index) in serviceItems">
                  <td  v-if="items.ishebing !== '1' " :rowspan="items.rowNum" style="border-width: 1px;">
                     <!-- 类别 -->
                         {{items.servicecategoryname}}
                    </td>
                      <!-- 项目 -->
                    <td class="tdheigth">
                      {{items.servicename}}
                    </td>
                    <!-- 服务计划 -->
                    <td>
                      {{items.servicemesure}}{{items.servicemesureunit}}/
                      {{items.serviceperiodidentity === '0' ? '每年' : items.serviceperiodidentity === '1' ? '每月' : items.serviceperiodidentity === '2' ? '每周' : ''}}
                      , {{items.isappointment === 0 ? '上门服务' : items.isappointment === 1 ? '需预约' : ''}}
                    </td>
                    <td>
                      <p v-if="items.isappointment === 0 && formModel.applystatus === 0">
                        <Date-picker  v-model="items.firstservicedate" format="yyyy-MM-dd HH:mm" type="datetime" placeholder="请选择首次服务时间" style="width: 160px"></Date-picker>
                      </p>
                      <p v-if="items.isappointment === 0  && formModel.applystatus === 1">
                        {{items.firstservicedate}}
                      </p>
                    </td>
                    <!-- 所需医疗人员 -->
                    <td>
                      {{items.doctormesure}}{{items.doctormesureunit}}
                    </td>
                    <!-- 医疗设备 -->
                    <td>
                      {{items.medicalequipmentname}}
                    </td>
                    <!-- 预计收费 -->
                    <td>
                      {{items.servicecharge}}{{items.servicechargeunit}}/{{items.chargefrequencyname}}
                    </td>
                    <!-- 显示服务对象名称 -->
                    <td v-if="items.customercategory === 2"  >
                      <Span v-for="item in items.customer"> {{item.membername}}&nbsp;&nbsp; </Span>
                    </td>
                    <!-- 显示服务对象 -->
                    <td v-if="items.customercategory !== 2">
                      {{items.customercategory === 0 ? '全部家庭成员' : items.customercategory === 1 ? '仅本人' : ''}}
                    </td>
              </tr>
            </table>
      </Form>
      <Row type="flex" justify="center">
          <Button type="primary" @click="handleSave()"  style="margin:18px">保存</Button>
          <Button type="primary" @click="handleReturn()"  style="margin:18px">返回</Button>
      </Row>
    </div>
  <!--医生确认弹框1-->
    <Modal v-model="modal1" title="修改签约协议信息" @on-ok="saveProtocol" @on-cancel="cancelProtocol">
      <div class='filterTitle'>是否保存?</div>
    </Modal>
  </div>
</template>
<script>
import {
    findContactByKey, updateContactByKey
} from '../../api/api'
export default {
  data () {
    return {
      modal1: false,
      single: false,
      serviceColumn: [
        {
          title: '签约服务类别',
          key: 'type'
        },
        {
          title: '签约服务项目',
          key: 'item'
        },
        {
          title: '服务计划',
          key: 'plan'
        },
        {
          title: '首次服务时间'
        },
        {
          title: '所需医疗人员',
          key: 'doctors'
        },
        {
          title: '医疗设备',
          key: 'advice'
        },
        {
          title: '预计收费',
          key: 'fee'
        },
        {
          title: '服务对象',
          key: 'target'
        }
      ],
      serviceItems: [
      ],
      servicesModel: {
        contactnumber: '',
        applyInstitutions: '',
        applyDoctorTeam: '',
        applySignType: '1',
        applyNo: '',
        applyDate: '',
        applySignDate: ''
      },
      // 申请信息
      formModel: {
      },
      // 签约明细
      tblResidentContactRecord: {
      },
      // 签约服务内容
      services: [],
      // 保存model
      insertModel: {
      }
    }
  },
  created () {
    // console.log(this.$route.params.id)
    let para = {
      id: this.$route.params.id
    }
    this.$Loading.start()
    findContactByKey(para).then((res) => {
      this.$Loading.finish()
      this.formModel = res.data
      this.tblResidentContactRecord = res.data.tblResidentContactRecord
      if (this.tblResidentContactRecord.applydate !== null) {
        this.tblResidentContactRecord.applydate = this.renderDate(this.tblResidentContactRecord.applydate)
      }
      if (this.tblResidentContactRecord.enabledate !== null) {
        this.tblResidentContactRecord.enabledate = this.renderDate(this.tblResidentContactRecord.enabledate)
      }
      // console.log(res.data)

      // 将查出的数据排序
      var orderdata = {}
      if (res !== null && res.data.services !== null && res.data.services.length > 0) {
        for (var q = 0; q < res.data.services.length; q++) {
          for (var i = 0; i < res.data.services.length; i++) {
            if (res.data.services[q].servicecategoryid < res.data.services[i].servicecategoryid) {
              orderdata = res.data.services[q]
              res.data.services[q] = res.data.services[i]
              res.data.services[i] = orderdata
            }
          }
        }
      }
      // 将返回的值赋给渲染字段
      this.serviceItems = res.data.services
      if (this.serviceItems !== null && this.serviceItems.length > 1) {
        // 合并的行数
        var num = 1
        // 第一个相等的下标
        var farstnum = 0
        for (var j = 0; j < this.serviceItems.length; j++) {
          if (this.serviceItems[j].isappointment === 0 && this.serviceItems[j].applystatus === 1) {
            this.serviceItems[j].firstservicedate = this.renderDate(this.serviceItems[j].firstservicedate)
          }
          if (j > 0) {
            if (this.serviceItems[farstnum].servicecategoryid === this.serviceItems[j].servicecategoryid && j !== this.serviceItems.length) {
              this.serviceItems[j].ishebing = '1'
              num++
              this.serviceItems[farstnum].rowNum = num
            } else {
              farstnum = j
              num = 1
            }
          } else {
            farstnum = 0
            num = 1
          }
        }
      }
    })
  },
  methods: {
    // 提交
    handleSave () {
      // 弹框1
      this.modal1 = true
    },
    cancelProtocol () {
      this.$Message.info('点击了取消')
    },
    saveProtocol () {
      // 主键
      this.insertModel.id = this.formModel.id
      // 手机号
      this.insertModel.telephone = this.formModel.telephone
      // 地址
      this.insertModel.address = this.formModel.address
      // 服务想修改的时间
      this.insertModel.services = []
      if (this.serviceItems != null && this.serviceItems.length > 0) {
        for (var k = 0; k < this.serviceItems.length; k++) {
          if (this.serviceItems[k].isappointment === 1) {
            var serviceItem = {}
            serviceItem.id = this.serviceItems[k].id
            serviceItem.firstservicedate = this.serviceItems[k].firstservicedate
            this.insertModel.services.push(serviceItem)
          }
        }
      }
      this.$Loading.start()
      // console.log(this.insertModel)
      updateContactByKey(this.insertModel).then((res) => {
        if (res.status === 200) {
          this.$Message.info('保存成功')
          this.$router.push({path: 'contact'})
        }
      })
    },
    handleReturn () {
      this.$router.go(-1)
      // 跳转
      // this.$router.push({path: 'contact'})
    },
    // 毫秒数转换为标准时间
    renderDate (date) {
      var commonTime = new Date(date)
      var time = this.formatDate(commonTime) // 标准时间转换为指定格式
      return time
    },
    // 日期格式转换
    formatDate (date) {
      var y = date.getFullYear()
      var m = date.getMonth() + 1
      m = m < 10 ? '0' + m : m
      var d = date.getDate()
      d = d < 10 ? ('0' + d) : d
      var h = date.getHours()
      var minute = date.getMinutes()
      minute = minute < 10 ? ('0' + minute) : minute
      var newDate = y + '-' + m + '-' + d + ' ' + h + ':' + minute
      return newDate
    }
  }
}
</script>
<style  scoped lang="less" >
  table.gridtable {
    border: 1px solid #E9EAEC;
    width: 100%;
	  font-family: verdana,arial,sans-serif;
	  font-size:11px;
	  color:#333333;
	  border-collapse: collapse;
    text-align: center;
 }
 table.gridtable th {
  font-size:14px;
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #E9EAEC;
  text-align: center;
 }
 table.gridtable td {
	border-width: 1px;
	border-style: solid;
	border-color: #E9EAEC;
  text-align: center;
 }
  table.gtable {
  border: 0px solid #E9EAEC;
  width: 100%;
  height: 100%;
  font-family: verdana,arial,sans-serif;
  font-size:11px;
  color:#333333;
  border-collapse: collapse;
  text-align: center;
 }
 table.gtable td {
	border-width: 0px;
	padding: 8px;
  text-align: center;
 }
.divcss {
margin-top: 15px;
}
.tdheigth {
  height:50px
}
.layout-copy {
  text-align: center;
  color: #9ea7b4;
}
</style>
