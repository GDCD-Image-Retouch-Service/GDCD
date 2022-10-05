<template>
  <div class="main-view sub d-flex flex-column align-items-center main">
    <router-view class="flex-grow-1" />
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
  padding-top: 70px;
  padding-bottom: 60px;
  height: 100vh;
  width: 100vw;
  overflow-y: scroll;
}
</style>
