<template>
  <div class="profile-update">
    <label for="update-profile" class="profile-image common-image">
      <img :src="userStore.updateProfile" alt="" />
    </label>
    <input
      type="text"
      class="profile-nickname"
      v-model="userStore.updateNickname"
      @keyup="selectNickname(userStore.updateNickname, userStore.updateProfile)"
    />
    <input
      style="display: none"
      id="update-profile"
      type="file"
      :v-model="updateProfile"
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
import { useUserStore } from '@/stores/user.js';
import { ref } from 'vue';
const userStore = useUserStore();

userStore.getMyinfo();
const updateProfile = ref(userStore.profile.item?.user.profile);
const updateNickname = ref(userStore.profile.item?.user.nickname);
console.log(userStore.profile.item?.user.profile);
const selectNickname = (nickname) => {
  userStore.nicknameOverlapCheck(nickname);
};

console.log(userStore.profile);
const profileUpdate = (nickname, profile) => {
  if (userStore.nicknameOverlap) {
    console.log(nickname);
    userStore.updateMyInfo(nickname, profile);
  }
};
console.log(userStore.updateNickname, userStore.updateProfile);
</script>

<style scoped>
.profile-update {
  width: calc(100% - 2 * var(--grid-side));
  margin-left: var(--grid-side);
  margin-top: var(--grid-vertical);
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
