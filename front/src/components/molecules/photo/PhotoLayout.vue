<template>
  <div class="photo-layout">
    <div v-for="(values, key) in userStore.myPhoto.item" :key="values">
      <date-format :updateInfo="key" class="photo-date" />
      <div class="image-wrap">
        <div
          v-for="value in values"
          :key="value"
          :style="{
            backgroundImage: 'url(' + value.beforeImage.imageUrl + ')',
          }"
          class="photo-image"
          @click="clickImage(value), router.push({ name: 'PhotoDetail' })"
        ></div>
      </div>
    </div>
  </div>
</template>

<script setup>
import DateFormat from '../common/DateFormat.vue';
import { useUserStore } from '@/stores/user.js';
import { useCommunityStore } from '@/stores/community.js';
import { useRouter } from 'vue-router';

const userStore = useUserStore();
const communityStore = useCommunityStore();
const router = useRouter();

userStore.getMyPhoto();

const clickImage = (img) => {
  communityStore.selectImage = img;
};
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
}
.photo-items {
  width: calc((100% - 20px) / 3);
}
.photo-item {
  width: 100%;
  object-fit: cover;
}
.photo-date {
  margin-top: 30px;
}
.photo-date:first-child {
  margin-top: 0px;
}
.image-wrap {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  margin-top: 10px;
  margin-bottom: var(--grid-vertical);
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
