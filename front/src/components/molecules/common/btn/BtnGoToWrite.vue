<template>
  <router-link to="community/write" class="btn-scroll-to-top common-image">
    <i class="bi bi-pencil-square"></i>
  </router-link>
</template>

<script setup></script>

<style scoped>
.btn-scroll-to-top {
  z-index: 1;
  position: fixed;
  right: var(--grid-side);
  bottom: calc(var(--size-h-footer) + var(--grid-side));
  width: 50px;
  height: 50px;
  border-radius: 50%;
  background: var(--theme-color);
  color: black;
  font-size: 16pt;

  display: flex;
  align-items: center;
  justify-content: center;
}
</style>
