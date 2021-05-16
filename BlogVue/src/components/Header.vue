<template>
  <div class="toolbar-header-wrap">
    <div class="m-content-theme toolbar-header">
      <a class="m-icon-theme-default logo" @click="toMainPage()">
        <!-- <svg
          width="60"
          height="40"
          viewBox="0 0 186 130"
          version="1.1"
          xmlns="http://www.w3.org/2000/svg"
          xmlns:xlink="http://www.w3.org/1999/xlink"
        >
          <title>Matrix</title>
          <defs>
            <polygon
              id="path-1"
              points="18.845574 24.125929 40.0587774 2.91272556 115.012096 77.8660444 93.7988928 99.0792478"
            ></polygon>
            <filter
              x="-0.5%"
              y="-0.5%"
              width="101.0%"
              height="102.1%"
              filterUnits="objectBoundingBox"
              id="filter-2"
            >
              <feOffset
                dx="0"
                dy="1"
                in="SourceAlpha"
                result="shadowOffsetOuter1"
              ></feOffset>
              <feComposite
                in="shadowOffsetOuter1"
                in2="SourceAlpha"
                operator="out"
                result="shadowOffsetOuter1"
              ></feComposite>
              <feColorMatrix
                values="0 0 0 0 1   0 0 0 0 0   0 0 0 0 0  0 0 0 1 0"
                type="matrix"
                in="shadowOffsetOuter1"
              ></feColorMatrix>
            </filter>
            <polygon
              id="path-3"
              points="148.51968 3.08326112 169.916739 24.2597317 94.3137977 99.0832611 72.9167389 77.9067905"
            ></polygon>
            <filter
              x="-0.5%"
              y="-0.5%"
              width="101.0%"
              height="102.1%"
              filterUnits="objectBoundingBox"
              id="filter-4"
            >
              <feOffset
                dx="0"
                dy="1"
                in="SourceAlpha"
                result="shadowOffsetOuter1"
              ></feOffset>
              <feComposite
                in="shadowOffsetOuter1"
                in2="SourceAlpha"
                operator="out"
                result="shadowOffsetOuter1"
              ></feComposite>
              <feColorMatrix
                values="0 0 0 0 1   0 0 0 0 0   0 0 0 0 0  0 0 0 1 0"
                type="matrix"
                in="shadowOffsetOuter1"
              ></feColorMatrix>
            </filter>
            <polygon id="path-5" points="4 3 40 3 40 125 4 125"></polygon>
            <filter
              x="-18.1%"
              y="-4.5%"
              width="136.1%"
              height="110.7%"
              filterUnits="objectBoundingBox"
              id="filter-6"
            >
              <feOffset
                dx="0"
                dy="1"
                in="SourceAlpha"
                result="shadowOffsetOuter1"
              ></feOffset>
              <feGaussianBlur
                stdDeviation="2"
                in="shadowOffsetOuter1"
                result="shadowBlurOuter1"
              ></feGaussianBlur>
              <feComposite
                in="shadowBlurOuter1"
                in2="SourceAlpha"
                operator="out"
                result="shadowBlurOuter1"
              ></feComposite>
              <feColorMatrix
                values="0 0 0 0 0   0 0 0 0 0   0 0 0 0 0  0 0 0 0.5 0"
                type="matrix"
                in="shadowBlurOuter1"
              ></feColorMatrix>
            </filter>
            <polygon id="path-7" points="146 3 182 3 182 125 146 125"></polygon>
            <filter
              x="-18.1%"
              y="-4.5%"
              width="136.1%"
              height="110.7%"
              filterUnits="objectBoundingBox"
              id="filter-8"
            >
              <feOffset
                dx="0"
                dy="1"
                in="SourceAlpha"
                result="shadowOffsetOuter1"
              ></feOffset>
              <feGaussianBlur
                stdDeviation="2"
                in="shadowOffsetOuter1"
                result="shadowBlurOuter1"
              ></feGaussianBlur>
              <feComposite
                in="shadowBlurOuter1"
                in2="SourceAlpha"
                operator="out"
                result="shadowBlurOuter1"
              ></feComposite>
              <feColorMatrix
                values="0 0 0 0 0   0 0 0 0 0   0 0 0 0 0  0 0 0 0.5 0"
                type="matrix"
                in="shadowBlurOuter1"
              ></feColorMatrix>
            </filter>
          </defs>
          <g
            id="页面-1"
            stroke="none"
            stroke-width="1"
            fill="none"
            fill-rule="evenodd"
          >
            <g id="矩形">
              <use
                fill="black"
                fill-opacity="1"
                filter="url(#filter-2)"
                xlink:href="#path-1"
              ></use>
              <path
                stroke="#FF0000"
                stroke-width="1"
                d="M40.0587774,3.61983234 L114.304989,77.8660444 L93.7988928,98.372141 L19.5526808,24.125929 L40.0587774,3.61983234 Z"
                stroke-linejoin="square"
                fill="#FF0000"
                fill-rule="evenodd"
              ></path>
            </g>
            <g id="矩形">
              <use
                fill="black"
                fill-opacity="1"
                filter="url(#filter-4)"
                xlink:href="#path-3"
              ></use>
              <path
                stroke="#FF0000"
                stroke-width="1"
                d="M148.51968,3.78673246 L169.20594,24.2597317 L94.3137977,98.3797898 L73.627538,77.9067905 L148.51968,3.78673246 Z"
                stroke-linejoin="square"
                fill="#FF0000"
                fill-rule="evenodd"
              ></path>
            </g>
            <g id="矩形">
              <use
                fill="black"
                fill-opacity="1"
                filter="url(#filter-6)"
                xlink:href="#path-5"
              ></use>
              <path
                stroke="#000000"
                stroke-width="1"
                d="M39.5,3.5 L39.5,124.5 L4.5,124.5 L4.5,3.5 L39.5,3.5 Z"
                stroke-linejoin="square"
                fill="#000000"
                fill-rule="evenodd"
              ></path>
            </g>
            <g id="矩形">
              <use
                fill="black"
                fill-opacity="1"
                filter="url(#filter-8)"
                xlink:href="#path-7"
              ></use>
              <path
                stroke="#000000"
                stroke-width="1"
                d="M181.5,3.5 L181.5,124.5 L146.5,124.5 L146.5,3.5 L181.5,3.5 Z"
                stroke-linejoin="square"
                fill="#000000"
                fill-rule="evenodd"
              ></path>
            </g>
          </g>
        </svg> -->
        <img
          src="../../static/image/MatrixLogo.png"
          alt=""
          style="width: 3.5rem;object-fit: contain;"
        />
      </a>
      <nav role="navigation" class="main-nav">
        <ul class="m-routers-theme-default nav-list">
          <!-- <li class="m-mian-navi-theme-default nav-item">
            <ul role="tab" class="m-router-ul-theme-default phone-hide ">
              <li class="link-item nav-item">
                <a class="nav-item-a" href="#/blogs">首页</a>
              </li>
            </ul>
          </li> -->
          <li class="m-person-theme-default nav-item search">
            <div class="search-form-theme-default ">
              <searchbtn @matrix-input-value="searchBlog"></searchbtn>
            </div>
          </li>
          <li
            :class="[
              'm-person-theme-default',
              'nav-item',
              'write-item',
              hasLogin ? 'hide-write-item' : ''
            ]"
          >
            <div class="add-btn-group">
              <button class="add-blog" @click="writeblog()">写文章</button>
            </div>
          </li>
          <li
            v-if="hasLogin"
            class="m-person-theme-default nav-item menu person-item"
          >
            <div
              class="lazy avatar avatar loaded immediate"
              id="user-function"
              :style="
                `background-image:url(${
                  user.id != 0 && user.avatar
                    ? user.avatar
                    : '../../static/image/m.ico'
                })`
              "
            ></div>
            <ul
              class="nav-menu user-dropdown-list"
              id="nav-menu-list-display"
              style="display:none;"
            >
              <div class="nav-menu-item-group">
                <li class="nav-menu-item">
                  <a @click="writeblog()">
                    <i class="fa fa-pencil icon" aria-hidden="true"></i>
                    <span>写文章</span>
                  </a>
                </li>
              </div>
              <div class="nav-menu-item-group">
                <li class="nav-menu-item">
                  <a @click="homePage()">
                    <i class="fa fa-user-secret icon" aria-hidden="true"></i>
                    <span>我的主页</span>
                  </a>
                </li>
              </div>
              <div class="nav-menu-item-group">
                <li class="nav-menu-item">
                  <a @click="signout()">
                    <i class="fa fa-sign-out icon" aria-hidden="true"></i>
                    <span>退出</span>
                  </a>
                </li>
              </div>
            </ul>
          </li>

          <!-- <li class="m-person-theme-default nav-item notifaction">
            <a href=""></a>
          </li> -->
        </ul>
      </nav>
    </div>
  </div>
