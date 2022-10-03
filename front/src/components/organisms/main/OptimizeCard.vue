<template>
  <div
    class="optimize-card main outer d-flex flex-column align-items-center justify-content-center"
  >
    <div v-if="isLoading" class="spacer" />
    <div
      v-if="isLoading"
      class="d-flex align-items-center"
      style="height: 48px; font-size: 12pt"
    >
      <div>사진을 최적화하는 중입니다 {{ progress }}/7</div>
    </div>
    <div v-if="isLoading" class="spacer" />

    <loading-dots v-if="isLoading" />

    <div
      v-show="!isLoading"
      id="carouselExampleCaptions"
      class="carousel slide"
      :class="[$root.theme == 'light' ? '' : 'carousel-dark']"
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
              <!-- score 보여줄 공간 -->
              <div class="spacer" />
              <div
                class="d-flex align-items-center justify-content-evenly"
                style="
                  height: 48px;
                  width: 100%;
                  max-width: 100%;
                  font-size: 12pt;
                "
              >
                <div class="d-flex flex-column align-items-center">
                  <icon-rank
                    :rank="Math.ceil(9 - (mainStore.getTempScore * 8) / 100)"
                  />
                  <div style="height: 8px" />
                  <div>총점</div>
                </div>
                <div class="d-flex flex-column align-items-center">
                  <icon-rank :rank="mainStore.getTempEScore" />
                  <div style="height: 8px" />
                  <div>심미성</div>
                </div>
                <div class="d-flex flex-column align-items-center">
                  <icon-rank :rank="mainStore.getTempQScore" />
                  <div style="height: 8px" />
                  <div>선명도</div>
                </div>
              </div>
              <div class="spacer" />

              <img
                ref="picBox"
                src=""
                style="width: 380px; height: 380px; object-fit: cover"
              />
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
              <!-- score 보여줄 페공간 -->
              <div class="spacer" />
              <div
                class="d-flex align-items-center justify-content-evenly"
                style="
                  height: 48px;
                  width: 100%;
                  max-width: 100%;
                  font-size: 12pt;
                "
              >
                <div class="d-flex flex-column align-items-center">
                  <icon-rank :rank="Math.ceil(9 - (opti.score * 8) / 100)" />
                  <div style="height: 8px" />
                  <div>총점</div>
                </div>
                <div class="d-flex flex-column align-items-center">
                  <icon-rank :rank="opti.e" />
                  <div style="height: 8px" />
                  <div>심미성</div>
                </div>
                <div class="d-flex flex-column align-items-center">
                  <icon-rank :rank="opti.q" />
                  <div style="height: 8px" />
                  <div>선명도</div>
                </div>
              </div>
              <div class="spacer" />

              <img
                :src="opti.url"
                style="width: 380px; height: 380px; object-fit: cover"
              />
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
        v-if="!isLoading"
        @click="optimizeSave"
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
import IconRank from '@/components/atoms/IconRank.vue';

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
  selectNo.value = selectNo.value == 0 ? 7 : selectNo.value - 1;
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

const optimizeSave = async () => {
  const photoNo = selectNo.value;
  const payload = {
    imageId: mainStore.getTempId,
    imageUrl: mainStore.getTempOptiList[photoNo].url,
    aesthetic: mainStore.getTempOptiList[photoNo].e,
    quality: mainStore.getTempOptiList[photoNo].q,
  };

  console.log('저장값', payload);

  const data = await image.optimizingSave(payload);
  console.log('데이터', data);
};

const optimize = async () => {
  mainStore.resetTempOptiList();
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
        let aesthetic = optiList.value[i][url]['aesthetic'];
        let quality = optiList.value[i][url]['quality'];

        let e = Math.ceil((5.8 - aesthetic) * 14 + 1);
        if (e > 9) e = 9;
        else if (e < 1) e = 1;

        let q = Math.ceil((6.8 - quality) * 14 + 1);
        if (q > 9) q = 9;
        else if (q < 1) q = 1;

        let score = Math.ceil(
          (((aesthetic - 5.5) * 10 + (quality - 6.5) * 10) / 2) * 10 + 50,
        );
        if (score > 100) score = 100;
        else if (score < 0) score = 0;

        mainStore.pushTempOptiList({
          url: url,
          e: e,
          q: q,
          score: score,
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
