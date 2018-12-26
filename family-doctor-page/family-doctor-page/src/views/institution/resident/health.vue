<template>
    <div>
        <div class="layout-breadcrumb">
            <Breadcrumb>
                <Breadcrumb-item href="health">&nbsp;居民健康档案</Breadcrumb-item>
            </Breadcrumb>
        </div>
        <div style="height:90%; overflow:auto;">
        <Tabs>
            <Tab-pane label="封面">
                        <Form ref="formData" class="form-block" :model="formData" :label-width="130">
                            <Row>
                                <Col span="5">
                                <Form-item label="编号" prop="recordNo">
                                    <i-input :value.sync="value" placeholder="" style="width: 200px"></i-input>
                                </Form-item>
                                </Col>
                            </Row>
                            <Form-item>
                                <h1 class="title">居民个人健康档案</h1>
                            </Form-item>
                            <Form-item label="姓名" prop="applyName">
                                <Input v-model="formData.applyName" placeholder="姓名"></Input>
                            </Form-item>
                            <Form-item label="现住址" prop="address">
                                <Input v-model="formData.address" placeholder="住址"></Input>
                            </Form-item>
                            <Form-item label="户籍住址" prop="homeaddress">
                                <Input v-model="formData.homeaddress" placeholder="户籍住址"></Input>
                            </Form-item>
                            <Form-item label="联系电话" prop="telephone">
                                <Input v-model="formData.telephone" placeholder="电话"></Input>
                            </Form-item>
                            <Form-item label="乡镇（街道）名称" prop="town">
                                <Input v-model="formData.town" placeholder="乡镇（街道）名称"></Input>
                            </Form-item>
                            <Form-item label="村（居）委会名称" prop="viliage">
                                <Input v-model="formData.viliage" placeholder="村（居）委会名称"></Input>
                            </Form-item>
                            <Form-item label="建档单位" prop="company">
                                <Input v-model="formData.company" placeholder="建档单位"></Input>
                            </Form-item>
                            <Form-item label="责任医生" prop="doctor">
                                <Input v-model="formData.doctor" placeholder="责任医生"></Input>
                            </Form-item>
                            <Form-item label="建档日期" prop="date">
                                <Row>
                                    <Col span="12" >
                                      <Date-picker type="date" placeholder="选择日期" style="width: 200px"></Date-picker>
                                    </Col>
                                </Row>
                            </Form-item>
                            <Form-item>
                                <Button type="primary" @click="handleform('formData')">提交</Button>
                            </Form-item>
                        </Form>
                     
            </Tab-pane>
            <Tab-pane label="个人基本信息">
                    <div  style="height:90%; overflow:auto;">
                        <Form ref="formInfo" class="form-block" :model="formInfo" :label-width="130">
                            <Row>
                                <Col span="5">
                                <Form-item label="编号" prop="recordNo">
                                    <Input :model="value" placeholder="" style="width: 200px"></Input>
                                </Form-item>
                                </Col>
                            </Row>
                            <Form-item>
                                <h1 class="title">个人基本信息表</h1>
                            </Form-item>
                            <div class="tdclass1">
                                <Row>
                                    <Col span="5">
                                    <Form-item label="姓名" prop="applyName">
                                        <Input v-model="formInfo.applyName"></Input>
                                    </Form-item>
                                    </Col>
                                    <Col span="5">
                                    <Form-item label="性别" prop="sex">
                                        <Select v-model="formInfo.sex" style='width:80px'>
                                        <Option v-for='item in sex' :value='item.value' :key='item'>{{ item.label }}</Option>
                                    </Select>
                                    </Form-item>
                                    </Col>
                                    <Col span="5">
                                    <Form-item label="出生日期" prop="birthDate">
                                        <Row>
                                            <Col>
                                               <Date-picker type="date" placeholder="选择日期" v-model="formInfo.birthDate"></Date-picker>
                                            </Col>
                                        </Row>
                                    </Form-item>
                                    </Col>
                                    <Col span="5">
                                    <Form-item label="身份证号" prop="identCard">
                                        <Input v-model="formInfo.identCard"></Input>
                                    </Form-item>
                                    </Col>
                                </Row>
                                <Row>
                                    <Col span="5">
                                    <Form-item label="工作单位" prop="companyName">
                                        <Input v-model="formInfo.companyName"></Input>
                                    </Form-item>
                                    </Col>
                                    <Col span="5">
                                    <Form-item label="联系人电话" prop="telephone">
                                        <Input v-model="formInfo.telephone"></Input>
                                    </Form-item>
                                    </Col>
                                    <Col span="5">
                                    <Form-item label="本人电话" prop="personaltelephone">
                                        <Input v-model="formInfo.personaltelephone"></Input>
                                    </Form-item>
                                    </Col>
                                    <Col span="5">
                                    <Form-item label="联系人姓名" prop="linkman">
                                        <Input v-model="formInfo.linkman"></Input>
                                    </Form-item>
                                    </Col>
                                </Row>
                            </div>
                            <div class="tdclass2">
                                <Row>
                                    <Col span="5">
                                    <Form-item label="常住类型" prop="lifetype">
                                        <Select v-model="formInfo.lifetype" style='width:80px'>
                                    <Option v-for='item in lifetype' :value='item.value' :key='item'>{{ item.label }}</Option>
                                </Select>
                                    </Form-item>
                                    </Col>
                                    <Col span="5">
                                    <Form-item label="血型" prop="bloodtype">
                                        <Select v-model="formInfo.bloodtype" style='width:80px'>
                                    <Option v-for='item in bloodtype' :value='item.value' :key='item'>{{ item.label }}</Option>
                                </Select>
                                    </Form-item>
                                    </Col>
                                    <Col span="5">
                                    <Form-item label="RH阴性" prop="RH">
                                        <Select v-model="formInfo.RH" style='width:80px'>
                                    <Option v-for='item in RH' :value='item.value' :key='item'>{{ item.label }}</Option>
                                </Select>
                                    </Form-item>
                                    </Col>
                                    <Col span="5">
                                    <Form-item label="婚姻状况" prop="marriage">
                                        <Select v-model="formInfo.marriage" style='width:80px'>
                                    <Option v-for='item in marriage' :value='item.value' :key='item'>{{ item.label }}</Option>
                                </Select>
                                    </Form-item>
                                    </Col>
                                </Row>
                                <Row>
                                    <Col span="5">
                                    <Form-item label="文化程度" prop="degree">
                                        <Select v-model="formInfo.degree" style='width:80px'>
                                    <Option v-for='item in degree' :value='item.value' :key='item'>{{ item.label }}</Option>
                                </Select>
                                    </Form-item>
                                    </Col>
                                    <Col span="5">
                                    <Form-item label="职业" prop="work">
                                        <Select v-model="formInfo.work" style='width:80px'>
                                            <Option v-for='item in work' :value='item.value' :key='item'>{{ item.label }}</Option>
                                        </Select>
                                    </Form-item>
                                    </Col>
                                    <Col span="5">
                                    <Form-item label="民族" prop="nation">
                                        <Select v-model="formInfo.nation" style='width:80px'>
                                          <Option v-for='item in nation' :value='item.value' :key='item'>{{ item.label }}</Option>
                                        </Select>
                                    </Form-item>
                                    </Col>
                                    <Col span="8">
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
                                    <Col span="11">
                                    <Form-item label="药物过敏史" prop="druggery">
                                        <Checkbox-group v-model="druggery">
                                            <Checkbox label="青霉素"></Checkbox>
                                            <Checkbox label="链霉素"></Checkbox>
                                            <Checkbox label="其他"></Checkbox>
                                            <Checkbox label="无"></Checkbox>
                                        </Checkbox-group>
                                        <Input v-model="formInfo.druggery" size="small" placeholder="其他"></Input>
                                    </Form-item>
                                    </Col>
                                    <Col span="10">
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
                            </div>
                            <div class="tdclass3">
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
                            </div>
                            <Row type="flex" justify="center">
                                <Form-item>
                                    <Button type="primary" @click="handleform('formInfo')">提交</Button>
                                </Form-item>
                            </Row>
                        </Form>
                    </div>
            </Tab-pane>
        </Tabs>
        </div>
        <div class="layout-copy">
            2011-2016 &copy; TalkingData
        </div>
    </div>
