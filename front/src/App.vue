<template>
  <div>
    <header-nav />
    <router-view />
    <footer-nav />
  </div>
</template>

<script>
import HeaderNav from '@/components/organisms/common/HeaderNav.vue';
import FooterNav from '@/components/organisms/common/FooterNav.vue';

export default {
  components: {
    HeaderNav,
    FooterNav,
  },
};
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;400;700&display=swap');

:root {
  /* 비율 */
  --size-h-spacer: 20px;
  --size-h-header: 70px;
  --size-h-footer: 60px;
  --size-w-footer: 400px;
  --size-radius: 20px;

  /* 색상 */
  /* 테마 색 */
  --theme-color: #ffe49c;

  /* 다크모드 */
  --dark-main-color: #404040;
  --dark-sub-color: #000000;
  --dark-reverser-color: #f4f4f4;

  /* 라이트 모드 */
  --light-main-color: #ffffff;
  --light-sub-color: #f4f4f4;
  --light-reverse-color: #404040;

  /* 자주 사용 */
  --black: #3c3c3a;
  --instagram-grey: #dbdbdb;
  --instagram-dark-grey: #828282;
  --delete-btn: #8898a8;
  --grid-header: 30px;
  --grid-vertical: 30px;
  --grid-side: 20px;
}

* {
  margin: 0;
  padding: 0;
  overflow-x: hidden;
  box-sizing: border-box;

  -ms-overflow-style: none;
  scrollbar-width: none;
}

*::-webkit-scrollbar,
body::-webkit-scrollbar {
  display: none;
}

#app {
  height: calc(
    100vh - calc(var(--size-h-header) + var(--grid-header)) -
      calc(var(--size-h-footer) + var(--grid-header)) var(--grid-side)
  );
  width: calc(100vw - 2 * var(--grid-side));
  max-height: 100vh;
  max-width: 100vw;

  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;

  /* 폰트 */
  color: var(--black);
  line-height: 16px;
  font-size: 16px;
  font-family: 'Noto Sans KR', sans-serif;

  scroll-behavior: smooth;

  /* 그리드 */
  margin: calc(var(--size-h-header) + var(--grid-header)) var(--grid-side)
    calc(var(--size-h-footer) + var(--grid-header)) var(--grid-side);

  /* 배경 */
  /* background-color: #e654d2; */
}

/* @media (min-width: 820px) {
  :root {
    --grid-side: 484px;
  }
} */

/* 대각선 방향 그림자인데 괜찮아보여서 일단 써봄 */
.outer {
  box-shadow: 4px 4px 10px -1px rgba(0, 0, 0, 0.12),
    -4px -4px 4px -1px rgba(255, 255, 255, 0.25);
}

.inner {
  box-shadow: inset 4px 4px 10px -1px rgba(0, 0, 0, 0.25),
    inset -4px -4px 10px -1px rgba(255, 255, 255, 0.25);
}
.common-image {
  box-shadow: 0 5px 6px #00000026;
  border-radius: 10px;
}
.spacer {
  height: var(--size-h-spacer);
}

a {
  color: #404040;
  text-decoration: none;
}
</style>
