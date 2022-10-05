<template>
  <div
    class="error-view sub d-flex flex-column align-items-center justify-content-center"
  >
    에러페이지 지롱ㅋㅋㅋㅋ
    <icon-rank :rank="1" />
    <icon-rank :rank="2" />
    <icon-rank :rank="3" />
    <icon-rank :rank="4" />
    <icon-rank :rank="5" />
    <icon-rank :rank="6" />
    <icon-rank :rank="7" />
    <icon-rank :rank="8" />
    <icon-rank :rank="9" />
  </div>
</template>

<script setup>
import { useUserStore } from '@/stores/user.js';
import IconRank from '@/components/atoms/IconRank';
const userStore = useUserStore();

userStore.headerSetDropdown = false;
userStore.loginModal = false;
userStore.logoutModal = false;
</script>

<style scoped>
.error-view {
  padding-top: 70px;
  padding-bottom: 60px;
  height: 100vh;
  min-height: 100vh;
  background: red;
  width: 100vw;
}
</style>
