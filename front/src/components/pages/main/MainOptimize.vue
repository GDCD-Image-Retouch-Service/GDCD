<template>
  <div
    class="main-optimize d-flex flex-wrap align-items-center justify-content-center"
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
  padding: 8px;
  overflow-y: auto;
}
</style>
