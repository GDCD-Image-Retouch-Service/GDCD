<template>
  <nav class="footer-nav">
    <router-link to="/"> 홈 </router-link> |
    <router-link to="/community"> 커뮤니티 </router-link> |
    <router-link to="/community"> 마이페이지 </router-link> |
    <router-link to="/community"> 사진첩 </router-link>
  </nav>
</template>

<style scoped>
.footer-nav {
  position: fixed;
  bottom: 0;

  width: 100vw;
  height: var(--size-h-header);
  background: white;

  line-height: var(--size-h-header);
}
</style>
