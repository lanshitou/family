/** * @Title : 系统导航 * @Description : 系统导航用户信息 * @author : TiuWeb */
<template>
  <div class="rightbar">
    <div class="user-center" placement="bottom-end">
      <span class="text-white user-head">
        <img class="userPic" src="../assets/person.png" height="40" width="40" @click="changeModel()" >
      </span>
      <span class="user-head" @click="closesys">
        <Icon class="closePic" type="android-exit" ></Icon>
      </span>
    </div>
  </div>
</template>
<script>
  export default {
    data () {
      return {
        model1: ''
      }
    },
    methods: {
      changeModel () {
        this.$router.push({path: 'personal'})
      },
      closesys: function () {
        let vm = this
        vm.$Modal.confirm({
          title: '提示',
          content: '是否退出当前子系统?',
          onOk: function () {
            vm.$Message.success('已退出，请重新登录')
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
.user-head {
  cursor: pointer;
  display: block;
  float: left;
  color: #b9c5d2;
}
.userPic {
  float: left;
  height: 30px;
  width: 30px;
  border-radius: 100%;
  color: #b9c5d2;
  margin: 10px 5px 10px 0;
}
.closePic {
  &:hover {
    font-size: 28px;
    color: #FFFFFF;
    transform: rotateZ(360deg);
  }
  float: left;
  height: 24px;
  width: 24px;
  font-size: 24px;
  border-radius: 100%;
  margin: 13px 5px 11px;
  transition: all .3s ease;
}
</style>
