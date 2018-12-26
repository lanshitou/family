<template>
  <div class="index">
    <Table :context="self" @on-selection-change="select" border :columns="columns7" :data="data6"></Table>
  </div>
</template>
<script>
export default {
  data () {
    return {
      self: this,
      selections: [],
      updateModel: false,
      updateData: {
        roleName: '',
        memberName: '',
        roleDuty: ''
      },
      updateIndex: 0,
      columns7: [
        {
          type: 'selection',
          width: 60,
          key: 'id',
          align: 'center'
        },
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
</style>
