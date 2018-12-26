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
                 :label="(index + 1)+'、'"
                 :prop="'items.' + index + '.membername'"
                 :rules="{required: true, message: '成员' + (index + 1) +'姓名不能为空', trigger: 'blur'}">
                <Row>
                  <Col span="8">
                    <Input v-model="item.identitynumber" placeholder="请输入身份证号查询" :disabled="item.id === '' || item.id === undefined ? false : true"></Input>                   
                  </Col>          
                  <Col span="2" style="margin-left:1px;margin-right:1px;">
                    <Button  type="ghost" @click="handleQuery(index)" :disabled="item.id === '' || item.id === undefined ? false : true">查询</Button>
                  </Col>                              
                  <Col span="3" style="margin-left:5px">
                    <Input  v-model="item.membername" placeholder="姓名" prop="membername" :disabled="item.id === '' || item.id === undefined ? false : true" readonly></Input>
                  </Col>
                  <Col span="2" style="margin-left:5px">
                    <Input v-model="item.sexname" placeholder="性别" :disabled="item.id === '' || item.id === undefined ? false : true" readonly></Input>
                  </Col> 
                  <Col span="2" style="margin-left:5px">
                    <Input  v-model="item.age" placeholder="年龄" :disabled="item.id === '' || item.id === undefined ? false : true" readonly></Input>
                  </Col>                                             
                  <Col span="4" style="margin-left:5px">
                    <Select v-model="item.membership" placeholder="请选择成员身份">
                      <Option v-for="shipitem in memberShipSource" :value="shipitem.codeinfovalue">{{ shipitem.codeinfoname }}</Option>
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
                <Button type="primary" @click="handleFamily('formDynamic')">提交</Button>
                <Button type="primary" style="margin-left: 8px" @click="back">返回</Button>
              </Form-item>
            </Form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import {
   findMemberWithContactByCondition, findMemberShip
   , findMemeberWithInsertByCon
   , updateWithInsertMember
} from '../../api/api'
export default {
  data () {
    return {
      count: 0, // 计算户主个数
      memberShipSource: [],
      address: '', // 户籍地址
      identitynumber: '', // 身份证号
      membername: '', // 户主姓名
      telephone: '', // 联系电话
      formDynamic: {
        items: [
          {
            identitynumber: '', // 家庭成员的身份证编号
            healthidentity: '', // 居民健康档案外键
            membername: '', // 成员姓名
            membership: 0, // 家庭成员身份
            sex: '',
            age: '',
            address: '',
            archivesnumber: '', // 居民健康档案编号
            ismaster: 0, // 是否是户主
            birthdate: '',
            telephone: ''
          }
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
    this.identitynumber = this.$route.params.identitynumber
    let parm = {
      identitynumber: this.$route.params.identitynumber
    }
    this.$Loading.start()
    findMemeberWithInsertByCon(parm).then((res) => {
      if (res.data !== null && res.data.length > 0) {
        this.formDynamic.items = res.data
        // console.log(res.data)
        this.familynumberId = res.data[0].familyidentity
        this.formDynamic.items.forEach(function (element) {
          element.sexname = element.sex === 0 ? '未知性别' : element.sex === 1 ? '男' : element.sex === 2 ? '女' : '未说明性别'
          if (element.membership === 0) {
            element.membership = '0'
          }
          if (element.membership === 1) {
            element.membership = '1'
          }
          if (element.membership === 2) {
            element.membership = '2'
          }
          if (element.membership === 3) {
            element.membership = '3'
          }
          if (element.membership === 4) {
            element.membership = '4'
          }
          if (element.membership === 5) {
            element.membership = '5'
          }
          if (element.membership === 6) {
            element.membership = '6'
          }
          if (element.membership === 7) {
            element.membership = '7'
          }
          if (element.membership === 8) {
            element.membership = '8'
          }
          if (element.membership === 9) {
            element.membership = '9'
          }
        })
      }
    })
  },
  methods: {
    // 返回
    back () {
      this.$router.push({name: 'contact/add', params: {id: this.identitynumber, type: '2'}})
    },
    handleAdd () {
      this.formDynamic.items.push({
        identitynumber: '',
        membername: '',
        membership: 521,
        sex: '',
        age: ''
      })
    },
    // 提交
    handleFamily (name) {
      this.$refs[name].validate((valid) => {
        if (!valid) {
          this.$Message.error('表单验证失败!')
        } else {
          // 保存数据结构拼接
          var saveData = {
            members: [
            ]
          }
          var nullboo = true
          var masterCount = 0
          // var master = {}
          for (var i = 0; i < this.formDynamic.items.length; i++) {
            if (this.formDynamic.items[i].membership === '0') {
              this.formDynamic.items[i].ismaster = 1
            } else {
              this.formDynamic.items[i].ismaster = 0
            }
          }
          this.formDynamic.items.forEach(function (element) {
            if (element.membership === '0') {
              masterCount = masterCount + 1
              // master = element
            }
            if (element.membership === 521) {
              nullboo = false
            }
          })
          if (!nullboo) {
            nullboo = false
            this.$Message.error('请选择成员关系')
          }
          if (nullboo && (masterCount === 0)) {
            this.$Message.error('请选择户主!')
            nullboo = false
          }
          if (nullboo && masterCount > 1) {
            this.$Message.error('只能选择一个户主!')
            nullboo = false
          }
          if (nullboo) {
            for (var k = 0; k < this.formDynamic.items.length; k++) {
              if (this.formDynamic.items[k].membership === '0') {
                // console.log(this.formDynamic.items[k])
                saveData.id = this.formDynamic.items[k].familyidentity
                saveData.familynumber = this.formDynamic.items[k].familynumber
                saveData.healthidentity = this.formDynamic.items[k].healthidentity
                saveData.membername = this.formDynamic.items[k].membername
                saveData.identitynumber = this.formDynamic.items[k].identitynumber
                saveData.telephone = this.formDynamic.items[k].telephone
                saveData.address = this.formDynamic.items[k].address
              }
              // 原始数据  只传ID和关系
              if (this.formDynamic.items[k].id !== null && this.formDynamic.items[k].id !== '' && this.formDynamic.items[k].id !== undefined) {
                var model = {
                  id: '',
                  identitynumber: '',
                  membership: '',
                  ismaster: ''
                }
                model.ismaster = this.formDynamic.items[k].ismaster
                model.id = this.formDynamic.items[k].id
                model.identitynumber = this.formDynamic.items[k].identitynumber
                model.membership = this.formDynamic.items[k].membership
                saveData.members.push(model)
              } else {
                saveData.members.push(this.formDynamic.items[k])
              }
            }
            // console.log(saveData)
            updateWithInsertMember(saveData).then((res) => {
              if (res.status === 200) {
                this.$Message.info('保存成功')
                // type仅仅只是为了再添加协议页面加载时的判断
                this.$router.push({name: 'contact/add', params: {id: this.identitynumber, type: '2'}})
              }
            })
          }
        }
      })
    },
    handleRemove (index) {
      if (this.formDynamic.items[index].id !== '' && this.formDynamic.items[index].id !== undefined) {
        this.$Modal.warning({
          title: '',
          content: '<p>原始数据不能删除！</p>'
        })
      } else {
        this.formDynamic.items.splice(index, 1)
      }
    },
    handleQuery (index) {
      if (this.formDynamic.items[index].identitynumber === '') {
        this.$Message.error('请输入身份证号!')
      } else {
        var idcode = this.formDynamic.items[index].identitynumber
        if (idcode !== undefined && idcode !== '') {
          var boo = true
          for (var k = 0; k < this.formDynamic.items.length; k++) {
            if (k !== index && this.formDynamic.items[k].identitynumber === idcode) {
              boo = false
            }
          }
          if (!boo) {
            this.$Modal.warning({
              title: '',
              content: '<p>身份证号重复！</p>'
            })
            this.formDynamic.items[index].age = ''
            this.formDynamic.items[index].identitynumber = this.formDynamic.items[index].identitynumber
            this.formDynamic.items[index].membername = ''
            this.formDynamic.items[index].sex = ''
            this.formDynamic.items[index].healthidentity = ''
            this.formDynamic.items[index].address = ''
            this.formDynamic.items[index].archivesnumber = ''
            this.formDynamic.items[index].birthdate = ''
            this.formDynamic.items[index].telephone = ''
          } else {
            let queryParam = {
              identitynumber: this.formDynamic.items[index].identitynumber
            }
            // 开始查询
            this.$Loading.start()
            findMemberWithContactByCondition(queryParam).then((res) => {
              // console.log(res)
              if (res.data !== null && res.data.length > 0) {
                this.$Loading.finish()
                let resdient = res.data[0]
                this.formDynamic.items[index].age = resdient.age
                this.formDynamic.items[index].identitynumber = resdient.identitynumber
                this.formDynamic.items[index].membername = resdient.residentname
                this.formDynamic.items[index].sex = resdient.sex
                this.formDynamic.items[index].healthidentity = resdient.id
                this.formDynamic.items[index].address = resdient.permanentaddress
                this.formDynamic.items[index].archivesnumber = resdient.archivesnumber
                this.formDynamic.items[index].birthdate = resdient.birthdate
                this.formDynamic.items[index].telephone = resdient.contactphone
              } else {
                this.formDynamic.items[index].age = ''
                this.formDynamic.items[index].identitynumber = this.formDynamic.items[index].identitynumber
                this.formDynamic.items[index].membername = ''
                this.formDynamic.items[index].sex = ''
                this.formDynamic.items[index].healthidentity = ''
                this.formDynamic.items[index].address = ''
                this.formDynamic.items[index].archivesnumber = ''
                this.formDynamic.items[index].birthdate = ''
                this.formDynamic.items[index].telephone = ''
                this.$Modal.confirm({
                  title: '居民健康档案信息',
                  content: '<p>暂无此居民档案，是否新建？</p><p>注：若无居民档案，则无法进行签约</p>',
                  onOk: () => {
                    this.$Message.info('点击了确定')
                    this.$router.push({ name: 'personal/record', params: {identitynumber: this.identitynumber, backtype: '1'} })
                  },
                  onCancel: () => {
                    this.$Message.info('点击了取消')
                  }
                })
              }
            })
          }
        }
      }
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
