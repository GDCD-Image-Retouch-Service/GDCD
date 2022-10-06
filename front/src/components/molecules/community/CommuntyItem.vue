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
            <date-format
              :updateInfo="comment.registTime"
              class="profile-date"
            />
          </div>
          <div v-else class="update-wrap">
            <date-format
              :updateInfo="comment.updateTime"
              class="profile-date"
            />
            <span class="profile-date profile-date"> &nbsp;수정</span>
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
          @click="clickDeleteComment(route.params.postId, comment.commentId)"
        >
          삭제
        </div>
      </div>
    </div>

    <v-dialog v-model="deleteCommentDialog">
      <div class="error-alert">
        <div class="create-post-modal">
          <div class="modal-title">정말 삭제하시겠습니까?</div>
          <div
            class="modal-close"
            @click="postDeleteCommet(route.params.postId, comment.commentId)"
          >
            확인
          </div>
        </div>
      </div>
    </v-dialog>

    <!-- 메시지 -->
    <div class="comment-message" :class="{ none: isClickUpdate }">
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
import { useRoute } from 'vue-router';
import { useUserStore } from '@/stores';
import DateFormat from '../common/DateFormat.vue';

const route = useRoute();
const communityStore = useCommunityStore();
const userStore = useUserStore();
const props = defineProps({
  comment: Object,
});

const { comment } = toRefs(props);
const isClickUpdate = ref(false);
const myUpdateComment = ref('');
const updateInfo = ref(false);
const deleteCommentDialog = ref(false);

const enterUpdateComment = (commentId, content) => {
  const data = {
    postId: route.params.postId,
    commentId: commentId,
    content: content,
  };
  if (content !== '') {
    updateInfo.value = true;
    communityStore.updateComment(data);
  }
  isClickUpdate.value = false;
};

myUpdateComment.value = '';

const clickDeleteComment = () => {
  deleteCommentDialog.value = true;
};
const postDeleteCommet = (postId, commentId) => {
  communityStore.deleteComment(postId, commentId);
};
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
  font-size: 16px;
  height: 16px;
  line-height: 16px;
  font-weight: 700;
  margin-bottom: 8px;
}
.profile-date {
  font-size: 12px;
  min-height: 14px;
  line-height: 12px;
}
.none {
  display: none;
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
  width: 100%;
  word-break: break-all;
  text-align: start;
  padding: 0 0 0 5px;
  margin-top: 10px;
  line-height: 22px;
  font-size: 16px;
  height: 30px;
}

.update-input {
  width: calc(100% - 30px);

  border-radius: 1px;
  border: none;
  border-bottom: 1px solid var(--instagram-dark-grey);
  height: 30px;
  word-break: break-all;
  text-align: start;
  outline: none;
  font-size: 16px;
  line-height: 22px;

  padding: 0 0 0 5px;
  background-color: var(--color-main);
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
.error-alert {
  width: 100%;
  position: fixed;
  left: 0;
  bottom: 0;
  background-color: #ffffff;
  border: 1px solid var(--instagram-grey);
  border-radius: 5px;
  text-align: center;
}
.create-post-modal {
  width: 100%;
  max-width: 400px;
  height: 170px;
  background-color: var(--color-main);
  border-radius: 5px;
  text-align: center;
  position: relative;
  margin: 0 auto;
}
.modal-title {
  width: 100%;

  position: absolute;
  top: calc(40% - 12px);
  color: var(--color-reverse);
  font-family: 'Pretendard-Regular';
}
.modal-close {
  background-color: var(--theme-color);
  border-radius: 5px;
  border: none;
  color: var(--light-main-color);
  font-weight: 500;
  width: 259px;
  height: 38px;
  line-height: 38px;
  position: absolute;
  top: calc(75% - 12px);
  left: calc(50% - 130px);
}
</style>
