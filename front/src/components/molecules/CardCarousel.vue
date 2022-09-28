<template>
  <div style="width: 100%">
    <div v-if="secondImage !== ''">
      <btn-toggle @click="moveCrousel()" />

      <div class="card-carousel">
        <div class="image-wrap" :style="{ transform: carouselValue }">
          <div class="box">
            <img :src="firstImage" alt="" class="card-image" />
            <div class="tag-wrap">
              <div v-for="tag in firstTags" :key="tag" class="tag">
                {{ tag }}
              </div>
            </div>
          </div>
          <div class="box">
            <img :src="secondImage" alt="" class="card-image" />
            <div class="tag-wrap">
              <div v-for="tag in secondTags" :key="tag" class="tag">
                {{ tag }}
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <img v-else :src="firstImage" alt="" class="card-image" />
  </div>
</template>

<script setup>
import { defineProps, ref, toRefs } from 'vue';
import BtnToggle from '@/components/molecules/common/btn/BtnToggle.vue';

const props = defineProps({
  firstImage: String,
  secondImage: String,
  firstTags: Object,
  secondTags: Object,
});

// 이 줄 추가해야 안정적임
const { firstImage, secondImage, firstTags, secondTags } = toRefs(props);

let carouselValue = ref('translateX(0)');

const moveCrousel = () => {
  if (carouselValue.value === 'translateX(0)') {
    carouselValue.value = 'translateX(calc(-50%))';
  } else {
    carouselValue.value = 'translateX(0)';
  }
};
</script>

<style scoped>
.card-carousel {
  width: 100%;
  overflow: hidden;
}
.image-wrap {
  width: calc(200% + 30px);
  display: flex;
  /* overflow: hidden; */
  transition-duration: 0.3s;
}
.box {
  width: 100%;
  display: flex;
  gap: 30px;
  flex-direction: column;
}
.card-image {
  width: calc(100%);
  /* transform: translateX(calc(-20% - 50px)); */
  object-fit: cover;
}
/* 태그 */
.tag-wrap {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
}
.tag {
  padding: 0 14px;
  line-height: 27px;
  height: 27px;

  background-color: #e7d7e9;
  /* color: var(--light-main-color); */
  border-radius: 30px;
}
</style>
