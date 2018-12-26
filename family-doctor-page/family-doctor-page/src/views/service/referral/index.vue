<template>
  <div class="">
    <div class='layout-content-main' style="height:90%; overflow:auto;">
      <div class='columnBox menu'>
        <div class='filterTitle'>时间范围</div>
        <Date-picker v-model="searchParams.beginDate" format="yyyy-MM-dd" type="date" placeholder="开始日期" style="width: 110px"></Date-picker>
          <Date-picker v-model="searchParams.endDate" format="yyyy-MM-dd" type="date" placement="bottom-end" placeholder="结束日期" style="width: 110px"></Date-picker>
        <div class='filterTitle' style="padding-left:5px">最&#8195;近</div>
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
        <div class='filterTitle'  style="padding-left:8px">关键字</div>
        <Input v-model="searchParams.keyWords"  placeholder='请输入关键字搜索' style="width: 120px"></Input>
        <div class='filterTitle'  style="padding-left:8px">状&#8195;态</div>
        <Select v-model="searchParams.signType"  style='width:120px' placeholder='全部'>
            <Option v-for='item,index in signType' :value='item.value' :key='index'>{{ item.label }}</Option>
        </Select>
        <div class='filterTitle'  style="padding-left:8px">类&#8195;别</div>
        <Select v-model="searchParams.leibieType"  style='width:120px' placeholder='全部'>
            <Option v-for='item,index in leibieType' :value='item.value' :key='index'>{{ item.label }}</Option>
        </Select>
      </div>
      <div class='columnBox menu'>
        <div class='columnBox menu'>
        </div>
      </div>
      <div class='columnBox marginTop20'>
        <div class='columnBox menu searchInput leftshow'>
            <Button class='marginLeft12 ' type='primary'  :loading='loading2' icon='search' @click='search'>
                <span v-if='!loading2'>查询</span>
                <span v-else>Loading...</span>
            </Button>
        </div>
        <div class = "blue marginTop20"  @click='nexus'>
            <i-button class = "float_right blue" type="text">转诊关系对照表>>></i-button>
        </div>
      </div>
      <div class='rowBox marginTop20'>
          <!-- <div  style="background:#eee;padding: 1px;text-align:left;">
              <Button  type="primary"  @click.native='add'>
                  <span v-if='!loading2'>新增</span>
              </Button>
          </div> -->
        <v-button :params="params">新增</v-button>
        <v-table :searchInfo="searchInfo"></v-table>
      </div>
    </div>
  </div>
</template>
<script>
  import button from '../../../components/add_btn'
  import vTable from './referral_table.vue'
  export default {
    components: {
      'v-button': button,
      'v-table': vTable
    },
    data () {
      return {
        params: 'referral_add',
        value4: '',
        searchInfo: {},
        searchParams: {
          beginDate: '',
          endDate: '',
          recently: '',
          keyWords: '',
          signType: '',
          sort: ''
        },
        loading: false,
        loading2: false,
        dataBtnValue: '一个月',
        stateType: [
          {
            value: '',
            label: '全部'
          },
          {
            value: '0',
            label: '待审批'
          },
          {
            value: '1',
            label: '已生效'
          },
          {
            value: '2',
            label: '已结束'
          }
        ],
        signType: [
          {
            value: '',
            label: '全部'
          },
          {
            value: '0',
            label: '取消'
          },
          {
            value: '1',
            label: '转入成功'
          },
          {
            value: '2',
            label: '转出成功'
          },
          {
            value: '3',
            label: '待接收'
          },
          {
            value: '4',
            label: '未接收'
          }
        ],
        sortType: [
          {
            value: 'desc',
            label: '生效日期降序'
          },
          {
            value: 'asc',
            label: '生效日期升序'
          }
        ],
        leibieType: [
          {
            value: '0',
            label: '上转'
          },
          {
            value: '1',
            label: '下转'
          }
        ]
      }
    },
    methods: {
      toLoading () {
        this.loading = true
      },
      nexus () {
        this.$router.push({ path: 'referral_nexus' })
      },
      search () {
        // console.log(this.searchParams)
        this.searchInfo = [this.searchParams]
      },
      add () {
        this.$router.push({ path: 'referral_add' })
      }
    }
  }
</script>
<style scoped lang="less">
.rowBox {
  display: -webkit-flex;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  padding: 2px;
}
.columnBox {
  display: -webkit-flex;
  /* Safari */
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
}
.blue {
    color: blue;
}
.marginTop20 {
   margin-top: 8px;
}
.marginTop12 {
   margin-top: -20px;
}

.filterBar {
  background: #f8f8f9;
  border: 1px #dddee1 solid;
  border-radius: 4px;
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
.menu {
  flex-direction: row;
  align-items: center;
}
.layout-content-main {
  padding: 10px;
}
.layout-copy {
  text-align: center;
  padding: 10px 0 20px;
  color: #9ea7b4;
}
.layout-content {
  min-height: 85%;
  margin-top: 15px;
  margin-left: 1px;
  overflow: hidden;
  background: #fff;
  border-radius: 1px;
}
.filterTitle {
  font-size: 12px;
  color: #1c2438;
  padding: 10px 12px;
}
.searchInput {
  flex: 1;
  margin-right: 0px;
  
}
.marginLeft12 {
  margin-left: 12px;
}
.rightshow {
  justify-content: flex-end;
  height: 20px;
}
</style>
