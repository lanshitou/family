<template>
  <div>
    <div class="recordHead">
      <Form :label-width="100" >
        <Row>
          <Col span="6">
            <Form-item label="姓名">
              {{formmodel.membername}}
            </Form-item>
          </Col>
          <Col span="6">
            <Form-item label="签约单号">
              {{formmodel.contactnumber}}
            </Form-item>
          </Col>
          <Col span="6">
            <Form-item label="是否转诊" prop="istransfer">
              <Radio-group v-model="threemodel.istransfer">
                <Radio label="true">是</Radio>
                <Radio label="false">否</Radio>
              </Radio-group>
            </Form-item>
          </Col>
          <Col span="6">
            <Form-item label="">
              <Button @click="queren" type="primary">医生确认</Button>
              <Button type="primary" @click.native="back">
                返回
              </Button>
            </Form-item>
          </Col>
        </Row>
      </Form>
    </div>
    <div class="recordForm">
      <h1 style="text-align:center">录入结论</h1>
        <Form ref="firstmodel" :model="firstmodel" :rules="firstmodelValidate" :label-width="100" label-position="right">
          <div >
              <Row>
                <Col span="12">
                  <Form-item label="随访日期" prop="servicedate">
                    <Date-picker v-model="firstmodel.servicedate"  format="yyyy-MM-dd" type="date" placeholder="请选择随访日期" style="width: 160px"></Date-picker>
                  </Form-item>
                </Col>
                <Col span="12">
                  <Form-item label="随访方式" prop="isappointment">
                    <Select v-model="firstmodel.isappointment"  style='width: 250px' placeholder="请选择">
                      <Option value="0" label="上门服务"></Option>
                      <Option value="1" label="预约服务"> </Option>
                    </Select>
                  </Form-item>
                </Col>
              </Row>

              <Row>
                <Col span="12">
                  <Form-item label="下次随访时间" prop="nextservicedate">
                    <Date-picker v-model="firstmodel.nextservicedate"  format="yyyy-MM-dd" type="date" placeholder="请选择下次随访时间" style="width: 160px"></Date-picker>
                  </Form-item>
                </Col>
                <Col span="12">
                  <Form-item label="随访医生" prop="doctoridentity">
                    <Select v-model="firstmodel.doctoridentity" style='width: 250px' placeholder="请选择">
                      <Option v-for='item in yisheng' :value='item.doctoridentity' >{{item.doctorname}}</Option>
                    </Select>
                  </Form-item>
                </Col>
              </Row>
          </div>
        </Form>
        <Form ref="datamodel" :model="datamodel" :rules="datamodelValidate" :label-width="100" label-position="right">
        <div class="divList">
          <fieldset>
            <legend>症状</legend>
            <Row >
              <Col span="24">
                  <Form-item prop="zhengzhuang">
                    <Checkbox-group v-model="datamodel.zhengzhuang" >
                      <Checkbox label="无症状">无症状</Checkbox>
                      <Checkbox label="恶心呕吐">恶心呕吐</Checkbox>
                      <Checkbox label="呼吸困难">呼吸困难</Checkbox>
                      <Checkbox label="鼻衄出血不止">鼻衄出血不止</Checkbox>
                      <Checkbox label="下肢水肿">下肢水肿</Checkbox>
                      <Checkbox label="头痛头昏">头痛头昏</Checkbox>
                      <Checkbox label="眼花耳鸣">眼花耳鸣</Checkbox>
                      <Checkbox label="心悸胸闷">心悸胸闷</Checkbox>
                      <Checkbox label="四肢发麻">四肢发麻</Checkbox>
                      <Checkbox label="其他">其他</Checkbox>
                    </Checkbox-group>
                  </Form-item>
              </Col>
            </Row>
            <Row >
              <Col :xs="{ span: 21, offset: 1 }">
                <Form-item label="其他">
                  <Row>
                    <Col :xs="{ span: 5}">
                    <Input v-model="datamodel.otherzhengzhuang"></Input>
                    </Col>
                  </Row>
                </Form-item>
              </Col>
            </Row>
          </fieldset>
        </div>
  
        <div class="divList">
          <fieldset>
            <legend>体征</legend>
            <Row>
              <Col :xs="{ span: 20, offset: 1 }">
              <Row class="rowClass">
                <Col span="6">
                <Form-item  label="收缩压mmHg" prop="shousuoya">
                  <Input v-model="datamodel.shousuoya"></Input>
                </Form-item>
                </Col>
                <Col  span="6">
                <Form-item label="舒张压mmHg" prop="shuzhangya">
                  <Input v-model="datamodel.shuzhangya"></Input>
                </Form-item>
                </Col>
                <Col  span="6">
                <Form-item label="身高cm" prop="shengao">
                  <Input v-model="datamodel.shengao"></Input>
                </Form-item>
                </Col>
                <Col span="6">
                <Form-item  label="体重kg" prop="tizhong">
                  <Input v-model="datamodel.tizhong"></Input>
                </Form-item>
                </Col>
              </Row>
              <Row>
                <Col  span="6">
                <Form-item label="体质指数" prop="tizhi">
                  <Input v-model="datamodel.tizhi"></Input>
                </Form-item>
                </Col>
                <Col  span="6">
                <Form-item label="心率(次/分)" prop="xinlv">
                  <Input v-model="datamodel.xinlv"></Input>
                </Form-item>
                </Col>
                <Col  span="6">
                  <Form-item label="其他" prop="tizhengother">
                    <Input v-model="datamodel.tizhengother"></Input>
                  </Form-item>
                </Col>
              </Row>
              </Col>
            </Row>
          </fieldset>
        </div>
  
        <div class="divList">
          <fieldset>
            <legend>生活方式指导</legend>
            <Row>
              <Col :xs="{ span: 21, offset: 1 }">
              <Row>
                <Col  span="6">
                <Form-item label="吸烟(支/日)"  prop="yanrimeizhi">
                  <Input v-model="datamodel.yanrimeizhi"></Input>
                </Form-item>
                </Col>
                <Col span="6">
                <Form-item label="饮酒(两/日)"  prop="yinjiutianmeiliang">
                  <Input v-model="datamodel.yinjiutianmeiliang"></Input>
                </Form-item>
                </Col>
                <Col span="6">
                <Form-item label="运动(目前次/周)"  prop="yundongzhoumeiciyi">
                  <Input v-model="datamodel.yundongzhoumeiciyi"></Input>
                </Form-item>
                </Col>
                <Col  span="6">
                <Form-item label="次/分钟"  prop="yundongfenzhongmeici">
                  <Input v-model="datamodel.yundongfenzhongmeici"></Input>
                </Form-item>
                </Col>
              </Row>
              <Row>
                <Col span="6">
                <Form-item label="运动(目标次/周)"   prop="yundongzhoumeicier">
                  <Input v-model="datamodel.yundongzhoumeicier"></Input>
                </Form-item>
                </Col>
                <Col span="6">
                <Form-item label="分钟/次"   prop="yundongcimeifenzhong">
                  <Input v-model="datamodel.yundongcimeifenzhong"></Input>
                </Form-item>
                </Col>
                <Col  span="6">
                <Form-item label="摄盐情况(克/天)"   prop="sheyanqingkuangpinlv">
                  <Input v-model="datamodel.sheyanqingkuangpinlv"></Input>
                </Form-item>
                </Col>
                <Col  span="6">
                <Form-item label="心理调整" prop="xinlutiaozheng">
                  <Input v-model="datamodel.xinlutiaozheng"></Input>
                </Form-item>
                </Col>
              </Row>
              <Row>
                <Col  span="6">
                <Form-item label="遵医行为" prop="zunyixingwei">
                  <Input v-model="datamodel.zunyixingwei"></Input>
                </Form-item>
                </Col>
              <Col  span="6">
                  <Form-item label="摄盐情况(咸淡)" prop="sheyanqingkuang">
                    <Input v-model="datamodel.sheyanqingkuang"></Input>
                  </Form-item>
                </Col>
              </Row>
              </Col>
            </Row>
          </fieldset>
        </div>
  
        <div class="divList">
          <fieldset>
            <legend>
              其他
            </legend>
            <Row>
              <Col :xs="{ span: 21, offset: 1 }">
              <Row>
                <Col span="8">
                <Form-item label="辅助检查" prop="fuzhujiancha">
                  <Input v-model="datamodel.fuzhujiancha"></Input>
                </Form-item>
                </Col>
                <Col span="8">
                <Form-item label="健康教育" prop="jiankangjiaoyu">
                  <Input v-model="datamodel.jiankangjiaoyu"></Input>
                </Form-item>
                </Col>
                <Col span="8">
                <Form-item label="服药依从性"  prop="fuyaoyilaixing">
                  <Input v-model="datamodel.fuyaoyilaixing"></Input>
                </Form-item>
                </Col>
              </Row>
              <Row>
                <Col span="8">
                  <Form-item label="药物不良反应"  prop="yaowubuliangfanying">
                    <Input v-model="datamodel.yaowubuliangfanying"></Input>
                  </Form-item>
                </Col>
                <Col span="8">
                  <Form-item label="此次随访分类" prop="cicisuifangfenlei">
                    <Input v-model="datamodel.cicisuifangfenlei"></Input>
                  </Form-item>
                </Col>
              </Row>
              </Col>
            </Row>
          </fieldset>
        </div>
  
        <div class="divList">
          <fieldset>
            <legend>用药情况</legend>
            <Form ref="formDynamic" v-model="formDynamic" :label-width="60">
              <Row>
                <Col>
                <Form-item v-for="(item, index) in formDynamic" :key="index">
                  <Row type="flex">
                    <Col span="5">
                    <Form-item label="药物名称">
                      <Row>
                        <Col span="12">
                        <Input v-model="item.yaowuname" style="width:130px" type="text" placeholder="请输入..."></Input>
                        </Col>
                      </Row>
                    </Form-item>
                    </Col>
                    <Col span="5">
                    <Form-item label="用法">
                      <Row>
                        <Col span="12">
                        <Input v-model="item.yongfa" style="width:130px" type="text" placeholder="请输入..."></Input>
                        </Col>
                      </Row>
                    </Form-item>
                    </Col>
                    <Col span="5">
                    <Form-item label="每天次数">
                      <Row>
                        <Col span="12">
                        <Input v-model="item.pinlv" style="width:130px" type="text" placeholder="请输入..."></Input>
                        </Col>
                      </Row>
                    </Form-item>
                    </Col>
                    <Col span="5">
                    <Form-item style="width:130px" label="每次剂量">
                      <Row>
                        <Col span="12">
                        <Input v-model="item.jiliang" style="width:130px" type="text" placeholder="请输入..."></Input>
                        </Col>
                      </Row>
                    </Form-item>
                    </Col>
                    <Col span="4">
                    <Button type="ghost" @click="handleRemove(index)">删除</Button>
                    </Col>
                  </Row>
                </Form-item>
                </Col>
              </Row>
              <Form-item>
                <Row>
                  <Col span="12">
                  <Button type="dashed" long @click="handleAdd" icon="plus-round">新增</Button>
                  </Col>
                </Row>
              </Form-item>
            </Form>
          </fieldset>
        </div>
        </Form>
      <Form ref="threemodel" :model="threemodel" :rules="threemodelValidate" :label-width="100" label-position="right">
        <div class="divList">
          <fieldset>
            <legend>
              诊断结论
            </legend>
              <Form-item label="" prop="conclusion">
                <Input v-model="threemodel.conclusion" type="textarea" :autosize="{minRows: 2,maxRows: 5}" placeholder="请输入..."></Input>
              </Form-item>
          </fieldset>
        </div>

        <div class="divList">
          <fieldset>
            <legend>
              医嘱
            </legend>
              <Form-item label="" prop="advice">
                <Input v-model="threemodel.advice" type="textarea" :autosize="{minRows: 2,maxRows: 5}" placeholder="请输入..."></Input>
              </Form-item>
          </fieldset>
        </div>
