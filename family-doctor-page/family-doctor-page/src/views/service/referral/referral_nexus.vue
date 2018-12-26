<template>
    <div class="">
        <div class="layout-content layout-content-main">
          <div class='columnBox menu'>
              <div class='filterTitle'>
                <Input v-model="searchParams.keyWords" icon="ios-search" placeholder="请输入关键字搜索" style="width: 200px"></Input>
              </div>
            <i-button  type="primary" @click.native="query">查询</i-button>
          </div>
          <div style="background:#eee;padding: 1px;text-align:left">
            <Button-group>
              <!-- <i-button type="primary" style="margin:1px"    @click='referral_nexus_add' class=" ">新增</i-button> -->
              <i-button type="primary" style="margin:1px"  @click='breakout' class=" ">返回</i-button>
            </Button-group>
          </div>
          <Table  border  :columns="columns" :data="data"></Table>
          <div class='pages-block'>
              <div class='pages-right'>
                  <!-- <Page :total='total' :page-size='rows' @on-page-size-change='doPageSizeChange' @on-change='change' show-sizer show-total :current='page' 
                  show-sizer  show-total show-elevator></Page> -->
                  <Page heigth="200px" :total="total" :page-size="rows" @on-page-size-change="doPageSizeChange" @on-change="change" show-sizer  show-total
           show-elevator></Page>
              </div>
          </div>  
            <v-button :params="params">新增</v-button>
        </div>
    </div>
</template>
<script>
import button from '../../../components/add_btn'
import {
  findcontrastpage, deletecontrast
} from '../../../api/api'
export default {
  components: {
    'v-button': button
  },
  data () {
    return {
      params: 'referral_nexus_add',
      total: 0,
      rows: 10,
      page: 1,
      searchParams: {
        keyWords: ''
      },
      columns: [
        // {
        //   title: '区县名称',
        //   align: 'center',
        //   key: 'county_name'
        // },
        {
          title: '基层医疗卫生服务机构',
          align: 'center',
          key: 'currentorgname'
        },
        {
          title: '县级医院',
          align: 'center',
          key: 'countyorgname'
        },
        {
          title: '市级医院',
          align: 'center',
          key: 'cityorgname'
        },
        {
          title: '编辑',
          align: 'center',
          key: 'county_name',
          render: (h, params) => {
            let vm = this
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
                    icon: 'compose'
                  },
                  style: {
                    margin: '5px'
                  },
                  on: {
                    click: () => {
                      vm.$router.push({ name: 'referral/nexus/add', params: {id: params.row.id} })
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
                    icon: 'trash-a'
                  },
                  style: {
                    margin: '5px'
                  },
                  on: {
                    click: () => {
                      this.remove(params.row.id)
                    }
                  }
                }),
                h('div', {
                  slot: 'content'
                }, [
                  h('div', [
                    h('div', '删除')
                  ])
                ])
              ])
            ])
          }
        }
      ],
      data: [
      ]
    }
  },
  created: function () {
    let params = {
      page: 1,
      rows: this.rows,
      order: 'create_date',
      searchParams: this.searchParams
    }
    findcontrastpage(params).then((res) => {
      if (res.status === 200) {
        this.total = res.data.total
        this.data = res.data.rows
      }
    })
  },
  methods: {
    remove (id) {
      var ths = this
      this.$Modal.confirm({
        title: '转诊对照信息',
        content: `是否删除此记录`,
        onOk: function () {
          ths.$Loading.start()
          let param = {
            id: id
          }
          deletecontrast(param).then((res) => {
            if (res.status === 200) {
              ths.$Loading.start()
              ths.$Message.info('删除成功！')
              // 成功之后重新差一次
              ths.pagefind()
            }
          })
        }
      })
    },
    // 切换每页显示多少条
    doPageSizeChange: function (size) {
      this.rows = size
      this.page = 1
      this.pagefind()
    },
    // 接口方法
    pagefind () {
      let params = {
        page: this.page,
        rows: this.rows,
        order: 'create_date',
        searchParams: this.searchParams
      }
      findcontrastpage(params).then((res) => {
        if (res.status === 200) {
          this.total = res.data.total
          this.data = res.data.rows
        }
      })
    },
    // 点击分页页码
    change (page) {
      this.page = page
      this.pagefind()
    },
    breakout () {
      this.$router.go(-1)
    },
    referral_nexus_add () {
      this.$router.push({ path: '../referral_nexus_add' })
    },
    query () {
      let params = {
        searchParams: this.searchParams
      }
      findcontrastpage(params).then((res) => {
        if (res.status === 200) {
        }
      })
    }
  }
}
</script>
<style scoped lang="less">
.pages-block {
      margin: 0px;
      overflow: hidden;
      margin-top: 10px;
      margin-left: 1px;
      margin-bottom: 10px;
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
.margin_top12 {
    margin-top:15px;
}
.margin_left1000 {
    margin-left:1000px;
}
.layout-breadcrumb {
  padding: 10px 15px 0;
}
/*style="height:90%; overflow:auto;"  */
.layout-content {
  height: 85%;
  margin-top: 15px;
  overflow: auto;
  background: #fff;
  border-radius: 4px;
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
.layout-content-main {
  padding: 10px;
}
.show_left {
  text-align: left
}
.layout-copy {
  text-align: center;
  padding: 10px 0 20px;
  color: #9ea7b4;
}
</style>
