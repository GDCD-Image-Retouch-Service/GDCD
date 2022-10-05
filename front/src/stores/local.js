import { defineStore } from 'pinia';
import { ref, computed } from 'vue';

export const useLocalStore = defineStore('local', () => {
  // init
  // state
  const prev = ref(localStorage.prev);
  const path = ref(prev.value ? prev.value.split(';')[0] : '-');
  const url = ref(prev.value ? prev.value.split(';')[1] : '-');
  const score = ref(prev.value ? prev.value.split(';')[2] : '-');
  const eRank = ref(prev.value ? prev.value.split(';')[3] : '-');
  const qRank = ref(prev.value ? prev.value.split(';')[4] : '-');

  // action
  const resetPrev = () => {
    delete localStorage.prev;
  };
  const loadPrev = () => {
    if (localStorage.prev) {
      prev.value = localStorage.prev;

      [path.value, url.value, score.value, eRank.value, qRank.value] =
        prev.value.split(';');

      console.log(' * 로드 완료');
      console.log('prev :', prev.value);
      console.log('path :', path.value);
      console.log('url :', url.value);
      console.log('score :', score.value);
      console.log('eRank :', eRank.value);
      console.log('qRank :', qRank.value);
    } else {
      console.log(' * 로드 실패');
    }
  };
  const setPrev = () => {
    prev.value = `${path.value};${url.value};${score.value};${eRank.value};${qRank.value}`;
    localStorage.prev = prev.value;
  };
  const setPath = (data) => {
    path.value = data;
  };
  const setUrl = (data) => {
    url.value = data;
  };
  const setScore = (data) => {
    score.value = data;
  };
  const setERank = (data) => {
    eRank.value = data;
  };
  const setQRank = (data) => {
    qRank.value = data;
  };

  // getter
  const getPrev = computed(() => prev.value);
  const getPath = computed(() => path.value);
  const getUrl = computed(() => url.value);
  const getScore = computed(() => score.value);
  const getERank = computed(() => eRank.value);
  const getQRank = computed(() => qRank.value);

  return {
    // action
    resetPrev,
    loadPrev,
    setPrev,
    setPath,
    setUrl,
    setScore,
    setERank,
    setQRank,

    // getter
    getPrev,
    getPath,
    getUrl,
    getScore,
    getERank,
    getQRank,
  };
});
