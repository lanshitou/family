<template>
    <div  v-if="tag" class="climb-spiner">
      <div class="spiner-cover"></div>
      <div class="spiner-cont">
        <slot>
          <i :class="[icon, rotating]" :style="{color: iconColor, fontSize: size+'px'}"></i>
        </slot>
      </div>
    </div>
    <div  v-else class="climb-spiner" v-show="show">
      <div class="spiner-cover"></div>
      <div class="spiner-cont">
        <slot>
          <i :class="[icon, rotating]" :style="{color: iconColor, fontSize: size+'px'}"></i>
        </slot>
      </div>
    </div>
</template>
<script>
  export default {
    data () {
      return {
        show: false
      }
    },
    props: {
      icon: {
        type: Array,
        default () {
          return ['fa fa-spinner fa-pulse']
        }
      },
      tag: {
        type: Boolean,
        default () {
          return false
        }
      },
      rotate: {
        type: Boolean,
        default () {
          return true
        }
      },
      iconColor: {
        type: String,
        default () {
          return '#f00'
        }
      },
      size: {
        type: Number,
        default () {
          return 36
        }
      }
    },
    computed: {
      rotating: function () {
        let vm = this
        return {
          [`climb-rotate`]: !!vm.role
        }
      }
    }
  }
</script>
