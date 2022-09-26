import { createAxiosApi } from '@/api/axios';

const REST_PATH = '/image';
const axiosApi = createAxiosApi();

export default {
  testConnection: function (imageId) {
    return new Promise((resolve, reject) => {
      axiosApi
        .get(REST_PATH + '/info?imageId=' + imageId)
        .then((response) => {
          resolve(response.data);
        })
        .catch((error) => {
          reject(error);
        });
    });
  },
  scoringInitial: function (image) {
    return new Promise((resolve, reject) => {
      axiosApi
        .post(REST_PATH + '/initial', { image: image })
        .then((response) => {
          resolve(response.data);
        })
        .catch((error) => {
          reject(error);
        });
    });
  },

  // /initial
};
