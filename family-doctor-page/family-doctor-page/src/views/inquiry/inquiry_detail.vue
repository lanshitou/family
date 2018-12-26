/**
 * @Title : 问诊记录详情
 * @Description : 
 * @author : TiuWeb
 */
<template>
  <div>
    <div class="layout-content" style="height:90%; overflow:auto;">
       <Row type="flex" justify="end">
        <Button type="primary" @click="last">返回问诊列表</Button>
       </Row>
      <div class="layout-content-main">
        <hr></hr>
        <Row type="flex" justify="center">
          <Col span="7">
          <p>提交时间 {{inquiryModel.createdate}}</p>
          </Col>
        </Row>
        <Form ref="inquiryModel" class="form-block" :model="inquiryModel" :label-width="100">
          <Row type="flex" justify="start">
            <Col>
            <img src="../../assets/person.png" height="50" width="50" style="margin-right:15px">
            <p>用户 {{inquiryModel.loginname}}</p>
            </Col>
            <Col span="15" style="float:left">
            <p>病情类别 &nbsp;&nbsp;&nbsp;{{inquiryModel.patientscategoryname}}</p>
            <p>病情描述 &nbsp;&nbsp;&nbsp;{{inquiryModel.content}}</p>
            <!-- <Input v-model="inquiryModel.content" type="textarea" placeholder="内容描述" disabled :autosize="{minRows: 3}" ></Input> -->
            </Col>
          </Row>
          <P class="color">问诊信息区</P>
          <hr style="border:1px dashed blue;"></hr>
          <P class="color">问诊回复区</P>
          <Row type="flex" justify="center">
            <Col span="7">
            <p v-model="inquiryModel.createdate">提交时间 {{inquiryModel.createdate}}</p>
            </Col>
          </Row>
          <Form-item v-for="(item, index) in inquiryModel.replys" :key="index"  v-model="inquiryModel.reply">
            <Row type="flex" justify="start" v-if="item.comein === false">
              <Col span="">
              <img src="../../assets/person.png" height="50" width="50" style="margin-right:25px">
              <p>{{item.replayname}}</p>
              </Col>
              <Col span="10">
                 <div class="divPop" style="float:left;">{{item.reply}}</div>
                 <div class="divPopBefore"></div>   
                 </Col>
            </Row>
            <Row type="flex" justify="end" v-if="item.comein === true">
              <Col span="10">
                <div  class="divPop" style="float:right;">{{item.reply}}</div><div class="divPopAfter"></div>  
              </Col>
              <Col span="3">
              <img src="../../assets/framework/cloud.png" height="50" width="50" style="margin-left:25px">
              <p style="margin-left:30px">{{item.replayname}}</p>
              </Col>
            </Row>
          </Form-item>
          <div v-show="doctorinput">
            <Form-item v-for="(items, index) in doctor" :key="index" v-model="doctor">
            <Row type="flex" justify="end">
              <Col span="10">
              <Input v-model="items.reply" type="textarea" :autosize="true"  placeholder="医生结论" size="large" ></Input>
              </Col>
              <Col span="3">
              <img src="../../assets/framework/cloud.png" height="50" width="50" style="margin-left:25px">
              <p>{{items.replayname}}</p>
              </Col>
            </Row>
          </Form-item>
        </div>
          <Form-item>
            <Row type="flex" justify="end">
              <Col span="4">
              <span v-show="buttonhandle">
                <Button type="primary" @click="handle">发送</Button>
              </span>
              </Col>
            </Row>
          </Form-item>
          <div v-show="inquiry_endTime">
            <Alert show-icon>
              <p v-model="inquiryModel.updatedate">本次问诊已结束 {{inquiryModel.updatedate}}</p>
            </Alert>
          </div>
          <div v-show="assess">
            <hr style="border:1px dashed blue;"></hr>
            <P class="color">评价后</P>
          <Form-item v-for="(item, index) in inquiryModel.evaluates"  :key="index" v-model="inquiryModel.evaluates">
            <Row type="flex" justify="start">
              <Col span="2">
              <p>用户名</p>
              </Col>
              <Col span="15">
              <p>{{item.createuser}}</p>
              </Col>
            </Row>
            <Row type="flex" justify="start">
              <Col span="2">
              <P>评价时间</P>
              </Col>
              <Col span="15">
              <p>{{item.createdate}}</p>
              </Col>
            </Row>
            <Row type="flex" justify="start">
              <Col span="2">
              <P>是否解决</P>
              </Col>
              <Col span="15">
              <Radio-group v-model="item.isresolve">
                <Radio label="是" ></Radio>
                <Radio label="否" ></Radio>
              </Radio-group>
              </Col>
            </Row>
            <Row type="flex" justify="start">
              <Col span="3">
              <P>响应结果满意度</P>
              </Col>
              <Col span="15">
              <Rate allow-half v-model="item.responserate" disabled></Rate>
              </Col>
            </Row>
            <Row type="flex" justify="start">
              <Col span="3">
              <P>处理结果满意度</P>
              </Col>
              <Col span="15">
              <Rate allow-half v-model="item.resultrate" disabled></Rate>
              </Col>
            </Row>
            <Row type="flex" justify="start">
              <Col span="2">
              <P>评价内容</P>
              </Col>
              <Col span="20">
                <p>{{item.reply}}</p>
              <!-- <Input v-model="item.reply" type="textarea" placeholder="评价内容" disabled :autosize="true" size="large"></Input> -->
              </Col>
            </Row>
          </Form-item>
          </div>
          <div v-show="assessend">
            <Alert show-icon>
              <P class="colorassess">注： 若一周内未做出评价默认为好评</P>
            </Alert>
          </div>
        </Form>
      </div>
    </div>
  </div>
