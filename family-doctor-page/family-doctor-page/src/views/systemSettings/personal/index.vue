/**
 * @title: 个人资料展示
 * @description: 个人资料展示
 * @author: TiuWeb
*/
<template>
  <div class="index">
    <Row>
      <Button type="primary" style="float:right" @click="back">返回</Button>
    </Row>
    <Form  :label-width="100" >
      <fieldset >
        <legend style="font-weight:bold;font-size:15px;padding: 1px;text-align:left">基本信息</legend>
        <Row>
          <Col span="8">
          <Form-item label="姓名:" style="margin:0px">
            {{showData.doctorname}}
          </Form-item>
          </Col>
          <Col span="8">
          <Form-item label="年龄:" style="margin:0px">
            {{showData.age}}
          </Form-item>
          </Col>
          <Col span="8">
          <Form-item label="头像:" style="margin:0px">
            {{showData.previewimage}}
          </Form-item>
          </Col>
        </Row>
        <Row>
          <Col span="8">
          <Form-item label="性别:" style="margin:0px">
            {{showData.sex}}
          </Form-item>
          </Col>
          <Col span="8">
          <Form-item label="类别:" style="margin:0px">
            {{showData.professionallevelname}}
          </Form-item>
          </Col>
        </Row>
        <Row>
          <Col span="8">
          <Form-item label="用户名:" style="margin:0px">
            {{showData.paccount}}
          </Form-item>
          </Col>
          <Col span="8">
          <Form-item label="所属医疗机构:" style="margin:0px">
            {{showData.institutionname}}
          </Form-item>
          </Col>
        </Row>
        <Row>
          <Col span="8">
          <Form-item label="邮箱:" style="margin:0px">
            {{showData.email}}
          </Form-item>
          </Col>
          <Col span="8">
          <Form-item label="所属机构地址:" style="margin:0px">
            {{showData.medicalinstitutionaddress}}
          </Form-item>
          </Col>
        </Row>
        <Row>
          <Col span="8">
          <Form-item label="联系电话:" style="margin:0px">
            {{showData.paccount}}
          </Form-item>
          </Col>
        </Row>
      </fieldset>
        <fieldset >
        <legend style="font-weight:bold;font-size:15px;padding: 1px;text-align:left">签约信息</legend>
        <Form :label-width="100">
          <Form-item style="margin:0px" v-for="(item, index) in contractList" :label="'所在团队:' +(index + 1)+'、'">
            {{item.teamname}}
            <Row>
              <Col span="12">
              <Form-item label="已接受家庭签约:" style="text-align:center;margin:0px">
                {{item.contractcount}} 份
                <a href="">查看</a>
              </Form-item>
              </Col>
            </Row>
            <Row>
              <Col span="12">
              <Form-item label="已提供家庭签约:" style="text-align:center;margin:0px">
                {{item.servicecount}} 份
                <a href="">查看</a>
              </Form-item>
              </Col>
            </Row>
            <Row>
              <Col span="12">
              <Form-item label="签约服务评价:" style="text-align:center;margin:0px">
                {{item.resultrate}} 份
                <a href="">查看</a>
              </Form-item>
              </Col>
            </Row>
          </Form-item>
        </Form>
      </fieldset>
    </Form>
  </div>
</template>
<script>
import {
  findDocotrByKey
} from '../../../api/api'
export default {
  data () {
    return {
      doctorId: '',
      listData: [],
      showData: [],
      contractInfo: {
        teamname: '',
        contractcount: '',
        servicecount: '',
        resultrate: ''
      },
      formDynamic: {
        items: [
          {
            value: ''
          }
        ]
      },
      contractList: [
        {
          teamname: '测试团队',
          contractcount: '20',
          servicecount: '2',
          resultrate: '60'
        },
        {
          teamname: 'h团队',
          contractcount: '60',
          servicecount: '2',
          resultrate: '60'
        }
      ]

    }
  },
  created: function () {
    this.$Loading.start()
    // this.doctorId = this.$route.path.substring(13, 46) // 截取url后的参数
    let para = {
      id: 'D81D310F0FD948FBB8FFD5794E180063'
    }
    // 根据id查询医生信息
    findDocotrByKey(para).then((res) => {
      this.$Loading.finish()
      this.showData = res.data
    })
  },
  methods: {
    // 判断性别
    changeSex (sex) {
      var nsex = 0
      if (sex === 0) {
        nsex = '未知的性别'
      } else if (sex === 1) {
        nsex = '男'
      } else if (sex === 2) {
        nsex = '女'
      } else if (sex === 9) {
        nsex = '未说明的性别'
      }
      return nsex
    },
    // 返回
    back () {
      this.$router.push({ path: '/doctors' })
    },
    // 修改
    update () {
    }
  }
}
</script>
<style scoped lang="less">
.index {
  margin:5px;
  height: 85%;
  overflow: auto
}
</style>
