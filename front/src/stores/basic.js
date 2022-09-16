import { defineStore } from 'pinia';

export const useBasicStore = defineStore('basicStore', {
  state: () => ({
    // 푸터 네브 바
    isActiveFooter: 0,
  }),

  actions: {},
});
