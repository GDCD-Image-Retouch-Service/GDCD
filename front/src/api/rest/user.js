import { createAxiosApi } from '@/api/axios';

const REST_PATH = '/user';
const axiosApi = createAxiosApi();

export default {
  testConnection: function (payload) {
    let params = {
      data: payload.data,
    };

    return new Promise((resolve, reject) => {
      axiosApi
        .post(REST_PATH + '/test', params)
        .then((response) => {
          resolve(response);
        })
        .catch((error) => {
          reject(error);
        });
    });
  },
};
