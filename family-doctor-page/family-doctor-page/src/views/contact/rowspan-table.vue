<!--/**
 * @title:合并行表格组件
 * @author: ChaoLeer
 * @description: 该组件目前只实现首列合并,若有特殊定制需求,可参考逻辑实现
*/-->
<template>
  <table class="ivu-table ivu-table-border" style="width: 100%;display: table;">
      <thead>
        <tr>
          <td v-for="head in rowspanTableCol">{{head.title}}</td>
        </tr>
      </thead>
      <tbody>
        <template v-for="tr in rowspanTableData">
          <tr v-if="tr.children&&tr.children.length>0">
            <template v-for="headItem,index in rowspanTableCol">
              <td v-if="index === 0" :rowspan="tr.children.length">
                <climb-render v-if="tr[headItem.key].render" :render="tr[headItem.key].render" :row="tr" ></climb-render>
                <template v-else>{{tr[headItem.key]}}</template>
              </td>
              <td v-else>
                <climb-render v-if="tr.children[0][headItem.key].render" :render="tr.children[0][headItem.key].render" :row="tr.children[0]"></climb-render>
                <template v-else>{{tr.children[0][headItem.key]}}</template>
              </td>
            </template>
          </tr>
          <tr v-for="row,rowinx in tr.children" v-if="rowinx>0">
            <td v-for="headItem,index in rowspanTableCol" v-if="index > 0">
              <climb-render v-if="row[headItem.key].render" :render="row[headItem.key].render" :row="row"></climb-render>
              <template v-else>{{row[headItem.key]}}</template>
            </td>
          </tr>
        </template>
      </tbody>
    </table>
</template>
<script>
  export default {
    props: {
      rowspanTableCol: {
        type: Array,
        default: []
      },
      rowspanTableData: {
        type: Array,
        default: []
      }
    },
    components: {
      'climb-render': {
        functional: true,
        props: {
          render: Function,
          row: Object
        },
        render (h, ctx) {
          let params = {
            row: ctx.props.row
          }
          return ctx.props.render(h, params)
        }
      }
    }
  }
</script>
