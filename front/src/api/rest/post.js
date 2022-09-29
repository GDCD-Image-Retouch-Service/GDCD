import { createAxiosApi } from '@/api/axios';

const HOST = process.env.VUE_APP_REST_SERVER;

const REST_PATH = '/post';
const axiosApi = createAxiosApi();

export default {
  // GET: 전체 글 리스트 조회
  postsAll: () => HOST + REST_PATH + '/list',

  // GET: 게시글 단일 조회
  // POST: 게시글 생성
  // PUT: 게시글 수정
  // DELETE: 게시글 삭제
  post: () => HOST + REST_PATH,

  // GET: 개인 게시글 리스트
  myPost: () => HOST + '/post' + '/search',

  // GET: 게시글 좋아요
  like: () => HOST + REST_PATH + '/like',

  // GET: 게시글 스크랩
  scrap: () => HOST + REST_PATH + '/scrap',

  // POST: 게시글 신고
  report: () => HOST + REST_PATH + '/report',

  // GET: 댓글 리스트 조회
  // POST: 댓글 생성
  // PUT: 댓글 수정
  // DELETE: 댓글 삭제
  comment: () => HOST + REST_PATH + '/comment',

  testConnection: function (payload) {
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
};
