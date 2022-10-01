<template>
  <div class="profile-view">
    <profile-header />
    <router-view />
  </div>
</template>

<script setup>
import ProfileHeader from '@/components/organisms/profile/ProfileHeader.vue';
import { useBasicStore } from '@/stores/basic.js';
import { useUserStore } from '@/stores/user.js';
import { useRoute } from 'vue-router';

const route = useRoute();
const userId = route.params.userId;
const userStore = useUserStore();
const basicStore = useBasicStore();

basicStore.isActiveFooter = 2;
userStore.getOtherinfo(userId);
</script>

<style scoped></style>
