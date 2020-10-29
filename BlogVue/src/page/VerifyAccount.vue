<template>
  <div>
    <h1>欢迎加入</h1>
    <p>你已经验证成功，快去书写你的博客</p>
  </div>
</template>

<script>
export default {
  data() {
    return {};
  },

  mounted() {
    const _this = this;
    _this.getVerifiCode();
  },

  methods: {
    getVerifiCode() {
      const _this = this;
      let params = _this.getQueryVariable();
      let queryParam = {
        name: params[0].value,
        code: params[1].value
      };
      console.log(queryParam);
    },

    getQueryVariable() {
      var query = window.location.href.substring(
        window.location.href.lastIndexOf("?") + 1,
        window.location.href.length
      );
      var vars = query.split("&");
      let params = [];
      for (var i = 0; i < vars.length; i++) {
        var pair = vars[i].split("=");
        let obj = {
          key: pair[0],
          value: pair[1]
        };
        params.push(obj);
      }
      return params;
    }
  }
};
</script>

<style lang="scss" scoped></style>
