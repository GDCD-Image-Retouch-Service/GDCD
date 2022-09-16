<template>
  <div class="profile-friends-header">
    <div class="profile-friends-header-item">
      <div
      :class="{ active : userStore.isFriendActive }"
      @click="userStore.isFriendActive = true"
      >
        팔로워
      </div>
      <div
      :class="{ active : !userStore.isFriendActive }"
      @click="userStore.isFriendActive = false"
      >
        팔로잉
      </div>
    </div>
  </div>
</template>

<script setup>
import { useUserStore } from '@/stores/user.js'

const userStore = useUserStore();
</script>

<style scoped>
.active {
  font-weight: 700;
}
.profile-friends-header {
  width: 100%;
  height: 50px;
  display: flex;
  justify-content: center;
  align-items: center;
}
.profile-friends-header-item {
  display: flex;
  width: 50%;
  justify-content: space-around;
}
</style>