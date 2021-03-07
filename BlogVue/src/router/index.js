import Vue from 'vue'
import VueRouter from 'vue-router';
import Router from 'vue-router'

const Login = () => import("../page/login/Login.vue");
const Blogs = () => import("../page/blogs/Blogs.vue");
const BlogDetail = () => import("../page/blogs/BlogDetails.vue");
const BlogEdit = () => import("../page/blogs/BlogEdit.vue");
const VerifyAccount = () => import("../page/login/VerifyAccount.vue");
const MyHomePage = () => import("../page/userinfo/MyHomePage.vue");
const Settings = () => import("../page/userinfo/EditProfile.vue");

const NotFoundComponent = () => import("../page/errorPage/404.vue")

Vue.use(Router)

const router = new VueRouter({
  mode: "history",
  //全部的
  routes: [
    // 404页面
    { path: '*', component: NotFoundComponent },

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
      component: Blogs,
      meta: {
        modId: 2
      },
      children: [
        {
          path: '/user',
          name: 'User',
          meta: {
            requireAuth: true,
            modId: 21

          },
          component: MyHomePage
        },
        {
          path: '/blogDetails',
          name: 'BlogDetail',
          component: BlogDetail,
          meta: {
            modId: 22
          }
        },
        {
          path: '/setting',
          name: 'AccountSetting',
          meta: {
            requireAuth: true,
            modId: 23
          },
          component: Settings
        },
      ]
    },

    {
      path: '/verifyaccount',
      name: 'VerifyAccount',
      meta: {
        requireAuth: false,
        modId: 3

      },
      component: VerifyAccount
    },
    {
      path: '/blog/edit',
      name: 'BlogEdit',
      meta: {
        requireAuth: true,
        modId: 4

      },
      component: BlogEdit
    },



  ],

  pathById: function (id) {
    var path = '';
    this.routes.forEach(element => {
      let children = [];
      if (typeof element.meta != "undefined") {
        if (element.meta.modId == id) {
          path = element.path
        };
        children = typeof element.children != "undefined" ? element.children : [];
        children.forEach(element => {
          if (element.meta.modId == id) {
            path = element.path;
            // throw new Error("EndIterative");
          }
        });
      }
    });
    return path;
  }

}
)




// export default new Router({mode: 'history',routes})
export default router
// export default new Router({ routes, pathById })


