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

    let param = {
      aesthetic: payload.aesthetic,
      quality: payload.quality,
    };

    const form = new FormData();
    form.append('image', payload.image);
    form.append('requestDto', param);

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
};
