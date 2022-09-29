<template>
  <div class="photo-layout">
    <div v-for="(values, key) in userStore.myPhoto.item" :key="values">
      <date-format :updateInfo="key" style="margin-top: 30px" />
      <div class="image-wrap">
        <div
          v-for="value in values"
          :key="value"
          :style="{
            backgroundImage: 'url(' + value.beforeImage.imageUrl + ')',
          }"
          class="photo-image"
        >
          <!-- <img :src="value.beforeImage.imageUrl" alt="" class="photo-image" /> -->
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import DateFormat from '../common/DateFormat.vue';
import { useUserStore } from '@/stores/user.js';
const userStore = useUserStore();

userStore.getMyPhoto();
</script>

<style scoped>
.photo-layout-wrap {
  display: flex;
  flex-direction: column;
  gap: 10px;
  margin-bottom: 30px;
}
.photo-item-wrap {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  width: 100%;

  /* justify-content: space-between; */
}
.photo-items {
  width: calc((100% - 20px) / 3);
}
.photo-item {
  width: 100%;
  object-fit: cover;
}
.image-wrap {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  margin-top: 10px;
  width: 100%;
}

.photo-image {
  width: calc((100% - 20px) / 3);
  padding-bottom: calc((100% - 20px) / 3);
  background-position: center;
  background-size: cover;
  border-radius: 5px;
}
</style>
