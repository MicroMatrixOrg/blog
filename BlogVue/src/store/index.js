import Vue from 'vue';
import Vuex from 'vuex';


Vue.use(Vuex);

const state = {
  listPagePars: new Map(),//各个页面分页的状态集合
  lisPathSys: new Map(),//左侧模块数据
};

export default new Vuex.Store({
  state: {
    token: localStorage.getItem("token"),
    userInfo: JSON.parse(sessionStorage.getItem("userInfo")),
    isLogin: false
  },
  getters: {
    //获得用户信息
    GET_USER: state => {
      return state.userInfo;
    },
    //获得用户session
    GET_TOKEN: state => {
      return state.token;
    },
    //获取用户登录状态
    GET_LOGIN_STATUS: state => {
      return state.isLogin;
    }
  },

  mutations: {
    //设置session
    SET_TOKEN: (state, token) => {
      state.token = token;
      localStorage.setItem("token", token);
      // document.cookie = `ustk=${token}; domain=.${location.hostname}; max-age=10800`;
    },
    //设置用户信息
    SET_USERINFO: (state, userInfo) => {
      state.userInfo = userInfo;
      sessionStorage.setItem("userInfo", JSON.stringify(userInfo));
      // document.cookie = `usid=${userInfo}; domain=.${location.hostname}; max-age=10800`;
    },
    //移除信息
    REMOVE_INFO: (state) => {
      localStorage.setItem("token", '');
      sessionStorage.setItem("userInfo", JSON.stringify(''));
      // document.cookie = `ustk=''; domain=''; max-age=0`;
      // document.cookie = `usid=''; domain=''; max-age=0`;
      state.userInfo = {};
    },
    //设置登录状态
    SET_LOGIN_STATUS: (state) => {
      state.isLogin = state.token != "" ? true : false;
    }
  },
});