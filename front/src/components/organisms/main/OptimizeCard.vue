<template>
  <div
    class="optimize-card main outer d-flex flex-column align-items-center justify-content-center"
  >
    <div class="spacer" />
    <div
      class="d-flex align-items-center"
      style="height: 48px; font-size: 12pt"
    >
      <!-- <img
        :src="require('@/assets/grade/1.png')"
        style="width: 48px; height: 48px; object-fit: cover"
        alt="your image"
      /> -->
      <div v-if="isLoading">사진을 최적화하는 중입니다 {{ progress }}/7</div>
      <!-- <div>iId:{{ mainStore.getTempId }}</div>
      <div style="margin-left: 8px">rId:{{ mainStore.getRequestId }}</div> -->
    </div>
    <div class="spacer" />

    <loading-dots v-if="!isLoading" />

    <div
      id="carouselExampleCaptions"
      class="carousel slide"
      :class="[$root.theme ? '' : 'carousel-dark']"
      data-bs-ride="false"
      style="width: 100%"
    >
      <!-- carousel bottom button -->
      <div class="carousel-indicators">
        <button
          type="button"
          data-bs-target="#carouselExampleCaptions"
          data-bs-slide-to="0"
          class="active"
          aria-current="true"
          aria-label="Slide 1"
        ></button>
        <button
          type="button"
          data-bs-target="#carouselExampleCaptions"
          data-bs-slide-to="1"
          aria-label="Slide 2"
        ></button>
      </div>

      <!-- carousel contents -->
      <div class="carousel-inner" style="height: 100%">
        <div class="carousel-item active" style="height: 100%">
          <div class="carousel-item-1 itemBox d-flex" style="height: 100%">
            <div
              class="d-flex flex-column align-items-center justify-content-center"
              style="height: 100%; width: 400px"
            >
              <div class="textBox">
                <img
                  ref="picBox"
                  src=""
                  style="width: 380px; height: 380px; object-fit: cover"
                  alt="your image"
                />
                <h2>원본</h2>
                <h2>점수</h2>
              </div>
            </div>
            <div class="carousel-item-1-img flex-grow-1"></div>
          </div>
        </div>

        <div class="carousel-item" style="height: 100%">
          <div class="carousel-item-2 itemBox d-flex" style="height: 100%">
            <div class="container-fluid" style="height: 100%; width: 100%">
              <div class="row" style="height: 100%; width: 100%">
                <div
                  class="textBox col-12 col-sm-4"
                  style="padding-top: 24vh; padding-left: 8%"
                >
                  <h2>
                    <span>사진</span>
                  </h2>
                  <div class="spacer"></div>
                  <p>optimize 점수 내역:</p>
                  <p>tag</p>
                </div>

                <div
                  class="carousel-item-2-img col-12 col-sm-8"
                  :style="{
                    'min-height': '50%',
                    'background-image':
                      'url(' + require('@/assets/grade/1.png') + ')',
                    'background-size': 'contain',
                    'background-repeat': 'no-repeat',
                    'background-position': 'center',
                  }"
                ></div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- carousel side button -->
      <button
        class="carousel-control-prev"
        type="button"
        data-bs-target="#carouselExampleCaptions"
        data-bs-slide="prev"
        style="width: 10%"
      >
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
      </button>
      <button
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
      <div
        @click="optimize"
        class="btn-set-button inner d-flex align-items-center justify-content-center"
        style="margin-left: 8px"
      >
        <i class="bi bi-stars"></i>
      </div>

      <div
        @click="process"
        class="btn-set-button inner d-flex align-items-center justify-content-center"
        style="margin-left: 8px"
      >
        <i class="bi bi-cloud-check-fill"></i>
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
const progress = ref(0);
const optiList = ref([]);

// method
const init = async () => {
  picBox.value.src = mainStore.getTempImg;
  // isLoading.value = false;
};

const optimize = async () => {
  const data = await image.optimization(mainStore.getTempId);
  console.log(data);
  mainStore.setRequestId(data.item.requestId);
};

const process = async () => {
  const data = await image.optimizingProcess(mainStore.getRequestId);
  console.log(data);
  progress.value = data.item.progress;
  if (progress.value != 7) {
    setTimeout(process, 10000);
  } else {
    optiList.value = data.item.dict;
    console.log(optiList.value);
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
