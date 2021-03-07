<!--
 * @Author: David
 * @Date: 2020-11-07 10:05:11
 * @LastEditTime: 2021-03-04 21:33:40
 * @LastEditors: David
 * @Description: 用户动态页面
 * @FilePath: /BlogVue/src/components/userInfoComponents/Stories.vue
 * 可以输入预定的版权声明、个性签名、空行等
-->

<template>
  <div class="stories-list">
    <div class="stories-wrap" v-for="(item, index) in blogList" :key="index">
      <div class="stories">
        <div class="avatar">
          <img
            :src="
              userInfo.avatar ? userInfo.avatar : '../../../static/image/m.ico'
            "
            alt=""
          />
        </div>
        <div class="s-content">
          <span class="username">{{ userInfo.username }}</span>
          <span class="time">{{ $utils.goodTime(item.created) }}</span>
          <h3 class="title" @click="openDetail(item.id)">{{ item.title }}</h3>
        </div>
      </div>
      <div class="thumb-action">
        <div class="thumbs">
          <div
            class="thumbs-o-up"
            v-show="typeof item.isVote == null || !item.isVote"
            @click.stop="thumb(item)"
          >
            <i class="fa fa-thumbs-o-up" aria-hidden="true"></i>&nbsp;<span
              >赞</span
            >
          </div>
          <div class="thumbs-up" v-show="item.isVote" @click.stop="thumb(item)">
            <i class="fa fa-thumbs-up voted" aria-hidden="true"></i>&nbsp;<span
              >{{ item.voteCount }}</span
            >
          </div>
        </div>
        <div class="comment">
          <div class="comment-wrap">
            <i class="fa fa-comments-o" aria-hidden="true"></i>&nbsp;<span>{{
              item.commentCount
            }}</span>
          </div>
        </div>
        <div class="share"></div>
      </div>
    </div>
  </div>
</template>

<script>
import myScroll from "@/components/scroll/scroll.vue";
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
  mixins: [myScroll],
  data() {
    return {
      pageSize: 10, //每页的数量
      currentPage: 0, //当前页数
      totalPage: 0,

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
  },
  methods: {
    /**
     * @description: 文章点赞
     * @param {Number} blog 文章ID
     * @return {*}
     * @Date: 2021-03-03 22:00:44
     * @Author: David
     */

    thumb(blog) {
      let user = this.$store.getters.GET_USER;
      if (user) {
        let params = {
          userId: user.id,
          voteableId: blog.id
        };
        this.$axios.post(APIConfig.Thumb.Like, params).then(res => {
          let resp = res.resp;
          let respData = res.respData;
          if (respData.code == 200) {
            if (respData.data) {
              blog.voteCount++;
              blog.isVote = true;
            } else {
              blog.voteCount--;
              blog.isVote = false;
            }
          }
        });
      } else {
        let path = this.routerCfg.options.pathById(1);
        this.$router.push(path);
      }
    },

    /**
     * @description: 全局计算距离底部多少的时候加载数据 calcHeight方法在mixins中混入
     * @Date: 2020-11-04 09:50:47
     * @Author: David
     */

    loadMore() {
      const _this = this;
      let haveNext = _this.currentPage < _this.totalPage;
      let loadPage = _this.calcHeight(haveNext);
      if (loadPage) {
        this.currentPage++;
        // this.getUserStories();
      }
    },
    /**
     * @description: 打开详情野页面
     * @param {number} id
     * @return {*}
     * @Date: 2020-11-09 17:05:15
     * @Author: David
     */

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
      if (_this.$store.getters.GET_USER) {
        _this.userInfo = _this.$store.getters.GET_USER;
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
        _this.totalPage = respData.data.total;
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
.voted {
  color: #6cbd45 !important;
}
</style>
