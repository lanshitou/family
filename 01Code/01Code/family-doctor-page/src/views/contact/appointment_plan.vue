<template>
  <div style="height:100%;overflow:auto">
    <div>
      <div class="layout-content-main">
        <div>
            <Form ref="formModel" v-model="formModel" :label-width="130">
            <fieldset >
              <legend style="font-weight:bold;font-size:8px;padding: 1px;text-align:left">个人信息</legend>
                  <Row>
                    <Col span="8">
                      <Form-item label="身份证号"  prop="identitynumber">
                        {{formModel.identitynumber}}
                      </Form-item>
                    </Col>
                  </Row>
                  <Row>
                    <Col span="8">
                      <Form-item label="申请人姓名"  prop="applyName">
                        {{formModel.applyname}}
                      </Form-item>
                    </Col>
                    <Col span="8">
                      <Form-item label="年龄"  prop="age">
                        {{formModel.age}}
                      </Form-item>
                    </Col>
                    <Col span="8">
                      <Form-item label="性别"  prop="sex">
                        {{formModel.sex === 0 ? '未知性别' : formModel.sex === 1 ? '男' : formModel.sex === 2 ? '女' : formModel.sex === 9 ? '未说明性别' : ''}}
                      </Form-item>
                    </Col>
                  </Row>
                  <Row>
                    <Col span="8">
                      <Form-item label="联系电话"  prop="telephone">
                        {{formModel.telephone}}
                      </Form-item>
                    </Col>
                    <Col span="8">
                        <Form-item label="家庭住址"  prop="address">
                          {{formModel.address}}
                        </Form-item>
                    </Col>
                  </Row>
              </fieldset>
            <fieldset>
              <legend style="font-weight:bold;font-size:8px;padding: 1px;text-align:left">
                签约信息
              </legend>
              <Row>
                <Col span="12">
                  <Form-item label="签约单号"  prop="contactnumber">
                    {{formModel.contactnumber}}
                  <Tag v-if="formModel.contactnumber !== undefined" type="border" color="blue" @click.native="signDetails">查看签约详情</Tag>
                  </Form-item>
                </Col>
                <Col span="12">
                  <Form-item label="签约类别"  prop="signtype">
                    {{formModel.signtype === 0 ? '个人' : formModel.signtype === 1 ? '家庭' : ''}}
                  </Form-item>
                </Col>
              </Row>
              <Row>
                <Col span="12">
                  <Form-item label="签约医疗机构"  prop="institutionname">
                    {{formModel.institutionname}}
                  </Form-item>
                </Col>
                <Col span="12">
                  <Form-item label="签约团队"  prop="teamname">
                    {{formModel.teamname}}
                  </Form-item>
                </Col>
              </Row>
            </fieldset>
          </Form>
        </div>
        <Tabs>
          <Tab-pane label="已有计划">
              <div style="height:900px">
                <div class='columnBox menu'>
                  <div class='filterTitle'>时间范围</div>
                  <Date-picker v-model="searchParams.beginDate" format="yyyy-MM-dd" type="date" placeholder="开始日期" style="width: 110px"></Date-picker>
                  <Date-picker v-model="searchParams.endDate" format="yyyy-MM-dd" type="date" placement="bottom-end" placeholder="结束日期" style="width: 110px"></Date-picker>
                  <div class='filterTitle' style="padding-left:8px">最&#8195;近</div>
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
                 <Input v-model="searchParams.queryCondition" style="width:130px" placeholder="请输入关键字查询"></Input>
                  <Button type="primary"  style="margin-left:10px" @click="queryJ">查询</Button> 
                  <Button type="primary"  style="margin-left:20px" @click="breakout">返回</Button> 
                </div>
                <div class='columnBox menu'>
                </div>
                <Table :data="listData" @on-selection-change="select" :columns="tableColumns" border>
                </Table>
                <div  class="pages-block">
                  <div class="pages-right">
                    <Page :total="total" :page-size="rows" @on-page-size-change="doPageSizeChange" @on-change="change" show-sizer show-total show-elevator></Page>
                  </div>
                </div>
              </div>
  
          </Tab-pane>
          <Tab-pane label="待预约">
              <Form ref="yuyuelist" v-model="yuyuelist" :label-width="130">
                  <table  class="gridtable" :columns="serviceColumn" :data="yuyuelist">
                    <tr>
                      <th v-for="items in serviceColumn">{{items.title}}</th>
                    </tr>
                    <tr v-for="(items, index) in yuyuelist">
                        <td  v-if="items.ishebing !== '1' " :rowspan="items.rowNum" style="border-width: 1px;">
                          <!-- 类别 -->
                              {{items.servicecategoryname}}
                          </td>
                            <!-- 项目 -->
                          <td class="tdheigth">
                            {{items.servicename}}
                          </td>
                          <!-- 服务计划 -->
                          <td>
                            {{items.servicemesure}}{{items.servicemesureunit}}/
                            {{items.serviceperiodidentity === '0' ? '每年' : items.serviceperiodidentity === '1' ? '每月' : items.serviceperiodidentity === '2' ? '每周' : ''}}
                            , {{items.isappointment === 0 ? '上门服务' : items.isappointment === 1 ? '需预约' : ''}}
                          </td>
                          <!-- 所需医疗人员 -->
                          <td>
                            {{items.doctormesure}}{{items.doctormesureunit}}
                          </td>
                          <!-- 医疗设备 -->
                          <td>
                            {{items.medicalequipmentname}}
                          </td>
                          <!-- 预计收费 -->
                          <td>
                            {{items.servicecharge}}{{items.servicechargeunit}}/{{items.servicechargefrequencyname}}
                          </td>
                          <!-- 显示服务对象名称 -->
                          <td v-if="items.customercategory === 2"  >
                            <Span v-for="item in items.customer"> {{item.membername}}&nbsp;&nbsp; </Span>
                          </td>
                          <!-- 显示服务对象 -->
                          <td v-if="items.customercategory !== 2">
                            {{items.customercategory === 0 ? '全部家庭成员' : items.customercategory === 1 ? '仅本人' : ''}}
                          </td>
                          <td>
                            已服务{{items.servicecount}}次，共{{items.totalcount}}次 
                          </td>
                    </tr>
                  </table>
                </Row>
          </Form>
          </Tab-pane>
        </Tabs>
  
      </div>
    </div>
  </div>
