<template>
  <div :id="containerId" />
</template>

<script setup>
import { onMounted, onUnmounted } from 'vue';

let gameInstance = null;
const containerId = 'game-container';
const { createLoadingGame } = await import('@/api/phaser');

onMounted(() => {
  gameInstance = createLoadingGame(containerId);
});

onUnmounted(() => {
  gameInstance.destroy(false);
});
</script>

<style scoped></style>
