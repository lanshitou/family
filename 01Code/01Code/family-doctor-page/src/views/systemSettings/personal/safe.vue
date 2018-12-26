<template>
  <Row type="flex" justify="center">
    <Col>
    <Form ref="editPasswordForm" :model="editPasswordForm" :rules="ruleValidate" :label-width="80">
      <Form-item label="原密码" prop="oldpasswd" style="margin-top:30px">
        <Input type="password" v-model="editPasswordForm.oldpasswd" style="width:200px"></Input>
      </Form-item>
      <Form-item label="新密码" prop="passwd" style="margin-top:30px">
        <Input type="password" v-model="editPasswordForm.passwd" style="width:200px"></Input>
      </Form-item>
      <Form-item label="确认密码" prop="passwdCheck" style="margin-top:30px">
        <Input type="password" v-model="editPasswordForm.passwdCheck" style="width:200px"></Input>
      </Form-item>
      <Form-item>
        <Button type="primary"  @click="handleSave('editPasswordForm')">提交</Button>
        <Button type="ghost" @click="handleReset('editPasswordForm')" style="margin-left: 8px">重置</Button>
      </Form-item>
    </Form>
    </Col>
  </Row>
</template>
<script>
import {
  updatePwd
} from '../../../api/api'
export default {
  data () {
    const validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        let ru = /^[a-zA-Z]\w{5,18}$/
        if (this.editPasswordForm.passwdCheck !== '') {
          // 对第二个密码框单独验证
          this.$refs.editPasswordForm.validateField('passwdCheck')
        }
        if (ru.test(value)) {
          callback()
        } else {
          callback(new Error('新密码以字母开头，包含字符、数字和下划线，至少6位'))
        }
      }
    }
    const validatePassCheck = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.editPasswordForm.passwd) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      editPasswordForm: {
        oldpasswd: '',
        passwd: '',
        passwdCheck: ''
      },
      ruleValidate: {
        oldpasswd: [
          { required: true, message: '原密码不能为空', trigger: 'blur' },
          { type: 'string', max: 32, message: '原密码不能超过32个字符', trigger: 'blur' }
        ],
        passwd: [
          { required: true, message: '新密码不能为空', trigger: 'blur' },
          { validator: validatePass, trigger: 'blur' }
        ],
        passwdCheck: [
          { required: true, message: '确认密码不能为空', trigger: 'blur' },
          { validator: validatePassCheck, trigger: 'blur' }
        ]
      }
    }
  },
  props: ['resetFields'],
  watch: {
    resetFields: function () {
      this.handleReset('editPasswordForm')
    }
  },
  methods: {
    // 保存密码
    handleSave: function (name) {
      let vm = this
      vm.$refs[name].validate((valid) => {
        if (valid) {
          let para = {
            searchParams: {
              password: vm.editPasswordForm.oldpasswd,
              newpassword: vm.editPasswordForm.passwd
            }
          }
          updatePwd(para).then((res) => {
            if (res.status === 200) {
              vm.$Message.success('密码修改成功')
              setTimeout(function () {
                location.href = 'http://192.168.25.143/sso/#/login'
              }, 2000)
            } else {
              vm.$Message.error('密码修改失败')
            }
          })
        } else {
          vm.$Message.error('密码信息验证失败!')
        }
      })
    },
    // 重置密码
    handleReset: function (name) {
      this.$refs[name].resetFields()
    }
  }
}
</script>
