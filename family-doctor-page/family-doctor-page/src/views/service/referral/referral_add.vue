<template>
    <div class="">
        <div class="layout-content margin_top12" style="height:83%; overflow:auto;">
                <div class="margin_top12">
                  <Form ref="fillmodel" :model="fillmodel"  :rules="fillmodelValidate"  :label-width="130">
                    <fieldset>
                        <legend class="legend">
                            机构信息
                        </legend>
                        <Row>
                            <Col span="12">
                                <div class = "margin_top">
                                    <Form-item label="转诊类型">
                                        <Radio-group v-model="fillmodel.transfertreatmentcategory" >
                                            <Radio  label="0">上转</Radio>
                                            <Radio  label="1">下转</Radio>
                                        </Radio-group>
                                    </Form-item>   
                                </div>
                            </Col>
                            <Col span="12">
                                <div class = "margin_top ">
                                    <Form-item label="转诊机构" prop="abutmentorgidentity">
                                        <Select v-model="fillmodel.abutmentorgidentity" style='width:300px' placeholder='全部'>
                                            <Option v-for='item in deptlist' :value='item.id' >{{ item.name }}</Option>
                                        </Select>
                                    </Form-item>
                                </div>
                            </Col>
                            <!-- <i-button class = "float_right blue" @click='nexus' type="text"> >>>转诊预约对照表查询</i-button> -->
                        </Row>
                    </fieldset>
                  </Form>
                  <Form ref="servicesModel" :model="servicesModel"  :rules="firstmodelValidate"  :label-width="130">
                    <fieldset class="margin_top12">
                        <legend class="legend">
                            <span>被转诊人信息</span>
                        </legend>
                        <Row>
                            <Col span="12">
                                <Form-item label="身份证" prop="identitynumber">
                                    <Input v-model="servicesModel.identitynumber" style='width:300px' ></Input>
                                </Form-item>
                            </Col>
                            <Col span="12">
                                <Form-item label="患者姓名" prop="membername">
                                    <Input v-model="servicesModel.membername" style='width:300px' ></Input>
                                </Form-item>
                            </Col>
                        </Row>
                        <Row>
                            <Col span="12">
                                <Form-item label="性别" prop="sex">
                                    <Radio-group v-model="servicesModel.sex">
                                        <Radio label="1">男</Radio>
                                        <Radio label="2">女</Radio>
                                    </Radio-group>
                                </Form-item>
                            </Col>
                            <Col span="12">
                                <Form-item label="年龄" prop='age'>
                                    <Input v-model="servicesModel.age" style='width:300px' ></Input>
                                </Form-item>
                            </Col>
                        </Row>
                        <Row>
                            <Col span="12">
                                <Form-item label="家庭地址" prop="residentaddress">
                                    <Input v-model="servicesModel.residentaddress" style='width:300px' ></Input>
                                </Form-item>
                            </Col>
                            <Col span="12">
                                <Form-item label="电话" prop="residenttelphone">
                                    <Input v-model="servicesModel.residenttelphone" style='width:300px' ></Input>
                                </Form-item>
                            </Col>
                        </Row>
                    </fieldset>
                    <div class = "center">
                        <i-button type="primary"  @click='save' class="margin_top12">确定</i-button>
                        <i-button type="primary"  @click='breakout' class="margin_top12">返回</i-button>
                    </div>
                </Form>
              </div>
        </div>
    </div>
</template>

