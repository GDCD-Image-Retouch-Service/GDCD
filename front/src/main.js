import { createApp, prototype } from 'vue';
import App from './App.vue';
import router from './router';

// for bootstrap
import { BootstrapVue3, BToastPlugin } from 'bootstrap-vue-3';
// import CSS
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue-3/dist/bootstrap-vue-3.css';
import 'bootstrap-icons/font/bootstrap-icons.css';
// pinia
import { createPinia } from 'pinia';

// Vue.prototype.$scrollToTop = () => {
//   window.scrollTo(0, 0);
// };

createApp(App)
  .use(createPinia())
  .use(router)
  .use(BootstrapVue3)
  .use(BToastPlugin)
  .mount('#app');

prototype.$scrollToTop = () => {
  window.scrollTo(0, 0);
};
// app.provide('$scrollToTop', () => {
//   console.log('클릭');
// });
