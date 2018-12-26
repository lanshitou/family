/**
 * @Title : 医疗机构签约，服务情况统计
 * @Description : 
 * @author : TiuWeb
 */
<template>
  <div>
    <div style="padding:10px 20px;padding-top:30px;height:100%;overflow:auto">
      <Row>
        <Col>
        <div class="index">
          <div class="box-body">
            <Tabs type="card">
              <Tab-pane label="总体情况统计">
                <div style="margin-top:50px">
                  <Row>
                    <Col span="12">
                    <div id="contractNoYear"  style="width: 600px;height:400px;"></div>
                    </Col>
                    <Col span="12">
                    <div id="contractNoMouth" style="width: 600px;height:400px;"></div>
                    </Col>
                  </Row>
                </div>
                <div style="margin-top:100px">
                  <Row>
                    <Col span="12">
                    <div id="serviceType" style="width: 600px;height:400px;"></div>
                    </Col>
                    <Col span="12">
                    <div id="serviceEvaluate" style="width: 600px;height:400px;"></div>
                    </Col>
                  </Row>
                </div>
              </Tab-pane>
              <Tab-pane label="医疗机构考核">
                <Row type="flex" justify="center">
                  <Col>
                  <div id="zonghe" style="width: 600px;height:400px;"></div>
                  </Col>
                </Row>
                <Row>
                  <Col span="12">
                  <div id="contractSum" style="width: 600px;height:400px;"></div>
                  </Col>
                  <Col span="12">
                  <div id="fuWuSum" style="width: 600px;height:400px;"></div>
                  </Col>
                </Row>
                <Row type="flex" justify="center">
                  <Col>
                  <div id="hapPingSum" style="width: 600px;height:400px;"></div>
                  </Col>
                </Row>
              </Tab-pane>
            </Tabs>
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
import { FormateGroupData } from '../../framework/utils'
export default {
  data () {
    return {
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
    drawPie (id, edata) {
      let vm = this
      if (document.getElementById(id) === null) {
        return false
      }
      let _chart = {}
      _chart[id] = echarts.init(document.getElementById(id))
      vm.chart = Object.assign({}, vm.chart, _chart)
      vm['chart'][id].setOption({
        tooltip: {
          trigger: 'axis'
        },
        title: {
          text: '签约数',
          x: 'center'
        },
        xAxis: {
          data: edata.xAxis
        },
        yAxis: {
          type: 'value'
        },
        legend: {
          left: 50,
          top: 10,
          itemWidth: 15,
          itemHeight: 10,
          data: edata.category
        },
        series: edata.series
      })
    },
    drawBar (id, edata) {
      let vm = this
      if (document.getElementById(id) === null) {
        // console.info('不绘制')
        return false
      }
      let _chart = {}
      _chart[id] = echarts.init(document.getElementById(id))
      vm.chart = Object.assign({}, vm.chart, _chart)
      vm['chart'][id].setOption({
        color: ['#4F81BD'],
        title: {
          text: '签约数',
          x: 'center'
        },
        tooltip: {},
        xAxis: {
          data: edata.xAxis
        },
        yAxis: {
          type: 'value'
        },
        legend: {
          left: 50,
          top: 10,
          itemWidth: 15,
          itemHeight: 10,
          data: ['数量']
        },
        series: [{
          name: '数量',
          type: 'bar',
          data: edata.series[0].data
        }]
      })
    },
    pie (id, edata) {
      let vm = this
      if (document.getElementById(id) === null) {
        // console.info('不绘制')
        return false
      }
      let _chart = {}
      _chart[id] = echarts.init(document.getElementById(id))
      vm.chart = Object.assign({}, vm.chart, _chart)
      vm['chart'][id].setOption({
        title: {
          text: '医疗服务类型',
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
        color: ['#5B9BD5', '#ED7D31', '#A5A5A5', '#FFC000', '#4472C4'],
        series: [{
          type: 'pie',
          radius: '55%',
          center: ['50%', '60%'],
          data: edata
        }]
      })
    },
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
          text: '医疗服务评价统计',
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
          name: '医疗服务评价',
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
    let vm = this
    this.$nextTick(function () {
      $.ajax({
        url: '../../../static/data/supervise.json',
        method: 'get',
        success: function (res) {
          edata = FormateGroupData(res, 'line', false)
          // 签约数折线图
          vm.drawPie('contractNoYear', edata)
          // 签约数柱状图
          vm.drawBar('contractNoMouth', edata)
        }
      })
    })
    this.$nextTick(function () {
      $.ajax({
        url: '../../../static/data/pie.json',
        method: 'get',
        success: function (res) {
          edata = res
          // 服务类型饼状图
          vm.pie('serviceType', edata)
          // 服务评价饼状图
          vm.pie('serviceEvaluate', edata)
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
          vm.pie1('serviceEvaluate', edata)
        }
      })
    })
    let myChZongHe = echarts.init(document.getElementById('zonghe'))
    // 指定图表的配置项和数据
    let optionZ = {
      color: ['#A9D18E'],
      title: {
        text: '综合考核分'
      },
      tooltip: {
        trigger: 'axis',
        axisPointer: {
          type: 'shadow'
        }
      },
      grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
      },
      xAxis: {
        type: 'value',
        boundaryGap: [0, 0.01]
      },
      yAxis: {
        type: 'category',
        data: ['巴西', '印尼', '美国', '印度', '中国', '世界人口(万)']
      },
      series: [
        {
          name: '2011年',
          type: 'bar',
          data: [18203, 23489, 29034, 104970, 131744, 630230]
        },
        {
          name: '2012年',
          type: 'bar',
          data: [19325, 23438, 31000, 121594, 134141, 681807]
        }
      ]
    }
    // 使用刚指定的配置项和数据显示图表。
    myChZongHe.setOption(optionZ)
    let contractSum = echarts.init(document.getElementById('contractSum'))
    // 指定图表的配置项和数据
    let optionSum = {
      color: ['#ED7D31'],
      title: {
        text: '签约协议数量',
        x: 'center'
      },
      tooltip: {},
      legend: {
        left: 50,
        top: 10,
        itemWidth: 15,
        itemHeight: 10,
        data: ['数量']
      },
      xAxis: {
        data: ['2012', '2013', '2014', '2015', '2016']
      },
      yAxis: {},
      series: [{
        name: '数量',
        type: 'bar',
        data: [5, 20, 36, 10, 10]
      }]
    }
    // 使用刚指定的配置项和数据显示图表。
    contractSum.setOption(optionSum)
    let fuWuSum = echarts.init(document.getElementById('fuWuSum'))
    // 指定图表的配置项和数据
    let optionfuWuSum = {
      color: ['#2E7FB6'],
      title: {
        text: '签约服务数量',
        x: 'center'
      },
      tooltip: {},
      legend: {
        left: 50,
        top: 10,
        itemWidth: 15,
        itemHeight: 10,
        data: ['数量']
      },
      xAxis: {
        data: ['2012', '2013', '2014', '2015', '2016']
      },
      yAxis: {},
      series: [{
        name: '数量',
        type: 'bar',
        data: [5, 20, 36, 10, 10]
      }]
    }
    // 使用刚指定的配置项和数据显示图表。
    fuWuSum.setOption(optionfuWuSum)
    let haoPingSum = echarts.init(document.getElementById('hapPingSum'))
    // 指定图表的配置项和数据
    let optionhaopingSum = {
      color: ['#00B0F0'],
      title: {
        text: '签约服务好评率',
        x: 'center'
      },
      tooltip: {},
      legend: {
        left: 50,
        top: 10,
        itemWidth: 15,
        itemHeight: 10,
        data: ['数量']
      },
      xAxis: {
        data: ['2012', '2013', '2014', '2015', '2016']
      },
      yAxis: {},
      series: [{
        text: '签约服务好评率',
        name: '数量',
        type: 'bar',
        data: [5, 20, 36, 10, 10]
      }]
    }
    // 使用刚指定的配置项和数据显示图表。
    haoPingSum.setOption(optionhaopingSum)
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
  margin-top: 20px;
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
</style>
