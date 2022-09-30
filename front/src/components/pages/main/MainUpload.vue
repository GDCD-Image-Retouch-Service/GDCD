<template>
  <div
    class="main-upload d-flex flex-column align-items-center justify-content-center"
  >
    <upload-card />
  </div>
</template>

<script setup>
import uploadCard from '@/components/organisms/main/UploadCard.vue';
</script>

<style scoped>
.main-upload {
  height: 100%;
  width: 100%;
  padding: 4px;
  overflow-y: auto;
}
</style>
