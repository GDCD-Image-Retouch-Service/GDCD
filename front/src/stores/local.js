import { defineStore } from 'pinia';
import { ref, computed } from 'vue';

export const useLocalStore = defineStore('local', () => {
  // init
  // state
  const prev = ref('');
  const url = ref('');
  const imageId = ref('');
  const eScore = ref('');
  const qScore = ref('');

  // action
  const loadPrev = () => {
    if (localStorage.prev) {
      prev.value = localStorage.prev;
    } else {
      console.log('로드 실패');
    }
  };
  const setPrev = () => {
    prev.value =
      url.value + ';' + imageId.value + ';' + eScore.value + ';' + qScore.value;
  };
  const setUrl = (data) => {
    url.value = data;
  };
  const setImageId = (data) => {
    imageId.value = data;
  };
  const setEScore = (data) => {
    eScore.value = data;
  };
  const setQScore = (data) => {
    qScore.value = data;
  };

  // getter
  const getPrev = computed(() => prev.value);

  return {
    // action
    loadPrev,
    setPrev,
    setUrl,
    setImageId,
    setEScore,
    setQScore,

    // getter
    getPrev,
  };
});
