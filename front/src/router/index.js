import { createRouter, createWebHistory } from 'vue-router';
import MainView from '../views/MainView.vue';
import CommunityView from '../views/CommunityView.vue';

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
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
