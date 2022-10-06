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
          <i
            class="bi bi-caret-right-fill"
            style="color: var(--theme-color)"
          ></i>
        </router-link>
      </div>
      <div>
        <btn-add-friednd
          v-if="
            userStore.currentUser.item?.user?.userId ==
            userStore.profile.item?.user?.userId
          "
          @click="clickFriend"
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
      <div @click="clickPost" class="posts-scraps-likes-item">
        <div :class="{ active: userStore.isItemActive === 0 }">게시물</div>
        <div>{{ userStore.profile.item?.user?.postCount }}</div>
      </div>

      <!-- 스크랩 -->
      <div @click="clickScrap" class="posts-scraps-likes-item">
        <div :class="{ active: userStore.isItemActive === 1 }">스크랩</div>
        <div>{{ userStore.profile.item?.user?.scrapCount }}</div>
      </div>
      <!-- 좋아요 -->
      <div @click="clickLike" class="posts-scraps-likes-item">
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
import { useRoute } from 'vue-router';
import { useCommunityStore } from '@/stores';
import { useUserStore } from '@/stores/user';

const communityStore = useCommunityStore();
const userStore = useUserStore();
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

const clickPost = () => {
  userStore.isItemActive = 0;
  communityStore.postList = {};
  communityStore.getMyPostsAll(route.params.userId);
};

const clickScrap = () => {
  userStore.isItemActive = 1;
  userStore.scrapList = {};
  userStore.getMyScrap(route.params.userId);
};

const clickLike = () => {
  userStore.isItemActive = 2;
  userStore.likeList = {};
  userStore.getMyLike(route.params.userId);
};

const clickFriend = () => {
  userStore.isItemActive = 3;
  userStore.follower = {};
  userStore.following = {};
  userStore.getMyFollower(route.params.userId);
  userStore.getMyFollowing(route.params.userId);
};
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
  gap: 20px;
}
.nickname-follow-button {
  display: flex;
  justify-content: space-between;
  font-weight: 700;
  margin-right: 10px;
}
.posts-scraps-likes {
  display: flex;
  justify-content: space-between;
  text-align: center;
  padding: 0 2px;
  padding-right: 10px;
}
.posts-scraps-likes-item {
  display: flex;
  flex-direction: column;
  gap: 10px;
  font-size: 15px;
  color: var(--instagram-dark-grey);
  font-weight: 700;
  text-decoration: none;
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

@media (min-width: 1024px) {
  .profile-follow {
    min-width: 400px;
    max-width: 400px;
  }
  .scrap-wrap {
    max-width: calc(25% - 21px);
  }
}
.profile-follow {
  width: 100%;
  margin: 0 auto;
}
.follow-wrap {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.user-wrap {
  display: flex;
  gap: 20px;
  align-items: center;
  font-size: 15px;
  font-weight: 700;
}
.profile-image {
  width: 50px;
  height: 50px;
  border-radius: 50px;
  object-fit: cover;
}
</style>
