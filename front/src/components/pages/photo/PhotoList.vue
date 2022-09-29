<template>
  <div class="photo-list">
    <photo-section />
  </div>
</template>

<script setup>
import PhotoSection from '@/components/organisms/photo/PhotoSection.vue';
</script>

<style scoped>
.photo-list {
  width: calc(100% - 2 * var(--grid-side));
  margin-left: var(--grid-side);
  margin-top: var(--grid-vertical);
}
</style>
