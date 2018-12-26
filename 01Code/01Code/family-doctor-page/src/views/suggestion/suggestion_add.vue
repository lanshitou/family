/**
 * @Title : 投诉建议添加
 * @Description : 
 * @author : TiuWeb
 */
<template>
  <div>
    <div class="layout-content">
      <div class="layout-content-main">
        <div class='columnBox menu'>
          <div class="index">
            <Form ref="inquiryModel" :model="inquiryModel" :label-width="130" :rules="inquiryValidate">
              <Row type="flex" justify="center" class="code-row-bg">
                <Col span="12">
                <Form-item label="类别" prop="typeKey">
                    <Select v-model="inquiryModel.typeKey" style="width:200px">
                        <Option v-for="item in typeItem" :value="item.value" :key="item.value">{{ item.label }}</Option>
                    </Select>
                </Form-item>
                </Col>
              </Row>
              <Row type="flex" justify="center" class="code-row-bg">
                <Col span="12">
                <Form-item label="接受机构" prop="institution">
                    <Select v-model="inquiryModel.institution" style="width:200px">
                        <Option v-for="item in institutionItem" :value="item.value" :key="item.value">{{ item.label }}</Option>
                    </Select>
                </Form-item>
                </Col>
              </Row>
              <Row type="flex" justify="center" class="code-row-bg">
                <Col span="12">
                <Form-item label="内容阐述" prop="contactdesc">
                  <Input style="width: 500px" v-model="inquiryModel.contactdesc" type="textarea" :rows="6"></Input>
                </Form-item>
                </Col>
              </Row>
              <Row type="flex" justify="center" class="code-row-bg">
                <Col span="12">
                <Form-item>
                  <Button style="width:80px;margin-right:20px" type="primary" @click="saveform('inquiryModel')">提交</Button>
                  <Button style="width:80px" type="primary" @click="back()">返回</Button>
                </Form-item>
                </Col>
              </Row>
            </Form>
          </div>
        </div>
      </div>
    </div>
  
    <div class="layout-copy">
      2011-2016 &copy; TalkingData
    </div>
  </div>
</template>
<script>
// import {
//   insertOffices
// } from '../../../api/api'
export default {
  data () {
    return {
      typeItem: [
        {
          value: 'type1',
          label: '投诉'
        },
        {
          value: 'type2',
          label: '建议'
        },
        {
          value: 'type3',
          label: '咨询'
        },
        {
          value: 'type4',
          label: '其他'
        }
      ],
      institutionItem: [
        {
          value: 'type1',
          label: '投诉'
        },
        {
          value: 'type2',
          label: '建议'
        },
        {
          value: 'type3',
          label: '咨询'
        },
        {
          value: 'type4',
          label: '其他'
        }
      ],
      inquiryModel: {
        typeKey: '',
        institution: '',
        contactdesc: ''
      },
        // 校验
      inquiryValidate: {
        typeKey: [
        { required: true, max: 500, message: '类别不能为空', trigger: 'blur' }
        ],
        institution: [
        { required: true, max: 500, message: '接收机构不能为空', trigger: 'blur' }
        ],
        contactdesc: [
        { required: true, max: 500, message: '内容阐述不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
        // 保存数据
    saveform (formData) {
    //   this.$refs['inquiryModel'].validate((valid) => {
    //     if (valid) {
    //       insertOffices(this.inquiryModel).then((res) => {
    //         if (res.status === 200) {
    //           this.$Message.success('保存成功')
    //           this.$router.push({ path: 'department' })
    //         } else {
    //           this.$Message.error('保存失败')
    //         }
    //       })
    //     } else {
    //       this.$Message.error('投诉建议信息验证失败!')
    //     }
    //   })
    },
    back () {
      this.$router.push({path: 'suggestion'})
    }
  }
}
</script>
<style scoped lang="less">
.layout-content {
  min-height: 200px;
  margin: 15px;
  overflow: hidden;
  background: #fff;
  border-radius: 4px;
  height: 85%;
  overflow: auto;
}

.layout-content-main {
  padding: 10px;
}

.layout-copy {
  text-align: center;
  padding: 10px 0 20px;
  color: #9ea7b4;
}

.index {
  margin-top: 80px;
  margin-bottom: 10PX;
}

.demo-upload-list {
  display: inline-block;
  width: 60px;
  height: 60px;
  text-align: center;
  line-height: 60px;
  border: 1px solid transparent;
  border-radius: 4px;
  overflow: hidden;
  background: #fff;
  position: relative;
  box-shadow: 0 1px 1px rgba(0, 0, 0, .2);
  margin-right: 4px;
}

.demo-upload-list img {
  width: 100%;
  height: 100%;
}

.demo-upload-list-cover {
  display: none;
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  background: rgba(0, 0, 0, .6);
}

.demo-upload-list:hover .demo-upload-list-cover {
  display: block;
}

.demo-upload-list-cover i {
  color: #fff;
  font-size: 20px;
  cursor: pointer;
  margin: 0 2px;
}
</style>
