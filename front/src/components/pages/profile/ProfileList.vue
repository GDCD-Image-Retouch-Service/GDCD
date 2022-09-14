<template>
  <div>
    <div style="height: 20px"></div>
    <div class="d-flex align-items-center justify-content-center">
      <b-avatar
        variant="info"
        size="6rem"
        src="https://placekitten.com/300/300"
      ></b-avatar>
      <div>게시글</div>
      |
      <div>북마크</div>
      |
      <div>팔로우</div>
      |
      <div>팔로워</div>
    </div>
    <div style="height: 20px"></div>
    <div style="width: 100%; height: 2px; background: lightgray"></div>
    <masonry-layout />
  </div>
</template>

<script setup>
import MasonryLayout from '@/components/organisms/MasonryLayout.vue';
</script>

<style></style>
