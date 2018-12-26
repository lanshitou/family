<template>
  <div>
    <div class="layout-content-main" style="height:95%; overflow:auto;">
      <div class='columnBox menu'>
        <div class='filterTitle'>时间范围</div>
          <Date-picker v-model="searchParams.beginDate" format="yyyy-MM-dd" type="date" placeholder="开始日期" style="width: 110px"></Date-picker>
          <Date-picker v-model="searchParams.endDate" format="yyyy-MM-dd" type="date" placement="bottom-end" placeholder="结束日期" style="width: 110px"></Date-picker>
        <div class='filterTitle' style="padding-left:10px">最&#8195;近</div>
        <Select v-model="searchParams.recently" style="width:150px">
          <Option value="1" label="一个月">
            <span>一个月</span>
            <span style="float:right;color:#ccc">one month</span>
          </Option>
          <Option value="2" label="三个月">
            <span>三个月</span>
            <span style="float:right;color:#ccc">three months</span>
          </Option>
          <Option value="3" label="半年">
            <span>半年</span>
            <span style="float:right;color:#ccc">half a year</span>
          </Option>
          <Option value="4" label="一年">
            <span>一年</span>
            <span style="float:right;color:#ccc">one year</span>
          </Option>
        </Select>
        <div class='filterTitle'>关键字</div>
        <Input v-model="searchParams.keyWords" style="width:150px" placeholder="请输入关键字查询"></Input>
        <Button type="primary" icon="ios-search" style="margin-left:8px" @click="query">查询</Button>
      </div>
      <div>
        <Tabs @on-click="check">
          <Tab-pane label="待确认">
            <v-comfirme :searchInfo="searchInfo"></v-comfirme>
          </Tab-pane>
           <Tab-pane label="待执行">
            <v-execute :searchInfo="searchInfo"></v-execute>
          </Tab-pane>
          <Tab-pane label="已过期">
            <v-outOfDate :searchInfo="searchInfo"></v-outOfDate>
          </Tab-pane>
          <Tab-pane label="已完成">
            <v-finish :searchInfo="searchInfo"></v-finish>
          </Tab-pane> 
        </Tabs>
      </div>
    </div>
  </div>
</template>
<script>
import vExecute from '../../../views/service/plans/execute.vue'
import vComfirme from '../../../views/service/plans/comfirme.vue'
import vOutOfDate from '../../../views/service/plans/out_of_date.vue'
import vFinish from '../../../views/service/plans/finish.vue'
export default {
  components: {
    'v-execute': vExecute,
    'v-comfirme': vComfirme,
    'v-outOfDate': vOutOfDate,
    'v-finish': vFinish
  },
  data () {
    return {
      searchInfo: [],
      order: '',
      page: 0,
      rows: 0,
      sort: '',
      searchParams: {
        beginDate: '',
        endDate: '',
        recently: '',
        keyWords: '',
        status: 0
      }
    }
  },
  methods: {
    // 查询数据
    query () {
      this.searchInfo = [this.searchParams]
    },
    // 获取所选的tabs
    check (name) {
      this.searchParams.status = name // 获取tabs当前选中的name
      this.searchInfo = [this.searchParams]
    }
  }
}
</script>
<style scoped lang="less">
.columnBox {
  display: -webkit-flex;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
}

.menu {
  flex-direction: row;
  align-items: center;
}

.filterTitle {
  font-size: 12px;
  color: #1c2438;
  padding: 10px 12px;
}

.searchInput {
  flex: 1;
  justify-content: flex-end;
  margin-right: 12px;
}
.layout-content {
  min-height: 200px;
  margin: 15px;
  overflow: hidden;
  background: #fff;
  border-radius: 4px;
}

.layout-content-main {
  padding: 12px;
}

.layout-copy {
  margin-top:10px;
  text-align: center;
  padding: 10px 0 20px;
  color: #9ea7b4;
}

.ivu-col {
  transition: width .2s ease-in-out;
}
</style>
