/**
 * @title: 导航
 * @description: 导航信息
 * @author: TiuWeb
*/
<template>
  <div class="climb-con">
    <Row>
      <i-col :span="6" class="c-nav-left">
        <div id="sidebar-collapse" class="sidebar-collapse btn" @click="togslide" :t="show">
          <i class="fa fa-bars" data-icon1="fa fa-bars" data-icon2="fa fa-bars"></i>
        </div>
        <Logo></Logo>
      </i-col>
      <i-col :span="18" class="rightbar">
        <Userinfo></Userinfo>
      </i-col>
    </Row>
  </div>
</template>

<script>
  import Logo from '../../components/navigation/logo.vue'
  import Userinfo from '../../components/navigation/userinfo.vue'
  import {DispatchResize} from '../../framework/utils'
  import { mapActions } from 'vuex'

  export default {
    name: 'navigation',
    components: {
      Logo,
      Userinfo
    },
    data () {
      return {
        show: true
      }
    },
    methods: {
      ...mapActions([
        'closeSlidemenu',
        'openSlidemenu'
      ]),
      togslide: function () {
        if (this.show) {
          this.closeSlidemenu()
        } else {
          this.openSlidemenu()
        }
        this.show = !this.show
        DispatchResize()
        // return this.show
      }
    }
  }

</script>
<style lang="scss" scoped>
  @mixin nav_a($flg) {
    @if $flg==true {
      color: #fff;
    }
    text-decoration: none;
  }
  
  .climb-con {
    position: relative;
    height: 50px;
    line-height: 50px;
    // background: none repeat scroll 0 0 #5e87b0;
    border: none;
    border-radius: 0;
    margin: 0;
    .c-nav-left{
      height: 50px;
    }
    .btn {
      display: inline-block;
      margin-bottom: 0;
      font-weight: normal;
      text-align: center;
      vertical-align: middle;
      cursor: pointer;
      background-image: none;
      border-width: 1px;
      border-style: solid;
      /*border: 1px solid transparent;*/
      white-space: nowrap;
      padding: 6px 12px;
      font-size: 14px;
      line-height: 1.428571429;
      border-radius: 4px;
      -webkit-user-select: none;
      -moz-user-select: none;
      -ms-user-select: none;
      -o-user-select: none;
      user-select: none;
    }
    .sidebar-collapse {
      padding: 10px 10px 0 0;
      position: absolute;
      top: 10px;
      left: 10px;
      /*border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);*/
      box-shadow: 0 1px 0 rgba(255, 255, 255, 0.2) inset, 0 1px 2px rgba(0, 0, 0, 0.05);
      color: #FFFFFF;
      padding: 6px 8px;
    }
    .sidebar-collapse:hover {
      /*background-color: #507aa4;*/
    }
    .fa {
      display: inline-block;
      font-family: FontAwesome;
      font-style: normal;
      font-weight: normal;
      line-height: 1;
      -webkit-font-smoothing: antialiased;
      -moz-osx-font-smoothing: grayscale;
    }
    .sidebar-collapse>[class*="fa-"],
    .switcher>[class*="fa-"] {
      color: #FFFFFF;
      cursor: pointer;
      display: inline-block;
      padding: 0 5px;
      position: relative;
      float: right;
    }
  }
</style>
