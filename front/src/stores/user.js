import { defineStore } from 'pinia';
import axios from 'axios';
import user from '@/api/rest/user';
// import { useAccountStore } from './account';
// import { ref } from 'vue';

export const useUserStore = defineStore('userStore', {
  state: () => ({
    // 로그인한 유저 정보
    currentUserd: {
      item: {
        profile: require('@/assets/sdprofile.png'),
        nickname: '일이삼사오육',
      },
      msg: 'string',
    },
    // 프로필 페이지에서 보이는 게시글
    post: {
      item: {
        posts: [
          {
            postId: 0,
            title: 'String',
            image: 'String',
            rank: 0,
          },
        ],
        postCount: 1,
      },
      msg: 'string',
    },

    //
    // 프로필 헤더
    isItemActive: 0,

    // 프로필 친구
    isFriendActive: true,

    // 여기서부터 새로 api 적용되는 애들 위에는 아직 더미

    // 현재 로그인한 유저
    currentUser: {},

    // 토큰
    // token: ref(localStorage.getItem('token')),
    token:
      'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0amRlanIzMzdAZ21haWwuY29tIiwiaWF0IjoxNjY0NjQxMDA5LCJleHAiOjE2NjQ2NTkwMDl9.BEYSx7y1aUFcUItPHeB6fACrHvUqY5KIMs839nJ_zBg',
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
      let data = {
        userId: userId,
      };

      if (userId == 0) {
        data = {};
      }

      axios({
        url: user.myInfo(),
        method: 'GET',
        headers: {
          token: this.token,
        },
        params: data,
      })
        .then((res) => {
          this.profile = res.data;
          console.log(res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    // 스크랩 조회
    getMyScrap(userId) {
      let data = {
        userId: userId,
      };
      if (userId == 0) {
        data = {};
      }
      axios({
        url: user.myScrap(),
        method: 'GET',
        headers: {
          token: this.token,
        },
        params: data,
      })
        .then((res) => {
          this.scrapList = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },

    // 라이크 조회
    getMyLike(userId) {
      let data = {
        userId: userId,
      };
      if (userId == 0) {
        data = {};
      }
      axios({
        url: user.myLike(),
        method: 'GET',
        headers: {
          token: this.token,
        },
        params: data,
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
      let data = {};
      if (userId != 0) {
        data = {
          userId: userId,
        };
      }
      axios({
        url: user.myFollower(),
        method: 'GET',
        headers: {
          token: this.token,
        },
        params: data,
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
      let data = {};
      if (userId != 0) {
        data = {
          userId: userId,
        };
      }

      axios({
        url: user.myFollowing(),
        method: 'GET',
        headers: {
          token: this.token,
        },
        params: data,
      })
        .then((res) => {
          this.following = res.data;
          console.log(res.data, 'dddd');
        })
        .catch((err) => {
          console.log(err);
        });
    },

    // 회원정보수정
    updateMyInfo(nickname, profile) {
      const formdata = new FormData();

      formdata.append('nickname', nickname);
      if (profile.type) {
        console.log('?');
        formdata.append('profile', profile);
      }

      axios({
        url: user.myInfo(),
        method: 'PUT',
        headers: {
          token: this.token,
          'Content-Type': 'multipart/form-data',
        },
        data: formdata,
      })
        .then((res) => {
          console.log(res.data);
          this.getOtherinfo(0);
        })
        .catch((err) => {
          console.log(err);
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
