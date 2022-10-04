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
import { onBeforeMount, onUnmounted } from 'vue';
const route = useRoute();
const communityStore = useCommunityStore();
const userStore = useUserStore();

userStore.isItemActive = 0;

userStore.resetVariable();
onBeforeMount(() => {
  userStore.getOtherinfo(route.params.userId);
  communityStore.getMyPostsAll(route.params.userId);
});

onUnmounted(() => {});
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

@media screen and (min-width: 1500px) {
  .profile-post {
    width: calc(100% - 500px);
    margin-left: 250px;
    margin-top: 30px;
    display: flex;
    gap: 50px;
    margin-bottom: 30px;
  }
  .posts-wrap {
    width: 100%;
    display: flex;
    flex-wrap: wrap;
    gap: 30px;
  }
  .my-post-card {
    width: calc(25% - 25px);
  }
}

@media screen and (min-width: 1024px) and (max-width: 1500px) {
  .profile-post {
    width: calc(100% - 300px);
    margin-left: 150px;
    margin-top: 30px;

    gap: 30px;
    margin-bottom: 30px;
  }
  .posts-wrap {
    width: 100%;
    display: flex;
    flex-wrap: wrap;
    gap: 30px;
  }
  .my-post-card {
    width: calc(25% - 22.5px);
  }
}
</style>
