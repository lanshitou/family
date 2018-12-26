/**
 * @title: 团队所有成员展示
 * @description: 团队所有成员展示，添加修改删除团队成员
 * @author: TiuWeb
*/
<template>
  <div>
    <div style="margin:2px">
      <div class="layout-content-main">
        <div style="background:#f3f3f3;padding:2px;text-align:right">
          <Button-group>
            <Button type="primary" style="margin:1px" @click="back">返回</Button>
          </Button-group>
        </div>
        <Table border :columns="columns" :data="teamList" @on-selection-change="select" border></Table>
        <!--团队成员添加弹出框-->
        <Modal v-model="addModel" title="团队成员添加"  class="update-model" @on-ok="addTeamMember">
          <Form :label-width="80" ref="addData" :model="addData" :rules="formValidate">
            <Form-item label="成员姓名" prop="doctoridentity">
              <Select v-model="addData.doctoridentity"  style="width:150px" placeholder="请选择团队成员" @on-change="changeM" :label-in-value="true">
                <Option v-for="item in teammember" :value="item.id" :key="item.id">{{ item.doctorname }}</Option>
              </Select>
            </Form-item>
            <Form-item label="角色" prop="doctorroleidentity">
              <Select :model="addData.doctorroleidentity" style="width:150px" placeholder="请选择角色名称" @on-change="changeR"  :label-in-value="true">
                <Option v-for="(item,index) in teamrolename" :value="index" :key="item.id">{{ item.rolename }}</Option>
              </Select>
            </Form-item>
            <Form-item label="角色职责" prop="jobdescription">
              <Input v-model="addData.jobdescription" style="width: 250px" readonly></Input>
            </Form-item>
            <Form-item label="是否是队长">
              <Checkbox v-model="addData.iscaptain" >是</Checkbox>
            </Form-item>
          </Form>
        </Modal>
        <!--团队成员编辑弹出框-->
        <Modal v-model="updateModel" title="团队成员编辑" class="update-model" @on-ok="saveTeamMember">
          <Form :label-width="80">
            <Form-item label="成员姓名">
              {{updateData.doctorname}}
              <!--<Input v-model="" readonly></Input>-->
              <!--<Select v-model="updateData.doctoridentity" placeholder="请选择团队成员" @on-change="changeUM" :label-in-value="true">
                <Option v-for="item in teammember" :value="item.id" :key="item.id">{{ item.doctorname }}</Option>
              </Select>-->
            </Form-item>
            <Form-item label="角色">
              <Select style="width:200px" v-model="updateData.doctorroleidentity" placeholder="请选择角色名称" @on-change="changeUR" :label-in-value="true">
                <Option v-for="item in teamrolename" :value="item.id" :key="item.id">{{ item.rolename }}</Option>
              </Select>
            </Form-item>
             <Form-item label="是否是队长">
              <Checkbox v-model="updateData.iscaptain" >是</Checkbox>
            </Form-item>
          </Form>
        </Modal>
        <button class="c-table-btn user-head"  @click="add">新增成员</button>
      </div>
    </div>
  </div>
