/**
 * @Title : 健康评估首页
 * @Description :
 * @author : TiuWeb
 */
<template>
  <div class="layout-body">
    <div class="layout-body-breadcrumb">
    <Breadcrumb>
      <BreadcrumbItem href="object">
        <Icon type="ios-home-outline"></Icon> 健康评估
      </BreadcrumbItem>
    </Breadcrumb>
  </div>
    <div class="layout-content">
     <Tabs type="card">
        <TabPane label="健康内联型分析">
          <v-health></v-health>
				</TabPane>
        <TabPane label="效果比较研究">
          <v-result></v-result>
        </TabPane>
        <TabPane label="体验结果比较">
          <Row>
            <Col span="8">
              <div class='layout-head-title'>保健卡号</div>
              <Input  style="width:150px" placeholder="请输入关键字查询"></Input>
              <Button type="primary" icon="ios-search" style="color:#fff;margin-left:8px">查询</Button>
            </Col>
          </Row>
        </TabPane>
				<TabPane label="疾病趋势分析">
          <v-sickness></v-sickness>
        </TabPane>
    </Tabs>
    </div>
  </div>
</template>
<script>
import 'iview/dist/styles/iview.css'
import health from './health.vue'
import result from './result.vue'
import sickness from './sickness.vue'
export default {
  components: {
    'v-health': health,
    'v-result': result,
    'v-sickness': sickness
  },
  data () {
    return {
      total: 0,
      page: 1,
      rows: 10,
      dataList: []
    }
  },
  created: function () {
  },
  methods: {
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
}
.layout-head{
  margin:10px;
}
.layout-head-title{
  float:left;
  padding:10px 12px;
}
.layout-content{
  margin-top:10px;
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
