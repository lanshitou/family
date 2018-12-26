<template>
 <div class="layout-body">
  <div class="layout-body-breadcrumb">
    <Breadcrumb>
      <BreadcrumbItem href="organization">
        <Icon type="ios-home-outline"></Icon> 保健机构
      </BreadcrumbItem>
      <BreadcrumbItem href="organization_edit">
        <Icon type="social-buffer-outline"></Icon>保健机构修改
      </BreadcrumbItem>
    </Breadcrumb>
  </div>
  <div class="layout-content">
    <div class="layout-content-form">
      <Form :model="formItem" :label-width="80">
        <Row>
          <Col span="8">
            <FormItem label="机构名称">
              <Input  v-model="formItem.name" placeholder="请输入"></Input>
            </FormItem>
          </Col>
          <Col span="8" offset="4">
            <FormItem label="机构代码">
              <Input  v-model="formItem.orgcode" placeholder="请输入"></Input>
            </FormItem>
          </Col>
       </Row>
       <Row>
          <Col span="8">
            <FormItem label="联系人">
              <Input  v-model="formItem.person" placeholder="请输入"></Input>
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
            <FormItem label="机构地址">
              <Input  v-model="formItem.address" placeholder="请输入"></Input>
            </FormItem>
          </Col>
          <Col span="8" offset="4">
            <FormItem label="级别">
              <Select v-model="formItem.rank" placeholder="请选择">
                <Option value="beijing">陕西省人民医院</Option>
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
              <Input  v-model="formItem.desc" placeholder="请输入"  type="textarea" :autosize="{minRows: 2,maxRows: 5}"></Input>
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
      updateList: [],
      formItem: {
        name: '陕西省人民医院',
        orgcode: '435',
        person: '陕西省人民医院',
        phone: '546464644',
        address: '54544488787',
        rank: 'beijing',
        desc: '857857857857',
        input: '667445454'
      }
    }
  },
  methods: {
    // 返回
    back () {
      this.$router.push({path: '/db'})
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
