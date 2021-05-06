import Vue from 'vue';
import VueRouter, {RouteConfig, RouterMode} from 'vue-router';

Vue.use(VueRouter);

const routes: Array<RouteConfig> = [
  {
    path: '/',
    name: 'index',
    component: () => import('@/views/Main.vue'),
    meta: {title: 'Main', showInMenu: true, icon: 'mdi-home'}
  },
  {
    path: '/web_page',
    name: 'web_pages',
    component: () => import('@/views/WebPageTable.vue'),
    meta: {title: 'Web Page', showInMenu: true, icon: 'mdi-table'}
  },
  {
    path: '/web_page_item/:id',
    name: 'web_page_item',
    component: () => import('@/views/item/WebPageItem.vue'),
    meta: {title: 'Web Page Item', showInMenu: false},
    props: true
  },
  {
    path: '/web_portal',
    name: 'web_portals',
    component: () => import('@/views/WebPortalTable.vue'),
    meta: {title: 'Web Portal', showInMenu: true, icon: 'mdi-table'}
  },
  {
    path: '/web_portal_item/:id',
    name: 'web_portal_item',
    component: () => import('@/views/item/WebPortalItem.vue'),
    meta: {title: 'Web Portal Item', showInMenu: false},
    props: true
  },
  {
    path: '/web_page_analyse_result',
    name: 'web_page_analyse_results',
    component: () => import('@/views/WebPageAnalyseResultTable.vue'),
    meta: {title: 'Web Page Analyse Result', showInMenu: true, icon: 'mdi-table'}
  },
  {
    path: '/web_page_analyse_result_item/:id',
    name: 'web_page_analyse_result_item',
    component: () => import('@/views/item/WebPageAnalyseResultItem.vue'),
    meta: {title: 'Web Page Analyse Result Item', showInMenu: false},
    props: true
  },
  {
    path: '/web_page_backlog',
    name: 'web_page_backlog',
    component: () => import('@/views/WebPageBacklog.vue'),
    meta: {title: 'Web Page Backlog', showInMenu: true, icon: 'mdi-file-question-outline'}
  }
];

const router = new VueRouter({
  mode: process.env.VUE_APP_ROUTING_MODE as RouterMode,
  scrollBehavior(to, from, savedPosition) {
    return savedPosition || {x: 0, y: 0};
  },
  routes
});

router.beforeEach((to, from, next) => {
  document.title = `Crawler API UI ${document.location.hostname}`;
  next();
});

export default router;
