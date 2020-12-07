<!--
 * @Author: David
 * @Date: 2020-11-27 17:39:40
 * @LastEditTime: 2020-12-01 14:47:31
 * @LastEditors: David
 * @Description: 解析mavon-editor的文章目录
 * @FilePath: /BlogVue/src/components/article/menu.vue
 * @可以输入预定的版权声明、个性签名、空行等
-->

<template>
  <div class="menu-box">
    文章内容
  </div>
</template>

<script>
export default {
  props: {
    article: {
      type: [String, Object],
      defautl: () => {
        return null;
      }
    }
  },
  data() {
    return {
      menuList: [] //文章列表标题数组
    };
  },
  watch: {
    article(newValue, oldValue) {
      this.article = newValue;
      this.generation(newValue);
    }
  },
  mounted() {},
  methods: {
    /**
     * @description: 分解html格式成
     * @param {*} val
     * @return {*}
     * @Date: 2020-11-30 17:56:19
     * @Author: David
     */

    generation(val) {
      console.log(val);
    }
  }
};
</script>

<style lang="scss" scoped></style>
