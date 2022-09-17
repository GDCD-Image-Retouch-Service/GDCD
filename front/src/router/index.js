import { createRouter, createWebHistory } from 'vue-router';

// Main
import MainView from '@/views/MainView.vue';
import MainUpload from '@/components/pages/main/MainUpload';

// Community
import CommunityView from '@/views/CommunityView.vue';
import CommunityList from '@/components/pages/community/CommunityList';

import CommunityWrite from '@/components/pages/community/CommunityWrite';
import CommunityDetail from '@/components/pages/community/CommunityDetail';
import CommunityDetailChatting from '@/components/pages/community/CommunityDetailChatting';

// Profile
import ProfileView from '@/views/ProfileView.vue';
import ProfileList from '@/components/pages/profile/ProfileList';
import ProfileFriends from '@/components/pages/profile/ProfileFriends';

// photo
import PhotoView from '@/views/PhotoView.vue';
import PhotoList from '@/components/pages/photo/PhotoList.vue';

const routes = [
  {
    path: '/', // Landing page
    name: 'main',
    component: MainView,
    children: [
      {
        path: '', // default page
        name: 'MainUpload',
        component: MainUpload,
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
        name: 'ProfileList',
        component: ProfileList,
      },
      {
        path: 'friends',
        name: 'ProfileFriends',
        component: ProfileFriends,
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
