<template>
  <div class="comment-input-wrap">
    <!-- 유저 프로필 -->
    <img
      :src="userStore.currentUser.item?.user?.profile"
      alt=""
      class="profile-image"
    />

    <!-- 인풋 -->
    <input
      type="text"
      class="comment-input"
      placeholder="댓글 달기..."
      v-model="communityStore.thisContent"
    />

    <!-- 버튼 -->
    <div class="comment-button" @click="createComment()">게시</div>
  </div>
</template>

<script setup>
import { useRoute } from 'vue-router';
import { useCommunityStore } from '@/stores/community.js';
import { useUserStore } from '@/stores/user';

const userStore = useUserStore();

const route = useRoute();
const communityStore = useCommunityStore();

const createComment = () => {
  const data = {
    postId: route.params.postId,
    content: communityStore.thisContent,
  };
  communityStore.createComment(data);
  communityStore.thisContent = '';
};
</script>

<style scoped>
.comment-input-wrap {
  position: fixed;
  bottom: var(--size-h-footer);
  width: 100%;
  left: 0;
  height: 50px;

  display: flex;
  gap: 10px;
  align-items: center;
  background-color: var(--light-sub-color);
}
.profile-image {
  width: 30px;
  height: 30px;
  object-fit: cover;
  border-radius: 50px;
  margin-left: 20px;
}
.comment-input {
  width: calc(100% - 120px);
  height: 35px;
  border: none;
  padding: 0 7px;
  border-radius: 10px;
  font-size: 12px;

  line-height: 35px;
}
.comment-button {
  width: 30px;
  color: var(--instagram-dark-grey);
  font-size: 12px;
}
</style>
