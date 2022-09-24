<template>
  <div
    class="main-upload d-flex flex-column align-items-center justify-content-center"
  >
    <div>시간과 정신과 구글로그인의 방</div>
    <h1>{{ msg }}</h1>
    <GoogleLogin :callback="callback" prompt auto-login />
  </div>
</template>

<script setup>
import { onMounted } from 'vue';
import { googleOneTap, decodeCredential } from 'vue3-google-login';

const callback = (response) => {
  // decodeCredential will retrive the JWT payload from the credential
  const userData = decodeCredential(response.credential);
  console.log('Handle the userData', userData);
};

onMounted(async () => {
  await googleOneTap({ autoLogin: true })
    .then((response) => {
      // This promise is resolved when user selects an account from the the One Tap prompt
      console.log('Handle the response', response);
      // callback(response);
    })
    .catch((error) => {
      console.log('Handle the error', error);
    });
});
console.log(process.env.VUE_APP_GOOGLE_CLIENT_ID);
console.log(process.env.VUE_APP_GOOGLE_CLIENT_SSIBAL);
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
