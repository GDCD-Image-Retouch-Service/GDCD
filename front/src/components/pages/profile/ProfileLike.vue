<template>
  <div class="scrap">
    <div
      v-for="post in userStore.likeList.item?.posts"
      :key="post.postId"
      style="width: calc(50% - 5px)"
    >
      <div
        :style="{ backgroundImage: 'url(' + post.images.imageUrl + ')' }"
        class="scrap-image common-image"
      ></div>
      <btn-image-toggle />
    </div>
  </div>
</template>

<script setup>
import { useUserStore } from '@/stores/user.js';
import { BtnImageToggle } from '@/components/molecules/common/btn/BtnImageToggle.vue';
import { useRoute } from 'vue-router';

const route = useRoute();
const userStore = useUserStore();

userStore.isItemActive = 1;

userStore.getMyLike(route.params.userId);
</script>

<style scoped>
.scrap {
  width: calc(100% - 2 * var(--grid-side));
  margin-left: var(--grid-side);
  margin-top: var(--grid-vertical);

  display: flex;
  flex-wrap: wrap;
  gap: 10px;
}
.scrap-image {
  width: 100%;
  padding-bottom: 100%;
  background-position: center;
  background-size: cover;
}
</style>
