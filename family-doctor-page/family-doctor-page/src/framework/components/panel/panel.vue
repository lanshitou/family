<template>
  <div class="panel panel-default">
    <div class="panel-heading">
      <slot name="header">
        <h2 class="panel-title">{{title}}</h2>
      </slot>
    </div>
    <div class="panel-body">
      <slot>

      </slot>
    </div>
  </div>
</template>

<script>
  export default {
    props: {
      title: {
        default: ''
      }
    }
  }

</script>
