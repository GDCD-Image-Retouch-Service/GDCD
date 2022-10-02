<template>
  <nav class="footer-nav d-flex justify-content-center">
    <div class="footer-nav-wrap main outer d-flex justify-content-around">
      <router-link to="/" class="list d-flex flex-column align-items-center">
        <div
          class="icon-circle d-flex justify-content-center align-items-center"
        >
          <span class="icon"><i class="bi bi-house-door"></i></span>
        </div>
      </router-link>

      <router-link to="/">
        <div
          class="icon-circle d-flex justify-content-center align-items-center"
        >
          <span class="icon"><i class="bi bi-people"></i></span>
        </div>
      </router-link>

      <router-link to="/" class="list d-flex flex-column">
        <div
          class="icon-circle d-flex justify-content-center align-items-center"
        >
          <span class="icon"><i class="bi bi-person"></i></span>
        </div>
      </router-link>

      <router-link to="/" class="list d-flex flex-column">
        <div
          class="icon-circle d-flex justify-content-center align-items-center"
        >
          <span class="icon"><i class="bi bi-image"></i> </span>
        </div>
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
  /* background: red; */
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

.icon-circle {
  height: calc(var(--size-h-footer) - 8px);
  width: calc(var(--size-h-footer) - 8px);
  border-radius: 50%;
  overflow: hidden;
  font-size: 16pt;
  /* border: solid 4px var(--color-theme); */
}
</style>
