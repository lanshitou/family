<template>
  <div class="body">
  <Navigation></Navigation>
  <Sidemenu></Sidemenu>
  <MainCont></MainCont>
  </div>
</template>

<script>
import Navigation from '../../views/health/layout/navigation.vue'
import Sidemenu from '../../views/health/layout/sidemenu.vue'
import MainCont from '../../views/health/layout/mainContent.vue'
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
