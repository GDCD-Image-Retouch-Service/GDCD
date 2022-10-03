<template>
  <div class="photo-detail">
    <img
      :src="targetImage"
      :ref="targetImage"
      alt=""
      class="main-image common-image"
    />
    <div class="button-wrap">
      <div class="image-toggle-button">
        <div class="button-left"></div>
        <div class="button-right"></div>
      </div>
    </div>
    <div class="tag-wrap">
      <div
        v-for="(tag, index) in communityStore.selectImage.beforeImage.imageTag"
        :key="index"
        class="tag common-image"
      >
        {{ tag }}
      </div>
    </div>
  </div>
</template>

<script setup>
import { useCommunityStore } from '@/stores/community.js';
import { ref, onMounted } from 'vue';
const communityStore = useCommunityStore();

const targetImage = ref(communityStore.selectImage.beforeImage.imageUrl);

onMounted(() => {
  document
    .getElementsByClassName('image-toggle-button')[0]
    .addEventListener('touchstart', function () {
      targetImage.value = communityStore.selectImage.afterImage.imageUrl;
      console.log(targetImage);
    });

  document
    .getElementsByClassName('image-toggle-button')[0]
    .addEventListener('touchend', function () {
      targetImage.value = communityStore.selectImage.beforeImage.imageUrl;
      console.log(targetImage);
    });
});
</script>

<style scoped>
.photo-detail {
  padding: var(--grid-vertical) var(--grid-side);
}
.main-image {
  width: 100%;
}
.tag-wrap {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  margin-top: var(--grid-vertical);
}
.tag {
  padding: 3px 10px;
}
.button-wrap {
  width: 100%;
  display: flex;
  flex-direction: row-reverse;
}
.image-toggle-button {
  width: 40px;
  margin-top: var(--grid-vertical);
  height: 20px;
  border-radius: 30px;
  background-color: var(--instagram-grey);
  display: flex;
  overflow: hidden;
}
.button-left {
  width: 50%;
  background-color: var(--instagram-dark-grey);
}
.button-right {
  width: 50%;
  border-radius: 0 30px 30px 0;
}
.image-toggle-button:active .button-left {
  background-color: var(--instagram-grey);
}
.image-toggle-button:active .button-right {
  background-color: var(--instagram-dark-grey);
}
</style>
