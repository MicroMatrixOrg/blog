<template>
  <div class="m-container">
    <Header></Header>

    <main class="detail-container-wrap" role="main">
      <div class="article-view column-view">
        <div class="main-conten shadow main-area">
          <article
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
              <div data-v-064b8453="" class="author-info-box">
                <a
                  href="/user/1618109547953623"
                  target="_blank"
                  rel=""
                  class="username username ellipsis"
                  ><span
                    data-v-1b6b7cba=""
                    class="name"
                    style="max-width: calc(100% - 50px);"
                  >
                    夏冰雹
                  </span>
                </a>
                <div data-v-064b8453="" class="meta-box">
                  <time
                    data-v-064b8453=""
                    datetime="2020-10-31T08:37:35.000Z"
                    title="Sat Oct 31 2020 16:37:35 GMT+0800 (中国标准时间)"
                    class="time"
                  >
                    2020年10月31日
                  </time>
                  <span data-v-064b8453="" class="views-count">阅读 1</span>
                  <span data-v-064b8453="" class="dot">·</span>
                  <span data-v-064b8453="" class="edit-btn">编辑</span>
                </div>
              </div>
            </div>

            <h1 class="article-title">{{ blog.title }}</h1>
            <div class="article-content">
              <mavon-editor
                class="content markdown-body"
                ref="md"
                v-model="blog.content"
                defaultOpen="preview"
                :subfield="false"
                :editable="false"
                :toolbarsFlag="false"
              />
            </div>
          </article>
        </div>
        <aside></aside>
        <div class="article-support-panle"></div>
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
export default {
  name: "BlogDetail",
  components: { Header },
  data() {
    return {
      blog: {
        userId: null,
        title: "",
        description: "",
        content: ""
      },
      ownBlog: false
    };
  },
  methods: {
    getBlog() {
      const blogId = this.$route.params.blogId;
      const _this = this;
      this.$axios.get(`${APIConfig.Base.Blog}/${blogId}`).then(res => {
        // console.log(res);
        // console.log(res.data.data);
        _this.blog = res.data.data;
        var MarkdownIt = require("markdown-it"),
          md = new MarkdownIt();
        var result = md.render(_this.blog.content);

        _this.blog.content = res.data.data.content;
        // 判断是否是自己的文章，能否编辑
        _this.ownBlog = _this.blog.userId === _this.$store.getters.getUser.id;
      });
    }
  },
  created() {
    this.getBlog();
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
}
.article-view {
  margin-top: 1.767rem;
}
.column-view {
  padding: 0 0 8rem;
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
</style>
