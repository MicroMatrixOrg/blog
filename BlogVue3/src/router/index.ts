import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'

import Dashboard from '../page/home/home.vue'

import Error404 from '../page/error/404.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'Dashboard',
    component: Dashboard,
    meta: {
      title: '首页',
    },
  },
  {
    path: '/404',
    component: Error404,
    meta: {
      title: '404',
    },
  },
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router
