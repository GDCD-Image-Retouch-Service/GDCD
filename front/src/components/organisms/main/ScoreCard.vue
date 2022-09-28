<template>
  <div
    class="score-card outer d-flex flex-column align-items-center justify-content-center"
  >
    <div class="spacer" />
    <div class="d-flex align-items-center" style="font-size: 24pt">
      <img
        v-if="score > 90"
        :src="require('@/assets/grade/1.png')"
        style="width: 48px; height: 48px; object-fit: cover"
        alt="your image"
      />
      <img
        v-else-if="score > 80"
        :src="require('@/assets/grade/2.png')"
        style="width: 48px; height: 48px; object-fit: cover"
        alt="your image"
      />
      <img
        v-else-if="score > 70"
        :src="require('@/assets/grade/3.png')"
        style="width: 48px; height: 48px; object-fit: cover"
        alt="your image"
      />
      <img
        v-else-if="score > 60"
        :src="require('@/assets/grade/4.png')"
        style="width: 48px; height: 48px; object-fit: cover"
        alt="your image"
      />
      <img
        v-else-if="score > 50"
        :src="require('@/assets/grade/5.png')"
        style="width: 48px; height: 48px; object-fit: cover"
        alt="your image"
      />
      <img
        v-else-if="score > 40"
        :src="require('@/assets/grade/6.png')"
        style="width: 48px; height: 48px; object-fit: cover"
        alt="your image"
      />
      <img
        v-else-if="score > 30"
        :src="require('@/assets/grade/7.png')"
        style="width: 48px; height: 48px; object-fit: cover"
        alt="your image"
      />
      <img
        v-else-if="score > 20"
        :src="require('@/assets/grade/8.png')"
        style="width: 48px; height: 48px; object-fit: cover"
        alt="your image"
      />
      <img
        v-else
        :src="require('@/assets/grade/9.png')"
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
          background: #000000;
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
        to="/"
        class="btn-set-button inner d-flex align-items-center justify-content-center"
      >
        <i class="bi bi-arrow-counterclockwise"></i>
      </router-link>

      <router-link
        to="/optimize"
        class="btn-set-button inner d-flex align-items-center justify-content-center"
        style="margin-left: 8px"
      >
        <i class="bi bi-stars"></i>
      </router-link>
    </div>
    <div class="spacer" />
  </div>
</template>

<script setup>
import LoadingDots from '@/components/atoms/LoadingDots.vue';

import { ref, onMounted } from 'vue';
import { image } from '@/api/rest';
import { useMainStore } from '@/stores/main';

const mainStore = useMainStore();

const isLoading = ref(true);
const picBox = ref(null);
const data = ref(null);
const score = ref(0);

const init = async () => {
  picBox.value.src = mainStore.getTempImg;

  data.value = await image.scoringInitial(mainStore.getTempFile);
  score.value = mainStore.getScore(data.value.item);
  isLoading.value = false;
};

onMounted(() => {
  init();
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
