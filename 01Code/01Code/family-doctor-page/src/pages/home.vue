/** * @title: 主页面 * @description: 包含导航和左侧菜单及其右侧内容区 * @author: TiuWeb */
<template>
  <div class="main">
    <Navigation></Navigation>
    <transition name="component-fade" mode="out-in">
      <!--<router-view></router-view>-->
      <div class="container-fluid">
        <Sidemenu class="sidebar"></Sidemenu>
        <MainCont></MainCont>
      </div>
    </transition>
  </div>
</template>

<script>
  import Navigation from '../views/layout/navigation.vue'
  import Sidemenu from '../views/home/sidemenu.vue'
  const MainCont = resolve => require(['../views/home/main.vue'], resolve)
  export default {
    components: {
      Navigation,
      Sidemenu,
      MainCont
    }
  }

</script>

<style lang='scss'>

  .main {
    position: absolute;
    bottom: 0;
    width: 100%;
    background: #fff;
    height: 100%;
  }

  .tabs-content-wrapper {
    .ivu-table-wrapper {
      .ivu-table {
        table {
          width: 100%;
        }
      }
    }
  }
  .side-menu {
    .ivu-menu-item:hover {
      background-color: #fff !important;
    }
    .ivu-menu-submenu .ivu-menu-item:hover,
    .ivu-menu-submenu-title:hover {
      background-color: #fff !important;
    }
    .ivu-menu-submenu .ivu-menu-item:hover,
    .ivu-menu-submenu-title:hover {
      background-color: #fff !important;
    }
  }
</style>
