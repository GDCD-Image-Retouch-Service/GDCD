<template>
  <div :id="containerId" />
</template>

<script setup>
import { onMounted, onUnmounted } from 'vue';

let gameInstance = null;
const containerId = 'game-container';
const { createTitleGame } = await import('@/api/phaser');

onMounted(() => {
  gameInstance = createTitleGame(containerId);
});

onUnmounted(() => {
  gameInstance.destroy(false);
});
</script>

<style scoped></style>
