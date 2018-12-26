/**
 * @Title : 效果比较研究
 * @Description :
 * @author : TiuWeb
 */
<template>
  <div class="layout-body">
    <div class="layout-head">
    <Row>
      <Col span="8">
        <div class='layout-head-title'>保健卡号</div>
        <Input  style="width:150px" placeholder="请输入关键字查询"></Input>
        <Button type="primary" icon="ios-search" style="margin-left:8px">查询</Button>
      </Col>
    </Row>
    </div>
    <div class="layout-content">
      <Table border :columns="columns1" :data="dataList"></Table>
    </div>
  </div>
</template>
<script>
export default {
  data () {
    return {
      dataList: [
        {
          measure: '开药',
          time: '2017-11-11',
          creat: '张**',
          details: '阿莫西林',
          result: '病情缓解'
        },
        {
          measure: '输液',
          time: '2017-11-11',
          creat: '张**',
          details: '阿莫西林',
          result: '病情明显好转'
        }
      ],
      columns1: [
        {
          title: '干预措施',
          key: 'measure'
        },
        {
          title: '干预时间',
          key: 'time'
        },
        {
          title: '创建人',
          key: 'creat'
        },
        {
          title: '干预详情',
          key: 'details'
        },
        {
          title: '效果',
          key: 'result'
        }
      ]
    }
  },
  created: function () {
  },
  methods: {
    query: function () {
      alert('11111111')
    },
    update () {
      this.$router.push({name: 'person/edit'})
    },
    // 注销
    remove (index) {
      var th = this
      th.$Modal.confirm({
        title: '保健人员信息',
        content: `是否注销此记录`,
        onOk: function () {
          th.dataList.splice(index, 1)
          th.$Message.success('注销成功!')
        },
        onCancel: function () {
          th.$Message.success('取消')
        }
      })
    },
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
.layout-body{
  padding:5px;
  background: #f8f8f9;
}
.layout-body-breadcrumb{
  margin-left:10px;
  text-align:left;
  font-size:36px;
}
.layout-head{
  margin:10px;
}
.layout-head-title{
  float:left;
  padding:10px 12px;
}
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
