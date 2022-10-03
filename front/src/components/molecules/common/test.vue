<template>
  <div>
    <article class="main-manual-title">
      <h2>개떡찰떡 사용법</h2>
      <div class="toggle-button">
        <span class="material-icons-outlined" @click="toggleShowDescription">
          expand_less
        </span>
      </div>
    </article>
    <article class="article-description" v-if="isShowDescription">
      {{ data.description }}
    </article>
  </div>
</template>

<script setup>
import { ref } from 'vue';
const isShowDescription = ref(false);
const toggleShowDescription = () => {
  isShowDescription.value = !isShowDescription.value;
};
</script>

<style scoped>
.main-manual-title {
  width: 40%;
  height: 100%;
  display: flex;
  justify-content: flex-end;
  align-items: center;
}
</style>
