<template>
    <div class="layout-body">
        <div class="layout-body-breadcrumb">
            <Breadcrumb>
                <BreadcrumbItem href="object">
                    <Icon type="ios-paper"></Icon> 短信短语管理
                </BreadcrumbItem>
            </Breadcrumb>
        </div>
        <div class="layout-head">
        </div>
        <div class="layout-content">
            <Tabs  @on-click="checktab" v-model="Tabname">
                <TabPane label="短语类型" name="1">
                    <v-type></v-type>
                </TabPane>
                <TabPane label="常用短语" name="2">
                    <v-aways></v-aways>
                </TabPane>
            </Tabs>
        </div>
        <v-button :params="params">新增</v-button>
    </div>
</template>
<script>
import button from '../../../components/add_btn'
import type from './type.vue'
import aways from './aways.vue'
export default {
  components: {
    'v-type': type,
    'v-aways': aways,
    'v-button': button
  },
  data () {
    return {
      Tabname: '1',
      params: {
        name: 'dy_add',
        params: {
          id: 1
        }
      }
    }
  },
  created: function () {
    if (this.$route.params.code !== '' && this.$route.params.code !== undefined) {
      this.Tabname = '' + this.$route.params.code
      this.checktab(this.name)
    }
  },
  methods: {
      // 获取所选的tabs
    checktab (name) {
      var na = 1
      if (name === '1') {
        na = 1
      }
      if (name === '2') {
        na = 2
      }
      this.params.params.id = na // 获取tabs当前选中的name
    }
  }
}
</script>
<style>
    .layout-body {
        padding: 5px;
        background: #f8f8f9;
    }

    .layout-body-breadcrumb {
        margin-left: 10px;
        text-align: left;
    }

    .layout-head {
        margin: 10px;
    }

    .layout-head-title {
        float: left;
        padding: 10px 12px;
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