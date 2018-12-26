/**
 * @title: 新增按钮组件
 * @description: 新增
 * @author: TiuWeb
*/
<template>
  <button class="c-table-btn user-head" @click="add">
    新增</button>
</template>
<script>
export default {
  props: [
    'params'
  ],
  data () {
    return {
    }
  },
  methods: {
    // 新增点击事件
    add () {
      // 判断跳转的路由是否需要传递参数
      if (this.params.name) {
        this.$router.push({ name: this.params.name, params: { id: this.params.params.id } })
      } else {
        this.$router.push({ path: this.params })
      }
    }
  }
}
</script>
<style scoped>
.c-table-btn {
  position: fixed;
  right: 18px;
  bottom: 18px;
  z-index: 99;
  width: 86px;
  height: 86px;
  font-size: 18px;
  background-color: #5e87b0;
  color: #ffffff;
  border-radius: 50%;
  border: none;
  outline: none;
}
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
</style>
