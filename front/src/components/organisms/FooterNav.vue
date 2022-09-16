<template>
  <!-- <div class="footer-spacer" /> -->
  <nav class="footer-nav outer">
    <router-link to="/"> 홈 </router-link> |
    <router-link to="/community"> 커뮤니티 </router-link> |
    <router-link to="/profile"> 마이페이지 </router-link> |
    <router-link to="/community"> 사진첩 </router-link>
  </nav>
</template>

<style scoped>
.footer-spacer {
  width: 100vw;
  height: var(--size-h-footer);
}

.footer-nav {
  z-index: 100;
  position: fixed;
  bottom: -4px;

  width: 100vw;
  height: var(--size-h-header);
  background: white;

  /* line-height: 20px; */
}
</style>
