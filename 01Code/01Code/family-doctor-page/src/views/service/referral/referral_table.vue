<template>
  <div class='index'>
    <Table   :context="self" :data="data7"  :columns="columns8" stripe border></Table>
    <br>
    <div class='pages-block'>
        <div class='pages-right'>
            <Page :total='total' :page-size='rows' @on-page-size-change='doPageSizeChange' @on-change='change' show-sizer show-total :current='page' show-elevator></Page>
        </div>
    </div>
    <!--修改状态-->
    <Modal v-model="updateModel" title="状态修改" class="update-model" @on-ok="save">
      <Form :label-width="80">
        <Form-item label="转诊状态" prop="doctoridentity">
          <Select v-model="updatadata.status" style='width:200px' placeholder="请选择转诊状态" :label-in-value="true">
            <Option v-for="item in signType" :value="item.value" >{{ item.label }}</Option>
          </Select>
        </Form-item>
      </Form>
    </Modal>
  </div>
</template>
<script>
import {
  findtransferpage
  , updatatransferbykey
} from '../../../api/api'
export default {
  props: [
    'searchInfo'
  ],
  data () {
    return {
      self: this,
      selections: [],
      updateModel: false,
      total: 1,
      page: 1,
      rows: 1,
      // 查询条件的存放
      searchInfos: {
        // 开始时间
        beginDate: '',
        // 结束时间
        endDate: '',
        // 最近
        recently: '',
        // 状态
        signType: '',
        // 关键字
        keyWords: '',
        // 排序
        sort: ''
      },
      // 修改的model记录
      updatadata: {
        id: '',
        status: ''
      },
      signType: [],
      zhuangtai: [
        {
          value: '0',
          label: '取消'
        },
        {
          value: '1',
          label: '转入成功'
        },
        {
          value: '2',
          label: '转出成功'
        },
        {
          value: '3',
          label: '待接收'
        },
        {
          value: '4',
          label: '未接收'
        }
      ],
      columns8: [
        {
          title: '操作',
          width: 80,
          align: 'center',
          render: (h, params) => {
            return h('div', [
              h('Icon', {
                style: {
                  textalign: 'center'
                },
                size: '500px',
                props: {
                  type: 'compose'
                },
                nativeOn: {
                  click: () => {
                    this.edit(params.index)
                  }
                }
              })
            ])
            // const row = params.row
            // // const displaytype = row.type !== 0 ? 'none' : ''
            // var displaytype = ''
            // return h('div', [
            //   h('Button', {
            //     props: {
            //       type: 'primary',
            //       size: 'small'
            //     },
            //     style: {
            //       marginRight: '5px',
            //       display: displaytype
            //     },
            //     on: {
            //       click: () => {
            //         this.changetype('1', row)
            //       }
            //     }
            //   }, '成功'),
            //   h('Button', {
            //     props: {
            //       type: 'primary',
            //       size: 'small'
            //     },
            //     style: {
            //       marginRight: '5px',
            //       display: displaytype
            //     },
            //     on: {
            //       click: () => {
            //         this.changetype('2', row)
            //       }
            //     }
            //   }, '失败'),
            //   h('Button', {
            //     props: {
            //       type: 'primary',
            //       size: 'small'
            //     },
            //     style: {
            //       display: displaytype
            //     },
            //     on: {
            //       click: () => {
            //         this.changetype('3', row)
            //       }
            //     }
            //   }, '取消')
            // ])
          }
        },
        {
          title: '状态',
          width: 150,
          align: 'center',
          key: 'status',
          render: (h, params) => {
            let row = params.row
            let text = row.status === 0 ? '取消' : row.status === 1 ? '转入成功' : row.status === 2 ? '转出成功' : row.status === 3 ? '待接收' : row.status === 4 ? '未接收' : ''
            return h('span', {
              props: {
                type: 'text',
                // color: butcolor,
                style: {
                }
              }
            }, text)
          }
        },
        {
          title: '转诊日期',
          width: 150,
          align: 'center',
          key: 'createdate'
        },
        {
          title: '转诊编号',
          width: 150,
          align: 'center',
          key: 'transfernumber'
        },
        {
          title: '患者姓名',
          width: 150,
          align: 'center',
          key: 'residentname'
        },
        {
          title: '性别',
          key: 'sex',
          align: 'center',
          width: 150,
          render: (h, params) => {
            // 0未知的性别,1男性,2女性,9未说明的性别',
            var sex = params.row.six
            let text = sex === 0 ? '未知性别' : sex === 1 ? '男' : sex === 2 ? '女' : sex === 9 ? '未说明性别' : ''
            return h('span', {
              props: {
                type: 'text'
              }
            }, text)
          }
        },
        {
          title: '身份证号',
          width: 150,
          align: 'center',
          key: 'identitynumber'
        },
        {
          title: '联系电话',
          width: 150,
          align: 'center',
          key: 'linkmanphone'
        },
        {
          title: '转入机构',
          key: 'abutmentorgname',
          align: 'center',
          width: 150
        },
        {
          title: '转出机构',
          key: 'currentorgname',
          align: 'center',
          width: 150
        }
      ],
      data7: [
      ]
    }
  },
  watch: {
    searchInfo: function () {
      // console.log(this.searchInfo)
      this.searchInfos.beginDate = this.searchInfo[0].beginDate
      this.searchInfos.endDate = this.searchInfo[0].endDate
      this.searchInfos.recently = this.searchInfo[0].recently
      this.searchInfos.keyWords = this.searchInfo[0].keyWords
      this.searchInfos.sort = this.searchInfo[0].sort
      this.searchInfos.signType = this.searchInfo[0].signType
      let param = {
        page: 1,
        rows: this.rows,
        order: 'create_date',
        // 排序
        sort: this.searchInfos.sort,
        searchParams: this.searchInfos
      }
      findtransferpage(param).then((res) => {
        if (res.status === 200) {
          // this.total = res.total
          // this.data7 = res.data
          // console.log(res.data)
        }
      })
    }
  },
  created: function () {
  },
  methods: {
    doPageSizeChange: function (size) {
      this.rows = size
      let para = {
        page: 1,
        rows: this.rows,
        order: 'create_date',
        // 排序
        sort: this.searchInfos.sort,
        searchParams: this.searchInfos
      }
      this.$Loading.start()
      findtransferpage(para).then((res) => {
        this.$Loading.finish()
        // this.total = res.data.total
        // this.page = res.data.pageNum
        // this.listData = res.data.rows
      })
    },
    edit (index) {
      this.signType = this.zhuangtai
      this.updateModel = true
      this.updatadata.id = this.listData.id
    },
    save () {
      // console.log(this.updatadata)
    },
    // 点击页码事件
    change (page) {
      this.page = page
      let para = {
        page: page,
        rows: this.rows,
        order: 'create_date',
        // 排序
        sort: this.searchInfos.sort,
        searchParams: this.searchInfos
      }
      this.$Loading.start()
      findtransferpage(para).then((res) => {
        this.$Loading.finish()
        // this.total = res.data.total
        // this.listData = res.data.rows
      })
    },
    // 操作列按钮事件
    changetype (type, row) {
      // updatatransferbykey
      let param = {
        id: row.id,
        status: type
      }
      updatatransferbykey(param).then((res) => {
        if (res.status === 200) {
          // 调用页码时间并调用当前页
          this.change(this.page)
        }
      })
      this.$Loading.start()
      row.type = type
      this.$Loading.finish()
    }
  }
}
</script>

<style scoped lang="less">
.index {
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
  .success-row td{
      background-color: lightgray;
      color: red;
  }
  .invalid-row td{
    background-color: mistyrose;
    color: gray;
  }
}

</style>

