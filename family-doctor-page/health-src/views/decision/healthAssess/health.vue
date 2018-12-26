
/**
 * @Title : 健康内链分析
 * @Description :
 * @author : TiuWeb
 */
<template>
  <div>
    <div>
      <Row>
        <Col span="5">
          <Select v-model="model1" style="width:200px">
            <Option v-for="item in modellist1" :value="item.value" :key="item.value">{{ item.label }}</Option>
          </Select>
        </Col>
        <Col span="5">
          <Select v-model="model2" style="width:200px">
            <Option v-for="item in modellist2" :value="item.value" :key="item.value">{{ item.label }}</Option>
          </Select>
        </Col>
      </Row>
    </div>
    <div style="margin-top:50px">
    <Row type="flex" justify="start">
      <Col span="12">
      <div class="panelEcharts">
        <div id="health"></div>
      </div>
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
        color: ['#4F81BD'],
        tooltip: {},
        xAxis: {
          name: '年龄',
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
    }
  },
  mounted () {
    let edata
    let vm = this
    this.$nextTick(function () {
      $.ajax({
        url: data.base + 'static/data/health.json',
        method: 'get',
        success: function (res) {
          edata = FormateGroupData(res, 'line', false)
          vm.drawBar('health', edata)
        }
      })
    })
  }
}
</script>
<style>
.panelEcharts{
  border-radius:10px;
  width: 600px;
  border:1px solid #b1daff;
  background:#fff;
}
#health{
width: 600px;
height:400px;
}
</style>
