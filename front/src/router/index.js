import { createRouter, createWebHistory } from 'vue-router';

// Main
import MainView from '@/views/MainView.vue';

// Community
import CommunityView from '@/views/CommunityView.vue';
import CommunityList from '@/components/templates/community/CommunityList';

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
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
