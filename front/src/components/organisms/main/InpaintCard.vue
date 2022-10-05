<template>
  <div
    class="inpaint-card main outer d-flex flex-column align-items-center justify-content-center"
  >
    <div class="spacer" />
    <div v-if="isLoading">사진을 분석 중 입니다...</div>
    <div v-if="!isLoading && !isWork">지울 대상을 골라주세요</div>
    <div v-if="isWork">지우고 있는 중 입니다...</div>
    <div
      v-if="!isLoading"
      class="d-flex align-items-center"
      style="width: 100%; height: 48px; font-size: 18pt; overflow-x: scroll"
    >
      <div v-for="(item, index) in objectList" :key="index">
        <span
          class="btn-object-badge badge rounded-pill"
          @click="BtnActive(index)"
          >{{ item.split(';')[0] }}</span
        >
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
          height: 380px;W
          max-height: 380px;
          overflow: hidden;
        "
      >
        <img
          ref="picBox"
          src=""
          style="width: 380px; height: 380px; object-fit: cover"
          alt="your image"
          @load="objectDetection"
        />

        <div v-for="(item, index) in objectList" :key="index">
          <btn-object
            class="btn-object"
            @click="BtnActive(index)"
            :objectData="item"
            :naturalWidth="naturalWidth"
            :naturalHeight="naturalHeight"
          ></btn-object>
        </div>
      </div>
    </div>

    <div class="spacer" />
    <div v-if="!isLoading" class="btn-set d-flex justify-content-center">
      <router-link
        to="/main/result"
        class="btn-set-button inner d-flex align-items-center justify-content-center"
      >
        <i class="bi bi-arrow-counterclockwise"></i>
      </router-link>

      <div
        @click="inpainting"
        class="btn-set-button inner d-flex align-items-center justify-content-center"
        style="margin-left: 8px"
      >
        <i class="bi bi-arrow-right"></i>
      </div>
    </div>
    <div class="spacer" />
  </div>
</template>

<script setup>
import LoadingDots from '@/components/atoms/LoadingDots.vue';
import BtnObject from '@/components/molecules/main/btn/BtnObject';

import { useRouter, useRoute } from 'vue-router';
import { ref, onMounted } from 'vue';
import { image } from '@/api/rest';
import { useLocalStore } from '@/stores';

// init
const localStore = useLocalStore();
const router = useRouter();
const route = useRoute();

// data
const path = ref('');
const url = ref(0);
const score = ref(0);
const eRank = ref(0);
const qRank = ref(0);

const isWork = ref(false);
const isLoading = ref(true);
const picBox = ref(null);

const objectList = ref(null);

const naturalWidth = ref(0);
const naturalHeight = ref(0);

const BtnActive = (index) => {
  document
    .getElementsByClassName('btn-object-badge')
    [index].classList.toggle('btn-active');
  document.getElementsByClassName('btn-object')[index].classList.toggle('blur');
};

const inpainting = async () => {
  isLoading.value = true;
  isWork.value = true;
  let output = [];
  const btnElList = document.getElementsByClassName('btn-object-badge');
  for (let i = 0; i < objectList.value.length; i++) {
    if (btnElList[i].classList.contains('btn-active')) {
      let splitData = objectList.value[i].split(';');
      let temp = [];

      temp.push((splitData[1].split(',')[0] *= 1));
      temp.push((splitData[1].split(',')[1] *= 1));
      temp.push((splitData[2].split(',')[0] *= 1));
      temp.push((splitData[2].split(',')[1] *= 1));
      output.push(temp);
    }
  }
  console.log('삭제하기', output);

  let payload = {
    imageId: url.value,
    objects: JSON.stringify(output),
  };

  const data = await image.inpainting(payload);
  console.log('* after inpainting: ', data);
  console.log('after inpainting: ', data.item.image.imageId);
  console.log('after inpainting: ', data.item.image.imageAesthetic);
  console.log('after inpainting: ', data.item.image.imageQuality);

  let id = data.item.image.imageId;
  let e = data.item.image.imageAesthetic;
  let q = data.item.image.imageQuality;

  // let e =
  //   eScore < 4.7
  //     ? 9
  //     : eScore < 4.9
  //     ? 8
  //     : eScore < 5.1
  //     ? 7
  //     : eScore < 5.2
  //     ? 6
  //     : eScore < 5.3
  //     ? 5
  //     : eScore < 5.4
  //     ? 4
  //     : eScore < 5.5
  //     ? 3
  //     : eScore < 5.7
  //     ? 2
  //     : 1;
  let eScore =
    e == 9
      ? 4.7
      : e == 8
      ? 4.9
      : e == 7
      ? 5.1
      : e == 6
      ? 5.2
      : e == 5
      ? 5.3
      : e == 4
      ? 5.4
      : e == 3
      ? 5.5
      : e == 2
      ? 5.7
      : 5.9;

  // let q =
  //   qScore < 4.7
  //     ? 9
  //     : qScore < 6.6
  //     ? 8
  //     : qScore < 6.7
  //     ? 7
  //     : qScore < 6.8
  //     ? 6
  //     : qScore < 6.85
  //     ? 5
  //     : qScore < 6.9
  //     ? 4
  //     : qScore < 6.95
  //     ? 3
  //     : qScore < 7.0
  //     ? 2
  //     : 1;

  let qScore =
    q == 9
      ? 4.7
      : q == 8
      ? 6.6
      : q == 7
      ? 6.7
      : q == 6
      ? 6.8
      : q == 5
      ? 6.85
      : q == 4
      ? 6.9
      : q == 3
      ? 6.95
      : q == 2
      ? 7.0
      : 7.1;

  let s = Math.ceil(
    (((eScore - 5.3) * 10 + (qScore - 6.8) * 10) / 2) * 10 + 50,
  );
  if (s > 100) s = 100;
  else if (s < 0) s = 0;

  localStore.setUrl(id);
  localStore.setScore(s);
  localStore.setERank(e);
  localStore.setQRank(q);

  console.log(' * 인페인팅 전', localStore.getPrev);
  localStore.setPrev();
  console.log(' * 인페인팅 후', localStore.getPrev);

  isLoading.value = false;
  router.push('/main/result');
};

const objectDetection = async () => {
  naturalWidth.value = picBox.value.naturalWidth;
  naturalHeight.value = picBox.value.naturalHeight;
  console.log('NW', naturalWidth.value);
  console.log('NH', naturalHeight.value);

  const data = await image.objectDetection(url.value);
  console.log(data);
  objectList.value = data.item;
  console.log(objectList.value);
  isLoading.value = false;

  console.log(document.getElementsByClassName('btn-object-badge'));
  console.log(document.getElementsByClassName('btn-object'));
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
    alert('분석 중 문제가 발생했습니다');
    localStore.resetPrev();
    router.replace('error');
  } else {
    picBox.value.src = `https://j7b301.p.ssafy.io/api/image?imageId=${url.value}`;
  }
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

.btn-object-badge {
  margin-left: 8px;
  margin-right: 8px;
  background: lightgray;
}

.btn-active {
  background: var(--color-theme);
}
</style>
