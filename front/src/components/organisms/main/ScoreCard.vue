<template>
  <div
    class="score-card outer d-flex flex-column align-items-center justify-content-center"
  >
    <div class="spacer" />
    <btn-change-mode />
    <div class="spacer" />

    <loading-dots v-if="isLoading" />

    <div class="pic-mode">
      <div>{{ data }}</div>
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
      <div class="spacer" />
      <div class="btn-set d-flex justify-content-center" v-if="!isLoading">
        <!-- download 버튼 -->
        <a
          class="btn-set-button inner d-flex align-items-center justify-content-center"
          @click="downloadImage"
        >
          <i class="bi bi-download"></i>
        </a>

        <router-link
          to="/"
          class="btn-set-button inner d-flex align-items-center justify-content-center"
        >
          <i class="bi bi-download"></i>
        </router-link>
      </div>
      <div class="spacer" />
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { image } from '@/api/rest';
import { useMainStore } from '@/stores/main';

const mainStore = useMainStore();

const picBox = ref(null);
const data = ref(null);

const init = async () => {
  picBox.value.src = mainStore.getTempImg;
  console.log(mainStore.getTempFile);

  data.value = await image.scoringInitial(mainStore.getTempFile);
};

onMounted(() => {
  init();
});
</script>

<style scoped>
.upload-card {
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
