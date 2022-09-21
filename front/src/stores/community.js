import { defineStore } from 'pinia';

export const useCommunityStore = defineStore('communityStore', {
  state: () => ({
    // 전체 게시글 조회
    postsAll: {
      msg: 'SUCCESS',
      item: [
        {
          userId: 1,
          writerNickname: '애리',
          writerProfile: 'user1.jpg',
          likeCount: 0,
          title: '아이씐나!',
          representative: '1',
          rank: '1',
          updateTime: '2022-09-18T03:12:33.844',
        },
      ],
    },

    // 게시글 상세
    post: {
      msg: 'SUCCESS',
      item: {
        writerNickname: '애리',
        writerProfile: require('@/assets/sdprofile.png'),
        title: '진짜됨!',
        content:
          'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        tag: ['고양이', '사람'],
        updateTime: '2022-09-17T23:39:39.277',
        likeCount: 0,
        privacyBound: null,
        images: [
          {
            imageUrl: require('@/assets/sdprofile.png'),
            rank: 1,
          },
          {
            imageUrl: require('@/assets/sdprofile.png'),
            rank: 1,
          },
        ],
      },
    },
    isToggleButton: true,
  }),
  actions: {},
});
