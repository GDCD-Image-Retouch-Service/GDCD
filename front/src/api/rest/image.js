import { createAxiosApi } from '@/api/axios';

const REST_PATH = '/image';
const axiosApi = createAxiosApi();

export default {
  scoring: function (image) {
    const form = new FormData();
    form.append('image', image);
    return new Promise((resolve, reject) => {
      axiosApi
        .post(REST_PATH + '/scoring', form, {
          headers: {
            'Content-Type': 'multipart/form-data',
          },
        })
        .then((response) => {
          resolve(response.data);
        })
        .catch((error) => {
          reject(error);
        });
    });
  },
  save: function (payload) {
    console.log(payload.image);
    console.log(payload.aesthetic);
    console.log(payload.quality);

    const form = new FormData();
    form.append('image', payload.image);
    form.append('aesthetic', payload.aesthetic);
    form.append('quality', payload.quality);

    return new Promise((resolve, reject) => {
      axiosApi
        .post(REST_PATH, form, {
          headers: {
            'Content-Type': 'multipart/form-data',
          },
        })
        .then((response) => {
          resolve(response.data);
        })
        .catch((error) => {
          reject(error);
        });
    });
  },

  objectDetection: function (payload) {
    return new Promise((resolve, reject) => {
      axiosApi
        .get(REST_PATH + '/object?imageId=' + payload)
        .then((response) => {
          resolve(response.data);
        })
        .catch((error) => {
          reject(error);
        });
    });
  },

  // 사진 최적화 요청
  optimization: function (payload) {
    return new Promise((resolve, reject) => {
      axiosApi
        .get(REST_PATH + '/optimization?imageId=' + payload)
        .then((response) => {
          resolve(response.data);
        })
        .catch((error) => {
          reject(error);
        });
    });
  },

  // 사진 최적화 요청 재확인 Polling
  optimizingProcess: function (payload) {
    return new Promise((resolve, reject) => {
      axiosApi
        .get(REST_PATH + '/process?requestId=' + payload)
        .then((response) => {
          resolve(response.data);
        })
        .catch((error) => {
          reject(error);
        });
    });
  },

  // 최적화 사진 저장
  optimizingSave: function () {
    // return new Promise((resolve, reject) => {
    //   axiosApi
    //     .get(REST_PATH + '/optimization?imageId=' + payload)
    //     .then((response) => {
    //       resolve(response.data);
    //     })
    //     .catch((error) => {
    //       reject(error);
    //     });
    // });
  },
};
