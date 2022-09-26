import { defineStore } from 'pinia';
import axios from 'axios';
import user from '@/api/rest/user';

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
    // 프로필 페이지에서 보이는 스크랩
    scrap: {
      item: {
        posts: [
          {
            postId: 0,
            title: 'String',
            image: require('@/assets/sdprofile.png'),
            rank: 0,
          },
          {
            postId: 1,
            title: 'String',
            image: require('@/assets/logo.png'),
            rank: 0,
          },
          {
            postId: 2,
            title: 'String',
            image: require('@/assets/sdprofile.png'),
            rank: 0,
          },
        ],
        scrapCount: 1,
      },
      msg: 'string',
    },
    // 프로필 페이지에서 보이는 좋아요
    like: {
      item: {
        posts: [
          {
            postId: 0,
            title: 'String',
            image: 'String',
            rank: 0,
          },
        ],
        likeCount: 1,
      },
      msg: 'string',
    },
    // 팔로우 리스트
    follow: {
      item: {
        followers: [
          {
            userId: 0,
            image: require('@/assets/sdprofile.png'),
            nickname: 'String',
          },
        ],
        followerCount: 0,
      },
      msg: 'string',
    },
    // 팔로잉 리스트
    following: {
      item: {
        followings: [
          {
            userId: 0,
            image: require('@/assets/sdprofile.png'),
            nickname: 'String',
          },
        ],
        followingCount: 0,
      },
      msg: 'string',
    },
    //
    // 프로필 헤더
    isItemActive: 0,

    // 프로필 친구
    isFriendActive: true,

    // 여기서부터 새로 api 적용되는 애들 위에는 아직 더미
    // 토큰
    token:
      'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0amRlanIzMzdAZ21haWwuY29tIiwiaWF0IjoxNjY0MTU3ODgwLCJleHAiOjE2NjQxNzU4ODB9.TNo3hOgibv5YAttsbO3Zd9Z__dlUdp7uxsmhgx65g3M',
    // 로그인한 유저 정보
    currentUserInfo: {},

    // 스크랩 리스트
    scrapList: {
      item: {
        posts: [
          {
            postId: 0,
            title: 'String',
            image: require('@/assets/sdprofile.png'),
            rank: 1,
            writerNickname: 'String',
            writerProfile: require('@/assets/sdprofile.png'),
            likeCount: 100,
          },
          {
            postId: 1,
            title: 'String',
            image: require('@/assets/sdprofile.png'),
            rank: 1,
            writerNickname: 'String',
            writerProfile: require('@/assets/sdprofile.png'),
            likeCount: 100,
          },
          {
            postId: 2,
            title: 'String',
            image: require('@/assets/sdprofile.png'),
            rank: 1,
            writerNickname: 'String',
            writerProfile: require('@/assets/sdprofile.png'),
            likeCount: 100,
          },
        ],
      },
      msg: 'string',
    },

    // 닉네임 중복 체크
    nicknameOverlap: false,

    // 유저별 사진 리스트
    myPhoto: {},
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
          this.currentUserInfo = res.data;
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
          this.currentUserInfo = res.data;
          console.log(res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    // 스크랩 조회
    getMyScrap() {
      axios({
        url: user.myScrap(),
        method: 'GET',
        headers: {
          token: this.token,
        },
      })
        .then((res) => {
          this.scrapList = res.data;
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

    // 회원정보수정
    updateMyInfo(nickname) {
      axios({
        url: user.myInfo(),
        method: 'PUT',
        headers: {
          token: this.token,
        },
        data: {
          nickname: nickname,
        },
      })
        .then((res) => {
          console.log(res.data);
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
          // token: this.token,
          token:
            'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtaW5jaG9UcmFzaDRAZ21haWwuY29tIiwiaWF0IjoxNjY0MTEyNzczLCJleHAiOjE2NjQxMzA3NzN9.X621Uk3vLtljdOvvefrjJWtR7MeMZjDJ_q9b6JPnlsw',
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
