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
          <FormItem label="机构类型">
            <Select v-model="formItem.type" placeholder="请选择">
              <Option value="保健管理机构">保健管理机构</Option>
              <Option value="基地医院">基地医院</Option>
              <Option value="社区服务中心">社区服务中心</Option>
            </Select>
          </FormItem>
        </Col>
        <Col span="8" offset="4">
          <FormItem label="机构名称">
            <Input  v-model="formItem.name" placeholder="请输入"></Input>
          </FormItem>
        </Col>
      </Row>
        <Row>
          <Col span="8" >
            <FormItem label="机构代码">
              <Input  v-model="formItem.orgcode" placeholder="请输入"></Input>
            </FormItem>
          </Col>
           <Col span="8" offset="4">
            <FormItem label="联系人">
              <Input  v-model="formItem.person" placeholder="请输入"></Input>
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
            <FormItem label="机构地址">
              <Input  v-model="formItem.address" placeholder="请输入"></Input>
            </FormItem>
          </Col>
        </Row>
        <Row>
          <Col span="8">
            <FormItem label="级别">
              <Select v-model="formItem.rank" placeholder="请选择">
                <Option value="三甲">三甲</Option>
                <Option value="三乙">三乙</Option>
                <Option value="三丙">三丙</Option>
              </Select>
            </FormItem>
          </Col>
          <Col span="8" offset="4">
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
        type: '保健管理机构',
        name: '陕西省人民医院',
        orgcode: '200101177634',
        person: '张三',
        phone: '13854786452',
        address: '陕西省西安市****街道***号',
        rank: '三甲',
        desc: '服务周到，技术一流',
        input: '口腔'
      }
    }
  },
  created: function () {
    this.formItem.person = this.$route.params.name
    this.formItem.address = this.$route.params.address
  },
  methods: {
    // 返回
    back () {
      this.$router.push({path: '/organization'})
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