/**
 * @Title : 通知公告详情
 * @Description : 
 * @author : TiuWeb
 */
<template>
  <div class="" >
    <div  style="height:90%; overflow:auto;">
        <div class="layout-content-main">
            <div class='role'>
                <Form ref="noticeModel" class="form-block" v-model="noticeModel" :label-width="100">
                  <Row type="flex" justify="end">
                        <Button type="primary" style="margin:3px" @click="doCancel">返回</Button>
                    </Row>
                    <Row type="flex" justify="start">
                        <Col span="7">
                        <Form-item label="主题" prop="title">
                            <p v-model="noticeModel.title">{{noticeModel.title}}</p>
                        </Form-item>
                        </Col>
                    </Row>
                    <Row type="flex" justify="start">
                        <Col span="7">
                        <Form-item label="发布机构" prop="publishorgname">
                            <p v-model="noticeModel.publishorgname">{{noticeModel.publishorgname}}</p>
                        </Form-item>
                        </Col>
                    </Row>
                    <Row type="flex" justify="start">
                        <Col span="7">
                        <Form-item label="发布时间" prop="publishtime">
                            <p v-model="noticeModel.publishtime">{{noticeModel.publishtime}}</p>
                        </Form-item>
                        </Col>
                    </Row>
                    <Row type="flex" justify="start">
                        <Col span="7">
                        <Form-item label="发布范围" prop="publishcategory">
                            <p v-model="noticeModel.publishcategory">{{noticeModel.publishcategory}}</p>
                        </Form-item>
                        </Col>
                    </Row>
                    <Row type="flex" justify="start">
                        <Col span="20">
                        <Form-item label="发布内容" prop="content">
                          <p v-model="noticeModel.content">{{noticeModel.content}}</p>
                        </Form-item>
                        </Col>
                    </Row>
                    <Row type="flex" justify="start">
                        <Col span="7">
                        <Form-item label="文件附件" prop="file" v-for="(item, index) in noticeModel.attachments" :key="index" >
                            <p v-model="item.attachmentname">{{item.attachmentname}}</p>
                        </Form-item>
                        </Col>
                    </Row>
                    
                </Form>
            </div>
        </div>
    </div>
    <div class="layout-copy">
      2011-2016 &copy; TalkingData
    </div>
  </div>
</template>
<script>
import {
  findNoticetById
} from '../../../api/api'
export default {
  data () {
    return {
      noticeModel: {
        title: '',
        publishorgname: '',
        publishtime: '',
        publishcategory: '',
        content: '',
        attachments: []
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
      if (this.noticeModel.publishcategory === 1) {
        this.noticeModel.publishcategory = '所有人可见'
      }
      if (this.noticeModel.publishcategory === 2) {
        this.noticeModel.publishcategory = '所有医护端账号可见'
      }
      if (this.noticeModel.publishcategory === 3) {
        this.noticeModel.publishcategory = '本机构内部可见'
      }
      if (this.noticeModel.publishcategory === 4) {
        this.noticeModel.publishcategory = '所有签约居民和家庭可见'
      }
      var date = new Date(this.noticeModel.publishtime)
      var nowDate = this.formatDate(date)
      this.noticeModel.publishtime = nowDate
    })
  },
  methods: {
    doCancel () {
      this.$router.push({path: '/notice'})
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
