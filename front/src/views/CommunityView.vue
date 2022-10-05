<template>
  <div class="community-view sub">
    <router-view />
  </div>
</template>

<script setup>
import { useUserStore } from '@/stores/user.js';
const userStore = useUserStore();

userStore.headerSetDropdown = false;
userStore.loginModal = false;
userStore.logoutModal = false;
</script>

<style scoped>
.community-view {
  margin-top: var(--size-h-header);
  margin-bottom: var(--size-h-footer);
  overflow: scroll;
}
</style>
