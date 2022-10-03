<template>
  <div class="community-list">
    <div
      v-for="(post, index) in communityStore.postsAll.item"
      :key="index"
      class="post-wrap"
    >
      <div class="masonry-item">
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

<style>
.community-list {
  width: calc(100% - 2 * var(--grid-side));
  margin-top: var(--grid-vertical);
  margin-bottom: var(--grid-vertical);
  margin-left: var(--grid-side);
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
}
.post-wrap {
  width: calc(50% - 5px);
}
</style>
