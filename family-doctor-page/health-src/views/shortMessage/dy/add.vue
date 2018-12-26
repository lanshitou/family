<template>
    <div class="layout-body">
        <div class="layout-body-breadcrumb">
            <Breadcrumb>
                <BreadcrumbItem href="organization">
                    <Icon type="ios-home-outline"></Icon> 短信短语管理
                </BreadcrumbItem>
                <BreadcrumbItem href="organization_add">
                    <Icon type="social-buffer-outline"></Icon>短信短语新增
                </BreadcrumbItem>
            </Breadcrumb>
        </div>
        <div class="layout-content">
            <div class="layout-content-form">
                <Form :model="formItem" :label-width="150">
                    <div v-show="show">
                            <Row type="flex" justify="center">
                                <Col span="12">
                                <FormItem label="新增短语类型">
                                    <Input v-model="formItem.input" placeholder="请输入"></Input>
                                </FormItem>
                                </Col>
                            </Row>
                    </div>
                    <div v-show="show1">
                            <Row type="flex" justify="center">
                                <Col span="12">
                                <FormItem label="新增短语类型">
                                    <Input v-model="formItem.input" placeholder="请输入"></Input>
                                </FormItem>
                                </Col>
                            </Row>
                            <Row type="flex" justify="center">
                                <Col span="12">
                                <FormItem label="新增短语内容">
                                    <Input v-model="formItem.input" placeholder="请输入"></Input>
                                </FormItem>
                                </Col>
                            </Row>
                    </div>
                    <FormItem>
                        <Button style="background:#2d8cf0;color:#fff">发送</Button>
                        <Button style="background:#2d8cf0;color:#fff;margin-left: 8px" @click="back">返回</Button>
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
      show: false,
      show1: false,
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
  created: function () {
    // console.log(this.$route.params.id)
    var s = this.$route.params.id
    if (s === 1) {
      this.show = true
    } else {
      this.show1 = true
    }
  },
  methods: {
    // 返回
    back () {
      var s = this.$route.params.id
      this.$router.push({name: 'dy', params: {code: s}})
    }
  }
}
</script>
<style scoped>
    .layout-body {
        background: #f8f8f9;
    }

    .layout-body-breadcrumb {
        text-align: left;
        font-size: 36px;
    }

    .layout-content {
        width: 80%;
        margin: 20px;
        background: #ffffff;
        border: 1px solid #dddee1;
    }

    .layout-content-form {
        padding: 20px;
        padding-bottom: 50px;
    }
</style>
       