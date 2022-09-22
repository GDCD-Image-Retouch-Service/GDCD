// 이미지 서버 업로드 테스트 코드
<template>
  <div
    class="main-upload d-flex flex-column align-items-center justify-content-center"
  >
    <img src="https://picsum.photos/200/330" id="myImage" />
    <canvas id="myCanvas" style="display: none" />
    <button @click="sendImage">사진 전송 버튼</button>
  </div>
</template>

<script setup>
// import { inject } from 'vue';
// import axios from 'axios';

const sendImage = () => {
  const canvas = document.getElementById('myCanvas');
  var canvasCtx = canvas.getContext('2d');
  var imgTag = document.getElementById('myImage');
  canvasCtx.drawImage(imgTag, 10, 10);

  // var imgString = document.getElementById('myImage').src;
  var imgString = canvas.toDataURL('image/png');
  // imgString = 's';
  console.log(imgString);

  // fetch(imgString)
  //   .then((res) => res.blob())
  //   .then((blob) => {
  //     const file = new File([blob], 'dot.png', blob);
  //     console.log(file);
  //   });

  // var imgString = canvas.toDataURL();

  // const url = 'C:/Users/dkdld/Pictures/a';
  // const formData = new FormData();
  // formData.append('image', file);
  // formData.append('name', userName);
  // formData.append('birthday', birthday);
  // formData.append('gender', gender);
  // const config = { headers: { 'content-type': 'multipart/form-data' } };
  // return axios.post(url, formData, config);
};

// var c = document.getElementById('myCanvas');
// var ctx = c.getContext('2d');
// var img = document.getElementById('myImage');
// ctx.drawImage(img, 10, 10);
// var imgString = c.toDataURL();
// ajax({
//   type: 'POST',
//   dataType: 'JSON',
//   data: {
//     imageString: imgString,
//   },
//   url: 'C:/Users/dkdld/Pictures/a',
//   success: function () {
//     alert('success');
//   },
// })
//   .then(() => {
//     console.log('success');
//   })
//   .catch((e) => {
//     console.log(e);
//   });
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
