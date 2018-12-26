<template>
  <div class="head">
    <div class="headText">医疗保健信息管理系统</div>
    <div style="float:right"><Userinfo></Userinfo></div>
  </div>
</template>
<script>
  import Userinfo from '../../../components/userinfo.vue'
  // import { mapActions } from 'vuex'
  export default {
    name: 'navigation',
    components: {
      Userinfo
    },
    data () {
      return {
        show: true
      }
    },
    methods: {
      // ...mapActions([
      //   'closeSlidemenu',
      //   'openSlidemenu'
      // ]),
      // togslide: function () {
      //   if (this.show) {
      //     this.closeSlidemenu()
      //   } else {
      //     this.openSlidemenu()
      //   }
      //   this.show = !this.show
      // }
    }
  }
</script>
<style scoped>
.head{
  height:50px;
  background-color: #5e87b0;
}
.headText{
  float:left;
  color: #fff;
  text-align:left;
  line-height:50px; 
  padding-left:20px;
  font-size: 20px;
}
</style>
