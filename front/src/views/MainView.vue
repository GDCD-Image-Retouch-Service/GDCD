<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png">
    <main-content msg="Welcome to Your Vue.js App" />

  </div>
</template>

<script>
// @ is an alias to /src
import MainContent from '@/components/main/MainContent.vue'

export default {
  name: 'HomeView',
  components: {
    MainContent
  }
}
</script>
