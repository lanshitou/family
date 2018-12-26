<template>
  <div class="layout">>
    <div class="layout-content">
      <span class="headline">团队明细</span>
      <span @click="back" class="buttonBack">>>返回上一页</span>
      <div class="layout-content-main">
        <Table border :columns="columns7" :data="data6"></Table>
      </div>
    </div>
    <!--信息编辑弹出框-->
    <Modal v-model="updateModel" title="团队信息编辑" class="update-model">
        <Form  :label-width="80">
        <Form-item label="角色">
            <Select v-model="updateData.roleName" >
                <Option value="全科医生">全科医生</Option>
                <Option value="公卫医师">公卫医师</Option>
                <Option value="中医医师">中医医师</Option>
                <Option value="护士">护士</Option>
            </Select>
        </Form-item>
        <Form-item label="成员姓名">
            <Select v-model="updateData.memberName" >
                <Option value="孙杨">孙杨</Option>
                <Option value="李建">李建</Option>
                <Option value="张三">张三</Option>
            </Select>
        </Form-item>
    </Form>
    </Modal>
  </div>
</template>
<script>
export default {
  data () {
    return {
      updateModel: false,
      updateData: {
        roleName: '',
        memberName: '',
        roleDuty: ''
      },
      updateIndex: 0,
      columns7: [
        {
          title: '角色',
          key: 'roleName',
          render: (h, params) => {
            return h('div', [
              h('strong', params.row.roleName)
            ])
          }
        },
        {
          title: '团队成员姓名',
          key: 'memberName'
        },
        {
          title: '角色职责',
          key: 'roleDuty'
        },
        {
          title: '操作',
          key: 'action',
          width: 150,
          align: 'center',
          render: (h, params) => {
            return h('div', [
              h('Button', {
                props: {
                  type: 'primary',
                  size: 'small'
                },
                style: {
                  marginRight: '5px'
                },
                on: {
                  click: () => {
                    this.show(params.index)
                  }
                }
              }, '修改')
            ])
          }
        }
      ],
      data6: [
        {
          roleName: '全科医生',
          memberName: '孙杨',
          roleDuty: '职责说明 职责说明 职责说明'
        },
        {
          roleName: '公卫医师',
          memberName: '李建',
          roleDuty: '职责说明 职责说明 职责说明'
        },
        {
          roleName: '中医医师',
          memberName: '张三',
          roleDuty: '职责说明职责说明 职责说明'
        },
        {
          roleName: '护士',
          memberName: '孙杨',
          roleDuty: '职责说明 职责说明 职责说明'
        }
      ]
    }
  },
  methods: {
    back () {
      this.$router.push({ path: '../appointMent_sign_plans' })
    },
    show (index) {
      this.updateData.roleName = this.data6[index].roleName
      this.updateData.memberName = this.data6[index].memberName
      this.updateData.roleDuty = this.data6[index].roleDuty
      this.updateModel = true
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
  background: #f5f7f9;
  position: relative;
  border-radius: 4px;
  overflow: auto;
}

.layout-breadcrumb {
  padding: 5px 15px 0;
}

.layout-content {
  margin: 10px;
  min-height: 200px;
  overflow: hidden;
  background: #fff;
  border-radius: 4px;
}

.layout-content-main {
  padding: 5px;
}

.layout-copy {
  text-align: center;
  padding: 10px 0 20px;
  color: #9ea7b4;
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
  color:#0066FF;
}
.buttonSign {
  padding: 5px;
  float: left;
  color:#0066FF;
}
</style>
