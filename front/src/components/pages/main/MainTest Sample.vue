// 이미지 서버 업로드 테스트 코드
<template>
  <div
    class="main-upload d-flex flex-column align-items-center justify-content-center"
  >
    <img src="http://some/image/source" id="myImage" />
    <canvas id="myCanvas" style="display: none" />
  </div>
</template>

<script setup>
import { inject } from 'vue';

const ajax = inject('ajax');

var c = document.getElementById('myCanvas');
var ctx = c.getContext('2d');
var img = document.getElementById('myImage');
ctx.drawImage(img, 10, 10);
var imgString = c.toDataURL();
ajax({
  type: 'POST',
  dataType: 'JSON',
  data: {
    imageString: imgString,
  },
  url: 'C:/Users/dkdld/Pictures/a',
  success: function () {
    alert('success');
  },
})
  .then(() => {
    console.log('success');
  })
  .catch((e) => {
    console.log(e);
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
