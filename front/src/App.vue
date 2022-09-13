<template>
  <header-nav />
  <router-view />
  <footer-nav />
</template>

<script>
import HeaderNav from '@/components/organisms/HeaderNav.vue';
import FooterNav from '@/components/organisms/FooterNav.vue';

export default {
  components: {
    HeaderNav,
    FooterNav,
  },
};
</script>

<style>
:root {
  /* 비율 */
  --size-h-header: 48px;
  --size-h-footer: 48px;
  --size-w-footer: 48px;

  /* 색상 */
  --theme-color: #ffe49c;

  --dark-main-color: #404040;
  --dark-sub-color: #000000;
  --dark-reverser-color: #f4f4f4;

  --light-main-color: #ffffff;
  --light-sub-color: #f4f4f4;
  --light-reverse-color: #404040;
}

* {
  margin: 0;
  padding: 0;
  overflow-x: hidden;
  box-sizing: border-box;

  -ms-overflow-style: none;
  scrollbar-width: none;
}

*::-webkit-scrollbar {
  display: none;
}

#app {
  height: 100vh;
  width: 100vw;
  max-height: 100vh;
  max-width: 100vw;

  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

/* 대각선 방향 그림자인데 괜찮아보여서 일단 써봄 */
.outer {
  box-shadow: 4px 4px 10px -1px rgba(0, 0, 0, 0.25),
    -4px -4px 10px -1px rgba(255, 255, 255, 0.25);
}

.inner {
  box-shadow: inset 4px 4px 10px -1px rgba(0, 0, 0, 0.25),
    inset -4px -4px 10px -1px rgba(255, 255, 255, 0.25);
}

.spacer {
  height: 24px;
}
</style>
