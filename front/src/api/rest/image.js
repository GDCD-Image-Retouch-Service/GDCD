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
    const form = new FormData();
    form.append('image', image);
    return new Promise((resolve, reject) => {
      axiosApi
        .post(REST_PATH + '/initial', form, {
          headers: {
            'Content-Type': 'multipart/form-data',
          },
        })
        .then((response) => {
          resolve(response.data);
          // 응답 처리
        })
        .catch((error) => {
          reject(error);
          // 예외 처리
        });
    });
  },
};
