<!--
 * @Author: David
 * @Date: 2020-11-27 17:07:48
 * @LastEditTime: 2020-12-07 16:33:24
 * @LastEditors: David
 * @Description: 评论组件
 * @FilePath: /BlogVue/src/components/comment/comment.vue
 * @可以输入预定的版权声明、个性签名、空行等
-->

<template>
  <div class="comment-box">
    <div class="comment-form">
      <div class="avatar-box">
        <div
          class="avatar"
          :style="{ '--avatar-src': `url(${userInfo.avatar})` }"
        ></div>
      </div>
      <matrix-input @getValue="inputValue"></matrix-input>
    </div>
    <div class="comment-list">
      <ul class="comment-ul">
        <li
          class="comment-li item sub-comment"
          v-for="(item, index) in commentList"
          :key="index"
        >
          <div
            class="avatar"
            :style="{ '--avatar-src': `url(${item.avatar})` }"
          ></div>
          <div class="comment-divider-line user-content-box">
            <div class="meta-box">
              <div class="user-popover-box">
                <span>{{ item.username }}</span>
              </div>
            </div>
            <div class="content">{{ item.body }}</div>
            <div class="reply-stat">
              <time :title="item.createTime">{{
                $utils.goodTime(item.createTime)
              }}</time>
              <div
                class="delete-box"
                @click="removeComment(item, index, true, index)"
                v-if="item.userId == userInfo.id"
              >
                &nbsp;·&nbsp;删除
              </div>

              <div class="action-box">
                <div
                  class="comment-action"
                  @click="showInput(index, item, true, index)"
                >
                  <i class="fa fa-commenting" aria-hidden="true"></i>
                  回复
                </div>
              </div>
            </div>
            <!-- 一级回复 -->
            <matrix-input
              :id="`father${index}`"
              @getValue="inputValue"
              :isFirstComment="false"
              :replyObj="item"
              class="matrix-input"
            ></matrix-input>

            <div class="sub-comment-list" v-if="null != item.sonComment">
              <ul>
                <li
                  class="comment-li item sub-comment"
                  v-for="(item2, index2) in item.sonComment.sonList"
                  :key="index2"
                >
                  <div
                    class="user-popaver-box avatar"
                    :style="{ '--avatar-src': `url(${item2.avatar})` }"
                  ></div>
                  <div class="comment-divider-line user-content-box">
                    <div class="meta-box">
                      <div class="user-popover-box">
                        <span
                          >{{ item2.username }}回复{{
                            item2.commentedUserName
                          }}</span
                        >
                      </div>
                    </div>
                    <div class="content">{{ item2.body }}</div>
                    <div class="reply-stat">
                      <time :title="item2.createTime">{{
                        $utils.goodTime(item2.createTime)
                      }}</time>

                      <div
                        class="delete-box"
                        @click="removeComment(item2, index2, false, index)"
                        v-if="item.userId == userInfo.id"
                      >
                        &nbsp;·&nbsp;删除
                      </div>

                      <div class="action-box">
                        <div
                          class="comment-action"
                          @click="
                            showInput(index, item, false, `${index}${index2}`)
                          "
                        >
                          <i class="fa fa-commenting" aria-hidden="true"></i>
                          回复
                        </div>
                      </div>
                    </div>
                    <!-- 次级回复 -->
                    <matrix-input
                      :id="`son${index}${index2}`"
                      @getValue="inputValue"
                      :isFirstComment="false"
                      :replyObj="item"
                      class="matrix-input"
                    ></matrix-input>
                  </div>
                </li>
              </ul>
            </div>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import matrixInput from "@/components/comment/CommentInput.vue";
