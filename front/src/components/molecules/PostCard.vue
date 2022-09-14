<template>
  <div class="post-card outer">
    <img
      class="fit-picture"
      :src="`${url}`"
      style="width: 100%"
      alt="사진뻑남"
    />
    <div class="d-flex flex-column align-items-center" style="height: 80px">
      <div>게시글</div>
      <div>공사중</div>

      <!-- spacer -->
      <div style="width: 90%; height: 2px; background: lightgray"></div>

      <div class="d-flex" style="width: 90%">
        <i class="bi bi-heart">0</i>
        <i class="bi bi-bookmark"></i>

        <div class="flex-grow-1"></div>

        <b-avatar
          variant="info"
          size="1.5rem"
          src="https://placekitten.com/300/300"
        ></b-avatar>
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
