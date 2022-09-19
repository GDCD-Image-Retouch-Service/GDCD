<template>
  <div
    class="btn-change-mode inner d-flex align-items-center justify-content-center"
    @click="changeMode()"
  >
    <icon-camera />
    <icon-picture />
  </div>
</template>

<script setup>
import IconCamera from '@/components/atoms/IconCamera.vue';
import IconPicture from '@/components/atoms/IconPicture.vue';

import { ref } from 'vue';
const width = ref(0);

const changeMode = () => {
  width.value = 10;
};
</script>

<style scoped>
.btn-change-mode {
  height: var(--size-h-header);
  width: 90%;
  max-width: 380px;
  /* background: var(--theme-color); */
  font-size: 12pt;
  border-radius: calc(var(--size-h-header) / 2);
}
</style>
