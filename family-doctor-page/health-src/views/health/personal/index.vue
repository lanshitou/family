<template>
    <div class="layout-body">
        <div class="layout-body-breadcrumb">
            <Breadcrumb>
                <BreadcrumbItem href="object">
                    <Icon type="ios-gear"></Icon> 个人中心
                </BreadcrumbItem>
            </Breadcrumb>
        </div>
        <div class="layout-head">
            
        <Row type="flex" justify="end">
                <Button style="background:#5e87b0;color:#fff" @click="editpwd">修改密码</Button>
                <Button style="background:#5e87b0;color:#fff;margin-left: 8px" @click="edittel">更换手机号</Button>
            </Row>
        </div>
        <div class="layout-content">
          <div class="layout-content-form">
            <Form :model="formItem" :label-width="80">
              <Row type="flex" justify="center">
                <Col span="8">
                  <FormItem label="ID">
                        <span style="float:left">{{formItem.id}}</span>
                  </FormItem>
                </Col>
             </Row>
             <Row type="flex" justify="center">
               <Col span="8" >
                 <FormItem label="角色">
                        <span style="float:left">{{formItem.role}}</span>
                 </FormItem>
               </Col>
            </Row>
             <Row  type="flex" justify="center">
                <Col span="8">
                  <FormItem label="用户名">
                        <span style="float:left">{{formItem.user}}</span>
                  </FormItem>
                </Col>
              </Row>
              <Row  type="flex" justify="center">
                 <Col span="8" >
                   <FormItem label="电话">
                        <span style="float:left">{{formItem.tel}}</span>
                   </FormItem>
                 </Col>
               </Row>
              <Row  type="flex" justify="center">
                <Col span="8">
                  <FormItem label="身份证">
                        <span style="float:left">{{formItem.idcard}}</span>
                  </FormItem>
                </Col>
              </Row>
              <Row  type="flex" justify="center">
                <Col span="8" >
                  <FormItem label="性别">
                        <span style="float:left">{{formItem.sex}}</span>
                  </FormItem>
                </Col>
              </Row>
              <Row  type="flex" justify="center">
                <Col span="8">
                  <FormItem label="所属机构">
                        <span style="float:left">{{formItem.institution}}</span>
                  </FormItem>
                </Col>
              </Row>
              <Row  type="flex" justify="center">
                <Col span="8">
                  <FormItem label="机构地址">
                      <span style="float:left">{{formItem.address}}</span>
                  </FormItem>
                </Col>
              </Row>
          </Form>
          </div>
        </div>

    </div>
    </div>
</template>
  <script>
  export default {
    data () {
      return {
        formItem: {
          id: '6102646',
          role: '保健人员',
          user: 'male',
          tel: '13685241475',
          idcard: '610425198805223451',
          sex: '男',
          institution: '省保健局',
          address: '陕西省西安市雁塔区'
        }
      }
    },
    created: function () {
    },
    methods: {
      editpwd () {
        this.$router.push({path: 'personal_editpwd'})
      },
      edittel () {
        this.$router.push({path: 'personal_edittel'})
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