export default {
  props: {
    aritcle: {
      type: Object,
      default: () => {
        return {};
      }
    }
  },
  components: {
    matrixInput
  },
  data() {
    return {
      commBtn: "false",
      userInfo: {
        id: null,
        avatar:
          "https://image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/images/5a9f48118166308daba8b6da7e466aab.jpg"
      },
      commentList: [],

      pageSize: 10, //评论列表每页的数量
      pageCount: 0, //评论的页数
      submitBtn: true,

      parentIndex: 0 //评论的父ID所在的下标
    };
  },
  beforeDestroy() {
    const _this = this;
    document.removeEventListener("click", _this.hideInput, true);
  },
  mounted() {
    const _this = this;
    if (_this.$store.getters.GET_USER) {
      _this.userInfo = _this.$store.getters.GET_USER;
    }
    _this.getCommentList();

    document.addEventListener("click", this.hideInput, true);
  },

  methods: {
    /**
     * @description: 获取组件中的值 父评论框
     * @param {*} val input组件框中返回的值
     * @return {*}
     * @Date: 2020-12-05 16:27:23
     * @Author: David
     */

    inputValue(val) {
      // console.log(val);
      this.comment(val);
    },
    /**
     * @description: 提交评论内容 在输入的时候把被评论的对象传入在接受到该函数，如果有被评论的对象就是次级评论，否则就是一级评论，然后在对此进行改装(减少请求接口的机会)
     * @param {*}
     * @return {*}
     * @Date: 2020-12-03 14:27:08
     * @Author: David
     */

    comment(val) {
      if (this.userInfo.id) {
        let params = {
          title: "咱不设置评价标题",
          body: val.commentValue,
          commentableId: this.aritcle.id,
          parentId: val.replyObj ? val.replyObj.id : 0,
          userId: this.userInfo.id,
          commentedUserId: val.replyObj ? val.replyObj.userId : null
        };
        this.$axios
          .post(APIConfig.Comment.ReleaseComment, params, {
            headers: {
              Authorization: localStorage.getItem("token")
            }
          })
          .then(res => {
            let resp = res.resp;
            let respData = res.respData;
            if (respData.code == 200) {
              let commentValue = respData.data;
              commentValue.username = this.userInfo.username;
              commentValue.avatar = this.userInfo.avatar;

              if (!val.replyObj) {
                this.commentList.unshift(commentValue);
              } else {
                commentValue.commentedUserName = val.replyObj.username;
                if (this.commentList[this.parentIndex].sonComment) {
                  this.commentList[this.parentIndex].sonComment.sonList.unshift(
                    commentValue
                  );
                } else {
                  let obj = {
                    sonList: [commentValue]
                  };
                  this.commentList[this.parentIndex].sonComment = obj;
                }
              }

              //提醒父组件对评论数进行加减
              this.$emit("changeCount", 1);
            }
          });
      } else {
        let path = this.routerCfg.options.pathById(1);
        this.$router.push(path);
      }
    },

    /**
     * @description: 获取评论列表
     * @Date: 2020-12-04 17:12:29
     * @Author: David
     */

    getCommentList() {
      let params = {
        commentableId: this.aritcle.id, //文章ID
        parentId: 0,
        pageSize: this.pageSize,
        pageCount: this.pageCount
      };
      this.$axios.post(APIConfig.Comment.GetCommentList, params).then(res => {
        let resp = res.resp;
        let respData = res.respData;
        if (respData.code == 200) {
          this.commentList = respData.data.records;
        }
        // console.log(respData);
      });
    },

    /**
     * @description: 删除评论
     * @param {Obeject} comment 删除的对象
     * @param {Number} Index 删除对象的下标
     * @param {Boolean} firstComment 删除的对象是否是一级评论
     * @param {Number} Index2 二级评论对应的一级评论的下标
     * @return {*}
     * @Date: 2020-12-05 15:45:25
     * @Author: David
     */
    removeComment(comment, index, firstComment, index2) {
      let params = {
        id: comment.id,
        commentableId: comment.commentableId,
        parentId: comment.parentId,
        userId: comment.userId
      };

      this.$axios
        .post(APIConfig.Comment.RemoveComment, params, {
          headers: {
            Authorization: localStorage.getItem("token")
          }
        })
        .then(res => {
          let resp = res.resp;
          let respData = res.respData;
          if (firstComment) {
            this.commentList.splice(index, 1);
            let count = -1;
            if (this.commentList[index].sonComment) {
              count = this.commentList[index].sonComment.sonList
                ? -this.commentList[index].sonComment.sonList.length
                : -1;
            }
            //提醒父组件对评论数进行加减
            this.$emit("changeCount", count);
          } else {
            this.commentList[index2].sonComment.sonList.splice(index, 1);
            this.$emit("changeCount", -1);
          }
        });
    },
    /**
     * @description: 点击回复按钮显示回复的input框 记录父节点的下标
     * @param {Number} index
     * @param {Object} item 评论的对象
     * @param {Boolean} isFirstComment 是否是关于一级评论
     * @param {Number} index2 查找对应的input框的
     * @return {*}
     * @Date: 2020-12-05 17:26:26
     * @Author: David
     */

    showInput(index, item, isFirstComment, index2) {
      const _this = this;
      let myInput = null;
      this.parentIndex = index;
      console.log(this.parentIndex);
      if (isFirstComment) {
        myInput = document.getElementById(`father${index2}`);
      } else {
        myInput = document.getElementById(`son${index2}`);
      }
      myInput.style.display = "unset";
    },

    /**
     * @description: 点击其他地方隐藏输入框
     * @return {*}
     * @Date: 2020-10-29 10:45:21
     * @Author: David
     */

    hideInput(event) {
      const _this = this;
      var cDom = document.querySelectorAll(".matrix-input");
      var tDom = event.target;
      for (let i = 0; i < cDom.length; i++) {
        if (cDom[i].contains(tDom)) {
          return;
        } else {
          cDom[i].style.display = "none";
        }
      }
    }
  }
};
</script>

<style lang="scss" scoped>
.comment-form {
  padding: 0 20px;
  display: flex;
}

.avatar {
  margin: 0 1rem 0 0;
  width: 2.667rem;
  height: 2.667rem;
  border-radius: 50%;
  background-image: var(--avatar-src);
  background-size: contain;
}
.action-box {
  display: flex;
  align-items: center;
  justify-content: flex-end;
  flex: 1 1 auto;
}
.comment-action {
  cursor: pointer;
}

.comment-list {
  margin: 0 1.666rem 0 6.5rem;
}
.comment-li {
  display: flex;
}
.comment-divider-line {
  flex: 1 1 auto;
}
.comment-li:not(:last-child) {
  border-bottom: 1px solid #f1f1f1;
}
.meta-box {
  display: flex;
  align-items: center;
  font-size: 1.083rem;
  line-height: 1.25;
  white-space: nowrap;
}

.sub-comment-list {
  margin: 1rem 0;
  padding: 0 0 0 1rem;
  background-color: #fafbfc;
  border-radius: 3px;
}
.sub-comment {
  position: relative;
  padding: 1rem 0 0;
}
.content {
  margin-top: 0.55rem;
  font-size: 1.083rem;
  line-height: 1.833rem;
  word-wrap: break-word;
  white-space: pre-wrap;
  color: #505050;
  overflow: hidden;
  text-align: left;
}
.reply-stat {
  display: flex;
  margin-top: 7px;
  font-weight: 400;
  margin-bottom: 1rem;
}

.user-content-box {
  margin-left: 0.833rem;
  margin-right: 1rem;
  padding-bottom: 12px;
  font-size: 1.083rem;
  color: #17181a;
  width: calc(100% - 33px);
}
.delete-box {
  cursor: pointer;
  color: red;
}
.matrix-input {
  display: none;
}
</style>
