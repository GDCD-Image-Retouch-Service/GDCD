<template>
  <div v-if="!accountStore.getIsLogined" style="margin-right: var(--grid-side)">
    <img src="@/assets/sdprofile.png" alt="" class="profile-image" />
  </div>
  <div v-else class="open-options">
    <div @click="accountStore.setIsLogined(false)">로그아웃</div>
    <div @click="userStore.deleteMyInfo()">회원탈퇴</div>
  </div>
</template>

<script setup>
import { useUserStore } from '@/stores/user';
import { useAccountStore } from '@/stores/account';

const userStore = useUserStore();
const accountStore = useAccountStore();

console.log('로그인 상황: ' + accountStore.getIsLogined);
</script>

<style scoped>
.profile-image {
  width: 30px;
  height: 30px;
  object-fit: cover;
  border-radius: 30px;
}
/* deleteMyInfo */
.open-options {
  /* position: fixed;
  bottom: 0;
  z-index: 51;
  left: 0;
  width: 100%;
  height: 200px;
  border-radius: 30px 30px 0 0;
  border: 1px solid var(--instagram-grey);
  background-color: var(--light-main-color);

  display: flex;
  flex-direction: column;
  align-items: center;
  gap: var(--grid-vertical); */
}
</style>
