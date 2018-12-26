<template>
  <div class="container">
    <div class="panel panel-default">
      <div class="panel-heading">
        <h3>加载动画(编程式)</h3>
      </div>
      <div class="panel-body">
        <Button type="ghost" @click="start">测试加载</Button>
        <p>1s后调用finish()方法</p>
      </div>
    </div>
    <div class="panel panel-default">
      <div class="panel-heading">
        <h3>加载动画(标签式)</h3>
      </div>
      <div class="panel-body">
        <h4>加载动画</h4>
        <Button type="ghost" @click="tagStart">测试加载</Button>
        <climb-spiner :tag="true" v-show="loading"></climb-spiner>
        <p>1s后将v-show置为false方法</p>

        <h4>配置size属性</h4>
        <Button type="ghost" @click="tagSetStart">测试加载</Button>
        <climb-spiner :size="162" :tag="true" v-show="loading1"></climb-spiner>
        
        <h4>配置icon属性</h4>
        <Button type="ghost" @click="tagSetStart2">测试加载</Button>
        <climb-spiner :icon="['fa', 'fa-user', 'fa-pulse']" :tag="true" v-show="loading2"></climb-spiner>
      
        <h4>配置iconColor属性</h4>
        <Button type="ghost" @click="tagSetStart3">测试加载</Button>
        <climb-spiner iconColor="#00BCD4" :tag="true" v-show="loading3"></climb-spiner>

        <h4>配置slot</h4>
        <Button type="ghost" @click="tagSetStart4">测试加载</Button>
        <climb-spiner :tag="true" v-show="loading4">
          <div>
            <img src="../../assets/person.png" width="130" alt="">
          </div>
          <i class="fa fa-quora fa-pulse" style="font-size: 66px;color: #f00"></i>
        </climb-spiner>

      </div>
    </div>
  </div>
</template>
<script>
  export default {
    data () {
      return {
        loading: false,
        loading1: false,
        loading2: false,
        loading3: false,
        loading4: false,
        imgUrl: require('../../assets/person.png')
      }
    },
    methods: {
      start: function () {
        let vm = this
        vm.$Spiner.start()
        setTimeout(function () {
          vm.$Spiner.finish()
        }, 1000)
      },
      tagStart: function () {
        let vm = this
        vm.loading = true
        setTimeout(function () {
          vm.loading = false
        }, 1000)
      },
      tagSetStart: function () {
        let vm = this
        vm.loading1 = true
        setTimeout(function () {
          vm.loading1 = false
        }, 1000)
      },
      tagSetStart2: function () {
        let vm = this
        vm.loading2 = true
        setTimeout(function () {
          vm.loading2 = false
        }, 1000)
      },
      tagSetStart3: function () {
        let vm = this
        vm.loading3 = true
        setTimeout(function () {
          vm.loading3 = false
        }, 1000)
      },
      tagSetStart4: function () {
        let vm = this
        vm.loading4 = true
        setTimeout(function () {
          vm.loading4 = false
        }, 1000)
      }
    }
  }
</script>
<style lang="scss" scoped>
  h3{
    font-size: 20px;
  }
  h4 {
    padding: 6px 0;
    margin-bottom: 8px;
    border-bottom: 1px dotted #00BCD4;
    color: #00BCD4;
    font-size: 18px;
  }
</style>
