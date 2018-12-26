<template>
  <div>
    <div style=" height:90%;overflow:auto;">
       <v-protocol></v-protocol>
        <Row type="flex" justify="center">
            <Button v-if="formModel.applystatus === 0" type="primary" @click="handleDoctor()" style="margin:18px">医生确认</Button>
            <Button v-if="formModel.applystatus === 1" type="primary" @click="handleOver" style="margin:18px">终止</Button>
            <Button v-if="formModel.applystatus === 3 || formModel.applystatus === 2" type="primary" @click="xuqian" style="margin:18px">续签</Button>
            <Button type="primary" @click="handleReturn()" style="margin:18px">返回</Button>
        </Row>
    </div>   
    <!--医生确认弹框1-->
      <Modal v-model="modal1" title="医生确认" @on-ok="saveProtocol">
        <div class='filterTitle'>是否确认接收此份家庭医生签约协议?</div>
      </Modal>
  </div>
</template>
<script>
import {
    findContactByKey, renew, approval, relieve
} from '../../api/api'
// 引入组件
import vProtocol from './protocol.vue'
export default {
  components: {
    'v-protocol': vProtocol
  },
  data () {
    return {
      /**
       * 1:服务计划页面
      */
      backtype: '',
      id: '',
      modal1: false,
      // 申请信息
      formModel: {
      }
    }
  },
  created () {
    this.id = this.$route.params.id
    this.backtype = this.$route.params.backtype
    // console.log(this.$route.params.id)
    let para = {
      id: this.$route.params.id
    }
    this.$Loading.start()
    findContactByKey(para).then((res) => {
      this.formModel = res.data
    })
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
        id: this.formModel.id
      }
      this.$Loading.start()
      approval(para).then((res) => {
        if (res.status === 200) {
          this.$router.push({path: 'contact'})
        }
      })
    },
    // 解除
    handleOver () {
      var that = this
      // 弹框
      this.$Modal.confirm({
        title: '',
        content: '<p>是否确认解除此份“家庭医生”签约服务？</p>',
        onOk: () => {
          that.$Message.info('点击了确定')
          let par = {
            id: that.formModel.id
          }
          relieve(par).then((res) => {
            if (res.status === 200) {
              that.$Message.info('解除成功')
              that.$router.push({path: 'contact'})
            }
          })
        },
        onCancel: () => {
          that.$Message.info('点击了取消')
        }
      })
    },
    handleReturn () {
      this.backvue()
    },
    xuqian () {
      var ths = this
      this.$Modal.confirm({
        title: '续约',
        content: `是否对当前协议进行续约？`,
        onOk: function () {
          let para = {
            id: ths.formModel.id
          }
          ths.$Loading.start()
          renew(para).then((res) => {
            if (res.status === 200) {
              ths.$Message.info('续约成功！')
              ths.backvue()
            }
          })
        }
      })
    },
    backvue () {
      if (this.backtype === '1') {
        this.$router.push({name: 'appointment/plan', params: {id: this.id}})
      } else {
        this.$router.push({path: 'contact'})
      }
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

