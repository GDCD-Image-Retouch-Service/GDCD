<template>
  <div>
    <button class="hi-button">하이</button>
  </div>
</template>

<script setup>
import { useCommunityStore } from '@/stores/community.js';
import { ref, onMounted } from 'vue';
const communityStore = useCommunityStore();

const targetImage = ref(communityStore.selectImage.beforeImage.imageUrl);
console.log('?');
onMounted(() => {
  console.log(document.getElementsByClassName('hi-button')[0], 'ㅇㅇㅇ');
  document
    .getElementsByClassName('hi-button')[0]
    .addEventListener('touchstart', function () {
      targetImage.value = communityStore.selectImage.afterImage.imageUrl;
      console.log(targetImage);
    });

  document
    .getElementsByClassName('hi-button')[0]
    .addEventListener('touchend', function () {
      targetImage.value = communityStore.selectImage.beforeImage.imageUrl;
      console.log(targetImage);
    });
});
</script>

<style scoped></style>
