import { createRouter, createWebHistory } from 'vue-router';

// Main
import MainView from '@/views/MainView.vue';

// Community
import CommunityView from '@/views/CommunityView.vue';
import CommunityList from '@/components/pages/community/CommunityList';

// Profile
import ProfileView from '@/views/ProfileView.vue';
import ProfileList from '@/components/pages/profile/ProfileList';
import ProfileFriends from '@/components/pages/profile/ProfileFriends';

const routes = [
  {
    path: '/',
    name: 'main',
    component: MainView,
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
      {
        path: 'friends',
        name: 'ProfileFriends', // default page
        component: ProfileFriends,
      },
    ],
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
