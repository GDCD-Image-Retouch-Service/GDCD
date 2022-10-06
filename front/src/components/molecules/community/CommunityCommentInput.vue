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
      @keyup.enter="createComment()"
      minlength="1"
      maxlength="20"
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
  if (data.content != '') {
    communityStore.createComment(data);
  }
  communityStore.thisContent = '';
};
</script>

<style scoped>
.comment-input-wrap {
  width: 100%;
  height: 50px;
  border: 1px solid var(--instagram-grey);
  display: flex;
  gap: 10px;
  align-items: center;
  background-color: var(--color-main);
  border-radius: 10px 10px 0 0;
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
  height: 30px;
  border: none;
  padding: 0 7px;
  outline: none;
  border-radius: 10px;
  font-size: 16px;
  line-height: 35px;
}
.comment-button {
  width: 30px;
  color: var(--instagram-dark-grey);
  font-size: 16px;
}
</style>
