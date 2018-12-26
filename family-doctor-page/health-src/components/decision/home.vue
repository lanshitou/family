<template>
  <div class="body">
  <Navigation></Navigation>
  <Sidemenu></Sidemenu>
  <MainCont></MainCont>
  </div>
</template>

<script>
import Navigation from '../../views/decision/layout/navigation.vue'
import Sidemenu from '../../views/decision/layout/sidemenu.vue'
import MainCont from '../../views/decision/layout/mainContent.vue'
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
  margin:0px;
  padding:0px;
  clear:both;
}
</style>
