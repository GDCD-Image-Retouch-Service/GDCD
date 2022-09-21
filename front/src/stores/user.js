import { defineStore } from 'pinia';

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
    //
  }),
  actions: {},
});
