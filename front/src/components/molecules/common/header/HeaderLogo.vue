<template>
  <div class="nav-logo-wrap">
    <img src="@/assets/logo.png" class="logo" alt="logo" />
    <div style="font-weight: 700">
      <span style="color: var(--theme-color)">개떡</span><span>찰떡</span>
    </div>
  </div>
</template>

<script setup></script>

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
</style>
