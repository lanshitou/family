<!-- 
    签约协议添加页面
  -->
<template>
  <div>
    <div class="step-block">
      <p>
      <Steps :current="current">
        <Step title="调取签约居民信息"></Step>
        <Step title="拟签约服务明细选择"></Step>
        <Step title="签约确认生效"></Step>
      </Steps>
      </p>
    </div>
    <template v-if="current === 0">
      <div style="height:80%;overflow:auto">
        <Form ref="formModel"  class="form-block" :rules="servicesValidate" :model="formModel" :label-width="130">
          <Row>
            <Col span="18">
            <Form-item label="身份证号码" prop="identitynumber">
              <Input v-model="formModel.identitynumber" style='width:400px' placeholder="请输入身份证号码" ></Input>
              <Button type="primary" @click.native="record('formModel')">查询居民信息</Button>
            </Form-item>
            </Col>
          </Row>
          <Row>
            <Col span="12">
              <Form-item label="申请人姓名" prop="residentname">
                {{formModel.residentname}}
              </Form-item>
            </Col>
          </Row>
            <Row>
              <Col span="12">
                <Form-item label="出生日期" prop="birthdate">
                  {{formModel.birthdate}}
                </Form-item>
              </Col>
            </Row>
            <Row>
              <Col span="12">
              <Form-item label="年龄" prop="age">
                {{formModel.age === 0 ? '' : formModel.age}}
              </Form-item>
              </Col>
          </Row>
          <Row>
            <Col span="12">
            <Form-item label="联系电话" prop="contactphone">
              {{formModel.contactphone}}
            </Form-item>
            </Col>
          </Row>
          <Row>
            <Col span="10">
            <Form-item label="家庭地址" prop="permanentaddress">
              {{formModel.permanentaddress}}
            </Form-item>
            </Col>
          </Row>
          <Row v-show="jiatingchengyuan">
            <Form-item :label="'家庭成员'">
              <Row>
                <Col span="3">
                  姓名
                </Col>
                <Col span="3">
                年龄
                </Col>
                <Col span="3">
                性别
                </Col>
                <Col span="3">
                关系
                </Col>
                <Col span="8">
                健康档案号
                </Col>
              </Row>
            </Form-item>
            <Form-item v-for="item,index in familyModel" :key="index" label="">
              <Row>
                <Col span="3">
                {{item.membername}}
                </Col>
                <Col span="3">
                {{item.age}}
                </Col>
                <!-- 0未知的性别,1男性,2女性,9未说明的性别', -->
                <Col span="3">
                {{item.sex === 0 ? '未知性别' : item.sex === 1 ? '男' : item.sex === 2 ? '女' : '未说明性别'}}
                </Col>
                <Col span="3">
                {{item.codeinfoname}}
                </Col>
                <Col span="8">
                {{item.archivesnumber}}
                </Col>
              </Row>
            </Form-item>
          </Row>
          <div v-show="isShow">
              <Alert show-icon>
                  消息提示
                  <template slot="desc">
                    <Row>
                      <Col span="2">
                      <p>暂无家庭档案</p>
                      <i-button style="color:blue;" type="text" @click="nowAdd">现在新增>></i-button>
                      </Col>
                      <p class="color">注：如需签订家庭协议，请先新增家庭档案</p>
                    </Row>
                  </template>
              </Alert>
          </div>
          <Row>
            <Form-item>
              <i-button type="primary" @click="next">下一步</i-button>
            </Form-item>
          </Row>
        </Form>
      </div>
    </template>
    <template v-else-if="current === 1">
      <div style="height:80%;overflow:auto">
        <Form  ref="servicesModel" class="form-block" :model="servicesModel" :label-width="140">
          <br>
          <Row>
            <Col span="12">
            <Form-item label="拟申请医疗机构" prop="institutionidentity">
                <Select  @on-change="jigouclick" v-model="servicesModel.institutionidentity" style='width:200px' placeholder='请选择医疗机构'>
                    <Option v-for='(item,index) in applyInstitutions' :value='index'>{{ item.orgname }}</Option>
                </Select>
            </Form-item>
            </Col>
          </Row>
          <Row>
            <Col span="13">
            <Form-item label="拟申请医生团队" prop="teamidentity">
              <Select @on-change="yishengclick" v-model="servicesModel.teamidentity" style='width:200px' placeholder='请选择医生团队'>
                <Option v-for='(item,index) in applyDoctorTeam' :value='index'>{{ item.teamname }}</Option>
              </Select>
            </Form-item>
            </Col>
          </Row>
          <Row>
            <Col span="12">
            <Form-item label="签约类型" prop="signtype">
              <Radio-group v-model="servicesModel.signtype" @on-change="rediochange('index')">
                <Radio label="0">个人</Radio>
                <Radio :disabled="singtypeisdisabled" label="1">家庭</Radio>
              </Radio-group>
            </Form-item>
            </Col>
          </Row>
          <Row>
            <Col span="4">
            <Form-item label="签约服务">
            </Form-item>
            </Col>
            <Col>
            <router-link :to="{ path: 'detail'}">查看签约协议详细条款</router-link>
            </Col>
          </Row>
          <Row>
            <table  class="gridtable" :columns="serviceColumn" :data="serviceItems">
              <tr>
                <th v-for="items in serviceColumn">{{items.title}}</th>
              </tr>
                <tr v-for="(items, index) in serviceItems">
                      <td v-if="items.ishebing !== '1' " :rowspan="items.rowNum" style="border-width: 1px;">  
                       <!-- <td  style="border-width: 1px;">  -->
                     <!-- 类别 -->
                       <Checkbox @click.prevent.native="check(index)" v-model="items.leibie">
                        {{items.servicecategoryname}}
                        </Checkbox>
                    </td>
                      <!-- 项目 -->
                    <td>
                      <!--复选框-->
                      <Checkbox @click.prevent.native="checkChange(index)"  v-model="items.xiangmu" >{{items.servicename}}</Checkbox>
                    </td>
                    <!-- 服务计划 -->
                    <td>
                      {{items.servicemesure}}{{items.servicemesureunit}}/
                      {{items.periodidentity === '0' ? '每年' : items.periodidentity === '1' ? '每月' : items.periodidentity === '2' ? '每周' : ''}}
                      <p  >, {{items.isappointment === 0 ? '上门服务' : items.isappointment === 1 ? '需预约' : ''}}</p>
                    </td>
                    <td>
                      <p v-if="items.isappointment === 0">
                      <Date-picker  v-model="items.firstservicedate" format="yyyy-MM-dd HH:mm" type="datetime" placeholder="请选择首次服务时间" style="width: 160px"></Date-picker>
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
                    <td>
                      <Radio-group v-model="items.redioduixiang">
                        <Row>
                          <Col span="10">
                            <Radio  @click="clickchange(0)" label="1">仅本人</Radio>
                          </Col>
                          <Col  span="14">
                            <Radio :disabled="singTypedisabled" label="0">全部成员</Radio>
                          </Col>
                        </Row>
                        <Row>
                          <Col span="8">
                            <Radio :disabled="singTypedisabled" label="2">其他</Radio>
                          </Col>
                          <Col span="16">
                            <Dropdown  placement="left-start"  :visible="visible" style="margin-left: 0px">
                                <a href="javascript:void(0)" >
                                    家庭成员姓名
                                </a>
                                <Dropdown-menu  trigger="custom" slot="list" style="margin-left: 0px">
                                    <Checkbox-group v-model="items.serviceselected" @on-change="checkboxChange('index')">
                                      <Checkbox :disabled="singTypedisabled" v-for="check,index in familyModel" :key="index" :label="index">{{check.membername}}</Checkbox>
                                    </Checkbox-group>
                                </Dropdown-menu>
                            </Dropdown>
                          </Col>
                        </Row>
                      </Radio-group>
                        
                    </td>
              </tr>
            </table>
          </Row>
          <Row id="rowid" type="flex" justify="center">
            <Form-item>
              <i-button type="primary" @click="last">上一步</i-button>
              <Button type="primary" @click="handleServices">提交</Button>
            </Form-item>
          </Row>
        </Form>
      </div>
    </template>
  </div>
