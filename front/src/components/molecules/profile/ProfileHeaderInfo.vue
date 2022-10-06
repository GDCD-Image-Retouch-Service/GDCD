<template>
  <div class="profile-header-info">
    <div class="nickname-follow-button">
      <div>
        {{ userStore.profile.item?.user?.nickname }}
        <router-link
          to="/profile/update"
          v-if="
            userStore.currentUser.item?.user?.userId ==
            userStore.profile.item?.user?.userId
          "
        >
          <i class="bi bi-caret-right-fill" style="color: var(--black)"></i>
        </router-link>
      </div>
      <div>
        <btn-add-friednd
          v-if="
            userStore.currentUser.item?.user?.userId ==
            userStore.profile.item?.user?.userId
          "
          @click="router.push({ name: 'ProfileFriend' })"
          style="text-decoration: none"
        />
        <btn-add-follow
          :userId="userStore.profile.item?.user?.userId"
          v-if="
            (userStore.currentUser.item?.user?.userId !=
              userStore.profile.item?.user?.userId) &
            userStore.isCheckFollow
          "
          @click="
            userFollow(), (userStore.isCheckFollow = !userStore.isCheckFollow)
          "
        />
        <btn-un-follow
          :userId="userStore.profile.item?.user?.userId"
          v-if="
            (userStore.currentUser.item?.user?.userId !=
              userStore.profile.item?.user?.userId) &
            !userStore.isCheckFollow
          "
          @click="
            userFollow(), (userStore.isCheckFollow = !userStore.isCheckFollow)
          "
        />
      </div>
    </div>
    <div class="posts-scraps-likes">
      <!-- 게시물 -->
      <div
        @click="router.push({ name: 'ProfilePost', params: { name: 'post' } })"
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
        <div>{{ userStore.profile.item?.user?.scrapCount }}</div>
      </div>

      <!-- 좋아요 -->
      <div
        @click="router.push({ name: 'ProfileLike' })"
        class="posts-scraps-likes-item"
      >
        <div :class="{ active: userStore.isItemActive === 2 }">좋아요</div>
        <div>{{ userStore.profile?.item?.user?.likeCount }}</div>
      </div>
    </div>
  </div>
</template>

<script setup>
import BtnAddFriednd from '../common/btn/BtnAddFriednd.vue';
import BtnAddFollow from '../common/btn/BtnAddFollow.vue';
import BtnUnFollow from '../common/btn/BtnUnFollow.vue';
import { useRouter, useRoute } from 'vue-router';

import { useUserStore } from '@/stores/user';
const userStore = useUserStore();
const router = useRouter();
const route = useRoute();
const userFollow = () => {
  if (
    userStore.currentUser.item?.user?.userId !=
    userStore.profile.item?.user?.userId
  ) {
    userStore.follow(userStore.currentUser.item?.user?.userId);
  }
};
userStore.checkMyFollow(route.params.userId);
</script>

<style scoped>
.active {
  font-weight: 700;
  color: var(--color-reverse);
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
