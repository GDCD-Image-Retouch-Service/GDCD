// 다운로드 기능은 여기있
<template>
  <div
    class="upload-card outer d-flex flex-column align-items-center justify-content-center"
  >
    <div class="spacer" />
    <btn-change-mode />
    <div class="spacer" />

    <loading-dots v-if="mainStore.isCamMode && isLoading" />

    <div
      v-if="mainStore.isCamMode"
      v-show="!isLoading"
      class="camera-box"
      :class="{ flash: isShotPhoto }"
    >
      <div class="camera-shutter" :class="{ flash: isShotPhoto }"></div>

      <video
        v-show="!isPhotoTaken"
        ref="camera"
        width="380"
        height="380"
        autoplay
      ></video>

      <canvas
        v-show="isPhotoTaken"
        id="photoTaken"
        ref="canvas"
        width="380"
        height="380"
      ></canvas>
    </div>

    <!-- 사진 찍기 버튼 -->
    <div v-if="mainStore.isCamMode && !isLoading">
      <div class="spacer" />

      <div class="camera-shoot-button inner" @click="takePhoto">
        <i v-if="!isPhotoTaken" class="bi bi-camera-fill"></i>
        <i v-else class="bi bi-arrow-clockwise"></i>
      </div>

      <div class="spacer" />
    </div>

    <!-- download 버튼 -->
    <div v-if="isPhotoTaken && mainStore.isCamMode" class="camera-download">
      <a
        id="downloadPhoto"
        download="my-photo.jpg"
        class="button"
        role="button"
        @click="downloadImage"
      >
        Download
      </a>
    </div>
  </div>
</template>

<script setup>
import BtnChangeMode from '@/components/molecules/main/btn/BtnChangeMode.vue';
import LoadingDots from '@/components/atoms/LoadingDots.vue';

import { ref, watch } from 'vue';
import { useMainStore } from '@/stores/main';

const mainStore = useMainStore();

const camera = ref(null);
const canvas = ref(null);
const isPhotoTaken = ref(false);
const isShotPhoto = ref(false);
const isLoading = ref(false);

mainStore.isCamModeOff();

watch(
  () => mainStore.isCamMode,
  () => {
    console.log(mainStore.isCamMode);
    if (!mainStore.isCamMode) {
      isPhotoTaken.value = false;
      isShotPhoto.value = false;
      stopCameraStreame();
    } else {
      createCameraElement();
    }
  },
);

const createCameraElement = () => {
  isLoading.value = true;
  const constraints = (window.constraints = {
    audio: false,
    video: {
      width: { min: 240, ideal: 720, max: 1080 },
      height: { min: 240, ideal: 720, max: 1080 },
    },
  });

  navigator.mediaDevices
    .getUserMedia(constraints)
    .then((stream) => {
      isLoading.value = false;
      camera.value.srcObject = stream;
    })
    .catch((e) => {
      isLoading.value = false;
      console.log(e);
      alert('카메라 장치에 문제가 있거나 호환되지 않습니다.');
    });
};

const stopCameraStreame = () => {
  if (camera.value != null) {
    let tracks = camera.value.srcObject.getTracks();
    tracks.forEach((track) => {
      track.stop();
    });
  }
};

const takePhoto = () => {
  if (!isPhotoTaken.value) {
    isShotPhoto.value = true;

    const FLASH_TIMEOUT = 50;

    setTimeout(() => {
      isShotPhoto.value = false;
    }, FLASH_TIMEOUT);
  }

  isPhotoTaken.value = !isPhotoTaken.value;

  const context = canvas.value.getContext('2d');
  console.log('테스문구');
  console.log(camera.value.height);
  console.log(camera.value.width);
  context.drawImage(camera.value, 0, 0, 380, 380);
};

const downloadImage = () => {
  const download = document.getElementById('downloadPhoto');
  const canvas = document
    .getElementById('photoTaken')
    .toDataURL('image/jpeg')
    .replace('image/jpeg', 'image/octet-stream');
  download.setAttribute('href', canvas);
};
</script>

<style scoped>
.upload-card {
  border-radius: 20px;
  width: 90%;
  max-width: 380px;
}

.camera-box {
  width: 380px;
  height: 380px;
  background-color: #000000;
}

.camera-shutter {
  opacity: 0;
  width: 380px;
  height: 380px;
  background-color: #ffffff;
  position: absolute;
}

.camera-shutter.flash {
  opacity: 1;
}

.camera-shoot-button {
  height: 48px;
  width: 48px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 100%;
  border: solid 4px var(--theme-color);
  /* background: #f4f4f4; */
  font-size: 20pt;
}
</style>
