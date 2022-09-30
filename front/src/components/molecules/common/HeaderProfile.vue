<template>
  <Popper arrow>
    <div v-if="!accountStore.getIsLogined">로그인안함</div>
    <div v-else>로그인함</div>
    <template #content="{ close }">
      <btn-toggle borderWidth="100"></btn-toggle>
      <Button @click="homeStore.setIsDarkToggle">색바꾸기</Button>
      <Button @click="close">Close</Button>
      <GoogleLogin :callback="callback" />
    </template>
  </Popper>
</template>

<script setup>
import BtnToggle from '@/components/molecules/common/header/BtnToggle.vue';

// import { useUserStore } from '@/stores/user';
import { useAccountStore, useHomeStore } from '@/stores';
// import { useAccountStore } from '@/stores/home';

// const userStore = useUserStore();
const accountStore = useAccountStore();
const homeStore = useHomeStore();

const callback = async () => {
  console.log('개열받네?');
  // const userData = await decodeCredential(response.credential);
  // console.log(userData);
  // await user.login({
  //   email: userData.email,
  //   nickname: userData.name,
  // });
};
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
