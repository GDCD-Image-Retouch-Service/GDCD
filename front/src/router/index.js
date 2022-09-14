import { createRouter, createWebHistory } from 'vue-router';

// Main
import MainView from '@/views/MainView.vue';
import MainService from '@/components/pages/main/MainService';

// Community
import CommunityView from '@/views/CommunityView.vue';
import CommunityList from '@/components/pages/community/CommunityList';

// Profile
import ProfileView from '@/views/ProfileView.vue';
import ProfileList from '@/components/pages/profile/ProfileList';

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
    ],
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
