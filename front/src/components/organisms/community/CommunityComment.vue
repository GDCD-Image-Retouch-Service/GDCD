<template>
  <div>
    <community-comment-input v-if="communityStore.isOpenComment" />
    <div
      class="community-review common-image"
      v-if="
        communityStore.isOpenComment &
        (communityStore.commentAll.item?.comments.length !== 0)
      "
    >
      <div class="review-wrap">
        <div class="review-item">
          <community-comment-item />
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import CommunityCommentItem from '@/components/molecules/community/CommunityCommentItem';
import CommunityCommentInput from '@/components/molecules/community/CommunityCommentInput.vue';

import { useCommunityStore } from '@/stores/community.js';
import { useRoute } from 'vue-router';

const communityStore = useCommunityStore();
const route = useRoute();

const postId = route.params.postId;

// 데이터 초기화
communityStore.isOpenComment = false;
communityStore.getComment(postId);
console.log(postId);
</script>

<style scoped>
.community-review {
  position: relative;
  border: 1px solid var(--instagram-grey);
  border-top: none;
  margin-bottom: calc(50px);
  border-radius: 0 0 10px 10px;
  max-height: calc(
    100vh - var(--size-h-header) - var(--size-h-footer) - 50px -
      var(--grid-vertical) - var(--grid-vertical)
  );
  overflow: hidden;
  margin-bottom: var(--grid-vertical);
  background-color: var(--light-main-color);
}
.review-wrap {
  width: calc(100% - 2 * var(--grid-side));
  margin-top: var(--grid-vertical);
  margin-bottom: var(--grid-vertical);
  margin-left: var(--grid-side);
  max-height: calc(
    100vh - var(--size-h-header) - var(--size-h-footer) - 50px -
      var(--grid-vertical) - var(--grid-vertical) - var(--grid-vertical) -
      var(--grid-vertical)
  );
  overflow: hidden;
}
.review-item {
  width: 100%;
  word-break: break-all;
  text-align: start;
  max-height: 100%;
}
</style>
