<template>
  <main class="container main-container">
    <div class="view user-view">
      <div class="major-area">
        <div
          itemscope="itemscope"
          itemtype="http://schema.org/Person"
          class="user-info-block shadow block"
        >
          <meta itemprop="url" :content="userInfo.avatar" />
          <meta itemprop="image" :content="userInfo.avatar" /><meta
            itemprop="name"
            :content="userInfo.avatar"
          />
          <div class="user-avatar">
            <img
              :src="
                userInfo.avatar
                  ? userInfo.avatar
                  : '../../../static/image/m.ico'
              "
            />
          </div>
          <div class="user-name user-infobox">
            <div class="top">{{ userInfo.username }}</div>
            <!-- <div class="work">你从事什么职业</div>
            <div class="imtro">你的个人主页是什么</div> -->
          </div>
          <div class="user-infomation">
            <button class="edit-userinfo edit-btn" @click="editProfile()">
              编辑个人资料
            </button>
          </div>
        </div>
        <div class="list-block">
          <tapHeader>
            <tabPanel
              v-for="(item, index) in headerData"
              :key="index"
              :tab-title="item.tabTitle"
            >
              <!-- <stories v-show="item.id == 1" :user-id="userInfo.id"></stories> -->
              <matrix-articles
                v-show="item.id == 2"
                :user-id="userInfo.id"
              ></matrix-articles>
            </tabPanel>
          </tapHeader>
        </div>
      </div>
      <aside class="minor-area" v-show="false"></aside>
    </div>
  </main>
</template>

<script>
import tapHeader from "@/components/tabForTemplate/tab.vue";
import tabPanel from "@/components/tabForTemplate/tabPanel.vue";

import stories from "@/components/userInfoComponents/Stories.vue";
import MatrixArticles from "@/components/userInfoComponents/Article.vue";

export default {
  data() {
    return {
      //Tap栏渲染
      headerData: [
        // { id: 1, tabTitle: "动态" },
        { id: 2, tabTitle: "文章" }
      ],
      //用户信息
      userInfo: {
        username: "",
        avatar: "",
        id: 0
      }
    };
  },
  components: {
    tapHeader,
    tabPanel,
    stories,
    MatrixArticles
  },
  computed: {},
  mounted() {
    const _this = this;
    _this.getUserInfo();
  },
  methods: {
    /**
     * @description: 获取当前登录用户的信息
     * @Date: 2021-03-04 21:51:31
     * @Author: David
     */

    getUserInfo() {
      const _this = this;
      if (_this.$store.getters.GET_USER) {
        _this.userInfo = _this.$store.getters.GET_USER;
      }
    },

    /**
     * @description: 编辑个人信息
     * @Date: 2020-11-09 14:39:53
     * @Author: David
     */

    editProfile() {
      const _this = this;
      let path = this.routerCfg.options.pathById(23);
      this.$router.push(path);
    }
  }
};
</script>

<style lang="scss" scoped>
.container {
  position: relative;
  margin: 0 auto;
  width: 100%;
  max-width: 960px;
}
.view {
  margin-top: 1.767rem;
}
.user-view {
  display: flex;
  margin-bottom: 6rem;
}
.major-area {
  flex: 1 1 auto;
  min-width: 0;
}
.user-info-block {
  display: flex;
  padding: 2.5rem;
}

.block {
  background-color: #fff;
  border-radius: 2px;
}
.shadow {
  box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.05);
}
.minor-area {
  flex: 0 0 auto;
  margin-left: 1rem;
  width: 20rem;
  line-height: 1.2;
}
.user-name {
  flex: 1 1 auto;
  margin-right: 2rem;

  .top {
    margin: 0;
    padding: 0;
    font-size: 2.167rem;
    font-weight: 600;
    line-height: 1.2;
    color: #000;
  }
}
.user-infobox {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
}
.user-avatar {
  flex: 0 0 auto;
  margin-right: 2.4rem;

  background-color: #f9f9f9;
  > img {
    width: 7.5rem;
    height: 7.5rem;
    object-fit: cover;
    border-radius: 50%;
  }
}
.user-infomation {
  flex: 0 0 auto;
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  justify-content: center;
}
.edit-userinfo {
  width: 9.834rem;
  color: #3780f7;
}
.edit-btn {
  margin: 1rem 0 0;
  padding: 0;
  height: 2.834rem;
  font-size: 1.333rem;
  font-weight: 500;
  line-height: 1.2;
  border: 1px solid;
  background-color: #fff;
  border-radius: 4px;
}
button {
  cursor: pointer;
}

.list-block {
  margin-top: 1rem;
}
</style>
