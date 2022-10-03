<template>
  <div class="icon-rank">
    <img
      v-if="props.rank == 1"
      :src="require('@/assets/grade/1.png')"
      style="width: 48px; height: 48px; object-fit: cover"
      alt="your image"
    />
    <img
      v-else-if="props.rank == 2"
      :src="require('@/assets/grade/2.png')"
      style="width: 48px; height: 48px; object-fit: cover"
      alt="your image"
    />
    <img
      v-else-if="props.rank == 3"
      :src="require('@/assets/grade/3.png')"
      style="width: 48px; height: 48px; object-fit: cover"
      alt="your image"
    />
    <img
      v-else-if="props.rank == 4"
      :src="require('@/assets/grade/4.png')"
      style="width: 48px; height: 48px; object-fit: cover"
      alt="your image"
    />
    <img
      v-else-if="props.rank == 5"
      :src="require('@/assets/grade/5.png')"
      style="width: 48px; height: 48px; object-fit: cover"
      alt="your image"
    />
    <img
      v-else-if="props.rank == 6"
      :src="require('@/assets/grade/6.png')"
      style="width: 48px; height: 48px; object-fit: cover"
      alt="your image"
    />
    <img
      v-else-if="props.rank == 7"
      :src="require('@/assets/grade/7.png')"
      style="width: 48px; height: 48px; object-fit: cover"
      alt="your image"
    />
    <img
      v-else-if="props.rank == 8"
      :src="require('@/assets/grade/8.png')"
      style="width: 48px; height: 48px; object-fit: cover"
      alt="your image"
    />
    <img
      v-else
      :src="require('@/assets/grade/9.png')"
      style="width: 48px; height: 48px; object-fit: cover"
      alt="your image"
    />
  </div>
</template>

<script setup>
import { defineProps } from 'vue';

const props = defineProps({
  rank: Number,
});
</script>

<style scoped></style>
