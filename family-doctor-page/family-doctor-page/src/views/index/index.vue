/**
 * @Title : 首页
 * @Description : 首
 * @author : TiuWeb
 */
<template>
<div style="padding:10px 20px;background:#f5f5f5;height:90%; overflow:auto;">
  <div class="welcome">
    <h2 class="title">欢迎您使用{{sysname}}！</h2>
    <span class="info">系统概括</span>
  </div>
  <Row :gutter="16">
    <Col :xs="24" :sm="24" :md="12" :lg="6">
    <Card class="card">
      <div class="card-block">
        <div class="card-left"><img class="card-img" src="../../assets/framework/buy.png"></div>
        <div class="card-right">
          <div class="number">{{loginnum}}</div>
          <router-link :to="{path: 'contact'}">待处理签约协议</router-link>
          <span class="label label-success"> Today </span>
        </div>
      </div>
    </Card>
    </Col>
    <Col :xs="24" :sm="24" :md="12" :lg="6">
    <Card class="card">
      <div class="card-block">
        <div class="card-left"><img class="card-img" src="../../assets/framework/cloud.png"></div>
        <div class="card-right">
          <div class="number">{{opernum}}</div>
          <router-link :to="{path: 'plans'}">待执行签约服务</router-link>
          <span class="label label-success"> Today </span>
        </div>
      </div>
    </Card>
    </Col>
    <Col :xs="24" :sm="24" :md="12" :lg="6">
    <Card class="card">
      <div class="card-block">
        <div class="card-left"><img class="card-img" src="../../assets/framework/settings.png"></div>
        <div class="card-right">
          <div class="number">{{bussexpnum}}</div>
          <router-link :to="{path: 'plans'}">服务申请</router-link>
          <span class="label label-success"> Today </span>
        </div>
      </div>
    </Card>
    </Col>
    <Col :xs="24" :sm="24" :md="12" :lg="6">
    <Card class="card">
      <div class="card-block">
        <div class="card-left"><img class="card-img" src="../../assets/framework/exep.png"></div>
        <div class="card-right">
          <div class="number">{{expnum}}</div>
          <router-link :to="{path: 'inquiry'}">新增问诊</router-link>
          <span class="label label-success"> Today </span>
        </div>
      </div>
    </Card>
    </Col>
  </Row>
  </Row>
      <Row :gutter="16">
      <Col :xs="24" :sm="24" :md="12" :lg="6">
        <Card class="card">
          <div class="card-block">
            <div class="card-left"><img class="card-img" src="../../assets/framework/exep.png"></div>
            <div class="card-right">
              <div class="number">{{noticenum}}</div>
              <router-link :to="{path: 'notice'}">新增投诉建议</router-link>
              <span class="label label-success"> Today </span>
            </div>
          </div>
        </Card>
      </Col>
    </Row>
  <Row type="flex" justify="end">
     <!-- <router-link :to="{path: 'moreNotice'}">更多</router-link>  -->
  </Row>
  <Row type="flex" justify="center">
    <Col span="24">
    <div class="">
      <div class="">
        <div id="exLog" class="chains"></div>
      </div>
    </div>
    </Col>
  </Row>
  <Row type="flex" justify="end" style="text-align:center; margin-top:25px;">
    <Col span="12">
    <span id="service" style="width: 600px;height:400px;"></span>
    </Col>
    <Col span="12">
    <span id="evaluate" style="width: 600px;height:400px;"></span>
    </Col>
  </Row>
