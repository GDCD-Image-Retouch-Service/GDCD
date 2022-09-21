<template>
  <div
    class="btn-change-mode inner d-flex align-items-center justify-content-center camera-mode"
    @click="changeMode()"
    ref="btnChangeMode"
  >
    <div class="btn-slider outer" :style="{ left: leftPos }"></div>
    <icon-picture />
    <icon-camera />
  </div>
</template>

<script setup>
import IconCamera from '@/components/atoms/IconCamera.vue';
import IconPicture from '@/components/atoms/IconPicture.vue';

import { ref } from 'vue';
// const leftPos = ref('4px');

const btnChangeMode = ref(null);

const changeMode = () => {
  // document.querySelector('.account-sign')
  btnChangeMode.value.classList.toggle('camera-mode');
};
</script>

<style scoped>
.btn-change-mode {
  position: relative;
  overflow: hidden;
  height: var(--size-h-header);
  width: 90%;
  max-width: 380px;
  background: var(--theme-color);
  font-size: 12pt;
  border-radius: calc(var(--size-h-header) / 2);
}
.btn-slider {
  z-index: 2;
  position: absolute;
  left: calc(0% + 4px);
  /* left: calc(50% + 4px); */
  height: calc(var(--size-h-header) - 8px);
  line-height: var(--size-h-header);
  border-radius: var(--size-radius);
  font-size: 20pt;
  width: calc(50% - 8px);
  background: white;

  -webkit-transition: 0.5s ease-in-out;
  -ms-transition: 0.5s ease-in-out;
  transition: 0.5s ease-in-out;
}

.camera-mode .btn-slider {
  left: calc(50% + 4px);
}
</style>
