import { ref, computed } from 'vue';
import { defineStore } from 'pinia';

export const useMainStore = defineStore('main', () => {
  // state
  const isCamMode = ref(false);

  // action
  function isCamModeOn() {
    isCamMode.value = true;
  }

  function isCamModeOff() {
    isCamMode.value = false;
  }

  function isCamModeToggle() {
    isCamMode.value = !isCamMode.value;
  }

  // getter
  const getIsCamMode = computed(() => isCamMode.value);

  return {
    // state
    isCamMode,

    // action
    isCamModeOn,
    isCamModeOff,
    isCamModeToggle,

    // getter
    getIsCamMode,
  };
});
