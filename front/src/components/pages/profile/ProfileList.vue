<template>
  <ProfileHeader/>
  <masonry-layout
  v-if="userStore.isItemActive === 0"
  />

</template>

<script setup>
import { useUserStore } from '@/stores/user.js'

import ProfileHeader from '@/components/organisms/ProfileHeader.vue'
import MasonryLayout from '@/components/organisms/MasonryLayout.vue';

const userStore = useUserStore();
</script>

<style></style>
