<template>
    <div class="layout-body">
        <div class="layout-body-breadcrumb">
            <Breadcrumb>
                <BreadcrumbItem href="object">
                    <Icon type="ios-gear"></Icon> 个人中心
                </BreadcrumbItem>
                <BreadcrumbItem href="organization_edit">
                    <Icon type="social-buffer-outline"></Icon>密码修改
                </BreadcrumbItem>
            </Breadcrumb>
        </div>
        <div class="layout-head">
        </div>
        <div class="layout-content">
            <div class="layout-content-form">
                <Form :model="formItem" :label-width="150" :rules="ruleCustom">
                    <Row type="flex" justify="center">
                        <Col >
                        <FormItem label="原密码" prop="pwd">
                                <Input  v-model="formItem.pwd" placeholder="请输入"  type="password"></Input>
                        </FormItem>
                        </Col>
                    </Row>
                    <Row type="flex" justify="center">
                        <Col >
                        <FormItem label="新密码" prop="newpwd">
                                <Input  v-model="formItem.newpwd" placeholder="请输入"  type="password"></Input>
                        </FormItem>
                        </Col>
                    </Row>
                    <Row type="flex" justify="center">
                        <Col >
                        <FormItem label="再次输入新密码" prop="newpwdcheck">
                                <Input  v-model="formItem.newpwdcheck" placeholder="请输入"  type="password"></Input>
                        </FormItem>
                        </Col>
                    </Row>
                    <Row type="flex" justify="center">
                        <FormItem>
                                <Button style="background:#5e87b0;color:#fff" @click="submit">确定</Button>
                                <Button style="background:#5e87b0;color:#fff" @click="cancel">取消</Button>
                        </FormItem>
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
    const validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入原始密码'))
      } else {
        callback()
      }
    }
    const validateNewPass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        if (this.formItem.newpwdcheck !== '') {
            // 对第二个密码框单独验证
          this.$refs.formItem.validateField('newpwdcheck')
        }
        callback()
      }
    }
    const validatePassCheck = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.formItem.newpwd) {
        callback(new Error('两次密码输入不一致!'))
      } else {
        callback()
      }
    }
    return {
      formItem: {
        pwd: '',
        newpwd: '',
        newpwdcheck: ''
      },
      ruleCustom: {
        pwd: [
            { validator: validatePass, trigger: 'blur' }
        ],
        newpwd: [
            { validator: validateNewPass, trigger: 'blur' }
        ],
        newpwdcheck: [
            { validator: validatePassCheck, trigger: 'blur' }
        ]
      }
    }
  },
  created: function () {
  },
  methods: {
    submit () {
      this.$router.push({path: 'personal'})
    },
    cancel () {
      this.$router.push({path: 'personal'})
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