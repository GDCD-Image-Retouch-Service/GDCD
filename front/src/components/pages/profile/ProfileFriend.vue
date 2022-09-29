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
.profile-friend {
  width: calc(100% - 2 * var(--grid-side));
  margin: var(--grid-side);
}
</style>
