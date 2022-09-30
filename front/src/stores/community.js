import { defineStore } from 'pinia';
import axios from 'axios';
import post from '@/api/rest/post';
import { useUserStore } from './user';

export const useCommunityStore = defineStore('communityStore', {
  state: () => ({
    // 전체 게시글 조회
    postsAll: {},

    // 게시글 상세
    post: {},

    // 게시물 리스트
    oddPostList: [],
    evenPostList: [],

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
        headers: {
          token: useUserStore().token,
        },
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

    // 내 게시글 조회
    getMyPostsAll() {
      axios({
        url: post.myPost(),
        method: 'GET',
        headers: {
          token: useUserStore().token,
        },
      })
        .then((res) => {
          console.log(res.data);
          this.oddPostList = [];
          this.evenPostList = [];
          res.data.item.forEach((e, index) => {
            console.log(e);
            if (index % 2 === 0) {
              this.oddPostList.push(e);
            } else {
              this.evenPostList.push(e);
            }
          });
        })
        .catch((err) => {
          console.log(err);
        });
    },

    // 게시글 생성
    createPost: (data) => {
      console.log(data);
      axios({
        url: post.post(),
        method: 'POST',
        headers: {
          token: useUserStore().token,
        },
        data: {
          title: data.title,
          content: data.content,
          privacyBound: data.privacyBound,
          images: data.images,
          representative: data.representative,
        },
      })
        .then((res) => {
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
    deletePost: (postId) => {
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
