<template>
  <div class="profile-update">
    <!-- image  -->
    <div class="wrapper">
      <label for="file" class="upload-btn">
        <img
          :src="userStore.profile?.item?.user?.profile"
          class="image-box"
          ref="imgBox"
        />
      </label>
      <input
        id="file"
        type="file"
        accept="image/*"
        @change="testfunc"
        ref="fileInput"
        style="display: none"
      />
    </div>

    <!-- nickname  -->
    <input
      type="text"
      class="profile-nickname"
      v-model="userStore.updateNickname"
      @change="checkOoverlap(userStore.updateNickname)"
    />

    <div v-if="userStore.nicknameOverlap">사용할 수 있는 닉네임입니다!</div>

    <div>
      <img src="@/assets/image/info.png" alt="" />
      닉네임은 몇 자
    </div>
    <button
      class="button"
      @click="profileUpdate(userStore.updateNickname, userStore.updateProfile)"
    >
      제출
    </button>
  </div>
</template>

<script setup>
import { useUserStore } from '@/stores/user.js';

import { ref } from 'vue';
import { useRouter } from 'vue-router';

const myRouter = useRouter();
const userStore = useUserStore();

const fileInput = ref(null);

let imgBox = ref(null);

// 이미지 미리보기 함수
const testfunc = () => {
  const reader = new FileReader();
  reader.onload = ({ target }) => {
    imgBox.value.src = target.result;
  };
  userStore.updateProfile = fileInput.value.files[0];
  reader.readAsDataURL(fileInput.value.files[0]);
};

// 닉네임 유효성 검사
const checkOoverlap = (nickname) => {
  useUserStore.nicknameOverlapCheck(nickname);
};

//  회원 정보 수정 요청
const profileUpdate = (name, file) => {
  userStore.updateMyInfo(name, file);

  myRouter.push({ name: 'profile', params: { userId: 0 } });
};
</script>

<style scoped>
.profile-update {
  width: calc(100% - 2 * var(--grid-side));
  margin-left: var(--grid-side);
  display: flex;
  flex-direction: column;
  gap: var(--grid-vertical);
}
.wrapper {
  width: 100%;
  display: flex;
  justify-content: center;
  margin-top: var(--grid-vertical);

  text-align: center;
}
.image-box {
  width: 130px;
  height: 130px;
  border-radius: 130px;
  border: 1px solid var(--instagram-grey);
  background-position: center;
  background-size: cover;
  object-fit: cover;
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
