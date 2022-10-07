<template>
  <div
    class="main-upload d-flex flex-wrap align-items-center justify-content-center"
  >
    <upload-card />
  </div>
</template>

<script setup>
import uploadCard from '@/components/organisms/main/UploadCard.vue';
</script>

<style scoped>
.main-upload {
  width: 100%;
  overflow-y: auto;
}
</style>
