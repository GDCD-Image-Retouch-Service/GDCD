<template>
  <div class="header-profile">
    <Popper arrow>
      <div v-if="!accountStore.getIsLogined">
        <i class="bi bi-person-circle"></i>
      </div>
      <div v-else>
        <span class="material-icons-outlined"> account_circle </span>
      </div>
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
</style>
