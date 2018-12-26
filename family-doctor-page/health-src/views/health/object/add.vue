<template>
 <div class="layout-body">
  <div class="layout-body-breadcrumb">
    <Breadcrumb>
      <BreadcrumbItem href="object">
        <Icon type="ios-people"></Icon> 保健对象
      </BreadcrumbItem>
      <BreadcrumbItem href="object_edit">
        <Icon type="social-buffer-outline"></Icon>保健对象新增
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
            <FormItem label="身份证">
              <Input  v-model="formItem.input" placeholder="请输入"></Input>
            </FormItem>
          </Col>
       </Row>
       <Row>
          <Col span="8">
            <FormItem label="性别">
              <Select v-model="formItem.select" placeholder="请选择">
                <Option value="男">男</Option>
                <Option value="女">女</Option>
                <Option value="未知">未知</Option>
              </Select>
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
            <FormItem label="机构">
              <Select v-model="formItem.select" placeholder="请选择">
                <Option value="人大">人大</Option>
                <Option value="省委">省委</Option>
                <Option value="省政府">省政府</Option>
                <Option value="财政厅">财政厅</Option>
                <Option value="卫计委">卫计委</Option>
              </Select>
            </FormItem>
          </Col>
          <Col span="8" offset="4">
            <FormItem label="级别">
              <Select v-model="formItem.select" placeholder="请选择">
                <Option value="省级">省级</Option>
                <Option value="副省">副省</Option>
                <Option value="厅级">厅级</Option>
                <Option value="副厅">副厅</Option>
              </Select>
            </FormItem>
          </Col>
        </Row>
        <Row>
          <Col span="12">
            <FormItem label="单位地址">
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
  background:#ffffff;
  border:1px solid #dddee1;
}
.layout-content-form{
  padding:20px;
  padding-bottom :50px;
}
</style>