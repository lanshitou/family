/**
 * @title: 右侧tabs页
 * @description: 右侧tabs页
 * @author: TiuWeb
*/
<template>
  <div class="main-content" :class="{'slide-right':pop}">
    <!--<vue-tabs class="tabs-content"></vue-tabs>-->
    <div class="breadcrumbs-bar">
        <span v-html='breadpath' class="breadcrumbs"></span>
        <Button type='text' @click="refreshHandler"><i class="fa fa-refresh"></i></Button>
    </div>
    <transition mode="out-in"  enter-active-class=" bounceInLeft" leave-active-class="bounceOutRight">
      <router-view class="tabs-content animated"></router-view>
    </transition>
  </div>
</template>

<script>
  import { mapState } from 'vuex'
  export default {
    data () {
      return {}
    },
    computed: mapState({
      // 箭头函数可使代码更简练
      pop: state => !state.slidemenu.slidemenuState,
      breadpath: state => state.slidemenu.breadpath
    }),
    methods: {
      refreshHandler: function () {
        this.$router.go(0)
      }
    }
  }

</script>

<style lang='scss' scoped>
  .el-tabs__item {
    color: #fff
  }
  
  .main-content {
    position: absolute;
    top: 50px;
    right: 0;
    bottom: 0;
    left: 200px;
    overflow: hidden;
    z-index: 1;
    background-color: #fff;
    /*border-left: 1px solid #f4f4f4;
    padding-left: 15px;*/
    transition: left .45s cubic-bezier(.23, 1, .32, 1);
    .tabs-content {
      height: 100%;
    }
  }
  
  .panels {
    height: 40px;
    background: #fff;
    margin-left: -16px;
  }
  
  .panels {
    span {
      display: inline-block;
      line-height: 40px;
      color: #48576a;
      font-size: 14px;
      padding-left: 15px;
      i:last-child {
        color: #20a0ff
      }
    }
  }
  
  .slide-right {
    left: 0;
    margin-left: 0px;
    background-color: #fff;
    border-left: 1px solid #f4f4f4;
    transition: left .45s cubic-bezier(.23, 1, .32, 1);
  }
  // 面包屑样式
  .breadcrumbs-bar{
    position: relative;
    .breadcrumbs{
      display: inline-block;
      width: 100%;
      padding:10px 20px; 
      border-bottom: 1px #cdd2d2 solid;
      color: #333;
      font-size: 14px;
      background-color: #F5F5F5;
    }
    .ivu-btn{
      position: absolute;
      right: 20px; 
      top: 0;
    }
  }
</style>
