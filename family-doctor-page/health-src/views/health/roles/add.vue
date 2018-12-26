<template>
 <div class="layout-body">
  <div class="layout-body-breadcrumb">
    <Breadcrumb>
      <BreadcrumbItem href="person">
        <Icon type="person"></Icon> 角色管理
      </BreadcrumbItem>
      <BreadcrumbItem href="person_add">
        <Icon type="social-buffer-outline"></Icon>角色新增
      </BreadcrumbItem>
    </Breadcrumb>
  </div>
  <div class="layout-content">
    <div class="layout-content-form">
      <Form :model="formItem" :label-width="80">
       
        <Row type="flex" justify="center">
          <Col span="8">
            <FormItem label="角色名称">
              <Input  v-model="formItem.name" placeholder="请输入"></Input>
            </FormItem>
          </Col>
       </Row>
       <Row type="flex" justify="center">
          <Col span="8" >
            <FormItem label="角色职责">
              <Input  v-model="formItem.role" placeholder="请输入"></Input>
            </FormItem>
          </Col>
        </Row>
        <FormItem >
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
        role: ''
      }
    }
  },
  methods: {
    // 返回
    back () {
      this.$router.push({path: '/roles'})
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