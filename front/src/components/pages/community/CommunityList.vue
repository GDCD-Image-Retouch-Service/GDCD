<template>
  <div class="community-list">
    <masonry-layout />
    <btn-scroll-to-top />
    <btn-go-to-write />
  </div>
</template>

<script setup>
import MasonryLayout from '@/components/organisms/MasonryLayout.vue';
import BtnScrollToTop from '@/components/molecules/BtnScrollToTop.vue';
import BtnGoToWrite from '@/components/molecules/common/btn/BtnGoToWrite';
import { useCommunityStore } from '@/stores/community.js';

const communityStore = useCommunityStore();
communityStore.getPostsAll();
</script>

<style>
.community-list {
  width: 100vw;
}
</style>
