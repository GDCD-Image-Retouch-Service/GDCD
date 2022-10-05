<template>
  <div class="profile-update sub">
    <router-view />
  </div>
</template>

<script setup>
import { useBasicStore } from '@/stores/basic.js';
import { useUserStore } from '@/stores/user.js';
import { useRoute } from 'vue-router';

const route = useRoute();
const userId = route.params.userId;
const userStore = useUserStore();
const basicStore = useBasicStore();

basicStore.isActiveFooter = 2;
userStore.getOtherinfo(userId);
userStore.headerSetDropdown = false;
userStore.loginModal = false;
userStore.logoutModal = false;
</script>

<style scoped>
.profile-update {
  margin-top: var(--size-h-header);
  margin-bottom: var(--size-h-footer);
}
</style>
