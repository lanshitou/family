/**
 * @Title : 投诉建议详情
 * @Description : 
 * @author : TiuWeb
 */
<template>
    <div>
        <div style="height:90%; overflow:auto;">
          <div class="layout-content-main">
            <Row type="flex" justify="end">
              <Button type="primary" style="margin-right:15px" @click="last">返回</Button>
            </Row>
                <Form ref="inquiryModel" class="form-block" :model="inquiryModel" :label-width="100">
                <fieldset style="margin:5px">
                  <legend>
                    发起方信息
                  </legend>
                <Row type="flex" justify="start">
                  <Col span="1">
                  </Col>
                  <Col span="5">
                  <Form-item label="账号" prop="createuser">
                    <p>{{inquiryModel.createuser}}</p>
                  </Form-item>
                  </Col>
                  <Col span="5">
                  <Form-item label="提交时间" prop="createdate">
                    <p>{{inquiryModel.createdate}}</p>
                  </Form-item>
                  </Col> 
                  <Col span="5">
                  <Form-item label="类别" prop="category">
                    <p>{{inquiryModel.category}}</p>
                  </Form-item>
                  </Col> 
                  <Col span="5">
                  <Form-item label="接收机构" prop="orgname">
                    <p>{{inquiryModel.orgname}}</p>
                  </Form-item>
                  </Col>
                </Row>
                <Row type="flex" justify="start">
                  <Col span="1">
                  </Col>
                  <Col span="20">
                  <Form-item label="内容" prop="content">
                    <p>{{inquiryModel.content}}</p>
                    <!-- <Input v-model="inquiryModel.content" type="textarea"  placeholder="内容描述" disabled :autosize="{minRows: 3}" size="large"></Input> -->
                  </Form-item>
                  </Col>
                </Row>
                </fieldset>
                <fieldset style="margin:5px">
                  <legend>
                    回复处理
                  </legend>
                <Row type="flex" justify="start">
                  <Col span="1">
                  </Col>
                  <Col span="5">
                  <Form-item label="机构" prop="orgname">
                    <p>{{inquiryModel.orgname}}</p>
                  </Form-item>
                  </Col>
                </Row>
                <Row type="flex" justify="start">
                  <Col span="1">
                  </Col>
                  <Col span="20">
                  <Form-item label="内容" prop="reply">
                    <p>{{inquiryModel.reply}}</p>
                    <!-- <Input v-model="inquiryModel.reply" type="textarea"  placeholder="内容描述" disabled :autosize="{minRows: 3}" size="large"></Input> -->
                  </Form-item>
                  </Col>
                </Row>
                  <div v-show="replytime">
                    <Alert show-icon><p v-model="inquiryModel.replydate">回复时间 {{inquiryModel.replydate}}</p></Alert>
                  </div>
               </fieldset>
                <div v-show="assess">
               <fieldset style="margin:5px">
                  <legend>
                    处理评价
                  </legend>
                  <Form-item v-for="(item, index) in inquiryModel.evaluates"  :key="index" v-model="inquiryModel.evaluates">
                <Row type="flex" justify="start">
                  <Col span="2">
                    <p >账号</p>
                  </Col>
                  <Col span="15">
                    <p >{{item.loginname}}</p>
                  </Col>
                </Row>
                <Row type="flex" justify="start">
                  <Col span="2">
                    <P>提交时间</P>
                  </Col>
                  <Col span="15">
                    <p >{{item.createdate}}</p>
                  </Col>
                </Row>
                <Row type="flex" justify="start">
                  <Col span="2">
                    <P>是否解决</P>
                  </Col>
                  <Col span="15">
                    <Radio-group v-model="item.isresolve">
                        <Radio label="是"></Radio>
                        <Radio label="否"></Radio>
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
                    <!-- <Input disabled v-model="item.reply" type="textarea"  placeholder="评价内容"></Input> -->
                  </Col>
                </Row>
                </Form-item>
               </fieldset>
                </div>
                <div v-show="assessend">
               <fieldset style="margin:5px">
                  <legend>
                    处理评价
                  </legend>
                  <Alert show-icon> <P class="colorassess">注： 若一周内未做出评价默认为好评</P></Alert>
               </fieldset>
                </div>
                </Form>
          </div>
        </div>
        <div class="layout-copy">
            2011-2016 &copy; TalkingData
        </div>
    </div>
</template>
<script>
import {
  findSuggestionById
} from '../../api/api'
export default {
  data () {
    return {
      replytime: false,  // 回复时间显示控制
      assessend: false,  // 评价提示显示控制
      assess: false,  //  评价显示控制
      inquiryModel: {
        createuser: '',
        createdate: '',
        category: '',
        content: '',
        orgname: '',
        reply: '',
        replydate: '',
        evaluates: []
      }
    }
  },
  created () {
    let para = {
      id: this.$route.params.id
    }
    this.$Loading.start()
    findSuggestionById(para).then((res) => {
      this.$Loading.finish()
      this.inquiryModel = res.data
      if (this.inquiryModel.status === 2) {
        this.replytime = true
        this.assessend = true
      }
      if (this.inquiryModel.status === 3) {
        this.replytime = true
        this.assess = true
      }
      if (this.inquiryModel.category === 1) {
        this.inquiryModel.category = '投诉'
      }
      if (this.inquiryModel.category === 2) {
        this.inquiryModel.category = '建议'
      }
      if (this.inquiryModel.category === 3) {
        this.inquiryModel.category = '咨询'
      }
      if (this.inquiryModel.category === 4) {
        this.inquiryModel.category = '其他'
      }
      for (var i = 0; i < this.inquiryModel.evaluates.length; i++) {
        if (this.inquiryModel.evaluates[i].isresolve === false) {
          this.inquiryModel.evaluates[i].isresolve = '是'
        } else {
          this.inquiryModel.evaluates[i].isresolve = '否'
        }
      }
      var date = new Date(this.inquiryModel.replydate)
      var nowDate = this.formatDate(date)
      this.inquiryModel.replydate = nowDate
    })
  },
  methods: {
    // 返回
    last () {
      this.$router.push({path: '/suggestion'})
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
      var seconds = date.getSeconds()
      minute = minute < 10 ? ('0' + minute) : minute
      seconds = seconds < 10 ? ('0' + seconds) : seconds
      var newDate = y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + seconds
      return newDate
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
.title {
  font-size: 15px;
  color: #1c2438;
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
