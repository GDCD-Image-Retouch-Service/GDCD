import { createRouter, createWebHistory } from 'vue-router';
import store from '@/stores/account';
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

// Landing
// import LandingView from '@/views/LandingView.vue';

// Error Handling
import LoadingView from '@/views/LoadingView.vue';

// Home
import HomeView from '@/views/HomeView.vue';

// Main
import MainView from '@/views/MainView.vue';
import MainUpload from '@/components/pages/main/MainUpload';
import MainTest from '@/components/pages/main/MainTest';
import MainScore from '@/components/pages/main/MainScore';
import MainOptimize from '@/components/pages/main/MainOptimize';

// Community
import CommunityView from '@/views/CommunityView.vue';
import CommunityList from '@/components/pages/community/CommunityList';

import CommunityWrite from '@/components/pages/community/CommunityWrite';
import CommunityDetail from '@/components/pages/community/CommunityDetail';

// Profile
import ProfileView from '@/views/ProfileView';
import ProfilePost from '@/components/pages/profile/ProfilePost';
import ProfileScrap from '@/components/pages/profile/ProfileScrap';
import ProfileLike from '@/components/pages/profile/ProfileLike';
import ProfileFriend from '@/components/pages/profile/ProfileFriend';

// ProfileUpdate
import ProfileUpdateView from '@/views/ProfileUpdateView';
import ProfileUpdate from '@/components/pages/profile/ProfileUpdate';

// Photo
import PhotoView from '@/views/PhotoView.vue';
import PhotoList from '@/components/pages/photo/PhotoList';

const routes = [
  {
    path: '/', // Landing page
    name: 'Landing',
    beforeEnter: beforeAuth(false),
    component: LoadingView,
  },
  {
    path: '/home',
    name: 'home',
    component: HomeView,
  },
  {
    path: '/main', // Landing page
    name: 'main',
    component: MainView,
    children: [
      {
        path: 'main', // default page
        name: 'MainUpload',
        component: MainUpload,
      },
      {
        path: 'main/test', // 이미지 상호작용 테스트 코드, 지워야함
        name: 'MainTest',
        component: MainTest,
      },
      {
        path: 'main/score',
        name: 'MainScore',
        component: MainScore,
      },
      {
        path: 'main/optimize',
        name: 'MainOptimize',
        component: MainOptimize,
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
    ],
  },
  {
    path: '/profile/:userId',
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
    path: '/profile/:userId',
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
    path: '/profile/update/',
    name: 'profileU',
    component: ProfileUpdateView,
    children: [
      {
        path: '', // default page
        name: 'ProfileUpdate',
        component: ProfileUpdate,
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
