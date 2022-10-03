<template>
  <div class="header-profile">
    <Popper arrow>
      <div v-if="!accountStore.getIsLogined">
        <i class="bi bi-person-circle"></i>
      </div>
      <div v-else><i class="bi bi-person-circle"></i></div>
      <template #content="{ close }">
        <div @click="close"><i class="bi bi-x-circle"></i></div>
        <div @click="mainStore.setIsDarkToggle">색바꾸기</div>
        <div v-if="!accountStore.getIsLogined">
          <GoogleLogin :callback="callback" />
        </div>
        <div v-else @click="user.logout()">(로그아웃)</div>
      </template>
    </Popper>
  </div>
</template>

<script setup>
import { useAccountStore, useMainStore } from '@/stores';
import { decodeCredential } from 'vue3-google-login';
import { user } from '@/api/rest';

const accountStore = useAccountStore();
const mainStore = useMainStore();

const callback = async (response) => {
  const userData = await decodeCredential(response.credential);
  console.log(userData);
  await user.login({
    email: userData.email,
    nickname: userData.name,
  });
};
</script>

<style scoped>
.header-profile {
  margin-right: var(--grid-side);
}
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
