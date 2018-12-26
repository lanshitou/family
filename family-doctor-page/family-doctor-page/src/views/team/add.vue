<template>
    <div style="margin-top:20px;margin-left:200px">
      <div class="layout-content-main">
        <div class='columnBox menu'>
          <div>
            <Form ref="addForm" label-position="right" :model="addForm" :rules="ruleValidate" :label-width="200">
              <Row>
                <Col span="6" pull="4">
                  <Form-item label="当前医疗机构"></Form-item>
                </Col>
                <Col span="18" pull="8">
                  <Form-item prop="institutionname">
                    {{addForm.institutionname}}
                  </Form-item>
                </Col>
              </Row>
              <Row>
                <Col span="6" pull="4">
                  <Form-item label="团队名称">
                  </Form-item>
                </Col>
                <Col span="18" pull="8">
                  <Form-item prop="teamname">
                    <Input placeholder="请输入团队名称" style="width:200px" v-model="addForm.teamname"></Input>
                  </Form-item>
                </Col>
              </Row>
            </Form>           
            <Form ref="formDynamic" label-position="right" :model="formDynamic" :label-width="200">
              <Row>
                <Col span="6" pull="4">
                  <Form-item label="家庭医生所需服务人员及角色" style="margin:0px"></Form-item>
                </Col>
                <Col span="18" pull="8">
                  <Form-item style="margin:0px" v-for="(item, index) in formDynamic.items" :key="item" :label="(index + 1)+'、'" 
              :prop="'items.' + index + '.doctorname'"
                 :rules="{required: true, message: '成员' + (index + 1) +'姓名不能为空', trigger: 'blur'}">
                <Row>
                  <Col span="7">
                  <Select style="width:130px" :model="item.doctorname" placeholder="请选择成员名称" :label-in-value="true" @on-change="changeM">
                    <Option v-for="item in teammember" :value="item.id" :key="item">{{ item.doctorname }}</Option>
                  </Select>
                  </Col>
                  <Col span="7" push="1">
                  <Select style="width:130px" :model="item.doctorrolename" placeholder="请选择角色名称" :label-in-value="true" @on-change="changeR">
                    <Option v-for="item in teamrolename" :value="item.id" :key="item">{{ item.rolename }}</Option>
                  </Select>
                  </Col>
                  <Col span="4" push="2">
                  <span>是否是队长</span>
                  </Col>
                  <Col span="3" push="2">
                    <Checkbox v-model="item.iscaptain" >是</Checkbox>
                  </Col>
                  <Col span="2" push="2">
                  <Button type="ghost" @click="handleRemove(index)">删除</Button>
                  </Col>
                </Row>
              </Form-item>
                </Col>
              </Row>
              <Form-item style="margin-top: 25px">
                <Row>
                  <Col span="10">
                  <Button type="dashed" long @click="handleAdd" icon="plus-round">新增</Button>
                  </Col>
                </Row>
              </Form-item>
              <Row>
                <Col>
                  <Form-item style="margin:5px">
                    <Button type="primary" @click="saveTeam('formDynamic', 'addForm')">提交</Button>
                    <Button type="primary" style="margin-left: 8px" @click="cancle">返回</Button>
                  </Form-item>
                </Col>
              </Row>
            </Form>
            </div>
          </div>
      </div>
    </div>
