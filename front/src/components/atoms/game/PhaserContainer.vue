<template>
  <div :id="containerId" />
</template>

<script setup>
import { onMounted, onUnmounted } from 'vue';

let gameInstance = null;
const containerId = 'game-container';
const game = await import('@/api/phaser/game');

onMounted(() => {
  gameInstance = game.launch(containerId);
});

onUnmounted(() => {
  gameInstance.destroy(false);
});
</script>

<style scoped></style>
