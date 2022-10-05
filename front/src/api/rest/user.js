import { createAxiosApi } from '@/api/axios';
import { useAccountStore } from '@/stores/account';
import { useUserStore } from '@/stores/user';

const HOST = process.env.VUE_APP_REST_SERVER;
const IMAGE = '/image';
const REST_PATH = '/user';
const axiosApi = createAxiosApi();

export default {
  // GET: 현재 유저 정보
  // DELETE: 회원 탈퇴
  // 파라미터 없이 주면 내정보/ 파라미터로 userId주면 해당 유저 정보 조회
  myInfo: () => HOST + REST_PATH,

  // GET: 회원 스크랩 리스트
  myScrap: () => HOST + REST_PATH + '/scrap-list',

  // GET: 회원 좋아요 리스트
  myLike: () => HOST + REST_PATH + '/like-list',

  // GET: 닉네임 중복체크
  nicknameOverlapCheck: () => HOST + REST_PATH + '/nickname',

  // GET: 유저별 사진 리스트 조회
  myPhoto: () => HOST + IMAGE + '/list',

  // GET: 팔로워 리스트
  myFollower: () => HOST + REST_PATH + '/follower',

  // GET: 팔로잉 리스트
  myFollowing: () => HOST + REST_PATH + '/following',

  // GET: 팔로우 버튼
  follow: () => HOST + REST_PATH + '/follow',

  // PUT: 회원 정보 수정
  updateProfile: () => HOST + REST_PATH + '/profile',

  // PUT: 닉네임 수정
  updateNickname: () => HOST + REST_PATH + '/nickname',

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
          const userStore = useUserStore();
          userStore.loginModal = false;
          userStore.headerSetDropdown = false;
          accountStore.setIsLogined(true);
          accountStore.setToken(res.data.item.token);
          userStore.setToken(res.data.item.token);
          resolve(res);
        })
        .catch((error) => {
          reject(error);
        });
    });
  },

  logout: () => {
    const accountStore = useAccountStore();
    const userStore = useUserStore();

    accountStore.setIsLogined(false);
    userStore.headerSetDropdown = false;
  },
};
