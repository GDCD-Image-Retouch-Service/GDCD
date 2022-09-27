<template>
  <div class="photo-list">
    <!-- {{ userStore.myPhoto }} -->
  </div>
</template>

<script setup>
import { useUserStore } from '@/stores/user.js';

const userStore = useUserStore();

userStore.getMyPhoto();
</script>

<style scoped>
.photo-list {
  width: 100%;
  background-color: aqua;
}
</style>
