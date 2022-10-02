<template>
  <div class="profile-post">
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
communityStore.getMyPostsAll(route.params.userId);
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
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
}
.my-post-card {
  width: calc(50% - 5px);
}
</style>
