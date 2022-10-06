<template>
  <div class="app sub d-flex flex-column" :class="theme">
    <header-nav />
    <router-view class="sub" :key="$route.fullPath" />
    <footer-nav />
  </div>
</template>

<script setup>
import HeaderNav from '@/components/organisms/common/HeaderNav.vue';
import FooterNav from '@/components/organisms/common/FooterNav.vue';

import { computed } from 'vue';
import { useUserStore, useMainStore } from '@/stores';

const mainStore = useMainStore();
const userStore = useUserStore();

if (localStorage.getItem('token')) {
  userStore.getMyinfo();
}

const theme = computed(() => (mainStore.getIsDark ? 'dark' : 'light'));

// 변수 초기화

userStore.loginModal = false;
userStore.logoutModal = false;
userStore.headerSetDropdown = false;
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;400;700&display=swap');
@font-face {
  font-family: 'Nanum Gothic';
  src: url('https://fonts.googleapis.com/css?family=Nanum+Gothic:400')
    format('truetype');
}

:root {
  /* 비율 */
  --size-h-spacer: 20px;
  --size-h-header: 70px;
  --size-h-footer: 60px;
  --size-w-footer: 400px;
  --size-radius: 24px;

  --size-font: 16px;

  /* 테마 색 */
  --theme-color: #ffe49c;
  --color-theme: #ffe49c;
  --light-main-color: #ffffff;

  /* 자주 사용 */
  --black: #3c3c3a;
  --instagram-grey: #dbdbdb;
  --instagram-dark-grey: #828282;
  --delete-btn: #8898a8;
  --grid-header: 30px;
  --grid-vertical: 30px;
  --grid-side: 20px;
}

/* 라이트 & 다크 모드 */
.dark {
  --color-main: #3c3c3a;
  --color-sub: #303030;
  --color-reverse: #f4f4f4;
  --popper-theme-background-color: #333333;
  --popper-theme-background-color-hover: #333333;
  --popper-theme-text-color: white;
  --popper-theme-border-width: 0px;
  --popper-theme-border-radius: 6px;
  --popper-theme-padding: 32px;
  --popper-theme-box-shadow: 0 6px 30px -6px rgba(0, 0, 0, 0.25);
}

.dark * {
  color: var(--color-reverse);
}

.light {
  --color-main: #ffffff;
  --color-sub: #f4f4f4;
  --color-reverse: #404040;
  --popper-theme-background-color: #ffffff;
  --popper-theme-background-color-hover: #ffffff;
  --popper-theme-text-color: #333333;
  --popper-theme-border-width: 1px;
  --popper-theme-border-style: solid;
  --popper-theme-border-color: #eeeeee;
  --popper-theme-border-radius: 6px;
  --popper-theme-padding: 32px;
  --popper-theme-box-shadow: 0 6px 30px -6px rgba(0, 0, 0, 0.25);
}

.light {
  color: var(--color-reverse);
}

.main {
  background: var(--color-main);
}

.sub {
  background: var(--color-sub);
}

.reverse {
  background: var(--color-reverse);
}

/* 블러 처리 */
.blur {
  box-shadow: 0 0 1rem 0 rgba(0, 0, 0, 0.2);
  border-radius: 5px;
  -webkit-backdrop-filter: blur(5px);
  backdrop-filter: blur(5px);
  background-blend-mode: overlay;
}

.dark .blur {
  background-color: rgba(255, 255, 255, 0.15);
}

.light .blur {
  background-color: rgba(0, 0, 0, 0.15);
}

/* 그림자 */
.outer {
  box-shadow: 4px 4px 10px -1px rgba(0, 0, 0, 0.12),
    -4px -4px 4px -1px rgba(255, 255, 255, 0.12);
}

.inner {
  box-shadow: inset 4px 4px 10px -1px rgba(0, 0, 0, 0.25),
    inset -4px -4px 10px -1px rgba(255, 255, 255, 0.25);
}

/* 간격 */
.spacer {
  height: var(--size-h-spacer);
  weight: var(--size-h-spacer);
}

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;

  -ms-overflow-style: none;
  scrollbar-width: none;

  transition: background-color 0.3s linear;
}

body {
  height: 100vh;
  width: 100vw;
}

*::-webkit-scrollbar,
body::-webkit-scrollbar {
  scrollbar-width: none;
  display: none;
}

.app {
  height: 100vh;
  width: 100vw;
  max-height: 100vh;
  max-width: 100vw;

  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;

  /* 폰트 */
  color: var(--black);
  line-height: 16px;
  font-size: 16px;

  /* font-family: 'Noto Sans KR', sans-serif; */
  font-family: 'Nanum Gothic';
  scroll-behavior: smooth;
}

.app-container {
  padding-top: var(--size-h-header);
  padding-bottom: var(--size-h-footer);
}

.common-image {
  box-shadow: 0 5px 6px #00000026;
  border-radius: 10px;
}

.button {
  width: 100%;
  height: 50px;
  background-color: var(--theme-color);
  border-radius: 10px;
  border: none;
  color: var(--light-main-color);
  font-weight: 700;
}

a {
  color: #404040;
  text-decoration: none;
}

.visible {
  display: none;
}
.nonScroll {
  position: fixed;
  width: 100%;
  height: 100%;
  overflow: hidden;
}
</style>
