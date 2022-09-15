<template>
  <div class="post-card outer" @click="$router.push({ name:'CommunityDetail', params: { postId: 1 } })">
    <!-- post card header -->
    <div
      class="d-flex align-items-center justify-content-start"
      style="width: 100%; height: 40px"
    >
      <b-avatar
        variant="info"
        size="2em"
        src="https://placekitten.com/300/300"
        style="margin-left: 8px"
      ></b-avatar>
      <div style="margin-left: 8px">성덕</div>
      <div class="flex-grow-1"></div>
      <i class="bi bi-three-dots" style="margin-right: 8px"></i>
    </div>

    <!-- post card image -->
    <b-img :src="`${url}`" fluid-grow alt="Responsive image"></b-img>

    <div
      class="d-flex align-items-center justify-content-end"
      style="margin-left: 8px; margin-right: 8px; font-size: 14pt; height: 32px"
    >
      <i class="bi bi-heart"></i>
      <div style="margin-left: 8px">0</div>
      <div class="flex-grow-1"></div>
      <i class="bi bi-bookmark"></i>
    </div>

    <div>
      <div>게시글 제목</div>
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
  url: String,
});

const { url } = toRefs(props);
</script>

<style scoped>
.post-card {
  z-index: 1;
  margin-top: 10px;
  margin-left: 10px;
  width: calc(100% - 20px);
  height: calc(100% - 20px);
  border-radius: 20px;
}
</style>
