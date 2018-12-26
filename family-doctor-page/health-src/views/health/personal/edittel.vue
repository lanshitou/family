<template>
    <div class="layout-body">
        <div class="layout-body-breadcrumb">
            <Breadcrumb>
                <BreadcrumbItem href="object">
                    <Icon type="ios-gear"></Icon> 个人中心
                </BreadcrumbItem>
                <BreadcrumbItem href="organization_edit">
                    <Icon type="social-buffer-outline"></Icon>手机号修改
                </BreadcrumbItem>
            </Breadcrumb>
        </div>
        <div class="layout-head">
        </div>
        <div class="layout-content">
            <div class="layout-content-form">
                <Form :model="formItem" :label-width="150">
                    <Row type="flex" justify="center">
                        <Col span="12">
                        <FormItem label="原手机号码">
                            <span style="float:left;">{{formItem.tel}}</span>
                        </FormItem>
                        </Col>
                    </Row>
                    <Row type="flex" justify="center">
                        <Col span="12">
                        <FormItem label="新手机号码">
                            <Input v-model="formItem.newpwd" placeholder="请输入" style="width:150px;float:left"></Input>
                            <Button style="background:#5e87b0;color:#fff" @click="check" :disabled="showbt">{{timeText}}</Button>
                        </FormItem>
                        </Col>
                    </Row>
                    <Row type="flex" justify="center">
                        <Col span="12">
                        <FormItem label="验证码">
                            <Input v-model="formItem.pwd" placeholder="请输入" style="width:150px;float:left"></Input>
                        </FormItem>
                        </Col>
                    </Row>
                    <Row type="flex" justify="center">
                        <Col span="12" pull="2">
                        <FormItem>
                            <Button style="background:#5e87b0;color:#fff" @click="submit">确定</Button>
                            <Button style="background:#5e87b0;color:#fff" @click="cancel">取消</Button>
                        </FormItem>
                        </Col>
                    </Row>
                </Form>
            </div>
        </div>
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
      formItem: {
        tel: '15398624578',
        newtel: '',
        pwd: ''
      }
    }
  },
  created: function () {
  },
  methods: {
      //  验证码
    update () {
      if (this.timer <= 0) {
        this.timer = 60
        clearInterval(this.Interval)
        this.showbt = false
        this.timeText = '点击获取验证码'
      } else {
        this.timer--
        this.showbt = true
        this.timeText = '验证码' + this.timer + '秒有效'
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
    submit () {
      this.$router.push({path: 'personal'})
    },
    cancel () {
      this.$router.push({path: 'personal'})
    },
    check () {
      this.startTimer()
    }
  }
}
</script>
<style scoped>
    .layout-body {
        background: #f8f8f9;
    }

    .layout-body-breadcrumb {
        text-align: left;
        font-size: 36px;
    }

    .layout-content {
        border-radius: 10px;
        width: 80%;
        margin: 20px;
        background: #ffffff;
        border: 1px solid #dddee1;
    }

    .layout-content-form {
        padding: 20px;
        padding-bottom: 50px;
    }
</style>