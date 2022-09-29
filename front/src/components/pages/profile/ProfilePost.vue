<template>
  <div class="profile-post">
    <div class="posts-wrap">
      <div
        v-for="post in communityStore.oddPostList"
        :key="post.postId"
        style="width: 100%"
      >
        <my-post-card :post="post" />
      </div>
    </div>

    <div class="posts-wrap">
      <div
        v-for="post in communityStore.evenPostList"
        :key="post.postId"
        style="width: 100%"
      >
        <my-post-card :post="post" />
      </div>
    </div>
  </div>
</template>

<script setup>
import MyPostCard from '@/components/molecules/MyPostCard.vue';

import { useCommunityStore } from '@/stores/community';
import { useUserStore } from '@/stores/user';
import { useRoute } from 'vue-router';

const route = useRoute();
const communityStore = useCommunityStore();
const userStore = useUserStore();

userStore.isItemActive = 0;
console.log(route.params.userId);
communityStore.getMyPostsAll();
</script>

<style>
.profile-post {
  width: calc(100% - 40px);
  margin-left: 20px;
  margin-top: 30px;
  display: flex;
  gap: 10px;
  margin-bottom: 30px;
}
.posts-wrap {
  width: calc(50% - 5px);
  display: flex;
  flex-direction: column;
  gap: 10px;
}
</style>
