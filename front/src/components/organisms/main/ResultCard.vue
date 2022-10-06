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
        style="background: lightgray; width: 380px; max-width: 380px"
      >
        <img ref="picBox" src="" style="width: 100%" alt="your image" />
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
        :download="`${photoName}`"
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

const downloadImage = async () => {
  let today = new Date();

  photoName.value = `${today.getFullYear()}-${
    today.getMonth() + 1
  }-${today.getDate()}-${today.getDay()}-${today.getHours()}-${today.getMinutes()}-${today.getSeconds()}.png`;

  if (localStore.getUrl == '-') {
    document
      .getElementById('downloadPhoto')
      .setAttribute(
        'href',
        mainStore.getTempImg.replace('image/png', 'image/octet-stream'),
      );
  } else {
    const payload = {
      imageQuery: localStore.getUrl,
      fileName: photoName,
    };

    const data = await image.get(payload);
    console.log(' * 이미지 다운로드: ', data);
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
{
  console.log('그냥 테스트');
  if (localStorage.prev) {
    localStore.loadPrev();
    [path.value, url.value, score.value, eRank.value, qRank.value] =
      localStore.getPrev.split(';');
    // console.log(route.fullPath);
  } else {
    router.replace('error');
  }
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

    // const data = await image.get(url.value);
    // console.log(' * 이미지 얻어지는 지, 테스트', data);
  }
});
</script>

<style scoped>
.score-card {
  margin-top: var(--grid-vertical);
  border-radius: 20px;
  width: calc(100% - 2 * var(--grid-side));
  max-width: 400px;
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
