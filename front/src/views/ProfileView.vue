<template>
  <div class="Community-view d-flex flex-column align-items-center main">
    <profile-header />
    <router-view />

    <!-- ~~~/profile/ -->
  </div>
</template>

<script setup>
import ProfileHeader from '@/components/organisms/ProfileHeader.vue';
</script>

<style scoped></style>
