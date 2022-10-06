<template>
  <div
    class="landing-view main position-fixed d-flex flex-column align-items-center justify-content-center"
  >
    <anim-chaltteogi />
  </div>
</template>

<script setup>
import AnimChaltteogi from '@/components/atoms/anim/AnimChaltteogi.vue';
import { onMounted } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();

onMounted(() => {
  setTimeout(function () {
    router.push('main');
  }, 1800);
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
