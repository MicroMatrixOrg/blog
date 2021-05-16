<!--
 * @Author: David
 * @Date: 2020-10-27 21:51:10
 * @LastEditTime: 2021-05-16 15:17:40
 * @LastEditors: David
 * @Description: 搜索输入框
 * @FilePath: /BlogVue/src/components/input/Input.vue
 * @可以输入预定的版权声明、个性签名、空行等
-->

<template>
  <div class="input-theme-default">
    <form role="search" class="search-form">
      <input
        type="search"
        placeholder="搜索文章名"
        v-model="keyword"
        class="search-input"
      />
      <i class="fa fa-search" @click="searchBlogByKeyword()"></i>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      keyword: ""
    };
  },
  methods: {
    /**
     * @description: 根据关键字搜索博客
     * @param {*}
     * @return {*}
     * @Date: 2021-05-16 15:03:00
     * @Author: David
     */

    searchBlogByKeyword() {
      this.$emit("matrix-input-value", this.keyword);
    }
  }
};
</script>

<style lang="scss" scoped>
.search-form {
  border: 1px solid hsla(0, 0%, 59.2%, 0.2);
  background-color: rgba(227, 231, 236, 0.2);
  display: flex;
  align-items: center;
  justify-content: space-between;
  border-radius: 2px;

  > i {
    padding: 0 0.5rem;
    cursor: pointer;
  }
}

.input-theme-default input[type="search"] {
  border: none;
  width: 12rem;
  padding: 0.6rem 0 0.6rem 1rem;
  box-shadow: none;
  outline: none;
  font-size: 1.1rem;
  color: #666;
  background-color: transparent;
}

@media (max-width: 570px) {
  //手机大小的屏幕
  .input-theme-default input[type="search"] {
    border: none;
    width: 10rem;
    padding: 0.6rem 0 0.6rem 1rem;
    box-shadow: none;
    outline: none;
    font-size: 1.1rem;
    color: #666;
    background-color: transparent;
  }
}
</style>
