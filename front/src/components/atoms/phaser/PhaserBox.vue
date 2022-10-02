<template>
  <Suspense>
    <PhaserContainer />

    <template #fallback>
      <div class="placeholder">Downloading ...</div>
    </template>
  </Suspense>
</template>

<script setup>
import PhaserContainer from '@/components/atoms/phaser/PhaserContainer';
</script>

<style scoped></style>
