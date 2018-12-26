/**
 * @Title : 投诉建议回复
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
            <Col span="7">
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
               <Input v-model="inquiryModel.reply" type="textarea" placeholder="内容描述" :autosize="{minRows: 3}" size="large"></Input> 
            </Form-item>
            </Col>
          </Row>
          </fieldset>
          <Row type="flex" justify="center">
            <Button style="width:80px;margin-right:20px; margin-top:20px" type="primary" @click="saveform('inquiryModel')">提交</Button>
          </Row>
        </Form>
      </div>
    </div>
  </div>
</template>
<script>
import {
  findSuggestionById, updateSuggestion
} from '../../api/api'
export default {
  data () {
    return {
      inquiryModel: {
        id: '',
        createuser: '',
        createdate: '',
        category: '',
        content: '',
        orgname: '',
        reply: ''
      }
    }
  },
  methods: {
    //   机构确认
    handelDo () {
      this.modal1 = true
    },
    // 返回
    last () {
      this.$router.push({path: '/suggestion'})
    },
    // 保存数据
    saveform (formData) {
      let p = {
        id: this.inquiryModel.id,
        reply: this.inquiryModel.reply,
        orgname: this.inquiryModel.orgname
      }
      updateSuggestion(p).then((res) => {
        if (res.status === 200) {
          this.$Message.success('回复成功！')
          this.$router.push({path: '/suggestion'})
        } else {
          this.$Message.error('回复失败！')
        }
      })
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
    })
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
