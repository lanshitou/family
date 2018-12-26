<template>
    <div class="layout-body">
      <div class="layout-body-breadcrumb">
        <Breadcrumb>
          <BreadcrumbItem href="organization">
            <Icon type="card"></Icon> 保健任务
          </BreadcrumbItem>
          <BreadcrumbItem href="organization_edit">
            <Icon type="social-buffer-outline"></Icon>保健任务查看
          </BreadcrumbItem>
        </Breadcrumb>
      </div>
      <div class="layout-content">
        <div class="layout-content-form">
          <Form id="form" :label-width="60">
            <fieldset>
            <Row type="flex" justify="center">
                <span class="titleTop">{{title}}</span>
            </Row>
             <Row>
              <Col span="8">
                <FormItem label="任务类型" class="form-label">{{type}}
                </FormItem>
              </Col>
              <Col span="8" push="2">
                <FormItem label="发布单位" class="form-label">{{institution}}
                </FormItem>
              </Col>
              <Col span="8" push="4">
                <FormItem label="发布时间" class="form-label">{{time}}
                </FormItem>
              </Col>
            </Row>
            <Row class="form-row">
                {{contect}}
            </Row>
            </fieldset>
              <FormItem style="margin-top:10px" label="附件">
                  <span style="color:#0E80F1;float:left">下载</span>
                <Button style="background:#5e87b0;color:#fff;margin-left: 8px;float:right" @click="goback">返回</Button>
                <Button style="background:#5e87b0;color:#fff;margin-left: 8px;float:right" @click="back">回复</Button>
            </FormItem>
          </Form>
          <Modal
          title=" "
          v-model="modal"
          :mask-closable="false"
          ok-text="发送"
          @on-ok="dohandel"
          cancel-text=" "
          >
        <Form>
            <FormItem label="回复" >
                {{title}}
            </FormItem>
            <FormItem label="回复标题" >
                <Input placeholder="请输入" style="width:80%"></Input>
            </FormItem>
            <FormItem label="回复内容">
                <Input placeholder="请输入"style="width:80%"type="textarea" :autosize="true"></Input>
            </FormItem>
            <!-- <FormItem label="添加附件："> -->
                    <div class='filterTitle'>添加附件</div>
                    <Row v-for="(item, index) in file"  :key="index" >
                       <div class='filter' >
                      <Col span="3"><Upload 
                            ref="upload"
                            multiple
                            :action="url" 
                            :max-size="1073741824"
                            :show-upload-list="false"
                            :before-upload="berforUpload"
                            :on-success="handleSuccess"
                            :on-exceeded-size="handleMaxSize"
                            :on-format-error="handleFormatError"
                            :format="['doc','docx','pdf','jpg','png','jpeg','gif','txt']"
                            style="float:left;margin-left:0px"
                            type="select"
                            >
                              <Button type="ghost" icon="ios-cloud-upload-outline">上传文件</Button>
                            </Upload>
                          </Col>
                      <Col span="8"><a :href="item.storepath" style="font-size: 12px;" target="_Blank">{{item.attachmentname}}</a></Col>
                      <Col span="1" push="2"><Button type="primary" @click="handledivRemove(index)" style="margin-bottom:5px">删除</Button></Col>
                      </div>
                    </Row>
                    <Row type="flex" justify="start">
                      <Col span="4">
                       <Form-item >
                          <Button type="dashed" @click="handleAdd" icon="plus-round" long>添加</Button>
                       </Form-item></Col>
                    </Row>
            <!-- </FormItem> -->
        </Form>
        </Modal>
        </div>
      </div>
    </div>
    </template>
    <script>
    export default {
      data () {
        return {
          url: '//jsonplaceholder.typicode.com/posts/', // 上传地址
          Params: {
            id: ((new Date()).valueOf() + Math.random()) * 10000
          },
          onefile: [],
          file: [],
          storFile: [],
          title: '关于若干干部去你院体检的通知',
          type: '体检',
          institution: '省保健局',
          time: '2017-11-3',
          contect: 'XXXXXXXXXXXXXXXXXXXXXXXXXXXXX',
          modal: false
        }
      },
      methods: {
        goback () {
          this.$router.push({path: 'task'})
        },
        // 回复
        back () {
          this.modal = true
        },
        dohandel () {
          this.$Message.success('发送成功!')
        },
      // 添加
        handleAdd () {
          if (this.file.length < 5) {
            this.onefile = []
            this.file.push({
              attachmentname: '',
              storepath: ''
            })
          } else {
            this.$Message.error('最多添加5个附件!')
          }
        },
      // 删除
        handledivRemove (index) {
          this.file.splice(index, 1)
          this.storFile.splice(index, 1)
        },
        // 上传成功
        handleSuccess: function (res, file) {
          console.log(file)
          var upload = {
            attachmentname: file.name
            // storepath: global.apiBaseConfigURL + file.response
          }
          var uploads = {
            attachmentname: file.name,
            storepath: file.response
          }
          this.onefile.push(upload)
          this.file.splice(this.file.length - 1, 1)
          this.file.push(upload)
          this.storFile.push(uploads)
        },
        // 格式验证
        handleFormatError: function (file) {
          this.$Message.error('文件格式不正确，请上传 doc  docx  pdf  txt  jpg jpeg png gif 格式的文件!')
        },
        // 大小验证
        handleMaxSize: function (file) {
          this.$Message.error('上传文件不能超过1G!')
        },
        // 数量验证
        berforUpload: function () {
          var jpg = /\w(\.gif|\.jpeg|\.png|\.jpg|\.bmp)/
          for (var i = 0; i < this.storFile.length; i++) {
            if (jpg.test(this.storFile[i].storepath)) {
              this.Params.id = this.Params.id * 3
            }
          }
          const check = this.onefile.length < 1
          if (!check) {
            this.$Message.error('一次只上传一个附件，需要添加请点击添加！')
          }
          return check
        }
      }
    }
    </script>
    <style scoped>
    .titleTop{
        font-size: 18px;
    }
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
      /* width: 110px */
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