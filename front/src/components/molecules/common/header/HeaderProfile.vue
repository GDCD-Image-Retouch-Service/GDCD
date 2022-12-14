<template>
  <div class="header-profile">
    <div v-if="accountStore.getIsLogined">
      <img
        :src="userStore.currentUser.item?.user?.profile"
        alt=""
        class="profile-image"
        @click="userStore.headerSetDropdown = !userStore.headerSetDropdown"
      />
    </div>
    <div v-else>
      <span
        class="material-icons-outlined icons"
        @click="userStore.headerSetDropdown = !userStore.headerSetDropdown"
      >
        account_circle
      </span>
    </div>
    <div class="header-dropdown" v-if="userStore.headerSetDropdown">
      <div
        v-if="accountStore.getIsLogined"
        @click="userStore.logoutModal = !userStore.logoutModal"
      >
        로그아웃
      </div>
      <div v-else @click="userStore.loginModal = !userStore.loginModal">
        로그인
      </div>

      <div v-if="mainStore.getIsDark" @click="mainStore.setIsDarkToggle">
        라이트모드
      </div>
      <div v-else @click="mainStore.setIsDarkToggle">다크모드</div>
    </div>

    <template>
      <div>
        <v-dialog v-model="userStore.loginModal">
          <div class="create-post-modal">
            <div class="modal-title">구글 계정을 통해</div>
            <div class="modal-title2">로그인하실 수 있습니다.</div>
            <div class="modal-close">
              <GoogleLogin :callback="callback" />
            </div>
          </div>
        </v-dialog></div
    ></template>
    <template>
      <div>
        <v-dialog v-model="userStore.logoutModal">
          <div class="create-post-modal" ref="postModal">
            <div class="modal-title-logout">정말 로그아웃하시겠습니까?</div>
            <div
              class="modal-logout"
              @click="user.logout(), (userStore.logoutModal = false)"
            >
              로그아웃
            </div>
          </div>
        </v-dialog>
      </div></template
    >
  </div>
</template>

<script setup>
import { useAccountStore, useMainStore, useUserStore } from '@/stores';
import { decodeCredential } from 'vue3-google-login';
import { user } from '@/api/rest';
import { ref, onMounted, watch } from 'vue';

// > Init
const accountStore = useAccountStore();
const mainStore = useMainStore();
const userStore = useUserStore();

const postModal = ref(null);

// > Method
const callback = async (response) => {
  const userData = await decodeCredential(response.credential);
  console.log(userData);
  await user.login({
    email: userData.email,
    nickname: userData.name,
  });
};

// > Watch
watch(
  () => mainStore.getIsDark,
  () => {
    if (mainStore.getIsDark) {
      document
        .getElementsByClassName('v-overlay-container')[0]
        .classList.add('dark');
    } else {
      document
        .getElementsByClassName('v-overlay-container')[0]
        .classList.remove('dark');
    }
  },
);

// > Life Cycle
onMounted(() => {
  if (mainStore.getIsDark) {
    document
      .getElementsByClassName('v-overlay-container')[0]
      .classList.add('dark');
  } else {
    document
      .getElementsByClassName('v-overlay-container')[0]
      .classList.remove('dark');
  }
});
</script>

<style scoped>
.header-profile {
  margin-right: var(--grid-side);
  position: relative;
}
.profile-image {
  width: 35px;
  height: 35px;
  object-fit: cover;
  border-radius: 35px;
  border: 1px solid var(--instagram-dark-grey);
}
.header-dropdown {
  width: 110px;
  height: 70px;
  position: absolute;
  top: 30px;
  right: 0;
  z-index: 40;
  background-color: var(--color-main);
  border-radius: 5px;
  border: 1px solid var(--instagram-grey);
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;
}
.create-post-modal {
  width: 100%;
  max-width: 400px;
  height: 170px;
  color: #3c3c3a;
  background: white;

  border-radius: 5px;
  text-align: center;
  position: relative;
  margin: 0 auto;
  z-index: 5;
}

.dark .create-post-modal {
  background: #3c3c3a;
  border-radius: 5px;
  text-align: center;
  position: relative;
  margin: 0 auto;
  z-index: 5;
}

.dark .create-post-modal * {
  color: white;
}

.modal-title {
  width: 100%;
  font-weight: 700;
  position: absolute;
  top: calc(17% - 12px);
  color: var(--black);
  font-family: 'NanumSquareRound';
}
.modal-title2 {
  width: 100%;
  font-weight: 700;
  position: absolute;
  top: calc(33% - 12px);
  font-family: 'NanumSquareRound';

  color: var(--black);
}
.modal-title-logout {
  width: 100%;
  font-weight: 700;
  position: absolute;
  top: calc(30% - 12px);
  color: var(--black);
  font-family: 'NanumSquareRound';
}
.modal-close {
  border-radius: 5px;
  border: none;
  color: var(--light-main-color);
  font-weight: 500;
  width: 50px;
  position: absolute;
  top: calc(60% - 12px);
  left: calc(50% - 100px);
}
.modal-logout {
  width: 150px;
  background-color: var(--theme-color);
  color: var(--light-main-color);
  border-radius: 5px;
  border: none;
  width: 110px;
  height: 40px;
  line-height: 40px;
  position: absolute;
  top: calc(60% - 12px);
  left: calc(50% - 55px);
}
.icons {
  font-size: 28px;
  color: var(--instagram-dark-grey);
}
</style>
