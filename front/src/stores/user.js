import { ref, computed } from 'vue';
import { defineStore } from 'pinia';

export const useUserStore = defineStore('user', () => {
  // state
  const email = ref('');
  const nickname = ref('');
  const token = ref('');

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

  // getter
  const getEmail = computed(() => email.value);
  const getNickname = computed(() => nickname.value);
  const getToken = computed(() => token.value);

  return {
    // state
    email,
    nickname,
    token,

    // action
    setEmail,
    setNickname,
    setToken,

    // getter
    getEmail,
    getNickname,
    getToken,
  };
});
