<template>
  <div class="post-card">
    <i class="bi bi-three-dots dot-icon"></i>

    <!-- post card image -->
    <img
      style="width: 100%"
      :src="post.images.imageUrl"
      alt=""
      @click="
        $router.push({
          name: 'CommunityDetail',
          params: { postId: post.postId },
        })
      "
    />
    <!-- post card -title -->
    <div class="post-card-title">
      {{ post.title }}
    </div>

    <date-format :updateInfo="post.updateTime" style="margin-left: 10px" />
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
  font-size: 14px;
  overflow: hidden;
}
.post-card-title {
  text-align: start;
  width: calc(100% - 10px);
  margin-left: 5px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;

  font-size: 14px;
}
.post-card-userinfo {
  display: flex;
  width: calc(100% - 10px);
  margin-right: 10px;
  justify-content: flex-end;
  font-size: 14px;
  margin-top: 35px;
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
</style>
