<template>
  <div class="community-detail-card common-image">
    <!-- 이미지 -->

    <div
      v-if="communityStore.post.item?.images?.length == 1"
      class="image-wrap"
    >
      <img
        :src="communityStore.post.item?.images[0].imageUrl"
        class="main-image"
      />
    </div>

    <div v-if="communityStore.post.item?.images?.length == 2">
      <div style="position: relative" class="image-wrap">
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

    <!-- 버튼 -->
    <div
      class="button-wrap"
      v-show="communityStore.post.item?.images?.length == 2"
    >
      <div class="image-toggle-button">
        <div class="button-left"></div>
        <div class="button-right"></div>
      </div>
    </div>

    <div class="tag-wrap">
      <div
        v-for="(tag, index) in communityStore.post.item?.images[0]?.imageTag"
        :key="index"
        class="tag"
      >
        {{ tag }}
      </div>
    </div>

    <!--  -->
    <div class="profile-date-wrap">
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
          <div>
            {{ communityStore.post.item?.writerNickname }}
          </div>
        </div>
        <!-- 데이트 -->
      </div>
      <div class="date-wrap" v-if="communityStore.post.item?.registTime">
        <div
          class="date"
          v-if="
            communityStore.post.item?.registTime ==
            communityStore.post.item?.updateTime
          "
        >
          <date-format :updateInfo="communityStore.post.item?.registTime" />
        </div>
        <div class="date" v-else>
          <div class="update-info">수정됨</div>
          <date-format :updateInfo="communityStore.post.item?.updateTime" />
        </div>
      </div>
    </div>
    <!-- 제목 -->
    <div class="card-title">
      {{ communityStore.post.item?.title }}
    </div>

    <span class="card-content">
      {{ communityStore.post.item?.content }}
    </span>
    <div
      v-if="
        userStore.currentUser.item?.user?.userId ==
        communityStore.post.item?.userId
      "
      class="update-delete"
    >
      <span
        class="material-icons-outlined"
        @click="deleteAlert = true"
        style="color: var(--instagram-dark-grey)"
      >
        delete
      </span>
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
    </div>
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

    <template v-if="deleteAlert">
      <div class="error-alert">
        <div class="create-post-modal">
          <div class="modal-title">정말 삭제하시겠습니까?</div>
          <div class="modal-close" @click="deletePost()">확인</div>
        </div>
      </div>
    </template>

    <template v-if="guideAlert">
      <div class="error-alert">
        <div class="create-post-modal">
          <div class="modal-title">삭제되었습니다.</div>
        </div>
      </div>
    </template>
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
const deleteAlert = ref(false);
const guideAlert = ref(false);

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

const deletePost = () => {
  communityStore.deletePost(postId);
  deleteAlert.value = true;
  guideAlert.value = true;
  setTimeout(function () {
    router.push({ name: 'CommunityList' });
  }, 2000);
};
</script>

<style scoped>
.oneImage {
  display: none;
}
.community-detail-card {
  display: flex;
  gap: 30px;
  flex-direction: column;
  border: 1px solid var(--instagram-grey);
  border-radius: 10px;
  width: 100%;
  margin-bottom: 30px;
  position: relative;
  margin-top: var(--grid-vertical);
  background-color: var(--color-main);
  overflow: hidden;
  align-items: normal;
}
.edit-icon {
  position: absolute;
  right: var(--grid-side);
  font-size: 24px;
  color: var(--black);
}

.error-alert {
  width: calc(100% - 2 * var(--grid-side));
  position: fixed;
  top: calc(50vh - 135px);
  background-color: var(--color-main);
  border: 1px solid var(--instagram-grey);
  height: 170px;
  text-align: center;
}

/* 제목 */
.card-title {
  width: 100%;
  font-size: 24px;
  word-break: break-all;
  white-space: normal;
  line-height: 25px;
  resize: none;
  font-weight: 600;
  height: fit-content;
  padding: 0 var(--grid-side);
  color: var(--instagram-dark-grey);
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
  color: var(--instagram-dark-grey);
  font-weight: 600;
}
.profile-image {
  width: 35px;
  height: 35px;
  object-fit: cover;
  border-radius: 35px;
}
.update-delete {
  display: flex;
  flex-direction: row-reverse;
  margin-right: var(--grid-side);
}

.tag-wrap {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  padding: 0 10px;
}
.tag {
  background-color: var(--theme-color);
  padding: 5px 15px;
  border-radius: 20px;
  line-height: 20px;
}

/* 데이트 */
.date-wrap {
  width: 100%;
  display: flex;
  flex-direction: column;
  text-align: end;
  height: 35px;
  line-height: 35px;
}
.date {
  display: flex;
  flex-direction: row-reverse;
  font-size: 14px;
  padding-right: var(--grid-side);
  color: var(--instagram-dark-grey);
}
.update-info {
  font-size: 10px;

  color: var(--instagram-dark-grey);
  margin-left: 5px;
}
.image-wrap {
  padding: 0 auto;
}
.profile-date-wrap {
  display: flex;
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
  background-color: var(--instagram-grey);
}
.button-right {
  width: 50%;
  border: 1px solid var(--instagram-grey);
}
.image-toggle-button:active .button-left {
  border: 1px solid var(--instagram-grey);
  background-color: #ffffff;
  background-color: none;
}
.image-toggle-button:active .button-right {
  border: 1px solid var(--instagram-grey);
  background-color: var(--instagram-grey);
}
.modal-title {
  width: 100%;
  font-weight: 700;
  position: absolute;
  top: calc(40% - 12px);
  color: var(--color-reverse);
  font-family: 'Nanum Gothic';
}
.modal-close {
  background-color: var(--theme-color);
  border-radius: 5px;
  border: none;
  color: var(--light-main-color);
  font-weight: 500;
  width: 50px;
  height: 38px;
  line-height: 38px;
  position: absolute;
  top: calc(75% - 12px);
  left: calc(50% - 25px);
}
</style>
