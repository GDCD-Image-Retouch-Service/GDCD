<template>
  <div class="profile-header-info">
    <div class="nickname-follow-button">
      <div>
        {{ userStore.profile.item?.user?.nickname }}
        <router-link to="/profile/update">
          <i class="bi bi-caret-right-fill"></i>
        </router-link>
      </div>
      <div
        @click="router.push({ name: 'ProfileFriend', params: { userid: 18 } })"
        style="text-decoration: none"
      >
        <btn-add-friednd />
      </div>
    </div>
    <div class="posts-scraps-likes">
      <!-- 게시물 -->
      <div
        @click="router.push({ name: 'ProfilePost' })"
        class="posts-scraps-likes-item"
      >
        <div :class="{ active: userStore.isItemActive === 0 }">게시물</div>
        <div>{{ userStore.profile.item?.user?.postCount }}</div>
      </div>

      <!-- 스크랩 -->
      <div
        @click="router.push({ name: 'ProfileScrap' })"
        class="posts-scraps-likes-item"
      >
        <div :class="{ active: userStore.isItemActive === 1 }">스크랩</div>
        <div>{{ userStore.profile.item?.user.scrapCount }}</div>
      </div>

      <!-- 좋아요 -->
      <div
        @click="router.push({ name: 'ProfileLike' })"
        class="posts-scraps-likes-item"
      >
        <div :class="{ active: userStore.isItemActive === 2 }">좋아요</div>
        <div>{{ userStore.like?.item?.likeCount }}</div>
      </div>
    </div>
  </div>
</template>

<script setup>
import BtnAddFriednd from '../common/btn/BtnAddFriednd.vue';
import router from '@/router/index.js';

import { useUserStore } from '@/stores/user.js';
import { useRoute } from 'vue-router';

const route = useRoute();

const userStore = useUserStore();

if (route.params.userId == 100000) {
  userStore.getMyinfo();
} else {
  userStore.getOtherinfo(route.params.userId);
}
</script>

<style scoped>
.active {
  font-weight: 700;
  color: var(--black);
}
.profile-header-info {
  width: calc(100% - 100px);
  max-width: 300px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  padding: 3px 0;
}
.nickname-follow-button {
  display: flex;
  justify-content: space-between;
  font-weight: 700;
}
.posts-scraps-likes {
  display: flex;
  justify-content: space-between;
  text-align: center;
  padding: 0 2px;
}
.posts-scraps-likes-item {
  display: flex;
  flex-direction: column;
  gap: 5px;
  font-size: 12px;
  color: var(--instagram-dark-grey);
  font-weight: 700;
  text-decoration: none;
}
</style>
