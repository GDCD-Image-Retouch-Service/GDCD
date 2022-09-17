<template>
  <div
    class="card-carousel"
    @click="moveCrousel()"
    :style="{ transform: carouselValue }"
  >
    <img :src="originImage" alt="" class="card-image" />
    <img :src="editImage" alt="" class="card-image" />
  </div>
</template>

<script setup>
import { defineProps, ref, toRefs } from 'vue';

const props = defineProps({
  originImage: Text,
  editImage: Text,
});

// 이 줄 추가해야 안정적임
const { originImage, editImage } = toRefs(props);

let carouselValue = ref('translateX(0)');

const moveCrousel = () => {
  if (carouselValue.value === 'translateX(0)') {
    carouselValue.value = 'translateX(calc(-30% - 5px))';
  } else {
    carouselValue.value = 'translateX(0)';
  }
};
</script>

<style scoped>
.card-carousel {
  width: calc(200% + 10px);
  display: flex;
  gap: 10px;
  transition-duration: 0.3s;
}
.card-image {
  width: calc(40% - 5px);
  /* transform: translateX(calc(-20% - 50px)); */
  object-fit: cover;
}
</style>
