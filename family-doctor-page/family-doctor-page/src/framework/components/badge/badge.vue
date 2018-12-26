<template>
  <div class="climb-badge-wrapper" :class="classes" :style="{width: width+'px', height: height+'px'}">
    <div class="climb-badge-main">
      <slot>

      </slot>
    </div>
    <div class="climb-badge-top">
      <slot name="top">

      </slot>
    </div>
    <div class="climb-badge-left">
      <slot name="left">

      </slot>
    </div>
    <div class="climb-badge-right">
      <slot name="right">

      </slot>
    </div>
    <div class="climb-badge-bottom">
      <slot name="bottom">
      </slot>
    </div>
  </div>
</template>

<script>
  export default {
    props: {
      height: {
        type: Number,
        default: 80
      },
      width: {
        type: Number,
        default: 80
      },
      circle: {
        type: Boolean,
        default: true
      },
      hide: {
        type: Boolean,
        default: true
      }
    },
    computed: {
      classes: function () {
        return [
          {
            'circle': this.circle,
            'hide': this.hide
          }
        ]
      }
    }
  }

</script>
