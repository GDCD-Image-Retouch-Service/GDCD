<template>
  <div
    class="main-upload d-flex flex-column align-items-center justify-content-center"
  >
    <div class="spacer" />
    <btn-change-mode />

    <div class="d-flex align-items-center">
      <input type="file" accept="image/*;capture=camera" />
      <button @click="takePicture">사진 찍는 버튼</button>
    </div>
    <!-- <video @canplay="initCanvas" ref="video">Stream unavailable</video> -->
    <div class="spacer" />
    <btn-score-receive />
    <div class="spacer" />

    <canvas ref="canvas" style="display: none"></canvas>
    <img :src="imageSrc" />
  </div>
</template>

<script setup>
import BtnChangeMode from '@/components/molecules/BtnChangeMode.vue';
import BtnScoreReceive from '@/components/molecules/BtnScoreReceive.vue';
import { onMounted, ref } from 'vue';

const video = ref(null);
const canvas = ref(null);
const imageSrc = ref(null);

const startCapture = () => {
  navigator.mediaDevices
    .getUserMedia({ video: true })
    .then((stream) => {
      video.value.srcObject = stream;
      video.value.play();
    })
    .catch((e) => {
      console.log(e);
    });
};

const takePicture = () => {
  let context = canvas.value.getContext('2d');
  context.drawImage(
    video.value,
    0,
    0,
    video.value.videoWidth,
    video.value.videoHeight,
  );
  imageSrc.value = canvas.value.toDataURL('image/png');
};

// const initCanvas = () => {
//   canvas.value.setAttribute('width', video.value.videoWidth);
//   canvas.value.setAttribute('height', video.value.videoHeight);
// };

onMounted(() => {
  startCapture();
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
