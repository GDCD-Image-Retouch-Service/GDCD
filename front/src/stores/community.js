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
    postList: [],

    // 전체 댓글 조회
    commentAll: {},
    isToggleButton: true,
    isOpenComment: false,
    thisContent: '',

    // 이미지 선택
    selectImage: '',

    updateCommentContent: '',
    targetImage: '',

    writeFirstModal: false,
  }),
  actions: {
    resetVariable() {
      // 전체 게시글 조회
      (this.postsAll = {}),
        // 게시글 상세
        (this.post = {}),
        // 게시물 리스트
        (this.postList = []),
        // 전체 댓글 조회
        (this.commentAll = {}),
        (this.isToggleButton = true),
        (this.isOpenComment = false),
        (this.thisContent = ''),
        // 이미지 선택
        (this.selectImage = ''),
        (this.updateCommentContent = ''),
        (this.targetImage = '');
    },

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
    getMyPostsAll(userId) {
      let data = {
        userId: userId,
      };
      if (userId == 0) {
        data = {};
      }
      axios({
        url: post.myPost(),
        method: 'GET',
        headers: {
          token: useUserStore().token,
        },
        params: data,
      })
        .then((res) => {
          this.postList = [];
          this.postList = res.data.item;
          console.log(res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    // 게시글 생성
    createPost: (data) => {
      return new Promise((resolve, reject) => {
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
            resolve(res);
          })
          .catch((err) => {
            reject(err);
          });
      });
    },

    // 게시글 수정
    updatePost: (data) => {
      return new Promise((resolve, reject) => {
        axios({
          url: post.post(),
          method: 'PUT',
          headers: {
            token: useUserStore().token,
          },
          data: {
            postId: data.postId,
            title: data.title,
            content: data.content,
            privacyBound: data.privacyBound,
            images: data.images,
            representative: data.representative,
          },
        })
          .then((res) => {
            resolve(res);
          })
          .catch((err) => {
            reject(err);
          });
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
          postId: data.postId,
          content: data.content,
        },
      })
        .then((res) => {
          this.getComment(data.postId);

          console.log(res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    // 댓글 수정
    updateComment(data) {
      axios({
        url: post.comment(),
        method: 'PUT',
        headers: {
          token: useUserStore().token,
        },
        data: {
          commentId: data.commentId,
          content: data.content,
        },
      })
        .then((res) => {
          this.getComment(data.postId);
          console.log(res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    // 댓글 삭제
    deleteComment(postId, commentId) {
      axios({
        url: post.comment(),
        method: 'DELETE',
        headers: {
          token: useUserStore().token,
        },
        params: {
          commentId: commentId,
        },
      })
        .then((res) => {
          this.getComment(postId);
          console.log(res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
});
