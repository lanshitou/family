<template>
  <div>
    <div style=" height:90%;overflow:auto;">
      <v-protocol></v-protocol>
      <Row type="flex" justify="center">
            <Button type="primary" @click="handleDoctor()" style="margin:8px">医生确认</Button>
            <Button type="primary" @click="handleReturn()" style="margin:8px">返回</Button>
      </Row>
    </div>
    <div class="layout-copy">
       2011-2016 &copy; TalkingData
    </div> 
      <!--医生确认弹框1-->
      <Modal v-model="modal1" title="医生确认" @on-ok="saveProtocol" @on-cancel="cancelProtocol">
        <div class='filterTitle'>是否确认接收此份家庭医生签约协议?</div>
      </Modal>
      <!--医生确认弹框2-->
      <Modal v-model="modal2" title="  " @on-ok="saveProtocol2" @on-cancel="cancelProtocol2">
        <div class='filterTitle'>审批成功，签约协议即时生效 ！</div>
        <div class='filterTitle'>是否进入下一条待审批签约？</div>
        <router-link :to="{ path: 'plans'}">查看新增签约服务计划</router-link>
      </Modal>
      <!--续约确认弹框1-->
      <Modal v-model="modal3" title="续约确定" @on-ok="saveExam" @on-cancel="cancelExam">
        <div class='filterTitle'>是否确认续签此份“家庭医生”签约服务？</div>
        <div class='filterTitle'>提示：如需变更，请您申请新的签约!</div>
        <router-link :to="{ path: 'add'}">申请新的签约</router-link>
      </Modal>
      <!--续约确认弹框2-->
      <Modal v-model="modal4" title="  " @on-ok="saveExam2" @on-cancel="cancelExam2">
        <div class='filterTitle'>续约成功</div>
        <router-link :to="{ path: 'plans'}">查看新增签约服务计划</router-link>
      </Modal>
  </div>
</template>
<script>
import {
  approval
} from '../../api/api'
import vProtocol from './protocol.vue'
export default {
  components: {
    'v-protocol': vProtocol
  },
  data () {
    return {
      modal1: false,
      modal2: false,
      modal3: false,
      modal4: false,
      id: ''
    }
  },
  created () {
    this.id = this.$route.params.id
  },
  methods: {
    // 医生确认生效
    handleDoctor () {
      // 弹框1
      this.modal1 = true
    },
    saveProtocol () {
      this.$Message.info('点击了医生确定')
      let para = {
        id: this.id
      }
      this.$Loading.start()
      approval(para).then((res) => {
        if (res.status === 200) {
          this.modal2 = true
        }
      })
    },
    cancelProtocol () {
      this.$Message.info('点击了取消')
    },
    saveProtocol2 () {
      this.$Message.info('点击了确定')
    },
    cancelProtocol2 () {
      this.$router.push({path: 'contact'})
    },
    // 修改/解除/取消
    handleReturn () {
      this.$router.go(-1)
      // 弹框1
      // this.$router.push({path: 'contact'})
    },
    // 续签
    handleGo () {
      // 弹框1
      this.modal3 = true
    },
    saveExam () {
      this.$Message.info('点击了确定')
      this.modal4 = true
    },
    cancelExam () {
      this.$Message.info('点击了取消')
    },
    saveExam2 () {
      this.$Message.info('点击了确定')
    },
    cancelExam2 () {
      this.$Message.info('点击了取消')
    },
    // 终止
    handleOver () {
      this.$Modal.confirm({
        title: '终止签约',
        content: '<p>是否确认终止此份“家庭医生”签约服务？</p>',
        onOk: () => {
          this.$Message.info('点击了确定')
        },
        onCancel: () => {
          this.$Message.info('点击了取消')
        }
      })
    }
  }
}
</script>
<style  scoped lang="less" >
.layout-copy {
  text-align: center;
  color: #9ea7b4;
}
</style>
