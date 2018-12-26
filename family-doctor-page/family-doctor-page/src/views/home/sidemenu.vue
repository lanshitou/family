/**
 * @title: 左侧菜单组件
 * @description: 左侧菜单组件
 * @author: TiuWeb
 */
<template>
  <nav class="side-menu" :class="{'show': !pop}">
    <Menu @on-select="clickMenuItem" unique-opened>
      <template v-for="(item,index) in menus">
        <Menu-item v-if="!item.only" :class="{'sm-active': selected===item.title}" :name='JSON.stringify(item)'>
          <i class="fa " :class="item.iconCls"></i>{{item.title}}
        </Menu-item>
        <Submenu :name="item.title" v-if="item.only">
          <template slot="title"><i class="fa " :class="item.iconCls"></i>{{item.title}}</template>
          <Menu-item v-for="child in item.children" :class="{'sm-active': selected===child.title}" :name='JSON.stringify(child)'>
            <i class="fa " :class="child.iconCls"></i>{{child.title}}
          </Menu-item>
        </Submenu>
      </template>
    </Menu>
  </nav>
</template>
<script>
  import { frameMenu, frameMenuPromise } from '../../framework/common/menu'
  import { mapState, mapActions } from 'vuex'
  import { DispatchResize, Equal } from '../../framework/utils'
  export default {
    data () {
      return {
        initMenuItem: {},
        menus: [],
        selected: '首页'
      }
    },
    created () {
      let vm = this
      frameMenuPromise.then(res => {
        this.menus = res
        let initMenuItem = {
          iconCls: res[0].iconCls,
          menuurl: res[0].menuurl,
          name: res[0].name,
          only: true,
          title: res[0].title
        }
        this.selected = initMenuItem.title
        vm.clickMenuItem(JSON.stringify(initMenuItem))
        vm.customSideMenuItem.parentTitle === undefined
        ? vm.updateBreadpath('<i class="fa ' + vm.customSideMenuItem.iconCls + '"></i> ' + vm.customSideMenuItem.title)
        : vm.updateBreadpath('<i class="fa ' + vm.customSideMenuItem.parentIconCls + '"></i> ' + vm.customSideMenuItem.parentTitle + ' &nbsp->&nbsp ' + '<i class="fa ' + vm.customSideMenuItem.iconCls + '"></i> ' + vm.customSideMenuItem.title)
        this.$router.push(initMenuItem.menuurl)
      })
    },
    computed: mapState({
      // 箭头函数可使代码更简练
      pop: state => state.slidemenu.slidemenuState,
      customSideMenu: state => state.customSideMenu,
      customSideMenuItem: state => state.customSideMenu.menuItem
    }),
    methods: {
      ...mapActions([
        'updateBreadpath',
        'mainRender',
        'updateMenuItem'
      ]),
      clickMenuItem (key) {
        let vm = this
        this.selected = JSON.parse(key).title
        this.updateMenuItem(JSON.parse(key))
        vm.customSideMenuItem.parentTitle === undefined
        ? vm.updateBreadpath('<i class="fa ' + vm.customSideMenuItem.iconCls + '"></i> ' + vm.customSideMenuItem.title)
        : vm.updateBreadpath('<i class="fa ' + vm.customSideMenuItem.parentIconCls + '"></i> ' + vm.customSideMenuItem.parentTitle + ' &nbsp->&nbsp ' + '<i class="fa ' + vm.customSideMenuItem.iconCls + '"></i> ' + vm.customSideMenuItem.title)
        this.$router.push(JSON.parse(key).menuurl)
        DispatchResize()
      }
    },
    mounted () {
      let vm = this
      vm.$nextTick(function () {
        if (Equal.isPromise(frameMenuPromise)) {
          frameMenuPromise.then(d => {
            vm.menus = d
            vm.mainRender(true)
          })
        } else {
          setTimeout(function () {
            vm.menus = frameMenu
            vm.mainRender(true)
          }, 200)
        }
      })
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
    .ivu-menu-item.ivu-menu-item-active:not(.ivu-menu-submenu) {
      color: #657180;
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
