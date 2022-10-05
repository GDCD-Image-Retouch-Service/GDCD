<template>
  <div class="profile-friend">
    <profile-friend-header />
    <profile-friend-follow v-if="userStore.isFriendActive" />
    <profile-friend-following v-else />
  </div>
</template>

<script setup>
import ProfileFriendHeader from '@/components/molecules/profile/ProfileFriendHeader.vue';
import ProfileFriendFollow from '@/components/molecules/profile/ProfileFriendFollow.vue';
import ProfileFriendFollowing from '@/components/molecules/profile/ProfileFriendFollowing.vue';
import { useUserStore } from '@/stores/user';
import { useCommunityStore } from '@/stores/community';

import { useRoute } from 'vue-router';

const route = useRoute();

const userStore = useUserStore();
const communityStore = useCommunityStore();
communityStore.getMyPostsAll(route.params.userId);
</script>

<style scoped>
@media (min-width: 1024px) {
  .profile-friend {
    min-width: 935px;
    max-width: 935px;
  }
  .scrap-wrap {
    max-width: calc(25% - 21px);
  }
}

.profile-friend {
  width: calc(100% - 2 * var(--grid-side));
  margin: 0 auto;
}
</style>