</template>
<script>
import {
  getAllRoles, getDocotrByTeam, insertTeam, findCurrentOrg
} from '../../api/api'
export default {
  data () {
    return {
      index: 0, // 责任医生服务优先级
      changeMedical: false,
      saveModel: true,
      teammember: [],
      teamrolename: [],
      institutionList: [],
      model1: '',
      addForm: {
        institutionidentity: '',
        institutionname: '',
        teamname: ''
      },
      formDynamic: {
        items: [
          {
            doctoridentity: '',
            doctorname: '',
            doctorroleidentity: '',
            doctorrolename: '',
            teamdoctorresponsibilityorder: 0,
            iscaptain: false // 是否是队长
          }
        ]
      },
      ruleValidate: {
        institutionname: [
          { required: true, message: '签约医生中未查询到当前用户所属机构', trigger: 'blur' }
        ],
        teamname: [
          { required: true, message: '团队名称不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  created: function () {
    // 查询当前登录用户所属机构
    findCurrentOrg().then((res) => {
      let para = {
        institutionidentity: res.data.id
      }
    // 获得当前医疗机构下的所有签约医生
      getDocotrByTeam(para).then((res) => {
        this.teammember = res.data
      })
      this.addForm.institutionidentity = res.data.id
      this.addForm.institutionname = res.data.orgname
    })
    // 查询全部医疗团队角色信息
    getAllRoles().then((res) => {
      this.teamrolename = res.data
    })
  },
  methods: {
    // 取消
    cancle () {
      this.$router.push({ path: '/teams' })
    },
    // 切换所属医疗机构输入框
    changeOrg () {
      if (this.changeMedical) {
        this.changeMedical = false
      } else {
        this.changeMedical = true
      }
    },
    // 保存团队信息
    saveTeam (name, addForm) {
      this.$refs[addForm].validate((valid) => {
        if (valid) {
          this.$refs[name].validate((valid) => {
            if (valid) {
              var captaincount = 0
              this.formDynamic.items.forEach(function (element) {
                if (element.iscaptain === true) {
                  captaincount = captaincount + 1
                }
              })
              // 没有队长
              if (captaincount === 0) {
                this.$Message.error('请选择队长!')
              } else if (captaincount > 1) {
                this.$Message.error('只能选择一个队长!')
              } else {
                let para = {
                  institutionidentity: this.addForm.institutionidentity,
                  institutionname: this.addForm.institutionname,
                  teamname: this.addForm.teamname,
                  member: this.formDynamic.items
                }
                insertTeam(para).then((res) => {
                  if (res.status === 200) {
                    this.$Message.success('添加成功!')
                    this.$router.push({ path: '/teams' })
                  } else {
                    this.$Message.error('添加失败!')
                  }
                })
              }
            } else {
              this.$Message.error('表单验证失败!')
            }
          })
        } else {
          this.$Message.error('表单验证失败!')
        }
      })
    },
    // 医疗机构select选择器选中的Option变化时触发
    changeI (change) {
      this.institutionidentity = change.value
      this.institutionname = change.label
    },
    // 团队成员select选择器选中的Option变化时触发
    changeM (change) {
      this.formDynamic.items[this.index].doctoridentity = change.value
      this.formDynamic.items[this.index].doctorname = change.label
    },
    // 角色select选择器选中的Option变化时触发
    changeR (change) {
      this.formDynamic.items[this.index].doctorroleidentity = change.value
      this.formDynamic.items[this.index].doctorrolename = change.label
    },
    handleReset (name) {
      this.$refs[name].resetFields()
    },
    handleAdd () {
      this.index = this.index + 1
      this.formDynamic.items.push({
        doctoridentity: '',
        doctorname: '',
        doctorroleidentity: '',
        doctorrolename: '',
        teamdoctorresponsibilityorder: this.index,
        iscaptain: false // 是否是队长
      })
    },
    handleRemove (index) {
      this.index = this.index - 1
      this.formDynamic.items.splice(index, 1)
    }
  }
}
</script>
<style scoped lang="less">
.filterTitle {
  font-size: 14px;
  color: #1c2438;
  padding: 10px 12px;
}

.menu {
  flex-direction: row;
  align-items: center;
}

.columnBox {
  display: -webkit-flex;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
}

.pages-block {
  margin: 0px;
  overflow: hidden;
  margin-top: 10px;
  margin-bottom: 10px;
  text-align: center;
  .pages-right {
    vertical-align: middle;
  }
}
.layout-content-main {
  padding: 5px;
}
</style>