</div>
</template>
<script>
import echarts from 'echarts'
import $ from 'jquery'
import {
  FormateGroupData
} from '../../framework/utils'
export default {
  data () {
    return {
      sysname: '家庭医生系统',
      chart: {},
      chart1: {},
      chart2: {},
      loginnum: '10',
      opernum: '6',
      bussexpnum: '8',
      expnum: '6',
      noticenum: '8'
    }
  },
  created () {},
  methods: {
    more () {
      this.$router.push({
        path: 'moreNotice'
      })
    },
    drawPie (id, edata) {
      let vm = this
      if (document.getElementById(id) === null) {
        return false
      }
      if (vm.chart[id] === null || vm.chart[id] === undefined) {} else {
        vm['chart'][id].resize()
        return
      }
      let _chart = {}
      _chart[id] = echarts.init(document.getElementById(id))
      vm.chart = Object.assign({}, vm.chart, _chart)
      vm['chart'][id].setOption({
        color: ['#3398DB'],
        tooltip: {},
        toolbox: {
          feature: {
            saveAsImage: {},
            dataView: {}
          },
          right: 15,
          top: 10
        },
        grid: {
          left: '1%',
          right: '1%',
          bottom: '2%',
          containLabel: true
        },
        xAxis: {
          type: 'category',
          axisTick: {
            alignWithLabel: true
          },
          data: edata.xAxis
        },
        yAxis: {
          type: 'value'
        },
        legend: {
          data: ['签约情况']
        },
        series: [{
          name: '签约情况',
          type: 'bar',
          data: edata.series[0].data
        }]
      })
    },
    pie2 (id, edata) {
      let vm = this
      if (document.getElementById(id) === null) {
        return false
      }
      let _chart = {}
      _chart[id] = echarts.init(document.getElementById(id))
      vm.chart1 = Object.assign({}, vm.chart1, _chart)
      vm['chart1'][id].setOption({
        title: {
          text: '服务计划完成情况统计',
          x: 'center'
        },
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
          orient: 'vertical',
          left: 'left',
          data: edata
        },
        color: ['#44A8EC', '#FFC000', '#FF66FF'],
        series: [{
          name: '服务计划完成情况',
          type: 'pie',
          radius: '55%',
          center: ['50%', '60%'],
          data: edata,
          itemStyle: {
            emphasis: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            },
            normal: {
              label: {
                show: true,
                formatter: '{b} : {c} ({d}%)'
              }
            }
          }
        }]
      })
    },
    pie3 (id, edata) {
      let vm = this
      if (document.getElementById(id) === null) {
        return false
      }
      let _chart = {}
      _chart[id] = echarts.init(document.getElementById(id))
      vm.chart2 = Object.assign({}, vm.chart2, _chart)
      vm['chart2'][id].setOption({
        title: {
          text: '评价统计',
          x: 'center'
        },
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
          orient: 'vertical',
          left: 'left',
          data: edata
        },
        color: ['#92D050', '#FFFF66', '#FFD966', '#89D8FF'],
        series: [{
          name: '评价统计',
          type: 'pie',
          radius: '55%',
          center: ['50%', '60%'],
          data: edata,
          itemStyle: {
            emphasis: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            },
            normal: {
              label: {
                show: true,
                formatter: '{b} : {c} ({d}%)'
              }
            }
          }
        }]
      })
    }
  },
  mounted () {
    let edata
    let vm = this
    this.$nextTick(function () {
      $.ajax({
        url: '../../../static/data/echarts.json',
        method: 'get',
        success: function (res) {
          edata = FormateGroupData(res, 'line', false)
          vm.drawPie('exLog', edata)
        }
      })
    })
    this.$nextTick(function () {
      $.ajax({
        url: '../../../static/data/finishPlan.json',
        method: 'get',
        success: function (res) {
          edata = res
          // 服务计划完成情况统计饼状图
          vm.pie2('service', edata)
        }
      })
    })
    this.$nextTick(function () {
      $.ajax({
        url: '../../../static/data/evaluate.json',
        method: 'get',
        success: function (res) {
          edata = res
          // 服务评价饼状图
          vm.pie3('evaluate', edata)
        }
      })
    })
  }
}
</script>
<style scoped lang="less">
.welcome {
  border-bottom: 1px solid #dddddd;
  margin-bottom: 20px;
  margin-top: -10px;
  margin-left: -20px;
  margin-right: -20px;
  padding-left: 20px;
  background: #fff;
}

.welcome .title {
  text-align: left;
  color: #666666;
  font-size: 29px;
  font-weight: normal;
}

.welcome .info {
  font-size: 14px;
  color: #b4b5b3;
}

