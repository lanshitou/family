/**
 * @Title : 首页
 * @Description : 首
 * @author : TiuWeb
 */
<template>
  <div style="padding:10px 20px;background:#f5f5f5;height:90%; overflow:auto;">
    <div class="welcome">
      <h2 class="title">欢迎您使用{{sysname}}！</h2>
      <span class="info">系统概括</span>
    </div>
    <Row type="flex" justify="end" >
      <Button type="primary" style="margin-bottom:5px" @click.native="back()">返回</Button>
    </Row>
    <Row :gutter="16">
      <Col :xs="24" :sm="24" :md="12" :lg="12">
        <Card class="card">
          <div class="card-block">
            <div class="card-left"><img class="card-img" src="../../assets/framework/buy.png"></div>
            <div class="card-right">
              <div class="number">{{loginnum}}</div>
              <router-link :to="{path: 'orgpage'}">待处理签约协议</router-link>
              <span class="label label-success"> Today </span>
            </div>
          </div>
        </Card>
      </Col>
      <Col :xs="24" :sm="24" :md="12" :lg="12">
        <Card class="card">
          <div class="card-block">
            <div class="card-left"><img class="card-img" src="../../assets/framework/cloud.png"></div>
            <div class="card-right">
              <div class="number">{{opernum}}</div>
              <router-link :to="{path: 'orgpage'}">待执行签约服务</router-link>
              <span class="label label-success"> Today </span>
            </div>
          </div>
        </Card>
      </Col>
      </Row>
      <Row :gutter="16">
      <Col :xs="24" :sm="24" :md="12" :lg="12">
        <Card class="card">
          <div class="card-block">
            <div class="card-left"><img class="card-img" src="../../assets/framework/settings.png"></div>
            <div class="card-right">
              <div class="number">{{bussexpnum}}</div>
              <router-link :to="{path: 'orgpage'}">服务申请</router-link>
              <!--<div class="title">服务申请</div>-->
              <span class="label label-success"> Today </span>
            </div>
          </div>
        </Card>
      </Col>
      <Col :xs="24" :sm="24" :md="12" :lg="12">
        <Card class="card">
          <div class="card-block">
            <div class="card-left"><img class="card-img" src="../../assets/framework/exep.png"></div>
            <div class="card-right">
              <div class="number">{{expnum}}</div>
              <router-link :to="{path: 'orgpage'}">新增问诊</router-link>
              <span class="label label-success"> Today </span>
            </div>
          </div>
        </Card>
      </Col>
    </Row>
      <Row :gutter="16">
      <Col :xs="24" :sm="24" :md="12" :lg="12">
        <Card class="card">
          <div class="card-block">
            <div class="card-left"><img class="card-img" src="../../assets/framework/exep.png"></div>
            <div class="card-right">
              <div class="number">{{noticenum}}</div>
              <router-link :to="{path: 'orgpage'}">新增投诉建议</router-link>
              <!--<div class="title">服务申请</div>-->
              <span class="label label-success"> Today </span>
            </div>
          </div>
        </Card>
      </Col>
      <!-- <Col :xs="24" :sm="24" :md="12" :lg="12">
        <Card class="card">
          <div class="card-block">
            <div class="card-left"><img class="card-img" src="../../assets/framework/settings.png"></div>
            <div class="card-right">
              <div class="number">{{settingnum}}</div>
              <router-link :to="{path: 'orgpage'}">系统设置</router-link>
              <span class="label label-success"> Today </span>
            </div>
          </div>
        </Card>
      </Col> -->
    </Row>
  </div>
</template>
<script>
  export default {
    data () {
      return {
        sysname: '家庭医生系统',
        chart: {},
        loginnum: '10',
        opernum: '0',
        bussexpnum: '5',
        expnum: '0',
        settingnum: '8',
        noticenum: '6'
      }
    },
    created () {
    },
    methods: {
      back () {
        this.$router.push({path: 'inedx'})
      }
    },
    mounted () {
    }
  }

