<!--
 * @Author: David
 * @Date: 2020-12-05 16:15:45
 * @LastEditTime: 2020-12-07 15:18:46
 * @LastEditors: David
 * @Description: 
 * @FilePath: /BlogVue/src/components/comment/commentInput.vue
 * @可以输入预定的版权声明、个性签名、空行等
-->

<template>
  <div class="input-wrap">
    <div class="form-box">
      <div class="autr-card">
        <input
          id="myInput"
          type="text"
          name=""
          class="comment-input"
          placeholder="请输入评论..."
          v-model="commentValue"
        />
      </div>
      <div class="action-box">
        <div class="submit">
          <button
            :disabled="submitBtn"
            class="submit-btn"
            @click.stop="comment()"
          >
            评论
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    isFirstComment: {
      type: Boolean,
      default: () => {
        return true;
      }
    },

    //回复信息的对象，如果是一级评论的话不需要
    replyObj: {
      type: Object,
      default: () => {
        return null;
      }
    }
  },
  data() {
    return {
      commentValue: "",
      submitBtn: true
    };
  },
  watch: {
    commentValue(newValue, oldValue) {
      if (newValue) {
        this.submitBtn = false;
      } else {
        this.submitBtn = true;
      }
    }
  },
  mounted() {},
  methods: {
    /**
     * @description: 子组件向父组件传递值
     * @Date: 2020-12-05 17:16:53
     * @Author: David
     */

    comment() {
      this.$emit("getValue", {
        commentValue: this.commentValue,
        isFirstComment: this.isFirstComment,
        replyObj: this.replyObj
      });
      this.commentValue = "";
    }
  }
};
</script>

<style lang="scss" scoped>
.input-wrap {
  width: calc(100%);
}
.form-box {
  flex: 1 1 auto;
}
.autr-card {
  font-size: 0;
  background-color: #fff;
  border-radius: 3px;
  .comment-input {
    width: calc(100%);
    position: relative;
    padding: 0.6rem 1rem;
    font-size: 1.083rem;
    line-height: 1.7;
    color: #17181a;
    outline: none;
    min-height: 1.3em;
    border: 1px solid #f1f1f1;
  }
}
.submit {
  display: flex;
  align-items: center;
  margin: 0.65rem 0 0;
}
.submit-btn {
  flex: 0 0 auto;
  margin-left: auto;
  padding: 0.5rem 1.3rem;
  font-size: 1.25rem;
  color: #fff;
  background-color: #027fff;
  border-radius: 2px;
  cursor: pointer;
  border: none;
}
.submit-btn:disabled {
  cursor: default;
  opacity: 0.4;
}
</style>
