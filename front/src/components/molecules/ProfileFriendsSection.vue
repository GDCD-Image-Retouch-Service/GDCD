<template>
  <div class="profile-friends-section">
    <div v-if="userStore.isFriendActive">
      <div
        v-for="follower in userStore.currentUser.friends.follower"
        :key="follower"
        class="profile-friends-item"
      >
        <div>
          <img :src="follower.profileImage" alt="" class="profile-image" />
          {{ follower.username }}
        </div>
        <button class="follower-delete-button">삭제</button>
      </div>
    </div>

    <div v-else>
      <div
        v-for="following in userStore.currentUser.friends.following"
        :key="following"
        class="profile-friends-item"
      >
        <div>
          <img :src="following.profileImage" alt="" class="profile-image" />
          {{ following.username }}
        </div>
        <button
          v-if="following.isFollow"
          class="following-unfollow-button"
          @click="following.isFollow = !following.isFollow"
        >
          팔로잉
        </button>

        <button
          v-if="!following.isFollow"
          class="following-unfollow-button following"
          @click="following.isFollow = !following.isFollow"
        >
          팔로우
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useUserStore } from '@/stores/user.js';

const userStore = useUserStore();
</script>

<style scoped>
.profile-friends-section {
  width: calc(100% - 40px);
}
.profile-friends-item {
  display: flex;
  justify-content: space-between;
  width: 100%;
  margin-bottom: 30px;
  align-items: center;
}
.profile-image {
  width: 30px;
  height: 30px;
  object-fit: cover;
  border-radius: 50px;
  margin-right: 5px;
}
.following-unfollow-button,
.follower-delete-button {
  border: none;
  width: 70px;
  height: 30px;
  border-radius: 7px;
}
.following {
  background-color: var(--theme-color);
  color: white;
}
</style>
