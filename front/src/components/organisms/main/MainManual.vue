<template>
  <div class="main-manual">
    <article class="main-manual-title">
      <span>개떡찰떡 사용법</span>
      <div class="toggle-button" :class="{ active: isShowDescription }">
        <span
          class="material-icons-outlined"
          @click="toggleShowDescription"
          v-if="isShowDescription"
        >
          expand_less
        </span>
        <span
          class="material-icons-outlined"
          @click="toggleShowDescription"
          v-else
        >
          keyboard_arrow_down
        </span>
      </div>
    </article>
    <article class="article-description" v-if="isShowDescription">
      <main-manual-desc1></main-manual-desc1>
    </article>
  </div>
</template>

<script setup>
import MainManualDesc1 from '@/components/organisms/main/MainManualDesc1.vue';
import { ref } from 'vue';
const isShowDescription = ref(false);
const toggleShowDescription = () => {
  isShowDescription.value = !isShowDescription.value;
};
</script>

<style scoped>
.active {
  transform: rotate(180);
  transition-duration: 1s;
}
.main-manual {
  width: 100%;
}
.main-manual-title {
  width: 100%;
  height: 50px;
  display: flex;
  justify-content: flex-start;
  align-items: center;
  background-color: #ffffff;
  justify-content: space-between;
  padding: 14px 20px;
  font-size: 16px;
}
</style>
