<template>
  <div class="stories-list">
    <div class="stories-wrap" v-for="(item, index) in blogList" :key="index">
      <div class="stories">
        <div class="avatar">
          <img :src="userInfo.avatar" alt="" />
        </div>
        <div class="s-content">
          <span class="username">{{ userInfo.username }}</span>
          <span class="time">{{ $utils.goodTime(item.created) }}</span>
          <h3 class="title" @click="openDetail(item.id)">{{ item.title }}</h3>
        </div>
      </div>
      <div class="thumb-action" v-show="false">
        <div class="thumbs">
          <div class="thumbs-o-up" v-show="true">
            <i class="fa fa-thumbs-o-up" aria-hidden="true"></i>&nbsp;<span
              >赞</span
            >
          </div>
          <div class="thumbs-up" v-show="false">
            <i class="fa fa-thumbs-up" aria-hidden="true"></i>&nbsp;<span
              >1</span
            >
          </div>
        </div>
        <div class="comment">
          <div class="comment-wrap">
            <i class="fa fa-comments-o" aria-hidden="true"></i>&nbsp;<span
              >2</span
            >
          </div>
        </div>
        <div class="share"></div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    userId: {
      type: [Number, String],
      default: 0
    }
  },
  watch: {
    userId(newValue, oldValue) {
      this.getUserStories();
    }
  },
  data() {
    return {
      pageSize: 10, //每页的数量
      currentPage: 0, //当前页数

      blogList: [], //博客列表

      //用户信息
      userInfo: {
        username: "",
        avatar: "",
        id: 0
      }
    };
  },
  mounted() {
    const _this = this;
    _this.getUserInfo();
  },
  methods: {
    openDetail(id) {
      const _this = this;
      let path = this.routerCfg.options.pathById(22);

      this.$router.push({ path, query: { blogId: id } });
    },

    /**
     * @description: 获取用户的信息，因为这个是自己的文章列表，所以用户信息就采用本地的用户信息
     * @Date: 2020-11-09 16:09:39
     * @Author: David
     */

    getUserInfo() {
      const _this = this;
      if (_this.$store.getters.getUser) {
        _this.userInfo = _this.$store.getters.getUser;
      }
    },

    /**
     * @description: 获取用户发布的文章
     * @Date: 2020-11-09 16:10:32
     * @Author: David
     */

    getUserStories() {
      const _this = this;
      let params = {
        userId: _this.userId,
        currentPage: this.currentPage,
        pageSize: this.pageSize
      };
      this.$axios.post(APIConfig.Base.BlogsByUserId, params).then(res => {
        let resp = res.resp;
        let respData = res.respData;
        _this.blogList = [..._this.blogList, ...respData.data.records];
        console.log(respData.data);
      });
    }
  }
};
</script>

<style lang="scss" scoped>
.stories-list {
  background-color: #f1f1f1;
}

.stories-wrap {
  background-color: #fff;
}
.stories-wrap:not(:first-child) {
  margin-top: 1rem;
}

.stories {
  display: flex;
  padding: 16px 20px;
}
.avatar {
  > img {
    width: 3.75rem;
    height: 3.75rem;
    border-radius: 50%;
    object-fit: cover;
  }
}

.s-content {
  display: flex;
  align-items: flex-start;
  flex-direction: column;
  margin-left: 1rem;
  .username {
    font-weight: 600;
    font-size: 15px;
    color: #17181a;
  }
  .time {
    display: flex;
    align-items: center;
    margin: 0.3rem 0 0;
    font-size: 13px;
    color: #8a9aa9;
    cursor: default;
  }
  .title {
    margin: 0;
    display: inline;
    font-size: 17px;
    color: #17181a;
    line-height: 1.5;
    cursor: pointer;
  }
}
.thumb-action {
  display: flex;
  padding: 10px 0;
  align-items: center;
  border-top: 1px solid #eee;

  > div:not(:last-child) {
    border-right: 1px solid #eee;
  }
}
.thumbs,
.comment {
  flex: 1 1 auto;
}
.thumbs-o-up,
.thumbs-up,
.comment-wrap {
  display: flex;
  justify-content: center;
  align-items: center;
  line-height: 20px;
  > i {
    font-size: 20px;
    cursor: pointer;
  }
  .span {
    font-size: 16px;
  }
}
</style>
