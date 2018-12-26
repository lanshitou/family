<template>
 <div class="layout-body">
  <div class="layout-body-breadcrumb">
    <Breadcrumb>
      <BreadcrumbItem href="person">
        <Icon type="person"></Icon> 用户管理
      </BreadcrumbItem>
      <BreadcrumbItem href="person_add">
        <Icon type="social-buffer-outline"></Icon>用户新增
      </BreadcrumbItem>
    </Breadcrumb>
  </div>
  <div class="layout-content">
    <div class="layout-content-form">
      <Form :model="formItem" :label-width="80">
       
        <Row>
          <Col span="8">
            <FormItem label="用户名">
              <Input  v-model="formItem.name" placeholder="请输入"></Input>
            </FormItem>
          </Col>
          <Col span="8" offset="4">
            <FormItem label="密码">
              <Input  v-model="formItem.idCard" placeholder="请输入"></Input>
            </FormItem>
          </Col>
       </Row>
       <Row>
          <Col span="8" >
            <FormItem label="电话">
              <Input  v-model="formItem.phone" placeholder="请输入"></Input>
            </FormItem>
          </Col>
          <Col span="8" offset="4">
            <FormItem label="角色">
            <Select v-model="formItem.sex" placeholder="请选择">
              <Option value="2" label="保健服务人员">
                <span>超级管理员</span>
              </Option>
              <Option value="3" label="保健管理人员">
                <span>管理员</span>
              </Option>
              </Select>
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
        name: '',
        idCard: '',
        sex: '2',
        phone: '',
        depart: ''
      }
    }
  },
  methods: {
    // 返回
    back () {
      this.$router.push({path: '/users'})
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