<template>
  <div>
    <div class="recordHead">
      <Form :label-width="100" >
        <Row>
          <Col span="6">
            <Form-item label="姓名">
              {{firstmodel.membername}}
            </Form-item>
          </Col>
          <Col span="6">
            <Form-item label="签约单号">
              {{firstmodel.contactnumber}}
            </Form-item>
          </Col>
          <Col span="6">
            <Form-item label="">
            </Form-item>
          </Col>
          <Col span="6">
            <Form-item label="">
              <Button type="primary" @click.native="back">
                返回
              </Button>
            </Form-item>
          </Col>
        </Row>
      </Form>
    </div>
    <div class="recordForm">
      <h1 style="text-align:center">居民{{title}}服务记录表</h1>
        <Form ref="firstmodel" :model="firstmodel"  :label-width="100" label-position="right">
          <div >
              <Row>
                <Col span="12">
                  <Form-item label="随访日期" prop="servicedate">
                    {{firstmodel.servicedate}}
                  </Form-item>
                </Col>
                <Col span="12">
                  <Form-item label="随访方式" prop="isappointment">
                    {{firstmodel.isappointment === 1 ? '预约服务' : firstmodel.isappointment === 0 ? '上门服务' : ''}}
                  </Form-item>
                </Col>
              </Row>

              <Row>
                <Col span="12">
                  <Form-item label="下次随访时间" prop="nextservicedate">
                    {{firstmodel.nextservicedate}}
                  </Form-item>
                </Col>
                <Col span="12">
                  <Form-item label="随访医生" prop="doctoridentity">
                    {{firstmodel.servicetreatmentdoctorname}}
                  </Form-item>
                </Col>
              </Row>
          </div>
        <div class="divList">
          <fieldset>
            <legend>症状</legend>
            <Row >
              <Col span="24">
                  <Form-item prop="zhengzhuang" label="病症">
                    <span v-for="item,index in datamodel.zhengzhuang">
                      {{item}}
                      <i v-if="(datamodel.zhengzhuang.length - 1) !== index">,</i>
                    </span>
                  </Form-item>
              </Col>
            </Row>
            <Row >
              <Col :xs="{ span: 21, offset: 1 }">
                <Form-item label="其他">
                  {{datamodel.otherzhengzhuang}}
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
                  {{datamodel.shousuoya}}
                </Form-item>
                </Col>
                <Col  span="6">
                <Form-item label="舒张压mmHg" prop="shuzhangya">
                  {{datamodel.shuzhangya}}
                </Form-item>
                </Col>
                <Col  span="6">
                <Form-item label="身高cm" prop="shengao">
                  {{datamodel.shengao}}
                </Form-item>
                </Col>
                <Col span="6">
                <Form-item  label="体重kg" prop="tizhong">
                  {{datamodel.tizhong}}
                </Form-item>
                </Col>
              </Row>
              <Row>
                <Col  span="6">
                <Form-item label="体质指数" prop="tizhi">
                  {{datamodel.tizhi}}
                </Form-item>
                </Col>
                <Col  span="6">
                <Form-item label="心率(次/分)" prop="xinlv">
                  {{datamodel.xinlv}}
                </Form-item>
                </Col>
                <Col  span="6">
                  <Form-item label="其他" prop="tizhengother">
                    {{datamodel.tizhengother}}
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
                  {{datamodel.yanrimeizhi}}
                </Form-item>
                </Col>
                <Col span="6">
                <Form-item label="饮酒(两/日)"  prop="yinjiutianmeiliang">
                  {{datamodel.yinjiutianmeiliang}}
                </Form-item>
                </Col>
                <Col span="6">
                <Form-item label="运动(目前次/周)"  prop="yundongzhoumeiciyi">
                  {{datamodel.yundongzhoumeiciyi}}
                </Form-item>
                </Col>
                <Col  span="6">
                <Form-item label="次/分钟"  prop="yundongfenzhongmeici">
                  {{datamodel.yundongfenzhongmeici}}
                </Form-item>
                </Col>
              </Row>
              <Row>
                <Col span="6">
                <Form-item label="运动(目标次/周)"   prop="yundongzhoumeicier">
                  {{datamodel.yundongzhoumeicier}}
                </Form-item>
                </Col>
                <Col span="6">
                <Form-item label="分钟/次"   prop="yundongcimeifenzhong">
                  {{datamodel.yundongcimeifenzhong}}
                </Form-item>
                </Col>
                <Col  span="6">
                <Form-item label="摄盐情况(克/天)"   prop="sheyanqingkuangpinlv">
                  {{datamodel.sheyanqingkuangpinlv}}
                </Form-item>
                </Col>
                <Col  span="6">
                <Form-item label="心理调整" prop="xinlutiaozheng">
                  {{datamodel.xinlutiaozheng}}
                </Form-item>
                </Col>
              </Row>
              <Row>
                <Col  span="6">
                <Form-item label="遵医行为" prop="zunyixingwei">
                  {{datamodel.zunyixingwei}}
                </Form-item>
                </Col>
              <Col  span="6">
                  <Form-item label="摄盐情况(咸淡)" prop="sheyanqingkuang">
                    {{datamodel.sheyanqingkuang}}
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
                  {{datamodel.fuzhujiancha}}
                </Form-item>
                </Col>
                <Col span="8">
                <Form-item label="健康教育" prop="jiankangjiaoyu">
                  {{datamodel.jiankangjiaoyu}}
                </Form-item>
                </Col>
                <Col span="8">
                <Form-item label="服药依从性"  prop="fuyaoyilaixing">
                  {{datamodel.fuyaoyilaixing}}
                </Form-item>
                </Col>
              </Row>
              <Row>
                <Col span="8">
                  <Form-item label="药物不良反应"  prop="yaowubuliangfanying">
                    {{datamodel.yaowubuliangfanying}}
                  </Form-item>
                </Col>
                <Col span="8">
                  <Form-item label="此次随访分类" prop="cicisuifangfenlei">
                    {{datamodel.cicisuifangfenlei}}
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
                <Form-item v-for="(item, index) in formDynamic" :key="index">
                  <Row type="flex">
                    <Col span="5">
                    <Form-item label="药物名称">
                      {{item.yaowuname}}
                    </Form-item>
                    </Col>
                    <Col span="5">
                    <Form-item label="用法">
                      {{item.yongfa}}
                    </Form-item>
                    </Col>
                    <Col span="5">
                    <Form-item label="每天次数">
                      {{item.pinlv}}
                    </Form-item>
                    </Col>
                    <Col span="5">
                    <Form-item style="width:130px" label="每次剂量">
                      {{item.jiliang}}
                    </Form-item>
                    </Col>
                  </Row>
                </Form-item>
            </Form>
          </fieldset>
        </div>
        <div class="divList">
          <fieldset>
            <legend>
              诊断结论
            </legend>
              <Form-item label="" prop="conclusion">
                {{firstmodel.conclusion}}
              </Form-item>
          </fieldset>
        </div>

        <div class="divList">
          <fieldset>
            <legend>
              医嘱
            </legend>
              <Form-item label="" prop="advice">
                {{firstmodel.advice}}
              </Form-item>
          </fieldset>
        </div>
  
        <div>
          <label style="float:left;padding:5px">是否属于家庭医生签约服务</label>
          <div style="padding:4px;float:left">
            <Form-item label="" prop="iscontactservice">
                {{firstmodel.iscontactservice === true ? '是' : firstmodel.iscontactservice === false ? '否' : ''}}
            </Form-item>
          </div>
        </div>
        
      </Form>
    </div>
  </div>
</template>
<script>
import {
  findtreatmentbykey, getTeamsMember
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
        doctoridentity: '',
        conclusion: '',
        advice: '',
        iscontactservice: false
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
      yisheng: [],
      formDynamic: [
      ]
    }
  },
  // recordidentify,
  created: function () {
    this.id = this.$route.params.id
    // alert(this.$route.params.servicerecordidentify)
    let para = {
      id: this.$route.params.servicerecordidentify
    }
    findtreatmentbykey(para).then((res) => {
      if (res.status === 200) {
        this.firstmodel = res.data
        this.firstmodel.servicedate = this.renderDate(this.firstmodel.servicedate)
        this.firstmodel.nextservicedate = this.renderDate(this.firstmodel.nextservicedate)
        this.datamodel = JSON.parse(res.data.treatmentkeywords)
        this.formDynamic = this.datamodel.formDynamic
        // console.log(this.datamodel)
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
    // 返回
    back () {
      this.$router.push({ name: 'evaluate', params: {id: this.id} })
    },
    handleReset (name) {
      this.$refs[name].resetFields()
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

