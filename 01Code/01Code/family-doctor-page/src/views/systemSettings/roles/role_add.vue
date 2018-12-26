/**
 * @Title : 团队角色新增
 * @Description : 
 * @author : TiuWeb
 */
<template>
  <div class="" >
    <div class="layout-content"  style="height:90%; overflow:auto;">
      <div class="layout-content-main">
      </div>
        <div class="layout-content-main">
            <div class='menu'>
                <Form ref="roleModel" class="form-block" :model="roleModel" :label-width="100" :rules="roleValidate">
                    <Row type="flex" justify="start">
                        <Col span="7">
                        <Form-item label="新增角色名称" prop="rolename">
                            <Input v-model="roleModel.rolename" placeholder="请输入..." ></Input>
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
                        <Button type="primary" style="width:80px;margin-right:20px" @click="doSubmit('roleModel')">提交</Button>
                        <Button type="primary" style="width:80px;" @click="doCancel">返回</Button>
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
  insertRole
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
        { required: true, message: '角色名称不能为空', trigger: 'blur' },
        { type: 'string', max: 32, message: '角色名称不能多于32个字符', trigger: 'blur' }
        ],
        jobdescription: [
        { required: true, message: '角色职责不能为空', trigger: 'blur' }
        ],
        jobqualification: [
        { required: true, message: '任职资格不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    doSubmit (roleModel) {
      this.$refs['roleModel'].validate((valid) => {
        if (valid) {
          insertRole(this.roleModel).then((res) => {
            if (res.status === 200) {
              this.$Message.success('新增成功!')
              this.$router.push({ path: 'roles' })
            } else {
              this.$Message.error('新增失败!')
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
