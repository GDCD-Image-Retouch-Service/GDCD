<template>
  <div
    class="landing-view position-fixed d-flex flex-column align-items-center justify-content-center"
    ref="something"
  >
    <anim-chaltteogi />
    <anim-logo />
  </div>
</template>

<script setup>
import AnimChaltteogi from '@/components/atoms/anim/AnimChaltteogi.vue';
import AnimLogo from '@/components/atoms/anim/AnimLogo.vue';
import { onMounted, ref } from 'vue';

// Data
const something = ref(null);

onMounted(() => {
  setTimeout(function () {
    something.value.style.background = 'red';
  }, 2000);
});
</script>

<style scoped>
.landing-view {
  z-index: 9999;
  top: 0px;
  width: 100vw;
  height: 100vh;
  background: var(--color-main);
}
</style>
