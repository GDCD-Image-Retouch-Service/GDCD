<template>
  <div class="profile-header">
    <div class="profile-header-wrap">
      <img
        :src="userStore.currentUserd.item.profile"
        alt=""
        class="profile-image"
      />
      <profile-header-info />
    </div>
  </div>
</template>

<script setup>
import ProfileHeaderInfo from '@/components/molecules/profile/ProfileHeaderInfo.vue';
import { useUserStore } from '@/stores/user.js';

const userStore = useUserStore();
</script>

<style scoped>
.profile-header {
  width: 100%;
  display: flex;
  justify-content: center;
  padding-bottom: var(--grid-vertical);
  border-bottom: 1px solid var(--instagram-dark-grey);
}
.profile-header-wrap {
  width: 100%;
  max-width: 400px;
  display: flex;
  justify-content: start;
  gap: 20px;
}
.profile-image {
  width: 80px;
  height: 80px;
  object-fit: cover;
  border-radius: 80px;
}
</style>
