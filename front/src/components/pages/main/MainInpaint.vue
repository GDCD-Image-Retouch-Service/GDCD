<template>
  <div
    class="main-inpaint d-flex flex-column align-items-center justify-content-center"
  >
    <inpaint-card />
  </div>
</template>

<script setup>
import InpaintCard from '@/components/organisms/main/InpaintCard.vue';
</script>

<style scoped>
.main-inpaint {
  width: 100%;
  height: 100%;
  min-height: calc(100vh - 96px);
  padding: 4px;
  overflow-y: auto;
}
</style>
