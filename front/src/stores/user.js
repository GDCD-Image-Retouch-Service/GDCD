import { defineStore } from 'pinia';
import { ref, computed } from 'vue';

export const useUserStore = defineStore('user', () => {
  const user = ref([]);
  function addList(param) {
    user.value.push(param);
  }
  const getDataAll = computed(() => user.value);
  return { user, addList, getDataAll };
});
