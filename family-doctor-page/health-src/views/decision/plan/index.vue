<template>
  <div class="layout-body">
  <div class="layout-body-breadcrumb">
    <Breadcrumb>
      <BreadcrumbItem href="object">
        <Icon type="ios-people"></Icon> 随访计划
      </BreadcrumbItem>
    </Breadcrumb>
  </div>
  <div class="layout-content">
      <Tabs type="card" >
        <TabPane label="随访计划">
        <v-plantable></v-plantable>
				</TabPane>
        <TabPane label="随访记录">
          <v-record></v-record>
        </TabPane>
        <TabPane label="随访统计">
          <v-tongji></v-tongji>
        </TabPane>
    </Tabs>
    </div>
  </div>
</template>
<script>
import plantable from './plantable.vue'
import record from './record.vue'
import tongji from './tongji.vue'
export default {
  components: {
    'v-plantable': plantable,
    'v-record': record,
    'v-tongji': tongji
  },
  data () {
    return {
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
  text-align:left;
  margin-left:10px;
  font-size:36px;
}
.layout-head{
  margin:10px;
}
.layout-head-title{
  float:left;
  padding:10px 12px;
}
.layout-content{
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
