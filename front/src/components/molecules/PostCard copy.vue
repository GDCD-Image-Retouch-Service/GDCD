// 밑에 두기
<template>
  <div class="post-card outer">
    <b-img :src="`${url}`" fluid-grow alt="Responsive image"></b-img>

    <div>
      <div>게시글 제목</div>
    </div>

    <div class="d-flex flex-column align-items-center" style="height: 68px">
      <div
        class="d-flex align-items-center justify-content-end"
        style="width: 90%; height: 40px"
      >
        <div style="margin-right: 10px">성덕</div>
        <b-avatar
          variant="info"
          size="2em"
          src="https://placekitten.com/300/300"
        ></b-avatar>
      </div>

      <!-- linear -->
      <div style="width: 90%; height: 2px; background: lightgray"></div>

      <div class="d-flex" style="width: 90%">
        <i class="bi bi-heart">0</i>
        <i class="bi bi-bookmark"></i>

        <div class="flex-grow-1"></div>

        <img
          src="@/assets/grade/1.png"
          style="margin-left: 8px; height: 20px"
          alt="logo"
        />
        <img
          src="@/assets/grade/6.png"
          style="margin-left: 8px; height: 20px"
          alt="logo"
        />
      </div>
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
