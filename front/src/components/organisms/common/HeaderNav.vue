<template>
  <div class="header-nav outer">
    <header-logo />

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
  z-index: 5001;
  width: 100vw;
  height: var(--size-h-header);
  min-height: var(--size-h-header);
  background: white;
  overflow: hidden;

  display: flex;
  align-items: center;
  justify-content: space-between;
}
</style>
