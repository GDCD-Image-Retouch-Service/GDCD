<template>
  <div class="nav-logo-wrap">
    <img :src="logoSrc" class="logo" alt="logo" />
    <div class="nav-logo-title">
      <span class="nav-logo-title-gd">개떡</span><span>찰떡</span>
    </div>
  </div>
</template>

<script setup>
const logoSrc = require('@/assets/logo.png');
</script>

<style scoped>
.nav-logo-wrap {
  display: flex;
  gap: 10px;
  align-items: center;
  margin-left: var(--grid-side);
}
.logo {
  height: 30px;
}
.nav-logo-title {
  font-weight: 700;
  display: flex;
}
.nav-logo-title-gd {
  color: var(--theme-color);
}
</style>
