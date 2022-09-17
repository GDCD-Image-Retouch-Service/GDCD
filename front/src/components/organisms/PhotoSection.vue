<template>
  <div class="photo-section">
    <photo-layout />
  </div>
</template>

<script setup>
import PhotoLayout from '@/components/molecules/PhotoLayout.vue';
</script>

<style scoped>
.photo-section {
  /* background-color: blue; */
  width: 100%;
  margin-bottom: var(--size-h-footer);
}
</style>
