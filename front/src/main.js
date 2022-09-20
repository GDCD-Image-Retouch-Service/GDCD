import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import vue3GoogleLogin from 'vue3-google-login';

// for bootstrap
import { BootstrapVue3, BToastPlugin } from 'bootstrap-vue-3';
// import CSS
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue-3/dist/bootstrap-vue-3.css';
import 'bootstrap-icons/font/bootstrap-icons.css';
// pinia
import { createPinia } from 'pinia';

createApp(App)
  .use(createPinia())
  .use(router)
  .use(BootstrapVue3)
  .use(BToastPlugin)
  .use(vue3GoogleLogin, {
    clientId: 'YOUR_GOOGLE_CLIENT_ID',
  })
  .mount('#app');
