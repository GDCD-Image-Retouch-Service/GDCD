<template>
  <div
    class="main-upload d-flex flex-column align-items-center justify-content-center"
  >
    시간과 정신과 구글로그인의 방
    <a id="custom-login-btn" href="javascript:loginWithKakao()">
      <img
        src="//k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg"
        width="222"
        alt="카카오 로그인 버튼"
      />
    </a>
  </div>
</template>

<script setup>
// import { onMounted } from 'vue';
import { loadScript } from '@/api/loadScript';

loadScript('KakaoJDK', 'https://developers.kakao.com/sdk/js/kakao.js')
  .then((value) => {
    console.log(value);
  })
  .catch((value) => {
    console.log(value);
  });
</script>

<style>
.main-upload {
  min-height: calc(100vh - var(--size-h-header));
  width: 100vw;
}

.main-upload-form {
  border-radius: 20px;
  padding: 8px;
  width: 300px;
}

/* box */
.box-upload-image {
  margin: 0;
  width: 100%;
  height: 400px;
  border: 10px #333 solid;
}

#videoElement {
  width: 100%;
  height: 370px;
  background: #666;
}
/*  */
</style>
