<template>
  <div class="index">
    <div class="head">
      <Row type="flex" justify="start" class="code-row-bg" align="middle">
        <Col span="4">
        <img src="../../assets/1.jpg" height="80" width="100">
        </Col>
        <Col span="6" pull="1">
        <Row type="flex" class="code-row-bg" align="top">
          <Col>
          <div style="margin:5px">今天是{{date}}</div>
          </Col>
        </Row>
        <Row type="flex" class="code-row-bg" align="top">
          <Col>
          <div style="margin:5px;font-size:24px">欢迎登陆</div>
          </Col>
        </Row>
        <Row type="flex" class="code-row-bg" align="top">
          <Col>
          <div style="margin:5px">切换到公卫系统|切换到HIS系统</div>
          </Col>
        </Row>
        </Col>
        <Col span="8" offset="6">
        <Row type="flex" justify="end" class="code-row-bg">
          <Col span="8">签约居民|家庭登陆</Col>
          <Col span="6">签约医生登陆</Col>
          <Col span="10">签约机构登陆</Col>
        </Row>
        <Row type="flex" class="code-row-bg" align="top">
          <div style="margin-top:30px"> 家庭医生官方APP下载</div>
        </Row>
        </Col>
      </Row>
    </div>
  
    <div class="body">
      <Row>
        <Col span="14">
        <img src="../../assets/3.jpg" height="200" width="550">
        </Col>
        <Col span="9">
        <div style="background-color:#F2F2F2;height:300px">
          <div style="margin-left:20px">
            <Form label-position="right" :model="ruleForm"  ref="ruleForm">
              <div style="font-size:24px">签约医生登陆</div>
              <Form-item style="margin:10px">
                <Input v-model="ruleForm.username" placeholder="请输入登陆账号" style="width: 300px"></Input>
              </Form-item>
              <Form-item style="margin:10px">
                <Input v-model="ruleForm.password" placeholder="请输入登陆密码" style="width: 300px"></Input>
              </Form-item>
              <a style="margin:10px">忘了密码？</a>
              <Row>
                <Col span="6">
                  <Input size="large" type="text" placeholder="请输入验证码"   v-model="ruleForm.codestr" style="width:170px;margin:10px" @on-enter="submitForm('ruleForm')">
                  <Icon slot="prepend" type="locked"></Icon>
                  </Input>
                </Col>
                <Col span="6" push="3" style="margin:10px">
                  <span class="codestring-inner">{{codeString}}</span>
                </Col>
                <Col span="6" push="3" style="margin:10px">
                  <Button type="text"  class="c-help-text" :disabled="loading" @click="newCodeString">看不清？换一个</Button>
                </Col>
              </Row>
             
              <Form-item style="margin:5px">
                <Checkbox>自动登录</Checkbox>
              </Form-item>
              <Form-item style="margin-right:20px;margin-top:0px;margin-bottom:0px">
                <Button type="primary" long>立即登录</Button>
              </Form-item>
            </Form>
          </div>
        </div>
         <a style="float:right;margin-bottom:20px">还未注册？立即注册>></a>
        </Col>
      </Row>
    </div>
  
    <div class="foot">
      <Row>
        <Col span="5">
        <Carousel autoplay v-model="value1" :height="130">
          <Carousel-item>
            <div class="demo">
              <img src="../../assets/1.jpg" height="120">
            </div>
          </Carousel-item>
          <Carousel-item>
            <div class="demo">
              <img src="../../assets/1.jpg" height="120">
            </div>
          </Carousel-item>
        </Carousel>
        </Col>
        <Col span="5">
        <Carousel autoplay v-model="value2" :height="130">
          <Carousel-item>
            <div class="demo">
              <img src="../../assets/1.jpg" height="120">
            </div>
          </Carousel-item>
          <Carousel-item>
            <div class="demo">
              <img src="../../assets/1.jpg" height="120">
            </div>
          </Carousel-item>
        </Carousel>
        </Col>
        <Col span="8" offset="3">
        <div> 关于“家庭医生”平台使用中的热门问题：</div>
        {{question}}
        </Col>
      </Row>
    </div>
  </div>
</template>
<script>
export default {
  data () {
    return {
      state: false,
      loading: false,
      sendCodeInner: '发送验证码',
      countdown: 60,
      tar: false,
      codeString: '',
      ruleForm: {
        password: '',
        username: '',
        code: '',
        codestr: ''
      },
      date: '',
      value1: 0,
      value2: 0,
      question: '1、为何登录失败？2、如何修改密码？3、如何查看签约协议？4、如何调整家庭医生签约内容？5、如何查看服务记录？ 6、居民个人签约与家庭签约有何区别？'
    }
  },
  created: function () {
    var d = new Date()
    this.date = this.formatDate(d)
    this.newCodeString()
  },
  methods: {
    // 日期格式转换
    formatDate: function (date) {
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
    },
    newCodeString: function () {
      var result = []
      let vm = this
      for (var i = 0; i < 4; i++) {
        var ranNum = Math.ceil(Math.random() * 25) // 生成一个0到25的数字
        // 大写字母'A'的ASCII是65,A~Z的ASCII码就是65 + 0~25;然后调用String.fromCharCode()传入ASCII值返回相应的字符并push进数组里
        result.push(String.fromCharCode(65 + ranNum))
      }
      vm.codeString = result.join('').toLocaleLowerCase()
    }
  }
}
</script>
<style scoped lang="less">
.index {
  margin: 5px;
  background-color: #D7F0FB;
}

.head {
  padding: 10px;
  margin: 10px;
  border: 1px solid #797979;
  font-size: 12px;
  color: #1c2438;
  background-color: #FFFFFF;
}

.body {
  padding: 10pX;
  margin: 10pX;
  height: 50%;
  padding-bottom:20px;
  border: 1px solid #797979;
  background-color: #FFFFFF;
}

.foot {
  margin-bottom: 20px;
  padding: 10pX;
  margin: 10pX;
  height: 25%;
  border: 1px solid #797979;
  background-color: #FFFFFF;
}

.demo {
  padding-left: 35%;
}

.code-form-item {
  .ivu-form-item-content {
    display: flex;
  }
  .ivu-input-wrapper {
    width: 120px;
    text-align: center;
  }
}

.codestring-inner {
  display: inline-block;
  text-align: center;
  margin-top: 1px;
  color: #20a0ff;
  background: #eaeaea;
  text-shadow: 8px 3px -0px #cacaca;
  letter-spacing: 4px;
  width: 120px;
  line-height: 36px;
  font-size: 20px;
  text-align: center;
  margin-left: 4px;
  background-color: rgba(255, 255, 255, 0.42);
  background-image: linear-gradient(90deg, transparent 79px, #abced4 79px, #abced4 81px, transparent 81px),
  linear-gradient(#eee .1em, transparent .1em);
  background-size: 9px 8px;
  vertical-align: top;
}
</style>
