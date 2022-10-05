<template>
  <div
    class="optimize-card main outer d-flex flex-column align-items-center justify-content-center"
  >
    <div class="spacer" />
    <div
      v-if="isLoading"
      class="d-flex align-items-center"
      style="height: 48px; font-size: 12pt"
    >
      <div>사진을 최적화하는 중입니다 {{ progress }}/7</div>
    </div>
    <div v-if="isLoading" class="spacer" />

    <loading-dots v-if="isLoading" />

    <div v-if="!isLoading">저장할 사진을 골라주세요</div>
    <div v-if="!isLoading" class="spacer" />

    <div
      v-show="!isLoading"
      id="carouselExampleCaptions"
      class="carousel slide"
      :class="[mainStore.getIsDark ? 'carousel-dark' : '']"
      data-bs-ride="false"
      style="width: 100%"
    >
      <!-- carousel bottom button -->
      <!-- <div class="carousel-indicators">
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
      </div> -->

      <!-- carousel contents -->
      <div class="carousel-inner" style="height: 100%; width: 100%">
        <div class="carousel-item active" style="height: 100%; width: 100%">
          <div
            class="carousel-item-1 itemBox d-flex"
            style="height: 100%; width: 100%"
          >
            <div
              class="d-flex flex-column align-items-center justify-content-center"
            >
              <!-- score 보여줄 공간 -->
              <div class="spacer" />
              <div
                class="d-flex align-items-center justify-content-evenly"
                style="height: 80px; width: 100%; font-size: 12pt"
              >
                <div class="d-flex flex-column align-items-center">
                  <icon-rank :rank="Math.ceil(9 - (score * 8) / 100)" />
                  <div style="height: 8px" />
                  <div>총점</div>
                </div>
                <div class="d-flex flex-column align-items-center">
                  <icon-rank :rank="eRank" />
                  <div style="height: 8px" />
                  <div>심미성</div>
                </div>
                <div class="d-flex flex-column align-items-center">
                  <icon-rank :rank="qRank" />
                  <div style="height: 8px" />
                  <div>선명도</div>
                </div>
              </div>
              <div class="spacer" />

              <img ref="picBox" src="" style="width: 100%" />

              <div class="spacer" />
              <div class="btn-set d-flex justify-content-center">
                <!-- Btn Back -->
                <router-link
                  to="/main/result"
                  class="btn-set-button inner d-flex align-items-center justify-content-center"
                >
                  <i class="bi bi-arrow-counterclockwise"></i>
                </router-link>

                <!-- Btn Download -->
                <div
                  class="btn-set-button inner d-flex align-items-center justify-content-center"
                  style="margin-left: 8px"
                  @click="downloadImage()"
                >
                  <i class="bi bi-download"></i>
                </div>

                <!-- Btn Server Upload -->
                <div
                  @click="optimizeSave"
                  class="btn-set-button inner d-flex align-items-center justify-content-center"
                  style="margin-left: 8px"
                >
                  <i class="bi bi-cloud-arrow-up-fill"></i>
                </div>
              </div>
            </div>
          </div>
        </div>

        <div
          v-for="(opti, index) in convertList"
          :key="index"
          class="carousel-item"
          style="height: 100%; width: 100%; overflow: hidden"
        >
          <div
            :class="`carousel-item-${index + 2} itemBox d-flex`"
            style="height: 100%; width: 100%"
          >
            <div
              class="d-flex flex-column align-items-center justify-content-center"
            >
              <!-- score 보여줄 공간 -->
              <div class="spacer" />
              <div
                class="d-flex flex-wrap align-items-center"
                style="width: 100%; font-size: 12pt"
              >
                <div class="flex-grow-1 flex-shrink-1"></div>
                <div class="d-flex flex-column align-items-center">
                  <icon-rank :rank="Math.ceil(9 - (opti.score * 8) / 100)" />
                  <div style="height: 8px" />
                  <div>총점</div>
                </div>
                <div class="flex-grow-1 flex-shrink-1"></div>
                <div class="d-flex flex-column align-items-center">
                  <icon-rank :rank="opti.e" />
                  <div style="height: 8px" />
                  <div>심미성</div>
                </div>
                <div class="flex-grow-1 flex-shrink-1"></div>
                <div class="d-flex flex-column align-items-center">
                  <icon-rank :rank="opti.q" />
                  <div style="height: 8px" />
                  <div>선명도</div>
                </div>
              </div>
              <div class="spacer" />

              <img :src="opti.url" class="optimizedImg" style="width: 100%" />

              <div class="spacer" />
              <div class="btn-set d-flex justify-content-center">
                <!-- Btn Back -->
                <router-link
                  to="/main/result"
                  class="btn-set-button inner d-flex align-items-center justify-content-center"
                >
                  <i class="bi bi-arrow-counterclockwise"></i>
                </router-link>

                <!-- Btn Download -->
                <div
                  class="btn-set-button inner d-flex align-items-center justify-content-center"
                  style="margin-left: 8px"
                  @click="downloadImage(2)"
                >
                  <!-- 여기코드 기반 저장로직 변경 필요 -->
                  <i class="bi bi-download"></i>
                </div>

                <!-- Btn Server Upload -->
                <div
                  @click="optimizeSave"
                  class="btn-set-button inner d-flex align-items-center justify-content-center"
                  style="margin-left: 8px"
                >
                  <i class="bi bi-cloud-arrow-up-fill"></i>
                </div>
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
        style="width: 30%"
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
        style="width: 30%"
      >
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
      </button>
    </div>

    <div class="spacer" />
    <div class="btn-set d-flex justify-content-center">
      <!-- Btn Back -->
      <router-link
        to="/main/result"
        class="btn-set-button inner d-flex align-items-center justify-content-center"
      >
        <i class="bi bi-arrow-counterclockwise"></i>
      </router-link>

      <!-- Btn Download -->
      <div
        v-if="!isLoading"
        class="btn-set-button inner d-flex align-items-center justify-content-center"
        style="margin-left: 8px"
        @click="downloadImage"
      >
        <i class="bi bi-download"></i>
      </div>

      <!-- Btn Server Upload -->
      <div
        v-if="!isLoading"
        @click="optimizeSave"
        class="btn-set-button inner d-flex align-items-center justify-content-center"
        style="margin-left: 8px"
      >
        <i class="bi bi-cloud-arrow-up-fill"></i>
      </div>
    </div>
    <div class="spacer" />
  </div>
