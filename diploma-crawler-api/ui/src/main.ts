import '@mdi/font/css/materialdesignicons.min.css';

import Vue from 'vue';
import App from '@/App.vue';
import Component from 'vue-class-component';

// без этого не работают хуки роутера внутри компонентов
// решение взято https://github.com/vuejs/vue-router/issues/1066#issuecomment-632719343
Component.registerHooks([
    'beforeRouteEnter',
    'beforeRouteLeave',
    'beforeRouteUpdate'
]);

Vue.config.productionTip = process.env.NODE_ENV === 'production';

new Vue({
    render: h => h(App)
}).$mount('#app');
