<template>
  <div>
    <div
      v-for="comment in comments"
      :key="comment.commentId"
      class="comment-wrap"
    >
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
          <div>수정</div>
          <div>삭제</div>
        </div>
      </div>

      <!-- 메시지 -->
      <div class="comment-message">
        {{ comment.content }}
      </div>
    </div>
  </div>
</template>

<script setup>
import { defineProps, toRefs } from 'vue';

const props = defineProps({
  comments: Object,
});

const { comments } = toRefs(props);
</script>

<style scoped>
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
</style>
