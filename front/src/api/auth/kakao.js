import { socialLogin, socialSignup } from '@/api/auth';
// import { loadScript } from '@/api/loadScript';

// loadScript('recaptcha', 'https://www.google.com/recaptcha/api.js').then(
//   (value) => console.log(value),
// );

const kakao = {
  init() {
    window.Kakao.init(process.env.VUE_APP_KAKAO_APP_KEY);
    return true;
  },

  getInfo(authObj, division) {
    window.Kakao.API.request({
      url: '/v2/user/me',
      success: async (res) => {
        console.log(res);
        const kakao_account = res.kakao_account;
        const req = {
          id: res.id,
          name: kakao_account.profile.nickname,
          email: kakao_account.email,
          picture: kakao_account.profile.profile_image_url,
          social: 'Kakao',
        };
        if (division === 'login') {
          socialLogin(req);
        } else {
          socialSignup(req);
        }
      },
      fail: (error) => {
        console.log(error);
      },
    });
  },
};

export default kakao;
