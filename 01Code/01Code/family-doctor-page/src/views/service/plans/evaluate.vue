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
                      <Span v-if="plandata.detail !== '' && plandata.detail.length < 10">
                          {{plandata.detail.substring(0,10)}}
                      </Span>
                      <Tooltip placement="bottom-end" v-if="plandata.detail !== '' && plandata.detail.length > 10"  content="这里是提示文字">
                          {{plandata.detail.substring(0,10)}}...
                        <div slot="content">
                              <p>{{plandata.detail.substring(0,20)}}</p>
                              <p>{{plandata.detail.substring(20,40)}}</p>
                              <p>{{plandata.detail.substring(40,60)}}</p>
                              <p>{{plandata.detail.substring(60,80)}}</p>
                              <p>{{plandata.detail.substring(80,100)}}</p>
                              <p>{{plandata.detail.substring(100,120)}}</p>
                              <p>{{plandata.detail.substring(120,140)}}</p>
                              <p>{{plandata.detail.substring(140,160)}}</p>
                              <p>{{plandata.detail.substring(160,180)}}</p>
                              <p>{{plandata.detail.substring(180,200)}}</p>
                              <p>{{plandata.detail.substring(200,220)}}</p>
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
                        <Rate disabled v-model="residentData.servicerate"></Rate>
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
        this.plandata = res.data
      }
    })
    let para = {
      planidentify: this.$route.params.id
    }
    findtreatmentbycon(para).then((res) => {
      if (res.status === 200) {
        this.residentData = res.data[0]
        // console.log(this.residentData.id)
        if (this.residentData !== null) {
          let param = {
            // findevaluatebycon
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
