import { createRouter, createWebHistory } from 'vue-router';

// Main
import MainView from '@/views/MainView.vue';
import MainService from '@/components/pages/main/MainService';

// Community
import CommunityView from '@/views/CommunityView.vue';
import CommunityList from '@/components/pages/community/CommunityList';
import CommunityDetail from '@/components/pages/community/CommunityDetail';
import CommunityDetailChatting from '@/components/pages/community/CommunityDetailChatting';


// Profile
import ProfileView from '@/views/ProfileView.vue';
import ProfileList from '@/components/pages/profile/ProfileList';
import ProfileFriends from '@/components/pages/profile/ProfileFriends';

// photo
import PhotoBook from "@/views/PhotoBookView.vue";

const routes = [
  {
    path: '/',
    name: 'main',
    component: MainView,
    children: [
      {
        path: '',
        name: 'MainService', // default page
        component: MainService,
      },
    ],
  },
  {
    path: '/community',
    name: 'community',
    component: CommunityView,
    children: [
      {
        path: '',
        name: 'CommunityList', // default page
        component: CommunityList,
      },
      {
        path: ':postId',
        name: 'CommunityDetail', // default page
        component: CommunityDetail,
      },
      {
        path: ':postId/chat',
        name: 'CommunityDetailChatting', // default page
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
        path: '',
        name: 'ProfileList', // default page
        component: ProfileList,
      },
      {
        path: 'friends',
        name: 'ProfileFriends', // default page
        component: ProfileFriends,
      },
    ],
  },
  {
    path: '/photo',
    name: 'photo',
    component: CommunityView,
    children: [
      {
        path: '',
        name: 'PhotoBook', // default page
        component: PhotoBook,
      },
    ],
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
