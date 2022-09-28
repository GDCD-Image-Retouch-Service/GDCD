<template>
  <div class="profile-update">
    <label for="update-profile" class="profile-image common-image">
      <img :src="userStore.updateProfile" alt="" class="update-image" />
    </label>

    <!-- style="display: none" -->
    <input
      type="file"
      id="update-profile"
      @change="uploadFile"
      ref="uploadImage"
    />

    <input
      type="text"
      class="profile-nickname"
      v-model="userStore.updateNickname"
      @keyup="selectNickname(userStore.updateNickname, userStore.updateProfile)"
    />

    <div v-if="userStore.nicknameOverlap">사용할 수 있는 닉네임입니다!</div>

    <div>
      <img src="@/assets/image/info.png" alt="" />
      닉네임은 몇 자
    </div>
    <button class="button" @click="profileUpdate(userStore.updateNickname)">
      제출
    </button>
    <button @click="check()">dd</button>

    {{ (userStore.updateProfile, userStore.updateNickname) }}
  </div>
</template>

<script setup>
import { useUserStore } from '@/stores/user.js';
import Swal from 'sweetalert2';
import { ref } from 'vue';

const uploadImage = ref('');

const userStore = useUserStore();

userStore.getMyinfo();

console.log(userStore.profile.item?.user.profile);

const selectNickname = (nickname) => {
  userStore.nicknameOverlapCheck(nickname);
};

console.log(userStore.profile);
const profileUpdate = (nickname, profile) => {
  if (userStore.nicknameOverlap) {
    Swal.fire({
      title: 'Do you want to save the changes?',
      showDenyButton: true,
      showCancelButton: true,
      confirmButtonText: 'Save',
      denyButtonText: `Don't save`,
      timer: 1500,
    }).then((result) => {
      if (result.isConfirmed) {
        Swal.fire('Saved!', '', 'success');
      } else if (result.isDenied) {
        Swal.fire('Changes are not saved', '', 'info');
      }
    });
    console.log(nickname);
    userStore.updateMyInfo(nickname, profile);
  }
};
console.log(userStore.updateNickname, userStore.updateProfile);

const check = () => {
  console.log(uploadImage);
};

const uploadFile = () => {
  console.log(
    uploadImage.value.files[0],
    'ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ',
    uploadImage.value.src,
  );

  const file = uploadImage.value.files[0];
  if (FileReader && file) {
    const reader = new FileReader();
    reader.onload = (e) => {
      console.log(e);
      const previewImage = document.getElementById('update-profile');
      previewImage.src = e.target.result;
      userStore.updateProfile = previewImage.src;

      console.log(previewImage.src);
    };
    userStore.updateProfile = reader.readAsDataURL(file);
  }
};
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
  overflow: hidden;
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
.update-image {
  width: 100%;
  object-fit: cover;
}
</style>
