<template>
  <profile-friend-header />
  <profile-friend-follow v-if="userStore.isFriendActive" />
  <profile-friend-following v-else />
</template>

<script setup>
import ProfileFriendHeader from '@/components/molecules/profile/ProfileFriendHeader.vue';
import ProfileFriendFollow from '@/components/molecules/profile/ProfileFriendFollow.vue';
import ProfileFriendFollowing from '@/components/molecules/profile/ProfileFriendFollowing.vue';
import { useUserStore } from '@/stores/user.txt';

const userStore = useUserStore();
</script>

<style scoped></style>
