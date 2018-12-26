<template>
    <div class="layout-body">
      <div class="layout-body-breadcrumb">
        <Breadcrumb>
          <BreadcrumbItem href="object">
            <Icon type="card"></Icon> 短信收发管理
          </BreadcrumbItem>
        </Breadcrumb>
      </div>
      <div class="layout-head">
      </div>
      <div class="layout-content">
          <Tabs value="name1">
              <TabPane label="发送记录" name="name1">
                <v-send></v-send>
              </TabPane>
              <TabPane label="接收记录" name="name2">
                <v-recepter></v-recepter>
              </TabPane>
          </Tabs>
      </div>
    </div>
  </template>
  <script>
  import send from './send.vue'
  import recepter from './recepter.vue'
  export default {
    components: {
      'v-send': send,
      'v-recepter': recepter
    },
    data () {
      return {
      }
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