</template>
<script>
export default {
  data () {
    return {
      formfisrt: {
        recordNo: ''
      },
      sex: [
        {
          value: '0',
          label: '男'
        },
        {
          value: '1',
          label: '女'
        }
      ],
      lifetype: [
        {
          value: '0',
          label: '常住'
        },
        {
          value: '1',
          label: '暂住'
        }
      ],
      bloodtype: [
        {
          value: '0',
          label: 'O 型'
        },
        {
          value: '1',
          label: 'A 型'
        },
        {
          value: '2',
          label: 'AB 型'
        },
        {
          value: '3',
          label: 'B 型'
        }
      ],
      RH: [
        {
          value: '0',
          label: '是'
        },
        {
          value: '1',
          label: '否'
        }
      ],
      marriage: [
        {
          value: '0',
          label: '已婚'
        },
        {
          value: '1',
          label: '未婚否'
        },
        {
          value: '2',
          label: '离异'
        }
      ],
      degree: [
        {
          value: '0',
          label: '学士'
        },
        {
          value: '1',
          label: '硕士'
        },
        {
          value: '2',
          label: '博士'
        },
        {
          value: '3',
          label: '大专'
        }
      ],
      work: [
        {
          value: '0',
          label: '公务员'
        },
        {
          value: '1',
          label: '普通职员'
        },
        {
          value: '2',
          label: '无业'
        }
      ],
      nation: [
        {
          value: '0',
          label: '汉族'
        },
        {
          value: '1',
          label: '少数民族'
        }
      ],
      payment: [''],
      druggery: [''],
      expose: [''],
      formInfo: {
        applyName: '',
        sex: '',
        birthDate: '',
        identCard: '',
        companyName: '',
        telephone: '',
        lifetype: '',
        bloodtype: '',
        RH: '',
        marriage: '',
        degree: '',
        work: '',
        nation: '',
        payment: '',
        druggery: '',
        expose: '',
        history: ''
      },
      value: '',
      formData: {
        applyName: '',
        address: '',
        homeaddress: '',
        telephone: '',
        town: '',
        viliage: '',
        company: '',
        person: '',
        doctor: '',
        date: ''
      },
      person: [
        {
          value: '0',
          label: '张三'
        },
        {
          value: '1',
          label: '李素'
        },
        {
          value: '2',
          label: '王二'
        }
      ],
      doctor: [
        {
          value: '0',
          label: '张三'
        },
        {
          value: '1',
          label: '李素'
        },
        {
          value: '2',
          label: '王二'
        }

      ]

    }
  },
  methods: {
    handleform (name) {}
  }
}
</script>
<style  scoped lang="less" >
.layout-copy {
  text-align: center;
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
