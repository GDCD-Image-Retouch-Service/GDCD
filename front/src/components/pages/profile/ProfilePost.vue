<template>
  <div class="profile-post" v-if="userStore.isItemActive == 0">
    <div class="posts-wrap">
      <div
        v-for="post in communityStore.postList"
        :key="post.postId"
        class="my-post-card"
      >
        <my-post-card :post="post" />
      </div>
    </div>
  </div>

  <div class="scrap" v-if="userStore.isItemActive == 1">
    <div
      v-for="post in userStore.scrapList.item?.posts"
      :key="post.postId"
      class="scrap-wrap"
    >
      <div
        :style="{ backgroundImage: 'url(' + post.images.imageUrl + ')' }"
        class="scrap-image common-image"
        @click="
          router.push({
            name: 'CommunityDetail',
            params: { postId: post.postId },
          })
        "
      ></div>
    </div>
  </div>

  <div class="scrap" v-if="userStore.isItemActive == 2">
    <div
      v-for="post in userStore.likeList.item?.posts"
      :key="post.postId"
      class="scrap-wrap"
    >
      <div
        :style="{ backgroundImage: 'url(' + post.images.imageUrl + ')' }"
        class="scrap-image common-image"
        @click="
          router.push({
            name: 'CommunityDetail',
            params: { postId: post.postId },
          })
        "
      ></div>
    </div>
  </div>
  <div v-if="userStore.isItemActive == 3">
    <div class="profile-friends-header">
      <div class="profile-friends-header-item">
        <div
          :class="{ active: userStore.isFriendActive }"
          @click="clickFollower()"
        >
          팔로워
        </div>
        <div
          :class="{ active: !userStore.isFriendActive }"
          @click="clickFollowing()"
        >
          팔로잉
        </div>
      </div>
    </div>
    <div class="profile-follow">
      <div
        v-for="follower in userStore.follower.item?.followers"
        :key="follower.userId"
        class="follow-wrap"
      >
        <div
          class="user-wrap"
          @click="
            router.push({
              name: 'ProfilePost',
              params: { userId: follower.userId },
            })
          "
        >
          <img :src="follower.profile" alt="" class="profile-image" />
          {{ follower.nickname }}
        </div>
        <delete-button @click="deleteFollow" />
      </div>
    </div>
  </div>

  <div v-if="userStore.isItemActive == 4">
    <div class="profile-friends-header">
      <div class="profile-friends-header-item">
        <div
          :class="{ active: userStore.isFriendActive }"
          @click="clickFollower()"
        >
          팔로워
        </div>
        <div
          :class="{ active: !userStore.isFriendActive }"
          @click="clickFollowing()"
        >
          팔로잉
        </div>
      </div>
    </div>
    <div class="profile-following">
      <div
        v-for="following in userStore.following.item?.followings"
        :key="following.userId"
        class="follow-wrap"
      >
        <div
          class="user-wrap"
          @click="
            router.push({
              name: 'ProfilePost',
              params: { userId: following.userId },
            })
          "
        >
          <img :src="following.profile" alt="" class="profile-image" />
          {{ following.nickname }}
        </div>
        <delete-button @click="userStore.follow(following.userId)" />
      </div>
    </div>
  </div>
</template>

<script setup>
import MyPostCard from '@/components/molecules/MyPostCard.vue';
import DeleteButton from '@/components/molecules/common/btn/BtnDelete.vue';

import { useCommunityStore } from '@/stores/community';
import { useUserStore } from '@/stores/user';
import { useRoute, useRouter } from 'vue-router';
import { onBeforeMount, onUnmounted } from 'vue';
const route = useRoute();
const router = useRouter();
const communityStore = useCommunityStore();
const userStore = useUserStore();

userStore.isItemActive = 0;

userStore.resetVariable();
onBeforeMount(() => {
  userStore.getOtherinfo(route.params.userId);
  communityStore.getMyPostsAll(route.params.userId);
});

onUnmounted(() => {});

const clickFollower = () => {
  userStore.isFriendActive = true;
  userStore.isItemActive = 3;
  userStore.follower = {};
  userStore.following = {};
  userStore.getMyFollower(route.params.userId);
  userStore.getMyFollowing(route.params.userId);
};
const clickFollowing = () => {
  userStore.isFriendActive = false;
  userStore.isItemActive = 4;
  userStore.follower = {};
  userStore.following = {};
  userStore.getMyFollower(route.params.userId);
  userStore.getMyFollowing(route.params.userId);
};
</script>

<style scoped>
@media (min-width: 1024px) {
  .profile-post {
    min-width: 935px;
    max-width: 935px;
  }

  .my-post-card {
    max-width: calc(25% - 7.5px);
  }
}

.profile-post {
  width: calc(100% - 40px);
  margin: 0 auto;
  margin-top: 30px;
  display: flex;
  gap: 10px;
  margin-bottom: 30px;
}
.posts-wrap {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
}
.my-post-card {
  width: calc(50% - 5px);
}

/* 스크랩 */
@media (min-width: 1024px) {
  .scrap {
    min-width: 935px;
    max-width: 935px;
  }
  .scrap-wrap {
    max-width: calc(25% - 7.5px);
  }
}
.scrap {
  width: calc(100% - 2 * var(--grid-side));
  margin: 0 auto;
  margin-top: var(--grid-vertical);

  display: flex;
  flex-wrap: wrap;
  gap: 10px;
}
.scrap-wrap {
  width: calc(50% - 5px);
}
.scrap-image {
  width: 100%;
  padding-bottom: 100%;
  background-position: center;
  background-size: cover;
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
  width: calc(100% - 2 * var(--grid-side));
  margin: 0 auto;
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