</template>
<script>
  // 接口
  import {
    getInstitutionByUser, addContact, getTeamsByCon, getFuWuByTeamsKey,
    findMemeberByCon, findMemberWithContactByCondition, findMemberShip
  } from '../../api/api'
  export default {
    data () {
      return {
        // 显示新增家庭成员信息使用
        isShow: false,
        // 家庭成员信息显示使用
        jiatingchengyuan: false,
        // 当鼠标放上是显示使用
        visible: false,
        // 当家庭成员只有自己一个人时签约类型中家庭不可编辑用
        singTypedisabled: false,
        // 签约类型是否可编辑
        singtypeisdisabled: false,
        guanxiList: [
        ],
        // 医疗机构下拉框数据
        applyInstitutions: [
        ],
        applyDoctorTeam: [
        ],
        serviceColumn: [
          {
            title: '服务类别'
          },
          {
            title: '服务项目'
          },
          {
            title: '服务计划'
          },
          {
            title: '首次服务时间'
          },
          {
            title: '医疗人员'
          },
          {
            title: '医疗设备'
          },
          {
            title: '预计收费'
          },
          {
            title: '服务对象'
          }
        ],
        serviceItems: [
        ],
        // 每一条服务对象中的选择按钮的值
        insertSingle: {
          leibie: false,
          xingmu: false,
          customercategory: 1000,
          serviceselected: []
        },
        returnduixiang: {
          // 服务项目ID
          servicecategoryid: '',
          // 服务对象选择值
          customercategory: 1000,
          // 服务对象ID
          serviceselected: []
        },
        // 校验========================开始
        servicesValidate: {
          identitynumber: [
            { required: true, max: 500, message: '身份证号不能为空', trigger: 'blur' }
          ],
          residentname: [
            { required: true, max: 500, message: ' ', trigger: 'blur' }
          ]
        },
        // 校验========================结束
        current: 0,
        // 服务明细选择信息
        servicesModel: {
          // 拟申请医疗机构
          institutionidentity: '',
          // 拟申请医生团队
          teamidentity: '',
          // 签约类型
          signtype: ''
        },
        // 居民信息
        formModel: {
        },
        // 家庭信息
        familyModel: {
        },
        // 添加数据结构============================================开始
        insertModel: {
        }
        // 添加数据结构============================================结束
      }
    },
    // ！！！！！！！！！！！！！！！！！！！！！！！！创建时的事件！！！！！！！！！！！！！！！！！！！
    created: function () {
      // 从新增家庭成员页面跳过来的 根据ID查询个人健康档案信息和家庭成员信息
      if (this.$route.params.type === '2') {
        let para = {
          identitynumber: this.$route.params.id
        }
        this.$Loading.start()
        // 查询个人健康档案信息=====================================开始
        findMemberWithContactByCondition(para).then((res) => {
          this.$Loading.finish()
          if (res.data !== null && res.data.length > 0) {
            this.formModel = res.data[0]
            this.formModel.birthdate = this.renderDate(this.formModel.birthdate)
            // 调用查询家庭成员信息接口
            this.findFamilyManById()
          }
        })
      }
      let para = {
      }
      // 查询当前登陆人的医疗机构
      getInstitutionByUser(para).then((res) => {
        this.applyInstitutions = []
        this.applyInstitutions = res.data
      })
      // 查询所有关系表
      findMemberShip().then((res) => {
        this.guanxiList = res.data
      })
    },
    // ！！！！！！！！！！！！！！！！！！！！！！！！方法区！！！！！！！！！！！！！！！！！！！！！！！
    methods: {
      clickchange (num) {
        alert(num)
      },
      // ·············································································选择医疗机构
      jigouclick () {
        this.applyDoctorTeam = []
        this.servicesModel.teamidentity = ''
        if (this.servicesModel.institutionidentity !== null && this.servicesModel.institutionidentity !== '') {
          let para = {
            institutionidentity: this.applyInstitutions[this.servicesModel.institutionidentity].id
          }
          // 根据医疗机构ID查询一声团队信息
          getTeamsByCon(para).then((res) => {
            this.$Loading.finish()
            this.applyDoctorTeam = res.data
            this.serviceItems = []
          })
        }
      },
      // ·················································································选择医生团队
      yishengclick () {
        if (this.servicesModel.teamidentity !== undefined && this.servicesModel.teamidentity !== '') {
          let para = {
            teamidentify: this.applyDoctorTeam[this.servicesModel.teamidentity].id
          }
          // 根据医生选择的医疗团队查询当前医生的服务项目================为开始
          // "teamidentify":"7944BB6EC44B4DC3A53D0A9ABD214F66"
          getFuWuByTeamsKey(para).then((res) => {
            this.$Loading.finish()
            // 动态给服务加字段分别有    类别选项   项目选项   对象单选   服务对象对象
            if (res !== null && res.data !== null && res.data.length > 0) {
              for (var k = 0; k < res.data.length; k++) {
                res.data[k].leibie = false
                res.data[k].xiangmu = false
                res.data[k].redioduixiang = 111
                res.data[k].serviceselected = []
                res.data[k].firstservicedate = ''
              }
            }
            // 将查出的数据排序
            var orderdata = {}
            if (res !== null && res.data !== null && res.data.length > 0) {
              for (var q = 0; q < res.data.length; q++) {
                for (var i = 0; i < res.data.length; i++) {
                  if (res.data[q].servicecategoryid < res.data[i].servicecategoryid) {
                    orderdata = res.data[q]
                    res.data[q] = res.data[i]
                    res.data[i] = orderdata
                  }
                }
              }
            }
            // 将返回的值赋给渲染字段
            this.serviceItems = res.data
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
          })
        }
      },
      rediochange () {
        if (this.servicesModel.signtype === '0') {
          this.singTypedisabled = true
        }
        if (this.servicesModel.signtype === '1') {
          this.singTypedisabled = false
        }
      },
      // ··································································将签约基本数据转移到添加的model中去
      assignment () {
        // 地址 address birthdate
        this.insertModel.address = this.formModel.permanentaddress
        // 出生日期
        this.insertModel.birthdate = this.formModel.birthdate
        // 年龄 age
        this.insertModel.age = this.formModel.age
        // 性别 sex
        this.insertModel.sex = this.formModel.sex
        // 签约类型 signtype
        this.insertModel.signtype = this.servicesModel.signtype
        // 团队ID    teamidentity
        this.insertModel.teamidentity = this.applyDoctorTeam[this.servicesModel.teamidentity].id
        // 团队名称 teamname
        this.insertModel.teamname = this.applyDoctorTeam[this.servicesModel.teamidentity].teamname
        // 电话  telephone
        this.insertModel.telephone = this.formModel.contactphone
        // 姓名 applyname
        this.insertModel.applyname = this.formModel.residentname
        // 身份证号 identitynumber
        this.insertModel.identitynumber = this.formModel.identitynumber
        // 机构ID  institutionidentity
        this.insertModel.institutionidentity = this.applyInstitutions[this.servicesModel.institutionidentity].id
        // 机构名称 institutionname
        this.insertModel.institutionname = this.applyInstitutions[this.servicesModel.institutionidentity].orgname
        if (this.familyModel !== null && this.familyModel.length > 0) {
          // 居民家庭编号 residentfamilyidentity  familyModel
          this.insertModel.residentfamilyidentity = this.familyModel[0].familyidentity
        }
        // 健康档案编号 residenthealtharchivesidentity
        this.insertModel.residenthealtharchivesidentity = this.formModel.archivesnumber
        // 服务对象
        // 服务数据重组==================================================================开始
        this.insertModel.services = []
        if (this.serviceItems !== null && this.serviceItems.length > 0) {
          for (var k = 0; k < this.serviceItems.length; k++) {
            if (this.serviceItems[k].xiangmu) {
              var servicedata = {
                 // 一条记录的ID
                serviceitemidentity: '',
                // 服务对象选择值
                customercategory: 1000,
                // 服务对象ID
                customer: []
              }
              // 一条记录的ID
              servicedata.serviceitemidentity = this.serviceItems[k].id
              // 服务对象选的值
              servicedata.customercategory = this.serviceItems[k].redioduixiang
              // 首次服务时间
              servicedata.firstservicedate = this.serviceItems[k].firstservicedate
              if (this.serviceItems[k].redioduixiang === '2') {
                if (this.serviceItems[k].serviceselected !== null && this.serviceItems[k].serviceselected.length > 0) {
                  for (var j = 0; j < this.serviceItems[k].serviceselected.length; j++) {
                    var costomerVo = {
                      id: '',
                      membername: ''
                    }
                    costomerVo.id = this.familyModel[this.serviceItems[k].serviceselected[j]].id
                    costomerVo.membername = this.familyModel[this.serviceItems[k].serviceselected[j]].membername
                    servicedata.customer.push(costomerVo)
                  }
                }
              }
              this.insertModel.services.push(servicedata)
            }
          }
          // console.log(this.insertModel.services)
        }
        // 服务数据重组===============================结束
      },
      // ···············································································现在新增
      nowAdd () {
        // 有家庭成员就有家庭编号
        this.$router.push({name: 'family/add', params: {identitynumber: this.formModel.identitynumber}})
      },
      // ············································································上一步
      last () {
        if (this.current > 0) {
          this.current -= 1
        }
      },
      // ··········································································下一步
      next () {
        this.$refs['formModel'].validate((valid) => {
          if (valid) {
            this.handleStatus()
          } else {
            this.$Message.error('居民签约信息验证失败!')
          }
        })
        /**
         * 当前家庭成员是否只有一个，如果只有一个并且是本人的话
         * 签约类型只能是 “个人”
        */
        if (this.familyModel === null || this.familyModel[0] === undefined) {
          // 让下一页的签约类型“家庭”不可编辑
          this.singtypeisdisabled = true
        }
        // 只能选择个人
        this.servicesModel.signtype = '0'
        this.singTypedisabled = true
      },
      // ················································································确认框
      confirm () {
        var that = this
        this.$Modal.confirm({
          title: '操作确认',
          content: '<p>签约成功，是否继续新增?</p>',
          loading: true,
          onOk: () => {
            that.$Message.info('点击了确定')
            that.$Modal.remove()
            that.handleStatus()
            that.$router.push({ path: '/contact' })
          },
          onCancel: () => {
            that.$Message.info('点击了取消')
            that.$router.push({ path: '/contact' })
          }
        })
      },
      checkboxChange (index) {
        // console.info(this.serviceItems[0].serviceselected)
        // console.info(this.serviceItems[1].serviceselected)
      },
      // ·············································································进行条
      handleStatus () {
        if (this.current === 2) {
          this.current = 0
        } else {
          this.current += 1
        }
        if (this.current === 2) {
          this.confirm()
        }
      },
      // ···············································································提交
      handleServices () {
        this.$refs['servicesModel'].validate((valid) => {
          if (valid) {
            // 校验数据是否选择完整
            var boo = this.datacomplete()
            if (!boo) {
              this.assignment()
              // console.log(JSON.stringify(this.insertModel))
              // 添加签约协议
              addContact(this.insertModel).then((res) => {
                if (res.status === 200) {
                  this.confirm()
                }
              })
            }
          } else {
            this.$Message.error('居民签约信息验证失败!')
          }
        })
      },
      // =======================================================提交是判断数据是否选择完整 this.formModel
      datacomplete () {
        var boo = false
        // 医疗机构
        if (!boo && this.servicesModel.institutionidentity === '') {
          this.$Message.warning('请选择医疗机构')
          boo = true
        }
        // 医疗团队
        if (!boo && this.servicesModel.teamidentity === '') {
          this.$Message.warning('请选择医疗团队')
          boo = true
        }
        if (!boo && this.serviceItems !== null && this.serviceItems.length > 0) {
          for (var j = 0; j < this.serviceItems.length; j++) {
            var item = this.serviceItems[j]
            if (item.xiangmu) {
              if (!boo && item.isappointment !== 1 && (item.firstservicedate === undefined || item.firstservicedate === '')) {
                this.$Message.warning('请选择第' + (j + 1) + '条服务项目首次服务时间')
                boo = true
              }
              if (!boo && item.redioduixiang === 111) {
                this.$Message.warning('请选择第' + (j + 1) + '条服务项目的服务对象')
                boo = true
              }
              if (!boo && item.redioduixiang === '2' && item.serviceselected.length === 0) {
                this.$Message.warning('请选择第' + (j + 1) + '条服务项目的服务对象中的对象名称')
                boo = true
              }
            }
          }
        }
        return boo
      },
      // ·····················································································重置
      handleReset (name, that) {
        that.$refs[name].resetFields()
      },
      // ······························································根据身份证好查询个人健康档案
      record (formdata) {
        this.familyModel = []
        if (this.formModel.identitynumber !== '' && this.formModel.identitynumber !== undefined) {
          // 先通过查询看当前证件号有没有记录
          let para = {
            // identitynumber: '610000985'
            identitynumber: this.formModel.identitynumber
          }
          this.$Loading.start()
          // 查询个人健康档案信息=====================================开始
          findMemberWithContactByCondition(para).then((res) => {
            this.$Loading.finish()
            if (res.data !== null && res.data.length > 0) {
              this.formModel = res.data[0]
              // console.log(this.formModel)
              this.formModel.birthdate = this.renderDate(this.formModel.birthdate)
              // 调用查询家庭成员信息接口
              this.findFamilyManById()
            } else {
              this.$refs[formdata].resetFields()
              this.isShow = false
              this.jiatingchengyuan = false
              this.$Modal.confirm({
                title: '居民健康档案信息',
                content: '<p>暂无此居民档案，是否新建？</p><p>注：若无居民档案，则无法进行签约</p>',
                onOk: () => {
                  this.$Message.info('点击了确定')
                  this.$router.push({ path: 'personal_record' })
                },
                onCancel: () => {
                  this.$Message.info('点击了取消')
                }
              })
            }
          })
        } else {
          this.$Modal.warning({
            title: '',
            content: '<p>请先填写要查询的身份证号！</p>'
          })
          this.$refs[formdata].resetFields()
          this.jiatingchengyuan = false
          this.isShow = false
        }
      },
      // 根据身份证号ID查询家庭成员信息
      findFamilyManById () {
        // 查询个人健康档案信息=====================================结束
          // 查询个人家庭信息=====================================开始
          /**
           * 如果个人健康档案没有信息  就不用查询家庭信息
          */
        let para = {
          identitynumber: this.formModel.identitynumber
        }
        if (this.formModel.identitynumber !== '' && this.formModel.identitynumber !== undefined) {
          // 显示新增家庭协议的提示
          this.isShow = true
          findMemeberByCon(para).then((res) => {
            this.$Loading.finish()
            if (res.data !== null && res.data.length > 0) {
              for (var z = 0; z < res.data.length; z++) {
                var num = res.data[z].membership
                // 调用关系表接口查出关系名称 findMemberShip
                if (this.guanxiList !== null && this.guanxiList.length > 0) {
                  for (var k = 0; k < this.guanxiList.length; k++) {
                    var num2 = this.guanxiList[k].codeinfovalue
                    if ((num + '') === num2) {
                      // console.log(this.guanxiList[k])
                      res.data[z].codeinfoname = this.guanxiList[k].codeinfoname
                    }
                  }
                }
              }
            }
            this.familyModel = res.data
            if (this.familyModel !== null && this.familyModel[0] !== undefined) {
              this.jiatingchengyuan = true
            } else {
              this.jiatingchengyuan = false
            }
          })
        } else {
          // 显示新增家庭协议的提示
          this.isShow = false
        }
        // 查询个人家庭信息=====================================结束
      },
      // ·············································································分项复选框事件
      checkChange (index) {
        var leibieid = this.serviceItems[index].servicecategoryid
        var boo = false
        if (this.serviceItems[index].xiangmu === false) {
          this.serviceItems[index].xiangmu = true
          for (var z = 0; z < this.serviceItems.length; z++) {
            if (leibieid === this.serviceItems[z].servicecategoryid && this.serviceItems[z].xiangmu !== true) {
              boo = true
            }
          }
          if (boo) {
            for (var x = 0; x < this.serviceItems.length; x++) {
              if (leibieid === this.serviceItems[x].servicecategoryid) {
                this.serviceItems[x].leibie = false
              }
            }
          } else {
            for (var y = 0; y < this.serviceItems.length; y++) {
              if (leibieid === this.serviceItems[y].servicecategoryid) {
                this.serviceItems[y].leibie = true
              }
            }
          }
        } else {
          this.serviceItems[index].xiangmu = false
          for (var v = 0; v < this.serviceItems.length; v++) {
            if (leibieid === this.serviceItems[v].servicecategoryid) {
              this.serviceItems[v].leibie = false
            }
          }
        }
      },
      // ·············································································类别事件
      check (index) {
        // 类别选择时要将后面的服务项目也选中
        // console.log(this.serviceItems[index].servicecategoryid)
        var leibieid = this.serviceItems[index].servicecategoryid
        if (this.serviceItems[index].leibie === true) {
          this.serviceItems[index].leibie = false
          for (var j = 0; j < this.serviceItems.length; j++) {
            if (leibieid === this.serviceItems[j].servicecategoryid) {
              this.serviceItems[j].xiangmu = false
            }
          }
        } else {
          this.serviceItems[index].leibie = true
          for (var k = 0; k < this.serviceItems.length; k++) {
            if (leibieid === this.serviceItems[k].servicecategoryid) {
              this.serviceItems[k].xiangmu = true
            }
          }
        }
      },
       // ··········································································毫秒数转换为标准时间
      renderDate (date) {
        var commonTime = new Date(date)
        var time = this.formatDate(commonTime) // 标准时间转换为指定格式
        return time
      },
      // ···········································································日期格式转换
      formatDate (date) {
        var y = date.getFullYear()
        var m = date.getMonth() + 1
        m = m < 10 ? '0' + m : m
        var d = date.getDate()
        d = d < 10 ? ('0' + d) : d
        var newDate = y + '-' + m + '-' + d
        return newDate
      }
    }
  }
</script>
<style  scoped lang="less" >
  .layout-copy {
    text-align: center;
    color: #9ea7b4;
  }
  .form-block {
    /*width: 100%;*/
    margin: 1px;
    /*background: #f8f8f9;*/
    margin-top: 10px;
    margin-bottom: 0px;
  }
  .step-block {
    margin: 1px;
    height: 50px;
    overflow: hidden;
    /*background: #f8f8f9;*/
    margin-top: 10px;
    margin-bottom: 10px;
  }
  .color {
    color: red
  }
  .layout-breadcrumb {
    padding: 10px 15px 0;
    margin-bottom: 10px;
  }
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
 magleft {
  margin-right: 20px
}
 table.gtable td {
	border-width: 0px;
	padding: 8px;
  text-align: center;
 }
</style>
