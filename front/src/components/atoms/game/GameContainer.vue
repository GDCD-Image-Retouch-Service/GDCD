<template>
  <div
    class="game-container sub d-flex flex-column align-items-center justify-content-center"
  >
    <Suspense>
      <PhaserContainer />

      <template #fallback>
        <div>Downloading ...</div>
      </template>
    </Suspense>
  </div>
</template>

<script setup>
import PhaserContainer from '@/components/atoms/game/PhaserContainer.vue';
</script>

<style scoped>
.game-container {
  position: fixed;
  z-index: 9999;
  top: 0px;
  width: 100vw;
  height: 100vh;
  background: transparent;
}
</style>
