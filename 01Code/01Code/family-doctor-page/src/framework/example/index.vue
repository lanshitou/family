<template>
  <div class="layout">
    <Menu mode="horizontal" theme="dark" active-name="1">
      <div class="layout-logo">组件</div>
      <div class="layout-nav">
        <!--<Menu-item name="1">
          <Icon type="ios-navigate"></Icon>
          导航一
        </Menu-item>
        <Menu-item name="2">
          <Icon type="ios-keypad"></Icon>
          导航二
        </Menu-item>
        <Menu-item name="3">
          <Icon type="ios-analytics"></Icon>
          导航三
        </Menu-item>
        <Menu-item name="4">
          <Icon type="ios-paper"></Icon>
          导航四
        </Menu-item>-->
      </div>
    </Menu>
    <!--<Menu mode="horizontal" active-name="1">
      <div class="layout-assistant">
        <Menu-item name="1">二级导航</Menu-item>
        <Menu-item name="2">二级导航</Menu-item>
        <Menu-item name="3">二级导航</Menu-item>
      </div>
    </Menu>-->
    <div class="layout-content">
      <Row>
        <i-col :xs="24" :md="4" :lg="3">
          <Menu active-name="badge" width="auto" @on-select="jumpTo">
            <Menu-item name="badge">图片标签</Menu-item>
            <Menu-item name="grid">级联表格</Menu-item>
            <Menu-item name="excel">导入excel</Menu-item>
            <Menu-item name="spiner">加载动画</Menu-item>
            <Menu-item name="schedule">日程表</Menu-item>
            
          </Menu>
        </i-col>
        <i-col :xs="24" :md="20" :lg="21">
          <div class="layout-content-main">
            <router-view></router-view>
          </div>
        </i-col>
      </Row>
    </div>
    <div class="layout-copy">
      2011-2016 &copy; TalkingData
    </div>
  </div>
</template>
<script>
  export default {
    methods: {
      jumpTo (name) {
        this.$router.replace('/example/' + name)
      }
    }
  }

</script>
<style scoped>
  .layout {
    flex: 1;
    border: 1px solid #d7dde4;
    background: #f5f7f9;
  }

  .layout-logo {
    width: 100px;
    height: 30px;
    /*background: #5b6270;*/
    border-radius: 3px;
    font-size: 26px;
    float: left;
    position: relative;
    top: 15px;
    left: 20px;
    line-height: 30px;
    text-align: center;
    color: #fff;
  }

  .layout-nav {
    width: 420px;
    margin: 0 auto;
  }

  .layout-assistant {
    width: 300px;
    margin: 0 auto;
    height: inherit;
  }

  .layout-breadcrumb {
    padding: 10px 15px 0;
  }

  .layout-content {
    min-height: 200px;
    margin: 15px;
    overflow: hidden;
    background: #fff;
    border-radius: 4px;
  }

  .layout-content-main {
    padding: 10px;
  }

  .layout-copy {
    position: absolute;
    bottom: 12px;
    left: 0;
    right: 0;
    text-align: center;
    padding: 10px 0 20px;
    color: #9ea7b4;
  }
</style>
