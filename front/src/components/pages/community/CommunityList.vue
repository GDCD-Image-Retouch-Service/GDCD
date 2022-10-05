<template>
  <div class="community-list">
    <div class="community-wrap">
      <div
        v-for="(post, index) in communityStore.postsAll.item"
        :key="index"
        class="post-wrap"
      >
        <post-card :post="post" :likeCount="post.likeCount" />
      </div>
    </div>

    <btn-go-to-write />
  </div>
</template>

<script setup>
import BtnGoToWrite from '@/components/molecules/common/btn/BtnGoToWrite';
import PostCard from '@/components/molecules/PostCard.vue';

import { useCommunityStore } from '@/stores/community.js';

const communityStore = useCommunityStore();
communityStore.getPostsAll();
</script>

<style scoped>
@media (min-width: 1024px) {
  .community-list {
    min-width: 935px;
    max-width: 935px;
    margin: 0 auto;
  }
  .community-wrap {
    gap: 28px !important;
  }
  .post-wrap {
    max-width: calc(25% - 21px);
  }
}

.community-list {
  width: calc(100% - 2 * var(--grid-side));
  margin: 0 auto;
  overflow: scroll;
}
.community-wrap {
  width: 100%;
  margin-top: var(--grid-vertical);
  margin-bottom: var(--grid-vertical);
  display: flex;
  gap: 10px;
  flex-wrap: wrap;
}
.post-wrap {
  width: calc(50% - 5px);
}
</style>
