<template>
  <div class="header-nav main outer">
    <div class="header-nav-wrap">
      <header-logo @click="router.push({ name: 'main' })" />

      <header-profile />
    </div>
  </div>
</template>

<script setup>
import HeaderLogo from '@/components/molecules/common/header/HeaderLogo.vue';
import HeaderProfile from '@/components/molecules/common/header/HeaderProfile.vue';
import { useRouter } from 'vue-router';

const router = useRouter();
</script>

<style scoped>
@media (min-width: 1024px) {
  .header-nav-wrap {
    min-width: 975px;
    max-width: 975px;
    margin: 0 auto;
  }
}
.header-nav {
  position: fixed;
  z-index: 5;
  width: 100%;
  height: var(--size-h-header);
  min-height: var(--size-h-header);
}
.header-nav-wrap {
  width: 100%;
  height: var(--size-h-header);
  justify-content: space-between;
  align-items: center;
  display: flex;
}
.header-logo {
  color: var(--black);
  text-decoration: none;
}
</style>
