<template>
  <div class="timeline-container view" id="timeline-contain">
    <div class="timeline-contain">
      <div class="timeline-enter-list">
        <header class="list-header">
          <ul class="timeline-ul">
            <li
              class="nav-header-item active"
              @click.stop="searchParams($event, 1)"
            >
              <a>最新</a>
            </li>
            <li class="nav-header-item" @click.stop="searchParams($event, 2)">
              <a>热门</a>
            </li>
            <li class="nav-header-item" @click.stop="searchParams($event, 3)">
              <a>热榜</a>
            </li>
          </ul>
        </header>

        <div class="entry-list-wrap" v-show="requested">
          <ul class="entry-list">
            <div
              class="entry-item-wrap"
              v-for="(item, index) in aritcleList"
              :key="index"
              @click="openDetail(item.id, item.isVote)"
            >
              <li class="entry-item">
                <header>
                  <span class="userinfo-span">{{ item.username }}</span
                  ><span class="userinfo-span">{{
                    $utils.goodTime(item.created)
                  }}</span>
                  <!-- <span class="userinfo-span">前端</span> -->
                </header>
                <div class="title">
                  <span
                    ><a>{{ item.title }}</a></span
                  >
                </div>
                <div class="action-row">
                  <ul class="action-list">
                    <li class="like-btn" @click.stop="thumb(item)">
                      <a class="title-box">
                        <i
                          :class="[
                            'fa',
                            'fa-thumbs-up',
                            item.isVote ? 'voted' : ''
                          ]"
                          aria-hidden="true"
                        ></i>
                        <span :class="[item.isVote ? 'voted' : '']">{{
                          item.voteCount
                        }}</span>
                      </a>
                    </li>
                    <li class="comment-btn">
                      <a class="title-box">
                        <i class="fa fa-commenting" aria-hidden="true"></i>
                        <span>{{ item.commentCount }}</span>
                      </a>
                    </li>
                  </ul>
                </div>
              </li>
            </div>

            <div class="entry-item-wrap" v-show="aritcleList.length <= 0">
              <img
                src="../../../static/image/empty.png"
                alt=""
                style="margin:0 auto;"
              />
            </div>
          </ul>
        </div>
        <div class="entry-list-wrap" v-show="!requested">
          <skeleton>
            <column class="entry-list">
              <div class="entry-item-wrap">
                <li class="entry-item">
                  <header>
                    <skeleton-square width="160px"></skeleton-square>
                  </header>
                  <div class="title">
                    <skeleton-square
                      width="400px"
                      height="23px"
                    ></skeleton-square>
                  </div>

                  <row style="display:flex;">
                    <skeleton-square
                      width="42px"
                      height="26px"
                      margin="5px 10px 5px 0"
                    ></skeleton-square>
                    <skeleton-square
                      width="42px"
                      height="26px"
                      margin="5px 10px 5px 0"
                    ></skeleton-square>
                  </row>
                </li>
              </div>
            </column>
          </skeleton>
        </div>
      </div>
      <aside st:block="sidebar" class="index-aside">
        <div class="sidebar-block">
          <div class="user-block">
            <header data-v-a95047c4="" class="user-block-header">
              🎖️作者榜
            </header>
            <ul class="user-list">
              <li class="user-item">
                <a href="" class="link">
                  <div class="avatar authorize"></div>
                  <div class="user-info">
                    <span class="username">测试开发人员</span>
                    <div class="user-description">
                      功能正在制作中
                    </div>
                  </div>
                </a>
              </li>
              <li class="more-item">
                <div class="more">
                  <span>完整榜单</span>
                  <i class="fa fa-chevron-right " aria-hidden="true"></i>
                </div>
              </li>
            </ul>
          </div>
        </div>
      </aside>
    </div>
  </div>
</template>

