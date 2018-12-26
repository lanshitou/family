/**
 * @Title : 医疗机构签约，服务情况统计
 * @Description : 
 * @author : TiuWeb
 */
<template>
  <div>
    <div style="padding:10px 20px;height:100%;overflow:auto">
      <Row>
        <Col>
        <div class="index">
          <div class="box-body">
            <div>
              <Row type="flex" justify="center">
                <Col span="8">
                <div id="serviceContract" style="width: 400px;height:400px;"></div>
                </Col>
                <Col span="8">
                <div id="serviceFinish" style="width: 400px;height:400px;"></div>
                </Col>
                <Col span="8">
                <div id="evaluate" style="width: 400px;height:400px;"></div>
                </Col>
              </Row>
            </div>
          </div>
        </div>
        </Col>
      </Row>
    </div>
  </div>
</template>
<script>
import echarts from 'echarts'
import $ from 'jquery'
// import { FormateGroupData } from '../../framework/utils'
export default {
  data () {
    return {
      zoom: 15,
      chart: {},
      loginnum: '',
      opernum: '',
      bussexpnum: '',
      expnum: '',
      loginnumpro: 0,
      expnumpro: 0,
      bussexpnumpro: 0
    }
  },
  methods: {
    pie1 (id, edata) {
      let vm = this
      if (document.getElementById(id) === null) {
        return false
      }
      let _chart = {}
      _chart[id] = echarts.init(document.getElementById(id))
      vm.chart = Object.assign({}, vm.chart, _chart)
      vm['chart'][id].setOption({
        title: {
          text: '签约情况统计',
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
        itemStyle: {
          emphasis: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: 'rgba(0, 0, 0, 0.5)'
          }
        },
        color: ['#5B9BD5', '#00B0F0', '#92D050', '#FFC000'],
        series: [{
          name: '签约情况',
          type: 'pie',
          radius: '55%',
          center: ['50%', '60%'],
          data: edata
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
      vm.chart = Object.assign({}, vm.chart, _chart)
      vm['chart'][id].setOption({
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
        itemStyle: {
          emphasis: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: 'rgba(0, 0, 0, 0.5)'
          }
        },
        color: ['#44A8EC', '#FFC000', '#FF66FF'],
        series: [{
          name: '服务计划完成情况',
          type: 'pie',
          radius: '55%',
          center: ['50%', '60%'],
          data: edata
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
      vm.chart = Object.assign({}, vm.chart, _chart)
      vm['chart'][id].setOption({
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
        itemStyle: {
          emphasis: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: 'rgba(0, 0, 0, 0.5)'
          }
        },
        color: ['#92D050', '#FFFF66', '#FFD966', '#89D8FF'],
        series: [{
          name: '评价统计',
          type: 'pie',
          radius: '55%',
          center: ['50%', '60%'],
          data: edata
        }]
      })
    }
  },
  mounted () {
    let edata
    // let edatas
    let vm = this
    this.$nextTick(function () {
      $.ajax({
        url: '../../../static/data/constract.json',
        method: 'get',
        success: function (res) {
          edata = res
          // 签约情况统计饼状图
          vm.pie1('serviceContract', edata)
          // 服务计划完成情况统计饼状图
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
          vm.pie2('serviceFinish', edata)
          // 服务计划完成情况统计饼状图
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
}

.label-success {
  background-color: #a8bc7b;
}

.index {
  margin-bottom: 25px;
}
.box .box-title {
  padding: 6px 10px 2px;
  min-height: 35px;
  margin-bottom: 0;
}

.box-body {
  padding: 10px;
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

#contractNoYear,
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

.tdclass1 {
  border: 1px dashed #C4C9DF;
  vertical-align: middle;
}
</style>
