import { createRouter, createWebHistory } from 'vue-router';
import store from '../stores/user';
import swal from 'sweetalert2';

// true : 로그인을 해야 이동 가능
// false : 로그인을 하면 이동 불가능
const beforeAuth = (needAuth) => async (from, to, next) => {
  // 로그인 기능 구현시 이 코드를 변경할 것
  const isLogined = false;
  if (needAuth && !isLogined) {
    if (from.path.includes('studyroom')) {
      // save last room info for using after login
      store.commit('SET_NEXT_ROOM', from.path);
    }

    // 로그인 필요한 서비스
    await swal.fire({
      icon: 'warning',
      title: '로그인이 필요한 서비스 입니다',
      timer: 3000,
    });
    next('/account/login');
    // 로그인 필요하지 않은 서비스
  } else if (!needAuth && isLogined) {
    next('/main');
  } else {
    next();
  }
};

// Main
import MainView from '@/views/MainView.vue';
import MainUpload from '@/components/pages/main/MainUpload';
import MainLogin from '@/components/pages/main/MainLogin';
import MainTest from '@/components/pages/main/MainTest';

// Community
import CommunityView from '@/views/CommunityView.vue';
import CommunityList from '@/components/pages/community/CommunityList';

import CommunityWrite from '@/components/pages/community/CommunityWrite';
import CommunityDetail from '@/components/pages/community/CommunityDetail';
import CommunityDetailChatting from '@/components/pages/community/CommunityDetailChatting';

// Profile
import ProfileView from '@/views/ProfileView.vue';
import ProfilePost from '@/components/pages/profile/ProfilePost';
import ProfileScrap from '@/components/pages/profile/ProfileScrap';
import ProfileLike from '@/components/pages/profile/ProfileLike';
import ProfileFriend from '@/components/pages/profile/ProfileFriend';

// photo
import PhotoView from '@/views/PhotoView.vue';
import PhotoList from '@/components/pages/photo/PhotoList.vue';

const routes = [
  {
    path: '/', // Landing page
    name: 'main',
    redirect: '/main',
    beforeEnter: beforeAuth(false),
    component: MainView,
    children: [
      {
        path: 'main', // default page
        name: 'MainUpload',
        component: MainUpload,
      },
      {
        path: 'main/login',
        name: 'MainLogin',
        component: MainLogin,
      },
      {
        path: 'main/test',
        name: 'MainTest',
        component: MainTest,
      },
    ],
  },
  {
    path: '/community',
    name: 'community',
    component: CommunityView,
    children: [
      {
        path: '', // default page
        name: 'CommunityList',
        component: CommunityList,
      },
      {
        path: 'write',
        name: 'CommunityWrite',
        component: CommunityWrite,
      },
      {
        path: ':postId',
        name: 'CommunityDetail',

        component: CommunityDetail,
      },
      {
        path: ':postId/chat',
        name: 'CommunityDetailChatting',
        component: CommunityDetailChatting,
      },
    ],
  },
  {
    path: '/profile',
    name: 'profile',
    component: ProfileView,
    children: [
      {
        path: '', // default page
        name: 'ProfilePost',
        component: ProfilePost,
      },
      {
        path: 'scrap',
        name: 'ProfileScrap',
        component: ProfileScrap,
      },
      {
        path: 'like',
        name: 'ProfileLike',
        component: ProfileLike,
      },
      {
        path: 'friend',
        name: 'ProfileFriend',
        component: ProfileFriend,
      },
    ],
  },
  {
    path: '/photo',
    name: 'photo',
    component: PhotoView,
    children: [
      {
        path: '', // default page
        name: 'PhotoList',
        component: PhotoList,
      },
    ],
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
