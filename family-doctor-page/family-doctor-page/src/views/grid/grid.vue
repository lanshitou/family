<template>
  <climb-grid :data="tableDatas" size='small' :columns="tableColumns"></climb-grid>
</template>
<script>
export default {
  data () {
    return {
      tableDatas: [{
        areaname: '北京市',
        areacode: '110000',
        areapycode: 'bjs'
      }, {
        areaname: '天津市',
        areacode: '120000',
        areapycode: 'tjs',
        children: [{
          areaname: '市辖区',
          areacode: '120100',
          areapycode: 'sxq'
        }, {
          areaname: '市辖县',
          areacode: '120200',
          areapycode: 'sxx'
        }]
      }, {
        areaname: '山西省',
        areacode: '140000',
        areapycode: 'sxs',
        children: [{
          areaname: '太原市',
          areacode: '140100',
          areapycode: 'sxq'
        }, {
          areaname: '大同市',
          areacode: '140200',
          areapycode: 'dts'
        }, {
          areaname: '阳泉市',
          areacode: '140300',
          areapycode: 'yqs',
          children: [{
            areaname: '市辖区',
            areacode: '140301',
            areapycode: 'sxq'
          }, {
            areaname: '城区',
            areacode: '140302',
            areapycode: 'cq'
          }]
        }]
      }],
      tableColumns: [{
        title: '区域名称',
        key: 'areaname'
      }, {
        title: '区域编码',
        key: 'areacode'
      }, {
        title: '拼音码',
        key: 'areapycode'
      }]
    }
  }
}
</script>