</template>
<script>
import {
  getTeamsMember, findTeamsByKey, updateTeamsMember, getAllRoles, getDocotrByTeam, insertTeamsMember, delTeamsMember
} from '../../api/api'
export default {
  data () {
    return {
      index: 0, // 责任医生服务优先级
      teamId: '',
      updateModel: false,
      addModel: false,
      updateData: {
        id: '',
        doctoridentity: '',
        doctorname: '',
        doctorroleidentity: '',
        doctorrolename: '',
        iscaptain: false // 是否是队长
      },
      addData: {
        doctoridentity: '',
        doctorname: '',
        doctorroleidentity: '',
        doctorrolename: '',
        medicalteamidentity: '',
        medicalteamname: '',
        teamdoctorresponsibilityorder: 0,
        jobdescription: '',
        iscaptain: false // 是否是队长
      },
      // 校验
      formValidate: {
        doctoridentity: [
        { required: true, message: '成员姓名不能为空', trigger: 'blur' }
        ],
        doctorroleidentity: [
        { required: true, message: '角色不能为空', trigger: 'blur' }
        ],
        jobdescription: [
        { required: true, message: '角色职责不能为空', trigger: 'blur' }
        ]
      },
      teammember: [],
      teamrolename: [],
      institutionList: [],
      listData: [],
      teamList: [],
      selections: [],
      columns: [
        {
          type: 'selection',
          width: 60,
          key: 'id',
          align: 'center'
        },
        {
          title: '角色',
          key: 'doctorrolename'
        },
        {
          title: '团队成员姓名',
          key: 'doctorname'
        },
        {
          title: '角色职责',
          key: 'roleduty'
        },
        {
          title: '操作',
          key: 'action',
          width: 150,
          align: 'center',
          render: (h, params) => {
            return h('div', [
              h('Tooltip', {
                props: {
                  placement: 'top'
                }
              }, [
                h('Button', {
                  props: {
                    type: 'ghost',
                    size: 'small',
                    shape: 'circle',
                    icon: 'compose'
                  },
                  style: {
                    marginLeft: '5px'
                  },
                  on: {
                    click: () => {
                      this.update(params.index)
                    }
                  }
                }),
                h('div', {
                  slot: 'content'
                }, [
                  h('div', [
                    h('div', '修改')
                  ])
                ])
              ]),
              h('Tooltip', {
                props: {
                  placement: 'top'
                }
              }, [
                h('Button', {
                  props: {
                    type: 'ghost',
                    size: 'small',
                    shape: 'circle',
                    icon: 'trash-a'
                  },
                  style: {
                    marginLeft: '5px'
                  },
                  on: {
                    click: () => {
                      this.remove(params.row.id)
                    }
                  }
                }),
                h('div', {
                  slot: 'content'
                }, [
                  h('div', [
                    h('div', '删除')
                  ])
                ])
              ])
            ])
          }
        }
      ]
    }
  },
  created: function () {
    this.teamId = this.$route.path.substring(11) // 截取url后的参数
    let para = {
      medicalteamidentity: this.teamId
    }
    // 获取团队成员
    getTeamsMember(para).then((res) => {
      this.teamList = res.data
      this.index = res.data.length
      this.addData.teamdoctorresponsibilityorder = this.index
    })
    let data = {
      id: this.teamId
    }
    // 根据团队主键查询团队所属医疗机构
    findTeamsByKey(data).then((res) => {
      this.addData.medicalteamidentity = res.data.id
      this.addData.medicalteamname = res.data.teamname
      var institutionidentity = res.data.institutionidentity
      let para = {
        institutionidentity: institutionidentity
      }
      // 根据医疗机构ID查询医生信息
      getDocotrByTeam(para).then((res) => {
        this.teammember = res.data
      })
    })
    // 查询所有医生角色信息
    getAllRoles().then((res) => {
      this.teamrolename = res.data
    })
  },
  methods: {
    // 添加成员弹出框
    add () {
      this.addModel = true
    },
    // 添加成员保存数据
    addTeamMember () {
      var captaincount = 0
      this.$refs['addData'].validate((valid) => {
        if (valid) {
          if (this.addData.iscaptain) {
            captaincount = 1
          }
          for (var i = 0; i < this.teamList.length; i++) {
            if (this.teamList[i].iscaptain) {
              captaincount = captaincount + 1
            }
          }
          // 没有队长
          if (captaincount === 0) {
            this.$Message.error('请选择队长!')
          } else if (captaincount > 1) {
            this.$Message.error('该团队已有队长，请勿重复选择!')
          } else {
            insertTeamsMember(this.addData).then((res) => {
              if (res.status === 200) {
                this.$Message.success('添加成功')
                this.index = 0
                let para = {
                  medicalteamidentity: this.teamId
                }
                getTeamsMember(para).then((res) => { // 重新加载数据渲染页面
                  this.teamList = res.data
                })
              } else {
                this.$Message.error('添加失败')
                this.index = 0
              }
            })
          }
        } else {
          this.$Message.error('团队成员信息验证失败!')
          this.index = 0
          this.loading = false
        }
      })
    },
    // 返回
    back () {
      this.$router.push({ path: '/teams' })
    },
    // 添加团队成员select选择器选中的Option变化时触发
    changeM (change) {
      this.addData.doctoridentity = change.value
      this.addData.doctorname = change.label
    },
    // 添加角色select选择器选中的Option变化时触发
    changeR (change) {
      var index = change.value
      this.addData.doctorroleidentity = this.teamrolename[index].id
      this.addData.doctorrolename = change.label
      this.addData.jobdescription = this.teamrolename[index].jobdescription
    },
    // 修改弹出框
    update (index) {
      this.updateData.id = this.teamList[index].id
      this.updateData.doctorname = this.teamList[index].doctorname
      this.updateData.doctorroleidentity = this.teamList[index].doctorroleidentity
      this.updateData.iscaptain = this.teamList[index].iscaptain
      this.updateModel = true
    },
    // 删除
    remove (id) {
      var th = this
      this.$Modal.confirm({
        title: '团队成员信息',
        content: `是否删除此记录`,
        onOk: function () {
          let para = { id: id }
          th.$Loading.start()
          delTeamsMember(para).then((res) => {
            if (res.status === 200) {
              th.$Message.success('删除成功!')
              th.$Loading.start()
              let para = {
                medicalteamidentity: th.teamId
              }
              getTeamsMember(para).then((res) => { // 重新加载数据渲染页面
                th.teamList = res.data
              })
            } else {
              th.$Message.error('删除失败！')
            }
          })
        }
      })
    },
    // // 修改团队成员select选择器选中的Option变化时触发
    // changeUM (change) {
    //   this.updateData.doctorname = change.label
    // },
    // 修改角色select选择器选中的Option变化时触发
    changeUR (change) {
      this.updateData.doctorrolename = change.label
    },
    // 保存修改数据
    saveTeamMember () {
      var captaincount = 0
      if (this.updateData.iscaptain) {
        captaincount = 1
      }
      for (var i = 0; i < this.teamList.length; i++) {
        if (this.teamList[i].iscaptain) {
          captaincount = captaincount + 1
        }
      }
      // 没有队长
      if (captaincount === 0) {
        this.$Message.error('请选择队长!')
      } else if (captaincount > 1) {
        this.$Message.error('该团队已有队长，请勿重复选择!')
      } else {
        updateTeamsMember(this.updateData).then((res) => {
          if (res.status === 200) {
            this.$Message.success('更新成功')
            var id = this.$route.params.id
            let para = {
              medicalteamidentity: id
            }
            getTeamsMember(para).then((res) => {
              this.teamList = res.data
            })
          } else {
            this.$Message.error('更新失败')
          }
        })
      }
    },
    select (selection) {
      this.selections = selection
    }
  }
}
</script>
<style scoped lang="less">
.index {
  padding: 2px;
  margin-top: 5px;
  margin-bottom: 10PX;
}

