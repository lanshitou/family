/**
 * @title: 左侧菜单组件
 * @description: 左侧菜单组件
 * @author: TiuWeb
 */
<template>
  <nav class="side-menu" :class="{'show': !pop}">
    <Menu @on-select="clickMenuItem" unique-opened ref="climbMenu">
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
        let initMenuItem = {
          iconCls: res[0].iconCls,
          menuurl: res[0].menuurl,
          name: res[0].name,
          only: true,
          title: res[0].title
        }
        this.selected = initMenuItem.name
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
