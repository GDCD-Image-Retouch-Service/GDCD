<template>
  <div
    class="main-score d-flex flex-column align-items-center justify-content-center"
  >
    <button @click="getScore">점수반환</button>
    <div>{{ score }}</div>
  </div>
</template>

<script setup>
import { useMainStore } from '@/stores/main';
import { image } from '@/api/rest';
import { ref } from 'vue';

const mainStore = useMainStore();

const score = ref(0);

const getScore = async () => {
  score.value = await image.scoringInitial(mainStore.getTempImg);
};
</script>

<style scoped></style>
