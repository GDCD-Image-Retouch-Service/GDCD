<template>
  <div style="height: var(--size-h-header)"></div>
  <div class="header-nav outer">
    <header-logo />
    <header-profile />
  </div>
</template>

<script setup>
import HeaderLogo from '@/components/molecules/common/HeaderLogo.vue';
import HeaderProfile from '@/components/molecules/common/HeaderProfile.vue';
</script>

<style scoped>
.header-nav {
  z-index: 100;
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: var(--size-h-header);
  background: white;

  display: flex;
  align-items: center;
  justify-content: space-between;
}
</style>
