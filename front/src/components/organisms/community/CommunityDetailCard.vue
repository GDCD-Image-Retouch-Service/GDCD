<template>
  <div class="community-detail-card common-image">
    <!-- 제목 -->
    <div class="card-title">
      {{ communityStore.post.item?.title }}
    </div>
    <div class="date-wrap" v-if="communityStore.post.item?.registTime">
      <div
        class="date"
        v-if="
          communityStore.post.item?.registTime !=
          communityStore.post.item?.updateTime
        "
      >
        &nbsp;
        <date-format :updateInfo="communityStore.post.item?.registTime" />
      </div>
      <div class="date" v-else>
        &nbsp;
        <date-format :updateInfo="communityStore.post.item?.updateTime" />
      </div>
    </div>
    <!-- 이미지 -->
    <div v-if="communityStore.post.item?.images?.length == 1">
      <img
        :src="communityStore.post.item?.images[0].imageUrl"
        class="main-image"
      />
    </div>

    <div v-if="communityStore.post.item?.images?.length == 2">
      <div style="position: relative">
        <img
          :src="communityStore.post.item?.images[0].imageUrl"
          class="main-image"
        />
        <img
          :src="communityStore.post.item?.images[1].imageUrl"
          class="main-image-second"
          v-if="isClick"
        />
      </div>
    </div>

    <div
      class="button-wrap"
      v-show="communityStore.post.item?.images?.length == 2"
    >
      <div class="image-toggle-button">
        <div class="button-left"></div>
        <div class="button-right"></div>
      </div>
    </div>
    <div class="profile-update-delete">
      <!-- 프로필 -->
      <div
        class="user-profile"
        @click="
          router.push({
            name: 'ProfilePost',
            params: { userId: communityStore.post.item?.userId },
          })
        "
      >
        <img
          :src="communityStore.post.item?.writerProfile"
          alt=""
          class="profile-image"
        />
        {{ communityStore.post.item?.writerNickname }}
      </div>

      <div
        v-if="
          userStore.currentUser.item?.user?.userId ==
          communityStore.post.item?.userId
        "
        class="update-delete"
      >
        <span
          class="material-icons-outlined"
          style="margin-right: 10px; color: var(--instagram-dark-grey)"
          @click="
            router.push({
              name: 'CommunityUpdateList',
              params: { postId: route.params.postId },
            })
          "
        >
          edit
        </span>
        <span
          class="material-icons-outlined"
          @click="communityStore.deletePost(postId)"
          style="color: var(--instagram-dark-grey)"
        >
          delete
        </span>
      </div>
    </div>

    <span class="card-content">
      {{ communityStore.post.item?.content }}
    </span>

    <!-- 채팅 좋아요 북마크 -->
    <div class="like-bookmark-chat">
      <div class="like-bookmark">
        <!-- 좋아요 -->
        <div class="herat-wrap">
          <span
            class="material-icons"
            v-if="!communityStore.post.item?.like"
            @click="clickLike()"
          >
            favorite_border
          </span>
          <span
            v-else
            class="material-icons"
            @click="clickLike()"
            style="color: #ed4956"
          >
            favorite
          </span>
          <span style="line-height: 24px">{{
            communityStore.post.item?.likeCount
          }}</span>
        </div>

        <!-- 스크랩 -->
        <span
          class="material-icons"
          v-if="!communityStore.post.item?.scrap"
          @click="
            communityStore.scrapPost(postId),
              (communityStore.post.item.scrap =
                !communityStore.post.item?.scrap)
          "
        >
          bookmark_border
        </span>

        <!--  -->
        <span
          class="material-icons"
          v-else
          @click="
            communityStore.scrapPost(postId),
              (communityStore.post.item.scrap =
                !communityStore.post.item?.scrap)
          "
        >
          bookmark
        </span>
      </div>

      <!-- 채팅 -->
      <div class="herat-wrap">
        <span
          class="material-icons"
          style="color: var(--instagram-dark-grey)"
          @click="clickComment()"
        >
          chat_bubble_outline
        </span>
        <span style="line-height: 24px">{{
          communityStore.commentAll.item?.comments?.length
        }}</span>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useCommunityStore } from '@/stores/community.js';
