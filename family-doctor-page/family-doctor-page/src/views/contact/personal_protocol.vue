<template>
  <div>
    <div style=" height:90%;overflow:auto;">
       <v-protocol></v-protocol>
        <Row type="flex" justify="center">
            <Button type="primary" @click="handleReturn()">返回</Button>
        </Row>
    </div>   
  </div>
</template>
<script>

import vProtocol from './protocol.vue'
export default {
  components: {
    'v-protocol': vProtocol
  },
  data () {
    return {
    }
  },
  methods: {
    handleReturn () {
      this.$router.push({path: 'contact'})
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