<script>
import {
  findplanbykey
  , findCruuentOrgContrast
  , inserttransfer
} from '../../../api/api'
export default {
  data () {
    return {
      id: '',
      value4: '',
      deptlist: [],
      insertdata: {},
      deptdata: {},
      fillmodel: {
        transfertreatmentcategory: 0,
        abutmentorgidentity: ''
      },
      servicesModel: {
        identitynumber: '',
        membername: '',
        sex: 1,
        age: '',
        residentaddress: '',
        residenttelphone: ''
      },
    //   校验
      fillmodelValidate: {
        abutmentorgidentity: [
          { required: true, message: '转诊机构不能为空', trigger: 'change' }
        ]
      },
      firstmodelValidate: {
        identitynumber: [
          { required: true, message: '身份证号不能为空', trigger: 'blur' },
          { type: 'string', max: 20, message: '身份证号不能多于20字', trigger: 'blur' }
        ],
        membername: [
          { required: true, message: '患者姓名不能为空', trigger: 'blur' },
          { type: 'string', max: 10, message: '患者姓名不能多于10字', trigger: 'blur' }
        ],
        age: [
          { required: true, message: '年龄不能为空', trigger: 'blur' },
          { type: 'string', max: 3, message: '年龄最多三位数', trigger: 'blur' }
        ],
        residentaddress: [
          { required: true, message: '家庭地址不能为空', trigger: 'blur' },
          { type: 'string', max: 500, message: '家庭地址不能多于500字', trigger: 'blur' }
        ],
        residenttelphone: [
          { required: true, message: '电话不能为空', trigger: 'blur' },
          { type: 'string', max: 15, message: '电话不能多于15字', trigger: 'blur' }
        ]
      }
    }
  },
  created: function () {
    var vm = this
    if (vm.$route.params.id !== undefined) {
      vm.id = vm.$route.params.id
    //   根据身份证查询患者信息
      let para = {
        id: vm.$route.params.id
      }
      findplanbykey(para).then((res) => {
        if (res.status === 200) {
          vm.servicesModel = res.data
          if (vm.servicesModel.age !== '' && vm.servicesModel.age !== undefined) {
            vm.servicesModel.age = vm.servicesModel.age + ''
          }
          vm.servicesModel.transfertreatmentcategory = 1
          // console.log(res.data)
        }
      })
    }
    // 查询当前医疗机构的能转诊的机构名单
    findCruuentOrgContrast().then((res) => {
      if (res.status === 200) {
        if (res.data !== null && res.data.length > 0) {
          vm.deptdata = res.data[0]
              // 市医院
          var dept1 = {}
          dept1.id = vm.deptdata.cityorgidentity
          dept1.name = vm.deptdata.cityorgname
          vm.deptlist.push(dept1)
          // 县医院
          var dept2 = {}
          dept2.id = vm.deptdata.countyorgidentity
          dept2.name = vm.deptdata.countyorgname
          vm.deptlist.push(dept2)
        }
      }
    })
  },
  methods: {
    datazhengli () {
      var t = this
      // 对接机构和名称
      if (t.deptlist !== null && t.deptlist.length > 0) {
        t.deptlist.forEach(function (data) {
          if (data.id === t.fillmodel.abutmentorgidentity) {
            t.insertdata.abutmentorgname = data.name
            t.insertdata.abutmentorgidentity = data.id
          }
        }, this)
      }
      // 转诊类型
      t.insertdata.transfertreatmentcategory = t.fillmodel.transfertreatmentcategory
      // 姓名
      t.insertdata.residentname = t.servicesModel.membername
      // 性别
      t.insertdata.sex = t.servicesModel.sex
      // 年龄
      t.insertdata.age = t.servicesModel.age
      // 身份证号
      t.insertdata.identitynumber = t.servicesModel.identitynumber
      // 电话
      t.insertdata.linkmanphone = t.servicesModel.residenttelphone
      // 地址
      t.insertdata.address = t.servicesModel.residentaddress
      // 转诊对照关系外键
      t.insertdata.transfercontrastidentify = t.deptdata.id
      // 查出来的数据结构
      if (t.id !== '' && t.id !== undefined) {
        // 签约协议记录外键
        t.insertdata.recordidentify = t.servicesModel.recordidentify
        // 签约协议外键
        t.insertdata.residentcontactidentify = t.servicesModel.contactidentify
        // 执行计划外键
        t.insertdata.planidentify = t.servicesModel.id
        // 当前医疗机构外键
        t.insertdata.currentorgidentity = t.deptdata.currentorgidentity
        // 当前医疗机构名称
        t.insertdata.currentorgname = t.deptdata.currentorgname
      }
    },
    breakout () {
      this.$router.push({ path: 'referral' })
    },
    save () {
      var vm = this
      var boo = true
      if (boo) {
        vm.$refs['servicesModel'].validate((valid) => {
          if (!valid) {
            boo = false
            vm.$Message.error('表信息验证失败!')
          }
        })
        vm.$refs['fillmodel'].validate((valid) => {
          if (!valid) {
            boo = false
            vm.$Message.error('表信息验证失败!')
          }
        })
      }
      if (boo) {
        var reg = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/
        var r = this.servicesModel.identitynumber.match(reg)
        if (r === null) {
          this.$Message.warning('身份证号格式不正确')
          boo = false
        }
      }
      if (boo) {
        vm.datazhengli()
        // console.log(vm.insertdata)
        inserttransfer(vm.insertdata).then((res) => {
          if (res.status === 200) {
            this.$Message.success('保存成功')
            vm.breakout()
          } else {
            vm.$Message.error('添加失败!')
          }
        })
      }
    },
    nexus () {
      this.$router.push({ path: '../referral_nexus' })
    }
  }
}
</script>

<style scoped lang="less">
.layout-breadcrumb {
  padding: 10px 15px 0;
}
.center {
 text-align:center;
}
.margin_top {
  margin-top:30px;
}
.margin_top12 {
    margin-top:15px;
}
.margin_left1000 {
    margin-left:1000px;
}
.font_size {
    font-size: 15px;
}
.blue {
    color: blue;
}
.layout {
  border: 1px solid #d7dde4;
  background: #f5f7f9;
  position: relative;
  border-radius: 4px;
  overflow: hidden;
}

.layout-content {
  min-height: 200px;
  margin: 10px;
  overflow: hidden;
  background: #fff;
  border-radius: 4px;
}
.float_right {
 float:right;
}
.layout-copy {
  text-align: center;
  padding: 10px 0 20px;
  color: #9ea7b4;
}
.legend {
    font-size:10px;
    padding-top: 1px;
    text-align:left
}
.margin_left {
    margin-left: -500px;
}
</style>