</template>
<script>
import searchbtn from "@/components/input/Input.vue";
export default {
  name: "Header",
  components: {
    searchbtn
  },
  data() {
    return {
      hasLogin: false,
      user: {
        id: 0,
        username: "请先登录",
        avatar:
          "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png"
      },
      blogs: {},
      currentPage: 1,
      total: 0,
      dialogVisible: false,

      form: {
        name: "",
        email: "",
        password: "",
        checkPass: "",
        verifiCode: ""
      }
    };
  },
  beforeDestroy() {
    const _this = this;
    document.removeEventListener("click", _this.showControl, true);
  },

  mounted() {
    const _this = this;
    document.addEventListener("click", _this.showControl, true);
  },
  created() {
    if (this.$store.getters.GET_USER) {
      this.user.id = this.$store.getters.GET_USER.id;
      this.user.username = this.$store.getters.GET_USER.username;
      this.user.avatar = this.$store.getters.GET_USER.avatar;
      this.hasLogin = true;
    }
  },
  methods: {
    /**
     * @description: 组件函数回调 搜索按钮被点击
     * @param {*}
     * @return {*}
     * @Date: 2021-05-16 15:06:07
     * @Author: David
     */

    searchBlog(keyword) {
      this.$emit("matrix-search", keyword);
    },
    /**
     * @description: 点击回到主页
     * @param {*}
     * @return {*}
     * @Date: 2021-03-07 11:15:19
     * @Author: David
     */

    toMainPage() {
      const _this = this;
      let path = this.routerCfg.options.pathById(2);
      this.$router.push({ path });
    },
    /**
     * @description: 退出
     * @Date: 2020-08-08 10:57:47
     * @Author: David
     */

    signout() {
      const _this = this;
      this.$axios
        .get(APIConfig.Base.Logout, {
          headers: {
            Authorization: this.$store.getters.GET_TOKEN
          }
        })
        .then(res => {
          _this.$store.commit("REMOVE_INFO");

          if (_this.$route.query.isVoted) {
            const query = JSON.parse(JSON.stringify(_this.$route.query));
            query.isVoted = null;
            _this.$router.push({ path: _this.$route.path, query });
          }
          _this.$router.go(0);
        });
    },
    /**
     * @description: 控制头像下的列表显示和隐藏
     * @return {*}
     * @Date: 2020-10-29 10:45:21
     * @Author: David
     */

    showControl(event) {
      const _this = this;
      var cDom = document.querySelector("#user-function");
      var tDom = event.target;
      var lDom = document.querySelector("#nav-menu-list-display");
      if (cDom && (cDom == tDom || cDom.contains(tDom))) {
        if (lDom.style.display == "unset") {
          lDom.style.display = "none";
        } else {
          lDom.style.display = "unset";
        }
      } else {
        if (lDom) {
          lDom.style.display = "none";
        }
      }
    },
    /**
     * @description: 写博客函数 如果登录了直接跳转到写文章页面 如何没有登录或者没有权限写文章，跳出登录框
     * @Date: 2020-10-28 09:54:03
     * @Author: David
     */

    writeblog() {
      if (this.user.id == 0) {
        let path = this.routerCfg.options.pathById(1);
        this.$router.push({ path });
      } else {
        let path = this.routerCfg.options.pathById(4);
        this.$router.push({ path });
      }
    },

    /**
     * @description: 我的主页
     * @Date: 2020-11-05 09:23:34
     * @Author: David
     */

    homePage() {
      const _this = this;
      let path = this.routerCfg.options.pathById(21);
      this.$router.push({ path }, () => {});
    }
  }
};
</script>

