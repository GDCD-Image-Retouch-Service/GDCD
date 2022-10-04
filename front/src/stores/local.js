import { defineStore } from 'pinia';
import { ref, computed } from 'vue';

export const useLocalStore = defineStore('local', () => {
  // init
  // state
  const prev = ref('');
  const curr = ref('');
  const next = ref('');

  // action
  const setPrev = (step) => {
    localStorage.prev = step;
    prev.value = step;
  };
  const setCurr = (newStep) => {
    curr.value = newStep;
  };
  const setNext = (newStep) => {
    next.value = newStep;
  };
  // getter
  const getPrev = computed(() => prev.value);
  const getCurr = computed(() => prev.value);
  const getNext = computed(() => prev.value);

  return {
    // action
    setPrev,
    setCurr,
    setNext,

    // getter
    getPrev,
    getCurr,
    getNext,
  };
});
