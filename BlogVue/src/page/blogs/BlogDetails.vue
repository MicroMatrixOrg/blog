<template>
  <div class="m-container">
    <main class="detail-container-wrap" role="main">
      <div class="article-view column-view">
        <div class="main-conten shadow main-area">
          <article
            v-show="requestedAritcle"
            class="article"
            itemscope="itemscope"
            itemtype="http://schema.org/Article"
          >
            <meta itemprop="headline" :content="blog.title" />
            <meta itemprop="datePublished" :content="blog.created" />
            <div
              itemprop="author"
              itemscope="itemscope"
              itemtype="http://schema.org/Person"
            ></div>
            <div
              itemprop="publisher"
              itemscope="itemscope"
              itemtype="http://schema.org/Organization"
            >
              <meta itemprop="name" content="matrixblog" />
              <div
                itemprop="logo"
                itemscope="itemscope"
                itemtype="https://schema.org/ImageObject"
              >
                <meta
                  itemprop="url"
                  content="https://b-gold-cdn.xitu.io/icon/icon-white-180.png"
                />
                <meta itemprop="width" content="180" />
                <meta itemprop="height" content="180" />
              </div>
            </div>
            <div class="author-info-block">
              <a
                href="/user/1618109547953623"
                target="_blank"
                rel=""
                class="avatar-link"
                ><div
                  data-src="https://sf6-ttcdn-tos.pstatp.com/img/user-avatar/e5d8e8cbb131ef979ad82e78a133d621~120x256.image"
                  class="lazy avatar avatar loaded"
                  style='background-image: url("https://sf6-ttcdn-tos.pstatp.com/img/user-avatar/e5d8e8cbb131ef979ad82e78a133d621~120x256.image");'
                ></div
              ></a>
              <div class="author-info-box">
                <a
                  href=""
                  target="_blank"
                  rel=""
                  class="username username ellipsis"
                  ><span class="name" style="max-width: calc(100% - 50px);">
                    {{ userInfo.username }}
                  </span>
                </a>
                <div class="meta-box">
                  <time
                    :datetime="blog.created"
                    :title="blog.created"
                    class="time"
                  >
                    {{ $utils.dateFormat(blog.created, "yyyy年MM月dd日") }}
                  </time>
                  <span class="views-count">阅读 {{ blog.readCount }}</span>
                  <span class="dot">·</span>
                  <span class="edit-btn" v-if="ownBlog"
                    ><router-link
                      :to="{ name: 'BlogEdit', query: { blogId: blog.id } }"
                      >编辑</router-link
                    ></span
                  >
                </div>
              </div>
            </div>

            <h1 class="article-title">{{ blog.title }}</h1>
            <div class="article-content">
              <mavon-editor
                class="content markdown-body"
                ref="md"
                :ishljs="true"
                v-model="blog.content"
                defaultOpen="preview"
                :subfield="false"
                :editable="false"
                :toolbarsFlag="false"
              />
            </div>
          </article>

          <article class="article" v-show="!requestedAritcle">
            <skeleton>
              <column>
                <skeleton-square
                  width="79px"
                  height="22px"
                  margin="0 0 10px 0"
                ></skeleton-square>
                <skeleton-square
                  width="172px"
                  height="22px"
                  margin=""
                ></skeleton-square>
              </column>
              <column style="align-items:unset !important;">
                <skeleton-square
                  width="300px"
                  height="45px"
                  margin="10px auto"
                ></skeleton-square>
              </column>
              <column padding="8px 25px 15px 25px">
                <skeleton-square
                  width="600px"
                  :count="10"
                  height="24px"
                  margin="10px auto 10px"
                ></skeleton-square>
              </column>
            </skeleton>
          </article>
          <hr />
          <br />
          <editorComment
            id="comment-body"
            :aritcle="blog"
            v-if="aritcleReady"
            @changeCount="setCount"
          ></editorComment>
        </div>
        <aside class="sidebar">
          <div class="sidebar-block shadow">
            <div class="block-title">
              关于作者
            </div>
            <div class="block-body">
              <a class="user-item item"
                ><div
                  :data-src="userInfo.avatar"
                  class="lazy avatar avatar loaded"
                  :style="
                    `background-image:url(${
                      userInfo.avatar
                        ? userInfo.avatar
                        : '../../../static/image/m.ico'
                    })`
                  "
                ></div>
                <div class="info-box">
                  <a class="username"
                    ><span class="name" style="max-width: 128px;">
                      {{ userInfo.username }}
                    </span>
                    <!----> </a
                  ><!---->
                </div></a
              >
            </div>
          </div>
          <div class="sidebar-block shadow">
            <!-- <div class="block-title">
              广告栏
            </div> -->
            <div class="block-body">
              <a href="https://www.vultr.com/?ref=8690574-6G"
                ><img
                  style="background-size:cover;"
                  src="https://file.matrixs.gq/blog/banner.png"
                  width="240"
                  height="250"
              /></a>
            </div>
          </div>
          <!-- <div class="sidebar-block shadow">
            <editor-menu :article="blog.content"></editor-menu>
          </div> -->
        </aside>
        <div class="article-support-panle">
          <ul>
            <li
              :vote-badge="`${blog.voteCount}`"
              class="helper-item thumb-icon"
              @click.stop="thumb(blog)"
            >
              <i
                :class="['fa', 'fa-thumbs-up', isVoted ? 'voted' : '']"
                aria-hidden="true"
              ></i>
            </li>
            <li
              :comment-badge="blog.commentCount"
              class="helper-item comment-icon"
              @click.stop="commentBlog(blog)"
            >
              <i :class="['fa', 'fa-commenting']" aria-hidden="true"></i>
            </li>
          </ul>
        </div>
      </div>
    </main>
    <!-- <div class="mblog">
      <h2>{{ blog.title }}</h2>
      <el-link icon="el-icon-edit" v-if="ownBlog"
        ><router-link :to="{ name: 'BlogEdit', params: { blogId: blog.id } }"
          >编辑</router-link
        ></el-link
      >
      <el-divider></el-divider>
     
    </div> -->
  </div>
