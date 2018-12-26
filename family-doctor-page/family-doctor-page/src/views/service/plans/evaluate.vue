<template>
  <div style="overflow:auto">
    <div>
      <div class="layout-content-main">
        <Form ref="residentData" :model="residentData" :label-width="100" label-position="right">
          <fieldset>
              <legend>
                基本信息
              </legend>
              <Row>
                  <Col span="6">
                    <Form-item label="服务时间">
                    {{plandata.createdate}}
                    </Form-item>
                  </Col>
                  <Col span="6">
                    <Form-item label="服务单号">
                    {{plandata.servicenumber}}
                    </Form-item>
                  </Col>
                  <Col span="6">
                    <Form-item label="医疗服务内容">
                      <Tooltip placement="bottom-end"   content="这里是提示文字">
                          {{plandata.detastr}}  
                        <div slot="content">
                              <p v-for="item in plandata.details">{{item.str}}</p>
                          </div>
                    </Tooltip>
                    </Form-item>
                  </Col>
                  <Col span="6">
                    <Form-item label="医疗服务方式">
                    {{plandata.isappointment === 0 ? '上门服务' : plandata.isappointment === 1 ? '预约服务' : ''}}
                    </Form-item>
                  </Col>
              </Row>
                <Row>
                  <Col span="6">
                    <Form-item label="服务对象">
                    {{plandata.membername}}
                    </Form-item>
                  </Col>
                  <Col span="6">
                    <Form-item label="签约单号">
                    {{plandata.contactnumber}}
                    </Form-item>
                  </Col>
                  <Col span="6">
                    <Form-item label="联系电话">
                    {{plandata.residenttelphone}}
                    </Form-item>
                  </Col>
                  <Col span="6">
                    <Form-item label="地址">
                    {{plandata.residentaddress}}
                    </Form-item>
                  </Col>
              </Row>
          </fieldset>
          <fieldset>
              <legend>
                服务结论
              </legend>
                <Row>
                    <Col span="2">
                      <Form-item label="">
                      </Form-item>
                    </Col>
                    <Col span="22">
                      <Form-item label="检查结果">
                     <i-button class = "float_right blue" @click='xiangqing' type="text">查看服务记录详情>>></i-button>
                      </Form-item>
                    </Col>
                </Row>
                  <Row>
                    <Col span="2">
                      <Form-item label="">
                      </Form-item>
                    </Col>
                    <Col span="22">
                      <Form-item label="诊断结论">
                       {{residentData.conclusion}} 
                      </Form-item>
                    </Col>
                </Row>
                <Row>
                    <Col span="2">
                      <Form-item label="">
                      </Form-item>
                    </Col>
                    <Col span="22">
                      <Form-item label="医嘱">
                       {{residentData.advice}} 
                      </Form-item>
                    </Col>
                </Row>
                  <Row>
                    <Col span="18">
                      <Form-item label="">
                      </Form-item>
                    </Col>
                    <Col span="6">
                        <div class="center">
                          出诊医生
                           {{residentData.servicetreatmentdoctorname}} 
                        </div>
                        <div class="center">
                       {{residentData.createdate}} 
                        </div>
                    </Col>
                </Row>
          </fieldset>
          <fieldset>
            <legend>
              服务评价
            </legend>
              <Row>
                <Row>
                    <Col span="2">
                      <Form-item label="">
                      </Form-item>
                    </Col>
                    <Col span="22">
                      <Form-item label="服务满意度">
                        <Rate disabled v-model="residentData.serviceratenum"></Rate>
                      </Form-item>
                    </Col>
                </Row>
                  <Row>
                    <Col span="2">
                      <Form-item label="">
                      </Form-item>
                    </Col>
                    <Col span="22">
                      <Form-item label="评价内容">
                        <div v-for="item in evaluate"> 
                            <div>{{item.comment}}</div>
                            <div>{{item.createdate}}</div>
                        </div>
                      </Form-item>
                    </Col>
                </Row>
              </Row>
          </fieldset>
          <div class="center" style="margin:5px">
            <Button style="width:80px" type="primary" @click="back()">返回</Button>
          </div>
        </Form>
      </div>
    </div>
  </div>
</template>
<script>
import {
  findtreatmentbycon, findplanbykey
  , findevaluatebycon
} from '../../../api/api'
export default {
  data () {
    return {
      id: '',
      residentData: {
      },
      plandata: {},
      evaluate: []
    }
  },
  created: function () {
    this.id = this.$route.params.id
    // alert(this.$route.params.id)
    let par = {
      id: this.$route.params.id
    }
    findplanbykey(par).then((res) => {
      if (res.status === 200) {
        // console.log(res.data)
        // 将内容组成数据
        res.data.details = []
        var a = res.data.detail
        var detailsstr = a.replace(/<.*?>/ig, '')
        if (detailsstr !== '' && detailsstr.length > 0) {
          var num = Math.ceil(detailsstr.length / 20)
          var item = []
          var k = 0
          for (var i = 0; i < num; i++) {
            var datastr = {
              str: ''
            }
            datastr.str = detailsstr.substring(k, k + 20)
            if (i === 0) {
              res.data.detastr = detailsstr.substring(0, 10)
            }
            k = k + 20
            item.push(datastr)
          }
          res.data.details = item
        }
        this.plandata = res.data
      }
    })
    let para = {
      planidentify: this.$route.params.id
    }
    findtreatmentbycon(para).then((res) => {
      if (res.status === 200) {
        // 判断满意度是否为空
        if (res.data[0].servicerate !== null && res.data[0].servicerate !== '' && res.data[0].servicerate !== undefined) {
          res.data[0].serviceratenum = res.data[0].servicerate
        } else {
          res.data[0].serviceratenum = 0
        }
        this.residentData = res.data[0]
        // console.log(this.residentData.id)
        if (res.data[0] !== null) {
          let param = {
            servicerecordidentify: this.residentData.id
          }
          findevaluatebycon(param).then((res) => {
            if (res.status === 200) {
              this.evaluate = res.data
            }
          })
        }
      }
    })
  },
  methods: {
    // 返回上一页
    back () {
      this.$router.push({ path: 'plans' })
    },
    xiangqing () {
      this.$router.push({ name: 'conclusion/details', params: {id: this.id, medicalteamidentity: this.plandata.medicalteamidentity, servicerecordidentify: this.residentData.id} })
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
.center {
    text-align: center;
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
</style>
