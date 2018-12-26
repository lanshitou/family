<template>
  <div id="app" class="bg">
    <router-view></router-view>
    <!--<climb-spiner :size='88' rotate>
    </climb-spiner>-->
  </div>
</template>

<script>
  export default {
    name: 'app',
    created () {
      // console.info(this)
      // this.$Loading.start()
      this.$Spiner.start()
      let vm = this
      setTimeout(function () {
        // vm.$Loading.finish()
        vm.$Spiner.finish()
      }, 2000)
    }
  }

</script>

<style lang="scss">
  /*引入css库*/
  @import "../node_modules/font-awesome/css/font-awesome.min.css";
  @import "../node_modules/animate.css/animate.min.css";
  /*引入scss文件*/
  $theme: #5E87B0;
  @import "framework/scss/index.scss";
  html,
  body {
    height: 100%;
  }
  .animated{
    animation-duration: .25s;
  }
  #app {
    font-family: "Microsoft Yahei", "Hiragino Sans GB", "Helvetica Neue", Helvetica, tahoma, arial, "WenQuanYi Micro Hei", Verdana, sans-serif, "\5B8B\4F53";
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    min-height: 100%;
    display: flex;
    .loading-container {
      text-align: center;
    }
  }
  
  .bg {
    background: url('assets/light_noise_diagonal.png');
  }
  .text-white {
    color: #fff;
  }
</style>
