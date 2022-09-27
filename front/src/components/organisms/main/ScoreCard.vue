<template>
  <div
    class="upload-card outer d-flex flex-column align-items-center justify-content-center"
  >
    <div class="spacer" />
    <btn-change-mode />
    <div class="spacer" />

    <loading-dots v-if="isLoading" />

    <div class="pic-mode" v-if="!mainStore.isCamMode">
      <div
        class="pic-container d-flex flex-column align-items-center justify-content-center"
        style="
          background: #000000;
          width: 380px;
          max-width: 380px;
          height: 380px;
          max-height: 380px;
        "
      >
        <img ref="picBox" src="" width="380" alt="your image" />
      </div>
      <div class="spacer" />
      <div class="btn-set d-flex justify-content-center" v-if="!isLoading">
        <!-- Input Picture -->
        <label className="input-file-button" for="input-file">
          <div
            class="btn-set-button inner d-flex align-items-center justify-content-center"
          >
            <i v-if="!isInput" class="bi bi-upload"></i>
            <i v-else class="bi bi-arrow-clockwise"></i>
          </div>
        </label>
        <input
          type="file"
          id="input-file"
          style="display: none"
          ref="picInputButton"
          @change="setPicBox"
        />

        <!-- download 버튼 -->

        <a
          v-if="isInput"
          class="btn-set-button inner d-flex align-items-center justify-content-center"
          style="margin-left: 8px"
          id="downloadPhoto"
          download="photo.jpg"
          role="button"
          @click="downloadImage"
        >
          <i class="bi bi-download"></i>
        </a>

        <!-- Upload -->
      </div>
      <div class="spacer" />
    </div>

    <div class="cam-mode" v-else>
      <div
        class="camera-box"
        :class="{ flash: isShotPhoto }"
        v-show="!isLoading"
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

      <div class="spacer" />
      <div class="btn-set d-flex justify-content-center" v-if="!isLoading">
        <!-- 사진 찍기 버튼 -->
        <div
          class="btn-set-button inner d-flex align-items-center justify-content-center"
          @click="takePhoto"
        >
          <i v-if="!isPhotoTaken" class="bi bi-camera-fill"></i>
          <i v-else class="bi bi-arrow-clockwise"></i>
        </div>

        <!-- download 버튼 -->
        <a
          v-if="isPhotoTaken"
          class="btn-set-button inner d-flex align-items-center justify-content-center"
          style="margin-left: 8px"
          id="downloadPhoto"
          download="photo.jpg"
          role="button"
          @click="downloadImage"
        >
          <i class="bi bi-download"></i>
        </a>
      </div>
      <div class="spacer" />
    </div>
  </div>
</template>

<script setup>
import BtnChangeMode from '@/components/molecules/main/btn/BtnChangeMode.vue';
import LoadingDots from '@/components/atoms/LoadingDots.vue';

import { ref, watch } from 'vue';
import { useMainStore } from '@/stores/main';

const mainStore = useMainStore();

// created
mainStore.isCamModeOff();

// Pic Mode
const picInputButton = ref(null);
const picBox = ref(null);
const isInput = ref(false);

const setPicBox = () => {
  const [file] = picInputButton.value.files;
  if (file) {
    const tempUrl = URL.createObjectURL(file);
    picBox.value.src = tempUrl;
    mainStore.tempImg = tempUrl;
    isInput.value = true;
  }
};

// Cam Mode
const camera = ref(null);
const canvas = ref(null);
const isPhotoTaken = ref(false);
const isShotPhoto = ref(false);
const isLoading = ref(false);

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

  mainStore.tempImg = canvas.value.toDataURL('image/png');
};

const downloadImage = () => {
  document
    .getElementById('downloadPhoto')
    .setAttribute(
      'href',
      mainStore.getTempImg.replace('image/jpeg', 'image/octet-stream'),
    );
};
</script>

<style scoped>
.upload-card {
  border-radius: 20px;
  width: 90%;
  max-width: 380px;
  overflow: hidden;
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

.btn-set-button {
  height: 48px;
  width: 48px;
  border-radius: 100%;
  border: solid 4px var(--theme-color);
  /* background: #f4f4f4; */
  font-size: 14pt;
  color: #000000;
  text-decoration: none;
}
</style>
