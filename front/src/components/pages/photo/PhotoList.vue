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
  width: calc(100% - 40px);
  margin-left: 20px;
  margin-top: 30px;
}
</style>
