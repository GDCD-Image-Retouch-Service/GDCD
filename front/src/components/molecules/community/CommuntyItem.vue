<template>
  <div class="comment-item">
    <div class="comment-header">
      <!-- 유저 정보 -->
      <div class="user-info">
        <!-- @click=" router.push({ name: 'ProfilePost', params: { userId:
        comment.writerNo }, }) " -->
        <img :src="comment.writerProfile" class="profile-image" />
        <div>
          <div class="profile-nickname">
            {{ comment.writerNickname }}
          </div>
          <div
            v-if="comment.registTime == comment.updateTime"
            class="update-wrap"
          >
            <date-format :updateInfo="comment.registTime" />
          </div>
          <div v-else class="update-wrap">
            <date-format :updateInfo="comment.updateTime" />
            <span class="update-info">수정</span>
          </div>
        </div>
      </div>

      <!-- 수정 삭제 -->
      <div
        class="edit-delete-wrap"
        v-if="userStore.currentUser.item?.user?.userId === comment.writerNo"
      >
        <div
          v-if="!isClickUpdate"
          @click="
            (isClickUpdate = !isClickUpdate),
              (myUpdateComment = comment.content)
          "
        >
          수정
        </div>
        <div
          v-else
          @click="
            (isClickUpdate = !isClickUpdate),
              (myUpdateComment = comment.content)
          "
        >
          취소
        </div>
        <div
          @click="
            communityStore.deleteComment(route.params.postId, comment.commentId)
          "
        >
          삭제
        </div>
      </div>
    </div>

    <!-- 메시지 -->
    <div class="comment-message">
      {{ comment.content }}
    </div>
    <div class="update-input-wrap" v-if="isClickUpdate">
      <input
        type="text"
        class="update-input"
        v-model="myUpdateComment"
        @keyup.enter="enterUpdateComment(comment.commentId, myUpdateComment)"
      />
      <span
        class="material-icons-outlined update-button"
        @click="enterUpdateComment(comment.commentId, myUpdateComment)"
      >
        north
      </span>
    </div>
  </div>
</template>

<script setup>
import { defineProps, ref, toRefs } from 'vue';
import { useCommunityStore } from '@/stores/community.js';
import { useRoute, useRouter } from 'vue-router';
import { useUserStore } from '@/stores';
import DateFormat from '../common/DateFormat.vue';

const route = useRoute();
const router = useRouter();
console.log(router);
const communityStore = useCommunityStore();
const userStore = useUserStore();
const props = defineProps({
  comment: Object,
});

const { comment } = toRefs(props);
const isClickUpdate = ref(false);
const myUpdateComment = ref('');

const enterUpdateComment = (commentId, content) => {
  const data = {
    postId: route.params.postId,
    commentId: commentId,
    content: content,
  };
  if (content !== '') {
    communityStore.updateComment(data);
  }
  isClickUpdate.value = false;
};

myUpdateComment.value = '';
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
.profile-nickname {
  font-size: 14px;
  height: 28px;
  line-height: 28px;
  font-weight: 700;
}
.profile-date {
  font-size: 12px;
  height: 12px;
  line-height: 12px;
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
  width: 40px;
  height: 40px;
  object-fit: cover;
  border-radius: 50px;
}
.comment-message {
  word-break: break-all;
  text-align: start;
  padding: 0 0 0 5px;
  margin-top: 10px;
  line-height: 22px;
}

.update-input {
  width: calc(100% - 30px);
  position: absolute;
  left: 0;
  top: 67px;
  border-radius: 1px;
  border: none;
  border-bottom: 1px solid var(--instagram-dark-grey);
  height: 30px;
  background-color: var(--color-main);
  outline: none;
}
.update-button {
  position: absolute;
  right: 0;
  top: 40px;
  color: var(--instagram-dark-grey);
}
.update-wrap {
  display: flex;
  font-size: 12px;
  color: var(--instagram-dark-grey);
}
.update-info {
  color: var(--instagram-dark-grey);
  font-size: 10px;
}
</style>
