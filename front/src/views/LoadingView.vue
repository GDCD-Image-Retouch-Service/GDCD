<template>
  <div
    class="loading-view blur d-flex flex-column align-items-center justify-content-center"
  >
    <game-container />
  </div>
</template>

<script setup>
import GameContainer from '@/components/atoms/game/GameContainer.vue';
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
