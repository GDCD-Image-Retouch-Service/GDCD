<template>
  <div class="main-view sub d-flex flex-column align-items-center main">
    <router-view />
    <main-manual />
  </div>
</template>

<script setup>
import MainManual from '@/components/organisms/main/MainManual.vue';

import { useUserStore } from '@/stores/user.js';
const userStore = useUserStore();

userStore.headerSetDropdown = false;
userStore.loginModal = false;
userStore.logoutModal = false;
</script>

<style scoped>
.main-view {
  width: 100vw;
  min-height: 100%;
}
</style>
