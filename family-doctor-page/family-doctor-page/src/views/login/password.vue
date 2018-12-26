/** * @Title : 密码找回 * @Description : * @author : TiuWeb */
<template>
  <div class="">
    <div style="height:90%; overflow:auto;">
      <div class="layout-content-main">
        <div class='menu'>
          <Form ref="formModel" class="form-block" :model="formModel" :label-width="100" :rules="formValidate">
            <div v-show="showName" style="margin-top:100px">
              <Row type="flex" justify="center">
                <Col span="12">
                <Form-item label="用户名" prop="name">
                  <Input v-model="formModel.name" placeholder="请输入..." style="width:200px;float:left;margin-right:10px"></Input>
                  <p class="color">忘记用户名？可使用绑定的手机号或者邮箱</p>
                </Form-item>
                </Col>
              </Row>
              <Row type="flex" justify="center">
                <Col span="9">
                <Form-item>
                  <Button type="primary" style="width:80px;margin-right:20px" @click="doSubmitName">确认</Button>
                </Form-item>
                </Col>
              </Row>
            </div>
          </Form>
          <div v-show="showStep">
            <div class="step-block">
              <p>当前正在进行第 {{ current + 1 }} 步</p>
              <Steps :current="current">
                <Step title="验证身份"></Step>
                <Step title="重置密码"></Step>
                <Step title="完成"></Step>
              </Steps>
            </div>
            <template v-if="current === 0">
              <div style="height:80%;overflow:auto">
                <Form ref="formModelTel" class="form-block" :model="formModelTel" :label-width="130" :rules="form1Validate">
                  <div style="margin-top:100px">
                    <Row type="flex" justify="center">
                      <Col span="12">
                      <Form-item label="绑定手机号" prop="tel">
                        <p  style="width: 100px;float:left;">{{formModelTel.tel}}</p>
                        <p class="color" style="float:left; margin-right:10px"> 更换验证方式</p>
                        <Radio-group v-model="type" style="float:left">
                          <Radio label="0">手机</Radio>
                          <Radio label="1">邮箱</Radio>
                        </Radio-group>
                      </Form-item>
                      </Col>
                    </Row>
                    <Row type="flex" justify="center">
                      <Col span="12">
                      <Form-item label="验证码" prop="code">
                        <Input v-model="formModelTel.code" style="width: 100px"></Input>
                        <Button type="primary" style="padding:4px;" @click="timeOut" :disabled="showbt">{{timeText}}&nbsp;&nbsp;{{timer}}秒有效</Button>
                      </Form-item>
                      </Col>
                    </Row>
                    <Row type="flex" justify="center">
                      <Col span="12">
                      <Form-item>
                        <Button type="primary" style="width:80px;margin-right:20px" @click="next">确认</Button>
                      </Form-item>
                      </Col>
                    </Row>
                  </div>
                </Form>
              </div>
            </template>
            <template v-else-if="current === 1">
              <div style="height:80%;overflow:auto">
                <Form ref="formPwdModel" class="form-block" :model="formPwdModel" :label-width="130" :rules="form2Validate">
                  <div style="margin-top:100px">
                    <Row type="flex" justify="center">
                      <Col span="12">
                         <Form-item label="请输入新的密码" prop="newPassword">
                          <Input v-model="formPwdModel.newPassword" style="width: 300px" type="password"></Input>
                        </Form-item> 
                      </Col>
                    </Row>
                    <Row type="flex" justify="center">
                      <Col span="12">
                         <Form-item label="请再次输入新的密码" prop="pass">
                          <Input v-model="formPwdModel.pass" style="width: 300px" type="password" @on-blur="confirmPwd()"></Input>
                        </Form-item> 
                      </Col>
                    </Row>
                    <Row type="flex" justify="center">
                      <Col span="12">
                      <Form-item>
                        <Button type="primary" style="width:80px;margin-right:20px" @click="nextpwd">确认</Button>
                      </Form-item>
                      </Col>
                    </Row>
                  </div>
                </Form>
              </div>
            </template>
            <template v-else-if="current === 2">
              <div style="height:80%;overflow:auto">
                <Form class="form-block" :label-width="130">
                  <div style="margin-top:100px">
                    <Row type="flex" justify="center">
                      <h1>密码找回成功！</h1>
                    </Row>
                    <Row type="flex" justify="center" style="margin-top:80px;">
                      <Button type="primary" style="width:80px;" @click="doBack">确认</Button>
                    </Row>
                  </div>
                </Form>
              </div>
            </template>
          </div>
        </div>
      </div>
    </div>
    <div class="layout-copy">
      2011-2016 &copy; TalkingData
    </div>
  </div>
</template>
<script>
export default {
  data () {
    return {
      showbt: false,
      timeText: '点击获取验证码',
      timer: 60,
      stop: false,
      Interval: null,
      showName: true,
      showStep: false,
      current: 0,
      type: 0,
      formModel: {
        name: ''
      },
      formModelTel: {
        tel: '********4258',
        code: ''
      },
      formPwdModel: {
        newPassword: '',
        pass: ''
      },
        // 校验
      formValidate: {
        name: [
        { required: true, message: '用户名不能为空', trigger: 'blur' }
        ]
      },
      // 校验
      form1Validate: {
        code: [
        { required: true, message: '验证码不能为空', trigger: 'blur' }
        ]
      },
      // 校验
      form2Validate: {
        newPassword: [
        { required: true, message: '密码不能为空', trigger: 'blur' }
        ],
        pass: [
        { required: true, message: '再次输入密码不能为空', trigger: 'blur' }
        ]
      }
    }
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
    // 提交名字
    doSubmitName () {
      this.$refs['formModel'].validate((valid) => {
        if (valid) {
          this.$Message.success('确认成功')
          this.showName = false
          this.showStep = true
        } else {
          this.$Message.error('用户名验证失败!')
        }
      })
    },
    // 提交返回
    doBack () {
      this.$router.push({path: '/index'})
    },
    // 再次输入密码验证
    confirmPwd () {
      if (this.formPwdModel.pass === this.formPwdModel.newPassword) {
      } else {
        this.$Message.error('密码不一致!')
        this.formPwdModel.newPassword = ''
        this.formPwdModel.pass = ''
      }
    },
    // 下一步
    next () {
      this.$refs['formModelTel'].validate((valid) => {
        if (valid) {
          this.handleStatus()
        } else {
          this.$Message.error('验证失败!')
        }
      })
    },
    // 下一步
    nextpwd () {
      this.$refs['formPwdModel'].validate((valid) => {
        if (valid) {
          this.handleStatus()
        } else {
          this.$Message.error('验证失败!')
        }
      })
    },
    // 步骤条
    handleStatus () {
      if (this.current === 3) {
        this.current = 0
      } else {
        this.current += 1
      }
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
    padding: 30px;
    }
    .layout-copy {
    text-align: center;
    padding: 10px 0 20px;
    color: #9ea7b4;
    }
    .color {
     color: blue;
    }
  .step-block {
    margin: 1px;
    height: 50px;
    overflow: hidden;
    /*background: #f8f8f9;*/
    margin-top: 10px;
    margin-bottom: 10px;
  }
</style>
