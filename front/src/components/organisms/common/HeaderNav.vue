<template>
  <div class="header-nav outer">
    <header-logo />

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
  // decodeCredential will retrive the JWT payload from the credential
  const userData = await decodeCredential(response.credential);
  // console.log('Handle the userData', userData);
  console.log(user.login({ email: userData.email, nickname: userData.name }));
};

// const callback = (response) => {
//   console.log('Handle the response', response);
// };
</script>

<style scoped>
.header-nav {
  width: 100vw;
  height: var(--size-h-header);
  min-height: var(--size-h-header);
  background: white;

  display: flex;
  align-items: center;
  justify-content: space-between;
}
</style>