<script>
import myScroll from "@/components/scroll/scroll.vue";
export default {
  data() {
    return {
      currentPage: 1, //页数
      pageSize: 10, //每页数量
      sortBy: 1, //排序类型
      aritcleList: [], //文章数组
      total: 0, //文章总数
      totalPage: 0, //总共有多少页面
      requested: false //请求完成
    };
  },
  mixins: [myScroll],
  destroyed() {
    const _this = this;
    window.removeEventListener("scroll", _this.loadMore);
  },
  mounted() {
    const _this = this;
    _this.articles();
    window.addEventListener("scroll", _this.loadMore, true);
  },
  methods: {
    /**
     * @description: 全局计算距离底部多少的时候加载数据
     * @Date: 2020-11-04 09:50:47
     * @Author: David
     */

    loadMore() {
      const _this = this;
      let haveNext = _this.currentPage < _this.totalPage;
      let loadPage = _this.calcHeight(haveNext);
      if (loadPage) {
        this.currentPage++;
        this.articles();
      }
    },

    /**
     * @description: 跳转到详情页面
     * @param {Number} id
     * @param {Object} voted 是否点赞
     * @return {*}
     * @Date: 2020-11-27 09:40:46
     * @Author: David
     */

    openDetail(id, voted) {
      const _this = this;
      let path = this.routerCfg.options.pathById(22);

      this.$router.push({ path, query: { blogId: id, isVoted: voted } });
    },

    /**
     * @description: 激活点击的路由导航栏
     * @param {Object} e
     * @Date: 2020-10-31 11:16:35
     * @Author: David
     */

    searchParams(e) {
      const _this = this;

      let aList = document.getElementsByClassName("nav-header-item");
      for (let i = 0; i < aList.length; i++) {
        if (aList[i].classList.contains("active")) {
          aList[i].classList.remove("active");
        }
      }
      e.target.parentNode.classList.add("active");
    },

    /**
     * @description: 获得文章
     * @param {Object} category  文章的分类 推荐、关注。。。。。
     * @return {*}
     * @Date: 2020-10-31 15:31:00
     * @Author: David
     */
    articles(category) {
      const _this = this;
      let params = {
        currentPage: this.currentPage,
        pageSize: this.pageSize
      };
      this.$axios.post(APIConfig.Base.Blogs, params).then(res => {
        let resp = res.resp;
        let respData = res.respData;

        _this.aritcleList = [...this.aritcleList, ...respData.data.records];
        _this.currentPage = respData.data.current;
        _this.total = respData.data.total;
        _this.totalPage = respData.data.pages;
        _this.pageSize = respData.data.size;
        _this.requested = true;
      });
    },

    /**
     * @description: 文章点赞
     * @param {Object} blog 文章ID
     * @return {*}
     * @Date: 2020-11-26 15:45:57
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
            blog.isVote = respData.data;
            blog.isVote ? blog.voteCount++ : blog.voteCount--;
          }
        });
      } else {
        let path = this.routerCfg.options.pathById(1);
        this.$router.push(path);
      }
    }
  }
};
</script>

<style lang="scss" scoped>
.view {
  margin: 1.67rem auto 0;
  max-width: 960px;
  position: relative;
}
.timeline-contain {
  display: flex;
  justify-content: center;
}
.timeline-enter-list {
  margin-right: 21.667rem;
  border-radius: 2px;
  width: 700px;
  background-color: #fff;
}
.list-header {
  padding: 1.3rem 1rem;
  border-bottom: 1px solid hsla(0, 0%, 59.2%, 0.1);
}
.index-aside {
  width: 20rem;
  position: absolute;
  top: 0;
  right: 0;
  z-index: 1;
}
.timeline-ul {
  display: flex;
}
.nav-header-item {
  font-size: 1.17rem;
  padding: 0 1.2rem;
  font-size: 1.16rem;
  border-right: 1px solid hsla(0, 0%, 59.2%, 0.2);
  cursor: pointer;

  > a {
    cursor: pointer;
    color: #909090;
  }
  > a:hover {
    color: #007fff;
  }
}
.active {
  > a {
    color: #007fff;
  }
}

.nav-item:last-child {
  border-right: none;
}
.entry-item-wrap {
  display: flex;
  align-items: center;
  padding: 1.5rem 2rem;
  min-width: 0;
  cursor: pointer;
}
.entry-item-wrap:not(:last-child) {
  border-bottom: 1px solid rgba(178, 186, 194, 0.15);
}
.entry-item {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  width: calc(100%);

  > header {
    font-size: 12px;
    color: #b2bac2;
    display: flex;
    align-items: baseline;
    white-space: nowrap;
  }

  .userinfo-span:not(:last-child):after {
    content: "·";
    margin: 0 0.4em;
    color: #b2bac2;
  }
}

.title {
  margin: 0.5rem 0 1rem;
  font-size: 1.4rem;
  font-weight: 600;
  line-height: 1.2;
  width: calc(100%);
  text-align: start;

  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  > span {
    > a {
      color: #2e3135;
    }
  }
}

.action-list {
  display: flex;

  > li {
    display: flex;
    align-items: center;
    justify-content: center;
    position: relative;
    padding: 0 0.8rem;
    height: 2rem;
    font-size: 1.083rem;
    line-height: 1;
    white-space: nowrap;
    color: #b2bac2;
    border-radius: 1px;
    border: 1px solid #edeeef;
  }

  > li:not(:last-child) {
    border-right: none;
  }

  .title-box {
    cursor: pointer;
    color: #909090;
  }
  .voted {
    color: #6cbd45 !important;
  }
}

.sidebar-block {
  background-color: #fff;
  box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.05);
  border-radius: 2px;
  margin-bottom: 1.3rem;
  font-size: 1.16rem;
  line-height: 1.29;
  color: #333;
}
.user-block {
  background-color: #fff;
}
.user-block-header {
  padding: 1rem 1.3rem;
  border-bottom: 1px solid hsla(0, 0%, 59.2%, 0.1);
}
.link {
  padding: 1rem 1.3rem;
  display: flex;
  align-items: center;
  cursor: pointer;
  color: #909090;
}
.avatar {
  background-image: url("../../../static/image/notebook.svg");
}
.authorize {
  flex: 0 0 auto;
  width: 3.8rem;
  height: 3.8rem;
  border-radius: 50%;
  margin-right: 0.5rem;
}
.user-info {
  overflow: hidden;
  text-align: start;
}
.username {
  font-size: 1.16rem;
  font-weight: 400;
  color: #333;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  margin-bottom: 3px;
}
.user-description {
  color: #909090;
  font-size: 1rem;
  margin-bottom: 3px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.more {
  color: #007fff;
  padding: 1rem 0;
  text-align: center;
  border-top: 1px solid hsla(0, 0%, 59.2%, 0.1);

  > i {
    color: #007fff;
    font-size: 10px;
    text-align: center;
  }
}

//动态布局
@media (max-width: 960px) {
  .index-aside {
    display: none;
  }
  .timeline-enter-list {
    width: calc(100%);
    margin-right: 0;
  }
}
@media (max-width: 600px) {
  .title {
    font-size: 1.2rem;
  }
}
</style>
