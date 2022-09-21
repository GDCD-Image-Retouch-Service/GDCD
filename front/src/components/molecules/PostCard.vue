<template>
  <div
    class="post-card"
    @click="$router.push({ name: 'CommunityDetail', params: { postId: 1 } })"
  >
    <i class="bi bi-three-dots dot-icon"></i>

    <!-- post card image -->
    <b-img :src="`${url}`" fluid-grow alt="Responsive image"></b-img>

    <!-- post card -title -->
    <div class="post-card-title">
      게시글 제목게시글 제목게시글 제목게시글 제목게시글 제목게시글 제목게시글
      제목게시글 제목게시글 제목게시글 제목
    </div>

    <!-- post card userprofile -->
    <div class="post-car-userinfo">
      <img src="@/assets/sdprofile.png" class="profile-image" alt="" />
      <div style="margin-left: 8px">성덕</div>
    </div>

    <!-- post card like and bookmark -->
    <div class="like-bookmark">
      <div class="herat-wrap">
        <i class="bi bi-heart"></i>
        <div>0</div>
      </div>
      <i class="bi bi-bookmark"></i>
    </div>

    <div
      class="d-flex align-items-center justify-content-end"
      style="height: 28px; background: #f4f4f4"
    >
      <img
        src="@/assets/grade/1.png"
        style="margin-right: 4px; height: 20px"
        alt="logo"
      />
      <img
        src="@/assets/grade/6.png"
        style="margin-right: 4px; height: 20px"
        alt="logo"
      />
    </div>
    <!-- https://picsum.photos/200/300 랜덤으로 사진 보내줌-->
  </div>
</template>

<script setup>
import { defineProps, toRefs } from 'vue';

const props = defineProps({
  url: {
    type: String,
    default: `require(@/assets/logo.png)`,
  },
});

const { url } = toRefs(props);
</script>

<style scoped>
.post-card {
  position: relative;
  z-index: 1;
  margin-top: 10px;
  margin-left: 10px;
  width: calc(100% - 20px);
  height: calc(100% - 20px);
  border-radius: 10px;

  display: flex;
  flex-direction: column;
  gap: 10px;
  color: var(--instagram-dark-grey);
  padding-bottom: 10px;
  box-shadow: 0 4px 4px -4px rgba(0, 0, 0, 0.5);
  border: 1px solid rgba(0, 0, 0, 0.12);
  background-color: var(--light-main-color);
}
.post-card-title {
  text-align: start;
  width: calc(100% - 10px);
  margin-left: 5px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;

  font-size: 12px;
}
.post-car-userinfo {
  display: flex;
  width: calc(100% - 10px);
  margin-right: 10px;
  justify-content: flex-end;
  font-size: 12px;
  margin-top: 20px;
}
.profile-image {
  width: 20px;
  height: 20px;
  object-fit: cover;
  border-radius: 20px;
}
.dot-icon {
  position: absolute;
  right: 0;
  margin: 5px 5px 0 0;
}
.like-bookmark {
  display: flex;
  width: calc(100% - 10px);
  margin-left: 5px;
  font-size: 14px;
  gap: 5px;
}
.herat-wrap {
  display: flex;
  gap: 3px;
}
</style>
