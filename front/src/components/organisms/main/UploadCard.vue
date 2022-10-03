<template>
  <div
    class="upload-card main outer d-flex flex-column align-items-center justify-content-center"
  >
    <div class="spacer" />
    <btn-change-mode />
    <div class="spacer" />

    <loading-dots v-if="isLoading" />

    <!-- Pic Mode -->
    <div class="pic-mode" v-if="!mainStore.isCamMode">
      <div
        class="pic-container d-flex flex-column align-items-center justify-content-center"
        style="
          background: lightgray;
          width: 380px;
          max-width: 380px;
          height: 380px;
          max-height: 380px;
          overflow: hidden;
        "
      >
        <div v-show="!isInput">사진을 올려주세요</div>
        <img
          v-show="isInput"
          ref="picBox"
          src=""
          style="width: 380px; height: 380px; object-fit: cover"
          alt="your image"
        />
      </div>
      <div class="spacer" />
      <div class="btn-set d-flex justify-content-center" v-if="!isLoading">
        <!-- Btn Input Picture -->
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

        <!-- Btn Download -->
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

        <!-- Btn Score -->
        <router-link
          v-if="isInput"
          to="/main/score"
          class="btn-set-button inner d-flex align-items-center justify-content-center"
          style="margin-left: 8px"
        >
          <i class="bi bi-check-lg"></i>
        </router-link>
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
        <!-- Btn Take Photo -->
        <div
          class="btn-set-button inner d-flex align-items-center justify-content-center"
          @click="takePhoto"
        >
          <i v-if="!isPhotoTaken" class="bi bi-camera-fill"></i>
          <i v-else class="bi bi-arrow-clockwise"></i>
        </div>

        <!-- Btn Download -->
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

        <!-- Btn Score -->
        <router-link
          v-if="isInput"
          to="/main/score"
          class="btn-set-button inner d-flex align-items-center justify-content-center"
          style="margin-left: 8px"
        >
          <i class="bi bi-check-lg"></i>
        </router-link>
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
import { useRouter } from 'vue-router';

const router = useRouter();
const mainStore = useMainStore();

// created
mainStore.isCamModeOff();

// Pic Mode
const picInputButton = ref(null);
const picBox = ref(null);
const isInput = ref(false);

const setPicBox = () => {
  const file = picInputButton.value.files[0];
  if (FileReader && file) {
    const reader = new FileReader();
    reader.onload = () => {
      const tempUrl = reader.result;
      picBox.value.src = tempUrl;
      mainStore.setTempImg(tempUrl);
      isInput.value = true;
    };
    reader.readAsDataURL(file);
  } else {
    mainStore.isCamModeOff();
    isInput.value = false;
    alert('이미지 업로드에 문제가 발생하였습니다.');
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
    console.log(mainStore.isCamMode ? '카메라모드' : '사진모드');
    if (!mainStore.isCamMode) {
      isInput.value = false;
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
      console.log('카메라 장치에 문제가 있거나 호환되지 않습니다.');
      console.log(e);
      router.go();
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
  isInput.value = !isInput.value;

  const context = canvas.value.getContext('2d');

  context.drawImage(camera.value, 0, 0, 380, 380);

  // canvas to url
  mainStore.setTempImg(canvas.value.toDataURL('image/png'));
};

const downloadImage = () => {
  document
    .getElementById('downloadPhoto')
    .setAttribute(
      'href',
      mainStore.getTempImg.replace('image/png', 'image/octet-stream'),
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
  width: 0px;
  height: 0px;
  background-color: #ffffff;
  position: absolute;
}

.camera-shutter.flash {
  width: 380px;
  max-width: 100vw;
  height: 380px;
  max-height: 100vh;
  opacity: 1;
}

.btn-set-button {
  height: 48px;
  width: 48px;
  border-radius: 100%;
  border: solid 4px var(--theme-color);
  font-size: 14pt;
  color: #000000;
  text-decoration: none;
}
</style>
