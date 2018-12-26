/**
 * @Title : 通知公告
 * @Description : 
 * @author : TiuWeb
 */
<template>
  <div class="">
    <div class="layout-content" style="height:95%; overflow:auto;">
      <div class="layout-content-main">
        <div class='columnBox menu'>
          <div class='filterTitle'>状&#8195;&#8195;态</div>
          <Select v-model="queyParams.searchParams.publishstatus" style="width:80px;" placeholder="全部">
                <Option v-for="item in statu" :value="item.value" :key="item.value">{{ item.label }}</Option>
          </Select>
          <div class='filterTitle'>
            <Input v-model="queyParams.searchParams.keyWords" icon="ios-search" placeholder="请输入关键字搜索" style="width: 200px"></Input>
          </div>
          <i-button type="primary"  @click.native="query">查询</i-button>
        </div>
        <div style="background:#eee;padding: 1px;text-align:left">
            <Button-group>
              <Button type="primary" @click.native="add">新增</Button>
            </Button-group>
          </div>
          <Table  :data="tableDatas" :columns="tableColumns"  border/>
          <div class="pages-block">
            <div class="pages-right">
               <Page :total="total" :page-size="queyParams.rows" @on-page-size-change="doPageSizeChange" @on-change="change" show-sizer show-total ref="tablePage" show-elevator></Page>
            </div>
          </div>
        </div>
      </div>
  </div>
