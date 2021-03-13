<!--
 * @Author: David
 * @Date: 2020-11-09 16:37:21
 * @LastEditTime: 2021-03-12 13:14:21
 * @LastEditors: David
 * @Description: 无限加载
 * @FilePath: /BlogVue/src/components/scroll/scroll.vue
 * 可以输入预定的版权声明、个性签名、空行等
-->

<script>
export default {
  data() {
    return {};
  },
  directives: {
    /**
     * @description: 自定义滚动加载指令 将该指令写在产生滚动条的div上
     * @Date: 2021-03-04 15:03:40
     * @Author: David
     */
    matrixScroll: {
      bind: (el, binding, vnode) => {
        vnode.elm.addEventListener("scroll", () => {
          if (el.scrollTop + el.clientHeight >= el.scrollHeight) {
            let fnc = binding.value;
            fnc();
          }
        });
      }
    }
  },
  methods: {
    /**
     * @description: 全局计算距离底部多少的时候加载数据
     * @param {Boolean} params
     * @Date: 2020-11-04 09:50:47
     * @Author: David
     */

    calcHeight(params) {
      let scrollHeight = document.documentElement.scrollHeight;
      let scrollTop = document.documentElement.scrollTop;
      let clientHeight = document.documentElement.clientHeight;

      if (scrollHeight - scrollTop - 60 <= clientHeight && params) {
        return true;
      } else {
        return false;
      }
    }
  }
};
</script>
