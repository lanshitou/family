<template>
  <div class="layout-body"> 
    <div class="layout-head">
      <img src="../assets/logo.png" style="height:75px;"/>
    </div>
    <div class="layout-content">
      <div class="layout-content-form">
      <div class="tittle">用户登录</div>
        <Form ref="formInline" :model="formInline" :rules="ruleInline" :label-width="80" class="form">
        <div class='formTitle'>账&#8195;号</div>
        <FormItem prop="username" style="margin:10px">
          <Input size="large" type="text" v-model="formInline.username" placeholder="账号">
              <Icon type="ios-person-outline" slot="prepend"></Icon>
          </Input>
        </FormItem>
        <div class='formTitle'>密&#8195;码</div>
        <FormItem prop="password" style="margin:10px">
          <Input v-model="formInline.password" size="large" type="password"  placeholder="密码">
              <Icon type="ios-locked-outline" slot="prepend"></Icon>
          </Input>
        </FormItem>
        <div class='formTitle'>验证码</div>
        <Form-item prop="codestr" style="margin:10px">
          <Input size="large" style="width:85px;float:left" placeholder="验证码" v-model="formInline.codestr"  type="text" :maxlength='4' @on-enter="handleSubmit('formInline')">
            <Icon slot="prepend" type="ios-locked-outline"></Icon>
          </Input>
          <span class="codestring-inner">{{codeString}}</span>
          <Button type="text" class="c-help-text" @click="newCodeString">看不清？换一个</Button>
        </Form-item>
        <FormItem class="formButton">
            <Button type="primary" style="width:230px"  size="large"  @click="handleSubmit('formInline')">登录</Button>
            <Button style="background:#bbbec4;color:#fff;margin-left:2px"  size="large"  @click="cancelSubmit('formInline')">重置</Button>
        </FormItem>
    </Form>
      </div>
    </div>
    <div class="layout-foot">
    </div>
  </div>
</template>
<script>
export default {
  data () {
    let vm = this
    // 密码验证规则
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        callback()
      }
    }
    // 验证码验证规则
    var validateCode = (rule, value, callback) => {
      let temp = 'kjkl'
      console.log(value === temp)
      if (value === '') {
        callback(new Error('请输入验证码'))
      } else if (value === temp) {
        callback()
      } else {
        callback(new Error('验证码错误'))
      }
    }
    var validateCode1 = (rule, value, callback) => {
      let v = value.toLowerCase()
      let temp = vm.codeString
      if (v === '') {
        callback(new Error('请输入验证码'))
      } else if (v === temp) {
        callback()
      } else {
        callback(new Error('验证码错误'))
      }
    }
    return {
      codeString: '',
      formInline: {
        password: '',
        username: '',
        code: '',
        codestr: ''
      },
      ruleInline: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 1, max: 15, message: '长度在 1 到 15 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { validator: validatePass, trigger: 'blur' }
        ],
        code: [
          {required: true, validator: validateCode, trigger: 'blur'}
        ],
        codestr: [
          {required: true, validator: validateCode1, trigger: 'blur'}
        ]
      }
    }
  },
  created: function () {
    this.newCodeString()
  },
  methods: {
    newCodeString: function () {
      var result = []
      let vm = this
      for (var i = 0; i < 4; i++) {
        var ranNum = Math.ceil(Math.random() * 25) // 生成一个0到25的数字
        // 大写字母'A'的ASCII是65,A~Z的ASCII码就是65 + 0~25;然后调用String.fromCharCode()传入ASCII值返回相应的字符并push进数组里
        result.push(String.fromCharCode(65 + ranNum))
      }
      vm.codeString = result.join('').toLocaleLowerCase()
    },
    handleSubmit (name) {
      this.$refs[name].validate((valid) => {
        if (valid) {
          this.$router.push('/home')
        } else {
          this.$Message.error('表单验证失败!')
        }
      })
    },
    cancelSubmit (name) {
      this.$refs[name].resetFields()
    }
  }
}
</script>
<style>
.layout-body{
  height:100%;
  margin:0px;
}
.layout-head{
  position: absolute;
  top: 5%;
  left: 50;
  margin-left:80px;

}
.layout-content{
  position: absolute;
  top: 19%;
  bottom: 0;
  left: 0;
  z-index: -1;
  width: 100%;
  height: 70%;
  overflow: hidden;
  background-image:url("../assets/bg.png"),url("../assets/bg.png");
  background-repeat: no-repeat, no-repeat, no-repeat;  
  background-position: 0 0, 200px 0, 400px 201px;
}
.layout-content-form{
  box-shadow: 8px 8px 5px #888888;
  border-radius:20px;
  position:absolute;
  right:50px;
  top:50px;
  width:400px;
  height:350px;
  background:#ffffff;
}
.tittle{
  border-top-left-radius:20px;
  border-top-right-radius:20px;
  background:#fff;
  text-align:center;
  color: #384313;
  width: 100%;
  font-size:20px;
  line-height: 65px;
  border-bottom: 1px solid #D8DEE2;

}
.form{
  margin:40px;
}
.formTitle{
  float:left;
  font-size: 18px;
  color: #1c2438;
  padding: 10px 12px;
}
.formButton{
  margin-left:-80px;
  height:42px;
}
.layout-foot{
  width:100%;
  position:absolute;
  top:90%;
  height:50px;
  line-height:50px;
  color:#666;
  background: #f6f6f6;
}
.codestring-inner{
  float:left;
  display: inline-block;
  text-align: center;
  margin-top: 1px;
  color: #20a0ff;
  background: #eaeaea;
  text-shadow: 8px 3px -0px #cacaca;
  letter-spacing: 4px;
  width: 70px;
  line-height: 36px;
  font-size: 20px;
  text-align: center;
  margin-left: 4px;
  background-color: rgba(255, 255, 255, 0.42); 
  background-image: 
  linear-gradient(90deg, transparent 79px, #abced4 79px, #abced4 81px, transparent 81px),
  linear-gradient(#eee .1em, transparent .1em);
  background-size: 9px 8px;
  vertical-align: top;
}
.c-help-text{
  color: #666;
  margin-left:-50px;
  width:20px;
}
</style>