</template>

<script setup>
import LoadingDots from '@/components/atoms/LoadingDots.vue';
import IconRank from '@/components/atoms/IconRank.vue';
// import swal from 'sweetalert2';

import { ref, onMounted } from 'vue';
import { image } from '@/api/rest';
import { useMainStore, useLocalStore } from '@/stores';
import { useRouter, useRoute } from 'vue-router';

// init
const mainStore = useMainStore();
const localStore = useLocalStore();
const router = useRouter();
const route = useRoute();

// data
const path = ref('');
const url = ref('');
const score = ref('');
const eRank = ref('');
const qRank = ref('');

const isLoading = ref(true);
const picBox = ref(null);
const progress = ref(0);
const optiList = ref([]);
const convertList = ref([]);
const requestId = ref(0);
const selectNo = ref(0);

// method
// const setSelectNo = (no) => {
//   selectNo.value = no;
//   console.log('변화', selectNo.value);
// };

const setSelectNoUp = () => {
  selectNo.value = (selectNo.value + 1) % 8;
  console.log('업', selectNo.value);
};

const setSelectNoDown = () => {
  selectNo.value = selectNo.value == 0 ? 7 : selectNo.value - 1;
  console.log('다운', selectNo.value);
};

const downloadImage = async (imageQuery) => {
  let today = new Date();
  let fileName = `${today.getFullYear()}-${
    today.getMonth() + 1
  }-${today.getDate()}-${today.getDay()}-${today.getHours()}-${today.getMinutes()}-${today.getSeconds()}.jpg`;

  // const payload = {
  //   imageId: url.value,
  //   fileName,
  // };
  const payload = {
    imageQuery,
    fileName,
  };

  const data = await image.get(payload);
  console.log(' * 이미지 다운로드: ', data);
};

