import Vue from 'vue'
import Router from 'vue-router'

const Login = () => import("../page/Login.vue");
const BlogDetail = () => import("../page/BlogDetails.vue");
const BlogEdit = () => import("../page/BlogEdit.vue");



Vue.use(Router)

//全部的
var routes = [

  {
    path: '/',
    name: 'Index',
    redirect: { name: 'Blogs' }
  },
  {
    path: '/loginPage',
    name: 'Login',
    component: Login,
    meta: {
      modId: 1
    }
  },
  {
    path: '/blogs',
    name: 'Blogs',
    //懒加载
    component: () => import('../page/Blogs.vue'),
    meta: {
      modId: 2
    },
    children: []
  },
  {
    path: '/blog/add',
    name: 'BlogAdd',
    component: BlogEdit,
    meta: {
      requireAuth: true,
      modId: 21
    }
  },
  {
    path: '/blog/:blogId',
    name: 'BlogDetail',
    component: BlogDetail,
    meta: {
      modId: 22
    }
  },
  {
    path: '/blog/:blogId/edit',
    name: 'BlogEdit',
    meta: {
      requireAuth: true,
      modId: 23

    },
    component: BlogEdit
  }
];

var pathById = (id) => {
  var path = '';
  routes.forEach(element => {
    if (typeof element.meta != "undefined") {
      if (element.meta.modId == id) path = element.path;
    }
  });
  return path;
}


// export default new Router({mode: 'history',routes})
// export default new Router({ routes })
export default new Router({ routes, pathById })


