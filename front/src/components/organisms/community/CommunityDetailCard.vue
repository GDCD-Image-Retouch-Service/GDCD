<template>
  <div class="community-detail-card common-image">
    <!-- 제목 -->
    <div class="card-title">
      {{ communityStore.post.item?.title }}
    </div>

    <div class="profile-update-delete">
      <!-- 프로필 -->
      <div class="user-profile">
        <img
          :src="communityStore.post.item?.writerProfile"
          alt=""
          class="profile-image"
        />
        {{ communityStore.post.item?.writerNickname }}
      </div>

      <div
        v-if="
          userStore.currentUser.itme?.user?.userId ===
          communityStore.post.item?.postId
        "
      >
        <span>수정</span>
        <span @click="communityStore.deletePost(postId)">삭제</span>
      </div>
    </div>

    <!-- 이미지 -->
    <label for="btnMyPhoto">
      <card-carousel
        :firstImage="communityStore.post.item?.images[0].imageUrl"
        :secondImage="
          communityStore.post.item?.images[1].imageUrl
            ? communityStore.post.item?.images[1].imageUrl
            : ''
        "
        :firstTags="communityStore.post.item?.images[0].imageTag"
        :secondTags="communityStore.post.item?.images[1]?.imageTag"
      />
    </label>

    <span class="card-content">
      {{ communityStore.post.item?.content }}
    </span>

    <!-- 채팅 좋아요 북마크 -->
    <div class="like-bookmark-chat">
      <div class="like-bookmark">
        <div class="herat-wrap">
          <i class="bi bi-heart" @click="communityStore.likePost(postId)"></i>
          <span>{{ communityStore.post.item?.likeCount }}</span>
        </div>
        <i class="bi bi-bookmark" @click="communityStore.scrapPost(postId)"></i>
      </div>
      <i class="bi bi-chat" style="color: var(--black)" @click="clickComment()">
      </i>
    </div>
  </div>
</template>

<script setup>
import CardCarousel from '@/components/molecules/CardCarousel.vue';
import { useCommunityStore } from '@/stores/community.js';
import { useUserStore } from '@/stores/user.js';
import { watch } from '@vue/runtime-core';
import { useRoute } from 'vue-router';

const route = useRoute();
const communityStore = useCommunityStore();
const userStore = useUserStore();

communityStore.getPost(route.params.postId);

const postId = route.params.postId;
const clickComment = () => {
  communityStore.isOpenComment = !communityStore.isOpenComment;
  // document.getElementById('app').scrollTop =
  //   document.getElementById('app').scrollHeight;
  // document.getElementById('app').scrollTop = 1200;
  watch(communityStore.isOpenComment, (newValues) => {
    window.scrollTo(0, document.body.scrollHeight);
    console.log(newValues, '아아아아아아아왜안돼애애애애');
  });

  console.log('눌림');
};
</script>

<style scoped>
.community-detail-card {
  display: flex;
  align-items: flex-start;
  gap: 30px;
  flex-direction: column;
  border: 1px solid var(--instagram-grey);
  border-radius: 10px;
  padding: var(--grid-vertical) var(--grid-side);
  width: 100%;
  background-color: var(--light-main-color);
  margin-bottom: 30px;
  position: relative;
}
.edit-icon {
  position: absolute;
  right: var(--grid-side);
  font-size: 24px;
  color: var(--black);
}

/* 제목 */
.card-title {
  width: 100%;
  font-size: 18px;
  word-break: break-all;
  white-space: normal;
  line-height: 25px;
  resize: none;

  height: fit-content;
}
/* 프로필 수정 삭제 */
.profile-update-delete {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
}

/* 프로필 */
.user-profile {
  display: flex;
  align-items: center;
  gap: 10px;
}
.profile-image {
  width: 35px;
  height: 35px;
  object-fit: cover;
  border-radius: 35px;
}
/*  */
.card-image {
  width: 100%;
  object-fit: cover;
}
.card-content {
  text-align: start;
  word-break: break-all;
}
.like-bookmark-chat {
  display: flex;
  width: 100%;
  justify-content: space-between;
}
.like-bookmark {
  display: flex;
}
.herat-wrap {
  display: flex;
  gap: 3px;
  margin-right: 5px;
}
</style>
