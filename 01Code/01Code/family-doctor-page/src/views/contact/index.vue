<template>
  <div >
    <div style="height:90%;overflow:auto;">
    <div class='rowBox'>
      <div class='columnBox'>
        <div class='columnBox menu'>
          <div class='filterTitle'>时间范围</div>
            <Date-picker v-model="searchParam.beginDate" format="yyyy-MM-dd" type="date" placeholder="开始日期" style="width: 110px"></Date-picker>
            <Date-picker v-model="searchParam.endDate" format="yyyy-MM-dd" type="date" placement="bottom-end" placeholder="结束日期" style="width: 110px"></Date-picker>
          
        </div>

        <div class='columnBox menu'>
          <div class='filterTitle'>最&#8195;&#8195;近
            <Select v-model="searchParams.recently" style='width:120px' placeholder='一个月'>
              <Option v-for='(item,index) in dataBtnValue' :value='item.value' :key='index'>{{ item.label }}</Option>
            </Select> 
          </div>
        </div>
        <div class='columnBox menu'>
          <div class='filterTitle'>签约类型</div>
          <Select v-model="searchParams.applySignType" style='width:120px' placeholder='全部'>
              <Option v-for='(item,index) in signType' :value='item.value' :key='index'>{{ item.label }}</Option>
          </Select>
        </div>
        <div class='columnBox menu '>
          <div class='filterTitle'>关键字</div>
          <Input v-model="searchParams.keyWords" style="width:120px" placeholder="请输入关键字查询"></Input>
        </div>
        <div class='columnBox menu searchInput'>
          <Button type="primary" :loading='loading2' icon='search' @click='search'>
            <span v-if='!loading2'>查询</span>
            <span v-else>Loading...</span>
          </Button>
        </div>
      </div>
    </div>
    <div>
      <div>
        <div style="background:#eee;padding: 1px;text-align:left">
          <Button-group>
            <Button type="primary" @click.native="add">新增</Button>
          </Button-group>
        </div>
        <div>
          <Tabs  @on-click="check">
              <Tab-pane label="全部">
                  <v-all :searchInfo="searchInfo"></v-all>
              </Tab-pane>
              <Tab-pane label="待审批">
                  <v-wait :searchInfo="searchInfo"></v-wait>
              </Tab-pane>
              <Tab-pane label="已生效">
                  <v-success :searchInfo="searchInfo"></v-success>
              </Tab-pane>
              <Tab-pane label="已结束">
                  <v-finshp :searchInfo="searchInfo"></v-finshp>
              </Tab-pane>
          </Tabs>
        </div>
    </div>
    </div>
    </div>
  </div>
</template>
<script>
  import vAll from './protall.vue'
  import vWait from './wait.vue'
  import vSuccess from './success.vue'
  import vFinshp from './finshp.vue'
  export default {
    components: {
      'v-all': vAll,
      'v-wait': vWait,
      'v-success': vSuccess,
      'v-finshp': vFinshp
    },
    data () {
      return {
        searchInfo: [],
        searchParam: {
          beginDate: '',
          endDate: ''
        },
        searchParams: {
          beginDate: '',
          endDate: '',
          recently: '',
          keyWords: '',
          sort: '',
          applySignType: '',
          status: 0
        },
        loading: false,
        loading2: false,
        dataBtnValue: [
          {
            value: '1',
            label: '一个月'
          },
          {
            value: '2',
            label: '三个月'
          },
          {
            value: '3',
            label: '半年'
          },
          {
            value: '4',
            label: '一年'
          }
        ],
        signType: [
          {
            value: '',
            label: '全部'
          },
          {
            value: '0',
            label: '个人'
          },
          {
            value: '1',
            label: '家庭'
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
        ]
      }
    },
    methods: {
      add () {
        this.$router.push({path: 'add'})
      },
      toLoading () {
        this.loading = true
      },
      search () {
        if (this.searchParams.beginDate !== '') {
          this.searchParams.beginDate = this.formatDate(this.searchParam.beginDate)
        }
        if (this.searchParams.endDate !== '') {
          this.searchParams.endDate = this.formatDate(this.searchParam.endDate)
        }
        this.searchInfo = [this.searchParams]
      },
      // 获取所选的tabs
      check (name) {
        this.searchParams.status = name // 获取tabs当前选中的name
        if (this.searchParams.beginDate !== '') {
          this.searchParams.beginDate = this.formatDate(this.searchParam.beginDate)
        }
        if (this.searchParams.endDate !== '') {
          this.searchParams.endDate = this.formatDate(this.searchParam.endDate)
        }
        this.searchInfo = [this.searchParams]
      },
      // 日期格式转换
      formatDate (date) {
        var y = date.getFullYear()
        var m = date.getMonth() + 1
        m = m < 10 ? '0' + m : m
        var d = date.getDate()
        d = d < 10 ? ('0' + d) : d
        var newDate = y + '-' + m + '-' + d
        return newDate
      }
    }
  }
</script>
<style  scoped lang="less" >
  .layout-copy {
  text-align: center;
  padding: 10px 0 20px;
  color: #9ea7b4;
}
.rowBox {
  display: -webkit-flex;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  padding: 12px;
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
  min-height: 140px;
  margin: 15px;
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
  margin: 10px;
}
.marginLeft12 {
  margin-left: 12px;
}
.rightshow {
  justify-content: flex-end;
}
</style>
