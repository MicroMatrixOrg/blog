<template>
  <div class="m-container">
    <Header></Header>
    <!-- <nav role="navigation" class="view-nav" v-show="showBlogList"> -->
    <!-- <ul class="nav-list left"> -->
    <!-- <li class="nav-item active" @click="searchParams($event)">推荐</li> -->
    <!-- <li class="nav-item " @click="searchParams($event)">关注</li> -->
    <!-- <li class="nav-item " @click="searchParams($event)">后端</li> -->
    <!-- <li class="nav-item " @click="searchParams($event)">前端</li> -->
    <!-- <li class="nav-item " @click="searchParams($event)">Android</li> -->
    <!-- <li class="nav-item " @click="searchParams($event)">iOS</li> -->
    <!-- <li class="nav-item " @click="searchParams($event)">人工智能</li> -->
    <!-- <li class="nav-item " @click="searchParams($event)">开发工具</li> -->
    <!-- <li class="nav-item nav-item-rigth" @click="searchParams($event)">
          标签管理
        </li> -->
    <!-- </ul> -->
    <!-- </nav> -->
    <blog-list v-show="showBlogList"></blog-list>
    <router-view v-show="!showBlogList"></router-view>
  </div>
</template>
<script>
import Header from "@/components/Header";
import BlogList from "@/components/bloglist/BlogList";
export default {
  name: "Blogs",
  components: { Header, BlogList },
  data() {
    return {
      showBlogList: true
    };
  },
  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.meta.modId == 2) {
        vm.showBlogList = true;
      } else {
        vm.showBlogList = false;
      }
    });
  },

  watch: {
    $route(router) {
      if (router.meta.modId == 2) {
        this.showBlogList = true;
      } else {
        this.showBlogList = false;
      }
    }
  },
  methods: {
    /**
     * @description: 激活点击的路由导航栏
     * @param {Object} e
     * @Date: 2020-10-31 11:16:35
     * @Author: David
     */

    searchParams(e) {
      const _this = this;
      let aList = document.getElementsByClassName("nav-item");
      for (let i = 0; i < aList.length; i++) {
        if (aList[i].classList.contains("active")) {
          aList[i].classList.remove("active");
        }
      }
      e.target.classList.add("active");
    }
  },
  mounted() {}
};
</script>

<style lang="scss" scoped>
.view-nav,
.view-nav:before {
  left: 0;
  background-color: #fff;
}
.view-nav {
  position: fixed;

  width: 100%;
  height: 3.833rem;
  z-index: 100;
  box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.05);
  transition: all 0.2s;
  transform: translateZ(0);

  > .nav-list {
    max-width: 960px;
    height: 100%;
    margin: auto;
    display: flex;
    align-items: center;
    line-height: 1;
  }
}

.nav-list {
  position: relative;
}
.nav-item-rigth {
  position: absolute;
  right: 0;
}
.nav-item {
  height: 100%;
  align-items: center;
  display: flex;
  flex-shrink: 0;
  font-size: 1.16rem;
  color: #71777c;
  padding: 0 1rem;

  cursor: pointer;
}

.nav-item:first-child {
  padding: 0 1rem 0 0;
}

.nav-item.active {
  color: #007fff;
}
</style>
