<template>
  <div
    class="error-view sub position-fixed d-flex flex-column align-items-center justify-content-center"
  >
    에러페이지 지롱ㅋㅋㅋㅋ
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
.landing-view {
  z-index: 9999;
  top: 0px;
  width: 100vw;
  height: 100vh;
  background: var(--color-main);
}
</style>
