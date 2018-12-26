<template>
  <div class="body">
  <Navigation></Navigation>
  <Sidemenu></Sidemenu>
  <MainCont></MainCont>
  </div>
</template>

<script>
import Navigation from '../../views/shortMessage/layout/navigation.vue'
import Sidemenu from '../../views/shortMessage/layout/sidemenu.vue'
import MainCont from '../../views/shortMessage/layout/mainContent.vue'
export default {
  components: {
    Navigation,
    Sidemenu,
    MainCont
  },
  name: 'hello',
  data () {
    return {
    }
  }
}
</script>
<style scoped>
.body{
}
</style>
