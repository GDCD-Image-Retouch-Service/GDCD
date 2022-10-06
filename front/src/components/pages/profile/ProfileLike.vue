<template>
  <div class="scrap">
    <div
      v-for="post in userStore.likeList.item?.posts"
      :key="post.postId"
      class="scrap-wrap"
    >
      <div
        :style="{ backgroundImage: 'url(' + post.images.imageUrl + ')' }"
        class="scrap-image common-image"
        @click="
          router.push({
            name: 'CommunityDetail',
            params: { postId: post.postId },
          })
        "
      ></div>
    </div>
  </div>
</template>

<script setup>
import { useUserStore } from '@/stores/user.js';
import { useRoute, useRouter } from 'vue-router';

const router = useRouter();
const route = useRoute();
const userStore = useUserStore();

userStore.isItemActive = 2;

userStore.getMyLike(route.params.userId);
</script>

<style scoped>
@media (min-width: 1024px) {
  .scrap {
    min-width: 935px;
    max-width: 935px;
  }
  .scrap-wrap {
    max-width: calc(25% - 7.5px);
  }
}
.scrap {
  width: calc(100% - 2 * var(--grid-side));
  margin: 0 auto;
  margin-top: var(--grid-vertical);

  display: flex;
  flex-wrap: wrap;
  gap: 10px;
}
.scrap-wrap {
  width: calc(50% - 5px);
}
.scrap-image {
  width: 100%;
  padding-bottom: 100%;
  background-position: center;
  background-size: cover;
}
</style>
