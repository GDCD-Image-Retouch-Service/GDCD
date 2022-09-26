<template>
  <div class="scrap">
    <div class="scrap-items">
      <div v-for="post in evenScrap" :key="post">
        <img :src="post.writerProfile" alt="" class="card-image common-image" />
      </div>
    </div>
    <div class="scrap-items">
      <div v-for="post in oddScrap" :key="post">
        <img :src="post.image" alt="" class="card-image common-image" />
      </div>
    </div>
  </div>
</template>

<script setup>
import { useUserStore } from '@/stores/user.js';
import { ref } from 'vue';

const userStore = useUserStore();

let oddScrap = ref([]);
let evenScrap = ref([]);

const devideScrap = () => {
  userStore.scrapList?.item?.posts?.forEach((e, index) => {
    if (index % 2 === 0) {
      evenScrap.value.push(e);
    } else {
      oddScrap.value.push(e);
    }
  });
};
// userStore.getMyScrap();
devideScrap();
userStore.isItemActive = 1;
</script>

<style scoped>
.scrap {
  width: 100%;
  margin-top: var(--grid-vertical);

  display: flex;
  gap: 10px;
}
.scrap-items {
  display: flex;
  flex-direction: column;
  gap: 10px;
  width: 50%;
}
.card-image {
  width: 100%;
}
</style>
