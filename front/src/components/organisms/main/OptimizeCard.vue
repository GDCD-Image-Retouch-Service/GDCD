<template>
  <div
    class="optimize-card main outer d-flex flex-column align-items-center justify-content-center"
  >
    <div class="spacer" />
    <div class="d-flex align-items-center" style="font-size: 24pt">
      <img
        :src="require('@/assets/grade/1.png')"
        style="width: 48px; height: 48px; object-fit: cover"
        alt="your image"
      />
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
      <div
        @click="save"
        class="btn-set-button inner d-flex align-items-center justify-content-center"
        style="margin-left: 8px"
      >
        <i class="bi bi-stars"></i>
      </div>
    </div>
    <div class="spacer" />
  </div>
</template>

<script setup>
import LoadingDots from '@/components/atoms/LoadingDots.vue';

import { ref, onMounted } from 'vue';
import { image } from '@/api/rest';
import { useMainStore } from '@/stores/main';

// init
const mainStore = useMainStore();

// data
const isLoading = ref(true);
const picBox = ref(null);
// const data = ref(null);
const score = ref(0);

// method
const save = async () => {
  console.log('저장시작');
  await image.save({
    image: mainStore.getTempFile,
    aesthetic: mainStore.getTempEScore,
    quality: mainStore.getTempQScore,
  });
};

const init = async () => {
  picBox.value.src = mainStore.getTempImg;

  // console.log(data.value);
  // score.value = mainStore.setScore(data.item);
  // console.log(score.value);
  // score.value = 0;
  isLoading.value = false;
};

onMounted(() => {
  init();
});
</script>

<style scoped>
.optimize-card {
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
