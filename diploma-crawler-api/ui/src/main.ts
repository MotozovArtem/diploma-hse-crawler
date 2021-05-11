import '@mdi/font/css/materialdesignicons.min.css';

import Vue from 'vue';
import App from '@/App.vue';
import vuetify from '@/plugins/vuetify';
import router from '@/router';
import api from '@/plugins/api';
import searcher_scrapper_api from '@/plugins/searcher_scrapper_api'
import Component from 'vue-class-component';

// без этого не работают хуки роутера внутри компонентов
// решение взято https://github.com/vuejs/vue-router/issues/1066#issuecomment-632719343
Component.registerHooks([
  'beforeRouteEnter',
  'beforeRouteLeave',
  'beforeRouteUpdate'
]);

Vue.use(api);
Vue.use(searcher_scrapper_api)
Vue.config.productionTip = process.env.NODE_ENV === 'production';

new Vue({
  vuetify,
  router,
  render: h => h(App)
}).$mount('#app');
