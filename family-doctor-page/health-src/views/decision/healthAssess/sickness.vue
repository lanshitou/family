/**
 * @Title : 疾病趋势分析
 * @Description :
 * @author : TiuWeb
 */
<template>
  <div>
    <div>
      <Row>
        <Col span="2" style="margin-top:5px">查看方式</Col>
        <Col span="3">
          <Select v-model="model1" style="width:100px">
            <Option v-for="item in modellist1" :value="item.value" :key="item.value">{{ item.label }}</Option>
          </Select>
        </Col>
        <Col span="2" style="margin-top:5px">开始日期</Col>
        <Col span="3">
          <DatePicker type="date" placeholder="请选择日期" style="width: 150px"></DatePicker>
        </Col>
        </Col>
        <Col span="2">
          <Button type="primary" icon="ios-search" style="margin-left:8px">查询</Button>
        </Col>
      </Row>
    </div>
    <div>
      <Tabs type="card">
      <TabPane label="血压">
        <v-bloodpressure></v-bloodpressure>
      </TabPane>
      <TabPane label="餐前血糖">餐前血糖</TabPane>
      <TabPane label="运动">运动</TabPane>
      <TabPane label="体重、BMI">体重、BMI</TabPane>
      <TabPane label="肌肉率、体脂率">肌肉率、体脂率</TabPane>
      <TabPane label="内脏脂肪、皮下脂肪">内脏脂肪、皮下脂肪</TabPane>
      <TabPane label="水含量">水含量</TabPane>
      <TabPane label="基础代谢">基础代谢</TabPane>
      <TabPane label="睡眠">睡眠</TabPane>
      <TabPane label="餐后血糖">餐后血糖</TabPane>
      <TabPane label="血脂">血脂</TabPane>
      <TabPane label="糖化血红">糖化血红</TabPane>
    </Tabs>
    </div>
    <div style="margin-top:50px">
      <Row type="flex" justify="start">
        <Col span="12">
        <div id="health" style="width: 600px;height:400px;"></div>
        </Col>
      </Row>
    </div>
  </div>
</template>
<script>
import bloodpressure from './bloodpressure.vue'
import echarts from 'echarts'
import $ from 'jquery'
import { FormateGroupData } from '../../../framework/utils'
import data from '../../../config/data'
export default {
  components: {
    'v-bloodpressure': bloodpressure
  },
  data () {
    return {
      modellist1: [
        {
          value: '按次',
          label: '按次'
        },
        {
          value: '按月',
          label: '按月'
        },
        {
          value: '按年',
          label: '按年'
        }
      ],
      model1: '按次'
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
</style>
