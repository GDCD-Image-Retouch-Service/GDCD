import { ref, computed } from 'vue';
import { defineStore } from 'pinia';

export const useHomeStore = defineStore('home', () => {
  // state
  const isDark = ref(
    localStorage.getItem('theme') != undefined &&
      localStorage.getItem('theme') == 'dark',
  );

  // action
  const setIsDarkToggle = () => {
    isDark.value = !isDark.value;
  };

  // getter
  const getIsDark = computed(() => isDark.value);

  return {
    // state
    isDark,

    // action
    setIsDarkToggle,

    // getter
    getIsDark,
  };
});
