<template>
  <div id="app">
    <router-view />

    <div class="suspension-panel">
      <button
        v-show="isShowUp"
        title="回到顶部"
        class="btn to-top-btn"
        @click="upTop()"
      >
        <i class="fa fa-angle-up" aria-hidden="true"></i>
      </button>
      <!-- <button title="建议反馈" class="btn meiqia-btn" style="">
        <i class="fa fa-comments" aria-hidden="true"></i>
      </button> -->
    </div>
  </div>
</template>

<script>
export default {
  name: "App",
  data() {
    return {
      isShowUp: false
    };
  },
  mounted() {
    this.scrollTop();
  },
  methods: {
    /**
     * @description: 监听纵向滚动
     * @Date: 2020-11-02 15:11:28
     * @Author: David
     */

    scrollTop() {
      const _this = this;
      window.addEventListener("scroll", e => {
        if (window.scrollY > 320) {
          _this.isShowUp = true;
        } else {
          _this.isShowUp = false;
        }
      });
    },
    /**
     * @description: 滚动到顶部
     * @Date: 2020-11-02 16:02:40
     * @Author: David
     */

    upTop() {
      window.scrollTo({
        left: 0,
        top: 0,
        behavior: "smooth"
      });
    }
  }
};
</script>

<style lang="scss" scoped>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

.suspension-panel {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-end;
  position: fixed;
  right: 2rem;
  bottom: 2rem;
  z-index: 1000;

  > button {
    -webkit-appearance: none;
    -moz-appearance: none;
    appearance: none;
    background-color: #007fff;
    color: #fff;
    border-radius: 2px;
    border: none;
    outline: none;
    transition: background-color 0.3s, color 0.3s;
    cursor: pointer;

    border-radius: 50%;
  }
}

.btn {
  margin: 1rem 0 0;
  padding: 0;
  width: 3.33rem;
  height: 3.33rem;
  line-height: 1;
  color: #909090;
  background-color: #fff;
  border: 1px solid #f1f1f1;
  border-radius: 50%;
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.05);
  cursor: pointer;
}

.to-top-btn {
  font-size: 1.6rem;
}
.meiqia-btn {
  font-size: 1.3rem;
  color: #007fff;
}
</style>
