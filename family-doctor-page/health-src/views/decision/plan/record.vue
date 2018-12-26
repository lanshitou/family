<template>
   <div style="margin-top:30px">
       <Table stripe :columns="columns1" :data="data1"></Table>
        <div class="pages-block">
        <div class="pages-right">
          <Page :total="total" :page-size="rows" @on-page-size-change="doPageSizeChange" @on-change="change" show-sizer show-total
          ref="tablePage" show-elevator></Page>
        </div>
      </div>
      </div>
    </div>
</template>
<script>
export default {
  data () {
    return {
      total: 0,
      page: 1,
      rows: 10,
      columns1: [
        {
          title: '随访计划编号',
          key: 'no'
        },
        {
          title: '随访对象',
          key: 'object'
        },
        {
          title: '执行内容',
          key: 'content'
        },
        {
          title: '执行方式',
          key: 'way'
        },
        {
          title: '执行时间',
          key: 'time'
        },
        {
          title: '执行结果',
          key: 'result'
        }
      ],
      data1: [
        {
          no: '20170411',
          object: '赵平',
          content: '体检',
          way: '电话',
          time: '20170411',
          result: '健康'
        },
        {
          no: '20170528',
          object: '张凡',
          content: '头疼',
          way: '短信',
          time: '20170528',
          result: '感冒'
        },
        {
          no: '20170808',
          object: '张起',
          content: '肚子疼',
          way: '电话',
          time: '20170808',
          result: '肠胃不适'
        },
        {
          no: '20147265',
          object: '李尔',
          content: '眼睛流泪',
          way: '短信',
          time: '20170522',
          result: '眼部发炎'
        }
      ]
    }
  },
  methods: {
    // 点击分页页码
    change (page) {
    },
    // 改变当前显示的行数
    doPageSizeChange: function (size) {
      this.rows = size
    }
  }
}
</script>
<style>
.pages-block {
  margin: 0px;
  overflow: hidden;
  margin-top: 10px;
  margin-left: 1px;
  margin-bottom: 140px;
  text-align: center;
  }
.pages-right {
  vertical-align: middle;
}
</style>