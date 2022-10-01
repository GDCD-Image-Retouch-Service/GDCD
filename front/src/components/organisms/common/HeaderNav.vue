<template>
  <div class="header-nav main outer d-flex align-items-center">
    <header-logo />
    <div class="flex-grow-1"></div>

    <header-profile />
  </div>
</template>

<script setup>
import HeaderLogo from '@/components/molecules/common/HeaderLogo.vue';
import HeaderProfile from '@/components/molecules/common/HeaderProfile.vue';

// import { decodeCredential } from 'vue3-google-login';
// import { user } from '@/api/rest';
// import { useAccountStore } from '@/stores/account';

// const accountStore = useAccountStore();

// const callback = async (response) => {
//   const userData = await decodeCredential(response.credential);
//   console.log(userData);
//   await user.login({
//     email: userData.email,
//     nickname: userData.name,
//   });
// };
</script>

<style scoped>
.header-nav {
  position: fixed;
  z-index: 5001;
  width: 100vw;
  height: var(--size-h-header);
  min-height: var(--size-h-header);
  /* display: flex;
  align-items: center;
  justify-content: space-between; */
}
</style>
