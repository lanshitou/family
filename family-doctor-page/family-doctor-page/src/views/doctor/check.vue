/**
 * @title: 查看签约医生信息
 * @description: 查看签约医生信息
 * @author: TiuWeb
*/
<template>
  <div class="index">
    <Form  :label-width="100" label-position="right">
      <Card>
        <div slot="title">
          <Row>
            <Col span="23"><p>基本信息</p></Col>
            <Col span="1"><Button type="primary" @click="back">返回</Button></Col>
          </Row>
        </div>
        <Row>
          <Col span="8">
          <Form-item label="姓名" style="margin:0px">
            {{showData.doctorname}}
          </Form-item>
          </Col>
          <Col span="6" pull="2">
          <Form-item label="年龄" style="margin:0px">
            {{showData.age}}
          </Form-item>
          </Col>
          <Col span="6" pull="2">
          <Form-item label="头像" style="margin:0px">
            {{showData.previewimage}}
          </Form-item>
          </Col>
        </Row>
        <Row>
          <Col span="8">
          <Form-item label="性别" style="margin:0px">
            {{showData.sex}}
          </Form-item>
          </Col>
          <Col span="6" pull="2">
          <Form-item label="类别" style="margin:0px">
            {{showData.professionallevelname}}
          </Form-item>
          </Col>
        </Row>
        <Row>
          <Col span="8">
          <Form-item label="用户名" style="margin:0px">
            {{showData.paccount}}
          </Form-item>
          </Col>
          <Col span="6" pull="2">
          <Form-item label="所属医疗机构" style="margin:0px">
            {{showData.institutionname}}
          </Form-item>
          </Col>
        </Row>
        <Row>
          <Col span="8">
          <Form-item label="邮箱" style="margin:0px">
            {{showData.email}}
          </Form-item>
          </Col>
          <Col span="6" pull="2">
          <Form-item label="所属机构地址" style="margin:0px">
            {{showData.medicalinstitutionaddress}}
          </Form-item>
          </Col>
        </Row>
        <Row>
          <Col span="8">
          <Form-item label="联系电话" style="margin:0px">
            {{showData.paccount}}
          </Form-item>
          </Col>
        </Row>
      </Card>
      <Card>
        <div slot="title"><p>签约信息</p></div>
        <Form :label-width="100">
          <Form-item style="margin:0px" v-for="(item, index) in contractList" :label="'所在团队:' +(index + 1)+'、'">
            {{item.teamname}}
            <Row>
              <Col span="12">
              <Form-item label="已接受家庭签约" style="text-align:center;margin:0px">
                {{item.contractcount}} 份
                <a href="">查看</a>
              </Form-item>
              </Col>
            </Row>
            <Row>
              <Col span="12">
              <Form-item label="已提供家庭签约" style="text-align:center;margin:0px">
                {{item.servicecount}} 份
                <a href="">查看</a>
              </Form-item>
              </Col>
            </Row>
            <Row>
              <Col span="12">
              <Form-item label="签约服务评价" style="text-align:center;margin:0px">
                {{item.resultrate}} 份
                <a href="">查看</a>
              </Form-item>
              </Col>
            </Row>
          </Form-item>
        </Form>
      </Card>
    </Form>
  </div>
</template>
<script>
import {
  findDocotrByKey
} from '../../api/api'
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
    this.doctorId = this.$route.path.substring(14) // 截取url后的参数
    let para = {
      id: this.doctorId
    }
    // 根据id查询医生信息
    findDocotrByKey(para).then((res) => {
      this.$Loading.finish()
      this.showData = res.data
      if (res.data.sex === 0) {
        this.showData.sex = '未知的性别'
      } else if (res.data.sex === 1) {
        this.showData.sex = '男'
      } else if (res.data.sex === 2) {
        this.showData.sex = '女'
      } else if (res.data.sex === 9) {
        this.showData.sex = '未说明的性别'
      }
    })
  },
  methods: {
    // 返回
    back () {
      this.$router.push({ path: '/doctors' })
    }
  }
}
</script>
<style scoped lang="less">
.index {
  height: 85%;
  overflow: auto
}
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
  padding: 10px;
}

.layout-content {
  min-height: 78%;
  margin-top: 15px;
  overflow: hidden;
  background: #fff;
  border-radius: 4px;
}
</style>
