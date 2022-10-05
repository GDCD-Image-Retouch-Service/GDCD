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
import { useMainStore, useAccountStore } from '@/stores/';
import { useRouter, useRoute } from 'vue-router';

// init
const router = useRouter();
const route = useRoute();
const mainStore = useMainStore();
const accountStore = useAccountStore();

// data
const prev = ref('');
const imageId = ref('');
const score = ref('');
const eRank = ref('');
const qRank = ref('');

const isLoading = ref(false);
const picBox = ref(null);
// const data = ref(null);

// method
const save = async () => {
  if (!accountStore.getIsLogined) {
    console.log('비로그인');
    return;
  }
  const payload = {
    image: mainStore.getTempFile,
    aesthetic: mainStore.getTempEScore,
    quality: mainStore.getTempQScore,
  };
  console.log('저장시작', payload);
  const data = await image.save(payload);
  console.log('저장상태', data);

  mainStore.setTempId(data.item);
};

// Watch
watch(
  () => accountStore.getIsLogined,
  () => {
    if (mainStore.getTempEScore != 0) save();
  },
);

// > Life Cycle
// test===========================
// console.log('테스트 스텝 입력');
// const testData = '/main;257;89;2;1';
// localStorage.setItem('prev', testData);
// ================================

if (localStorage.prev) {
  const step = localStorage.prev.split(';');
  console.log(step);

  [prev.value, imageId.value, score.value, eRank.value, qRank.value] =
    localStorage.prev.split(';');
  console.log(prev.value);
  console.log(imageId.value);
  console.log(score.value);
  console.log(eRank.value);
  console.log(qRank.value);

  console.log(route.fullPath);
} else {
  router.replace('error');
}

onMounted(async () => {
  picBox.value.src = `https://j7b301.p.ssafy.io/api/image?imageId=${imageId.value}`;
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