</template>
<script>
// import "github-markdown-css/github-markdown.css"; // 然后添加样式markdown-body
import Header from "@/components/Header";
import editorMenu from "@/components/article/Menu.vue";
import editorComment from "@/components/comment/Comment.vue";

export default {
  name: "BlogDetail",
  components: { Header, editorMenu, editorComment },
  data() {
    return {
      blog: {
        userId: null,
        title: "",
        description: "",
        content: "",
        voteCount: 0,
        commentCount: 0,
        created: "2020-12-12" //创建日期
      },
      ownBlog: false, //是否是自己的博客
      isVoted: false, //自己是否给这个博客点赞过
      userInfo: {
        avatar: "",
        username: "未知",
        created: "2020/10/1"
      },
      requestedAritcle: false, //文章详情请求
      requestedAuthor: false, //文章作者信息请求

      aritcleReady: false //请求文章详情的接口完成状态
    };
  },

  created() {
    this.isVoted = JSON.parse(this.$route.query.isVoted);
    this.getBlog();
  },
  methods: {
    /**
     * @description: 获得文章详情
     * @Date: 2020-11-02 10:16:08
     * @Author: David
     */

    getBlog() {
      const _this = this;
      const blogId = this.$route.query.blogId;
      this.$axios.get(`${APIConfig.Base.Blog}/${blogId}`).then(res => {
        let resp = res.resp;
        let respData = res.respData;
        if (respData.code == 200) {
          _this.blog = respData.data;
          var MarkdownIt = require("markdown-it"),
            md = new MarkdownIt();
          var result = md.render(_this.blog.content);

          // _this.blog.content = result;

          // 判断是否是自己的文章，能否编辑
          if (_this.$store.getters.GET_USER) {
            _this.ownBlog =
              _this.blog.userId === _this.$store.getters.GET_USER.id;
          } else {
            _this.ownBlog = false;
          }
          _this.aritcleReady = true;
          _this.requestedAritcle = true;
          _this.getAuthoryInfo();
        }
      });
    },

    /**
     * @description: 获取改篇文章的用户信息
     * @Date: 2020-11-27 15:07:25
     * @Author: David
     */
    getAuthoryInfo() {
      const _this = this;
      this.$axios
        .get(`${APIConfig.User.GetUserBaseInfo}${_this.blog.userId}`)
        .then(res => {
          let resp = res.resp;
          let respData = res.respData;
          if (respData.code == 200) {
            _this.userInfo = respData.data;
            _this.$forceUpdate();
          }
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
        this.$axios
          .post(APIConfig.Thumb.Like, params, {
            headers: {
              Authorization: this.$store.getters.GET_TOKEN
            }
          })
          .then(res => {
            let resp = res.resp;
            let respData = res.respData;
            if (respData.code == 200) {
              this.isVoted = respData.data;
              this.isVoted ? blog.voteCount++ : blog.voteCount--;
            }
          });
      } else {
        let path = this.routerCfg.options.pathById(1);
        this.$router.push(path);
      }
    },

    /**
     * @description: 评价博客按钮 点击跳转到评价到DOM元素处
     * @param {*} val
     * @return {*}
     * @Date: 2020-12-08 11:11:12
     * @Author: David
     */

    commentBlog(val) {
      const _this = this;
      const commentBody = document.getElementById("comment-body");
      window.scrollTo({
        left: 0,
        top: commentBody.offsetTop,
        behavior: "smooth"
      });
      // console.log(commentBody.offsetTop);
    },

    /**
     * @description: 获取子组件传递给父组件对参数，来修改评论数量
     * @Date: 2020-12-07 16:31:24
     * @Author: David
     */

    setCount(val) {
      console.log(val);
      this.blog.commentCount += val;
    }
  }
};
</script>

<style lang="scss" scoped>
/deep/ .v-note-wrapper.shadow {
  box-shadow: none !important;
}

.detail-container-wrap {
  position: relative;
  margin: 0 auto;
  width: 100%;
  max-width: 960px;
  z-index: 1;
}
.article-view {
  margin-top: 1.767rem;
}
.column-view {
  // padding: 0 0 8rem;
}
.main-conten {
  position: relative;
  width: 700px;
  max-width: 100%;
  background-color: #fff;
  border-radius: 2px;
  box-sizing: border-box;
}
.shadow {
  box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.05);
}
.main-area {
  margin-bottom: 1.5rem;
  padding: 0 2rem;
}
.article {
  margin-bottom: 3rem;
  padding: 2rem 0 0;
}
.author-info-block {
  display: flex;
  align-items: center;
  margin-bottom: 2rem;
}
.avatar-link {
  font-size: 0;
  cursor: pointer;
  color: #909090;
}
.author-info-box {
  min-width: 0;
  flex-grow: 1;
  flex: 1;
  text-align: start;
}
.username {
  display: inline-block;
  width: 100%;
  font-size: 1.3rem;
  font-weight: 700;
  color: #333;
}
.ellipsis {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.meta-box {
  font-size: 1.1rem;
  color: #909090;
}
.time {
  letter-spacing: 1px;
}
.views-content {
  margin-left: 0.5em;
}
.dot {
  margin: 0 0.5em;
}
.edit-btn {
  color: #1264b6;
  cursor: pointer;
}

.article-title {
  margin: 0.67em 0;
  font-size: 2.5rem;
  font-weight: 700;
  line-height: 1.5;
}
.article-content {
  position: relative;
}
.sidebar {
  position: absolute;
  top: 0;
  right: 0;
  width: 20rem;
  z-index: 1;
}

.sidebar-block:not(.pure) {
  background-color: #fff;
}
.sidebar-block:not(.pure) .block-title {
  padding: 1rem 1.3rem;
  font-size: 1.16rem;
  color: #333;
  border-bottom: 1px solid hsla(0, 0%, 58.8%, 0.1);
}

.sidebar-block {
  position: relative;
  margin-bottom: 1.5rem;
  border-radius: 2px;
}
.item {
  display: flex;
  align-items: center;
}
.user-item {
  padding: 1.3rem;

  .avatar {
    flex: 0 0 auto;
    margin-right: 1rem;
    width: 4.167rem;
    height: 4.167rem;
    border-radius: 50%;
  }

  .info-box {
    flex: 1 1 auto;
    min-width: 0;

    .username {
      text-align: start;
      font-size: 1.333rem;
      font-weight: 600;
      color: #000;
      white-space: nowrap;
      overflow: hidden;
      text-overflow: ellipsis;
      white-space: pre-wrap;
      .name {
        display: inline-block;
        vertical-align: top;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
        max-width: 128px;
      }
    }
  }
}
.avatar {
  display: inline-block;
  position: relative;
  background-size: cover;
  background-color: #eee;
}
.article-support-panle {
  position: fixed;
  margin-left: -7rem;
  top: 16rem;
  z-index: 1;
}
.helper-item {
  width: 3rem;
  height: 3rem;
  margin-bottom: 0.75rem;
  background-color: #fff;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 50%;
  font-size: 1.5rem;
  color: #b2bac2;
  cursor: pointer;
  position: relative;
}
.thumb-icon:after {
  content: attr(vote-badge);
  position: absolute;
  top: 0;
  left: 75%;
  padding: 0.1rem 0.4rem;
  font-size: 1rem;
  text-align: center;
  line-height: 1;
  white-space: nowrap;
  color: #fff;
  background-color: #b2bac2;
  border-radius: 0.7rem;
  transform-origin: left top;
  transform: scale(0.75);
}

.comment-icon:after {
  content: attr(comment-badge);
  position: absolute;
  top: 0;
  left: 75%;
  padding: 0.1rem 0.4rem;
  font-size: 1rem;
  text-align: center;
  line-height: 1;
  white-space: nowrap;
  color: #fff;
  background-color: #b2bac2;
  border-radius: 0.7rem;
  transform-origin: left top;
  transform: scale(0.75);
}

.voted {
  color: #6cbd45 !important;
}

//动态布局
@media (max-width: 960px) {
  .sidebar,
  .article-support-panle {
    display: none;
  }
  .main-conten {
    width: calc(100%);
    margin-right: 0;
  }
}
</style>
