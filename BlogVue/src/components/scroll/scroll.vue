<script>
export default {
  data() {
    return {};
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
