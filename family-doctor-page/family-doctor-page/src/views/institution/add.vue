/**
 * @Title : 医疗机构添加
 * @Description : 
 * @author : TiuWeb
 */
<template>
    <div class="">
        <div class="layout-content" style="height:50%; overflow:auto;">
            <div class="layout-content-main">
                <div class='columnBox menu'>
                    <div class="index">
                        <Form ref="formData" :model="formData" :label-width="130" :rules="formValidate">
                            <Row type="flex" justify="start">
                                <Col span="8">
                                <Form-item label="机构名称" prop="orgname">
                                    <!-- <Select placeholder='机构名称' @on-change="changeM" :label-in-value="true" :model="formData.orgname">
                                      <Option v-for='(items, index) in institutionMember' :value='index'>{{items.orgname}}</Option>
                                    </Select> -->
                                    <p style="float:left">{{institutionMember.orgname}}</p>
                                </Form-item>
                                <Form-item label="组织机构代码" prop="orgcode">
                                    <!-- <p style="float:left">{{formData.orgcode}}</p> -->
                                     <Input v-model="formData.orgcode" placeholder="组织机构代码"></Input> 
                                </Form-item>
                                <Form-item label="联系电话" prop="telephone">
                                    <!-- <p style="float:left">{{formData.telephone}}</p> -->
                                     <Input v-model="formData.telephone" placeholder="联系电话"></Input> 
                                </Form-item>
                                <Form-item label="机构地址" prop="address">
                                    <!-- <p style="float:left">{{formData.address}}</p> -->
                                     <Input v-model="formData.address" placeholder="机构地址"></Input> 
                                </Form-item>
                                </Col>
                                <Col span="4">
                                </Col>
                                <Col span="8">
                                    <Form-item label="头像设置:" prop="preview_image">
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
                                                上传机构配图
                                                </div>
                                            </Upload>
                                            <Modal title="查看图片" v-model="visible">
                                                <img :src="'https://o5wwk8baw.qnssl.com/' + imgName + '/large'" v-if="visible" style="width: 100%">
                                            </Modal>
                                    </Form-item>
                                </Col>
                            </Row>
                            <Row type="flex" justify="start">
                                <Col span="12">
                                    <Form-item   label="设立科室" class="formItem">
                                      <Button type="primary" @click.native="addOffice" style="float:left;margin:3px;">新增</Button>
                                      <Button type="primary" @click.native="delOffice"style="float:left;margin:3px;">删除</Button>
                                    </Form-item>
                                </Col>
                                <Col span="12">
                                    <Form-item   label="所含医生职级" class="formItem">
                                      <Button type="primary" @click.native="addLevel" style="float:left;margin:3px;">新增</Button>
                                      <Button type="primary" @click.native="delLevel" style="float:left;margin:3px;">删除</Button>
                                    </Form-item>
                                </Col>
                            </Row>
                            <Row type="flex" justify="start">
                                <Col span="12">
                                    <Form-item prop="offices"  class="formItem">
                                      <Table height="186" :data="data"  @on-selection-change="select" :columns="tableColumns" stripe border/>
                                    </Form-item>
                                </Col>
                                <Col span="12">
                                    <Form-item   prop="levels" class="formItem">
                                      <Table height="186" :data="dataLevel"  @on-selection-change="select" :columns="tableColumns1" stripe border/>
                                    </Form-item>
                                </Col>
                            </Row>
                             <!--科室添加-->
                            <Modal
                                v-model="model1"
                                title="科室添加"
                                @on-ok="okOffice"
                                @on-cancel="cancelOffice">
                                <Checkbox-group  v-model="Checkoffices">
                                  <Checkbox v-for="(item,index) in officesItem" :label="index" :key="index">{{item.officesname}}</Checkbox>
                                </Checkbox-group>
                            </Modal>
                            <!--医生级别添加-->
                            <Modal
                                v-model="model2"
                                title="医生级别添加"
                                @on-ok="okLevel"
                                @on-cancel="canceLevel">
                                <Checkbox-group  v-model="CheckLevel">
                                  <Checkbox v-for="(item,index) in levelsItem" :label="index" :key="index">{{item.codeinfoname}}</Checkbox>
                                </Checkbox-group>
                            </Modal>
                            
                            <Row type="flex" justify="start">
                              <Form-item label="服务区域" class="formItem"style="margin-top:15px">
                                    <i-button type="primary" @click="handleArea" style="float:left;margin:3px;">新增</i-button>
                                      <Button type="primary" @click.native="delArea" style="float:left;margin:3px;">删除</Button>
                              </Form-item>
                            </Row>
                            <!--区域添加-->
                            <Modal
                                v-model="model3"
                                title="区域添加"
                                @on-ok="okArea"
                                @on-cancel="canceArea">
                                <Row type="flex" justify="center">
                                  <Col span="6">
                                <Select placeholder='省' @on-change="changeP" :label-in-value="true" v-model="areap" >
                                  <Option v-for="(items, index) in dataAreaP" :value="index">{{items.areaname}}</Option>
                                </Select>
                                </Col>
                                  <Col span="6">
                                <Select placeholder='市' @on-change="changeC" :label-in-value="true" v-model="areac" >
                                  <Option v-for="(items, index) in dataAreaC" :value="index">{{items.areaname}}</Option>
                                </Select>
                                </Col>
                                  <Col span="6">
                                <Select placeholder='区' @on-change="changeV" :label-in-value="true" v-model="areav">
                                  <Option v-for="(items, index) in dataAreaV" :value="index">{{items.areaname}}</Option>
                                </Select>
                                </Col>
                                  <Col span="6">
                                <Select placeholder='镇' @on-change="changeX" :label-in-value="true" v-model="areax">
                                  <Option v-for="(items, index) in dataAreaX" :value="index">{{items.areaname}}</Option>
                                </Select>
                                </Col>
                                </Row>
                            </Modal>
                              <!-- 区域 表格 -->
                            <Row type="flex" justify="start">
                                <Col span="12">
                                  <Form-item   prop="areas" class="formItem" >
                                  <Table height="250" :data="dataArea"  @on-selection-change="select" :columns="tableColumns2" stripe border/>
                                    </Form-item>
                                </Col>
                                <Col span="12">
                                <Form-item label="地图">
                                <div style="width:100%;height:250px;" class="tdclass1">
                                  <el-amap vid="amapDemo">
                                      <el-amap-marker  v-for="mar in marker" :position="mar.position">
                                     </el-amap-marker> 
                                  </el-amap>
                                </div>
                                </Form-item>
                                </Col>
                            </Row>
                            <Row type="flex" justify="start">
                                <Col span="12">
                                <Form-item label="机构简介" prop="summary">
                                    <Input type="textarea" v-model="formData.summary" placeholder="机构简介" :autosize="{minRows: 4}" size="large"></Input>
                                </Form-item>
                                </Col>
                            </Row>
                            <Row type="flex" justify="center">
                                <Form-item>
                                    <Button  type="primary" style="width:80px;margin-right:20px" @click="handleform('formData')">提交</Button>
                                    <Button  type="primary" style="width:80px;" @click="breakIndex">返回</Button>
                                </Form-item>
                            </Row>
                        </Form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import {
insertInstitution, getInstitutionByUser, getOffice, getDoctorLevel, getAreaAll
} from '../../api/api'
export default {
  data () {
    return {
      mar: {
        position: []
      },
      areap: 26,
      areac: 0,
      areav: 7,
      areax: 0,
      areaparentcodep: 610000,
      areaparentcodec: 610100,
      areaparentcodev: 610114,
      marker: [],
      institution: {},
      institutions: [],
      selections: [],
      model3: false,
      model2: false,
      model1: false,
      dataAreaP: [], //  区域省
      dataAreaC: [], //  区域市
      dataAreaV: [], //  区域区
      dataAreaX: [], //  区域镇
      defaultList: [
        {
          name: 'a42bdcc1178e62b4694c830f028db5c0',
          url: 'https://o5wwk8baw.qnssl.com/a42bdcc1178e62b4694c830f028db5c0/avatar'
        }
      ],
      Checkoffices: [],
      officesItem: [],
      CheckLevel: [],
      levelsItem: [],
      institutionMember: {},
      imgName: '',
      visible: false,
      uploadList: [],
      formData: {
        porgidentity: '',
        orgname: '',
        orgcode: '',
        telephone: '',
        address: '',
        summary: '',
        previewimage: '',
        offices: [],
        levels: [],
        shi: [],
        xian: [],
        cun: [],
        areas: []
      },
      insertModel: {
      },
      // 校验
      formValidate: {
        orgname: [
        { required: true, message: '机构名称不能为空', trigger: 'change' }
        ],
        orgcode: [
        { required: true, message: '机构代码不能为空', trigger: 'blur' },
        { type: 'string', max: 32, message: '机构代码不能多于32个字符', trigger: 'blur' }
        ],
        telephone: [
        { required: true, message: '联系电话不能为空', trigger: 'blur' },
        { type: 'string', max: 12, message: '联系电话不能多于12个字符', trigger: 'blur' }
        ],
        address: [
        { required: true, message: '机构地址不能为空', trigger: 'blur' },
        { type: 'string', max: 500, message: '机构地址不能多于500字符', trigger: 'blur' }
        ],
        offices: [
          { required: true, type: 'array', message: '科室不能为空', trigger: 'blur' }
        ],
        levels: [
          { required: true, type: 'array', message: '医生级别不能为空', trigger: 'blur' }
        ]
      },
      // 科室级别
      data: [],
      tableColumns: [
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
        }
      ],
      // 医生级别
      dataLevel: [],
      tableColumns1: [
        {
          type: 'selection',
          width: 60,
          key: 'codeinfovalue',
          align: 'center'
        },
        {
          title: '医生级别',
          align: 'center',
          key: 'codeinfoname'
        }
      ],
      //  区域
      dataArea: [],
      tableColumns2: [
        {
          type: 'selection',
          width: 60,
          key: 'id',
          align: 'center'
        },
        {
          title: '省',
          align: 'center',
          key: 'provincename'
        },
        {
          title: '市',
          align: 'center',
          key: 'cityname'
        },
        {
          title: '县区',
          align: 'center',
          key: 'countyname'
        },
        {
          title: '乡镇',
          align: 'center',
          key: 'townshipname'
        }
      ]
    }
  },
  created: function () {
    // 所有权限系统当前用户的机构
    getInstitutionByUser().then((res) => {
      this.institutionMember = res.data
      this.formData.porgidentity = this.institutionMember.id
      this.formData.orgname = this.institutionMember.orgname
      this.formData.orgcode = this.institutionMember.orgcode
      this.formData.telephone = this.institutionMember.orgtelep
      this.formData.address = this.institutionMember.orgaddr
      // console.log(res.data)
    })
    // 所有科室信息
    getOffice().then((res) => {
      this.officesItem = res.data
    })
    // 字典里 所有医生级别
    getDoctorLevel().then((res) => {
      this.levelsItem = res.data
      // console.log(res.data)
    })
    this.doareap()
    this.doareac()
    this.doareav()
    this.doareax()
  },
  methods: {
    // 区域省----------------------------------------
    doareap () {
      this.dataAreaP = []
      this.dataAreaC = []
      this.dataAreaV = []
      this.dataAreaX = []
      // 字典 所有区域信息 全部省
      let para = {
        page: 1,
        rows: 34,
        searchParams: {
          arealevel: 1
        }
      }
      this.$Loading.start()
      getAreaAll(para).then((res) => {
        this.$Loading.finish()
        this.dataAreaP = res.data.rows
      })
    },
    // 区域市-----------------------------------------
    doareac () {
      this.dataAreaC = []
      this.dataAreaV = []
      this.dataAreaX = []
      // 字典 所有区域信息 全部市
      let par = {
        page: 1,
        rows: 3600,
        searchParams: {
          arealevel: 2,
          areaparentcode: this.areaparentcodep
        }
      }
      // console.log(par)
      this.$Loading.start()
      getAreaAll(par).then((resp) => {
        this.$Loading.finish()
        this.dataAreaC = resp.data.rows
      })
    },
    // 区域 区----------------------------------------
    doareav () {
      this.dataAreaV = []
      this.dataAreaX = []
      // 字典 所有区域信息 全部区
      let pa = {
        page: 1,
        rows: 3600,
        searchParams: {
          arealevel: 3,
          areaparentcode: this.areaparentcodec
        }
      }
      this.$Loading.start()
      getAreaAll(pa).then((respo) => {
        this.$Loading.finish()
        this.dataAreaV = respo.data.rows
      })
    },
    // 区域 镇---------------------------------------
    doareax () {
      this.dataAreaX = []
      // 字典 所有区域信息 全部镇
      let p = {
        page: 1,
        rows: 3600,
        searchParams: {
          arealevel: 4,
          areaparentcode: this.areaparentcodev
        }
      }
      this.$Loading.start()
      getAreaAll(p).then((re) => {
        this.$Loading.finish()
        this.dataAreaX = re.data.rows
      })
    },
     // 省select选择器选中的Option变化时触发****************
    changeP (change) {
      var index = change.value
      this.areaparentcodep = this.dataAreaP[index].areacode
      this.doareac()
      this.areap = index
      // console.log(this.dataAreaC)
    },
     // 市select选择器选中的Option变化时触发*************************
    changeC (change) {
      if (change.value !== '') {
        var index = change.value
        this.areaparentcodec = this.dataAreaC[index].areacode
        this.doareav()
        this.areac = index
      }
    },
     // 区select选择器选中的Option变化时触发********************
    changeV (change) {
      if (change.value !== '') {
        var index = change.value
        this.areaparentcodev = this.dataAreaV[index].areacode
        this.doareax()
        this.areav = index
      }
    },
     // 镇select选择器选中的Option变化时触发***************
    changeX (change) {
      var index = change.value
      this.areax = index
    },
    // 区域确认++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    okArea () {
      //  必须定义局部对象，定义全局的，变量一直变动
      var dataSelect = {
        provincename: '',
        provincecode: '',
        cityname: '',
        citycode: '',
        countycode: '',
        countyname: '',
        townshipname: '',
        townshipcode: '',
        latitude: '',
        longitude: ''
      }
      dataSelect.provincename = this.dataAreaP[this.areap].areaname
      dataSelect.provincecode = this.dataAreaP[this.areap].areacode
      dataSelect.cityname = this.dataAreaC[this.areac].areaname
      dataSelect.citycode = this.dataAreaC[this.areac].areacode
      dataSelect.countyname = this.dataAreaV[this.areav].areaname
      dataSelect.countycode = this.dataAreaV[this.areav].areacode
      dataSelect.townshipname = this.dataAreaX[this.areax].areaname
      dataSelect.townshipcode = this.dataAreaX[this.areax].areacode
      dataSelect.latitude = this.dataAreaX[this.areax].latitude
      dataSelect.longitude = this.dataAreaX[this.areax].longitude
      var boo = true
      for (var i = 0; i < this.dataArea.length; i++) {
        if (this.dataArea[i].townshipname === dataSelect.townshipname) {
          boo = false
        }
      }
      if (boo) {
        this.dataArea.push(dataSelect)
      }
      this.marker = []
      // var latitude = dataSelect.latitude - 0
      // var longitude = dataSelect.longitude - 0
      // var dd = [longitude, latitude]
      // var dd = [109.221232, 34.660501]
      var dd = [108.728122, 34.281461]
      console.log(dd)
      var mm = {
        position: dd
      }
      this.mar = mm
      this.marker.push(mm)
    },
    // 取消
    canceArea () {},
    // 提交model
    submitForm () {
      this.insertModel.orgname = this.formData.orgname
      this.insertModel.telephone = this.formData.telephone
      this.insertModel.address = this.formData.address
      this.insertModel.summary = this.formData.summary
      this.insertModel.porgidentity = this.formData.porgidentity
      this.insertModel.orgcode = this.formData.orgcode
      this.insertModel.previewimage = this.formData.previewimage
      this.insertModel.levels = []
      this.insertModel.offices = []
      this.insertModel.areas = []
      for (var i = 0; i < this.formData.levels.length; i++) {
        var sss = {
          professionallevelidentity: '',
          professionallevelname: ''
        }
        sss.professionallevelidentity = this.formData.levels[i].codeinfovalue
        sss.professionallevelname = this.formData.levels[i].codeinfoname
        this.insertModel.levels.push(sss)
      }
      for (var j = 0; j < this.formData.offices.length; j++) {
        var s = {
          officesidentity: '',
          officesname: ''
        }
        s.officesname = this.formData.offices[j].officesname
        s.officesidentity = this.formData.offices[j].id
        this.insertModel.offices.push(s)
      }
    },
    // 科室删除
    delOffice (index) {
      if (this.selections.length === 0) {
        this.$Message.error('请选择一条记录！')
      } else if (this.selections.length === 1) {
        var thi = this
        thi.$Modal.confirm({
          title: '科室信息',
          content: `是否删除此记录`,
          onOk: function () {
            thi.Checkoffices.splice(index, 1)
            thi.formData.offices.splice(index, 1)
          }
        })
      } else {
        var th = this
        th.$Modal.confirm({
          title: '科室信息',
          content: `是否删除所有记录`,
          onOk: function () {
            th.Checkoffices = []
            th.formData.offices = []
            th.data = []
          }
        })
      }
    },
    // 级别删除
    delLevel (index) {
      if (this.selections.length === 0) {
        this.$Message.error('请选择一条记录！')
      } else if (this.selections.length === 1) {
        var thi = this
        thi.$Modal.confirm({
          title: '医生级别信息',
          content: `是否删除此记录`,
          onOk: function () {
            thi.CheckLevel.splice(index, 1)
            thi.formData.levels.splice(index, 1)
          }
        })
      } else {
        var th = this
        th.$Modal.confirm({
          title: '医生级别信息',
          content: `是否删除所有记录`,
          onOk: function () {
            th.CheckLevel = []
            th.formData.levels = []
            th.dataLevel = []
          }
        })
      }
    },
    // 添加科室
    addOffice () {
      this.model1 = true
    },
    // 科室确定
    okOffice () {
      this.formData.offices = []
      for (var i = 0; i < this.Checkoffices.length; i++) {
        this.formData.offices.push(this.officesItem[this.Checkoffices[i]])
        this.data = this.formData.offices
      }
    },
    // 取消
    cancelOffice () {},
    // 添加级别
    addLevel () {
      this.model2 = true
    },
    // 级别确认
    okLevel () {
      this.formData.levels = []
      for (var i = 0; i < this.CheckLevel.length; i++) {
        this.formData.levels.push(this.levelsItem[this.CheckLevel[i]])
        this.dataLevel = this.formData.levels
      }
    },
    // 取消
    canceLevel () {},
    // 区域新增
    handleArea () {
      this.model3 = true
    },
    // 删除区域
    delArea (index) {
      if (this.selections.length === 0) {
        this.$Message.error('请选择一条记录！')
      } else if (this.selections.length === 1) {
        var thi = this
        thi.$Modal.confirm({
          title: '区域信息',
          content: `是否删除此记录`,
          onOk: function () {
            thi.dataArea.splice(index, 1)
          }
        })
      } else {
        var th = this
        th.$Modal.confirm({
          title: '区域信息',
          content: `是否删除所有记录`,
          onOk: function () {
            th.dataArea = []
          }
        })
      }
    },
    handleBeforeUpload () {},
    // 机构唯一性判断
    onlyIntitution (orgname) {
    },
    // 提交
    handleform () {
      this.submitForm()
      this.$refs['formData'].validate((valid) => {
        if (valid) {
          insertInstitution(this.insertModel).then((res) => {
            if (res.status === 200) {
              this.$Message.success('新增成功！')
              this.$router.push({ path: 'institution' })
            } else {
              this.$Message.error('新增失败！')
            }
          })
        } else {
          this.$Message.error('机构信息验证失败!')
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
    breakIndex () {
      this.$router.push({ path: '../institution' })
    },
    select (selection) {
      this.selections = selection
    }
  },
  mounted () {
    this.uploadList = this.$refs.upload.fileList
  }
}
</script>
<style scoped lang="less">
.tdclass1 {
  border: 1px dashed #C4C9DF;
  vertical-align: middle;
}
.form-block {
    height: 100%;
    width: 100%;
    margin: 10px;
    overflow: hidden;
    background: #f8f8f9;
    margin-top: 10px;
    margin-bottom: 10px;
}
.formItem{
  margin:3px;
  text-align:center;
}
.layout {
  border: 1px solid #d7dde4;
  background: #f5f7f9;
  position: relative;
  border-radius: 4px;
  overflow: hidden;
}
.index {
  margin-top: 5px;
  margin-bottom: 10PX;
  text-align: center;
  height: 100%;
  overflow: auto;
}
.width200 {
  width: 80px;
}
.uploadDiv {
    width: 400px;
    height: 200px;
    margin-top: 50px;
    margin-right: 5px;
}
.layout-content {
  min-height: 88%;
  margin-top: 15px;
  overflow: hidden;
  background: #fff;
  border-radius: 4px;
}
.layout-content-main {
  padding: 10px;
}
.layout-breadcrumb {
  padding: 10px 15px 0;
}
.title {
    text-align: center;
}
.float_right {
    float:right;
}
.float_left {
    float:left;
}
.tdclass {
    border: 1px solid #C4C9DF;
    vertical-align: middle;
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
.layout-copy {
  text-align: center;
  padding: 10px 0 20px;
  color: #9ea7b4;
}
</style>