<!--   
        <div>
          <label style="float:left;padding:5px">是否属于家庭医生签约服务</label>
          <div style="padding:4px;float:left">
            <Form-item label="" prop="iscontactservice">
              <Radio-group v-model="threemodel.iscontactservice">
                <Radio label="true">是</Radio>
                <Radio label="false">否</Radio>
              </Radio-group>
            </Form-item>
          </div>
        </div> -->
        
      </Form>
    </div>
  </div>
</template>
<script>
import {
  findplanbykey
  , executeinsert
  , getTeamsMember
} from '../../../api/api'
export default {
  props: [
    'searchInfo'
  ],
  data () {
    return {
      // 计划id
      id: '',
      // 团队ID
      medicalteamidentity: '',
      formmodel: {},
      firstmodel: {
        servicedate: '',
        isappointment: '',
        nextservicedate: '',
        doctoridentity: ''
      },
      threemodel: {
        conclusion: '',
        advice: '',
        istransfer: 'false'
      },
      datamodel: {
        zhengzhuang: [],
        otherzhengzhuang: '',
        shousuoya: '',
        shuzhangya: '',
        shengao: '',
        tizhong: '',
        yanrimeizhi: '',
        yinjiutianmeiliang: '',
        yundongzhoumeiciyi: '',
        yundongfenzhongmeici: '',
        yundongzhoumeicier: '',
        yundongcimeifenzhong: '',
        sheyanqingkuangpinlv: '',
        xinlutiaozheng: '',
        zunyixingwei: '',
        sheyanqingkuang: '',
        fuzhujiancha: '',
        jiankangjiaoyu: '',
        fuyaoyilaixing: '',
        yaowubuliangfanying: '',
        cicisuifangfenlei: '',
        tizhengother: '',
        xinlv: '',
        tizhi: ''
      },
      title: '高血压',
      formItem: {
        input: 'rer',
        select: 'rerer',
        radio: 'male',
        checkbox: [],
        switch: true,
        date: '',
        time: '',
        slider: [20, 50],
        textarea: 'erer'
      },
      yisheng: [],
      // 向后台传的model
      insertdata: {
        identitynumber: '',
        residenttelphone: '',
        recordidentity: '',
        servicedate: '',
        nextservicedate: '',
        planidentify: '',
        conclusion: '',
        memberidentity: '',
        membername: '',
        advice: '',
        treatmentkeywords: '',
        sex: '',
        age: '',
        servicetreatmentdoctoridentity: ''
      },
      formDynamic: [
        {
          value: ''
        }
      ],
      // 校验
      threemodelValidate: {
        conclusion: [
          { required: true, message: '诊断结论不能为空', trigger: 'blur' }
        ],
        advice: [
          { required: true, message: '医嘱不能为空', trigger: 'blur' }
        ]
      },
      firstmodelValidate: {
        servicedate: [
          { required: true, type: 'date', message: '随访日期不能为空', trigger: 'change' }
        ],
        isappointment: [
          { required: true, message: '随访方式不能为空', trigger: 'change' }
        ],
        nextservicedate: [
          { required: true, type: 'date', message: '下次随访日期不能为空', trigger: 'change' }
        ],
        doctoridentity: [
          { required: true, message: '随访医生不能为空', trigger: 'change' }
        ]
      },
      datamodelValidate: {
        zhengzhuang: [
          { required: true, type: 'array', message: '至少选择一个症状', trigger: 'change' }
        ],
        otherzhengzhuang: [
          { required: true, message: '其他症状不能为空', trigger: 'blur' }
        ],
        shousuoya: [
          { required: true, message: '收缩压不能为空', trigger: 'blur' }
        ],
        shuzhangya: [
          { required: true, message: '舒正压不能为空', trigger: 'blur' }
        ],
        shengao: [
          { required: true, message: '身高不能为空', trigger: 'blur' }
        ],
        tizhong: [
          { required: true, message: '体重不能为空', trigger: 'blur' }
        ],
        yanrimeizhi: [
          { required: true, message: '吸烟（只/每日）不能为空', trigger: 'blur' }
        ],
        yinjiutianmeiliang: [
          { required: true, message: '饮酒(两/日)不能为空', trigger: 'blur' }
        ],
        yundongzhoumeiciyi: [
          { required: true, message: '运动(目前次/周)不能为空', trigger: 'blur' }
        ],
        yundongfenzhongmeici: [
          { required: true, message: '次/分钟不能为空', trigger: 'blur' }
        ],
        yundongzhoumeicier: [
          { required: true, message: ' 运动(目标次/周)不能为空', trigger: 'blur' }
        ],
        yundongcimeifenzhong: [
          { required: true, message: '分钟/次不能为空', trigger: 'blur' }
        ],
        sheyanqingkuangpinlv: [
          { required: true, message: '摄盐情况(克/天)不能为空', trigger: 'blur' }
        ],
        xinlutiaozheng: [
          { required: true, message: '心理调整不能为空', trigger: 'blur' }
        ],
        zunyixingwei: [
          { required: true, message: '遵医行为不能为空', trigger: 'blur' }
        ],
        sheyanqingkuang: [
          { required: true, message: '摄盐情况(咸淡)不能为空', trigger: 'blur' }
        ],
        fuzhujiancha: [
          { required: true, message: '辅助检查不能为空', trigger: 'blur' }
        ],
        jiankangjiaoyu: [
          { required: true, message: '健康教育不能为空', trigger: 'blur' }
        ],
        fuyaoyilaixing: [
          { required: true, message: '服药依从性不能为空', trigger: 'blur' }
        ],
        yaowubuliangfanying: [
          { required: true, message: '药物不良反应不能为空', trigger: 'blur' }
        ],
        cicisuifangfenlei: [
          { required: true, message: '此次随访分类不能为空', trigger: 'blur' }
        ],
        tizhengother: [
          { required: true, message: '体征其他不能为空', trigger: 'blur' }
        ],
        xinlv: [
          { required: true, message: '心率(次/分)不能为空', trigger: 'blur' }
        ],
        tizhi: [
          { required: true, message: '体质指数不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  // recordidentify,
  created: function () {
    // alert(this.$route.params.id)
    this.id = this.$route.params.id
    let para = {
      id: this.$route.params.id
    }
    findplanbykey(para).then((res) => {
      if (res.status === 200) {
        // console.log(res.data)
        this.formmodel = res.data
      }
    })
    // 根据团队ID查询团队成员信息 getTeamsMember
    if (this.$route.params.medicalteamidentity !== undefined && this.$route.params.medicalteamidentity !== '') {
      this.medicalteamidentity = this.$route.params.medicalteamidentity
      let param = {
        medicalteamidentity: this.$route.params.medicalteamidentity
      }
      getTeamsMember(param).then((res) => {
        if (res.status === 200) {
          // console.log(res.data)
          this.yisheng = res.data
        }
      })
    }
  },
  methods: {
    fuzhi () {
      this.insertdata.identitynumber = this.formmodel.identitynumber
      this.insertdata.residenttelphone = this.formmodel.residenttelphone
      this.insertdata.recordidentity = this.formmodel.recordidentify
      this.insertdata.sex = this.formmodel.sex
      this.insertdata.age = this.formmodel.age
      this.insertdata.memberidentity = this.formmodel.memberidentity
      this.insertdata.membername = this.formmodel.membername
      this.insertdata.servicetreatmentdoctoridentity = this.firstmodel.doctoridentity
      this.insertdata.isappointment = this.firstmodel.isappointment
      this.insertdata.servicedate = this.formatDate(this.firstmodel.servicedate)
      this.insertdata.nextservicedate = this.formatDate(this.firstmodel.nextservicedate)
      this.insertdata.conclusion = this.threemodel.conclusion
      this.insertdata.advice = this.threemodel.advice
      this.insertdata.istransfer = this.threemodel.istransfer === 'false' ? false : this.threemodel.istransfer === 'true' ? true : ''
      this.insertdata.planidentify = this.id
      // 待定
      this.datamodel.formDynamic = this.formDynamic
      this.insertdata.treatmentkeywords = JSON.stringify(this.datamodel)
    },
    // 医生确认firstmodelValidate
    queren () {
      var boo = true
      if (boo) {
        this.$refs['firstmodel'].validate((valid) => {
          if (!valid) {
            boo = false
          }
        })
      }
      if (boo) {
        this.$refs['datamodel'].validate((valid) => {
          if (!valid) {
            boo = false
          }
        })
      }
      if (boo) {
        this.$refs['threemodel'].validate((valid) => {
          if (!valid) {
            boo = false
          }
        })
      }
      if (!boo) {
        this.$Message.warning('记录表信息验证失败!')
      } else {
        this.fuzhi()
        // console.log(this.insertdata)
        executeinsert(this.insertdata).then((res) => {
          if (res.status === 200) {
            this.$Message.success('录入成功')
            this.back()
          }
        })
      }
    },
    // 返回firstmodelValidate
    back () {
      this.$router.push({ path: '../plans' })
    },
    handleReset (name) {
      this.$refs[name].resetFields()
    },
    handleAdd () {
      this.formDynamic.push({
        value: ''
      })
    },
    handleRemove (index) {
      this.formDynamic.splice(index, 1)
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
<style scoped lang="less">
.recordHead{
  /* border: 1px solid #C4C9DF; */
  /* background-color:#F9F9FB; */
  /* margin-left:10px; */
   margin-top:10px; 
  position:relative; 
}
.recordForm{
  height:85%;
  
  overflow:auto;
   border: 1px solid #C4C9DF; 
  /* background-color:#F9F9FB; */
   /* margin:10px  */
}
.divList{
   /* border: 1px solid #C4C9DF;  */
   /* background-color:#F9F9FB;  */
   margin:5px
}
.divLabel{
  border-right:1px solid #C4C9DF;
  height:100%;
}
.divBox{
  border: 0px solid #C4C9DF;
  background-color:#FFFFFF;
}
.divButton{
  padding:5px;
  text-align:center;
}
.itemButton{
  padding-right:10px;
  padding-top:20px;
  margin:auto;
  text-align:right;
}
.layout-copy {
  text-align: center;
  padding: 10px 0 20px;
  color: #9ea7b4;
}
</style>

