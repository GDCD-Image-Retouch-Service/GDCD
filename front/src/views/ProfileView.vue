<template>
  <div class="profile-view">
    <profile-header />
    <router-view />
  </div>
</template>

<script setup>
import ProfileHeader from '@/components/organisms/profile/ProfileHeader.vue';
import { useBasicStore } from '@/stores/basic.js';

const basicStore = useBasicStore();
basicStore.isActiveFooter = 2;
</script>

<style scoped></style>
