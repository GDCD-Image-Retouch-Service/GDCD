import { defineStore } from 'pinia';
import axios from 'axios';
import post from '@/api/rest/post';
import { useUserStore } from './user';

export const useCommunityStore = defineStore('communityStore', {
  state: () => ({
    // 전체 게시글 조회
    postsAll: {},

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

    // 전체 댓글 조회
    commentAll: {
      item: {
        comments: [
          {
            commentId: 0,
            content: 'String',
            writerNickname: 'String',
            writerProfile: require('@/assets/sdprofile.png'),
            updateDate: '2022-09-16T15:40:05.03495',
            kids: [
              {
                content: 'String',
                writerNickname: 'String',
                writerProfile: 'string',
                updateDate: '2022-09-16T15:40:05.03495',
              },
            ],
          },
          {
            commentId: 1,
            content: 'String',
            writerNickname: 'String',
            writerProfile: 'string',
            updateDate: '2022-09-16T15:40:05.03495',
            kids: [
              {
                content: 'String',
                writerNickname: 'String',
                writerProfile: 'string',
                updateDate: '2022-09-16T15:40:05.03495',
              },
            ],
          },
        ],
      },
      msg: 'string',
    },
    isToggleButton: true,
    isOpenComment: false,
    thisContent: '',
  }),
  actions: {
    // 전체 게시글 조회
    getPostsAll() {
      axios({
        url: post.postsAll(),
        method: 'GET',
        headers: {
          token: useUserStore().token,
        },
      })
        .then((res) => {
          console.log(res.data);
          this.postsAll = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },

    // 게시글 상세 조회
    getPost(postId) {
      axios({
        url: post.post(),
        method: 'GET',
        params: {
          postId: postId,
        },
      })
        .then((res) => {
          this.post = res.data;
          console.log(res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    // 게시글 생성
    createPost: (data) => {
      axios({
        url: post.post(),
        method: 'GET',
        headers: {
          token: useUserStore().token,
        },
        data: {
          data,
        },
      })
        .then((res) => {
          this.post = res.data;
          console.log(res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    // 게시글 수정
    updatePost: (data) => {
      axios({
        url: post.post(),
        method: 'PUT',
        headers: {
          token: useUserStore().token,
        },
        data: {
          data,
        },
      })
        .then((res) => {
          this.post = res.data;
          console.log(res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    // 게시글 삭제
    deletPost: (postId) => {
      axios({
        url: post.post(),
        method: 'DELETE',
        headers: {
          token: useUserStore().token,
        },
        params: {
          postId: postId,
        },
      })
        .then((res) => {
          console.log(res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    // 게시글 좋아요
    likePost(postId) {
      axios({
        url: post.like(),
        method: 'GET',
        headers: {
          token: useUserStore().token,
        },
        params: {
          postId: postId,
        },
      })
        .then((res) => {
          console.log(res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    //게시글 스크랩
    scrapPost(postId) {
      axios({
        url: post.scrap(),
        method: 'GET',
        headers: {
          token: useUserStore().token,
        },
        params: {
          postId: postId,
        },
      })
        .then((res) => {
          console.log(res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    // 댓글 조회
    getComment(postId) {
      axios({
        url: post.comment(),
        method: 'GET',
        headers: {
          token: useUserStore().token,
        },
        params: {
          postId: postId,
        },
      })
        .then((res) => {
          this.commentAll = res.data;
          console.log(res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    // 댓글 생성
    createComment(data) {
      axios({
        url: post.comment(),
        method: 'POST',
        headers: {
          token: useUserStore().token,
        },
        data: {
          data: data,
        },
      })
        .then((res) => {
          this.commentAll = res.data;
          console.log(res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
});
