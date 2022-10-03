import { ref, computed } from 'vue';
import { defineStore } from 'pinia';
import { useRouter } from 'vue-router';

export const useAccountStore = defineStore('account', () => {
  // init
  const router = useRouter();

  // state
  // const msg = ref('');
  // const profile = ref(null);
  const email = ref('');
  const nickname = ref('');
  const token = ref(localStorage.getItem('token'));
  const isLogined = ref(localStorage.getItem('token') != null);

  // action
  function setEmail(newEmail) {
    email.value = newEmail;
  }

  function setNickname(newNickname) {
    nickname.value = newNickname;
  }

  function setToken(newToken) {
    localStorage.setItem('token', newToken);
    token.value = newToken;
  }

  function setIsLogined(newIsLogined) {
    if (newIsLogined) {
      isLogined.value = true;
    } else {
      isLogined.value = false;
      delete localStorage.token;
      router.push('/main');
    }
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
