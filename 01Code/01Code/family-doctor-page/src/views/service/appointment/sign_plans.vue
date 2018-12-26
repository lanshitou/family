<template>
  <div style="overflow:auto">>
    <div>
      <span class="headline">签约信息</span>
      <span @click="back" class="buttonBack">>>返回上一页</span>
      <div class="layout-content-main">
        <div class='columnBox menu'>
          <div class='filterTitle'>身份证号码：</div>
          <Input v-model="IdCard" placeholder="请输入身份证号码" style="width: 200px"></Input>
          <Button type="primary" icon="ios-search" style="margin-left:10px" @click="queryPerson">查询</Button>
        </div>
        <div>
          <Row>
            <Col>
            <Card>
              <Row>
                <Col span="8">
                <label class='filterTitle'>姓名：</label>
                {{queryList.name}}</Col>
                <Col span="8">
                <label class='filterTitle'>年龄：</label>
                {{queryList.age}}</Col>
                <Col span="8">
                <label class='filterTitle'>性别：</label>
                {{queryList.sex}}</Col>
              </Row>
              <Row>
                <Col span="8">
                <label class='filterTitle'>联系电话：</label>
                {{queryList.phone}}</Col>
                <Col span="8">
                <label class='filterTitle'>家庭住址：</label>
                {{queryList.address}}</Col>
              </Row>
            </Card>
            </Col>
          </Row>
          <Row>
            <Col>
            <Card>
              <Row>
                <Col span="12">
                <label class='filterTitle'>签约单号：</label>
                {{queryList.signNo}}
                </Col>
                <Col span="12">
                <label class='filterTitle'>签约类别：</label>
                {{queryList.signType}}
                </Col>
              </Row>
              <Row>
                <Col span="12">
                <label class='filterTitle'>签约医疗机构：</label>
                {{queryList.medicalOrg}}
                </Col>
                <Col span="12">
                <label class='filterTitle'>签约团队：</label>
                {{queryList.medicalTeam}}
                </Col>
              </Row>
            </Card>
            </Col>
          </Row>
        </div>
        <Tabs>
          <Tab-pane label="已有计划">
              <div>
                <div class='columnBox menu'>
                  <div class='filterTitle'>时间范围:</div>
                  <Date-picker v-model="searchParams.timeRange" type='daterange' format="yyyy年M月d日" placement='bottom-start' placeholder='选择日期' style='width: 200px'></Date-picker>
                  <div class='filterTitle' style="padding-left:100px">最&#8195;&#8195;近:</div>
                  <Select v-model="searchParams.recently" style="width:200px">
                    <Option value="一个月" label="一个月">
                      <span>一个月</span>
                      <span style="float:right;color:#ccc">one month</span>
                    </Option>
                    <Option value="三个月" label="三个月">
                      <span>三个月</span>
                      <span style="float:right;color:#ccc">three months</span>
                    </Option>
                    <Option value="半年" label="半年">
                      <span>半年</span>
                      <span style="float:right;color:#ccc">half a year</span>
                    </Option>
                    <Option value="一年" label="一年">
                      <span>一年</span>
                      <span style="float:right;color:#ccc">one year</span>
                    </Option>
                  </Select>
                  <div class='filterTitle' style="padding-left:100px"></div>
                  <Input v-model="searchParams.queryCondition" style="width:200px" placeholder="请输入关键字查询"></Input>
                </div>
                <div class='columnBox menu'>
                  <div class='filterTitle'>排&#8195;&#8195;序:</div>
                  <Select v-model="searchParams.sort" style="width:200px;float:left">
                    <Option  label="升序">
                      <span>生效日期升序</span>
                    </Option>
                    <Option  label="降序">
                      <span>生效日期降序</span>
                    </Option>
                  </Select>
                  <Button type="primary" icon="ios-search" style="margin-left:100px" @click="queryJ">查询</Button> 
                </div>
                <Table :data="listData" @on-selection-change="select" :columns="tableColumns" stripe border>
                </Table>
                <div class="pages-block">
                  <div class="pages-right">
                    <Page @on-page-size-change="doPageSizeChange" @on-change="change" show-sizer show-total show-elevator></Page>
                  </div>
                </div>
              </div>
  
          </Tab-pane>
          <Tab-pane label="待预约">
              <Input v-model="keyword" style="width:200px;margin-bottom:10px" placeholder="请输入关键字"></Input>
              <Button type="primary" icon="ios-search" style="margin-left:10px;margin-bottom:10px" @click="queryD">查询</Button>
              <Table :data="listData"  :columns="tableColumns" stripe border>
              </Table>
              <div class="pages-block">
                <div class="pages-right">
                  <Page @on-page-size-change="doPageSizeChange" @on-change="change" show-sizer show-total show-elevator></Page>
                </div>
              </div>
          </Tab-pane>
        </Tabs>
  
      </div>
    </div>>
  </div>
