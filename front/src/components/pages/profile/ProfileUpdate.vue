<template>
  <div class="profile-update">
    <img :src="updateImage" alt="" class="profile-image common-image" />
    <input
      type="text"
      class="profile-nickname"
      v-model="updateNickname"
      @keyup="selectNickname(updateNickname)"
    />

    <div v-if="userStore.nicknameOverlap">사용할 수 있는 닉네임입니다!</div>

    <div>
      <img src="@/assets/image/info.png" alt="" />
      닉네임은 몇 자
    </div>
    <button class="button" @click="profileUpdate(updateNickname)">제출</button>
  </div>
</template>

<script setup>
import { useUserStore } from '@/stores/user.txt';

const userStore = useUserStore();

const updateImage = userStore.currentUserInfo.item.user.profile;
const updateNickname = userStore.currentUserInfo.item.user.nickname;

const selectNickname = (nickname) => {
  userStore.nicknameOverlapCheck(nickname);
};

const profileUpdate = (nickname) => {
  if (userStore.nicknameOverlap) {
    console.log(nickname);
    userStore.updateMyInfo(nickname);
  }
};
</script>

<style scoped>
.profile-update {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 20px;
}
.profile-image {
  width: 100px;
  height: 100px;
  border-radius: 100px;
  border: 1px solid var(--instagram-dark-grey);
}
.profile-nickname {
  font-size: 18px;
  width: 100%;
  height: 50px;
  padding: 0 5px;
  text-align: center;
  border: none;
  border-bottom: 1px solid var(--instagram-dark-grey);
}
</style>
