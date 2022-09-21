<template>
  <masonry-layout />
</template>

<script setup>
import MasonryLayout from '@/components/organisms/MasonryLayout.vue';
import { useUserStore } from '@/stores/user.js';

const userStore = useUserStore();

userStore.isItemActive = 0;
</script>

<style></style>
