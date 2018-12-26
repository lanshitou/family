/**
 * @Title : 注册
 * @Description : 
 * @author : TiuWeb
 */
<template>
  <div class="" >
    <div  style="height:90%; overflow:auto;">
        <div class="layout-content-main">
            <div class='menu'>
                <Form ref="registerModel" class="form-block" :model="registerModel" :label-width="100" :rules="registerValidate">
                    <Row type="flex" justify="center">
                        <Col span="9">
                        <Form-item label="申请人姓名" prop="name">
                            <Input v-model="registerModel.name" placeholder="请输入..." style="width:200px"></Input>
                        </Form-item>
                        </Col>
                    </Row>
                    <Row type="flex" justify="center">
                        <Col span="9">
                        <Form-item label="出生日期" prop="birthday">
                            <Date-picker v-model="registerModel.birthday" format="yyyy年MM月dd日" type="date" placeholder="选择日期" style="width: 200px"></Date-picker>
                        </Form-item>
                        </Col>
                    </Row>
                    <Row type="flex" justify="center">
                        <Col span="9">
                        <Form-item label="年龄" prop="age">
                            <Input v-model="registerModel.age" style="width:200px"></Input>
                        </Form-item>
                        </Col>
                    </Row>
                    <Row type="flex" justify="center">
                        <Col span="9">
                        <Form-item label="证件类型" prop="certificateidentity">
                            <Select v-model="registerModel.certificateidentity" style='width:200px' placeholder='请选择证件类型'  @on-change="changeC" :label-in-value="true">
                                <Option v-for='item in certificate' :value='item.codeinfovalue' :key='item.codeinfovalue'>{{ item.codeinfoname }}</Option>
                            </Select>
                        </Form-item>
                        </Col>
                    </Row>
                    <Row type="flex" justify="center">
                        <Col span="9">
                        <Form-item label="证件号码" prop="idno">
                            <Input v-model="registerModel.idno" style="width: 200px"></Input>
                        </Form-item>
                        </Col>
                    </Row>
                    <Row type="flex" justify="center">
                        <Col span="9">
                        <Form-item label="联系电话" prop="tel">
                            <Input v-model="registerModel.tel" style="width: 200px"></Input>
                        </Form-item>
                        </Col>
                    </Row>
                    <Row type="flex" justify="center">
                        <Col span="9">
                        <Form-item label="邮箱" prop="email">
                            <Input v-model="registerModel.email" style="width: 200px"></Input>
                        </Form-item>
                        </Col>
                    </Row>
                    <Row type="flex" justify="center">
                        <Col span="9">
                        <Form-item label="验证码" prop="code">
                            <Input v-model="registerModel.code" style="width: 100px"></Input>
                            <Button type="primary" style="padding:4px;" @click="timeOut" :disabled="showbt">{{timeText}}&nbsp;&nbsp;{{timer}}秒有效</Button>
                        </Form-item>
                        </Col>
                    </Row>
                    <Row type="flex" justify="center">
                        <Col span="9">
                        <Form-item label="家庭住址" prop="address">
                            <Input v-model="registerModel.address"></Input>
                        </Form-item>
                        </Col>
                    </Row>
                    <Row type="flex" justify="center">
                        <Col span="9">
                        <Form-item>
                        <Button type="primary" style="width:80px;margin-right:20px" @click="doSubmit('registerModel')">提交</Button>
                        <Button type="primary" style="width:80px;" @click="doCancel">返回</Button>
                        </Form-item>
                        </Col>
                    </Row>
                </Form>
            </div>
        </div>
    </div>
    <div class="layout-copy">
      2011-2016 &copy; TalkingData
    </div>
  </div>
</template>
<script>
import {
  findCertificateType
} from '../../api/api'
// import $ from 'jquery'
export default {
  data () {
    return {
      showbt: false,
      timeText: '点击获取验证码',
      timer: 60,
      stop: false,
      Interval: null,
      certificate: [],
      registerModel: {
        name: '',
        birthday: '',
        age: '',
        certificateidentity: '',
        idno: '',
        tel: '',
        email: '',
        address: ''
      },
        // 校验
      registerValidate: {
        name: [
        { required: true, message: '申请人姓名不能为空', trigger: 'blur' }
        ],
        birthday: [
        { required: true, type: 'date', message: '出生日期不能为空', trigger: 'change' }
        ],
        age: [
        { required: true, message: '年龄不能为空', trigger: 'blur' }
        ],
        certificateidentity: [
        { required: true, message: '证件类型不能为空', trigger: 'blur' }
        ],
        idno: [
        { required: true, message: '证件号码不能为空', trigger: 'blur' }
        ],
        tel: [
        { required: true, message: '电话不能为空', trigger: 'blur' },
        { type: 'string', max: 12, message: '联系电话不能多于12个字符', trigger: 'blur' }
        ],
        email: [
        { required: true, message: '邮箱不能为空', trigger: 'blur' },
        { type: 'email', message: '邮箱格式不正确', trigger: 'blur' }
        ],
        address: [
        { required: true, message: '地址不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  created: function () {
    // 获取所有证件类型
    findCertificateType().then((res) => {
      this.certificate = res.data
    })
  },
  methods: {
      //  验证码
    update () {
      if (this.timer <= 0) {
        this.timer = 60
        clearInterval(this.Interval)
        this.showbt = false
      } else {
        this.timer--
        this.showbt = true
      }
    },
    // 定时器
    startTimer () {
      if (this.stop === false) {
        this.Interval = setInterval(this.update, 1000)
      } else {
        clearInterval(this.Interval)
      }
    },
    // 验证码获取
    timeOut () {
      this.startTimer()
    },
    // 证件类型select选择器选中的Option变化时触发
    changeC (change) {
      this.formData.certificatename = change.label
    },
    doSubmit (registerModel) {
    //   this.$refs['registerModel'].validate((valid) => {
    //     if (valid) {
    //       insertRole(this.registerModel).then((res) => {
    //         if (res.status === 200) {
    //           this.$Message.success('保存成功')
    //           this.$router.push({ path: 'roles' })
    //         } else {
    //           this.$Message.error('保存失败')
    //         }
    //       })
    //     } else {
    //       this.$Message.error('角色信息验证失败!')
    //     }
    //   })
    },
    doCancel () {
      this.$router.push({path: '/index'})
    }
  }
}
</script>
<style scoped lang="less">
    .filterTitle {
      font-size: 12px;
      color: #1c2438;
      padding: 10px 12px;
    }
    .menu {
      flex-direction: row;
      align-items: center;
    }
    .layout {
    border: 1px solid #d7dde4;
    background: #f5f7f9;
    position: relative;
    border-radius: 4px;
    overflow: hidden;
    }
    .layout-breadcrumb {
    padding: 10px 15px 0;
    }
    .layout-content-main {
    margin-top: 50px;
    padding: 30px;
    }
    .layout-copy {
    text-align: center;
    padding: 10px 0 20px;
    color: #9ea7b4;
    }
</style>
