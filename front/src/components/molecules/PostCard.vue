<template>
  <div class="post-card">
    <i class="bi bi-three-dots dot-icon"></i>

    <!-- post card image -->
    <img
      :src="post.images.imageUrl"
      alt=""
      @click="
        $router.push({
          name: 'CommunityDetail',
          params: { postId: post.postId },
        })
      "
    />

    <!-- post card -title -->
    <div class="post-card-title">
      {{ post?.title }}
    </div>

    <!-- post card userprofile -->
    <div class="post-card-userinfo">
      <img :src="post.writerProfile" class="profile-image" alt="" />
      <div style="margin-left: 5px">{{ post.writerNickname }}</div>
    </div>

    <!-- post card like and bookmark -->
    <div
      style="
        display: flex;
        justify-content: space-between;
        width: calc(100% - 10px);
        margin-left: 5px;
      "
    >
      <div class="like-bookmark">
        <div class="herat-wrap">
          <i class="bi bi-heart" v-if="!myLike" @click="clickLike()"></i>
          <i
            class="bi bi-heart-fill"
            style="color: #ed4956"
            v-else
            @click="clickLike()"
          ></i>
          <div>{{ myLikeCount }}</div>
        </div>
        <i
          class="bi bi-bookmark"
          v-if="!myScrap"
          @click="(myScrap = !myScrap), communityStore.scrapPost(post.postId)"
        ></i>
        <i
          class="bi bi-bookmark-fill"
          v-else
          @click="(myScrap = !myScrap), communityStore.scrapPost(post.postId)"
        ></i>
      </div>
      <date-format :updateInfo="post.registTime" />
    </div>
  </div>
</template>

<script setup>
import DateFormat from '@/components/molecules/common/DateFormat.vue';
import { defineProps, ref } from 'vue';
import { useCommunityStore } from '@/stores/community';

const communityStore = useCommunityStore();

const props = defineProps({
  post: Object,
  likeCount: Number,
});

let myLike = ref(props.post.like);
let myScrap = ref(props.post.scrap);
let myLikeCount = ref(props.likeCount);

const clickLike = () => {
  if (!myLike.value) {
    myLikeCount.value += 1;
  } else {
    myLikeCount.value -= 1;
  }
  myLike.value = !myLike.value;
  communityStore.likePost(props.post.postId);
};
</script>

<style scoped>
.post-card {
  position: relative;
  z-index: 1;

  width: 100%;
  border-radius: 10px;

  display: flex;
  flex-direction: column;
  gap: 20px;
  color: var(--instagram-dark-grey);
  padding-bottom: 20px;
  box-shadow: 0 4px 4px -4px rgba(0, 0, 0, 0.5);
  border: 1px solid rgba(0, 0, 0, 0.12);
  background-color: var(--light-main-color);
  font-size: 14px;
  overflow: hidden;

  background: var(--color-main);
}
.post-card-title {
  text-align: start;
  width: calc(100% - 10px);
  margin-left: 5px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;

  font-size: 14px;
}
.post-card-userinfo {
  display: flex;
  width: calc(100% - 10px);
  margin-right: 10px;
  justify-content: flex-end;
  font-size: 14px;
  margin-top: 35px;
}
.profile-image {
  width: 20px;
  height: 20px;
  object-fit: cover;
  border-radius: 20px;
}
.dot-icon {
  position: absolute;
  right: 0;
  margin: 5px 5px 0 0;
}
.like-bookmark {
  display: flex;

  margin-left: 5px;
  font-size: 14px;
  gap: 5px;
}
.herat-wrap {
  display: flex;
  gap: 3px;
}
</style>
