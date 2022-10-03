<template>
  <div class="photo-detail">
    <img
      :src="targetImage"
      :ref="targetImage"
      alt=""
      class="main-image common-image"
    />
    <div>
      <button class="image-toggle-button">클릭</button>
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
.image-toggle-button {
  width: 100%;
}
</style>
