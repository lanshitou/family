/**
 * @title: 居民家庭新增
 * @description: 居民家庭新增
 * @author: TiuWeb
*/
<template>
  <div>
    <div style="margin-top:20px;margin-left:50px">
      <div class="layout-content-main">
        <div class='columnBox menu'>
          <div>
            <div class='filterTitle'>家庭成员信息</div>
            <Form ref="formDynamic" :model="formDynamic" :label-width="130">                                                            
              <Form-item style="margin:0px" v-for="(item, index) in formDynamic.items" 
                 :key="item" :label="(index + 1)+'、'"
                 :prop="'items.' + index + '.membername'"
                 :rules="{required: true, message: '成员' + (index + 1) +'姓名不能为空', trigger: 'blur'}">
                <Row>
                  <Col span="8">
                    <Input v-model="item.identitynumber" placeholder="请输入身份证号查询" style=""></Input>                   
                  </Col>          
                  <Col span="2" style="margin-left:1px;margin-right:1px;">
                    <Button type="ghost" @click="handleQuery(index)">查询</Button>
                  </Col>                              
                  <Col span="3" style="margin-left:5px">
                    <Input v-model="item.membername" placeholder="姓名" prop="membername" readonly></Input>
                  </Col>
                  <Col span="2" style="margin-left:5px">
                    <Input v-model="item.sex" placeholder="性别" readonly></Input>
                  </Col> 
                  <Col span="2" style="margin-left:5px">
                    <Input v-model="item.age" placeholder="年龄" readonly></Input>
                  </Col>                                             
                  <Col span="4" style="margin-left:5px">
                    <Select v-model="item.membership" placeholder="请选择成员身份" @on-change="changeR">
                      <Option v-for="item in memberShipSource" :value="item.codeinfovalue" :key="item">{{ item.codeinfoname }}</Option>
                    </Select>
                  </Col>
                  <Col span="2" offset="1" style="margin-left:3px">
                    <Button type="ghost" @click="handleRemove(index)">删除</Button>
                  </Col>
                </Row>
              </Form-item>               
              <Form-item style="margin-top: 25px">               
                <Row>
                  <Col span="8">
                    <Button type="dashed" long  @click="handleAdd" icon="plus-round">新增</Button>
                  </Col>
                </Row>                
              </Form-item>                               
              <Form-item style="margin:5px">
                <Button type="primary" @click="handleSubmit('formDynamic')">提交</Button>
                <Button type="primary" style="margin-left: 8px" @click="back">返回</Button>
              </Form-item>
            </Form>
          </div>
        </div>
      </div>
    </div>
    <div class="layout-copy">
      2011-2016 &copy; TalkingData
    </div>
  </div>
</template>
<script>
import {
   findAllMemberWithFamilyByCondition, findMemberShip
} from '../../../api/api'
export default {
  data () {
    return {
      memberShipSource: [],
      formDynamic: {
        items: [
          {
            identitynumber: '',
            healthidentity: '',
            membername: '',
            membership: {},
            sex: '',
            age: ''
          }
        ]
      },
      ruleValidate: {
        membername: [
            { required: true, message: '姓名不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  created: function () {
    // 查询身份信息
    this.$Loading.start()
    findMemberShip({}).then((res) => {
      this.$Loading.finish()
      this.memberShipSource = res.data
    })
  },
  methods: {
    // 返回
    back () {
      this.$router.push({ path: 'familys' })
    },
    // 保存家庭信息
    handleSubmit (name) {
      // console.log(this.formDynamic.items)
      this.$refs[name].validate((valid) => {
        if (valid) {
          this.$Message.success('提交成功!')
        } else {
          this.$Message.error('表单验证失败!')
        }
      })
    },
     // 身份select选择器选中的Option变化时触发
    changeR (change) {
    },
    handleAdd () {
      this.formDynamic.items.push({
        identitynumber: '',
        membername: '',
        membership: '',
        sex: '',
        age: ''
      })
    },
    handleRemove (index) {
      this.formDynamic.items.splice(index, 1)
    },
    handleQuery (index) {
      // console.log(this.formDynamic.items[index].identitynumber)
      let queryParam = {
        identitynumber: this.formDynamic.items[index].identitynumber
      }
      // 开始查询
      this.$Loading.start()
      findAllMemberWithFamilyByCondition(queryParam).then((res) => {
        this.$Loading.finish()
        let resdient = res.data[0]
        this.formDynamic.items[index].age = resdient.age
        this.formDynamic.items[index].identitynumber = resdient.identitynumber
        this.formDynamic.items[index].membername = resdient.residentname
        this.formDynamic.items[index].sex = resdient.sex
        this.formDynamic.items[index].healthidentity = resdient.id
      })
    }
  }
}
</script>
<style scoped lang="less">
.filterTitle {
  font-size: 14px;
  color: #1c2438;
  padding: 10px 12px;
}

.menu {
  flex-direction: row;
  align-items: center;
}

.columnBox {
  display: -webkit-flex;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
}

.pages-block {
  margin: 0px;
  overflow: hidden;
  margin-top: 10px;
  margin-bottom: 10px;
  text-align: center;
  .pages-right {
    vertical-align: middle;
  }
}
.layout-content-main {
  padding: 5px;
}

.layout-copy {
  text-align: center;
  padding: 10px 0 20px;
  color: #9ea7b4;
}
</style>
