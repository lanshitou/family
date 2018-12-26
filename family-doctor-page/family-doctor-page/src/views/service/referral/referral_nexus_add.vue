<template>
    <div class="">
        <div class="layout-content">
            <Form ref="insertModel" :rules="firstmodelValidate" :model="insertModel" :label-width="130" >
                 <Row class="margin_top">
                    <Col span="6">
                       <Form-item label="" prop="">
                        </Form-item >
                    </Col>
                    <!-- <Col  v-if="genghuanziduan === 0"   span="12">
                        <Form-item label="当前医疗机构为" prop="currentorgidentityindex">
                         <Input :model="insertModel.currentorgidentityindex"  style="width: 250px"></Input> 
                         <i-button  @click="genghuan"  type="primary">更换</i-button>
                         </Form-item > 
                    </Col> -->
                    <Col span="12">
                       <Form-item label="当前医疗机构" prop="currentorgidentity">
                          <Select :disabled="insertModel.id !== '' && insertModel.id !== undefined ? true : false" v-model= "insertModel.currentorgidentity" style='width:300px' placeholder='请选择医生机构'>
                              <Option v-for='item in deptlist' :value='item.id'>{{ item.orgname }}</Option>
                          </Select>
                          <!-- <i-button  @click="quxiao"  type="primary">取消</i-button> -->
                        </Form-item >
                    </Col>
                </Row> 
                 <Row class="margin_top">
                    <Col span="6">
                       <Form-item label="" prop="">
                        </Form-item >
                    </Col>
                    <Col  span="12">
                        <Form-item label="县级对接医院" prop="countyorgidentity">
                            <Select  v-model="insertModel.countyorgidentity" style='width:300px' placeholder='请选择医生机构'>
                                <Option v-for='item in deptlist' :value='item.id'>{{ item.orgname }}</Option>
                            </Select>
                        </Form-item>

                    </Col>
                </Row>
                 <Row class="margin_top">
                    <Col span="6">
                       <Form-item label="" prop="">
                        </Form-item >
                    </Col>
                    <Col  span="12">
                       <Form-item label="市级对接医院" prop="cityorgidentity">
                         <Select  v-model="insertModel.cityorgidentity" style='width:300px' placeholder='请选择医生机构'>
                            <Option v-for='item in deptlist' :value='item.id' >{{ item.orgname }}</Option>
                        </Select>
                        </Form-item >
                    </Col>
                </Row>  
                <div class = "center">
                      <i-button type="primary"  @click='save' class="margin_top12">确定</i-button>
                      <i-button type="primary"  @click='breakout' class="margin_top12">返回</i-button>
                  </div>
            </Form>
        </div>
    </div>
</template>
<script>
import {
  getInstitutionByUser
  , insertcontrast
  , findcontrastbykey
  , updatecontrast
} from '../../../api/api'
export default {
  data () {
    return {
      genghuanziduan: 0,
      deptlist: [],
      insertModel: {
        currentorgidentity: '',
        currentorgname: '',
        countyorgidentity: '',
        countyorgname: '',
        cityorgidentity: '',
        cityorgname: ''
      },
      firstmodelValidate: {
        countyorgidentity: [
          { required: true, message: '县医院不能为空', trigger: 'change' }
        ],
        currentorgidentity: [
          { required: true, message: '当前机构不能为空', trigger: 'change' }
        ],
        cityorgidentity: [
          { required: true, message: '市医院不能为空', trigger: 'change' }
        ]
      }
    }
  },
  created: function () {
    if (this.$route.params.id !== undefined) {
      let para = {
        id: this.$route.params.id
      }
      findcontrastbykey(para).then((res) => {
        if (res.status === 200) {
          this.insertModel = res.data
        }
      })
    }
    getInstitutionByUser().then((res) => {
      if (res.status === 200) {
        // console.log(res.data)
        this.deptlist = res.data
      }
    })
  },
  methods: {
    filldata () {
      if (this.deptlist !== [] && this.deptlist.length > 0) {
        for (var i = 0; i < this.deptlist.length; i++) {
          var item = this.deptlist[i]
          if (this.insertModel.currentorgidentity !== '' && this.insertModel.currentorgidentity === item.id) {
            this.insertModel.currentorgidentity = item.id
            this.insertModel.currentorgname = item.orgname
          }
          if (this.insertModel.countyorgidentity !== '' && this.insertModel.countyorgidentity === item.id) {
            this.insertModel.countyorgidentity = item.id
            this.insertModel.countyorgname = item.orgname
          }
          if (this.insertModel.cityorgidentity !== '' && this.insertModel.cityorgidentity === item.id) {
            this.insertModel.cityorgidentity = item.id
            this.insertModel.cityorgname = item.orgname
          }
        }
      }
    },
    save () {
      var boo = true
      if (boo) {
        this.$refs['insertModel'].validate((valid) => {
          if (!valid) {
            boo = false
            this.$Message.error('记录表信息验证失败!')
          }
        })
      }
      if (boo) {
        this.filldata()
        // 有ID  说明是修改  否则为新增
        if (this.insertModel.id !== '' && this.insertModel.id !== undefined) {
          updatecontrast(this.insertModel).then((res) => {
            if (res.status === 200) {
              this.$Message.success('修改成功')
              this.breakout()
            }
          })
        } else {
          // console.log(this.insertModel)
          insertcontrast(this.insertModel).then((res) => {
            if (res.status === 200) {
              this.$Message.success('保存成功')
              this.breakout()
            }
          })
        }
      }
    },
    breakout () {
      this.$router.go(-1)
      // this.$router.push({ path: 'referral_nexus' })
    },
    genghuan () {
      this.genghuanziduan = 1
    },
    quxiao () {
      this.genghuanziduan = 0
    }
  }
}
</script>
<style scoped lang="less">
.margin_top12 {
    margin-top:15px;
}
.margin_left800 {
    margin-left:800px;
}
.margin_left1000 {
    margin-left:1000px;
}
.marginTop100 {
    margin-top:100px;
}
.margin_left30 {
    margin-left:30px;
}
.center {
 text-align:center;
}
.margin_top {
  margin-top:50px;
}
.font_size {
    font-size: 15px;
}
.blue {
    color: blue;
}
.layout-content {
  min-height: 90%;
  margin: 15px;
  overflow: hidden;
  background: #fff;
  border-radius: 4px;
}
.layout-breadcrumb {
  padding: 10px 15px 0;
}
.layout-content-main {
  padding: 10px;
}
.float_right {
 float:right;
}
</style>
