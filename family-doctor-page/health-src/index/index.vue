<template>
<div class="content-right-system">
    <div class="topMenu">
      <div class="middle">
        <div class="middle-list">
            <div style="margin-left:85%">你好,admin! </div>
            <div><img src="../assets/set.png" style="margin-top:3px;margin-left:3px" @click="hello()"/></div>
        </div>
        <div class="middle-list-table" id="textDiv" style="display:none">
          <ul style="padding-left:0px">
            <li><a href="http://113.140.30.98:8866/health/#/login">返回首页</a></li>
            <li><a href="">修改密码</a></li>
            <li><a href="">退出登录</a></li>
          </ul>
        </div>
      </div>
    </div>
    <div class="logo">
      <img src="../assets/logo.png" style="height:75px;"/>
    </div>
    <div class="operationsystem">
      <Row>
        <Col>
          <div class="operationsystem-div">
            <Row type="flex" justify="center">
              <Col span="6" push="4">
                <div class="operationsystem-list">
                  <a href="http://113.140.30.98:8866/health/"  target="_Blank" style="width:300px"  >
                  <div class="listImg">
                  <img src="../assets/yiliaobaojianxinxi.png" class="contentPicture"/><br></div>
                  <div class="listDiv">医疗保健信<br>息管理系统</div>
                  </a>
                </div>
              </Col>
              <Col span="6" push="2">
                <div class="operationsystem-list" style="background:#dddee1">
                  <a href="http://115.29.102.11:8081/bjxxh/baojianjv/index_diagTreat" target="_Blank" style="width:300px">
                  <div class="listImg">
                  <img src="../assets/yiliaobaojianfuwu.png" class="contentPicture"/><br></div>
                  <div class="listDiv" style="color:#666">医疗保健服<br>务管理系统</div>
                  </a>
                </div>
              </Col>
              <Col span="6">
                <div class="operationsystem-list">
                  <a href="http://113.140.30.98:8866/health/#/healthAssess" target="_Blank" style="width:300px">
                  <div class="listImg">
                  <img src="../assets/yiliaobaojianjuece.png" class="contentPicture"/><br></div>
                  <div class="listDiv">医疗保健决<br>策支持系统</div>
                  </a>
                </div>
              </Col>
              <Col span="6" pull="2">
                <div class="operationsystem-list" style="background:#dddee1">
                  <a href="http://120.76.244.191:8158/HSC/login/login/temp" target="_Blank" style="width:300px">
                  <div class="listImg">
                  <img src="../assets/yuancheng.png" class="contentPicture"/><br></div>
                  <div class="listDiv" style="left:43px;color:#666">远程会诊系统</div>
                  </a>
                </div>
              </Col>
            </Row>
            <Row>
                <Col span="6" push="4">
                  <div class="operationsystem-list" style="background:#dddee1">
                    <a href="http://120.76.244.191:18080" target="_Blank" style="width:300px">
                    <div class="listImg">
                    <img src="../assets/shuju.png" class="contentPicture"/><br></div>
                    <div class="listDiv"  style="left:43px;color:#666">数据分析系统</div>
                    </a>
                  </div>
                </Col>
                <Col span="6" push="2">
                  <div class="operationsystem-list">
                    <a href="http://113.140.30.98:8866/health/#/db" target="_Blank" style="width:300px">
                    <div class="listImg">
                    <img src="../assets/duanxin.png" class="contentPicture"/><br></div>
                    <div class="listDiv">短信平台</div>
                    </a>
                  </div>
                </Col>
                <Col span="6" >
                  <div class="operationsystem-list" style="background:#dddee1">
                    <a href="http://localhost:8080/#/platform" target="_Blank" style="width:300px">
                    <div class="listImg">
                    <img src="../assets/pingtai.png" class="contentPicture"/><br></div>
                    <div class="listDiv" style="left:50px;color:#666">平台运行监<br>管系统</div>
                    </a>
                  </div>
                </Col>
                <Col span="6" pull="2">
                  <div class="operationsystem-list">
                    <a href="http://113.140.30.98:9090/card/index.jsp" target="_Blank" style="width:300px">
                    <div class="listImg">
                    <img src="../assets/menzhen.png" class="contentPicture"/><br></div>
                    <div class="listDiv" style="left:50px"> 门诊优先就诊</div>
                    </a>
                  </div>
                </Col>
            </Row>
          </div>
        </Col>
      </Row>
    <div style="clear:both"></div>
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
    // push () {
    //   this.$router.push({path: 'organization', params: {id: '123'}})
    // },
    hello () {
      if (document.getElementById('textDiv').style.display === 'block') {
        document.getElementById('textDiv').style.display = 'none'
      } else {
        document.getElementById('textDiv').style.display = 'block'
      }
    }
  }
}
</script>
<style>
 @import "./index.css";
</style>