<style lang="scss" scoped>
.main-nav {
  height: 100%;
  flex: 1 0 auto;
}

.logo {
  margin-right: 2rem;
  margin-left: 0.15rem;
  cursor: pointer;
}

.toolbar-header-wrap {
  background: #fff;
  border-bottom: 1px solid #f1f1f1;
  color: #909090;
  height: 5rem;
  z-index: 250;
  position: sticky;
  top: 0;
}
.toolbar-header {
  display: flex;
  align-items: center;

  max-width: 960px;
  margin: 0 auto;

  height: calc(100%);
}

.nav-list {
  display: flex;
  align-items: center;
  height: calc(100%);
}

.nav-item {
  color: #71777c;
  padding: 0 1.2rem;
  font-size: 1.33rem;
  justify-content: center;
  cursor: pointer;
}

.nav-item a {
  color: #007fff;
}

.phone-hide {
  display: flex;
}

.nav-item {
  color: #71777c;
  padding: 0 1.2rem;
  font-size: 1.33rem;
  justify-content: center;
  cursor: pointer;

  display: flex;
  align-items: center;
  height: 100%;
  margin: 0;
  user-select: none;
}

.link-item {
  padding: 0 1.5rem;
  height: 5rem;
}

.search {
  flex: 1 1 auto;
  justify-content: flex-end;
  cursor: auto;
}

