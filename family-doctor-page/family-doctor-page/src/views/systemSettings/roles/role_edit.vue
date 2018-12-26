/**
 * @Title : 团队角色修改
 * @Description : 
 * @author : TiuWeb
 */
<template>
  <div>
    <div class="layout-content"  style="height:90%; overflow:auto;">
      <div class="layout-content-main">
      </div>
        <div class="layout-content-main">
            <div class='role'>
                <Form ref="roleModel" class="form-block" :model="roleModel" :label-width="100" :rules="roleValidate">
                    <Row type="flex" justify="start">
                        <Col span="7">
                        <Form-item label="角色名称" prop="rolename">
                            <Input v-model="roleModel.rolename"></Input>
                        </Form-item>
                        </Col>
                    </Row>
                    <Row type="flex" justify="start">
                        <Col span="20">
                        <Form-item label="角色职责说明" prop="jobdescription">
                            <Input v-model="roleModel.jobdescription" type="textarea" :autosize="{minRows: 5}" size="large"></Input>
                        </Form-item>
                        </Col>
                    </Row>
                    <Row type="flex" justify="start">
                        <Col span="20">
                        <Form-item label="任职资格|标准" prop="jobqualification">
                            <Input v-model="roleModel.jobqualification" type="textarea" :autosize="{minRows: 6}" size="large"></Input>
                        </Form-item>
                        </Col>
                    </Row>
                    <Row type="flex" justify="center">
                        <Button type="primary" style="width:80px;margin-right:20px" @click="doSubmit('roleModel')">确认</Button>
                        <Button type="primary" style="width:80px;" @click="doCancel">返回</Button>
                    </Row>
                </Form>
            </div>
        </div>
    </div>
  </div>
</template>
<script>
import {
  getRolesByKey, updateRole
} from '../../../api/api'
export default {
  data () {
    return {
      roleModel: {
        rolename: '',
        jobdescription: '',
        jobqualification: ''
      },
        // 校验
      roleValidate: {
        rolename: [
        { required: true, max: 500, message: '科室名称不能为空', trigger: 'blur' },
        { type: 'string', max: 32, message: '角色名称不能多于16个字', trigger: 'blur' }
        ],
        jobdescription: [
        { required: true, max: 500, message: '关键字不能为空', trigger: 'blur' }
        ],
        jobqualification: [
        { required: true, max: 500, message: '年龄条件不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  created: function () {
    let para = {
      id: this.$route.params.id
    }
    this.$Loading.start()
    getRolesByKey(para).then((res) => {
      this.$Loading.finish()
      this.roleModel = res.data
    })
  },
  methods: {
    doSubmit (roleModel) {
      this.$refs['roleModel'].validate((valid) => {
        if (valid) {
          updateRole(this.roleModel).then((res) => {
            if (res.status === 200) {
              this.$Message.success('修改成功!')
              this.$router.push({ path: 'roles' })
            } else {
              this.$Message.error('修改失败!')
            }
          })
        } else {
          this.$Message.error('角色信息验证失败!')
        }
      })
    },
    doCancel () {
      this.$router.push({path: '/roles'})
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
.role {
  flex-direction: row;
  align-items: center;
  font-size: 36px;
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
</style>
