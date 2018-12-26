<template>
    <div>
        <div style="height:90%; overflow:auto;">
        
        <Tabs>
        <template v-if="current === 0">
            <Tab-pane label="封面">
                <Form ref="formInfo" class="form-block" :model="formInfo" :label-width="130">
                    <Row>
                        <Col span="5">
                        <Form-item label="编号" prop="archivesnumber">
                            {{formInfo.archivesnumber}}
                        </Form-item>
                        </Col>
                    </Row>
                    <Form-item>
                        <h1 class="title">居民个人健康档案</h1>
                    </Form-item>
                    <Row>
                        <Col span="12">
                            <Form-item label="姓名" prop="residentname">
                                {{formInfo.residentname}}
                            </Form-item>
                        </Col>
                        <Col span="12">
                            <Form-item label="现住址" prop="presentaddress">
                                {{formInfo.presentaddress}}
                            </Form-item>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="12">
                            <Form-item label="户籍住址" prop="permanentaddress">
                                {{formInfo.permanentaddress}}
                            </Form-item>
                        </Col>
                        <Col span="12">
                                <Form-item label="联系电话" prop="contactphone">
                                {{formInfo.contactphone}}
                            </Form-item>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="12">
                            <Form-item label="乡镇（街道）名称" prop="townshipname">
                                {{formInfo.townshipname}}
                            </Form-item>
                        </Col>
                        <Col span="12">
                            <Form-item label="村（居）委会名称" prop="villagename">
                                {{formInfo.villagename}}
                            </Form-item>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="12">
                            <Form-item label="建档单位" prop="filingunit">
                                {{formInfo.filingunit}}
                            </Form-item>
                        </Col>
                        <Col span="12">
                            <Form-item label="责任医生" prop="responsibledoctor">
                                {{formInfo.responsibledoctor}}
                            </Form-item>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="12">
                            <Form-item label="建档日期" prop="createdate">
                                {{formInfo.createdate}}
                            </Form-item>
                        </Col>
                    </Row>
                </Form>
                <div style="text-align: center; margin-bottom: 15px;">
                    <Button type="primary" @click="next">下一页</Button>
                    <Button type="primary" @click="returnBt">返回</Button>
                </div>
            </Tab-pane>
            </template>
            <template v-else-if="current === 1">
            <Tab-pane label="个人基本信息">
                    <div  style="height:90%; overflow:auto;">
                        <Form ref="formInfo" class="form-block" :model="formInfo" :label-width="130">
                            <Row>
                                <Col span="5">
                                <Form-item label="编号" prop="recordNo">
                                    {{formInfo.archivesnumber}}
                                </Form-item>
                                </Col>
                            </Row>
                            <Form-item>
                                <h1 class="title">个人基本信息表</h1>
                            </Form-item>
                            <div>
                                <fieldset>
                                    <legend  style="font-weight:bold;font-size:15px;padding: 1px;text-align:left">
                                        基本信息
                                    </legend>
                                    <Row>
                                        <Col span="6">
                                        <Form-item label="姓名" prop="residentname">
                                            {{formInfo.residentname}}
                                        </Form-item>
                                        </Col>
                                        <Col span="6">
                                        <Form-item label="性别" prop="sex">
                                            {{formInfo.sex === 0 ? '未知性别' : formInfo.sex === 1 ? '男' : formInfo.sex === 2 ? '女' : formInfo.sex === 9 ? '未说明性别' : ''}}
                                        </Form-item>
                                        </Col>
                                        <Col span="6">
                                        <Form-item label="出生日期" prop="birthdate">
                                           {{formInfo.birthdate}}
                                        </Form-item>
                                        </Col>
                                        <Col span="6">
                                            <Form-item label="年龄" prop="age">
                                                {{formInfo.age}}
                                            </Form-item>
                                        </Col>
                                    </Row>
                                    <Row>
                                        <Col span="6">
                                            <Form-item label="身份证号" prop="identitynumber">
                                                {{formInfo.identitynumber}}
                                            </Form-item>
                                        </Col>
                                        <Col span="6">
                                            <Form-item label="本人电话" prop="contactphone">
                                                {{formInfo.contactphone}}
                                            </Form-item>
                                        </Col>
                                        <Col span="6">
                                            <Form-item label="常住类型" prop="residenttype">
                                                {{formInfo.residenttype === 0 ? '常住' : formInfo.residenttype === 1 ? '暂住' : '未知类型'}}
                                            </Form-item>
                                        </Col>
                                        <Col span="6">
                                            <Form-item label="婚姻状况" prop="maritalstatus">
                                                {{formInfo.maritalstatus === 0 ? '已婚' : formInfo.maritalstatus === 1 ? '未婚' : formInfo.maritalstatus === 2 ? '离异' : '未知'}}
                                            </Form-item>
                                        </Col>
                                    </Row>
                                    <Row>
                                        <Col span="6">
                                            <Form-item label="工作单位" prop="jobaddress">
                                                {{formInfo.jobaddress}}
                                            </Form-item>
                                        </Col>
                                        <Col span="6">
                                            <Form-item label="联系人电话" prop="linkmanphone">
                                                {{formInfo.linkmanphone}}
                                            </Form-item>
                                        </Col>
                                        <Col span="6">
                                            <Form-item label="联系人姓名" prop="linkman">
                                                {{formInfo.linkman}}
                                            </Form-item>
                                        </Col>
                                        
                                        <Col span="6">
                                            <Form-item label="血型" prop="bloodtype">
                                                {{formInfo.bloodtype === 0 ? 'O型' : formInfo.bloodtype === 1 ? 'A型' : formInfo.bloodtype === 2 ? 'AB型' : formInfo.bloodtype === 3 ? 'B型' : '未知血型'}}
                                            </Form-item>
                                        </Col>
                                    </Row>
                                    <Row>
                                        <Col span="6">
                                            <Form-item label="RH阴性" prop="residenttype">
                                               {{formInfo.rhnegative === 0 ? '否' : formInfo.rhnegative === 1 ? '是' : '未知'}}
                                            </Form-item>
                                        </Col>
                                        <Col span="6">
                                            <Form-item label="文化程度" prop="education">
                                                {{formInfo.education === 0 ? '学士' : formInfo.education === 1 ? '硕士' : formInfo.education === 2 ? '博士' : formInfo.education === 3 ? '研究生' : formInfo.education === 4 ? '本科' : formInfo.education === 5 ? '大专' : formInfo.education === 6 ? '大专以下' : '未知学历'}}
                                            </Form-item>
                                        </Col>
                                        <Col span="6">
                                            <Form-item label="职业" prop="occupation">
                                                {{formInfo.occupation === 0 ? '公务员' : formInfo.occupation === 2 ? '普通文职' : formInfo.occupation === 3 ? '无业' : '未知'}}
                                            </Form-item>
                                        </Col>
                                        <Col span="6">
                                            <Form-item label="民族" prop="nation">
                                                {{formInfo.nation === 0 ? '汉' : formInfo.nation === 1 ? '少数民族' : '未知'}}
                                            </Form-item>
                                        </Col>
                                        
                                    </Row>
                                </fieldset>
                            </div>
                            <div class="tdclass2">
                                <fieldset class="divcss">
                                    <legend  style="font-weight:bold;font-size:15px;padding: 1px;text-align:left">
                                        身体情况
                                    </legend>
                                    <Row>
                                        <Col span="24">
                                            <Form-item label="暴露史">
                                                <Checkbox-group v-model="expose">
                                                <Checkbox label="化学品"></Checkbox>
                                                <Checkbox label="毒物"></Checkbox>
                                                <Checkbox label="射线"></Checkbox>
                                                <Checkbox label="无"></Checkbox>
                                                </Checkbox-group>
                                            </Form-item>
                                        </Col>
                                        
                                    </Row>
                                    <Row>
                                        <Col span="24">
                                        <Form-item label="医疗费用支付方式" prop="payment">
                                            <Checkbox-group v-model="payment">
                                                <Checkbox label="城镇职工基本医疗保险"></Checkbox>
                                                <Checkbox label="新型农村合作医疗"></Checkbox>
                                                <Checkbox label="商业医疗保险"></Checkbox>
                                                <Checkbox label="全工费"></Checkbox>
                                                <Checkbox label="全自费"></Checkbox>
                                                <Checkbox label="其他"></Checkbox>
                                            </Checkbox-group>
                                            <Input v-model="formInfo.payment" size="small" placeholder="其他"></Input>
                                        </Form-item>
                                        </Col>
                                    </Row>
                                    <Row>
                                        <Col span="24">
                                        <Form-item label="药物过敏史" prop="druggery">
                                            <Checkbox-group v-model="druggery">
                                                <Checkbox  label="青霉素"></Checkbox>
                                                <Checkbox  label="链霉素"></Checkbox>
                                                <Checkbox  label="其他"></Checkbox>
                                                <Checkbox  label="无"></Checkbox>
                                            </Checkbox-group>
                                            <Input v-model="formInfo.druggery" size="small" placeholder="其他"></Input>
                                        </Form-item>
                                        </Col>
                                    </Row>
                                    <Row>
                                        <Form-item label="既往史" prop="history">
                                            <Tabs type="card">
                                                <Tab-pane label="疾病">
                                                    <Input type="textarea" placeholder="请输入"></Input>
                                                </Tab-pane>
                                                <Tab-pane label="疾病说明">
                                                    <Input type="textarea" placeholder="请输入"></Input>
                                                </Tab-pane>
                                                <Tab-pane label="确诊时间">
                                                    <Input type="textarea" placeholder="请输入"></Input>
                                                </Tab-pane>
                                            </Tabs>
                                            <Tabs type="card">
                                                <Tab-pane label="手术史">
                                                    <Input type="textarea" placeholder="请输入"></Input>
                                                </Tab-pane>
                                                <Tab-pane label="外伤">
                                                    <Input type="textarea" placeholder="请输入"></Input>
                                                </Tab-pane>
                                                <Tab-pane label="输血">
                                                    <Input type="textarea" placeholder="请输入"></Input>
                                                </Tab-pane>
                                            </Tabs>
                                        </Form-item>
                                    </Row>
                                </fieldset>
                            </div>
                            <div style="text-align: center; margin-top: 15px; ">
                            <i-button type="primary" @click="last">上一页</i-button>
                            <Button type="primary" @click="returnBt">返回</Button>
                            </div>
                        </Form>
                    </div>
            </Tab-pane>
            </template>
        </Tabs>
        </div>
    </div>
