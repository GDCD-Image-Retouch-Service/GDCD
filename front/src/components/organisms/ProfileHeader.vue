<template>
  <div class="profile-header">
    <!-- 유저 정보 -->
    <div class="profile-header-userinfo">
      <img
        :src="userStore.currentUser.profileImage"
        alt=""
        class="profile-image"
      />
      <div class="profile-header-username">
        {{ userStore.currentUser.username }}
      </div>
    </div>

    <!-- 유저의 게시물, 좋아요, 북마크, 친구 -->
    <div class="profile-header-subinfo">
      <!-- 게시물 -->

      <router-link
        to="/profile"
        class="profile-header-subinfo-item"
        :class="{ active: userStore.isItemActive === 0 }"
        @click="clickProfileHeaderItem(0)"
      >
        게시물
        <div class="profile-header-subinfo-item-count">
          {{ userStore.currentUser.posts.length }}
        </div>
      </router-link>
      <!-- 좋아요 -->
      <div
        class="profile-header-subinfo-item"
        :class="{ active: userStore.isItemActive === 1 }"
        @click="clickProfileHeaderItem(1)"
      >
        좋아요
        <div class="profile-header-subinfo-item-count">
          {{ userStore.currentUser.likes.length }}
        </div>
      </div>
      <!-- 북마크 -->
      <div
        class="profile-header-subinfo-item"
        :class="{ active: userStore.isItemActive === 2 }"
        @click="clickProfileHeaderItem(2)"
      >
        북마크
        <div class="profile-header-subinfo-item-count">
          {{ userStore.currentUser.bookmarks.length }}
        </div>
      </div>

      <router-link
        to="/profile/friends"
        class="profile-header-subinfo-item"
        :class="{ active: userStore.isItemActive === 3 }"
        @click="clickProfileHeaderItem(3)"
      >
        친구
      </router-link>
    </div>
  </div>
</template>

<script setup>
import { useUserStore } from '@/stores/user.js';

const userStore = useUserStore();

// 변수 초기화
userStore.isItemActive = 0;

// 프로필 헤더에서 게시물, 좋아요, 북마크를 눌렀을 때 값 변경
const clickProfileHeaderItem = (num) => {
  userStore.isItemActive = num;
};
</script>

<style scoped>
.active {
  font-weight: 700;
}
.profile-header {
  width: calc(100% - 40px);
  padding-bottom: 30px;
  border-bottom: 1px solid var(--instagram-grey);
  text-align: center;

  display: flex;
  justify-content: space-between;
  margin-top: 30px;
  gap: 20px;
}
.profile-header-userinfo {
  width: 90px;
  display: flex;
  flex-direction: column;
  gap: 20px;
}
.profile-header-subinfo {
  width: calc(100% - 100px);
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.profile-image {
  width: 80px;
  height: 80px;
  border-radius: 70%;
  object-fit: cover;
}
.profile-header-username {
  width: 90px;
  text-align: center;
  font-size: 16px;
  color: var(--instagram-dark-grey);
  font-weight: 700;
}
.profile-header-subinfo-item {
  color: var(--instagram-dark-grey);
  text-decoration: none;
  font-size: 14px;
}
.profile-header-subinfo-item:hover {
  cursor: pointer;
}
.profile-header-subinfo-item-count {
  color: var(--black);
}
</style>