</script>
<style scoped lang="less">
  .welcome {
    border-bottom: 1px solid #dddddd;
    margin-bottom: 20px;
    margin-top: -10px;
    margin-left: -20px;
    margin-right: -20px;
    padding-left: 20px;
    background: #fff;
  }

  .welcome .title {
    text-align: left;
    color: #666666;
    font-size: 29px;
    font-weight: normal;
  }

  .welcome .info {
    font-size: 14px;
    color: #b4b5b3;
  }

  .card {
    background: #f5f5f5;
    margin-bottom: 20px;
  }

  .ivu-card:hover {
    box-shadow: none;
    border-color: #dddddd;
  }

  .ivu-card .ivu-card-body {
    padding: 0!important;
  }

  .ivu-card-bordered {
    border-color: #dddddd;
  }

  .card-block {
    width: 100%;
    height: 80px;
    box-sizing: border-box;
  }

  .card-left {
    width: 30%;
    height: 80px;
    float: left;
    padding: 17px 20px;
    border-radius: 4px 0 0 4px;
    color: #FFFFFF;
    text-align: center;
    background-color: #f4f4f4;
    border-right: 1px solid #DDDDDD;
  }

  .card-img {
    width: 46px;
    height: 46px;
  }

  .card-right {
    width: 70%;
    height: 100%;
    float: right;
    padding-top: 10px;
    border-radius: 0 4px 4px 0;
    position: relative;
    text-align: center;
    background: #fff;
  }

  .card-right .number {
    font-size: 30px;
    font-weight: 400;
    color: #666666;
  }

  .card-right title {
    text-transform: uppercase;
  }

  .card-right .label {
    position: absolute;
    right: 5px;
    top: 5px;
    font-size: 11px;
    padding: .2em .6em .3em;
    color: #ffffff;
    text-align: center;
    white-space: nowrap;
    vertical-align: baseline;
    border-radius: .25em;
  }

  .label-success {
    background-color: #a8bc7b;
  }

  .box {
    clear: both;
    margin-top: 0px;
    margin-bottom: 25px;
    padding: 0px;
    border-radius: 4px;
  }

  .box .box-title {
    padding: 6px 10px 2px;
    min-height: 35px;
    border-radius: 4px 4px 0 0;
    margin-bottom: 0;
  }

  .box-body {
    border-radius: 0 0 4px 4px;
    padding: 10px;
    background: #f5f5f5;
  }

  .ivu-tabs-nav .ivu-tabs-tab:before {
    content: '';
    position: absolute;
    z-index: 1;
    top: -1px;
    left: -10px;
    bottom: 0;
    width: 1em;
    background: #f5f5f5;
    border-left: 1px solid #d0d0d0;
    border-top: 1px solid #d0d0d0;
    transform: skew(170deg);
    -webkit-transform: skew(170deg);
    border-radius: 5px 0 0 0;
  }

  .ivu-tabs-nav .ivu-tabs-tab {
    position: relative;
    background: #f5f5f5;
    padding: 7px 15px;
    float: left;
    text-decoration: none;
    color: #444;
    text-shadow: 0 1px 0 rgba(255, 255, 255, 0.8);
    border-radius: 5px 0 0 0;
    box-shadow: 0 2px 2px rgba(0, 0, 0, 0.4);
    margin-right: 23px;
    border-top: 1px solid #d0d0d0;
  }

  .ivu-tabs-nav .ivu-tabs-tab:after {
    content: '';
    position: absolute;
    z-index: 1;
    top: -1px;
    right: -0.5em;
    bottom: 0;
    width: 1em;
    background: #f5f5f5;
    box-shadow: 2px 2px 2px rgba(0, 0, 0, 0.4);
    border-top: 1px solid #d0d0d0;
    transform: skew(10deg);
    -webkit-transform: skew(10deg);
    border-radius: 0 5px 0 0;
  }

  .ivu-tabs-nav .ivu-tabs-tab:active,
  .ivu-tabs-nav .ivu-tabs-tab:active:after,
  .ivu-tabs-nav .ivu-tabs-tab:active:before {
    background: #fff;
  }

  .ivu-tabs {
    margin-top: -45px;
    width: 100%;
  }

  .ivu-tabs-bar {
    float: right;
    right: -10px;
    position: relative;
    margin-bottom: 0;
  }

  .ivu-tabs-content {
    clear: both;
  }
  #exLog,
  #oper-log,
  #exep-log,
  #buss-log {
    position: relative;
    width: 100%;
    height: 300px;
  }
  @media screen and (max-width: 470px) {
    .chains {
      height: 200px;
    }
  }
</style>
