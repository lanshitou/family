/** * @Title : 系统导航 * @Description : 系统导航用户信息 * @author : TiuWeb */
<template>
  <div class="rightbar">
    <div class="user-center" placement="bottom-end">
      <span class="text-white user-head">
        <img class="userPic" @click="changeModel('个人资料')" :src="(systemUser || systemUser.userphoto==='')?imgUrl:systemUser.userphoto" height="40" width="40" :alt="systemUser.username"> {{systemUser.username}}
      </span>
      <span class="text-white user-head" >
        <div class="layout-ceiling">
            <div class="layout-ceiling-main">
              <Tooltip content="账号安全设置" style="margin-right:3px">
                <Icon type="gear-b" size="25"  @click.native="changeModel('账号安全设置')"></Icon>
              </Tooltip>
              <Tooltip content="提醒设置" style="margin-right:3px">
                <Icon type="email-unread" size="25"  @click.native="changeModel('提醒设置')"></Icon>
              </Tooltip>
            </div>
        </div>
      </span>
      <span class="user-head" @click="closesys">
        <Icon class="closePic" type="android-exit" ></Icon>
      </span>
    </div>
  </div>
</template>
<script>
  import { mapState, mapActions } from 'vuex'
  import { logout } from '../../api/api'
  export default {
    computed: mapState({
      // 箭头函数可使代码更简练
      systemUser: state => state.user
    }),
    data () {
      return {
        model1: '',
        imgUrl: require('../../assets/person.png')
      }
    },
    methods: {
      ...mapActions([
        'updateBreadpath',
        'userlogin',
        'userloginout'
      ]),
      changeModel: function (change) {
        let vm = this
        if (change === '个人资料') {
          this.$router.push({ path: '/personal' })
          vm.updateBreadpath('<i class="' + 'ivu-icon ivu-icon-person' + '"></i> ' + '个人资料')
        }
        if (change === '账号安全设置') {
          this.$router.push({ path: '/personal_safe' })
          vm.updateBreadpath('<i class="' + 'ivu-icon ivu-icon-gear-b' + '"></i> ' + '账号安全设置')
        }
        if (change === '提醒设置') {
          this.$router.push({ path: '/personal_remind' })
          vm.updateBreadpath('<i class="' + 'ivu-icon ivu-icon-email-unread' + '"></i> ' + '提醒设置')
        }
      },
      closesys: function () {
        let vm = this
        vm.userloginout()
        vm.$Modal.confirm({
          title: '提示',
          content: '是否退出当前子系统?',
          onOk: function () {
            logout().then((res) => {
              if (res.status === 200) {
                vm.$Message.success('已退出，请重新登录')
                setTimeout(function () {
                  location.href = 'http://192.168.25.143/sso/#/login'
                }, 2000)
              }
            })
          },
          onCancel: function () {
            vm.$Message.warning('已取消关闭')
          },
          type: 'error'
        })
      }
    }
  }

</script>
<style lang="scss">
 .layout-ceiling{
    overflow: hidden;
    }
    .layout-ceiling-main{
      margin-top: 5px;
      color: #ffffff;
      float: right;
    }
  @mixin nav_a($flg) {
    @if $flg==true {
      color: #fff;
    }
    text-decoration: none;
  }

  .rightbar {
    .user-center {
      float: right;
      padding: 0 15px;
      .user-head {
        cursor: pointer;
        display: block;
        float: left;
        /*padding: 0 10px;*/
        /*background-color: #80a0c1;*/
        .userPic {
          float: left;
          height: 30px;
          width: 30px;
          border-radius: 100%;
          margin: 10px 5px 10px 0;
        }
        .closePic {
          &:hover {
            /*color: #efefef;*/
            font-size: 28px;
            transform: rotateZ(360deg);
          }
          float: left;
          height: 24px;
          width: 24px;
          font-size: 24px;
          border-radius: 100%;
          margin: 13px 5px 11px;
          /*color: #b9c5d2;*/
          transition: all .3s ease;
        }
      }
    }
  }

</style>
