/**
 * @title: 修改签约医生信息
 * @description: 修改签约医生信息
 * @author: TiuWeb
*/
<template>
  <div class="index">
    <div class="layout-content">
      <div class="layout-content-main">
        <div class='columnBox menu'>
          <div class="index">
            <Form ref="listData" label-position="right" :model="listData" :label-width="150" :rules="ruleValidate">
              <Row>
                <Col span="12">
                <Form-item label="姓名" prop="doctorname">
                  <Input v-model="listData.doctorname" style="width: 80px"></Input>
                </Form-item>
                </Col>
                <Col span="8" pull="4">
                <Form-item label="性别">
                  <Radio-group v-model="listData.sex">
                    <Radio label="1">男</Radio>
                    <Radio label="2">女</Radio>
                  </Radio-group>
                </Form-item>
                </Col>
              </Row>
              <Row>
                <Col span="12">
                <Form-item label="年龄" prop="age">
                  <Input v-model="listData.age" style="width: 80px"></Input>
                </Form-item>
                </Col>
                <Col span="8" pull="4">
                <Form-item label="证件号码" prop="doctorscertificate">
                  <Input v-model="listData.doctorscertificate" style="width: 150px"></Input>
                </Form-item>
                </Col>
              </Row>
              <Row>
                <Col span="12">
                <Form-item label="用户名">
                  {{listData.paccount}}
                </Form-item>
                </Col>
                <Col span="8" pull="4">
                <Form-item label="头像设置">
                  <Upload action="//jsonplaceholder.typicode.com/posts/">
                    <Button type="ghost" icon="camera">上传</Button>
                  </Upload>
                </Form-item>
                </Col>
              </Row>
              <Row>
                <Col span="12">
                <Form-item label="邮箱" prop="email">
                  <Input v-model="listData.email" style="width: 150px"></Input>
                </Form-item>
                </Col>
                <Col span="8" pull="4">
                <Form-item label="联系电话" prop="paccount">
                  <Input v-model="listData.paccount" style="width: 150px"></Input>
                </Form-item>
                </Col>
              </Row>
              <Row>
                <Col span="12">
                <Form-item label="所属机构联系地址" prop="medicalinstitutionaddress">
                  <Input type="textarea" v-model="listData.medicalinstitutionaddress" :rows="3" style="width:250px"></Input>
                </Form-item>
                </Col>
                <Col span="8" pull="4">
                <Form-item label="医生简介" prop="doctordesc">
                  <Input type="textarea" v-model="listData.doctordesc" :rows="3" style="width:250px"></Input>
                </Form-item>
                </Col>
              </Row>
              <Form-item style="margin:10px">
                <Button type="primary" @click="saveDoctor('listData')">提交</Button>
                <Button type="primary" style="margin-left: 8px" @click="back">返回</Button>
              </Form-item>
            </Form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import {
  findDocotrByKey, updateDoctor
} from '../../api/api'
export default {
  data () {
    return {
      doctorId: '',
      listData: {},
      updateData: {
        id: '',
        doctorname: '',
        sex: '',
        age: '',
        doctorscertificate: '',
        paccount: '',
        telephone: '',
        email: '',
        medicalinstitutionaddress: '',
        doctordesc: ''
      },
      ruleValidate: {
        paccount: [
          { required: true, message: '联系电话不能为空', trigger: 'blur' },
          { type: 'string', max: 25, message: '联系电话不能超过50个字符', trigger: 'blur' }
        ],
        doctorname: [
          { required: true, message: '姓名不能为空', trigger: 'blur' },
          { type: 'string', max: 16, message: '姓名不能超过32个字符', trigger: 'blur' }
        ],
        institutionname: [
          { required: true, message: '医疗机构不能为空', trigger: 'blur' }
        ],
        doctorscertificate: [
          { required: true, message: '执业医师证编号不能为空', trigger: 'blur' },
          { type: 'string', max: 16, message: '执业医师证编号不能超过32个字符', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '邮箱不能为空', trigger: 'blur' },
          { type: 'email', message: '邮箱格式不正确', trigger: 'blur' }
        ],
        medicalinstitutionaddress: [
          { required: true, message: '医疗机构地址不能为空', trigger: 'change' },
          { type: 'string', max: 250, message: '医疗机构地址不能超过500个字符', trigger: 'blur' }
        ],
        sex: [
          { required: true, message: '请选择性别', trigger: 'change' }
        ],
        certificatenumber: [
          { required: true, message: '证件号码不能为空', trigger: 'blur' },
          { type: 'string', max: 50, message: '证件号码不能超过100个字符', trigger: 'blur' }
        ],
        doctordesc: [
          { required: true, message: '请输入医生简介', trigger: 'blur' },
          { type: 'string', max: 250, message: '医生简介不能超过500个字符', trigger: 'blur' }
        ],
        professionallevelidentity: [
          { required: true, message: '请选择医生职级', trigger: 'blur' }
        ],
        certificateidentity: [
          { required: true, message: '请选择证件类型', trigger: 'blur' }
        ]
      }
    }
  },
  created: function () {
    this.$Loading.start()
    this.doctorId = this.$route.path.substring(13) // 截取url后的参数
    let para = {
      id: this.doctorId
    }
    // 根据id查询医生信息
    findDocotrByKey(para).then((res) => {
      this.$Loading.finish()
      this.listData = res.data
    })
  },
  methods: {
    // 保存修改数据
    saveDoctor (listData) {
      this.$refs[listData].validate((valid) => {
        if (valid) {
          updateDoctor(this.listData).then((res) => {
            if (res.status === 200) {
              this.$Message.success('更新成功')
              this.$router.push({ path: '/doctors' })
            } else {
              this.$Message.error('更新失败')
            }
          })
        } else {
          this.$Message.error('医生信息验证失败!')
        }
      })
    },
    // 返回
    back () {
      this.$router.push({ path: '/doctors' })
    }
  }
}
</script>
<style scoped lang="less">
.index {
  height: 85%;
  overflow: auto
}

.layout-content-main {
  padding: 10px;
}

.layout-content {
  min-height: 78%;
  margin-top: 15px;
  overflow: hidden;
  background: #fff;
  border-radius: 4px;
}
</style>
