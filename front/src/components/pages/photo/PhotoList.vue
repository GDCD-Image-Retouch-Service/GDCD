<template>
  <div class="photo-list">
    <photo-header />
    <photo-section />
  </div>
</template>

<script setup>
import PhotoHeader from '@/components/organisms/PhotoHeader.vue';
import PhotoSection from '@/components/organisms/PhotoSection.vue';
</script>

<style scoped>
.photo-list {
  width: 100%;
}
</style>
