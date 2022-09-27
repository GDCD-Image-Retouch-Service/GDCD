<template>
  <div class="photo-list">
    <date-format :updateInfo="'2022-09-18T03:12:33.844'" />
    <photo-header />
    <photo-section />
  </div>
</template>

<script setup>
import PhotoHeader from '@/components/organisms/photo/PhotoHeader.vue';
import PhotoSection from '@/components/organisms/photo/PhotoSection.vue';
import DateFormat from '@/components/molecules/common/DateFormat.vue';
</script>

<style scoped>
.photo-list {
  width: calc(100% - 2 * var(--grid-side));
  margin-left: var(--grid-side);
}
</style>