.layout {
  border: 1px solid #d7dde4;
  position: relative;
  border-radius: 4px;
  overflow: auto;
}
.layout-content {
  margin: 10px;
  min-height: 200px;
  overflow: hidden;
  border-radius: 4px;
}

.layout-content-main {
  padding: 5px;
}
.breadcrumb {
  font-size: 16px;
  color: #464c5b;
  font-family: 'PingFang SC';
}

.headline {
  padding: 10px;
  font-size: 16px;
  color: #657180;
}

.buttonBack {
  padding: 5px;
  float: right;
  color: #0066FF;
}

.buttonSign {
  padding: 5px;
  float: left;
  color: #0066FF;
}

.c-table-btn {
  position: fixed;
  right: 18px;
  bottom: 18px;
  z-index: 99;
  width: 86px;
  height: 86px;
  font-size: 18px;
  background-color: #2d8cf0;
  color: #ffffff;
  border-radius: 50%;
  border: none;
  outline: none;
}
.user-head {
  cursor: pointer;
  display: block;
  float: left;
  /*padding: 0 10px;*/
  /*background-color: #80a0c1;*/
  .userPic {
    float: left;
    height: 30px;
    width: 30px;
    border-radius: 100%;
    margin: 10px 5px 10px 0;
  }
  .closePic {
    &:hover {
      /*color: #efefef;*/
      font-size: 28px;
      transform: rotateZ(360deg);
    }
    float: left;
    height: 24px;
    width: 24px;
    font-size: 24px;
    border-radius: 100%;
    margin: 13px 5px 11px;
    /*color: #b9c5d2;*/
    transition: all .3s ease;
  }
}
</style>
