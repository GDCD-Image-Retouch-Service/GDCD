<template>
  <div
    class="loading-view sub blur d-flex flex-column align-items-center justify-content-center"
  >
    <game-container />
  </div>
</template>

<script setup>
import { useUserStore } from '@/stores/user.js';
import GameContainer from '@/components/atoms/game/GameContainer.vue';
const userStore = useUserStore();

userStore.headerSetDropdown = false;
userStore.loginModal = false;
userStore.logoutModal = false;
</script>

<style scoped>
.loading-view {
  position: fixed;
  z-index: 9999;
  top: 0px;
  width: 100vw;
  height: 100vh;
}
</style>
