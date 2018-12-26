<template>
  <div class="container">
    <h1>TreeGrid表格树</h1>
    <div class="panel panel-default">
      <div class="panel-heading">
        <h2 class="panel-title">普通示例：</h2>
      </div>
      <div class="panel-body">
        <Grid></Grid>
      </div>
    </div>

    <div class="panel panel-default">
      <div class="panel-heading">
        <h2 class="panel-title">异步示例</h2>
      </div>
      <div class="panel-body">
        <Asyngrid></Asyngrid>
      </div>
    </div>
  </div>
</template>
<script>
  import Grid from '../../views/grid/grid.vue'
  import Asyngrid from '../../views/grid/asyngrid.vue'
  import Test from '../../framework/components/spiner/spiner.vue'
  export default {
    components: {
      Grid,
      Asyngrid,
      Test
    }
  }
</script>
