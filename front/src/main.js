import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import vue3GoogleLogin from 'vue3-google-login';
import Popper from 'vue3-popper';

// for bootstrap
import { BootstrapVue3, BToastPlugin } from 'bootstrap-vue-3';
// import CSS
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue-3/dist/bootstrap-vue-3.css';
import 'bootstrap-icons/font/bootstrap-icons.css';
// pinia
import { createPinia } from 'pinia';

import vuetify from './plugins/vuetify';
import { loadFonts } from './plugins/webfontloader';

loadFonts();

createApp(App)
  .use(createPinia())
  .use(router)
  .use(BootstrapVue3)
  .use(BToastPlugin)
  .use(vuetify)
  .use(vue3GoogleLogin, {
    clientId: '736357393555-9a6a7qk74dngs5ovcmvfa6pnhdl0m8th',
  })
  .component('Popper', Popper)
  .mount('#app');
