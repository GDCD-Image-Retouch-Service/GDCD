<template>
  <div
    class="main-score d-flex flex-column align-items-center justify-content-center"
  >
    <score-card />
  </div>
</template>

<script setup>
import ScoreCard from '@/components/organisms/main/ScoreCard.vue';
</script>

<style scoped>
.main-score {
  width: 100%;
  height: 100%;
  overflow-y: auto;
}
</style>
