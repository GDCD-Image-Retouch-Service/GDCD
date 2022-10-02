import axios from 'axios';
import { useAccountStore } from '@/stores/account';

const createAxiosApi = () => {
  // axios intercepter 설정에 따라 수정할 여지 있음
  const AxiosApi = axios.create({
    baseURL: process.env.VUE_APP_REST_SERVER,
    headers: {
      'content-type': 'application/json; charset=UTF-8',
    },
  });

  AxiosApi.interceptors.request.use(
    (config) => {
      const accountStore = useAccountStore();
      if (accountStore.getIsLogined) {
        config.headers.common['token'] = accountStore.getToken;
      }
      return config;
    },
    (error) => {
      Promise.reject(error);
    },
  );

  return AxiosApi;
};

export { createAxiosApi };
