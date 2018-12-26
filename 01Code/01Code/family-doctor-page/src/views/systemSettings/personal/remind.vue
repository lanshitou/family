<template>
  <div>
    <div class="layout-content-main">
      <div style="margin:10px">
        <Form ref="formDynamic" :model="formDynamic" :label-width="130">
          <Row v-for="(item, index) in formDynamic.items" type="flex" justify="center">
            <Col span="6">
            <Form-item :label="item.messagecategoryname" style="margin:5px">
              <Checkbox v-model="item.configstatus">开启</Checkbox>
            </Form-item>
            </Col>
            <Col span="6" pull="1">
            <Form-item label="提醒方式" style="margin:5px">
              <Checkbox v-model="item.messagestatus" >短信提醒</Checkbox>
            </Form-item>
            </Col>
          </Row>
          <Row >
            <Col span="6" push="6"> 
              <Form-item>
                <Button type="primary" @click="handleSubmit('formDynamic')">提交</Button>
                <Button type="ghost" @click="cancle()" style="margin-left: 8px">返回</Button>
              </Form-item>
            </Col>
          </Row>
        </Form>
      </div>
    </div>
  </div>
</template>
<script>
import {
  findallMsgcategory, saveMsgcategory, findallMsgconfig
} from '../../../api/api'
export default {
  data () {
    return {
      formDynamic: {
        items: []
      }
    }
  },
  created: function () {
    // 根据用户查询系统消息设置信息
    findallMsgconfig().then((res) => {
      if (res.data.length === 0) {
        // 查询全部系统消息分类
        findallMsgcategory().then((res) => {
          for (var i = 0; i < res.data.length; i++) {
            this.formDynamic.items.push({
              messagecategoryidentity: res.data[i].id,
              messagecategoryname: res.data[i].category,
              configstatus: true,
              messagestatus: true
            })
          }
        })
      } else {
        for (var i = 0; i < res.data.length; i++) {
          this.formDynamic.items.push({
            id: res.data[i].id,
            messagecategoryidentity: res.data[i].messagecategoryidentity,
            messagecategoryname: res.data[i].messagecategoryname,
            configstatus: res.data[i].configstatus,
            messagestatus: res.data[i].messagestatus
          })
        }
      }
    })
  },
  methods: {
    // 返回
    cancle: function () {
      this.$router.push({ path: 'personal' })
    },

    handleSubmit: function () {
      saveMsgcategory(this.formDynamic.items).then((res) => {
        if (res.status === 200) {
          this.$Message.success('设置成功!')
        } else {
          this.$Message.success('设置失败!')
        }
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

.layout-breadcrumb {
  padding: 10px 15px 0;
}

.layout-content-main {
  padding: 5px;
}
</style>
