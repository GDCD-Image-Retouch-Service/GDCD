import { defineStore } from 'pinia';
import axios from 'axios';
import user from '@/api/rest/user';
// import { useAccountStore } from './account';
import { ref } from 'vue';

export const useUserStore = defineStore('userStore', {
  state: () => ({
    // 로그인한 유저 정보
    currentUserd: {},
    // 프로필 페이지에서 보이는 게시글
    post: {},

    // 프로필 헤더
    isItemActive: 0,

    // 프로필 친구
    isFriendActive: true,

    // 여기서부터 새로 api 적용되는 애들 위에는 아직 더미

    // 현재 로그인한 유저
    currentUser: {},

    // 토큰
    token: ref(localStorage.getItem('token')),

    // 로그인한 유저 정보
    profile: {},

    // 스크랩 리스트
    scrapList: {},

    // 좋아요 리스트
    likeList: {},

    // 팔로워 리스트
    follower: {},

    // 팔로잉 리스트
    following: {},

    // 닉네임 중복 체크
    nicknameOverlap: false,

    //
    updateProfile: '',
    updateNickname: '',

    // 유저별 사진 리스트
    myPhoto: {},

    daePyoImage: '',
    photoSelect: [],
    selectedPhoto: [],
    selectedPhotoList: [],
    urlList: [],
    urlPhotoList: [],

    selectTag: [],
  }),
  actions: {
    resetVariable() {
      // 프로필 페이지에서 보이는 게시글
      (this.post = {}),
        // 프로필 친구
        (this.isFriendActive = true),
        // 로그인한 유저 정보
        (this.profile = {}),
        // 스크랩 리스트
        (this.scrapList = {}),
        // 좋아요 리스트
        (this.likeList = {}),
        // 팔로워 리스트
        (this.follower = {}),
        // 팔로잉 리스트
        (this.following = {}),
        // (this.updateProfile = ''),
        // (this.updateNickname = ''),
        // 유저별 사진 리스트
        (this.myPhoto = {}),
        (this.daePyoImage = ''),
        (this.photoSelec = []),
        (this.selectedPhoto = []),
        (this.selectedPhotoList = []),
        (this.urlList = []),
        (this.urlPhotoList = []),
        (this.selectTag = []);
    },

    setToken(token) {
      this.token = token;
      this.getMyinfo();
    },

    // 내정보 조회
    getMyinfo() {
      axios({
        url: user.myInfo(),
        method: 'GET',
        headers: {
          token: this.token,
        },
      })
        .then((res) => {
          console.dir(res);
          this.currentUser = res.data;
          this.updateProfile = res.data.item.user.profile;
          this.updateNickname = res.data.item.user.nickname;
          console.log(res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    // 다른 사람 정보 조회
    getOtherinfo(userId) {
      axios({
        url: user.myInfo(),
        method: 'GET',
        headers: {
          token: this.token,
        },
        params: {
          userId: userId,
        },
      })
        .then((res) => {
          this.profile = [];
          this.profile = res.data;
          console.log(res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    // 스크랩 조회
    getMyScrap(userId) {
      axios({
        url: user.myScrap(),
        method: 'GET',
        headers: {
          token: this.token,
        },
        params: {
          userId: userId,
        },
      })
        .then((res) => {
          console.log(res.data);
          this.scrapList = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },

    // 라이크 조회
    getMyLike(userId) {
      axios({
        url: user.myLike(),
        method: 'GET',
        headers: {
          token: this.token,
        },
        params: {
          userId: userId,
        },
      })
        .then((res) => {
          this.likeList = res.data;
          console.log(res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    // 내 사진 목록 조회
    getMyPhoto() {
      axios({
        url: user.myPhoto(),
        method: 'GET',
        headers: {
          token: this.token,
        },
      })
        .then((res) => {
          this.myPhoto = res.data;
          console.log(res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    // 내 팔로워 조회
    getMyFollower(userId) {
      axios({
        url: user.myFollower(),
        method: 'GET',
        headers: {
          token: this.token,
        },
        params: {
          userId: userId,
        },
      })
        .then((res) => {
          this.follower = res.data;
          console.log(res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    // 내가 팔로잉 하는 사람 조회
    getMyFollowing(userId) {
      axios({
        url: user.myFollowing(),
        method: 'GET',
        headers: {
          token: this.token,
        },
        params: {
          userId: userId,
        },
      })
        .then((res) => {
          this.following = res.data;
          console.log(res.data, 'dddd');
        })
        .catch((err) => {
          console.log(err);
        });
    },

    // 회원 이미지 수정
    updateUserProfile(profile) {
      return new Promise((resolve, reject) => {
        const formdata = new FormData();

        if (profile.type) {
          console.log('?');
          formdata.append('profile', profile);
        }

        axios({
          url: user.updateProfile(),
          method: 'PUT',
          headers: {
            token: this.token,
            'Content-Type': 'multipart/form-data',
          },
          data: formdata,
        })
          .then((res) => {
            this.getMyinfo();
            console.log(res.data);
            resolve(res);
          })
          .catch((err) => {
            console.log(err);
            reject(err);
          });
      });
    },

    // 회원 닉네임 수정
    updateUserNickname(nickname) {
      return new Promise((resolve, reject) => {
        axios({
          url: user.updateNickname(),
          method: 'PUT',
          headers: {
            token: this.token,
          },
          params: {
            nickname: nickname,
          },
        })
          .then((res) => {
            this.getMyinfo();
            console.log(res.data);
            resolve(res);
          })
          .catch((err) => {
            console.log(err);
            reject(err);
          });
      });
    },

    // 회원 탈퇴
    deleteMyInfo() {
      axios({
        url: user.myInfo(),
        method: 'DELETE',
        headers: {
          token: this.token,
        },
      })
        .then((res) => {
          console.log(res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    // 팔로우
    follow(userId) {
      axios({
        url: user.follow(),
        method: 'GET',
        headers: {
          token: this.token,
        },
        params: {
          userId: userId,
        },
      })
        .then((res) => {
          console.log(res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    // 닉네임 중복 체크
    nicknameOverlapCheck(nickname) {
      axios({
        url: user.nicknameOverlapCheck(),
        method: 'GET',
        params: {
          nickname: nickname,
        },
      })
        .then((res) => {
          console.log(res.data.item.usable);
          this.nicknameOverlap = res.data.item.usable;
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
});
