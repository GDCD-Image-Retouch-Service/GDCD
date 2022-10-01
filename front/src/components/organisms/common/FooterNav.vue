<template>
  <nav class="footer-nav d-flex justify-content-center">
    <div class="footer-nav-wrap main outer d-flex justify-content-around">
      <router-link to="/" class="list d-flex flex-column align-items-center">
        <span class="icon"><i class="bi bi-house-door"></i></span>
        <span class="text">홈</span>
      </router-link>
      <router-link to="/">
        <span class="icon"><i class="bi bi-people"></i></span>
        <span class="text">커뮤니티</span>
      </router-link>
      <router-link to="/" class="list d-flex flex-column">
        <span class="icon"><i class="bi bi-person"></i></span>
        <span class="text">프로필</span>
      </router-link>
      <router-link to="/" class="list d-flex flex-column">
        <span class="icon"><i class="bi bi-image"></i> </span>
        <span class="text">사진첩</span>
      </router-link>
    </div>
  </nav>
</template>

<script setup>
// import { useBasicStore } from '@/stores/basic.js';
// import router from '@/router/index.js';

// const basicStore = useBasicStore();
</script>

<style scoped>
.footer-nav {
  position: fixed;
  bottom: 8px;
  z-index: 5000;
  width: 100vw;
  height: var(--size-h-footer);
  min-height: var(--size-h-footer);
}

.footer-nav-wrap {
  height: var(--size-h-footer);
  width: 100vw;
  max-width: 280px;
  border-radius: var(--size-radius);
  justify-content: space-around;
}
.footer-nav-wrap a {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  color: var(--color-reverse);
}
</style>
