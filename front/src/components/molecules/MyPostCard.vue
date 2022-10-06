<template>
  <div class="post-card">
    <i class="bi bi-three-dots dot-icon"></i>

    <!-- post card image -->
    <div
      :style="{ backgroundImage: 'url(' + post.images.imageUrl + ')' }"
      class="post-image"
      @click="
        $router.push({
          name: 'CommunityDetail',
          params: { postId: post.postId },
        })
      "
    ></div>

    <!-- post card -title -->
    <div class="post-card-title">
      {{ post.title }}
    </div>

    <date-format :updateInfo="post.registTime" class="mypost-date" />
  </div>
</template>

<script setup>
import DateFormat from '@/components/molecules/common/DateFormat.vue';
import { defineProps, toRefs } from 'vue';
// import { useCommunityStore } from '@/stores/community';

// const communityStore = useCommunityStore();

const props = defineProps({
  post: Object,
});

const { post } = toRefs(props);
</script>

<style scoped>
.post-card {
  position: relative;
  z-index: 1;

  width: 100%;
  border-radius: 10px;

  display: flex;
  flex-direction: column;
  gap: 20px;
  color: var(--instagram-dark-grey);
  padding-bottom: 20px;
  box-shadow: 0 4px 4px -4px rgba(0, 0, 0, 0.5);
  border: 1px solid rgba(0, 0, 0, 0.12);
  background-color: var(--light-main-color);
  overflow: hidden;
  background-color: var(--color-main);
}
.post-card-title {
  text-align: start;
  width: calc(100% - 10px);
  margin-left: 5px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  height: 20px;
  line-height: 20px;
  font-size: 16px;
  color: var(--popper-theme-text-color);
  font-weight: 700;
}

.dot-icon {
  position: absolute;
  right: 0;
  margin: 5px 5px 0 0;
}
.like-bookmark {
  display: flex;
  width: calc(100% - 70px);
  margin-left: 5px;
  font-size: 14px;
  gap: 5px;
}
.herat-wrap {
  display: flex;
  gap: 3px;
}
.post-image {
  width: 100%;
  padding-bottom: 100%;
  background-position: center;
  background-size: cover;
}
.mypost-date {
  margin-left: 10px;
  font-size: var(--sub-font-size);
}
</style>
