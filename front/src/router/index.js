import { createRouter, createWebHistory } from 'vue-router';
import { useAccountStore } from '@/stores';
import swal from 'sweetalert2';

// true : 로그인을 해야 이동 가능
// false : 로그인을 하면 이동 불가능
const beforeAuth = (needAuth) => async (from, to, next) => {
  const accountStore = useAccountStore();
  if (needAuth && !accountStore.getIsLogined) {
    // 로그인 필요한 서비스
    await swal.fire({
      icon: 'warning',
      title: '로그인이 필요한 서비스 입니다',
      timer: 3000,
    });

    // 로그인 필요하지 않은 서비스
  } else {
    next();
  }
};

// Landing
// import LandingView from '@/views/LandingView.vue';

// Error Handling
// import LoadingView from '@/views/LoadingView.vue';

// Landing
import LandingView from '@/views/LandingView.vue';

// Main
import MainView from '@/views/MainView.vue';
import MainUpload from '@/components/pages/main/MainUpload';
import MainTest from '@/components/pages/main/MainTest';
import MainScore from '@/components/pages/main/MainScore';
import MainOptimize from '@/components/pages/main/MainOptimize';
import MainInpaint from '@/components/pages/main/MainInpaint';

// Community
import CommunityView from '@/views/CommunityView.vue';
import CommunityList from '@/components/pages/community/CommunityList';
import CommunityWrite from '@/components/pages/community/CommunityWrite';
import CommunityDetail from '@/components/pages/community/CommunityDetail';

import CommunityUpdateView from '@/views/CommunityUpdateView.vue';
import CommunityUpdate from '@/components/pages/community/CommunityUpdate';

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
import PhotoDetail from '@/components/pages/photo/PhotoDetail';

const routes = [
  {
    path: '/', // Landing page
    name: 'Landing',
    beforeEnter: beforeAuth(false),
    component: LandingView,
  },
  {
    path: '/main', // Landing page
    name: 'main',
    component: MainView,
    children: [
      {
        path: '', // default page
        name: 'MainUpload',
        component: MainUpload,
      },
      {
        path: 'test', // 이미지 상호작용 테스트 코드, 지워야함
        name: 'MainTest',
        component: MainTest,
      },
      {
        path: 'score',
        name: 'MainScore',
        component: MainScore,
      },
      {
        path: 'optimize',
        name: 'MainOptimize',
        component: MainOptimize,
      },
      {
        path: 'inpaint',
        name: 'MainInpaint',
        component: MainInpaint,
      },
    ],
  },
  {
    path: '/community',
    name: 'community',
    beforeEnter: beforeAuth(true),
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
    path: '/community/:postId/update',
    name: 'CommunityUpdate',
    beforeEnter: beforeAuth(true),
    component: CommunityUpdateView,
    children: [
      {
        path: '', // default page
        name: 'CommunityUpdateList',
        component: CommunityUpdate,
      },
    ],
  },
  {
    path: '/profile/:userId',
    name: 'profile',
    beforeEnter: beforeAuth(true),
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
    beforeEnter: beforeAuth(true),
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
    beforeEnter: beforeAuth(true),
    component: PhotoView,
    children: [
      {
        path: '', // default page
        name: 'PhotoList',
        component: PhotoList,
      },
      {
        path: 'detail',
        name: 'PhotoDetail',
        component: PhotoDetail,
      },
    ],
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
