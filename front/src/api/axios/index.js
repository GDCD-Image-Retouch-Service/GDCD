import axios from 'axios';

const createAxiosApi = () => {
  // axios intercepter 설정에 따라 수정할 여지 있음
  return axios.create({
    baseURL: process.env.VUE_APP_REST_SERVER,
    headers: {
      'content-type': 'application/json; charset=UTF-8',
    },
  });
};

export { createAxiosApi };
