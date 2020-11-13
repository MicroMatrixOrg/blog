import Vue from 'vue'
import App from './App'
import router from './router'
Vue.prototype.routerCfg = router;



//ie兼容处理
import 'babel-polyfill'


import './permission.js' // 路由拦截

import axios from 'axios'
Vue.prototype.$axios = axios //


import './axios.js' // 请求拦截


// 引用工具文件
import utils from './utils/index.js'
import APIConfig from './utils/config.js'
// 将工具方法绑定到全局
Vue.prototype.$utils = utils;


//加载vuex
import store from './store';
Vue.prototype.$store = store;

import storge from "./utils/localStorge.js";
Vue.prototype.$storge = storge;
// 全局注册
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
// use
Vue.use(mavonEditor)

//引入ElemeentUI
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI);

// 全局样式文件 为以后的手机端做准备
import "../static/style/base_html.css";
// 默认主题文件 为以后的个性化主题
import "../static/style/base_theme.css";
//引入anime.js
import anime from 'animejs/lib/anime.es.js';
Vue.prototype.$anime = anime;

//引入骨架屏
import skeleton from "./components/skeleton/index.js";
skeleton.skeleton.install(Vue);
skeleton.skeletonCircle.install(Vue);
skeleton.skeletonColumn.install(Vue);
skeleton.skeletonRow.install(Vue);
skeleton.skeletonSquare.install(Vue);

// //第一次路由跳转验证
// let isLoad = true;
// router.beforeEach((to, from, next) => {
//   // const _this=this;
//   // let pathArry=store.state.lisPathSys.get("modId");
//   // if(!pathArry){next();return; }
//   // let isAuth=pathArry.indexOf(to.meta.modId) == -1 && to.meta.modId !=0;
//   let path = '/discussion' + to.fullPath; //zy——后期的模块名
//   window.parent.postMessage({
//     path
//   }, '*');
//   next();
// })

/* eslint-disable no-new */

Vue.config.productionTip = false;
var app = new Vue({
  el: '#app',
  router,
  store, //使用vuex  
  template: '<App/>',
  components: {
    App
  },
  created () {
    window.APIConfig = APIConfig.Init(window.conf.baseUrl, window.conf.fileUrl);
  },
})