const optimizeSave = async () => {
  const photoNo = selectNo.value;
  const payload = {
    imageId: url.value,
    imageUrl: convertList.value[photoNo].url,
    aesthetic: convertList.value[photoNo].e,
    quality: convertList.value[photoNo].q,
  };

  console.log(' * 최적화 저장값', payload);

  const data = await image.optimizingSave(payload);
  console.log(' * 최적화 사진ID', data);
  localStore.setUrl(data.item);
  localStore.setPrev();

  router.push('/main/result');
};

const optimize = async () => {
  // mainStore.resetTempOptiList();
  convertList.value = [];
  console.log(' * 최적화 요청 전달 사진번호 : ', url.value);
  const data = await image.optimization(url.value);
  console.log(' * 최적화 요청 전달 성공 : ', data);
  // mainStore.setRequestId(data.item.requestId);
  requestId.value = data.item.requestId;
  process();
};

const process = async () => {
  if (route.fullPath != '/main/optimize') {
    console.log('최적화 통신 에러');
    return;
  }
  const data = await image.optimizingProcess(requestId.value);
  if (data) {
    console.log('최적화 통신중', data.item.progress);
    progress.value = data.item.progress;

    if (progress.value != 7 && isLoading.value) {
      setTimeout(process, 10000);
    } else {
      console.log(' * 최적화 결과 data: ', data);
      optiList.value = data.item.dict;

      for (var i = 0; i < 7; i++) {
        let url = Object.keys(optiList.value[i])[0];
        let eScore = optiList.value[i][url]['aesthetic'];
        let qScore = optiList.value[i][url]['quality'];

        let e =
          eScore < 4.7
            ? 9
            : eScore < 4.9
            ? 8
            : eScore < 5.1
            ? 7
            : eScore < 5.2
            ? 6
            : eScore < 5.3
            ? 5
            : eScore < 5.4
            ? 4
            : eScore < 5.5
            ? 3
            : eScore < 5.7
            ? 2
            : 1;

        let q =
          qScore < 4.7
            ? 9
            : qScore < 6.6
            ? 8
            : qScore < 6.7
            ? 7
            : qScore < 6.8
            ? 6
            : qScore < 6.85
            ? 5
            : qScore < 6.9
            ? 4
            : qScore < 6.95
            ? 3
            : qScore < 7.0
            ? 2
            : 1;

        let score = Math.ceil(
          (((eScore - 5.3) * 10 + (qScore - 6.8) * 10) / 2) * 10 + 50,
        );
        if (score > 100) score = 100;
        else if (score < 0) score = 0;

        convertList.value.push({
          url: url,
          e: e,
          q: q,
          score: score,
        });
      }

      console.log('최적화 통신 종료', convertList);
      isLoading.value = false;
    }
  } else {
    console.log('최적화 통신 에러');
    isLoading.value = false;
  }
};

// Life Cycle
if (localStorage.prev) {
  localStore.loadPrev();
  [path.value, url.value, score.value, eRank.value, qRank.value] =
    localStore.getPrev.split(';');
  console.log(route.fullPath);
} else {
  router.replace('error');
}

onMounted(async () => {
  if (url.value == '-') {
    alert('최적화 중 문제가 발생했습니다');
    localStore.resetPrev();
    router.replace('error');
  } else {
    picBox.value.src = `https://j7b301.p.ssafy.io/api/image?imageId=${url.value}`;

    console.log(' * 최적화 시작');
    optimize();
  }
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
