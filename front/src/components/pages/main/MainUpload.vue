<template>
  <div
    class="main-upload d-flex flex-column align-items-center justify-content-center"
  >
    <router-link to="main/test">test</router-link>
    <router-link to="main/image">image</router-link>
    <upload-card />
  </div>
</template>

<script setup>
import uploadCard from '@/components/organisms/main/UploadCard.vue';
</script>

<style scoped>
.main-upload {
  width: 100vw;
}
</style>
