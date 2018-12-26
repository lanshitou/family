<template>
    <div>
        <div style="height:90%; overflow:auto;">
        <div style="text-align: right; margin-top: 15px; ">
            <Button type="primary" @click="handleform">提交</Button>
            <Button type="primary" @click="returnBt">返回</Button>
        </div>
        
        <Tabs>
        <template v-if="current === 0">
            <Tab-pane label="封面">
                <Form ref="formInfo" :rules="servicesValidate" class="form-block" :model="formInfo" :label-width="130">
                    <Form-item>
                        <h1 class="title">居民个人健康档案</h1>
                    </Form-item>
                    <Row>
                        <Col span="12">
                            <Form-item label="姓名" prop="residentname">
                                <Input v-model="formInfo.residentname" placeholder="姓名"></Input>
                            </Form-item>
                        </Col>
                        <Col span="12">
                            <Form-item label="现住址" prop="presentaddress">
                                <Input v-model="formInfo.presentaddress" placeholder="住址"></Input>
                            </Form-item>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="12">
                            <Form-item label="户籍住址" prop="permanentaddress">
                                <Input v-model="formInfo.permanentaddress" placeholder="户籍住址"></Input>
                            </Form-item>
                        </Col>
                        <Col span="12">
                                <Form-item label="联系电话" prop="contactphone">
                                <Input v-model="formInfo.contactphone" placeholder="电话"></Input>
                            </Form-item>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="12">
                            <Form-item label="乡镇（街道）名称" prop="townshipname">
                                <Input v-model="formInfo.townshipname" placeholder="乡镇（街道）名称"></Input>
                            </Form-item>
                        </Col>
                        <Col span="12">
                            <Form-item label="村（居）委会名称" prop="villagename">
                                <Input v-model="formInfo.villagename" placeholder="村（居）委会名称"></Input>
                            </Form-item>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="12">
                            <Form-item label="建档单位" prop="filingunit">
                                <Input v-model="formInfo.filingunit" placeholder="建档单位"></Input>
                            </Form-item>
                        </Col>
                        <Col span="12">
                            <Form-item label="责任医生" prop="responsibledoctor">
                                <Input v-model="formInfo.responsibledoctor" placeholder="责任医生"></Input>
                            </Form-item>
                        </Col>
                    </Row>
                </Form>
                <div style="text-align: center; margin-bottom: 15px;">
                    <Button type="primary" @click="next">下一步</Button>
                </div>
            </Tab-pane>
            </template>
            <template v-else-if="current === 1">
            <Tab-pane label="个人基本信息">
                    <div  style="height:90%; overflow:auto;">
                        <Form ref="formInfo" class="form-block" :model="formInfo" :label-width="130">
                            <Form-item>
                                <h1 class="title">个人基本信息表</h1>
                            </Form-item>
                            <div class="">
                                <fieldset>
                                    <legend  style="font-weight:bold;font-size:15px;padding: 1px;text-align:left">
                                        基本信息
                                    </legend>
                                    <Row>
                                        <Col span="6">
                                        <Form-item label="姓名" prop="residentname">
                                            <Input style='width:150px' disabled v-model="formInfo.residentname"></Input>
                                        </Form-item>
                                        </Col>
                                        <Col span="6">
                                        <Form-item label="性别" prop="sex">
                                            <Select v-model="formInfo.sex" style='width:150px'>
                                            <Option v-for='item,index in sex' :value='item.id'>{{ item.label }}</Option>
                                        </Select>
                                        </Form-item>
                                        </Col>
                                        <Col span="6">
                                        <Form-item label="出生日期" prop="birthdate">
                                            <Row>
                                                <Col>
                                                <Date-picker style='width:150px' type="date" placeholder="选择日期" v-model="formInfo.birthdate"></Date-picker>
                                                </Col>
                                            </Row>
                                        </Form-item>
                                        </Col>
                                        <Col span="6">
                                            <Form-item label="年龄" prop="age">
                                                <Input style='width:100px' v-model="formInfo.age"></Input>
                                            </Form-item>
                                        </Col>
                                    </Row>
                                    <Row>
                                        <Col span="6">
                                            <Form-item label="本人电话" prop="contactphone">
                                                <Input style='width:150px' disabled v-model="formInfo.contactphone"></Input>
                                            </Form-item>
                                        </Col>
                                        <Col span="6">
                                            <Form-item label="身份证号" prop="identitynumber">
                                                <Input style='width:150px' v-model="formInfo.identitynumber"></Input> 
                                            </Form-item>
                                        </Col>
                                        <Col span="6">
                                            <Form-item label="常住类型" prop="residenttype">
                                                <Select v-model="formInfo.residenttype" style='width:150px'>
                                                    <Option v-for='item,index in lifetype' :value='item.id'>{{ item.label }}</Option>
                                                </Select>
                                            </Form-item>
                                        </Col>
                                        <Col span="6">
                                            <Form-item label="婚姻状况" prop="maritalstatus">
                                                <Select v-model="formInfo.maritalstatus" style='width:100px'>
                                                    <Option v-for='item,index in marriage' :value='item.id'>{{ item.label }}</Option>
                                                </Select>
                                            </Form-item>
                                        </Col>
                                    </Row>
                                    <Row>
                                        <Col span="6">
                                            <Form-item label="工作单位" prop="jobaddress">
                                                <Input style='width:150px' v-model="formInfo.jobaddress"></Input>
                                            </Form-item>
                                        </Col>
                                        <Col span="6">
                                            <Form-item label="联系人电话" prop="linkmanphone">
                                                <Input style='width:150px'  v-model="formInfo.linkmanphone"></Input>
                                            </Form-item>
                                        </Col>
                                        <Col span="6">
                                            <Form-item label="联系人姓名" prop="linkman">
                                                <Input style='width:150px' v-model="formInfo.linkman"></Input>
                                            </Form-item>
                                        </Col>
                                        
                                        <Col span="6">
                                            <Form-item label="血型" prop="bloodtype">
                                                <Select v-model="formInfo.bloodtype" style='width:100px'>
                                                    <Option v-for='item,index in bloodtype' :value='item.id'>{{ item.label }}</Option>
                                                </Select>
                                            </Form-item>
                                        </Col>
                                    </Row>
                                    <Row>
                                        <Col span="6">
                                            <Form-item label="RH阴性" prop="residenttype">
                                                <Select v-model="formInfo.residenttype" style='width:150px'>
                                                    <Option v-for='item,index in RH' :value='item.id'>{{ item.label }}</Option>
                                                </Select>
                                            </Form-item>
                                        </Col>
                                        <Col span="6">
                                            <Form-item label="文化程度" prop="education">
                                                <Select v-model="formInfo.education" style='width:150px'>
                                                    <Option v-for='item,index in degree' :value='item.id'>{{ item.label }}</Option>
                                                </Select>
                                            </Form-item>
                                        </Col>
                                        <Col span="6">
                                            <Form-item label="职业" prop="occupation">
                                                <Select v-model="formInfo.occupation" style='width:150px'>
                                                    <Option v-for='item,index in work' :value='item.id'>{{ item.label }}</Option>
                                                </Select>
                                            </Form-item>
                                        </Col>
                                        <Col span="6">
                                            <Form-item label="民族" prop="nation">
                                                <Select v-model="formInfo.nation" style='width:100px'>
                                                <Option v-for='item,index in nation' :value='item.id'>{{ item.label }}</Option>
                                                </Select>
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
                            <i-button type="primary" @click="last">上一步</i-button>
                            </div>
                        </Form>
                    </div>
            </Tab-pane>
            </template>
        </Tabs>
        </div>
        <div class="layout-copy">
            2011-2016 &copy; TalkingData
        </div>

        <Modal v-model="modal2" width="360">
            <p slot="header" >
            </p>
            <div style="text-align:center">
                新增健康档案成功,<Span style="font-size:15px">{{shijian}}</Span>s后返回签约协议界面
            </div>
            <div slot="footer">
                <Button type="primary" size="large"  :loading="modal_loading" @click="queren">确定</Button>
            </div>
        </Modal>
    </div>