import { useUserStore } from '@/stores/user.js';
import { watch } from '@vue/runtime-core';
import { useRoute, useRouter } from 'vue-router';
import { ref, onBeforeMount, onMounted } from 'vue';

import DateFormat from '@/components/molecules/common/DateFormat.vue';

const route = useRoute();
const router = useRouter();
const postId = route.params.postId;
const userStore = useUserStore();
const communityStore = useCommunityStore();

const isClick = ref(false);

const clickLike = () => {
  if (!communityStore.post.item?.like) {
    console.log(communityStore.post.item?.likeCount);
    console.log(!communityStore.post.item?.like);
    communityStore.post.item.likeCount += 1;
  } else {
    communityStore.post.item.likeCount -= 1;
  }
  communityStore.post.item.like = !communityStore.post.item?.like;
  communityStore.likePost(route.params.postId);
};

const clickComment = () => {
  communityStore.isOpenComment = !communityStore.isOpenComment;

  watch(communityStore.isOpenComment, (newValues) => {
    window.scrollTo(0, document.body.scrollHeight);
    console.log(newValues);
  });
};

onBeforeMount(() => {
  communityStore.getPost(postId);
  communityStore.targetImage = communityStore.post.item?.images[0].imageUrl;
  communityStore.getComment(postId);
  console.log(communityStore.commentAll);
});

onMounted(() => {
  document
    .getElementsByClassName('image-toggle-button')[0]
    .addEventListener('touchstart', function () {
      isClick.value = true;
    });

  document
    .getElementsByClassName('image-toggle-button')[0]
    .addEventListener('touchend', function () {
      isClick.value = false;
    });
});
</script>

<style scoped>
.oneImage {
  display: none;
}
.community-detail-card {
  display: flex;
  align-items: flex-start;
  gap: 30px;
  flex-direction: column;
  border: 1px solid var(--instagram-grey);
  border-radius: 10px;
  width: 100%;
  margin-bottom: 30px;
  position: relative;
  margin-top: var(--grid-vertical);
  background-color: var(--color-main);
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
  font-weight: 600;
  height: fit-content;
  padding: 0 var(--grid-side);
  margin-top: var(--grid-vertical);
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
  margin-left: var(--grid-side);
}
.profile-image {
  width: 35px;
  height: 35px;
  object-fit: cover;
  border-radius: 35px;
}
.update-delete {
  margin-right: var(--grid-side);
}

/* 데이트 */
.date-wrap {
  width: 100%;
  display: flex;
  flex-direction: column;
  text-align: end;
}
.date {
  display: flex;
  flex-direction: row-reverse;
  font-size: 12px;
  padding-right: var(--grid-side);
}
/*  */
.card-image {
  width: 100%;
  object-fit: cover;
}
.card-content {
  width: calc(100% - 2 * var(--grid-side));
  text-align: start;
  word-break: break-all;
  margin: 0 var(--grid-side);
  line-height: 28px;
}
.like-bookmark-chat {
  display: flex;
  width: 100%;
  justify-content: space-between;
  padding: 0 var(--grid-side);
  margin-bottom: var(--grid-vertical);
  color: var(--instagram-dark-grey);
}
.like-bookmark {
  display: flex;
}
.herat-wrap {
  display: flex;
  gap: 3px;
  margin-right: 5px;
}
.main-image {
  width: 100%;
}
.main-image-second {
  width: 100%;
  position: absolute;
  top: 0;
  left: 0;
  border-radius: 10px;
}
.button-wrap {
  width: 100%;
  display: flex;
  flex-direction: row-reverse;
}
.image-toggle-button {
  width: 40px;
  height: 20px;
  display: flex;
  overflow: hidden;
  margin-right: var(--grid-side);
}
.button-left {
  width: 50%;
  border-right: none;
  border: 1px solid var(--instagram-grey);
  border-radius: 2px;
}
.button-right {
  width: 50%;
  border: 1px solid var(--instagram-grey);
}
.image-toggle-button:active .button-left {
  border: 1px solid var(--instagram-grey);
  background-color: #ffffff;
}
.image-toggle-button:active .button-right {
  border: 1px solid var(--instagram-grey);
  background-color: var(--instagram-grey);
}
</style>
