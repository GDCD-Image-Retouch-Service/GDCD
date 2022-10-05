<template>
  <div
    class="main-result d-flex flex-column align-items-center justify-content-center"
  >
    <result-card />
  </div>
</template>

<script setup>
import ResultCard from '@/components/organisms/main/ResultCard.vue';
</script>

<style scoped>
.main-result {
  width: 100%;
  height: 100%;
  min-height: calc(100vh - 96px);
  padding: 4px;
  overflow-y: auto;
}
</style>
