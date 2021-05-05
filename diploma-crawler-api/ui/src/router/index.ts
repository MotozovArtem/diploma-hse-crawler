import Vue from 'vue';
import VueRouter, { RouteConfig, RouterMode } from 'vue-router';

Vue.use(VueRouter);

const routes: Array<RouteConfig> = [
  {
    path: '/',
    name: 'index',
    component: () => import('@/views/Main.vue'),
    meta: { title: 'Главная' }
  },
  {
    path: '/web_page',
    name: 'web_pages',
    component: () => import('@/views/WebPageTable.vue'),
    meta: { title: 'Web Page Table' }
  },
  {
    path: '/web_portal',
    name: 'web_portals',
    component: () => import('@/views/WebPortalTable.vue'),
    meta: { title: 'Web Portal Table' },
    props: true
  },
  {
    path: '/web_page_analyse_result',
    name: 'web_page_analyse_results',
    component: () => import('@/views/WebPageAnalyseResultTable.vue'),
    meta: { title: 'Web Page Analyse Result Table' },
    props: true
  }
];

const router = new VueRouter({
  mode: process.env.VUE_APP_ROUTING_MODE as RouterMode,
  scrollBehavior(to, from, savedPosition) {
    return savedPosition || { x: 0, y: 0 };
  },
  routes
});

router.beforeEach((to, from, next) => {
  document.title = `Crawler API UI ${document.location.hostname}`;
  next();
});

export default router;
