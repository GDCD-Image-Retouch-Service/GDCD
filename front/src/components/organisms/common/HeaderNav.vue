<template>
  <div class="header-nav outer d-flex align-items-center">
    <header-logo />
    <div class="flex-grow-1"></div>

    <Popper arrow>
      <header-profile />
      <template #content>
        <GoogleLogin :callback="callback" />
      </template>
    </Popper>
  </div>
</template>

<script setup>
import HeaderLogo from '@/components/molecules/common/HeaderLogo.vue';
import HeaderProfile from '@/components/molecules/common/HeaderProfile.vue';

import { decodeCredential } from 'vue3-google-login';
import { user } from '@/api/rest';

const callback = async (response) => {
  const userData = await decodeCredential(response.credential);
  await user.login({
    email: userData.email,
    nickname: userData.name,
  });
};
</script>

<style scoped>
.header-nav {
  width: 100vw;
  height: var(--size-h-header);
  min-height: var(--size-h-header);
  background: white;
  overflow: hidden;

  /* display: flex;
  align-items: center;
  justify-content: space-between; */
}
</style>
