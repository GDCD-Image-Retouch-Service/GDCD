<template>
  <div class="community-detail-card common-image">
    <i class="bi bi-pencil-square edit-icon"></i>

    <!-- 제목 -->
    <div class="card-title">
      {{ communityStore.post.item.title }}
    </div>

    <!-- 프로필 -->
    <span class="user-profile">
      <img
        :src="communityStore.post.item.writerProfile"
        alt=""
        class="profile-image"
      />
      {{ communityStore.post.item.writerNickname }}
    </span>

    <!-- 이미지 -->
    <label for="btnMyPhoto">
      <card-carousel
        :firstImage="communityStore.post.item.images[0].imageUrl"
        :secondImage="
          communityStore.post.item.images[1].imageUrl
            ? communityStore.post.item.images[1].imageUrl
            : ''
        "
        :firstTags="communityStore.post.item.images[0].imageTag"
        :secondTags="communityStore.post.item?.images[1]?.imageTag"
      />
    </label>

    <span class="card-content">
      {{ communityStore.post.item.content }}
    </span>

    <div v-for="(values, key) in userStore.myPhoto.item" :key="values">
      {{ key }}
      <div v-for="value in values" :key="value">
        <img :src="value.beforeImage.imageUrl" alt="" class="photo-image" />
      </div>
    </div>

    <!-- 채팅 좋아요 북마크 -->
    <div class="like-bookmark-chat">
      <div class="like-bookmark">
        <div class="herat-wrap">
          <i class="bi bi-heart" @click="communityStore.likePost(postId)"></i>
          <span>{{ communityStore.post.item.likeCount }}</span>
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
userStore.getMyPhoto();
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
/* 프로필 */
.user-profile {
  display: flex;
  gap: 10px;
  align-items: center;
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

.modal {
  width: 100%;
}
.image-select-modal {
  width: 100%;
  height: 500px;
  position: fixed;
  bottom: 0px;
  left: 0;
  margin: 0;
  background-color: var(--light-main-color);
  border-radius: 30px 30px 0 0;
  padding-top: var(--grid-vertical);
}
.photo-image {
  width: 100px;
}
.modal-section {
  overflow: scroll;
}
</style>
