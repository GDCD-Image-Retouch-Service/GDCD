<template>
  <div class="community-detail">
    <community-detail-card />
    <community-comment />
  </div>
</template>

<script setup>
import CommunityDetailCard from '@/components/organisms/community/CommunityDetailCard.vue';
import CommunityComment from '@/components/organisms/community/CommunityComment.vue';
</script>

<style scoped>
@media (min-width: 1024px) {
  .community-detail {
    min-width: 600px;
    max-width: 600px;
  }
}
.community-detail {
  width: calc(100% - 2 * var(--grid-side));
  margin: 0 auto;
  overflow: scroll;
}
</style>
