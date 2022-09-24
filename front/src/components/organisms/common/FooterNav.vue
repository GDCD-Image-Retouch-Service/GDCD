<template>
  <div style="height: var(--size-h-footer)"></div>
  <nav class="footer-nav">
    <div class="footer-nav-wrap outer">
      <!--  홈 -->
      <router-link to="/" @click="basicStore.isActiveFooter = 0">
        <i
          class="bi bi-house-door-fill"
          v-if="basicStore.isActiveFooter == 0"
        ></i>
        <i class="bi bi-house-door" v-else></i>
      </router-link>

      <!-- 커뮤니티 -->
      <router-link to="/community" @click="basicStore.isActiveFooter = 1">
        <i class="bi bi-people-fill" v-if="basicStore.isActiveFooter == 1"></i>
        <i class="bi bi-people" v-else></i>
      </router-link>

      <!-- 프로필 -->
      <router-link to="/profile" @click="basicStore.isActiveFooter = 2">
        <i class="bi bi-person-fill" v-if="basicStore.isActiveFooter == 2"></i>
        <i class="bi bi-person" v-else></i>
      </router-link>

      <!-- 사진첩 -->
      <router-link to="/photo" @click="basicStore.isActiveFooter = 3">
        <i class="bi bi-file-image"></i>
      </router-link>
    </div>
  </nav>
</template>

<script setup>
import { useBasicStore } from '@/stores/basic.js';

const basicStore = useBasicStore();
</script>

<style scoped>
.footer-nav {
  z-index: 100;
  position: fixed;
  bottom: 0;
  left: 0;
  width: 100vw;
  height: var(--size-h-footer);
  background-color: #ffffff;
  border-top: 1px solid var(--instagram-grey);
}
.footer-nav-wrap {
  height: var(--size-h-footer);
  padding: 0 var(--grid-side);
  display: flex;
  margin: 0 auto;
  justify-content: space-around;
  align-items: center;
}
.footer-nav-wrap a {
  color: var(--dark-main-color);
  font-size: 24px;
}
</style>
