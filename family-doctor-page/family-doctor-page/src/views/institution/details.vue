/**
 * @Title : 医疗机构查看
 * @Description : 
 * @author : TiuWeb
 */
<template>
    <div >
        <div style="height:90%; overflow:auto;">
            <div class="layout-content-main">
                    <div>
                        <Row type="flex" justify="end">
                            <div class='filterTitle'>
                                <i-button type="primary" @click="breakIndex">返回</i-button>
                            </div>
                        </Row>
                        <Form ref="formData" :model="formData" :label-width="130">
                            <Row type="flex" justify="start">
                                <Col span="12">
                                <Form-item label="机构名称" prop="orgname">
                                    <p style="float:left">{{formData.orgname}}</p>
                                </Form-item>
                                <Form-item label="组织机构代码" prop="orgcode">
                                    <p style="float:left">{{formData.orgcode}}</p>
                                </Form-item>
                                <Form-item label="联系电话" prop="telephone">
                                    <p style="float:left">{{formData.telephone}}</p>
                                </Form-item>
                                <Form-item label="机构地址" prop="address">
                                    <p style="float:left">{{formData.address}}</p>
                                </Form-item>
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
                                    <Form-item   label="设立科室" prop="offices">
                                       <div style="float:left" v-for="item in formData.offices" v-model="formData.offices">{{item.officesname}}&nbsp;&nbsp;&nbsp;&nbsp;</div>
                                    </Form-item>
                                </Col>
                                <Col span="12">
                                    <Form-item  label="所含医生职级" prop="levels">
                                            <div style="float:left" v-model="formData.levels" v-for="item in formData.levels">{{item.professionallevelname}}&nbsp;&nbsp;&nbsp;&nbsp;</div>
                                    </Form-item>
                                </Col>
                            </Row>
                            <Row type="flex" justify="start">
                            <p style="float:left; margin-left:70px">服务区域</p>
                            </Row>
                            <Row type="flex" justify="start">
                                <Col span="12">
                                    <Form-item  v-for="(item, index) in formData.areas" :key="index" >
                                       <p style="float:left;">{{item.provincename}}&nbsp;&nbsp;&nbsp;</p>
                                       <p style="float:left;">{{item.cityname}}&nbsp;&nbsp;&nbsp;</p>
                                       <p style="float:left;">{{item.countyname}}&nbsp;&nbsp;&nbsp;</p>
                                       <p style="float:left;">{{item.townshipname}}&nbsp;&nbsp;&nbsp;</p>
                                       <p style="float:left;">{{item.villagename}}&nbsp;&nbsp;&nbsp;</p>
                                    </Form-item>
                                </Col>
                                <Col span="12">
                                <Form-item label="地图">
                                <div style="width:100%;height:250px;" class="tdclass1">
                                    <el-amap vid="amapDemo"></el-amap>
                                </div>
                                </Form-item>
                                </Col>
                            </Row>
                            <Row>
                                <Col span="24">
                                    <Form-item label="机构简介" prop="summary">
                                        <p style="float:left">{{formData.summary}}</p>
                                    </Form-item>
                                </Col>
                            </Row>
                        </Form>
                    </div>
            </div>
        </div>
    </div>
</template>
<script>
import {
findInstitutionContactByKey
} from '../../api/api'
export default {
  data () {
    return {
      defaultList: [
        {
          'name': 'a42bdcc1178e62b4694c830f028db5c0',
          'url': 'https://o5wwk8baw.qnssl.com/a42bdcc1178e62b4694c830f028db5c0/avatar'
        }
      ],
      imgName: '',
      visible: false,
      uploadList: [],
      formData: {
        offices: [],
        levels: [],
        orgname: '',
        orgcode: '',
        telephone: '',
        address: '',
        summary: '',
        areas: []
      }
    }
  },
  created () {
    let para = {
      id: this.$route.params.id
    }
    this.$Loading.start()
    findInstitutionContactByKey(para).then((res) => {
      this.$Loading.finish()
      this.formData = res.data
    })
  },
  methods: {
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
    handledivRemove (index) {
      const check = this.familyModel.members.items.length < 2
      if (check) {
        this.$Notice.warning({
          title: '最后一条不能删除！'
        })
      } else {
        this.familyModel.members.items.splice(index, 1)
      }
      return check
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
.layout {
  border: 1px solid #d7dde4;
  background: #f5f7f9;
  position: relative;
  border-radius: 4px;
  overflow: hidden;
}
.filterTitle {
  font-size: 12px;
  color: #1c2438;
  /*padding: 5px 5px;*/
}
.width200 {
    width: 25%;
}
.uploadDiv {
    width: 400px;
    height: 200px;
    margin-top: 50px;
    margin-right: 5px;
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
