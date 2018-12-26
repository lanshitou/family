<template>
<div class="layout-body">
  <div class="layout-body-breadcrumb">
    <Breadcrumb>
      <BreadcrumbItem href="organization">
        <Icon type="ios-home-outline"></Icon> 短信点播
      </BreadcrumbItem>
      <BreadcrumbItem href="organization_add">
        <Icon type="social-buffer-outline"></Icon>短信点播新增
      </BreadcrumbItem>
    </Breadcrumb>
  </div>
  <div class="layout-content">
    <div class="layout-content-form">
      <Form :model="formItem" :label-width="150">
        <Row type="flex" justify="center">
          <Col span="12">
            <FormItem label="类型">
              <Input  v-model="formItem.input" placeholder="请输入"></Input>
            </FormItem>
          </Col>
      </Row>
      <Row type="flex" justify="center">
          <Col span="12">
            <FormItem label="主题">
              <Input  v-model="formItem.input" placeholder="请输入"></Input>
            </FormItem>
          </Col>
        </Row>
        <Row type="flex" justify="center">
          <Col span="12">
            <FormItem label="短信内容">
              <Input  v-model="formItem.input" placeholder="请输入"></Input>
            </FormItem>
          </Col>
        </Row>
        <Row type="flex" justify="center">
            <Col span="12">
              <FormItem label="发送范围">
                  <RadioGroup v-model="formItem.area"style="float:left">
                      <Radio label="群体"></Radio>
                      <Radio label="个人"></Radio>
                  </RadioGroup>
              </FormItem>
            </Col>
          </Row>
          <Row type="flex" justify="center">
            <Col span="12">
              <FormItem label="选择对象">
                  <Select  style="padding-top:5px;width:100px;float:left" placeholder="全部">
                      <Option value="" label="全部">
                        <span>全部</span>
                        <span style="float:right;color:#ccc"></span>
                      </Option>
                      <Option value="1" label="高血压">
                        <span>高血压</span>
                      </Option>
                      <Option value="2" label="高血脂">
                        <span>高血脂</span>
                      </Option>
                    </Select>
              </FormItem>
            </Col>
          </Row>
          <Row type="flex" justify="center">
            <Col span="12">
              <FormItem label="预计发送时间">
                <Input  v-model="formItem.input" placeholder="请输入"></Input>
              </FormItem>
            </Col>
          </Row>
        <FormItem >
            <Button style="background:#2d8cf0;color:#fff">提交</Button>
            <Button  style="background:#2d8cf0;color:#fff;margin-left: 8px" @click="back">返回</Button>
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
        area: '群体',
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
