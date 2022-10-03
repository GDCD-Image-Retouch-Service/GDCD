<template>
  <div
    class="inpaint-card main outer d-flex flex-column align-items-center justify-content-center"
  >
    <div class="spacer" />
    <div>지울 대상을 골라주세요</div>
    <div
      class="d-flex align-items-center"
      style="height: 48px; font-size: 24pt"
    >
      <div v-for="(item, index) in objectList" :key="index">
        <div>{{ item.split(';')[0] }} ,</div>
      </div>
    </div>
    <div class="spacer" />

    <loading-dots v-if="isLoading" />

    <div class="pic-mode" v-show="!isLoading">
      <div
        class="pic-container d-flex flex-column align-items-center justify-content-center"
        style="
          position: relative;
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

        <div v-for="(item, index) in objectList" :key="index">
          <btn-object
            :objectData="item"
            :naturalWidth="naturalWidth"
            :naturalHeight="naturalHeight"
          ></btn-object>
        </div>
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
    </div>
    <div class="spacer" />
  </div>
</template>

<script setup>
import LoadingDots from '@/components/atoms/LoadingDots.vue';
import BtnObject from '@/components/molecules/main/btn/BtnObject';

import { ref, onMounted } from 'vue';
import { image } from '@/api/rest';
import { useMainStore } from '@/stores';

const isLoading = ref(true);
const mainStore = useMainStore();
const picBox = ref(null);
const objectList = ref(null);
const naturalWidth = ref(0);
const naturalHeight = ref(0);

const init = async () => {
  picBox.value.src = mainStore.getTempImg;

  naturalWidth.value = picBox.value.naturalWidth;
  naturalHeight.value = picBox.value.naturalHeight;

  const data = await image.objectDetection(mainStore.getTempId);
  console.log(data);
  objectList.value = data.item.slice(0, 6);
  isLoading.value = false;
};

onMounted(() => {
  init();
});
</script>

<style scoped>
.inpaint-card {
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