</template>
<script>
import {
  findInquirytByKey, insertNotice
} from '../../api/api'
export default {
  data () {
    return {
      doctorinput: false,
      value: '',
      assessend: false,
      buttonhandle: false,
      inquiry_endTime: false,
      assess: false,
      doctor: [
        {
          replayname: '',
          reply: '',
          comein: true
        }
      ],
      inquiryModel: {
        isresolve: '',
        inquirystatus: '',
        createdate: '',
        loginname: '',
        patientscategoryname: '',
        content: '',
        updatedate: '',
        replys: [],
        evaluates: []
      }
    }
  },
  methods: {
    // 返回
    last () {
      this.$router.push({path: '/inquiry'})
    },
    //  发送按钮
    handle () {
      if (this.doctor[0].reply === '' | this.doctor[0].reply === null) {
        this.$Message.error('发送消息不能为空！')
      } else {
        this.handleSubmit()
      }
    },
    // id查询公共方法
    getDatas: function (params) {
      let vm = this
      let para = {
        id: vm.$route.params.id
      }
      vm.$Loading.start()
      findInquirytByKey(para).then((res) => {
        vm.$Loading.finish()
        vm.inquiryModel = res.data
        if (vm.inquiryModel.inquirystatus === 0) { // 0 未回复
          vm.doctorinput = true  // 医生对话框
          vm.buttonhandle = true // 医生对话框发送按钮
        }
        if (vm.inquiryModel.inquirystatus === 1) { // 1 回复中
          vm.doctorinput = true  // 医生对话框
          vm.buttonhandle = true // 医生对话框发送按钮
        }
        if (vm.inquiryModel.inquirystatus === 2) { // 2 已解决
          vm.inquiry_endTime = true // 询问结束时间
          vm.assessend = true // 评价提示
        }
        if (vm.inquiryModel.inquirystatus === 3) { // 3 已评价
          vm.inquiry_endTime = true // 询问结束时间
          vm.assess = true // 评价
        }
        for (var i = 0; i < vm.inquiryModel.evaluates.length; i++) {
          if (vm.inquiryModel.evaluates[i].isresolve === false) {
            vm.inquiryModel.evaluates[i].isresolve = '是'
          } else {
            vm.inquiryModel.evaluates[i].isresolve = '否'
          }
        }
      })
    },
    // 提交按钮
    handleSubmit () {
      var replynew = ''
      for (var j = 0; j < this.doctor.length; j++) {
        replynew = this.doctor[j].reply
      }
      let p = {
        residentinquiryidentity: this.inquiryModel.id,
        reply: replynew,
        comein: true
      }
      insertNotice(p).then((resp) => {
        if (resp.status === 200) {
          this.$Message.success('提交成功！')
          this.doctor[0].reply = ''
          this.getDatas()
        } else {
          this.$Message.error('提交失败！')
        }
      })
    }
  },
  created () {
    this.getDatas()
  }
}
</script>
<style scoped lang="less">
  .divPop {
    padding: 0px 10px;
    margin-top:20px; 
    background-color: #B0E2FF; 
    border-radius:5px 5px 5px 5px;
  }
  .divPopAfter {
    position:relative; 
    left:100%;
    bottom:-25px;
    width:0;
    height:0;
    border-width:5px;
    border-style:solid;
    border-color:transparent;
    margin-bottom: 60px;
    border-left-width:10px;
    border-left-color:currentColor;
    color:#B0E2FF;
  }
  .divPopBefore {
    position:relative; 
    right:100%;
    bottom:-25px;
    width:0;
    height:0;
    border-width:8px;
    border-style:solid;
    border-color:transparent;
    left: -20px;  
    margin-bottom:60px;
    border-right-width:15px;
    border-right-color:currentColor;
    color:#B0E2FF;}
.filterTitle {
  font-size: 12px;
  color: #1c2438;
  padding: 10px 12px;
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
.layout-content {
  min-height: 78%;
  margin-top: 15px;
  overflow: hidden;
  background: #fff;
  border-radius: 4px;
}
.layout-copy {
  text-align: center;
  padding: 10px 0 20px;
  color: #9ea7b4;
}
.layout-content-main {
  padding: 10px;
  margin-bottom: 10px;
}
.color {
    color: blue;
  }
.colorassess {
  color: red;
}
</style>
