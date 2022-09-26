<template>
  <div class="scrap">
    <div class="scrap-items">
      <div
        v-for="post in userStore.scrapList?.item?.posts"
        :key="post"
        class="image-wrap"
      >
        <img :src="post.writerProfile" alt="" class="card-image common-image" />
      </div>
    </div>
  </div>
</template>

<script setup>
import { useUserStore } from '@/stores/user.js';

const userStore = useUserStore();

userStore.isItemActive = 1;
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
