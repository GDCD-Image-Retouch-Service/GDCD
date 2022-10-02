<template>
  <div
    class="loading-view blur d-flex flex-column align-items-center justify-content-center"
  >
    <phaser-box />
  </div>
</template>

<script setup>
import PhaserBox from '@/components/atoms/phaser/PhaserBox.vue';
</script>

<style scoped>
.loading-view {
  position: fixed;
  z-index: 9999;
  top: 0px;
  width: 100vw;
  height: 100vh;
}
</style>
