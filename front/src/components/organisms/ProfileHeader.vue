<template>
  <div class="profile-header">
    <div class="profile-header-section">
      <!-- 유저 정보 -->
      <div class="profile-header-userinfo">
        <img :src="userStore.currentUser.profileImage" alt="" class="profile-image">

      </div>

      <!-- 유저의 게시물, 좋아요, 북마크, 친구 -->
      <div class="profile-header-subinfo">
        <!-- 게시물 -->
        <div class="profile-header-subinfo-item" :class="{ active: userStore.isItemActive === 0 }" @click="clickProfileHeaderItem(0)">
          게시물
          <div>
            {{ userStore.currentUser.posts.length }}
          </div>
        </div>
        <!-- 좋아요 -->
        <div class="profile-header-subinfo-item" :class="{ active: userStore.isItemActive === 1 }" @click="clickProfileHeaderItem(1)">
          좋아요
          <div>
            {{ userStore.currentUser.likes.length }}
          </div>
        </div>
        <!-- 북마크 -->
        <div class="profile-header-subinfo-item" :class="{ active: userStore.isItemActive === 2 }" @click="clickProfileHeaderItem(2)">
          북마크
          <div>
            {{ userStore.currentUser.bookmarks.length }}
          </div>
        </div>
        <!-- 친구 -->
        <div class="profile-header-subinfo-item" >
          친구
          <div>
            
          </div>
        </div>
      </div>

    </div>
    <div class="profile-header-username">
      {{ userStore.currentUser.username }}
    </div>
  </div>
</template>

<script setup>
import { useUserStore } from '@/stores/user.js'

const userStore = useUserStore();

// 프로필 헤더에서 게시물, 좋아요, 북마크를 눌렀을 때 값 변경
const clickProfileHeaderItem = (num) => {
  userStore.isItemActive = num
}

</script>

<style scoped>
.active {
  font-weight: 700;
}
.profile-header {
  width: 100%;
  padding-bottom: 30px;
  border-bottom: 1px solid black;
}
.profile-header-section {
  width: 100%;
  display: flex;
}
.profile-header-userinfo {
  position: relative;
  width: 30%;
  padding-bottom: 30% ;
}
.profile-header-subinfo {
  width: 70%;
  display: flex;
  justify-content: space-around;
  align-items: center;
}
.profile-image {
  position: absolute;
  width: 100%;
  height: 100%;
  border-radius: 70%;
  object-fit: cover;
  top: 0;
  left: 0;
}
.profile-header-username {
  width: 30%;
  margin-right: 70%;
}
.profile-header-subinfo-item:hover {
  cursor: pointer;
}
</style>