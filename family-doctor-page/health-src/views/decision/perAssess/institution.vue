 /** * @Title : 健康内链分析 * @Description : * @author : TiuWeb */
<template>
    <div>
        <div style="margin-top:50px">
            <Row type="flex" justify="center">
               
                <Col span="10">
                <div id="way" style="height:400px;"></div>
                </Col>
                <Select style="padding-top:5px;width:150px;" placeholder="陕西人民医院">
                        <Option value="" label="陕西人民医院">
                        <span>陕西人民医院</span>
                        <span style="float:right;color:#ccc"></span>
                        </Option>
                        <Option value="1" label="交大二院">
                        <span>交大二院</span>
                        </Option>
                        <Option value="2" label="交大一院">
                        <span>交大一院</span>
                        </Option>
                    </Select>
            </Row>
        </div>
    </div>
</template>
<script>
import echarts from 'echarts'
import $ from 'jquery'
import data from '../../../config/data'
export default {
  data () {
    return {
      chart: {}
    }
  },
  methods: {
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
          text: '医疗质量和水平',
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
        color: ['#bbbec4', '#009DD9', '#33FFFF', '#19be6b', '#ed3f14'],
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
        url: data.base + 'static/data/institution.json',
        method: 'get',
        success: function (res) {
          edata = res
          // 服务评价饼状图
          vm.pie3('way', edata)
        }
      })
    })
  }
}
</script>
<style>
</style>
