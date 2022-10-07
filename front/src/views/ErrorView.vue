<template>
  <div
    class="error-view sub d-flex flex-column align-items-center"
    @click="dataClear"
  >
    <div
      class="d-flex flex-column align-items-center"
      style="width: 100%; padding: 8px"
    >
      <div class="spacer"></div>
      <img
        :src="require('@/assets/sprite/ChaltteogiSad.png')"
        style="width: 60%; max-width: 380px"
      />
      <div class="d-flex flex-column align-items-center">
        <div class="spacer"></div>
        <h1>정말 죄송합니다</h1>
        <div>요청하신 결과를 찾을 수 없습니다.</div>
        <div>클릭하면 처음화면으로 돌아갑니다.</div>
        <div class="spacer"></div>
        <div>문제가 계속 반복된다면</div>
        <div>다음의 해결책을 추천합니다.</div>
        <div class="spacer"></div>
        <div><span>올바른 경로</span>인지 확인해주세요.</div>
        <div class="spacer"></div>
        <div><span>인터넷 연결 상태</span>를 확인해주세요.</div>
        <div class="spacer"></div>
        <div><span>로그아웃</span> 후에 다시 접속해주세요.</div>
        <div class="spacer"></div>
        <div>위의 방법으로도 해결되지 않는다면</div>
        <div>GDCD301@gmail.com 으로</div>
        <div>문의주시기 바랍니다.</div>
        <div class="spacer"></div>
        <div>더 나은 서비스를 제공하기 위해</div>
        <div>늘 노력하는 개떡찰떡이 되겠습니다.</div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useUserStore } from '@/stores/user.js';
import { useRouter } from 'vue-router';
const router = useRouter();

const userStore = useUserStore();

userStore.headerSetDropdown = false;
userStore.loginModal = false;
userStore.logoutModal = false;

// > Method
const dataClear = () => {
  router.push('/');
};
</script>

<style scoped>
.error-view {
  padding-top: 70px;
  padding-bottom: 60px;
  height: 100vh;
  width: 100vw;
  overflow-y: scroll;
}

span {
  color: var(--color-sub);
  background: var(--color-reverse);
  font-weight: bold;
}
</style>
