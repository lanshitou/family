<template>
  <nav class="side-menu">
    <Menu style="width:200;text-align:left" @on-select="clickMenuItem" ref="sideMenu" unique-opened  active-name='organization'>
      <MenuItem name="organization">
        <Row>
          <Col span="2"><Icon type="ios-home" :size="18"></Icon></Col>
          <Col span="8"offset="2">保健机构</Col>
        </Row>
      </MenuItem>
      <MenuItem name="object">
        <Row>
          <Col span="2"><Icon type="ios-people" :size="18"></Icon></Col>
          <Col span="8"offset="2">保健对象</Col>
        </Row>
      </MenuItem>
      <MenuItem name="person">
        <Row>
          <Col span="2"><Icon type="android-contacts" :size="18"></Icon></Col>
          <Col span="8"offset="2">保健人员</Col>
        </Row>
      </MenuItem>
        <MenuItem name="task">
          <Row>
            <Col span="2"><Icon type="card" :size="18"></Icon></Col>
            <Col span="8"offset="2">保健任务</Col>
          </Row> 
        </MenuItem>
        <MenuItem name="dictionaries">
          <Row>
            <Col span="2"><Icon type="ios-paper" :size="18"></Icon></Col>
            <Col span="8"offset="2">术语字典</Col>
          </Row>     
        </MenuItem>
        <MenuItem name="users">
          <Row>
            <Col span="2"><Icon type="person" :size="18"></Icon></Col>
            <Col span="8"offset="2">用户管理</Col>
          </Row>     
        </MenuItem>
        <MenuItem name="roles">
          <Row>
            <Col span="2"><Icon type="ios-paper" :size="18"></Icon></Col>
            <Col span="8"offset="2">角色管理</Col>
          </Row>     
        </MenuItem>
    </Menu>
    <br>
  </nav>
</template>
<script>
  export default {
    data () {
      return {
      }
    },
    created: function () {
      this.clickMenuItem('organization')
    },
    methods: {
      clickMenuItem (key) {
        var url = '/' + key
        this.$router.push({ path: url })
      }
    }
  }
</script>
<style lang='scss'>
  .side-menu {
    position: relative;
    width: 100%;
    left: 0;
    top: 0;
    color: #282828;
    .ivu-menu-item.is-active,
    .ivu-menu-item.is-active {
      color: #48576a;
    }
    .ivu-menu-vertical .ivu-menu-item,
    .ivu-menu-vertical .ivu-menu-submenu-title {
      padding: 0 0 0 10px;
    }
    .ivu-menu-vertical {
      .ivu-menu-item.sm-active,
      .ivu-menu-item.sm-active {
        color: #20a0ff;
      }
    }
    .ivu-menu {
      background: #f4f4f4;
      .fa {
        margin-right: 10px;
        speak: none;
        font-style: normal;
        font-weight: 400;
        font-variant: normal;
        text-transform: none;
        line-height: 1;
        vertical-align: baseline;
        display: inline-block;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
      }
    }
    &>.ivu-menu,
    &>.ivu-menu>.ivu-menu-item:last-child {
      border-bottom: 1px solid #e5e5e5;
    }
    .ivu-menu-submenu {
      line-height: 35px;
    }
    .ivu-menu-item,
    .ivu-menu-submenu {
      border-top: 1px solid #e5e5e5;
    }
    .ivu-menu-submenu .ivu-menu-item {
      background: #f4f4f4 !important;
      border-top: 0;
      padding-left: 34px;
    }
    .ivu-menu-item,
    .ivu-menu-submenu .ivu-menu-submenu-title {
      height: 35px;
      line-height: 35px;
    }
    .ivu-menu-item:hover {
      background-color: #fff;
    }
    .ivu-menu-submenu .ivu-menu-item:hover,
    .ivu-menu-submenu-title:hover {
      background-color: #fff;
    }
    .ivu-menu-submenu .ivu-menu-item:hover,
    .ivu-menu-submenu-title:hover {
      background-color: #fff;
    }
    .ivu-menu-vertical .ivu-menu-submenu-title-icon {
      top: 12px;
    }
  }

  .side-menu {
    padding: 0 !important;
    position: absolute;
    top: 50px;
    bottom: 0;
    left: 0;
    width: 200px;
    padding-top: 10px;
    z-index: 1;
    overflow: auto;
    background-color: rgba(238, 238, 238, 0.98);
    _background-color: rgb(238, 238, 238);
    border-right: 1px solid #e5e5e5;
    transition: all .45s cubic-bezier(.23, 1, .32, 1);
    .ivu-menu-submenu .ivu-menu-item:hover,
    .ivu-menu-submenu-title:hover {
      background-color: #fff;
    }
  }

  .side-menu.show {
    left: -200px;
  }
</style>
