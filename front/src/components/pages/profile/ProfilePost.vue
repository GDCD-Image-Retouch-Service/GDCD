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

console.log(route);
</script>

<style scoped>
@media (min-width: 1024px) {
  .profile-post {
    min-width: 935px;
    max-width: 935px;
  }
  .posts-wrap {
    gap: 28px !important;
  }
  .my-post-card {
    max-width: calc(25% - 21px);
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
</style>
