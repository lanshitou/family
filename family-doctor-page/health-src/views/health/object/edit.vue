<template>
<div class="layout-body">
  <div class="layout-body-breadcrumb">
    <Breadcrumb>
      <BreadcrumbItem href="object">
        <Icon type="ios-people"></Icon> 保健对象
      </BreadcrumbItem>
      <BreadcrumbItem href="object_edit">
        <Icon type="social-buffer-outline"></Icon>保健对象修改
      </BreadcrumbItem>
    </Breadcrumb>
  </div>
  <div class="layout-content">
    <div class="layout-content-form">
      <Form :model="formItem" id="form" :label-width="80">
        <fieldset>
         <legend>基本信息</legend>
         <Row class="form-row">
          <Col span="8">
            <FormItem label="姓名" class="form-label">
              <Input v-model="formItem.name" placeholder="请输入" class="form-text"></Input>
            </FormItem>
          </Col>
          <Col span="8" offset="4">
            <FormItem label="级别" class="form-label">
              <Input v-model="formItem.rank"  placeholder="请输入" class="form-text"></Input>
            </FormItem>
          </Col>
        </Row>
        <Row class="form-row">
          <Col span="8">
            <FormItem label="身份证" class="form-label">
              <Input v-model="formItem.orgcode" placeholder="请输入" class="form-text"></Input>
            </FormItem>
          </Col>
          <Col span="8" offset="4">
            <FormItem label="性别" class="form-label">
              <Input v-model="formItem.sex" placeholder="请输入" class="form-text"></Input>
            </FormItem>
          </Col>
        </Row>
        <Row class="form-row">
          <Col span="8">
            <FormItem label="电话" class="form-label">
              <Input v-model="formItem.phone" placeholder="请输入" class="form-text"></Input>
            </FormItem>
          </Col>
          <Col span="8" offset="4">
            <FormItem label="单位地址" class="form-label">
              <Input v-model="formItem.address" placeholder="请输入" class="form-text"></Input>
            </FormItem>
          </Col>
        </Row>
        </fieldset>
          <FormItem style="margin-top:10px">
            <Button style="background:#5e87b0;color:#fff">提交</Button>
            <Button style="background:#5e87b0;color:#fff;margin-left: 8px" @click="back">返回</Button>
        </FormItem>
      </Form>
    </div>
  </div>
</div>
</template>
<script>
export default {
  data () {
    return {
      formItem: {
        name: '陕西省人民医院',
        orgcode: '610110',
        person: '陕西省人民医院',
        phone: '13598745214',
        address: '陕西省西安市',
        rank: '省级',
        sex: '男'
      }
    }
  },
  created: function () {
    this.formItem.name = this.$route.params.name
  },
  methods: {
    // 返回
    back () {
      this.$router.push({path: '/object'})
    }
  }
}
</script>
<style scoped>
.layout-body{
  background:#f8f8f9;
}
.layout-body-breadcrumb{
  text-align:left;
  font-size:36px;
}
.layout-content{
  border-radius:10px;
  width:80%;
  margin:20px;
}
.layout-content-form{
  padding:20px;
  padding-bottom :20px;
}
.form-row {
  height:65px;
  background: rgba(255, 255, 255, .3);
  border-color: rgba(255, 255, 255, .6);
  border-style: solid;
  border-width: 2px;
  -webkit-border-radius: 5px;
  line-height: 30px;
  list-style: none;
  padding: 10px 10px;
  margin-bottom: 2px;
}
.form-label {
  float: left;
  font-size: 13px;
  width: 110px
}
.form-text {
  background: #ffffff;
  border: #eeeeee solid 1px;
  -webkit-border-radius: 3px;
  font: italic 13px Georgia, "Times New Roman", Times, serif;
  outline: none;
  width: 200px;
  margin-right:5px;
}
#form{
  background: #EEEEEE;
  -webkit-border-radius: 5px;
  border-radius: 5px;
  padding: 20px;
  margin:auto;
}
#form legend {
  text-align:left;
  color: #384313;
  font-size: 22px;
  padding-bottom: 10px;
  text-shadow: 0 1px 1px #eeeeee;
}
#form fieldset {
  border: none;
  margin-bottom: 10px;
}
#form fieldset  {
  color: #111111;
  font-size: 13px;
  font-weight: normal;
  padding-bottom: 0;
}
</style>