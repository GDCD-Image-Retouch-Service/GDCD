<template>
  <div class="profile-scrap">
    <div class="scraps-wrap">
      <div
        v-for="post in userStore.oddScrapList"
        :key="post.postId"
        style="width: 100%"
      >
        {{ post }}
      </div>
    </div>

    <div class="posts-wrap">
      <div
        v-for="post in userStore.evenScrapList"
        :key="post.postId"
        style="width: 100%"
      ></div>
    </div>
  </div>
</template>

<script setup>
import { useUserStore } from '@/stores/user.js';

const userStore = useUserStore();

userStore.isItemActive = 1;

userStore.getMyScrap();
</script>

<style scoped>
.scrap {
  width: calc(100% - 2 * var(--grid-side));
  margin-left: var(--grid-side);
  margin-top: var(--grid-vertical);

  display: flex;
  gap: 10px;
}
.scrap-items {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  width: 100%;
}
.image-wrap {
  width: calc(50% - 5px);
}
.card-image {
  width: 100%;
  object-fit: cover;
}
</style>
