<template>
  <div
    class="main-result d-flex flex-wrap align-items-center justify-content-center"
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
  padding: 8px;
  overflow-y: auto;
}
</style>
