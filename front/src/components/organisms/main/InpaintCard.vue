<template>
  <div
    class="inpaint-card main outer d-flex flex-column align-items-center justify-content-center"
  >
    <div class="spacer" />
    <div>지울 대상을 골라주세요</div>
    <div
      class="d-flex align-items-center"
      style="height: 48px; font-size: 18pt"
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

    <div class="pic-mode" v-show="!isLoading && !isDone">
      <div
        class="pic-container d-flex flex-column align-items-center justify-content-center"
        style="
          position: relative;
          background: lightgray;
          width: 380px;
          max-width: 380px;
          height: 380px;W
          max-height: 380px;
          over-flow: hidden;
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
            class="btn-object"
            @click="BtnActive(index)"
            :objectData="item"
            :naturalWidth="naturalWidth"
            :naturalHeight="naturalHeight"
          ></btn-object>
        </div>
      </div>
    </div>
    <!-- 바뀐 화면 -->
    <div class="inpaint-mode" v-show="isDone && !isLoading">
      <div
        class="inpaint-container d-flex flex-column align-items-center justify-content-center"
        style="
          position: relative;
          background: lightgray;
          width: 380px;
          max-width: 380px;
          height: 380px;W
          max-height: 380px;
          over-flow: hidden;
        "
      >
        <img
          ref="inpaintBox"
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

import { ref, onMounted } from 'vue';
import { image } from '@/api/rest';
import { useMainStore } from '@/stores';

const isDone = ref(false);

const isLoading = ref(true);
const mainStore = useMainStore();
const picBox = ref(null);
const inpaintBox = ref(null);
const objectList = ref(null);

const naturalWidth = ref(0);
const naturalHeight = ref(0);

const init = async () => {
  picBox.value.src = mainStore.getTempImg;

  naturalWidth.value = picBox.value.naturalWidth;
  naturalHeight.value = picBox.value.naturalHeight;

  const data = await image.objectDetection(mainStore.getTempId);
  console.log(data);
  objectList.value = data.item;
  isLoading.value = false;

  console.log(document.getElementsByClassName('btn-object-badge'));
  console.log(document.getElementsByClassName('btn-object'));
};

const BtnActive = (index) => {
  document
    .getElementsByClassName('btn-object-badge')
    [index].classList.toggle('btn-active');
  document.getElementsByClassName('btn-object')[index].classList.toggle('blur');
};

const inpainting = async () => {
  isLoading.value = true;
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
    imageId: mainStore.getTempId,
    objects: JSON.stringify(output),
  };

  const data = await image.inpainting(payload);
  console.log('after inpainting: ', data);
  console.log('after inpainting: ', data.item.image.imageUrl);
  inpaintBox.value.src = data.item.image.imageUrl;
  isDone.value = true;
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

.btn-object-badge {
  margin-left: 8px;
  margin-right: 8px;
  background: lightgray;
}

.btn-active {
  background: var(--color-theme);
}
</style>
