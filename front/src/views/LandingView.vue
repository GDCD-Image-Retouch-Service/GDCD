<template>
  <div
    class="landing-view blur position-fixed d-flex flex-column align-items-center justify-content-center"
    ref="something"
  >
    <game-container />
  </div>
</template>

<script setup>
import GameContainer from '@/components/atoms/game/GameContainer.vue';
import { onMounted, ref } from 'vue';
import { useRouter } from 'vue-router';
import { useUserStore } from '@/stores/user.js';
const userStore = useUserStore();
// Init
const router = useRouter();

// Data
const something = ref(null);

onMounted(() => {
  setTimeout(function () {
    router.push('main');
  }, 5000);
});
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
}
</style>