.add-btn-group {
  display: flex;
  align-items: center;
  position: relative;
  height: 2.667rem;
  cursor: pointer;
}

.add-blog {
  padding: 0 0.9rem;
  font-size: 1.167rem;
  border-top-right-radius: 0;
  border-bottom-right-radius: 0;
  height: 100%;
  color: #fff;
  background-color: #007fff;
  border-radius: 3px;
  border: 1px solid #007fff;
  cursor: pointer;
}

.lazy {
  position: relative;
}

.lazy.loaded:before {
  opacity: 0;
  pointer-events: none;
}

.lazy:before {
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: inherit;
  border-radius: inherit;
}

.avatar {
  width: 2.5rem;
  height: 2.5rem;
  border-radius: 50%;
  display: inline-block;
  position: relative;
  background-size: cover;
  background-color: #eee;
  background-position: 50%;
  background-repeat: no-repeat;
}

.menu {
  position: relative;
}
.nav-menu {
  position: absolute;
  width: 11rem;
  left: 50%;
  top: 100%;
  transform: translateX(-50%);
  margin: 0;
  padding: 0;
  list-style: none;
  background-color: #fff;
  box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.1);
  border: 1px solid rgba(177, 180, 185, 0.45);
  border-radius: 4px;
  font-size: 1.2rem;
}
.user-dropdown-list {
  width: 13.1rem;
  right: 0;
  left: auto;
  transform: translateX(0);
}
.nav-menu-item-group {
  border-bottom: 1px solid rgba(0, 0, 0, 0.04);
  padding: 1rem 0;
}
.nav-menu-item {
  font-size: 1.3rem;
  cursor: pointer;
}
.nav-menu-item {
  > a {
    display: flex;
    align-items: center;
    padding: 0.5rem 1rem;
    color: #71777c;

    .icon {
      width: 1em;
      height: 1em;
      margin-right: 0.8rem;
      font-size: 1.2em;
      vertical-align: middle;
      color: #b2bac2;
      font-style: normal;
      -webkit-font-smoothing: antialiased;
    }
  }

  > span {
    margin-left: 0;
    flex-grow: 1;
  }
}

@media (max-width: 570px) {
  //手机大小的屏幕

  .hide-write-item {
    display: none;
  }
}
</style>
