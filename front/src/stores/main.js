import { ref, computed } from 'vue';
import { defineStore } from 'pinia';

export const useMainStore = defineStore('main', () => {
  // state
  const count = ref(0);
  const isCamMode = ref(false);

  // action
  function increment() {
    count.value++;
  }
  function changeMode() {
    isCamMode.value = !isCamMode.value;
  }

  // getter
  const getDouble = computed(() => count.value * 2);
  return {
    // state
    count,
    isCamMode,
    // action
    increment,
    changeMode,
    // getter
    getDouble,
  };
});
