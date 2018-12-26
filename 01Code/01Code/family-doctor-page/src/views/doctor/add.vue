/**
 * @title: 签约医生信息新增
 * @description: 新增签约医生信息
 * @author: TiuWeb
*/
<template>
  <div>
    <div class="layout-content">
      <div class="layout-content-main">
        <div class='columnBox menu'>
          <div class="index">
            <Form ref="formData"  :model="formData" :label-width="130" :rules="ruleValidate">
              <Row>
                
                <Col span="12">
                  <Form-item label="账号" prop="paccount">
                    <Input v-model="formData.paccount" placeholder="请输入电话号码作为登录账户" style="width: 200px"></Input>
                  </Form-item>
                </Col>
                <Col span="9" pull="3" >
                  <Form-item label="所属签约医疗机构" prop="institutionname">
                    <Input placeholder="请选择医疗机构" v-model="formData.institutionname" readonly="readonly" style="width: 200px" @click.native="queryInstitution" icon="ios-search"></Input>
                  </Form-item>
                  <!--选择签约医疗机构弹出框-->
                  <Modal v-model="institutionModel" title="签约医疗机构信息" class="update-model" @on-ok="getInstitution">
                    <Table :data="institutionList" @on-selection-change="select" :columns="institutionColumns" stripe border>
                    </Table>
                  </Modal>
                  </Col>
              </Row>
              <Row>
                <Col span="12">
                  <Form-item label="姓名" prop="doctorname">
                    <Input v-model="formData.doctorname" placeholder="请输入姓名" style="width: 80px"></Input>
                  </Form-item>
                </Col>
                <Col span="9" pull="3">
                  <Form-item label="执业医师证编号" prop="doctorscertificate">
                    <Input v-model="formData.doctorscertificate" placeholder="请输入编号" style="width: 200px"></Input>
                  </Form-item>
                </Col>
              </Row>
               <Row>
                <Col span="12">
                  <Form-item label="头像设置" >
                    <div style="width:50%;height:50%;" class="demo-upload-list" v-for="item in uploadList">
                                            <template  v-if="item.status === 'finished'">
                                                <img :src="item.url">
                                                <div class="demo-upload-list-cover">
                                                    <Icon type="ios-eye-outline" @click.native="handleView(item.name)"></Icon>
                                                    <Icon type="ios-trash-outline" @click.native="handleRemove(item)"></Icon>
                                                </div>
                                            </template>
                                            <template v-else>
                                                <Progress v-if="item.showProgress" :percent="item.percentage" hide-info></Progress>
                                            </template>
                                        </div>
                                        <Upload 
                                                ref="upload"
                                                :show-upload-list="false"
                                                :default-file-list="defaultList"
                                                :on-success="handleSuccess"
                                                :format="['jpg','jpeg','png']"
                                                :max-size="2048"
                                                :on-format-error="handleFormatError"
                                                :on-exceeded-size="handleMaxSize"
                                                multiple
                                                type="drag"
                                                action="//jsonplaceholder.typicode.com/posts/"
                                                style="display: inline-block;width:100px;">
                                                <div style="width: 100px;height:30px;line-height: 30px;">
                                                上传头像
                                                </div>
                                            </Upload>
                                            <Modal title="查看图片" v-model="visible">
                                                <img :src="'https://o5wwk8baw.qnssl.com/' + imgName + '/large'" v-if="visible" style="width: 100%">
                                            </Modal>
                  </Form-item>
                </Col>
                <Col span="9" pull="3">
                  <Form-item label="执业医师证扫描件" >
                    <Upload action="//jsonplaceholder.typicode.com/posts/">
                      <Button type="ghost" icon="camera">上传</Button>
                    </Upload>
                  </Form-item>
                </Col>
              </Row>
               <Row>
                <Col span="12">
                  <Form-item label="性别" prop="sex">
                    <Radio-group v-model="formData.sex">
                      <Radio label="1">男</Radio>
                      <Radio label="2">女</Radio>
                    </Radio-group>
                  </Form-item>
                </Col>
                <Col span="9" pull="3">
                  <Form-item label="证件类型" prop="certificateidentity">
                    <Select v-model="formData.certificateidentity" style='width:200px' placeholder='请选择证件类型'  @on-change="changeC" :label-in-value="true">
                      <Option v-for='item in certificate' :value='item.codeinfovalue' :key='item.codeinfovalue'>{{ item.codeinfoname }}</Option>
                    </Select>
                  </Form-item>
                </Col>
              </Row>
               <Row>
                <Col span="12">
                  <Form-item label="年龄" prop="age">
                    <Input v-model="formData.age" placeholder="请输入年龄" style="width: 80px"></Input>
                  </Form-item>
                </Col>
                <Col span="9" pull="3">
                  <Form-item label="证件号码" prop="certificatenumber">
                    <Input v-model="formData.certificatenumber" placeholder="请输入证件号码" style="width: 200px"></Input>
                  </Form-item>
                </Col>
              </Row>
               <Row>
                <Col span="12">
                  <Form-item label="出生日期" prop="birthdate">
                    <Date-picker v-model="formData.birthdate" type="date" placeholder="请选择出生日期" style="width: 200px"></Date-picker>
                  </Form-item>
                </Col>
                <Col span="9" pull="3">
                  <Form-item label="职级类别" prop="professionallevelidentity">
                    <Select v-model="formData.professionallevelidentity" style='width:200px' placeholder='职级类别'  @on-change="changeD" :label-in-value="true">
                      <Option v-for='item in doctorLevel' :value='item.codeinfovalue' :key='item.codeinfovalue'>{{ item.codeinfoname }}</Option>
                    </Select>
                  </Form-item>
                </Col>
              </Row>
               <Row>
                <Col span="12">
                  <Form-item label="邮箱" prop="email">
                    <Input v-model="formData.email" placeholder="请输入邮箱" style="width: 200px"></Input>
                  </Form-item>
                </Col>
                <Col span="9" pull="3">
                  <Form-item label="所在科室" prop="officesname">
                    <Select style="width:200px"placeholder="请选择科室" @on-change="changeO" :label-in-value="true">
                        <Option v-for="item in officesList" :value="item.id" :key="item">{{ item.officesname }}</Option>
                      </Select>
                  </Form-item>
                </Col>
              </Row>
               <Row>
                <Col span="12">
                  <Form-item label="医生简介" prop="doctordesc">
                    <Input type="textarea" v-model="formData.doctordesc"  :rows="4" placeholder="请输入简介" style="width: 250px"></Input>
                  </Form-item>
                </Col>
                <Col span="9" pull="3">
                  <Form-item label="医生所属机构地址" prop="medicalinstitutionaddress">
                    <Input type="textarea" v-model="formData.medicalinstitutionaddress"  :rows="4" placeholder="请输入地址" style="width: 250px"></Input>
                  </Form-item>
                </Col>
              </Row>      
              <Form-item>
                <Button style="width:80px;margin-right:20px" type="primary" @click="saveform('formData')" >提交</Button>
                <Button style="width:80px" type="primary" @click="back">返回</Button>
              </Form-item>
            </Form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import {
  insertDoctor, getInstitutionPage, getOfficesPage, findDoctorLevel, findCertificateType
} from '../../api/api'
export default {
  data () {
    const validateAccount = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        let ru = /^1[34578]\d{9}$/
        if (ru.test(value)) {
          callback()
        } else {
          callback(new Error('手机号码有误，请重填！'))
        }
      }
    }
    return {
      institutionModel: false,
      institutionList: [],
      officesList: [],
      selections: [],
      officesSelections: [],
      uploadList: [],
      imgName: '',
      visible: false,
      defaultList: [
        {
          name: 'a42bdcc1178e62b4694c830f028db5c0',
          url: 'https://o5wwk8baw.qnssl.com/a42bdcc1178e62b4694c830f028db5c0/avatar'
        }
      ],
      doctorLevel: [], // 医生职级列表
      certificate: [], // 证件类型列表
      formData: {
        institutionidentity: '', // 所属医疗机构id
        institutionname: '', // 所属医疗机构名称
        doctorname: '', // 医生姓名
        sex: 1,
        birthdate: '',
        paccount: '', // 账号
        certificatename: '', // 证件类型名称
        certificateidentity: 10000, // 证件类型外键
        certificatenumber: '', // 证件号码
        email: '',
        doctorscertificate: '', // 执业医师证编号
        doctordesc: '',
        medicalinstitutionaddress: '', // 医生所属机构地址
        professionallevelidentity: 10000, // 医生职级类别id
        professionallevelname: '', // 医生职级类别名称
        officesidentity: 1, // 所在科室id
        officesname: '' // 所在科室名称
      },
      institutionColumns: [
        {
          type: 'selection',
          width: 60,
          key: 'id',
          align: 'center'
        },
        {
          title: '机构名称',
          align: 'center',
          key: 'orgname'
        },
        {
          title: '机构简介',
          align: 'center',
          key: 'summary'
        },
        {
          title: '地址',
          key: 'address',
          align: 'center'
        }
      ],
      officesColumns: [
        {
          type: 'selection',
          width: 60,
          key: 'id',
          align: 'center'
        },
        {
          title: '科室名称',
          align: 'center',
          key: 'officesname'
        },
        {
          title: '病情关键字',
          align: 'center',
          key: 'patientskeyword'
        }
      ],
      IDcard: [
        {
          value: '二代身份证',
          label: '二代身份证'
        },
        {
          value: '医生资格证',
          label: '医生资格证'
        }
      ],
      ruleValidate: {
        paccount: [
          { required: true, message: '账号不能为空', trigger: 'blur' },
          { type: 'string', max: 25, message: '账号不能超过50个字符', trigger: 'blur' },
          { validator: validateAccount, trigger: 'blur' }
        ],
        doctorname: [
          { required: true, message: '姓名不能为空', trigger: 'blur' },
          { type: 'string', max: 16, message: '姓名不能超过32个字符', trigger: 'blur' }
        ],
        institutionname: [
          { required: true, message: '医疗机构不能为空' }
        ],
        doctorscertificate: [
          { required: true, message: '执业医师证编号不能为空', trigger: 'blur' },
          { type: 'string', max: 16, message: '执业医师证编号不能超过32个字符', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '邮箱不能为空', trigger: 'blur' },
          { type: 'email', message: '邮箱格式不正确', trigger: 'blur' }
        ],
        medicalinstitutionaddress: [
          { required: true, message: '医疗机构地址不能为空', trigger: 'change' },
          { type: 'string', max: 250, message: '医疗机构地址不能超过500个字符', trigger: 'blur' }
        ],
        sex: [
          { required: true, message: '请选择性别' }
        ],
        certificatenumber: [
          { required: true, message: '证件号码不能为空', trigger: 'blur' },
          { type: 'string', max: 50, message: '证件号码不能超过100个字符', trigger: 'blur' }
        ],
        doctordesc: [
          { required: true, message: '请输入医生简介', trigger: 'blur' },
          { type: 'string', max: 250, message: '医生简介不能超过500个字符', trigger: 'blur' }
        ],
        professionallevelidentity: [
          { required: true, message: '请选择医生职级', trigger: 'blur' }
        ],
        certificateidentity: [
          { required: true, message: '请选择证件类型', trigger: 'blur' }
        ]
      }
    }
  },
  created: function () {
    let para = {
      page: 1,
      rows: 10
    }
    // 获取所有科室信息
    getOfficesPage(para).then((res) => {
      this.officesList = res.data.rows
    })
    // 获取所有医生职级类别
    findDoctorLevel().then((res) => {
      this.doctorLevel = res.data
    })
    // 获取所有证件类型
    findCertificateType().then((res) => {
      this.certificate = res.data
    })
  },
  methods: {
    // 查询医疗机构
    queryInstitution () {
      this.institutionModel = true
      let para = {
        page: 1,
        rows: 10
      }
      getInstitutionPage(para).then((res) => {
        this.total = res.data.total
        this.institutionList = res.data.rows
      })
    },
    // 获取医疗机构
    getInstitution () {
      if (this.selections.length === 0) {
        this.$Message.error('请选择一条记录！')
      } else if (this.selections.length !== 1) {
        this.$Message.error('只能选择一条记录！')
      } else {
        this.formData.institutionidentity = this.selections[0].id // 获取到选择的医疗机构的id
        this.formData.institutionname = this.selections[0].orgname // 获取到选择的医疗机构的名称
      }
    },
    // 所在科室select选择器选中的Option变化时触发
    changeO (change) {
      this.formData.officesidentity = change.value
      this.formData.officesname = change.label
    },
    // 医生职级select选择器选中的Option变化时触发
    changeD (change) {
      this.formData.professionallevelname = change.label
    },
    // 证件类型select选择器选中的Option变化时触发
    changeC (change) {
      this.formData.certificatename = change.label
    },
    // 返回
    back () {
      this.$router.push({ path: '../doctors' })
    },
    // 保存数据
    saveform (formData) {
      this.$refs['formData'].validate((valid) => {
        if (valid) {
          insertDoctor(this.formData).then((res) => {
            if (res.status === 200) {
              this.$Message.success('保存成功')
              this.$router.push({ path: '../doctors' })
            } else {
              this.$Message.error('保存失败')
              this.$refs['formData'].resetFields()
            }
          })
        } else {
          this.$Message.error('医生信息验证失败!')
        }
      })
    },
     // 机构图
    handleView (name) {
      this.imgName = name
      this.visible = true
    },
    handleRemove (file) {
      // 从 upload 实例删除数据
      const fileList = this.$refs.upload.fileList
      this.$refs.upload.fileList.splice(fileList.indexOf(file), 1)
    },
    handleSuccess (res, file) {
      // 因为上传过程为实例，这里模拟添加 url
      file.url = 'https://o5wwk8baw.qnssl.com/7eb99afb9d5f317c912f08b5212fd69a/avatar'
      file.name = '7eb99afb9d5f317c912f08b5212fd69a'
    },
    handleFormatError (file) {
      this.$Notice.warning({
        title: '文件格式不正确',
        desc: '文件 ' + file.name + ' 格式不正确，请上传 jpg 或 png 格式的图片。'
      })
    },
    handleMaxSize (file) {
      this.$Notice.warning({
        title: '超出文件大小限制',
        desc: '文件 ' + file.name + ' 太大，不能超过 2M。'
      })
    },
    select (selection) {
      this.selections = selection
    },
    officesSelect (selection) {
      this.officesSelections = selection
    },
    mounted () {
      this.uploadList = this.$refs.upload.fileList
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

.index {
  margin-top: 5px;
  margin-bottom: 10PX;
}
.demo-upload-list{
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
    box-shadow: 0 1px 1px rgba(0,0,0,.2);
    margin-right: 4px;
}
.demo-upload-list img{
    width: 100%;
    height: 100%;
}
.demo-upload-list-cover{
    display: none;
    position: absolute;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    background: rgba(0,0,0,.6);
}
.demo-upload-list:hover .demo-upload-list-cover{
    display: block;
}
.demo-upload-list-cover i{
    color: #fff;
    font-size: 20px;
    cursor: pointer;
    margin: 0 2px;
}
</style>
