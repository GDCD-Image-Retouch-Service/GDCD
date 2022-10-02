<template>
  <Suspense>
    <PhaserContainer />

    <template #fallback>
      <div class="placeholder">Downloading ...</div>
    </template>
  </Suspense>
</template>

<script setup>
import PhaserContainer from '@/components/atoms/game/PhaserContainer.vue';
</script>

<style scoped>
.placeholder {
  font-size: 2rem;
  font-family: 'Courier New', Courier, monospace;
}
</style>
