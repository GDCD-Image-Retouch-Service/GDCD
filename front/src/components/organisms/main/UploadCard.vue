<template>
  <div
    class="upload-card main outer d-flex flex-column align-items-center justify-content-center"
  >
    <div class="spacer" />
    <btn-change-mode />
    <div class="spacer" />

    <loading-dots v-if="isLoading" />
    <!-- Pic Mode -->
    <div class="pic-mode" v-if="!mainStore.isCamMode" v-show="!isLoading">
      <div
        class="pic-container d-flex flex-column align-items-center justify-content-center"
      >
        <div
          v-if="!isInput"
          class="pic-comment sub inner-shadow d-flex align-items-center justify-content-center"
          style="font-size: 12pt; color: black"
        >
          <div class="pic-message">사진을 업로드 해주세요</div>
        </div>
        <img
          v-show="isInput"
          ref="picBox"
          src=""
          style="width: 380px"
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
          :download="`${photoName}`"
          role="button"
          @click="downloadImage"
        >
          <i class="bi bi-download"></i>
        </a>

        <div
          v-if="isInput"
          @click="scoring"
          class="btn-set-button inner d-flex align-items-center justify-content-center"
          style="margin-left: 8px"
        >
          <i class="bi bi-check-lg"></i>
        </div>
      </div>
      <div class="spacer" />
    </div>

    <!-- Cam Mode -->
    <div class="cam-mode" v-else>
      <div
        class="camera-box"
        :class="{ flash: isShotPhoto }"
        v-show="!isLoading"
        style="overflow: hidden"
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
          :download="`${photoName}`"
          role="button"
          @click="downloadImage"
        >
          <i class="bi bi-download"></i>
        </a>

        <div
          v-if="isInput"
          @click="scoring"
          class="btn-set-button inner d-flex align-items-center justify-content-center"
          style="margin-left: 8px"
        >
          <i class="bi bi-check-lg"></i>
        </div>
      </div>
      <div class="spacer" />
    </div>
  </div>
</template>

<script setup>
import BtnChangeMode from '@/components/molecules/main/btn/BtnChangeMode.vue';
import LoadingDots from '@/components/atoms/LoadingDots.vue';

import Compressor from 'compressorjs';
import { ref, watch } from 'vue';
import { useMainStore, useLocalStore } from '@/stores';
import { useRouter, useRoute } from 'vue-router';
import { image } from '@/api/rest';

const router = useRouter();
const route = useRoute();
const mainStore = useMainStore();
const localStore = useLocalStore();

// Pic Mode
const picInputButton = ref(null);
const picBox = ref(null);
const isInput = ref(false);

// Cam Mode
const camera = ref(null);
const canvas = ref(null);
const isPhotoTaken = ref(false);
const isShotPhoto = ref(false);
const isLoading = ref(false);

const photoName = ref('');

// > method
const setPicBox = () => {
  const file = picInputButton.value.files[0];

  const options = {
    maxWidth: 720,
    maxHeight: 720,
    success: function (result) {
      if (result.size > 1024 * 1024) {
        // 리사이징 했는데도 용량이 큰 경우
        alert(' * 이미지 업로드 실패 : 용량이 초과되었습니다.');
        return;
      } else if (result.size < 50 * 50) {
        // 이미지가 지나치게 작은 경우
        alert(' * 이미지 업로드 실패 : 용량이 지나치게 작습니다.');
        return;
      }
      console.log(new File([result], result.name, { type: result.type }));

      const _URL = window.URL || window.webkitURL;
      if (_URL) {
        console.log(' * 이미지 압축 종료');
        mainStore.setTempFile(
          new File([result], result.name, { type: result.type }),
        );
        mainStore.setTempImg(_URL.createObjectURL(result));
        picBox.value.src = _URL.createObjectURL(result);
        isInput.value = true;
      }
    },
    error: function (err) {
      console.log(err);
    },
  };
  console.log(' * 이미지 압축 시작');
  new Compressor(file, options);
};

// > watch
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

// > method
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
  let today = new Date();

  photoName.value = `${today.getFullYear()}-${
    today.getMonth() + 1
  }-${today.getDate()}-${today.getDay()}-${today.getHours()}-${today.getMinutes()}-${today.getSeconds()}.jpg`;

  document
    .getElementById('downloadPhoto')
    .setAttribute(
      'href',
      mainStore.getTempImg.replace('image/png', 'image/octet-stream'),
    );
};
const scoring = async () => {
  localStore.loadPrev();
  isLoading.value = true;
  const data = await image.scoring(mainStore.getTempFile);
  if (!data.item) {
    console.log('점수 반환 실패');
    return;
  }

  const eScore = data.item.dict.aesthetic;
  const qScore = data.item.dict.quality;

  const eRank =
    eScore < 4.7
      ? 9
      : eScore < 4.9
      ? 8
      : eScore < 5.1
      ? 7
      : eScore < 5.2
      ? 6
      : eScore < 5.3
      ? 5
      : eScore < 5.4
      ? 4
      : eScore < 5.5
      ? 3
      : eScore < 5.7
      ? 2
      : 1;
  const qRank =
    qScore < 4.7
      ? 9
      : qScore < 6.6
      ? 8
      : qScore < 6.7
      ? 7
      : qScore < 6.8
      ? 6
      : qScore < 6.85
      ? 5
      : qScore < 6.9
      ? 4
      : qScore < 6.95
      ? 3
      : qScore < 7.0
      ? 2
      : 1;

  let score = Math.ceil(
    (((eScore - 5.3) * 10 + (qScore - 6.8) * 10) / 2) * 10 + 50,
  );
  if (score > 100) score = 100;
  else if (score < 0) score = 0;

  localStore.setPath(route.fullPath);
  localStore.setUrl('-');
  localStore.setScore(score);
  localStore.setERank(eRank);
  localStore.setQRank(qRank);
  localStore.setRequestId('-');
  localStore.setPrev();

  router.push('/main/result');
};

// > Life Cycle
{
  mainStore.isCamModeOff();
}
</script>

<style scoped>
.upload-card {
  margin-top: var(--grid-vertical);
  border-radius: 20px;
  width: calc(100% - 2 * var(--grid-side));
  max-width: 400px;
}
.pic-mode {
  width: 100%;
}
.pic-container {
  /* background: lightgray; */
  width: 100%;
  overflow: hidden;
  position: relative;
}
.pic-comment {
  width: 100%;
  padding-bottom: 100%;
  position: relative;
}
.pic-message {
  position: absolute;
  top: 50%;
}
.cam-mode {
  width: 100%;
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
