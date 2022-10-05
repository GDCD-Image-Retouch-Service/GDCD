import { defineStore } from 'pinia';
import { ref, computed } from 'vue';

export const useLocalStore = defineStore('local', () => {
  // init
  // state
  const prev = ref('');
  const curr = ref('');

  // action
  const setPrev = (step) => {
    prev.value = step;
  };
  const setCurr = (step) => {
    // 현 url
    // image id
    // image url
    // score e
    // score q
    curr.value = step;
  };

  // getter
  const getPrev = computed(() => prev.value);
  const getCurr = computed(() => prev.value);

  return {
    // action
    setPrev,
    setCurr,

    // getter
    getPrev,
    getCurr,
  };
});
