<template>
  <div class="layout-body">
    <div class="layout-body-breadcrumb">
    <Breadcrumb>
      <BreadcrumbItem href="object">
        <Icon type="card"></Icon> 绩效评估
      </BreadcrumbItem>
    </Breadcrumb>
  </div>
    <div class="layout-content">
     <Tabs type="card">
        <TabPane label="医疗机构">
          <v-institution></v-institution>
        </TabPane>
        <TabPane label="保健人员">
            <v-people></v-people>
        </TabPane>
    </Tabs>
    </div>
  </div>
</template>
<script>
import institution from './institution.vue'
import people from './people.vue'
export default {
  components: {
    'v-institution': institution,
    'v-people': people
  },
  data () {
    return {}
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