.card {
  background: #f5f5f5;
  margin-bottom: 20px;
}

.ivu-card:hover {
  box-shadow: none;
  border-color: #dddddd;
}

.ivu-card .ivu-card-body {
  padding: 0!important;
}

.ivu-card-bordered {
  border-color: #dddddd;
}

.card-block {
  width: 100%;
  height: 80px;
  box-sizing: border-box;
}

.card-left {
  width: 30%;
  height: 80px;
  float: left;
  padding: 17px 20px;
  border-radius: 4px 0 0 4px;
  color: #FFFFFF;
  text-align: center;
  background-color: #f4f4f4;
  border-right: 1px solid #DDDDDD;
}

.card-img {
  width: 46px;
  height: 46px;
}

.card-right {
  width: 70%;
  height: 100%;
  float: right;
  padding-top: 10px;
  border-radius: 0 4px 4px 0;
  position: relative;
  text-align: center;
  background: #fff;
}

.card-right .number {
  font-size: 30px;
  font-weight: 400;
  color: #666666;
}

.card-right title {
  text-transform: uppercase;
}

.card-right .label {
  position: absolute;
  right: 5px;
  top: 5px;
  font-size: 11px;
  padding: .2em .6em .3em;
  color: #ffffff;
  text-align: center;
  white-space: nowrap;
  vertical-align: baseline;
  border-radius: .25em;
}

.label-success {
  background-color: #a8bc7b;
}

.box {
  clear: both;
  margin-top: 0px;
  margin-bottom: 25px;
  padding: 0px;
  border-radius: 4px;
}

.box .box-title {
  padding: 6px 10px 2px;
  min-height: 35px;
  border-radius: 4px 4px 0 0;
  margin-bottom: 0;
}

.box-body {
  border-radius: 0 0 4px 4px;
  padding: 10px;
  background: #f5f5f5;
}

.ivu-tabs-nav .ivu-tabs-tab:before {
  content: '';
  position: absolute;
  z-index: 1;
  top: -1px;
  left: -10px;
  bottom: 0;
  width: 1em;
  background: #f5f5f5;
  border-left: 1px solid #d0d0d0;
  border-top: 1px solid #d0d0d0;
  transform: skew(170deg);
  -webkit-transform: skew(170deg);
  border-radius: 5px 0 0 0;
}

.ivu-tabs-nav .ivu-tabs-tab {
  position: relative;
  background: #f5f5f5;
  padding: 7px 15px;
  float: left;
  text-decoration: none;
  color: #444;
  text-shadow: 0 1px 0 rgba(255, 255, 255, 0.8);
  border-radius: 5px 0 0 0;
  box-shadow: 0 2px 2px rgba(0, 0, 0, 0.4);
  margin-right: 23px;
  border-top: 1px solid #d0d0d0;
}

.ivu-tabs-nav .ivu-tabs-tab:after {
  content: '';
  position: absolute;
  z-index: 1;
  top: -1px;
  right: -0.5em;
  bottom: 0;
  width: 1em;
  background: #f5f5f5;
  box-shadow: 2px 2px 2px rgba(0, 0, 0, 0.4);
  border-top: 1px solid #d0d0d0;
  transform: skew(10deg);
  -webkit-transform: skew(10deg);
  border-radius: 0 5px 0 0;
}

.ivu-tabs-nav .ivu-tabs-tab:active,
.ivu-tabs-nav .ivu-tabs-tab:active:after,
.ivu-tabs-nav .ivu-tabs-tab:active:before {
  background: #fff;
}

.ivu-tabs {
  margin-top: -45px;
  width: 100%;
}

.ivu-tabs-bar {
  float: right;
  right: -10px;
  position: relative;
  margin-bottom: 0;
}

.ivu-tabs-content {
  clear: both;
}

#exLog,
#oper-log,
#exep-log,
#buss-log {
  position: relative;
  width: 100%;
  height: 300px;
}

@media screen and (max-width: 470px) {
  .chains {
    height: 200px;
  }
}
</style>
