
<template>
  <div>
    <div>
    <Row type="flex" justify="start">
      <Col span="12">
      <div class="panelEcharts">
        <div id="blloodpressure" style="width: 600px;height:400px;"></div>
      </div>
      </Col>
      <Col span="10" push="1">
      <div class="panelEcharts">
        <div class="table">
          <Table :columns="columns1" :data="data1"></Table>
        </div>
      </div>
      </Col>
    </Row>
  </div>
  </div>
</template>
<script>
import echarts from 'echarts'
export default {
  data () {
    return {
      columns1: [
        {
          title: '高压(mmHg)',
          key: 'gao'
        },
        {
          title: '低压(mmHg)',
          key: 'di'
        },
        {
          title: '脉搏(次/每分钟)',
          key: 'maibo'
        },
        {
          title: '测量时间',
          key: 'time'
        }
      ],
      data1: [
        {
          gao: '97',
          di: '69',
          maibo: '73',
          time: '2017-07-16 13:15'
        },
        {
          gao: '93',
          di: '64',
          maibo: '78',
          time: '2017-07-23 16:22'
        },
        {
          gao: '98',
          di: '77',
          maibo: '80',
          time: '2017-07-29 20:38'
        },
        {
          gao: '93',
          di: '70',
          maibo: '86',
          time: '2017-08-03 14:15'
        }
      ]
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
        title: {
          text: '血压趋势图'
        },
        tooltip: {
          trigger: 'axis'
        },
        legend: {
          data: ['收缩压', '舒张压']
        },
        toolbox: {
          show: true,
          feature: {
            dataZoom: {
              yAxisIndex: 'none'
            },
            dataView: {readOnly: false},
            magicType: {type: ['line', 'bar']},
            restore: {},
            saveAsImage: {}
          }
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          axisLabel: {
            rotate: 40
          },
          data: ['07-16 13:15', '07-19 13:15', '07-16 17:15', '07-22 09:15', '07-25 11:15', '07-28 14:15', '07-31 18:15']
        },
        yAxis: {
          type: 'value',
          axisLabel: {
            formatter: '{value}'
          }
        },
        series: [
          {
            name: '收缩压',
            type: 'line',
            data: [91, 93, 97, 92, 112, 84, 96],
            markPoint: {
              data: [
                {type: 'max', name: '最大值'},
                {type: 'min', name: '最小值'}
              ]
            },
            markLine: {
              data: [
                {type: 'average', name: '平均值'}
              ]
            }
          },
          {
            name: '舒张压',
            type: 'line',
            data: [63, 66, 61, 75, 73, 78, 69],
            markPoint: {
              data: [
                {name: '周最低', value: -2, xAxis: 1, yAxis: -1.5}
              ]
            },
            markLine: {
              data: [
                {type: 'average', name: '平均值'},
                [{
                  symbol: 'none',
                  x: '90%',
                  yAxis: 'max'
                }, {
                  symbol: 'circle',
                  label: {
                    normal: {
                      position: 'start',
                      formatter: '最大值'
                    }
                  },
                  type: 'max',
                  name: '最高点'
                }]
              ]
            }
          }
        ]
      })
    }
  },
  mounted () {
    this.drawBar('blloodpressure')
  }
}
</script>
<style>
.panelEcharts{
  border-radius:10px;
  width: 600px;
  height:400px;
  border:1px solid #b1daff;
  background:#fff;
}
.table{
  position:absolute;
  top:60px;
  left:20px;
  width:500px;
  height:400px;
  overflow:auto;
}
</style>
