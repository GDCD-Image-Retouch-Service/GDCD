<template>
  <div
    class="score-card main outer d-flex flex-column align-items-center justify-content-center"
  >
    <div class="spacer" />
    <div class="d-flex align-items-center" style="font-size: 24pt">
      <icon-rank :rank="Math.ceil(9 - (score * 8) / 100)" />
      <div>: {{ score }}</div>
    </div>
    <div class="spacer" />

    <loading-dots v-if="isLoading" />

    <div class="pic-mode" v-show="!isLoading">
      <div
        class="pic-container d-flex flex-column align-items-center justify-content-center"
        style="
          background: lightgray;
          width: 380px;
          max-width: 380px;
          height: 380px;
          max-height: 380px;
        "
      >
        <img
          ref="picBox"
          src=""
          style="width: 380px; height: 380px; object-fit: cover"
          alt="your image"
        />
      </div>
    </div>
    <div class="spacer" />
    <div class="btn-set d-flex justify-content-center">
      <router-link
        to="/main"
        class="btn-set-button inner d-flex align-items-center justify-content-center"
      >
        <i class="bi bi-arrow-counterclockwise"></i>
      </router-link>

      <!-- Btn Download -->
      <a
        class="btn-set-button inner d-flex align-items-center justify-content-center"
        style="margin-left: 8px"
        id="downloadPhoto"
        :download="`${photoName}.jpg`"
        role="button"
        @click="downloadImage"
      >
        <i class="bi bi-download"></i>
      </a>

      <router-link
        to="optimize"
        class="btn-set-button inner d-flex align-items-center justify-content-center"
        style="margin-left: 8px"
      >
        <i class="bi bi-stars"></i>
      </router-link>

      <router-link
        to="inpaint"
        class="btn-set-button inner d-flex align-items-center justify-content-center"
        style="margin-left: 8px"
      >
        <i class="bi bi-eraser-fill"></i>
      </router-link>
    </div>
    <div class="spacer" />
  </div>
</template>

<script setup>
import LoadingDots from '@/components/atoms/LoadingDots.vue';
import IconRank from '@/components/atoms/IconRank.vue';

import { ref, onMounted, watch } from 'vue';
import { image } from '@/api/rest';
import { useMainStore, useAccountStore, useLocalStore } from '@/stores/';
import { useRouter } from 'vue-router';

// init
const router = useRouter();
// const route = useRoute();
const mainStore = useMainStore();
const accountStore = useAccountStore();
const localStore = useLocalStore();

// data
const path = ref('');
const url = ref('');
const score = ref('');
const eRank = ref('');
const qRank = ref('');
const photoName = ref('');
const isLoading = ref(false);
const picBox = ref(null);
// const data = ref(null);

// method
const save = async () => {
  if (localStore.getUrl == '-') {
    if (mainStore.getTempFile) {
      const payload = {
        image: mainStore.getTempFile,
        aesthetic: localStore.getERank,
        quality: localStore.getQRank,
      };
      console.log(' * 저장시작 at ResultCard', payload);
      const data = await image.save(payload);
      console.log(' * 저장완료', data);

      // mainStore.setTempId(data.item);
      localStore.setUrl(data.item);
      localStore.setPrev();
    } else {
      console.log(' * 저장 실패 : 데이터 전송 오류');
    }
  } else {
    console.log(' * 이미 저장된 사진임 at ResultCard');
  }
};

const downloadImage = () => {
  let today = new Date();

  let year = today.getFullYear();
  let month = today.getMonth() + 1;
  let date = today.getDate();
  let day = today.getDay();

  photoName.value = year + '_' + month + '_' + date + '_' + day + '.jpg';

  console.log(' * 픽박스 src', picBox.value.src);

  // document
  //   .getElementById('downloadPhoto')
  //   .setAttribute(
  //     'href',
  //     picBox.value.src.replace('image/png', 'image/octet-stream'),
  //   );
  if (!(window.ActiveXObject || 'ActiveXObject' in window)) {
    var save = document.createElement('a');
    save.href = picBox.value.src;
    save.target = '_blank';
    save.download = photoName.value || picBox.value.src;
    var evt = document.createEvent('MouseEvents');
    evt.initMouseEvent(
      'click',
      true,
      true,
      window,
      1,
      0,
      0,
      0,
      0,
      false,
      false,
      false,
      false,
      0,
      null,
    );
    save.dispatchEvent(evt);
    (window.URL || window.webkitURL).revokeObjectURL(save.href);
  }
};

// Watch
watch(
  () => accountStore.getIsLogined,
  () => {
    if (accountStore.getIsLogined) {
      console.log(' * 로그인 확인 Ok');
      save();
    } else {
      console.log(' * 로그인 확인 No');
    }

    // if (mainStore.getTempEScore != 0) save();
  },
  { immediate: true },
);

// > Life Cycle
if (localStorage.prev) {
  localStore.loadPrev();
  [path.value, url.value, score.value, eRank.value, qRank.value] =
    localStore.getPrev.split(';');
  // console.log(route.fullPath);
} else {
  router.replace('error');
}

onMounted(async () => {
  if (url.value == '-') {
    if (mainStore.getTempImg) {
      console.log('임시사진');
      picBox.value.src = mainStore.getTempImg;
    } else {
      localStore.resetPrev();
      router.push('/main');
    }
  } else {
    console.log('기존사진');
    picBox.value.src = `https://j7b301.p.ssafy.io/api/image?imageId=${url.value}`;
  }
});
</script>

<style scoped>
.score-card {
  border-radius: 20px;
  width: 90%;
  max-width: 380px;
  overflow: hidden;
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
