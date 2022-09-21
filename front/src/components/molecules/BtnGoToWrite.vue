<template>
  <router-link
    to="community/write"
    class="btn-scroll-to-top outer d-flex align-items-center justify-content-center"
  >
    <i class="bi bi-pencil-square"></i>
  </router-link>
</template>

<script setup></script>

<style scoped>
.btn-scroll-to-top {
  z-index: 1;
  position: fixed;
  right: 10px;
  bottom: 48px;
  width: 50px;
  height: 50px;
  border-radius: 50%;
  background: var(--theme-color);
  color: black;
  font-size: 16pt;
}
</style>
