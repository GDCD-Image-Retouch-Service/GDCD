<template>
  <div class="profile-header-info">
    <div class="nickname-follow-button">
      <div>
        {{ userStore.currentUserd.item.nickname }}
      </div>
      <router-link to="/profile/friend" style="text-decoration: none">
        <btn-add-friednd />
      </router-link>
    </div>
    <div class="posts-scraps-likes">
      <!-- 게시물 -->
      <router-link to="/profile" class="posts-scraps-likes-item">
        <div :class="{ active: userStore.isItemActive === 0 }">게시물</div>
        <div>{{ userStore.post.item.postCount }}</div>
      </router-link>

      <!-- 스크랩 -->
      <router-link to="/profile/scrap" class="posts-scraps-likes-item">
        <div :class="{ active: userStore.isItemActive === 1 }">스크랩</div>
        <div>{{ userStore.scrap.item.scrapCount }}</div>
      </router-link>

      <!-- 좋아요 -->
      <router-link to="/profile/like" class="posts-scraps-likes-item">
        <div :class="{ active: userStore.isItemActive === 2 }">좋아요</div>
        <div>{{ userStore.like.item.likeCount }}</div>
      </router-link>
    </div>
  </div>
</template>

<script setup>
import BtnAddFriednd from '../common/btn/BtnAddFriednd.vue';
import { useUserStore } from '@/stores/user.js';

const userStore = useUserStore();
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
