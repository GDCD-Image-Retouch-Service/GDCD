<template>
  <div v-if="isClick" class="following-button" @click="isClick = !isClick">
    언팔로우
  </div>
  <div v-else class="follow-button" @click="isClick = !isClick">+팔로우</div>
</template>

<script setup>
import { ref } from 'vue';
const isClick = ref(true);
</script>

<style scoped>
.following-button {
  width: 100px;
  padding: 4px 14px;
  height: 30px;
  line-height: 22px;
  text-align: center;
  border: 1px solid #dfdfdf;
  color: var(--color-main);
  background-color: var(--theme-color);
  border-radius: 20px;
}
.follow-button {
  width: 90px;
  padding: 4px 14px;
  height: 30px;
  line-height: 22px;
  text-align: center;
  border: 1px solid var(--theme-color);
  color: var(--theme-color);
  background-color: var(--color-main);
  border-radius: 20px;
}
</style>