</template>
<script>
import {
    getContentByIdentitynumber
} from '../../api/api'
export default {
  data () {
    return {
      current: 0,
      payment: [''],
      druggery: ['1'],
      expose: [''],
      formInfo: {
      },
      formModel: {
      }
    }
  },
  created () {
    // console.log(this.$route.params.id)
    // 根据身份证号查询个人健康档案 getContentByIdentitynumber
    let para = {
      identitynumber: this.$route.params.id
    }
    getContentByIdentitynumber(para).then((res) => {
    //   console.log(res.data)
      this.formInfo = res.data[0]
      if (this.formInfo.birthdate !== null) {
        this.formInfo.birthdate = this.renderDate(this.formInfo.birthdate)
      }
    //   console.log(this.formInfo.healthidentity)
      // findarchivesbykey
      if (this.formInfo.healthidentity !== undefined) {
      }
    })
  },
  methods: {
    // 上一步
    last () {
      if (this.current > 0) {
        this.current -= 1
      }
    },
    // 确认框
    confirm () {
      var that = this
      this.$Modal.confirm({
        title: '操作确认',
        content: '<p>添加成功,是否继续添加?</p>',
        loading: true,
        onOk: () => {
          this.$Message.info('点击了确定')
          this.$Modal.remove()
          that.handleStatus()
        },
        onCancel: () => {
          this.$Message.info('点击了取消')
          that.$router.push({ path: '/add' })
        }
      })
    },
    // 下一步
    next () {
      this.handleStatus()
    },
    // 进行条
    handleStatus () {
      if (this.current === 2) {
        this.current = 0
      } else {
        this.current += 1
      }
      if (this.current === 2) {
        this.confirm()
      }
    },
    returnBt () {
      this.$router.push({path: 'contact'})
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
      var newDate = y + '-' + m + '-' + d
      return newDate
    }
  }
}
</script>
<style  scoped lang="less" >
.layout-copy {
  text-align: center;
  margin-top: 15px;
  color: #9ea7b4;
}
.form-block {
    margin: 0px;
    /*background: #f8f8f9;*/
    margin-top: 10px;
    margin-bottom: 0px;
}

.title {
    text-align: center;
}
.tdclass1 {
    border: 1px solid #C4C9DF;
    vertical-align: middle;
}
.tdclass2 {
    border: 1px solid #C4C9DF;
    vertical-align: middle;
    border-top: none;
}
.tdclass3 {
    border: 1px solid #C4C9DF;
    vertical-align: middle;
    border-top: none;
}
</style>
