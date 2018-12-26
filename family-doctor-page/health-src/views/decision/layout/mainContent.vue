<template>
  <div class="main-content">
    <transition mode="out-in"  enter-active-class=" bounceInLeft" leave-active-class="bounceOutRight">
      <router-view class="tabs-content animated"></router-view>
    </transition>
  </div>
</template>
<style lang='scss' scoped>
  .el-tabs__item {
    color: #fff
  }

  .main-content {
    width:85%;
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
</style>