</template>
<script>
import {
    findContactByKey
    , getPlanListPage
} from '../../api/api'
export default {
  data () {
    return {
      id: '',
      formModel: {
      },
      // 详情list
      detailList: [],
      yuyuelist: [],
      searchParams: {
        timeRange: '',
        recently: '',
        queryCondition: '',
        sort: '',
        name: '吴晶晶'
      },
      total: 0,
      page: 1,
      // 每页显示几条
      rows: 10,
      listData: [],
      keyword: '',
      serviceColumn: [
        {
          title: '签约服务类别',
          key: 'type'
        },
        {
          title: '签约服务项目',
          key: 'item'
        },
        {
          title: '服务计划',
          key: 'plan'
        },
        {
          title: '所需医疗人员',
          key: 'doctors'
        },
        {
          title: '医疗设备',
          key: 'advice'
        },
        {
          title: '预计收费',
          key: 'fee'
        },
        {
          title: '服务对象',
          key: 'target'
        },
        {
          title: '备注',
          key: 'remark'
        }
      ],
      tableColumns: [
        {
          title: '计划服务时间',
          align: 'center',
          sortable: true,
          key: 'expectservicedate',
          render: (h, params) => {
            var date = this.renderDate(params.row.expectservicedate) // 获取到当前列的服务时间字段毫秒数
            return h('div', [
              h('span', date)
            ])
          }
        },
        {
          title: '服务对象',
          align: 'center',
          key: 'membername'
        },
        {
          title: '服务状态',
          align: 'center',
          key: 'status',
          render: (h, params) => {
            // 0待确认,1待执行,2已过期,3已完成'
            var str = params.row.status === 0 ? '待确认' : params.row.status === 1 ? '待执行' : params.row.status === 2 ? '已过期' : params.row.status === 3 ? '已完成' : ''
            return h('div', [
              h('span', str)
            ])
          }
        },
        {
          title: '服务项目',
          key: 'servicename',
          align: 'center',
          render: (h, params) => {
            return h('Poptip', {
              props: {
                trigger: 'hover',
                title: '详情',
                placement: 'right-start'
              }
            }, [
              h('Tag', params.row.servicename),
              h('div', {
                slot: 'content'
              }, [
                h('ul', this.detailList[params.index].split.map(item => {
                  return h('li', {
                    style: {
                      textAlign: 'center',
                      padding: '4px'
                    }
                  }, item)
                }))
              ])
            ])
          }
        },
        {
          title: '费用标准',
          key: 'serviceCost',
          align: 'center'
        },
        {
          title: '服务方式',
          key: 'serviceWay',
          align: 'center',
          render: (h, params) => {
            var str = params.row.isappointment === true ? '预约就诊' : '上门服务'
            return h('div', [
              h('span', str)
            ])
          }

        }
      ],
      value4: ''
    }
  },
  created () {
    // console.log(this.$route.params.id)
    this.id = this.$route.params.id
    // 分页查询计划根据主键
    let param = {
      page: 1,
      rows: this.rows,
      order: 'expect_service_date',
      // 排序
      sort: this.searchParams.sort,
      searchParams: {
        contactidentify: this.$route.params.id,
        // 开始时间
        beginDate: this.searchParams.beginDate,
        // 结束时间
        endDate: this.searchParams.endDate,
        // 最近
        recently: this.searchParams.recently,
        // 关键字
        keyWords: this.searchParams.keyWords
      }
    }
    getPlanListPage(param).then((res) => {
      if (res.status === 200) {
        // console.log('res.data')
        this.total = res.data.total
        this.listData = res.data.rows
        // console.log(this.listData)
        this.zhenglixiangqing()
      }
    })
    let para = {
      // 主键
      id: this.$route.params.id
    }
    // 根据主键查询签约服务项目
    findContactByKey(para).then((res) => {
      // console.log(res.data)
      this.formModel = res.data
      // 将查出的数据排序
      var orderdata = {}
      if (res !== null && res.data.services !== null && res.data.services.length > 0) {
        for (var q = 0; q < res.data.services.length; q++) {
          res.data.services[q].xiangmu = false
          for (var i = 0; i < res.data.services.length; i++) {
            if (res.data.services[q].servicecategoryid < res.data.services[i].servicecategoryid) {
              orderdata = res.data.services[q]
              res.data.services[q] = res.data.services[i]
              res.data.services[i] = orderdata
            }
          }
        }
        // 将预约的服务计划找出来就行了
        this.yuyuelist = []
        for (var w = 0; w < res.data.services.length; w++) {
          if (res.data.services[w].isappointment === 1) {
            this.yuyuelist.push(res.data.services[w])
          }
        }
      }
      // 将返回的值赋给渲染字段
      if (this.yuyuelist !== null && this.yuyuelist.length > 1) {
        // 合并的行数
        var num = 1
        // 第一个相等的下标
        var farstnum = 0
        for (var j = 0; j < this.yuyuelist.length; j++) {
          if (j > 0) {
            if (this.yuyuelist[farstnum].servicecategoryid === this.yuyuelist[j].servicecategoryid && j !== this.yuyuelist.length) {
              this.yuyuelist[j].ishebing = '1'
              num++
              this.yuyuelist[farstnum].rowNum = num
            } else {
              farstnum = j
              num = 1
            }
          } else {
            farstnum = 0
            num = 1
          }
        }
      }
      // console.log(this.yuyuelist)
    })
  },
  methods: {
    breakout () {
      this.$router.go(-1)
    },
    // 整理详情
    zhenglixiangqing () {
      this.detailList = []
      if (this.listData !== null && this.listData.length > 0) {
        for (var i = 0; i < this.listData.length; i++) {
          var a = this.listData[i].detail
          var details = a.replace(/<.*?>/ig, '')
          this.detailList.push({
            split: [
              details.substring(0, 30),
              details.substring(30, 60),
              details.substring(60, 90),
              details.substring(90, 120),
              details.substring(120, 150),
              details.substring(150, 180),
              details.substring(180, 210),
              details.substring(210, 240),
              details.substring(240, 270),
              details.substring(270, 300)
            ]
          })
        }
      }
    },
    // 查询已有计划
    queryJ () {
      // var data = this.searchParams.timeRange
      // var recently = this.searchParams.recently
      // var queryCondition = this.searchParams.queryCondition
      // var sort = this.searchParams.sort
      let param = {
        page: 1,
        rows: this.rows,
        order: 'expect_service_date',
        // 排序
        sort: this.searchParams.sort,
        searchParams: {
          contactidentify: this.$route.params.id,
          // 开始时间
          beginDate: this.searchParams.beginDate,
          // 结束时间
          endDate: this.searchParams.endDate,
          // 最近
          recently: this.searchParams.recently,
          // 关键字
          keyWords: this.searchParams.keyWords
        }
      }
      getPlanListPage(param).then((res) => {
        if (res.status === 200) {
          // console.log('res.data')
          // console.log(res.data.rows)
          this.listData = res.data.rows
          this.zhenglixiangqing()
        }
      })
      // alert('日期：' + data + '最近：' + recently + '查询条件：' + queryCondition + '排序：' + sort)
    },
    signDetails () {
      this.$router.push({name: 'contact/detail', params: {id: this.id, backtype: '1'}})
    },
    // 点击页码时间
    change (page) {
      this.page = page
      // 这里直接更改了模拟的数据，真实使用场景应该从服务端获取数据
      let param = {
        page: page,
        rows: this.rows,
        order: 'expect_service_date',
        // 排序
        sort: this.searchParams.sort,
        searchParams: {
          contactidentify: this.$route.params.id,
          // 开始时间
          beginDate: this.searchParams.beginDate,
          // 结束时间
          endDate: this.searchParams.endDate,
          // 最近
          recently: this.searchParams.recently,
          // 关键字
          keyWords: this.searchParams.keyWords
        }
      }
      getPlanListPage(param).then((res) => {
        if (res.status === 200) {
          // console.log('res.data')
          // console.log(res.data.rows)
          this.listData = res.data.rows
          this.zhenglixiangqing()
        }
      })
    },
    doPageSizeChange: function (size) {
      this.rows = size
      let param = {
        page: 1,
        rows: this.rows,
        order: 'expect_service_date',
        // 排序
        sort: this.searchParams.sort,
        searchParams: {
          contactidentify: this.$route.params.id,
          // 开始时间
          beginDate: this.searchParams.beginDate,
          // 结束时间
          endDate: this.searchParams.endDate,
          // 最近
          recently: this.searchParams.recently,
          // 关键字
          keyWords: this.searchParams.keyWords
        }
      }
      getPlanListPage(param).then((res) => {
        if (res.status === 200) {
          // console.log('res.data')
          // console.log(res.data.rows)
          this.listData = res.data.rows
          this.zhenglixiangqing()
        }
      })
    },
    // 毫秒数转换为标准时间
    renderDate (date) {
      var commonTime = new Date(date)
      var time = this.formatDate(commonTime) // 标准时间转换为指定格式
      return time
    },
    // 日期格式转换
    formatDate (date) {
      var y = date.getFullYear()
      var m = date.getMonth() + 1
      m = m < 10 ? '0' + m : m
      var d = date.getDate()
      d = d < 10 ? ('0' + d) : d
      var h = date.getHours()
      var minute = date.getMinutes()
      minute = minute < 10 ? ('0' + minute) : minute
      var newDate = y + '-' + m + '-' + d + ' ' + h + ':' + minute
      return newDate
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
table.gridtable {
    border: 1px solid #E9EAEC;
    width: 100%;
	  font-family: verdana,arial,sans-serif;
	  font-size:11px;
	  color:#333333;
	  border-collapse: collapse;
    text-align: center;
 }
 table.gridtable th {
  font-size:14px;
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #E9EAEC;
  text-align: center;
 }
 table.gridtable td {
	border-width: 1px;
	border-style: solid;
	border-color: #E9EAEC;
  text-align: center;
 }
  table.gtable {
  border: 0px solid #E9EAEC;
  width: 100%;
  height: 100%;
  font-family: verdana,arial,sans-serif;
  font-size:11px;
  color:#333333;
  border-collapse: collapse;
  text-align: center;
 }
 table.gtable td {
	border-width: 0px;
	padding: 8px;
  text-align: center;
 }
.buttonBack {
  padding: 5px;
  float: right;
  color: #0066FF;
}
.tdheigth {
  height:50px
}
</style>
