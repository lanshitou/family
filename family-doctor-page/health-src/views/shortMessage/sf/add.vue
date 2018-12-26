<template>
        <div class="layout-body">
          <div class="layout-body-breadcrumb">
            <Breadcrumb>
              <BreadcrumbItem href="organization">
                <Icon type="ios-home-outline"></Icon> 保健任务
              </BreadcrumbItem>
              <BreadcrumbItem href="organization_edit">
                <Icon type="social-buffer-outline"></Icon>保健任务新增
              </BreadcrumbItem>
            </Breadcrumb>
          </div>
          <div class="layout-content">
            <div class="layout-content-form">
              <Form id="form" :label-width="80">
                <fieldset>
                 <Row class="form-row">
                  <Col span="8">
                    <FormItem label="机构名称" class="form-label">
                        <Select  style="padding-top:5px;width:150px;float:left" placeholder="全部">
                          <Option value="1" label="全部">
                            <span>全部</span>
                          </Option>
                          <Option value="2" label="交大口腔">
                            <span>交大口腔</span>
                          </Option>
                          <Option value="3" label="交大一院">
                            <span>交大一院</span>
                          </Option>
                          <Option value="3" label="交大二院">
                            <span>交大二院</span>
                          </Option>
                        </Select>
                    </FormItem>
                  </Col>
                  <Col span="8" offset="4">
                    <FormItem label="机构代码" class="form-label">
                        <Select  style="padding-top:5px;width:150px;float:left" placeholder="全部">
                          <Option value="1" label="全部">
                            <span>全部</span>
                          </Option>
                          <Option value="2" label="交大口腔">
                            <span>交大口腔</span>
                          </Option>
                          <Option value="3" label="交大一院">
                            <span>交大一院</span>
                          </Option>
                          <Option value="3" label="交大二院">
                            <span>交大二院</span>
                          </Option>
                        </Select>
                    </FormItem>
                  </Col>
                </Row>
                <Row class="form-row">
                  <Col span="19">
                    <FormItem label="任务标题">
                      <Input placeholder="请输入" ></Input>
                    </FormItem>
                  </Col>
                </Row>
                <Row class="form-row">
                  <Col span="19">
                    <FormItem label="任务内容" >
                      <Input placeholder="请输入" ></Input>
                    </FormItem>
                  </Col>
                </Row>
                <Row class="form-row">
                  <Col span="19">
                    <FormItem label="附件" class="form-label">
                      <!-- <Input placeholder="请输入" class="form-text"></Input> -->
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
}
.layout-content{
  width:80%;
  margin:20px;
}
.layout-content-form{
  padding:20px;
  padding-bottom :20px;
}
.form-row {
  height:65px;
  background: #b9cf6a;
  background: rgba(255, 255, 255, .3);
  border-color: #e3ebc3;
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