</template>
<script>
import {
  findNoticetPage, revokeNotices, sendNotices
} from '../../../api/api'
export default {
  data () {
    return {
      total: 0,
      queyParams: {
        sort: 'desc',
        order: 'create_date',
        page: 0,
        rows: 10,
        searchParams: {
          keyWords: '',
          publishstatus: ''
        }
      },
      statu: [
        {
          value: '',
          label: '全部'
        },
        {
          value: '0',
          label: '待发布'
        },
        {
          value: '1',
          label: '已发布'
        },
        {
          value: '2',
          label: '已撤回'
        }
      ],
      // 详情list
      detailList: [],
      tableDatas: [],
      tableColumns: [
        {
          title: '标题',
          align: 'center',
          key: 'title'
        },
        {
          title: '内容摘要',
          align: 'center',
          key: 'content',
          render: (h, params) => {
            return h('Poptip', {
              props: {
                trigger: 'hover',
                title: '内容摘要',
                placement: 'right-start'
              }
            }, [
              h('Tag', params.row.content.substring(0, 10)),
              h('div', {
                slot: 'content'
              }, [
                h('ul', this.detailList[params.index].split.map(item => {
                  return h('li', {
                    style: {
                      textAlign: 'start',
                      padding: '4px'
                    }
                  }, item)
                }))
              ])
            ])
          }
        },
        {
          title: '发布机构',
          align: 'center',
          key: 'publishorgname'
        },
        {
          title: '发布状态',
          align: 'center',
          key: 'publishstatus',
          render: (h, params) => {
            var date = this.changeStatu(params.row.publishstatus)
            return h('div', [
              h('span', date)
            ])
          }
        },
        {
          title: '发布日期',
          align: 'center',
          key: 'publishtime',
          sortable: true,
          render: (h, params) => {
            var date = this.changeDate(params.row.publishtime)
            return h('div', [
              h('span', date)
            ])
          }
        },
        {
          title: '操作',
          key: 'action',
          width: '120',
          align: 'center',
          render: (h, params) => {
            let showSend = params.row.publishstatus === 2 ? 'inline' : 'none'
            let showRevoke = params.row.publishstatus === 1 | params.row.publishstatus === 0 ? 'inline' : 'none'
            let showEdit = params.row.publishstatus === 2 ? 'inline' : 'none'
            return h('div', [
              h('Tooltip', {
                props: {
                  placement: 'top'
                }
              }, [
                h('Button', {
                  props: {
                    type: 'ghost',
                    size: 'small',
                    shape: 'circle',
                    icon: 'ios-search'
                  },
                  on: {
                    click: () => {
                      this.$router.push({name: 'notice/notice_detail', params: {id: params.row.id}})
                    }
                  }
                }),
                h('div', {
                  slot: 'content'
                }, [
                  h('div', [
                    h('div', '详情')
                  ])
                ])
              ]),
              h('Tooltip', {
                props: {
                  placement: 'top'
                }
              }, [
                h('Button', {
                  props: {
                    type: 'ghost',
                    size: 'small',
                    shape: 'circle',
                    icon: 'reply'
                  },
                  style: {
                    marginLeft: '5px',
                    display: showRevoke
                  },
                  on: {
                    click: () => {
                      this.return(params.row.id)
                    }
                  }
                }),
                h('div', {
                  slot: 'content'
                }, [
                  h('div', [
                    h('div', '撤回')
                  ])
                ])
              ]),
              h('Tooltip', {
                props: {
                  placement: 'top'
                }
              }, [
                h('Button', {
                  props: {
                    type: 'ghost',
                    size: 'small',
                    shape: 'circle',
                    icon: 'compose'
                  },
                  style: {
                    marginLeft: '5px',
                    display: showEdit
                  },
                  on: {
                    click: () => {
                      this.$router.push({name: 'notice/notice_edit', params: {id: params.row.id}})
                    }
                  }
                }),
                h('div', {
                  slot: 'content'
                }, [
                  h('div', [
                    h('div', '修改')
                  ])
                ])
              ]),
              h('Tooltip', {
                props: {
                  placement: 'top'
                }
              }, [
                h('Button', {
                  props: {
                    type: 'ghost',
                    size: 'small',
                    shape: 'circle',
                    icon: 'android-done'
                  },
                  style: {
                    marginLeft: '5px',
                    display: showSend
                  },
                  on: {
                    click: () => {
                      this.send(params.row.id)
                    }
                  }
                }),
                h('div', {
                  slot: 'content'
                }, [
                  h('div', [
                    h('div', '发布')
                  ])
                ])
              ])
            ])
          }
        }
      ]
    }
  },
  created: function () {
    this.getTableDatas(this.queyParams)
  },
  methods: {
    // 整理详情
    zhenglixiangqing () {
      this.detailList = []
      if (this.tableDatas !== null && this.tableDatas.length > 0) {
        for (var i = 0; i < this.tableDatas.length; i++) {
          var a = this.tableDatas[i].content
          var details = a.replace(/<.*?>/ig, '')
          this.detailList.push({
            split: [
              details.substring(0, 30),
              details.substring(30, 60),
              details.substring(60, 90),
              details.substring(90, 120),
              details.substring(120, 150),
              details.substring(150, 180),
              details.substring(180, 210),
              details.substring(210, 240),
              details.substring(240, 270),
              details.substring(270, 300)
            ]
          })
        }
      }
    },
    // 新增
    add () {
      this.$router.push({path: '/notice_add'})
    },
    // 查询按钮
    query () {
      this.$refs.tablePage.currentPage = 1
      this.queyParams.page = 0
      this.queyParams.row = 10
      this.getTableDatas(this.queyParams)
    },
    // 分页查询公用方法
    getTableDatas: function (params) {
      let vm = this
      vm.$Loading.start()
      findNoticetPage(params).then((res) => {
        vm.$Loading.finish()
        vm.total = res.data.total
        vm.tableDatas = res.data.rows
        vm.zhenglixiangqing()
      })
    },
    // 点击分页查询
    change (page) {
      this.queyParams.page = page
      this.getTableDatas(this.queyParams)
    },
    // 改变当前显示的行数
    doPageSizeChange: function (size) {
      this.queyParams.rows = size
      this.getTableDatas(this.queyParams)
    },
    // 转换时间
    changeDate (publishtime) {
      var date = new Date(publishtime)
      var nowDate = this.formatDate(date)
      return nowDate
    },
    // 日期格式转换
    formatDate (date) {
      var y = date.getFullYear()
      var m = date.getMonth() + 1
      m = m < 10 ? '0' + m : m
      var d = date.getDate()
      d = d < 10 ? ('0' + d) : d
      var h = date.getHours()
      var minute = date.getMinutes()
      var seconds = date.getSeconds()
      minute = minute < 10 ? ('0' + minute) : minute
      seconds = seconds < 10 ? ('0' + seconds) : seconds
      var newDate = y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + seconds
      return newDate
    },
    // 转换状态
    changeStatu (publishstatus) {
      var status = ''
      if (publishstatus === 0) {
        status = '待发布'
      }
      if (publishstatus === 1) {
        status = '已发布'
      }
      if (publishstatus === 2) {
        status = '已撤回'
      }
      return status
    },
    // 发布
    send (id) {
      // 弹框
      this.$Modal.confirm({
        title: '  ',
        content: '<p>是否确认发布此条？</p>',
        onOk: () => {
          let p = {
            id: id
          }
          sendNotices(p).then((resg) => {
            if (resg.status === 200) {
              this.getTableDatas(this.queyParams)
              this.$Message.success('发布成功！')
            } else {
              this.$Message.error('发布失败！')
            }
          })
        },
        onCancel: () => {
          this.$Message.info('取消发布！')
        }
      })
    },
     // 撤回
    return (id) {
      // 弹框
      this.$Modal.confirm({
        title: '  ',
        content: '<p>是否确认撤回此条？</p>',
        onOk: () => {
          let p = {
            id: id
          }
          revokeNotices(p).then((resg) => {
            if (resg.status === 200) {
              this.getTableDatas(this.queyParams)
              this.$Message.success('撤回成功！')
            } else {
              this.$Message.error('撤回失败！')
            }
          })
        },
        onCancel: () => {
          this.$Message.info('取消撤回！')
        }
      })
    }
  }
}
</script>
<style scoped lang="less">
.filterTitle {
  font-size: 12px;
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
.index {
  padding: 2px;
  margin-top: 5px;
  margin-bottom: 10PX;
}
.pages-block {
  margin: 0px;
  overflow: hidden;
  margin-top: 10px;
  margin-bottom: 140px;
  text-align: center;
  .pages-right {
    vertical-align: middle;
  }
}
.layout {
  border: 1px solid #d7dde4;
  background: #f5f7f9;
  position: relative;
  border-radius: 4px;
  overflow: hidden;
}
.layout-breadcrumb {
  padding: 10px 15px 0;
}
.layout-content-main {
  padding: 5px;
}
.layout-content {
  min-height: 78%;
  margin-top: 0px;
  overflow: hidden;
  background: #fff;
  border-radius: 4px;
}
</style>
