<template>
  <div v-if="secondImage !== ''">
    <btn-toggle @click="moveCrousel()" />

    <div class="card-carousel">
      <div
        style="
          width: 100%;
          display: flex;
          overflow: hidden;
          gap: 20px;
          transition-duration: 0.3s;
        "
        :style="{ transform: carouselValue }"
      >
        <img :src="firstImage" alt="" class="card-image" />
        <img :src="secondImage" alt="" class="card-image" />
      </div>
    </div>
  </div>

  <img v-else :src="firstImage" alt="" class="card-image" />
</template>

<script setup>
import { defineProps, ref, toRefs } from 'vue';
import BtnToggle from '@/components/molecules/common/btn/BtnToggle.vue';

const props = defineProps({
  firstImage: String,
  secondImage: String,
});

// 이 줄 추가해야 안정적임
const { firstImage, secondImage } = toRefs(props);

let carouselValue = ref('translateX(0)');

const moveCrousel = () => {
  if (carouselValue.value === 'translateX(0)') {
    carouselValue.value = 'translateX(calc(-50% - 10px))';
  } else {
    carouselValue.value = 'translateX(0)';
  }
};
</script>

<style scoped>
.card-carousel {
  width: calc(200% + 20px);
  display: flex;
  gap: 30px;
  overflow: hidden;
}
.card-image {
  width: 100%;
  /* transform: translateX(calc(-20% - 50px)); */
  object-fit: cover;
}
</style>
