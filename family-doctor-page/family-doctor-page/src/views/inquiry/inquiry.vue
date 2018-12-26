/**
 * @Title : 问诊记录首页
 * @Description : 
 * @author : TiuWeb
 */
<template>
  <div >
    <div class="layout-content" style="height:95%; overflow:auto;">
      <div class="layout-content-main">
        <div class='columnBox menu'>
          <div class='filterTitle'>状&#8195;&#8195;态</div>
            <Select v-model="searchParams.inquirystatus" style="width:80px;" placeholder="全部" >
                  <Option v-for="item in statu" :value="item.value" :key="item.value">{{ item.label }}</Option>
            </Select>
          <div class='filterTitle'>
            <Input v-model="searchParams.keyWords" icon="ios-search" placeholder="请输入关键字搜索" style="width: 200px"></Input>
          </div>
          <Button type="primary" @click.native='search'>查询</Button>
        </div>
        <Tabs>
          <Tab-pane label="指定问诊">
            <v-appoint :searchInfo="searchInfo"></v-appoint>
          </Tab-pane>
          <Tab-pane label="非指定问诊">
            <v-free :searchInfo="searchInfo"></v-free>
          </Tab-pane>
        </Tabs>
      </div>
    </div>
  </div>
</template>
<script>
import vAppoint from './appoint.vue'
import vFree from './free.vue'
export default {
  components: {
    'v-appoint': vAppoint,
    'v-free': vFree
  },
  data () {
    return {
      statu: [
        {
          value: '',
          label: '全部'
        },
        {
          value: '0',
          label: '未回复'
        },
        {
          value: '1',
          label: '回复中'
        },
        {
          value: '2',
          label: '已解决'
        },
        {
          value: '3',
          label: '已评价'
        }
      ],
      searchInfo: [],
      searchParams: {
        keyWords: '',
        inquirystatus: ''
      }
    }
  },
  methods: {
    search () {
      this.searchInfo = [this.searchParams]
    }
  }
}
</script>
<style scoped lang="less">
.filterTitle {
  font-size: 12px;
  color: #1c2438;
  padding: 10px 12px;
}
.menu {
  flex-direction: row;
  align-items: center;
}
.columnBox {
  display: -webkit-flex;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
}
.layout {
  border: 1px solid #d7dde4;
  background: #f5f7f9;
  position: relative;
  border-radius: 4px;
  overflow: hidden;
}
.layout-breadcrumb {
  padding: 10px 15px 0;
}
.layout-content-main {
  padding: 5px;
}
.layout-content {
  min-height: 78%;
  margin-top: 0px;
  overflow: hidden;
  background: #fff;
  border-radius: 4px;
}
</style>
