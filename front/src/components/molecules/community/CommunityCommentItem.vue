<template>
  <div class="wrapper">
    <div
      v-if="communityStore.commentAll.item?.comments.length == 0"
      class="none-comment"
    >
      등록된 댓글이 없습니다.
    </div>
    <div
      v-for="comment in communityStore.commentAll.item?.comments"
      :key="comment?.commentId"
      class="comment-wrap"
    >
      <community-item :comment="comment" />
    </div>
  </div>
</template>

<script setup>
import CommunityItem from '@/components/molecules/community/CommuntyItem.vue';
import { useCommunityStore } from '@/stores/community.js';

const communityStore = useCommunityStore();
</script>

<style scoped>
.wrapper {
  max-height: 600px;
  overflow: scroll;
}
.comment-wrap {
  align-items: center;
  display: flex;
  /* justify-content: space-between; */
  gap: 10px;
  margin-bottom: var(--grid-vertical);
  flex-direction: column;
  width: 100%;
  align-items: start;
  line-height: 30px;
}
.spacer {
  width: 100%;
  height: var(--grid-vertical);
  background-color: pink;
}
.none-comment {
  height: 50px;
  line-height: 50px;
}
</style>
