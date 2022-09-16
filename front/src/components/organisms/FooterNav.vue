<template>
  <!-- <div class="footer-spacer" /> -->
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
.footer-spacer {
  width: 100vw;
  height: var(--size-h-footer);
}
.footer-nav-wrap {
  width: 80%;
  height: var(--size-h-footer);

  display: flex;
  margin: 0 auto;
  justify-content: space-around;
}
.footer-nav-wrap a {
  color: var(--dark-main-color);
  font-size: 24px;
}
.footer-nav {
  z-index: 100;
  position: fixed;
  bottom: 0px;

  width: 100vw;
  height: var(--size-h-footer);
}

.footer-nav-wrap {
  width: 100%;
  max-width: 380px;
  height: var(--size-h-footer);
  background: white;
  display: flex;
  margin: 0 auto;
  justify-content: space-around;
  border-top-left-radius: calc(var(--size-h-footer) / 2);
  border-top-right-radius: calc(var(--size-h-footer) / 2);
  border: solid 1px #f4f4f4;
}
.footer-nav-wrap a {
  color: var(--dark-main-color);
  font-size: 24px;
}
.footer-nav {
  z-index: 100;
  position: fixed;

  bottom: 0px;

  width: 100%;
  height: var(--size-h-footer);
}
</style>
