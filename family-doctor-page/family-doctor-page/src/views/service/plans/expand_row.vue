<style scoped>
.expand-row {
  margin-bottom: 16px;
}
</style>
<template>
  <div style="text-align:center">
    <Form ref="row"  class="form-block"  :model="row" :label-width="130">
      <Row class="expand-row">
        <Col span="6">
        <Form-item label="本次医疗服务内容:" prop="servicecategoryname">
          {{ row.servicecategoryname }} 
        </Form-item>
        </Col>
        <Col span="12">
        <Form-item label="服务内容详情:" prop="detail">
          <Span v-if="row.detail.length < 20">
              {{row.detail.substring(0,20)}}...
          </Span>
          <Tooltip placement="bottom-end" v-if="row.detail.length > 20"  content="这里是提示文字">
              {{row.detail.substring(0,20)}}...
             <div slot="content">
                  <p>{{row.detail.substring(0,20)}}</p>
                  <p>{{row.detail.substring(20,40)}}</p>
                  <p>{{row.detail.substring(40,60)}}</p>
                  <p>{{row.detail.substring(60,80)}}</p>
                  <p>{{row.detail.substring(80,100)}}</p>
                  <p>{{row.detail.substring(100,120)}}</p>
                  <p>{{row.detail.substring(120,140)}}</p>
                  <p>{{row.detail.substring(140,160)}}</p>
                  <p>{{row.detail.substring(160,180)}}</p>
                  <p>{{row.detail.substring(180,200)}}</p>
                  <p>{{row.detail.substring(200,220)}}</p>
              </div>
         </Tooltip>
        </Form-item>
        </Col>
      </Row>
      <Row class="expand-row">
        <Col span="6">
        <Form-item label="本次医疗服务方式:" prop="isappointment">
          {{row.isappointment===true ? '预约就诊' : '上门服务' }}
        </Form-item>
        </Col>
        <Col span="6" v-if="row.status ===3">
          <Form-item label="本次医疗服务费用:" prop="serviceCost">
           {{ row.serviceCost }}
          </Form-item>
        </Col>
      </Row>
      <Row class="expand-row">
        <Col span="6">
          <Form-item label="本次医疗人员:" prop="doctorname">
           {{ row.doctorname }}
          </Form-item>
        </Col>
        <Col span="6" v-if="row.status !== 3">
          <Button type="primary" @click="changeDoctor">更换医生</Button>
        </Col>
      </Row>
      <!--信息编辑弹出框-->
      <Modal v-model="updateModel" title="更换医生" class="update-model" @on-ok="ok">
        <Form :label-width="80">
          <Table :context="self" @on-selection-change="select" border :columns="columns7" :data="listData"></Table>
        </Form>
      </Modal>
    </Form>
  </div>
</template>
<script>
const initPage = {
  servcie_item_detail_identity: 1
}
import {
  doctorPage, upadtePlan
} from '../../../api/api'
export default {
  props: {
    row: Object
  },
  data () {
    return {
      isappointment: '',
      updateModel: false,
      self: this,
      selections: [],
      listData: [],
      columns7: [
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
          key: 'roleDuty'
        }
      ]
    }
  },
  watch: {
    searchInfo: function () {
      let para = {
        page: 1,
        rows: 10,
        servcie_item_detail_identity: 1
      }
      this.$Loading.start()
      doctorPage(para).then((res) => {
        this.$Loading.finish()
        this.listData = res.data
      })
    }
  },
  created: function () {
    this.$Loading.start()
    doctorPage(initPage).then((res) => {
      this.$Loading.finish()
      this.listData = res.data
    })
  },
  methods: {
    // 打开更换医生弹出框
    changeDoctor () {
      let para = {
        servcie_item_detail_identity: 1
      }
      this.$Loading.start()
      doctorPage(para).then((res) => {
        this.$Loading.finish()
        this.listData = res.data
      })
      this.updateModel = true
    },
    // 更换医生保存
    ok () {
      let self = this
      var id = 0
      var name = ''
      self.selections.forEach(function (element) {
        id = element.id // 弹出框当前选中的医生的id
        name = element.doctorname // 弹出框当前选中的医生的name
      }, this)
      var params = {
        id: this.row.id, // 签约服务id
        doctoridentity: id, // 签约服务医生外键
        doctorname: name // 签约服务医生姓名
      }
      upadtePlan(params).then((res) => {
        if (res.status === 200) {
          this.$Message.success('更换医生成功')
          this.row.doctorname = params.doctorname // 重新渲染页面显示的医生姓名
        } else {
          this.$Message.error('更换医生失败')
        }
      })
    },
    select (selection) {
      this.selections = selection
    }
  }
}
</script>
