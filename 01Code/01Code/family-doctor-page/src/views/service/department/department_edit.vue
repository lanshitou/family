/**
 * @Title : 科室修改
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
                <Form-item label="科室名称" prop="officesname">
                  <Input placeholder="请输入科室名称"  style="width: 350px" v-model="inquiryModel.officesname"  @on-blur=onblur()></Input>
                </Form-item>
                </Col>
              </Row>
              <Row type="flex" justify="center" class="code-row-bg">
                <Col span="12">
                <Form-item label="关键病情关键字" prop="patientskeyword">
                  <Input placeholder="请输入病情关键字"  style="width: 350px" v-model="inquiryModel.patientskeyword"></Input>
                </Form-item>
                </Col>
              </Row>
              <Row type="flex" justify="center" class="code-row-bg">
                <Col span="12">
                <Form-item label="性别条件" prop="sex">
                  <Radio-group v-model="inquiryModel.sex">
                        <Radio  label="1">男</Radio>
                        <Radio  label="2">女</Radio>
                        <Radio  label="10">不限</Radio>
                    </Radio-group>
                </Form-item>
                </Col>
              </Row>
              <Row type="flex" justify="center" class="code-row-bg">
                <Col span="12">
                <Form-item label="年龄条件" prop="ageconditionexpression">
                  <Input style="width: 350px" v-model="inquiryModel.ageconditionexpression"></Input>
                </Form-item>
                </Col>
              </Row>
              <Row type="flex" justify="center" class="code-row-bg">
                <Col span="12">
                <Form-item label="备注">
                  <Input style="width: 350px" v-model="inquiryModel.medicalofficesdesc" type="textarea" :rows="4"></Input>
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
import {
updateOffices, getContactOfficesById, getOffice
} from '../../../api/api'
export default {
  data () {
    return {
      inquiryModel: {
        officesname: '',
        patientskeyword: '',
        sex: '',
        ageconditionexpression: '',
        medicalofficesdesc: ''
      },
        // 校验
      inquiryValidate: {
        officesname: [
        { required: true, message: '科室名称不能为空', trigger: 'blur' },
        { type: 'string', max: 50, message: '科室名称不能多于25字', trigger: 'blur' }
        ],
        patientskeyword: [
        { required: true, message: '关键字不能为空', trigger: 'blur' }
        ],
        ageconditionexpression: [
        { required: true, message: '年龄条件不能为空', trigger: 'blur' },
        { type: 'string', max: 50, message: '年龄条件不能多于25字', trigger: 'blur' }
        ]
      }
    }
  },
  created () {
    let para = {
      id: this.$route.params.id
    }
    this.$Loading.start()
    getContactOfficesById(para).then((res) => {
      this.$Loading.finish()
      this.inquiryModel = res.data
    })
  },
  methods: {
    // 科室名称唯一性验证
    onblur () {
      getOffice().then((res) => {
        for (var i = 0; i < res.data.length; i++) {
          if (this.inquiryModel.officesname === res.data[i].officesname) {
            this.inquiryModel.officesname = ''
            this.$Message.error('科室名称已经存在！')
          }
        }
      })
    },
        // 保存数据
    saveform (formData) {
      this.$refs['inquiryModel'].validate((valid) => {
        if (valid) {
          updateOffices(this.inquiryModel).then((res) => {
            if (res.status === 200) {
              this.$Message.success('修改成功！')
              this.$router.push({ path: 'department' })
            } else {
              this.$Message.error('修改失败！')
            }
          })
        } else {
          this.$Message.error('科室信息验证失败!')
        }
      })
    },
    back () {
      this.$router.push({path: 'department'})
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
