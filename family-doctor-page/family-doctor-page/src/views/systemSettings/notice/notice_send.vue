/**
 * @Title : 通知公告发布
 * @Description : 
 * @author : TiuWeb
 */
<template>
  <div class="" >
    <div class="layout-content"  style="height:90%; overflow:auto;">
        <div class="layout-content-main">
            <div class='role'>
                <Form ref="noticeModel" class="form-block" :model="noticeModel" :label-width="100" :rules="noticeValidate">
                    <Row type="flex" justify="start">
                        <Col >
                        <Form-item label="主题" prop="title">
                            <Input v-model="noticeModel.title"   placeholder="主题" style="width: 400px"></Input>
                        </Form-item>
                        </Col>
                    </Row>
                    <Row type="flex" justify="start">
                        <Col >
                        <Form-item label="发布范围" prop="publishcategory">
                          <Radio-group v-model="noticeModel.publishcategory"  @on-change="changeRadioArea" >
                              <Radio label="1">所有人可见</Radio>
                              <Radio label="2">所有医护端账号可见</Radio>
                              <Radio label="3">本机构内部可见</Radio>
                              <Radio label="4">所有签约居民和家庭可见</Radio>
                          </Radio-group>
                        </Form-item>
                        </Col>
                    </Row>
                    <Row type="flex" justify="start">
                        <Col >
                        <Form-item label="发布方式" prop="publishmode">
                          <Radio-group v-model="noticeModel.publishmode"  @on-change="changeRadioMode" style="float:left">
                              <Radio label="0">实时发送</Radio>
                              <Radio label="1">定时发送</Radio>
                          </Radio-group>
                          <span v-show="sendTime" style="float:left">
                            <Date-picker type="datetime"  placeholder="请选择发布日期" style="width: 200px" @on-change="changeTime" v-model="noticeModel.publishtime" :editable=false></Date-picker>
                          </span>
                          <p style="color:red;float:left;margin-left:5px;">{{showNotice}}</p>
                        </Form-item>
                        </Col>
                    </Row>
                    <Row type="flex" justify="start">
                        <Col span="20">
                        <Form-item label="发布内容" prop="content">
                            <Input v-model="noticeModel.content" type="textarea" :rows="6" ></Input>
                        </Form-item>
                        </Col>
                    </Row>
                    <Row>
                      <div class='filterTitle'>附件</div>
                        <Form-item  prop="attachments" v-for="(item, index) in attachments" :key="index" class="formItem">
                            <Upload 
                            multiple 
                            action="//jsonplaceholder.typicode.com/posts/" 
                            :show-upload-list="true"
                            :name=attachmentname
                            v-model="noticeModel.attachments" 
                            style="float:left;margin-left:0px"
                            >
                                <Button type="ghost" icon="ios-cloud-upload-outline">上传文件</Button>
                            </Upload>
                                <Button type="primary" @click="handledivRemove(index)" style="margin-left:15px">删除</Button>
                        </Form-item>
                    </Row>
                    <Row type="flex" justify="start">
                      <Col span="4">
                       <Form-item >
                          <Button type="dashed" @click="handleAdd" icon="plus-round" long>添加</Button>
                       </Form-item></Col>
                    </Row>
                    <Row type="flex" justify="start">
                       <Form-item >
                      <Button type="primary" style="width:80px;margin-right:20px" @click="doSubmit('noticeModel')">发布</Button>
                        <Button type="primary" style="width:80px;" @click="doCancel">返回</Button>
                        </Form-item>
                    </Row>
                </Form>
            </div>
        </div>
    </div>
  </div>
</template>
<script>
import {
  findNoticetById, sendNotices
} from '../../../api/api'
export default {
  data () {
    return {
      sendTime: false,
      showNotice: '',
      attachmentname: '',
      attachments: [
        {
          attachmentname: ''
        }
      ],
      noticeModel: {
        id: '',
        title: '',
        content: '',
        publishcategory: '1',
        publishmode: '0',
        publishtime: '',
        attachments: []
      },
      // 校验
      noticeValidate: {
        title: [
        { required: true, max: 100, message: '主题不能为空', trigger: 'blur' },
        { type: 'string', max: 100, message: '主题不能多于100个字符', trigger: 'blur' }
        ],
        content: [
        { required: true, max: 1000, message: '内容不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  created: function () {
    let para = {
      id: this.$route.params.id
    }
    this.$Loading.start()
    findNoticetById(para).then((res) => {
      this.$Loading.finish()
      this.noticeModel = res.data
      if (this.noticeModel.publishmode === 1) {
        this.sendTime = true
      }
    })
  },
  methods: {
    doSubmit (noticeModel) {
      var boo = true
      if (boo) {
        if (this.noticeModel.publishmode === '1' && this.noticeModel.publishtime === '') {
          this.$Message.error('请选择发布日期!')
          this.showNotice = '请选择发布日期'
          boo = false
        }
      }
      if (boo) {
        this.$refs['noticeModel'].validate((valid) => {
          if (valid) {
            sendNotices(this.noticeModel).then((res) => {
              if (res.status === 200) {
                this.$Message.success('再次发布成功！')
                this.$router.push({ path: 'notice' })
              } else {
                this.$Message.error('发布失败！')
              }
            })
          } else {
            this.$Message.error('通知公告信息验证失败!')
          }
        })
      }
    },
    doCancel () {
      this.$router.push({path: '/notice'})
    },
    // 添加
    handleAdd () {
      this.attachments.push({
        attachmentname: ''
      })
    },
    // 删除
    handledivRemove (index) {
      this.attachments.splice(index, 1)
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
    },
    changeRadioMode (change) {
      if (change === '1') {
        this.sendTime = true
        if (this.noticeModel.publishtime === '') {
          this.showNotice = '请选择发布日期'
        }
      } else {
        this.sendTime = false
        this.showNotice = ''
      }
    },
    changeRadioArea (change) {
    },
    changeTime (change) {
      var date = new Date(change)
      var nowDate = new Date()
      if (date < nowDate) {
        this.showNotice = '时间已过期！'
        this.noticeModel.publishtime = date
      } else {
        this.noticeModel.publishtime = date
        this.showNotice = ''
      }
    }
  }
}
</script>
<style scoped lang="less">
.filterTitle {
  font-size: 12px;
  color: #1c2438;
  margin-left: 60px;
}
.formItem {
  margin-left: 0px;
}
.menu {
  flex-direction: row;
  align-items: center;
}
.role {
  flex-direction: row;
  align-items: center;
  font-size: 36px;
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
  padding: 30px;
}
.layout-copy {
  text-align: center;
  padding: 10px 0 20px;
  color: #9ea7b4;
}
</style>
