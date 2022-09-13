<template>
  <div>
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
    <masonry-layout />
  </div>
</template>

<script>
import MasonryLayout from '@/components/organisms/MasonryLayout.vue';

export default {
  components: {
    MasonryLayout,
  },
};
</script>

<style></style>
