<template>
  <div
    class="optimize-card main outer d-flex flex-column align-items-center justify-content-center"
  >
    <div class="spacer" />
    <div
      class="d-flex align-items-center"
      style="height: 48px; font-size: 12pt"
    >
      <div v-if="isLoading">사진을 최적화하는 중입니다 {{ progress }}/7</div>
    </div>
    <div class="spacer" />

    <loading-dots v-if="isLoading" />

    <div
      v-show="!isLoading"
      id="carouselExampleCaptions"
      class="carousel slide"
      :class="[$root.theme ? '' : 'carousel-dark']"
      data-bs-ride="false"
      style="width: 100%"
    >
      <!-- carousel bottom button -->
      <div class="carousel-indicators">
        <button
          @click="setSelectNo(0)"
          type="button"
          data-bs-target="#carouselExampleCaptions"
          data-bs-slide-to="0"
          class="active"
          aria-current="true"
          aria-label="Slide 1"
        ></button>
        <button
          @click="setSelectNo(index + 1)"
          v-for="(opti, index) in mainStore.getTempOptiList"
          :key="index"
          type="button"
          data-bs-target="#carouselExampleCaptions"
          :data-bs-slide-to="`${index + 1}`"
          :aria-label="`Slide ${index + 2}`"
        ></button>
      </div>

      <!-- carousel contents -->
      <div
        class="carousel-inner"
        style="height: 100%; width: 100%; overflow: hidden"
      >
        <div class="carousel-item active" style="height: 100%">
          <div class="carousel-item-1 itemBox d-flex" style="height: 100%">
            <div
              class="d-flex flex-column align-items-center justify-content-center"
            >
              <div class="textBox">
                <img
                  ref="picBox"
                  src=""
                  style="width: 380px; height: 380px; object-fit: cover"
                />
                <div>에스테</div>
                <div>퀄리티</div>
              </div>
            </div>
          </div>
        </div>

        <div
          v-for="(opti, index) in mainStore.getTempOptiList"
          :key="index"
          class="carousel-item"
          style="height: 100%; width: 100%; overflow: hidden"
        >
          <div
            :class="`carousel-item-${index + 2} itemBox d-flex`"
            style="height: 100%"
          >
            <div
              class="d-flex flex-column align-items-center justify-content-center"
            >
              <div class="textBox">
                <img
                  :src="opti.url"
                  style="width: 380px; height: 380px; object-fit: cover"
                  alt="your image"
                />
                <div>에스테{{ opti.e }}</div>
                <div>퀄리티{{ opti.q }}</div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- carousel side button -->
      <button
        class="carousel-control-prev"
        @click="setSelectNoDown"
        type="button"
        data-bs-target="#carouselExampleCaptions"
        data-bs-slide="prev"
        style="width: 10%"
      >
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
      </button>
      <button
        @click="setSelectNoUp"
        class="carousel-control-next"
        type="button"
        data-bs-target="#carouselExampleCaptions"
        data-bs-slide="next"
        style="width: 10%"
      >
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
      </button>
    </div>

    <div class="spacer" />
    <div class="btn-set d-flex justify-content-center">
      <router-link
        to="/main/score"
        class="btn-set-button inner d-flex align-items-center justify-content-center"
      >
        <i class="bi bi-arrow-counterclockwise"></i>
      </router-link>
      <div
        class="btn-set-button inner d-flex align-items-center justify-content-center"
        style="margin-left: 8px"
      >
        <i class="bi bi-check-all"></i>
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
import { useRoute } from 'vue-router';

// init
const mainStore = useMainStore();
const route = useRoute();

// data
const isLoading = ref(true);
const picBox = ref(null);
const progress = ref(0);
const optiList = ref([]);
const selectNo = ref(0);

// method
const setSelectNo = (no) => {
  selectNo.value = no;
  console.log('변화', selectNo.value);
};

const setSelectNoUp = () => {
  selectNo.value = (selectNo.value + 1) % 8;
  console.log('업', selectNo.value);
};

const setSelectNoDown = () => {
  selectNo.value = selectNo.value == 0 ? 6 : selectNo.value - 1;
  console.log('다운', selectNo.value);
};

const init = async () => {
  picBox.value.src = mainStore.getTempImg;
  console.log('최적화 시작');
  optimize();

  // if (mainStore.getTempOptiList.length == 0) {
  //   console.log('최적화 시작');
  //   optimize();
  // } else {
  //   console.log('이미 최적화되어있음', mainStore.getTempOptiList);
  //   optimize();
  //   isLoading.value = false;
  // }
};

// const optimizeSave = async (photoNo) => {
//   const payload = {
//     imageId: mainStore.getTempId,
//     imageUrl: mainStore.getTempOptiList[photoNo].url,
//     aesthetic: mainStore.getTempOptiList[photoNo].e,
//     quality: mainStore.getTempOptiList[photoNo].q,
//   };

//   console.log('저장값', payload);

//   const data = await image.optimizingSave(payload);
//   console.log('데이터', data);
// };

const optimize = async () => {
  const data = await image.optimization(mainStore.getTempId);
  console.log('최적화 요청 전달', data);
  mainStore.setRequestId(data.item.requestId);
  process();
};

const process = async () => {
  if (route.fullPath != '/main/optimize') {
    console.log('최적화 통신 에러');
    return;
  }
  const data = await image.optimizingProcess(mainStore.getRequestId);
  if (data) {
    console.log('최적화 통신중', data.item.progress);
    progress.value = data.item.progress;

    if (progress.value != 7 && isLoading.value) {
      setTimeout(process, 10000);
    } else {
      optiList.value = data.item.dict;

      for (var i = 0; i < 7; i++) {
        let url = Object.keys(optiList.value[i])[0];

        mainStore.pushTempOptiList({
          url: url,
          e: optiList.value[i][url]['aesthetic'],
          q: optiList.value[i][url]['quality'],
        });
      }

      console.log('최적화 통신 종료', mainStore.getTempOptiList);
      isLoading.value = false;
    }
  } else {
    console.log('최적화 통신 에러');
    isLoading.value = false;
  }
};

// Life Cycle
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
