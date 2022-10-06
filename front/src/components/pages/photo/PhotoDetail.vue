<template>
  <div class="photo-detail">
    <img
      :src="targetImage"
      :ref="targetImage"
      alt=""
      class="main-image common-image"
    />
    <div
      class="button-wrap"
      v-show="communityStore.selectImage.afterImage != null"
    >
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
@media (min-width: 1024px) {
  .photo-detail {
    min-width: 600px;
    max-width: 600px;
  }
}
.nullAfterImage {
  display: none;
}
.photo-detail {
  padding: var(--grid-vertical) var(--grid-side);
  margin: 0 auto;
  display: flex;
  flex-direction: column;
  gap: var(--grid-vertical);
  min-height: calc(100vh - var(--size-h-header) - var(--size-h-footer));
}
.main-image {
  width: 100%;
}
.tag-wrap {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  padding: 0 10px;
}
.tag {
  background-color: var(--theme-color);
  padding: 5px 15px;
  border-radius: 20px;
  line-height: 20px;
}
.button-wrap {
  width: 100%;
  display: flex;
  flex-direction: row-reverse;
}
.image-toggle-button {
  width: 40px;
  height: 20px;
  display: flex;
  overflow: hidden;
}
.button-left {
  width: 50%;
  border-right: none;
  border: 1px solid var(--instagram-grey);
  border-radius: 2px;
}
.button-right {
  width: 50%;
  border: 1px solid var(--instagram-grey);
}
.image-toggle-button:active .button-left {
  background-color: var(--instagram-grey);
}
.image-toggle-button:active .button-right {
  background-color: var(--instagram-dark-grey);
}
</style>
