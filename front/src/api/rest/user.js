import { createAxiosApi } from '@/api/axios';
import { useAccountStore } from '@/stores/account';

const HOST = process.env.VUE_APP_REST_SERVER;
const IMAGE = '/image';
const REST_PATH = '/user';
const axiosApi = createAxiosApi();

export default {
  // GET: 현재 유저 정보
  // PUT: 회원 정보 수정
  // DELETE: 회원 탈퇴
  // 파라미터 없이 주면 내정보/ 파라미터로 userId주면 해당 유저 정보 조회
  myInfo: () => HOST + REST_PATH,

  // GET: 회원 스크랩 리스트
  myScrap: () => HOST + REST_PATH + '/scrap-list',

  // GET: 닉네임 중복체크
  nicknameOverlapCheck: () => HOST + REST_PATH + '/nickname',

  // GET: 유저별 사진 리스트 조회
  myPhoto: () => HOST + IMAGE + '/list',

  testConnection: (payload) => {
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

  login: (payload) => {
    return new Promise((resolve, reject) => {
      axiosApi
        .post(REST_PATH + '/login', payload)
        .then((res) => {
          const accountStore = useAccountStore();
          accountStore.setIsLogined(true);
          accountStore.setToken(res.data.item.token);

          console.log('로그인 성공');
          resolve(res);
        })
        .catch((error) => {
          console.log('로그인 실패');
          reject(error);
        });
    });
  },
};
