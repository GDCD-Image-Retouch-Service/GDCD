<template>
  <div class="photo-section">
    <photo-layout />
  </div>
</template>

<script setup>
import PhotoLayout from '@/components/molecules/photo/PhotoLayout.vue';
</script>

<style scoped>
@media (min-width: 1024px) {
  .photo-section {
    min-width: 935px;
    max-width: 935px;
  }
}

.photo-section {
  margin: 0 auto;
  width: 100%;
}
</style>
