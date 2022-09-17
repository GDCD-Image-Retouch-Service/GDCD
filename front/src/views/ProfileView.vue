<template>
  <div
    class="profile-view d-flex flex-column align-items-center main"
    style="padding-bottom: calc(var(--size-h-footer))"
  >
    <profile-header />
    <router-view />
  </div>
</template>

<script setup>
import ProfileHeader from '@/components/organisms/ProfileHeader.vue';
</script>

<style scoped></style>
