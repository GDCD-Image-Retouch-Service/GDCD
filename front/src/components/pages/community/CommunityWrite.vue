<template>
  <div class="community-write">
    <!-- 제목 -->
    <div class="wrap">
      <div>제목</div>
      <input type="text" class="write-input" v-model="data.title" />
    </div>

    <!-- 이미지 -->
    <div class="wrap">
      <div>이미지</div>

      <label for="image-input" class="image-input">
        <img :src="data.images" alt="" />
      </label>

      <input
        type="file"
        id="image-input"
        style="display: none"
        :v-model="data.images"
      />
    </div>

    <!-- 내용 -->
    <div class="wrap">
      <div>내용</div>
      <textarea
        class="content-area"
        maxlength="100"
        v-model="data.content"
      ></textarea>
    </div>

    <button class="button" @click="createPost(data)">제출</button>
  </div>
</template>

<script setup>
import { useCommunityStore } from '@/stores/community.js';
import { ref } from 'vue';

const communityStore = useCommunityStore();

const data = ref({
  title: '',
  content: '',
  privacyBound: 1,
  images: '',
  representative: 0,
});

const createPost = (data) => {
  communityStore.createPost(data);
};
</script>

<style scoped>
.community-write {
  width: calc(100% - 2 * var(--grid-side));
  margin: var(--grid-vertical) var(--grid-side);

  display: flex;
  flex-direction: column;
  gap: var(--grid-vertical);
}
.wrap {
  display: flex;
  flex-direction: column;
  gap: 20px;
}
.write-input {
  width: 100%;
  height: 37px;
  border: 1px solid var(--instagram-grey);
  border-radius: 5px;
  background-color: var(--light-main-color);
  margin: 0 auto;
  padding: 5px;
}
.content-area {
  padding: 5px;
  width: 100%;
  border: 1px solid var(--instagram-grey);
  resize: none;
  line-height: 22px;
  border-radius: 5px;
  height: 150px;
}
.image-input {
  width: 100%;
  padding: 5px 5px 100% 5px;
  border: 1px solid var(--instagram-grey);
  border-radius: 5px;
  background-color: var(--light-main-color);
}
</style>
