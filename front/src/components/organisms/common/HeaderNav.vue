<template>
  <div class="header-nav main outer">
    <router-link to="/main" class="header-logo">
      <header-logo />
    </router-link>

    <header-profile />
  </div>
</template>

<script setup>
import HeaderLogo from '@/components/molecules/common/header/HeaderLogo.vue';
import HeaderProfile from '@/components/molecules/common/header/HeaderProfile.vue';
</script>

<style scoped>
.header-nav {
  position: fixed;
  z-index: 5;
  width: 100vw;
  height: var(--size-h-header);
  min-height: var(--size-h-header);
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.header-logo {
  color: var(--black);
  text-decoration: none;
}
</style>
