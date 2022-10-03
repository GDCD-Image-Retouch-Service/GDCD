<template>
  <div class="main-view sub d-flex flex-column align-items-center main">
    <router-view />
    <main-manual-vue />
  </div>
</template>

<script setup>
import MainManualVue from '@/components/organisms/main/MainManual.vue';
</script>

<style scoped>
.main-view {
  width: 100vw;
  min-height: 100%;
}
</style>
