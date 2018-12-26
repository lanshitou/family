<template>
  <climb-grid border :async="true" :data="tableDatas" :columns="tableColumns" :asyncData="subDatas" @on-async="loadSub"></climb-grid>
</template>
<script>
  import { apiBaseConfig } from '../../api'
  export default {
    data () {
      return {
        tableColumns: [{
          title: '区域名称',
          key: 'areaname'
        }, {
          title: '区域编码',
          key: 'areacode'
        }, {
          title: '经度',
          key: 'latitude'
        }, {
          title: '纬度',
          key: 'longitude'
        }, {
          title: '拼音码',
          key: 'areapycode'
        }],
        tableDatas: [],
        subDatas: []
      }
    },
    created () {
      this.getDatas()
    },
    methods: {
      getDatas: function () {
        let vm = this
        apiBaseConfig.post('/parea/findbycon', {arealevel: '1'})
          .then(res => {
            // console.warn(res.data)
            vm.tableDatas = res.data
          })
      },
      loadSub: function (item) {
        let vm = this
        apiBaseConfig.post('/parea/findbycon', {
          'areaparentcode': item.areacode
        }).then(res => {
          vm.subDatas = res.data
        }, () => {
          vm.$Message.warning('无下级数据')
        })
      }
    }
  }
</script>
