<template>
  <div
    class="main-upload d-flex flex-column align-items-center justify-content-center"
  >
    <div>시간과 정신과 구글로그인의 방</div>
    <h1>{{ msg }}</h1>

    <GoogleLogin :callback="callback" />
  </div>
</template>

<script setup>
const callback = (response) => {
  // This callback will be triggered when the user selects or login to
  // his Google account from the popup
  console.log('Handle the response', response);
};
const msg = '씨ㅡ발';
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
