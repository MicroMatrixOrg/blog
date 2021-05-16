<!--
 * @Author: David
 * @Date: 2020-11-07 10:05:11
 * @LastEditTime: 2021-05-15 10:01:28
 * @LastEditors: David
 * @Description: 用户自己的文章列表界面
 * @FilePath: /BlogVue/src/components/userInfoComponents/Article.vue
 * 可以输入预定的版权声明、个性签名、空行等
-->

<template>
  <div class="articles-list" v-cloak>
    <div class="entry-list-wrap" v-show="requested">
      <ul class="entry-list">
        <div
          class="entry-item-wrap"
          v-for="(item, index) in blogList"
          :key="index"
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
                <li
                  class="comment-btn"
                  @click.stop="openDetail(item.id, item.isVote)"
                >
                  <a class="title-box">
                    <i class="fa fa-commenting" aria-hidden="true"></i>
                    <span>{{ item.commentCount }}</span>
                  </a>
                </li>
                <li
                  class="share-btn"
                  title="share"
                  v-popover="`sharePop${item.id}`"
                >
                  <a class="title-box">
                    <i class="fa fa-share-alt" aria-hidden="true"></i>
                  </a>
                </li>
                <li class="ellipsish-btn" v-popover="`morePop${item.id}`">
                  <a class="title-box">
                    <i class="fa fa-ellipsis-h" aria-hidden="true"></i>
                  </a>
                </li>
              </ul>
            </div>
          </li>
          <!-- 文章更多选项的弹框 -->
          <el-popover
            :ref="`morePop${item.id}`"
            placement="bottom-start"
            trigger="click"
          >
            <ul id="more-menu-list-display" class="more-class">
              <div class="more-menu-item-group">
                <li class="more-menu-item">
                  <a @click="editBlog(item.id)">
                    <i class="fa fa-pencil icon" aria-hidden="true"></i>
                    <span>编辑</span>
                  </a>
                </li>
              </div>
              <div class="more-menu-item-group">
                <li class="more-menu-item">
                  <a @click="deleBlog(item.id)">
                    <i class="fa fa-user-secret icon" aria-hidden="true"></i>
                    <span>删除</span>
                  </a>
                </li>
              </div>
            </ul>
          </el-popover>
          <!-- 分享弹框 -->
          <el-popover
            :ref="`sharePop${item.id}`"
            placement="bottom-start"
            trigger="click"
            @show="setQRcode(item)"
            transfer
          >
            <div :id="`qrcode${item.id}`" class="qrcode"></div>
          </el-popover>
        </div>

        <div class="entry-item-wrap" v-show="blogList.length <= 0">
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
                <skeleton-square width="400px" height="23px"></skeleton-square>
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
      this.getUserAritcles();
    }
  },
  mixins: [myScroll],
  directives: {},

  data() {
    return {
      pageSize: 10, //每页的数量
      currentPage: 1, //当前页数
      totalPage: 0,

      blogList: [], //博客列表

      //用户信息
      userInfo: {
        username: "",
        avatar: "",
        id: 0
      },

      requested: false, //文章请求完成标志位
      moreOption: false, //更多按钮标志位
      shareOption: false
    };
  },
  components: {},
  destroyed() {
    const _this = this;
    window.removeEventListener("scroll", _this.loadMore);
  },

  mounted() {
    const _this = this;
    window.addEventListener("scroll", _this.loadMore, true);
  },
  methods: {
    /**
     * @description: 设置二维码
     * @param {*} item
     * @return {*}
     * @Date: 2021-04-04 11:11:00
     * @Author: David
     */
    setQRcode(item) {
      let qDom = document.getElementById(`qrcode${item.id}`);
      qDom.innerHTML = "";
      let qrcode = new QRCode(qDom, {
        text: "",
        width: 296, //二维码宽度,单位像素
        height: 296, //二维码高度,单位像素
        colorDark: "black",
        colorLoght: "transparent",
        correctLevel: QRCode.CorrectLevel.H
      });
      qrcode.clear();
      let content = `blog.matrixs.gq/blogDetails?blogId=${item.id}&isVoted`;
      qrcode.makeCode(content);
      // this.listenQrcodeSrc(qrcodeImg);
    },
    /**
     * @description: 监听二维码生成了吗
     * @param {*} qrcodeImg
     * @return {*}
     * @Date: 2021-04-04 12:52:51
     * @Author: David
     */

    listenQrcodeSrc(qrcodeImg) {
      let observeConfig = { attributes: true };
      let observeCb = function(mutationsList, observer) {
        mutationsList.forEach(function(mutation) {
          if (
            mutation.type.toLowerCase() === "attributes" &&
            mutation.attributeName.toLowerCase() === "src"
          ) {
            console.log("qrcodeImg src done!", mutation.target.src);
            // qrcodeImg.setAttribute("src", mutation.target.src);
            observer.disconnect();
          }
        });
      };
      if (typeof MutationObserver !== "undefined") {
        let observer = new MutationObserver(observeCb);
        observer.observe(qrcodeImg, observeConfig);
      }
    },

    /**
     * @description: 删除博客
     * @param {*} id 文章ID
     * @return {*}
     * @Date: 2021-03-07 13:07:12
     * @Author: David
     */

    deleBlog(id) {
      const _this = this;
      let params = {
        id: id
      };
      console.log(this.$store.getters.GET_TOKEN);
      this.$axios
        .post(APIConfig.Base.DeleteBlog, params, {
          headers: {
            Authorization: this.$store.getters.GET_TOKEN
          }
        })
        .then(res => {
          let resp = res.resp;
          let respData = res.respData;
          if (respData.code == 200) {
            this.currentPage = 1;
            this.blogList = [];
            this.getUserAritcles();
          }
          _this.$alert("操作成功", "提示", {
            confirmButtonText: "确定"
          });
        });
    },
    /**
     * @description: 编辑博客
     * @param {*} id 文章ID
     * @return {*}
     * @Date: 2021-03-07 12:25:41
     * @Author: David
     */

    editBlog(id) {
      let path = this.routerCfg.options.pathById(4);
      this.$router.push({ path, query: { blogId: id } });
    },

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
        console.log("dd");
        this.getUserAritcles();
      }
    },
    /**
     * @description: 打开详情野页面
     * @param {number} id
     * @param {[null boolean]} isVote
     * @return {*}
     * @Date: 2020-11-09 17:05:15
     * @Author: David
     */

    openDetail(id, isVoted) {
      const _this = this;
      let path = this.routerCfg.options.pathById(22);
      this.$router.push({ path, query: { blogId: id, isVoted: isVoted } });
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

    getUserAritcles() {
      const _this = this;
      // _this.requested = false;
      let params = {
        userId: _this.userId,
        currentPage: this.currentPage,
        pageSize: this.pageSize
      };
      this.$axios
        .post(APIConfig.Base.BlogsByUserId, params, {
          headers: {
            Authorization: this.$store.getters.GET_TOKEN
          }
        })
        .then(res => {
          let resp = res.resp;
          let respData = res.respData;
          _this.blogList = [..._this.blogList, ...respData.data.records];
          _this.totalPage = respData.data.pages;
          _this.requested = true;
        });
    }
  }
};
</script>

<style lang="scss" scoped>
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
  .share-btn {
    /deep/ .ivu-poptip-popper {
      min-width: 8rem;
    }
  }
  .ellipsish-btn {
    position: relative;
    /deep/ .ivu-poptip-popper {
      min-width: 8rem;
    }
  }
}
.more-class {
  min-width: 8rem;
  font-size: 0.9em;
  white-space: nowrap;
  color: #8f969c;
  background-color: #fff;

  .more-menu-item-group {
    padding: 0.8rem 0.9rem;
    text-align: start;
  }
}
.qrcode {
  /deep/ img {
    width: 8rem;
    object-fit: contain;
    margin: 0 auto;
  }
}
</style>
