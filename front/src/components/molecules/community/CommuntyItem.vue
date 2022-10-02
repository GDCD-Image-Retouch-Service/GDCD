<template>
  <div class="comment-item">
    <div class="comment-header">
      <!-- 유저 정보 -->
      <div class="user-info">
        <img :src="comment.writerProfile" class="profile-image" />
        <div class="profile-nickname">
          {{ comment.writerNickname }}
        </div>
      </div>

      <!-- 수정 삭제 -->
      <div class="edit-delete-wrap">
        <div v-if="!isClickUpdate" @click="isClickUpdate = !isClickUpdate">
          수정
        </div>
        <div v-else @click="isClickUpdate = !isClickUpdate">취소</div>
        <div>삭제</div>
      </div>
    </div>

    <!-- 메시지 -->
    <div class="comment-message">
      {{ comment.content }}
    </div>
    <input
      v-if="isClickUpdate"
      type="text"
      class="update-input"
      v-model="communityStore.updateCommentContent"
      @keyup.enter="
        enterUpdateComment(
          comment.commentId,
          communityStore.updateCommentContent,
        )
      "
    />
  </div>
</template>

<script setup>
import { defineProps, ref, toRefs } from 'vue';
import { useCommunityStore } from '@/stores/community.js';
import { useRoute } from 'vue-router';

const route = useRoute();
const communityStore = useCommunityStore();

const props = defineProps({
  comment: Object,
});

const { comment } = toRefs(props);
const isClickUpdate = ref(false);

const enterUpdateComment = (commentId, content) => {
  const data = {
    postId: route.params.postId,
    commentId: commentId,
    content: content,
  };
  isClickUpdate.value = false;
  console.log(commentId, content);
  communityStore.updateComment(data);
};

communityStore.updateCommentContent = '';
</script>

<style scoped>
.comment-item {
  align-items: center;
  /* justify-content: space-between; */
  gap: 10px;
  width: 100%;
  line-height: 30px;
  position: relative;
}
.comment-header {
  width: 100%;
  display: flex;
  justify-content: space-between;
}
.edit-delete-wrap {
  display: flex;
  gap: 10px;
  font-size: 12px;
  line-height: 30px;
}
.user-info {
  display: flex;
  align-items: center;
  gap: 10px;
  font-weight: 700;
}
.profile-image {
  width: 30px;
  height: 30px;
  object-fit: cover;
  border-radius: 50px;
}
.comment-message {
  word-break: break-all;
  text-align: start;
}
.update-input {
  width: 100%;
  position: absolute;
  left: 0;
  top: 40px;
  border-radius: 1px;
  border: none;
  border-bottom: 1px solid var(--instagram-dark-grey);
  height: 30px;
}
</style>
