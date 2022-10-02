<template>
  <Suspense>
    <PhaserContainer />

    <template #fallback>
      <div>Downloading ...</div>
    </template>
  </Suspense>
</template>

<script setup>
import PhaserContainer from '@/components/atoms/game/PhaserContainer.vue';
</script>

<style scoped></style>
