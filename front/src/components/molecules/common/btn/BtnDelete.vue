<template>
  <div v-if="isClick" class="following-button" @click="isClick = !isClick">
    팔로잉
  </div>
  <div v-else class="follow-button" @click="isClick = !isClick">+팔로우</div>
</template>

<script setup>
import { ref } from 'vue';
const isClick = ref(true);
</script>

<style scoped>
.following-button {
  width: 90px;
  padding: 4px 14px;
  height: 30px;
  line-height: 22px;
  text-align: center;
  border: 1px solid #dfdfdf;
  color: #dfdfdf;
  background-color: #ffffff;
  border-radius: 20px;
}
.follow-button {
  width: 90px;
  padding: 4px 14px;
  height: 30px;
  line-height: 22px;
  text-align: center;
  border: 1px solid #dfdfdf;
  color: #ffa182;
  background-color: #ffffff;
  border-radius: 20px;
}
</style>
