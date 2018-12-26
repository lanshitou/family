<template>
  <div>
    <div style="height:90%;overflow:auto;">
      <div class="layout-content-main">
        <div class='columnBox menu'>
          <div class='filterTitle'>身份证号码</div>
            <Input v-model="modelInfo.identitynumber" placeholder="请输入身份证号码" style="width: 200px"></Input>
            <Button type="primary" icon="ios-search" style="margin-left:10px" @click="query">查询</Button>
          
            <div class='filterTitle'>预约时间</div>
            <Date-picker  v-model="modelInfo.servicedate" format="yyyy-MM-dd HH:mm" type="datetime" placeholder="请选择预约时间" style="width: 160px"></Date-picker>
            
        </div>
        <div class="index">
          <Form ref="appoinement" :model="appoinement" :label-width="130">
            <fieldset >
              <legend style="font-weight:bold;font-size:15px;padding: 1px;text-align:left">个人信息</legend>
                  <Row>
                    <Col span="8">
                      <Form-item label="申请人姓名"  prop="applyName">
                        {{appoinement.applyname}}
                      </Form-item>
                    </Col>
                    <Col span="8">
                      <Form-item label="年龄"  prop="age">
                        {{appoinement.age}}
                      </Form-item>
                    </Col>
                    <Col span="8">
                      <Form-item label="性别"  prop="sex">
                        {{appoinement.sex === 0 ? '未知性别' : appoinement.sex === 1 ? '男' : appoinement.sex === 2 ? '女' : appoinement.sex === 9 ? '未说明性别' : ''}}
                      </Form-item>
                    </Col>
                  </Row>
                  <Row>
                    <Col span="8">
                      <Form-item label="联系电话"  prop="telephone">
                        {{appoinement.telephone}}
                      </Form-item>
                    </Col>
                    <Col span="8">
                        <Form-item label="家庭住址"  prop="address">
                          {{appoinement.address}}
                        </Form-item>
                    </Col>
                  </Row>
              </fieldset>
            <fieldset>
              <legend style="font-weight:bold;font-size:15px;padding: 1px;text-align:left">
                签约信息
              </legend>
              <Row>
                <Col span="12">
                  <Form-item label="签约单号"  prop="contactnumber">
                    {{formModel.contactnumber}}
                  <Tag v-if="formModel.contactnumber !== undefined" type="border" color="blue" @click.native="signDetails">查看签约详情</Tag>
                  </Form-item>
                </Col>
                <Col span="12">
                  <Form-item label="签约类别"  prop="signtype">
                    {{formModel.signtype === 0 ? '个人' : formModel.signtype === 1 ? '家庭' : ''}}
                    <!-- <Tag v-if="formModel.signtype === 0 || formModel.signtype === 1" type="border" color="blue" @click.native="signPlans">查看本签约所有服务计划</Tag> -->
                  </Form-item>
                </Col>
              </Row>
              <Row>
                <Col span="12">
                  <Form-item label="签约医疗机构"  prop="institutionname">
                    {{formModel.institutionname}}
                  </Form-item>
                </Col>
                <Col span="12">
                  <Form-item label="签约团队"  prop="teamname">
                    {{formModel.teamname}}
                  </Form-item>
                </Col>
              </Row>
            </fieldset>
          </Form>
        </div>
        <div class="index">
          <div style="background:#f3f3f3;padding:2px;text-align:left">
            <Button @click="save" type="primary" style="margin:1px">确认</Button>
          </div>
          <Form ref="serviceItems" v-model="serviceItems" :label-width="130">
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
                      <Checkbox  v-model="items.xiangmu" >
                        {{items.servicename}}
                      </Checkbox>
                    </td>
                    <!-- 服务计划 -->
                    <td>
                      {{items.servicemesure}}{{items.servicemesureunit}}/
                      {{items.serviceperiodidentity === '0' ? '每年' : items.serviceperiodidentity === '1' ? '每月' : items.serviceperiodidentity === '2' ? '每周' : ''}}
                      , {{items.isappointment === 0 ? '上门服务' : items.isappointment === 1 ? '需预约' : ''}}
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
                      {{items.servicecharge}}{{items.servicechargeunit}}/{{items.servicechargefrequencyname}}
                    </td>
                    <!-- 显示服务对象名称 -->
                    <td>
                      已服务{{items.servicecount}}次，共{{items.totalcount}}次 
                    </td>
              </tr>
            </table>
      </Form>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import {
  findAppointmentByCon
  , subscribecontact
} from '../../../api/api'
export default {
  data () {
    return {
      id: '',
      modelInfo: {
        identitynumber: '',
        servicedate: ''
      },
      formModel: {
      },
      appoinement: {
      },
      serviceItems: [],
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
          title: '备注',
          key: 'remark'
        }
      ]
    }
  },
  created: function () {
    if (this.$route.params.identitynumber !== undefined) {
      this.modelInfo.identitynumber = this.$route.params.identitynumber
      let para = {
        identitynumber: this.$route.params.identitynumber
      }
      findAppointmentByCon(para).then((res) => {
        // console.log(res.data)
        this.formModel = res.data
        this.id = res.data.id
        this.appoinement = res.data.appoinement
        // 将查出的数据排序
        var orderdata = {}
        if (res !== null && res.data.services !== null && res.data.services.length > 0) {
          for (var q = 0; q < res.data.services.length; q++) {
            res.data.services[q].xiangmu = false
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
        this.serviceItems = res.data.services
      })
    }
  },
  methods: {
    // 查询
    query () {
      let para = {
        identitynumber: this.modelInfo.identitynumber
      }
      findAppointmentByCon(para).then((res) => {
        if (res.status !== 200) {
          this.$Message.warning('程序异常')
        } else {
          // console.log(res.data)
          this.formModel = res.data
          this.id = res.data.id
          this.appoinement = res.data.appoinement
          // 将查出的数据排序
          var orderdata = {}
          if (res !== null && res.data.services !== null && res.data.services.length > 0) {
            for (var q = 0; q < res.data.services.length; q++) {
              res.data.services[q].xiangmu = false
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
          this.serviceItems = res.data.services
        }
      })
    },
    // 查看签约详情
    signDetails () {
      this.$router.push({name: 'contact/detail', params: {id: this.id}})
    },
    // 查看本签约所有服务计划
    signPlans () {
      this.$router.push({ path: '../appointMent_sign_plans' })
    },
    // 单机确认事件
    save () {
      var boo = true
      // 验证身份证号
      if (boo && this.modelInfo.identitynumber === undefined || this.modelInfo.identitynumber === null || this.modelInfo.identitynumber === '') {
        this.$Message.warning('请输入身份证号查询')
        boo = false
      }
      // 验证预约时间 servicedate
      if (boo && (this.modelInfo.servicedate === undefined || this.modelInfo.servicedate === null || this.modelInfo.servicedate === '')) {
        this.$Message.warning('请选择预约时间')
        boo = false
      }
      if (boo) {
        var insertModel = {
          identitynumber: '',
          expectservicedate: '',
          serviceidentify: ''
        }
        insertModel.identitynumber = this.modelInfo.identitynumber
        insertModel.expectservicedate = this.modelInfo.servicedate
        // 找出已选的服务
        if (boo && this.serviceItems !== null && this.serviceItems.length > 0) {
          var serviceModel = {}
          var num = 0
          for (var i = 0; i < this.serviceItems.length; i++) {
            if (this.serviceItems[i].xiangmu) {
              serviceModel = this.serviceItems[i]
              num++
            }
          }
          if (num === 0) {
            boo = false
            this.$Message.warning('请选择一条预约服务！')
          }
          if (num > 1) {
            boo = false
            this.$Message.warning('只能选择一条预约服务！')
          }
          if (boo) {
            insertModel.serviceidentify = serviceModel.id
          }
        }
        if (boo) {
          console.log(insertModel)
          subscribecontact(insertModel).then((res) => {
            if (res.status === 200) {
              this.$Message.success('预约成功！')
              this.$router.push({path: 'plans'})
            }
          })
        }
      }
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
<style scoped lang="less">
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
  width: 10%;
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
.filterTitle {
  font-size: 12px;
  color: #1c2438;
  padding: 10px 12px;
}

.menu {
  flex-direction: row;
  align-items: center;
  margin: 10px;
}

.columnBox {
  display: -webkit-flex;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
}

.index {
  padding-bottom: 5px;
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
.layout-breadcrumb {
  padding: 10px 15px 0;
}
.layout-content-main {
  padding: 5px;
}

.layout-copy {
  text-align: center;
  padding: 10px 0 20px;
  color: #9ea7b4;
}
.headline {
  padding: 10px;
  font-size: 14px;
  color: #657180;
}
</style>
