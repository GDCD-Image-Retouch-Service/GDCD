<template>
  <div class="profile-post">
    <masonry-layout />
  </div>
</template>

<script setup>
import MasonryLayout from '@/components/organisms/MasonryLayout.vue';
import { useUserStore } from '@/stores/user';

const userStore = useUserStore();

userStore.isItemActive = 0;
</script>

<style>
.profile-post {
  width: 100vw;
}
</style>
