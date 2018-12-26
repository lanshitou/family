
/**
 * @Title : 健康内链分析
 * @Description :
 * @author : TiuWeb
 */
<template>
  <div>
    <div style="margin-top:50px">
    <Row type="flex" justify="start">
      <Col span="10">
      <div id="health" style="height:400px;"></div>
      </Col>
      <Col span="10">
        <div id="way" style="height:400px;"></div>
      </Col>
    </Row>
  </div>
  </div>
</template>
<script>
import echarts from 'echarts'
import $ from 'jquery'
import { FormateGroupData } from '../../../framework/utils'
import data from '../../../config/data'
export default {
  data () {
    return {
      modellist1: [
        {
          value: '年龄与疾病',
          label: '年龄与疾病'
        },
        {
          value: '职业与疾病',
          label: '职业与疾病'
        },
        {
          value: '居住地与疾病',
          label: '居住地与疾病'
        }
      ],
      model1: '年龄与疾病',
      modellist2: [
        {
          value: '高血压',
          label: '高血压'
        },
        {
          value: '心脏病',
          label: '心脏病'
        },
        {
          value: '高血脂',
          label: '高血脂'
        }
      ],
      model2: '高血压',
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
    drawBar (id, edata) {
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
          text: '随访人数统计',
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
          text: '随访方式统计',
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
        color: ['#009DD9', '#33FFFF'],
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
    let vm = this
    this.$nextTick(function () {
      $.ajax({
        url: data.base + 'static/data/tongji.json',
        method: 'get',
        success: function (res) {
          edata = FormateGroupData(res, 'line', false)
          vm.drawBar('health', edata)
        }
      })
    })
    this.$nextTick(function () {
      $.ajax({
        url: data.base + 'static/data/way.json',
        method: 'get',
        success: function (res) {
          edata = res
          vm.pie3('way', edata)
        }
      })
    })
  }
  //   let waySum = echarts.init(document.getElementById('way'))
  //   // 指定图表的配置项和数据
  //   let optionSum = {
  //     color: ['#00B0F0'],
  //     title: {
  //       text: '签约服务好评率',
  //       x: 'center'
  //     },
  //     tooltip: {},
  //     legend: {
  //       left: 50,
  //       top: 10,
  //       itemWidth: 15,
  //       itemHeight: 10,
  //       data: ['数量']
  //     },
  //     xAxis: {
  //       data: ['2012', '2013', '2014', '2015', '2016']
  //     },
  //     yAxis: {},
  //     series: [{
  //       text: '签约服务好评率',
  //       name: '数量',
  //       type: 'bar',
  //       data: [5, 20, 36, 10, 10]
  //     }]
  //   }
  //   // 使用刚指定的配置项和数据显示图表。
  //   waySum.setOption(optionSum)
  // }
}
</script>
<style>
</style>
