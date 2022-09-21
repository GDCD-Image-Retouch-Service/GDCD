import store from '@/stores';
import router from '@/router';

async function socialLogin(req, isSignup) {
  try {
    const { data } = await store.dispatch('SOCIAL_LOGIN', req.id);
    store.commit('setUserToken', data.data.token);
    store.commit('setUser', data.data);

    if (isSignup === 'afterSignup')
      alert('회원가입 완료! 메인페이지로 이동합니다.'); //만약 회원가입 이후 도착한 화면이라면

    router.push('/');
  } catch (e) {
    console.log(e);

    const { data } = await store.dispatch('VALID_ID', req.id);
    if (data.status === 'OK') {
      const confirmYn = confirm(
        '아직 가입되지 않은 회원입니다. \n회원가입 화면으로 이동하시겠습니까?',
      );
      if (confirmYn) router.push('/auth/signup'); // 회원가입 화면으로 이동
    }
  }
}

async function socialSignup(req) {
  try {
    await store.dispatch('VALID_ID', req.id);
    await store.dispatch('SIGNUP', req);

    socialLogin(req, 'afterSignup');
  } catch (e) {
    console.log(e);

    const confirmYn = confirm(
      '이미 가입된 회원입니다. \n로그인 화면으로 이동하시겠습니까?',
    );
    if (confirmYn) router.push('/auth/login'); // 회원가입 화면으로 이동
  }
}

const createAuthApi = () => {};

export { createAuthApi, socialLogin, socialSignup };
