import { ref, computed } from 'vue';
import { defineStore } from 'pinia';

export const useAccountStore = defineStore('account', () => {
  // state
  const email = ref('');
  const nickname = ref('');
  const token = ref('');
  const isLogined = ref(false);

  // action
  function setEmail(newEmail) {
    email.value = newEmail;
  }

  function setNickname(newNickname) {
    nickname.value = newNickname;
  }

  function setToken(newToken) {
    token.value = newToken;
  }

  function setIsLogined(newIsLogined) {
    isLogined.value = newIsLogined;
  }

  // getter
  const getEmail = computed(() => email.value);
  const getNickname = computed(() => nickname.value);
  const getToken = computed(() => token.value);
  const getIsLogined = computed(() => isLogined.value);

  return {
    // state
    email,
    nickname,
    token,
    isLogined,

    // action
    setEmail,
    setNickname,
    setToken,
    setIsLogined,

    // getter
    getEmail,
    getNickname,
    getToken,
    getIsLogined,
  };
});
