<template>
  <div
    class="main-upload d-flex flex-column align-items-center justify-content-center"
  >
    <video
      :src="src"
      :width="width"
      :height="height"
      :autoplay="autoplay"
      ref="video"
    ></video>
    <hr />
    <img :src="photo" alt="" :width="width" :height="height" />
    <hr />
    <button type="button" @click="takePhoto">Take Photo</button>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';

const src = ref(null);
const width = 400;
const height = 400;
const autoplay = true;
const photo = ref(null);
const screenshotFormat = 'image/jpeg';
const video = ref(null);
const _stream = null;
const _hasUserMedia = false;
const _ctx = ref(null);

const takePhoto = () => {
  if (!_hasUserMedia) {
    return;
  }

  const canvas = getCanvas();
  photo.value = canvas.toDataURL(screenshotFormat);
};

const getCanvas = () => {
  if (!_hasUserMedia) {
    return;
  }

  if (this._ctx == null) {
    let canvas = document.createElement('canvas');
    canvas.height = video.value.clientHeight;
    canvas.width = video.value.clientWidth;

    _ctx.value = canvas.getContext('2d');
    _ctx.value.drawImage(video.value, 0, 0, canvas.width, canvas.heigh);

    return canvas;
  }
};

onMounted(() => {
  console.log('mounted');
  navigator.mediaDevices
    .getUserMedia({ video: true })
    .then((stream) => {
      console.log(stream);
      src.value = window.URL.createObjectURL(stream);
      _stream.value = stream;
      _hasUserMedia.value = true;
    })
    .catch((e) => {
      console.log(e);
    });
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
