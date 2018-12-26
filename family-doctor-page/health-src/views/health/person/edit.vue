<template>
 <div class="layout-body">
  <div class="layout-body-breadcrumb">
    <Breadcrumb>
      <BreadcrumbItem href="organization">
        <Icon type="android-contacts"></Icon> 保健人员
      </BreadcrumbItem>
      <BreadcrumbItem href="organization_edit">
        <Icon type="social-buffer-outline"></Icon>保健人员修改
      </BreadcrumbItem>
    </Breadcrumb>
  </div>
  <div class="layout-content">
    <div class="layout-content-form">
      <Form :model="formItem" :label-width="80">
        <Row>
          <Col span="8">
            <FormItem label="姓名">
              <Input  v-model="formItem.name" placeholder="请输入"></Input>
            </FormItem>
          </Col>
          <Col span="8" offset="4">
            <FormItem label="身份证号">
              <Input  v-model="formItem.idCard" placeholder="请输入"></Input>
            </FormItem>
          </Col>
       </Row>
       <Row>
          <Col span="8">
            <FormItem label="性别">
            <Select v-model="formItem.sex" placeholder="请选择">
                <Option value="男">男</Option>
                <Option value="女">女</Option>
                <Option value="未知">未知</Option>
              </Select>
            </FormItem>
          </Col>
          <Col span="8" offset="4">
            <FormItem label="电话">
              <Input  v-model="formItem.phone" placeholder="请输入"></Input>
            </FormItem>
          </Col>
        </Row>
        <Row>
          <Col span="8">
            <FormItem label="单位">
              <Input  v-model="formItem.depart" placeholder="请输入"></Input>
            </FormItem>
          </Col>
          <Col span="8"  offset="4">
            <FormItem label="单位地址">
              <Input  v-model="formItem.address" placeholder="请输入"></Input>
            </FormItem>
          </Col>
        </Row>
        <FormItem style="float:left">
            <Button style="background:#5e87b0;color:#fff">提交</Button>
            <Button  style="background:#5e87b0;color:#fff;margin-left: 8px" @click="back">返回</Button>
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
        name: '张三',
        idCard: '61042519880421351',
        sex: '男',
        phone: '029-23997400',
        depart: '人大',
        address: '陕西省西安市'
      }
    }
  },
  created: function () {
    this.formItem.name = this.$route.params.name
  },
  methods: {
    // 返回
    back () {
      this.$router.push({path: '/person'})
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
  background:#ffffff;
  border:1px solid #dddee1;
}
.layout-content-form{
  padding:20px;
  padding-bottom :50px;
}
</style>