</template>
<script>
var appData = require('.././tableList.json')
export default {
  data () {
    return {
      IdCard: '',
      searchParams: {
        timeRange: '',
        recently: '',
        queryCondition: '',
        sort: '',
        name: '吴晶晶'
      },
      listData: appData,
      keyword: '',
      queryList: {
        name: '李一花',
        age: '53',
        sex: '女',
        phone: '123456',
        address: '陕西省西安市阎良区',
        signNo: '20170706',
        signType: '家庭',
        medicalOrg: '阎良区凤凰街道卫生医疗中心',
        medicalTeam: '张三'
      },
      tableColumns: [
        {
          title: '计划服务时间',
          align: 'center',
          key: 'planTime'
        },
        {
          title: '服务对象',
          align: 'center',
          key: 'serviceObject'
        },
        {
          title: '服务状态',
          align: 'center',
          key: 'serviceStatus'
        },
        {
          title: '服务项目',
          key: 'serviceContent',
          align: 'center'

        },
        {
          title: '费用标准',
          key: 'serviceCost',
          align: 'center'

        },
        {
          title: '服务方式',
          key: 'serviceWay',
          align: 'center'

        }
      ],
      value4: ''
    }
  },
  methods: {
    // 查询预约对象基本信息
    queryPerson () {
      var id = this.IdCard
      alert(id)
    },
    // 查询已有计划
    queryJ () {
      var data = this.searchParams.timeRange
      var recently = this.searchParams.recently
      var queryCondition = this.searchParams.queryCondition
      var sort = this.searchParams.sort
      alert('日期：' + data + '最近：' + recently + '查询条件：' + queryCondition + '排序：' + sort)
    },
    // 查询待遇约
    queryD () {
      var keyword = this.keyword
      alert(keyword)
    },
    // 清空查询条件
    empty () {
      this.timeRange = ''
      this.recently = ''
      this.queryCondition = ''
      this.sort = ''
    },
    // 返回上一页
    back () {
      this.$router.push({ path: '../appointMent' })
    },
    change (row) {
      // 这里直接更改了模拟的数据，真实使用场景应该从服务端获取数据
      console.log(row)
    },
    doPageSizeChange: function (size) {
      this.pageSize = size
      this.$Loading.start()
    },
    select (selection) {
      this.selections = selection
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

.index {
  padding: 2px;
  margin-top: 5px;
  margin-bottom: 10PX;
}

.pages-block {
  margin: 0px;
  overflow: hidden;
  margin-top: 10px;
  margin-bottom: 10px;
  text-align: center;
  .pages-right {
    vertical-align: middle;
  }
}
.layout-breadcrumb {
  padding: 5px 15px 0;
}

.layout-content-main {
  padding: 5px;
}

.layout-copy {
  text-align: center;
  padding: 10px 0 20px;
  color: #9ea7b4;
}

.headline {
  padding: 10px;
  font-size: 16px;
  color: #657180;
}

.buttonBack {
  padding: 5px;
  float: right;
  color: #0066FF;
}
</style>
