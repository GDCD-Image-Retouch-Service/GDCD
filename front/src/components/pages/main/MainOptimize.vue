<template>
  <div
    class="main-optimize d-flex flex-column align-items-center justify-content-center"
  >
    <optimize-card />
  </div>
</template>

<script setup>
import OptimizeCard from '@/components/organisms/main/OptimizeCard.vue';
</script>

<style scoped>
.main-optimize {
  width: 100%;
  height: 100%;
  min-height: calc(100vh - 96px);
  padding: 4px;
  overflow-y: auto;
}
</style>
