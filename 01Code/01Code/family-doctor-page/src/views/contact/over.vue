<template>
  <div>
    <div style=" height:90%;overflow:auto;">
      <v-protocol></v-protocol>
      <Row type="flex" justify="center">
            <Button type="primary" @click="handleOver()" style="margin:8px">解除</Button>
            <Button type="primary" @click="handleReturn()" style="margin:8px">返回</Button>
        </Row>
    </div> 
  </div>
</template>
<script>
import {
  relieve
} from '../../api/api'
import vProtocol from './protocol.vue'
export default {
  components: {
    'v-protocol': vProtocol
  },
  data () {
    return {
      id: ''
    }
  },
  created () {
    this.id = this.$route.params.id
  },
  methods: {
    handleReturn () {
      this.$router.push({path: 'contact'})
    },
    handleOver () {
      var that = this
      // 弹框
      this.$Modal.confirm({
        title: '',
        content: '<p>是否确认解除此份“家庭医生”签约服务？</p>',
        onOk: () => {
          that.$Message.info('点击了确定')
          let par = {
            id: that.id
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

