<template>
 <div class="layout-body">
  <div class="layout-body-breadcrumb">
    <Breadcrumb>
      <BreadcrumbItem href="person">
        <Icon type="android-contacts"></Icon> 保健人员
      </BreadcrumbItem>
      <BreadcrumbItem href="person_add">
        <Icon type="social-buffer-outline"></Icon>保健人员新增
      </BreadcrumbItem>
    </Breadcrumb>
  </div>
  <div class="layout-content">
    <div class="layout-content-form">
      <Form :model="formItem" :label-width="80">
        <Row>
          <Col span="8">
            <FormItem label="姓名">
              <Input  v-model="formItem.input" placeholder="请输入"></Input>
            </FormItem>
          </Col>
          <Col span="8" offset="4">
            <FormItem label="性别">
              <Input  v-model="formItem.input" placeholder="请输入"></Input>
            </FormItem>
          </Col>
       </Row>
       <Row>
          <Col span="8">
            <FormItem label="联系人">
              <Input  v-model="formItem.input" placeholder="请输入"></Input>
            </FormItem>
          </Col>
          <Col span="8" offset="4">
            <FormItem label="电话">
              <Input  v-model="formItem.input" placeholder="请输入"></Input>
            </FormItem>
          </Col>
        </Row>
        <Row>
          <Col span="8">
            <FormItem label="机构地址">
              <Input  v-model="formItem.input" placeholder="请输入"></Input>
            </FormItem>
          </Col>
          <Col span="8" offset="4">
            <FormItem label="级别">
              <Select v-model="formItem.select" placeholder="请选择">
                <Option value="beijing">北京市</Option>
                <Option value="shanghai">上海市</Option>
                <Option value="shenzhen">深圳市</Option>
              </Select>
            </FormItem>
          </Col>
        </Row>
        <Row>
          <Col span="8">
            <FormItem label="专长信息">
              <Input  v-model="formItem.input" placeholder="请输入"></Input>
            </FormItem>
          </Col>
        </Row>
        <Row>
          <Col span="12">
            <FormItem label="机构简介">
              <Input  v-model="formItem.input" placeholder="请输入"  type="textarea" :autosize="{minRows: 2,maxRows: 5}"></Input>
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
        input: '',
        select: '',
        radio: 'male',
        checkbox: [],
        switch: true,
        date: '',
        time: '',
        slider: [20, 50],
        textarea: ''
      }
    }
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