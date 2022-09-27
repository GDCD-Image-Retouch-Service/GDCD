<template>
  <nav class="footer-nav">
    <div class="footer-nav-wrap outer d-flex align-items-center">
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
      <div
        @click="
          router.push({ name: 'ProfilePost', params: { userId: 100000 } }),
            (basicStore.isActiveFooter = 2)
        "
      >
        <i
          class="bi bi-person-fill"
          v-if="basicStore.isActiveFooter == 2"
          style="font-size: 24px"
        ></i>
        <i class="bi bi-person" v-else style="font-size: 24px"></i>
      </div>

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
  width: 100vw;
  height: var(--size-h-footer);
  min-height: var(--size-h-footer);
  overflow: hidden;
  background-color: #ffffff;
  border-top: 1px solid var(--instagram-grey);
}
.footer-nav-wrap {
  height: var(--size-h-footer);
  justify-content: space-around;
}
.footer-nav-wrap a {
  color: var(--dark-main-color);
  font-size: 24px;
}
</style>