</template>
<script>
import {
    addArchives
} from '../../api/api'
export default {
  data () {
    return {
      modal2: false,
      modal_loading: false,
      shijian: 5,
    //   返回不同页面的判断参数
    /**
     * 1 家庭成员新增页面
    */
      backtype: '',
    //   身份证号
      identitynumber: '',
      formfisrt: {
        recordNo: ''
      },
      sex: [
        {
          id: '1',
          label: '男'
        },
        {
          id: '2',
          label: '女'
        }
      ],
      lifetype: [
        {
          id: '0',
          label: '常住'
        },
        {
          id: '1',
          label: '暂住'
        }
      ],
      bloodtype: [
        {
          id: '0',
          label: 'O 型'
        },
        {
          id: '1',
          label: 'A 型'
        },
        {
          id: '2',
          label: 'AB 型'
        },
        {
          id: '3',
          label: 'B 型'
        }
      ],
      RH: [
        {
          id: '0',
          label: '是'
        },
        {
          id: '1',
          label: '否'
        }
      ],
      marriage: [
        {
          id: '0',
          label: '已婚'
        },
        {
          id: '1',
          label: '未婚否'
        },
        {
          id: '2',
          label: '离异'
        }
      ],
      degree: [
        {
          id: '0',
          label: '学士'
        },
        {
          id: '1',
          label: '硕士'
        },
        {
          id: '2',
          label: '博士'
        },
        {
          id: '3',
          label: '大专'
        }
      ],
      work: [
        {
          id: '0',
          label: '公务员'
        },
        {
          id: '1',
          label: '普通职员'
        },
        {
          id: '2',
          label: '无业'
        }
      ],
      nation: [
        {
          id: '0',
          label: '汉族'
        },
        {
          id: '1',
          label: '少数民族'
        }
      ],
      // 校验========================开始
      servicesValidate: {
        residentname: [
        { required: true, max: 500, message: '姓名不能为空', trigger: 'blur' }
        ],
        presentaddress: [
        { required: true, max: 500, message: '现住地址不能为空', trigger: 'blur' }
        ],
        permanentaddress: [
        { required: true, max: 500, message: '户籍地址不能为空', trigger: 'blur' }
        ],
        contactphone: [
        { required: true, max: 500, message: '电话不能为空', trigger: 'blur' }
        ],
        townshipname: [
        { required: true, max: 500, message: '乡镇（街道）名称不能为空', trigger: 'blur' }
        ],
        villagename: [
        { required: true, max: 500, message: '村（居）委会名称不能为空', trigger: 'blur' }
        ],
        filingunit: [
        { required: true, max: 500, message: '建档单位不能为空', trigger: 'blur' }
        ],
        responsibledoctor: [
        { required: true, max: 500, message: '责任医生不能为空', trigger: 'blur' }
        ]
      },
      current: 0,
      payment: [''],
      druggery: ['1'],
      expose: [''],
      formInfo: {
        // 创建人
        createuser: '',
        // 编号
        archivesnumber: '',
        // 姓名
        residentname: '',
        // 性别
        sex: '',
        // 生日
        birthdate: '',
        // 省份证号
        identitynumber: '',
        // 工作单位
        jobaddress: '',
        // 联系人姓名
        linkman: '',
        // 联系人的电话
        linkmanphone: '',
        // 常住类型
        residenttype: '',
        // 血型
        bloodtype: '',
        // RH阴性
        rhnegative: '',
        // 婚姻状况
        maritalstatus: '',
        // 文化程度
        education: '',
        // 职业
        occupation: '',
        // 民族
        nation: '',
        // 医疗费用支付方式
        payment: '',
        // 药物过敏史
        druggery: '',
        // 暴露史
        expose: '',
        // 既往史
        history: '',
        // 现住地址
        presentaddress: '',
        // 户籍地址
        permanentaddress: '',
        // 电话本人
        contactphone: '',
        // 乡镇名称
        townshipname: '',
        // 村/委员会名称
        villagename: '',
        // 建档单位
        filingunit: '',
        // 责任医生
        responsibledoctor: '',
        // 建档日期
        filinhdate: ''
      }
    }
  },
  created: function () {
    this.identitynumber = this.$route.params.identitynumber
    this.backtype = this.$route.params.backtype
  },
  methods: {
    // =================================================================================保存成功后弹框确认按钮
    queren () {
      this.$router.push({path: 'add'})
    },
    // 定时任务
    dingshi () {
      setTimeout(() => {
        this.shijian--
        if (this.shijian !== 0) {
          this.dingshi()
        } else {
          this.modal_loading = false
          this.modal2 = false
          this.backvue()
        //   this.$router.push({path: 'add'})
        }
      }, 1000)
    },
    // =======================================================================================上一步
    last () {
      if (this.current > 0) {
        this.current -= 1
      }
    },
    //  =================================================================================提交
    handleform () {
      var reg = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/
      var r = this.formInfo.identitynumber.match(reg)
      if (r === null) {
        this.$Message.warning('身份证号格式不正确')
      } else {
    //   调用保存接口
        addArchives(this.formInfo).then((res) => {
        //   console.log(res)
          if (res.status === 200) {
            this.shijian = 5
            this.modal2 = true
            this.dingshi()
          }
        })
      }
    },
    // =================================================================================下一步
    next () {
      this.$refs['formInfo'].validate((valid) => {
        if (valid) {
          this.handleStatus()
        } else {
          this.$Message.error('居民签约信息验证失败!')
        }
      })
    },
    // =================================================================================进行条
    handleStatus () {
      if (this.current === 2) {
        this.current = 0
      } else {
        this.current += 1
      }
    },
    // =================================================================================返回
    returnBt () {
      this.backvue()
    },
    backvue () {
        // 家庭成员新增也买呢
      if (this.backtype === '1') {
        this.$router.push({name: 'family/add', params: {identitynumber: this.identitynumber}})
      } else {
        this.$router.push({path: 'add'})
      }
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
.divcss {
margin-top: 15px;
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
