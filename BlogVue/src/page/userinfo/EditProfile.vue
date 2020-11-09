<template>
  <div class="profile-wrap">
    <header class="profile-header">
      <ul>
        <li class="back-my-home-page nav-item" @click="backHomePage()">
          <i class="fa fa-chevron-left" aria-hidden="true"></i>&nbsp;
          <span>返回个人主页</span>
        </li>
        <li class="nav-item active" @click="editProfile($event)">个人资料</li>
        <li class="nav-item" @click="editAccount($event)">账号设置</li>
      </ul>
    </header>
    <main class="view-content setting-view">
      <div class="profile-content shadow" v-show="settingFlag == 1">
        <h1>个人资料</h1>
        <ul>
          <li class="item"><span class="title">头像</span></li>
          <li class="item"><span class="title">用户名</span></li>
          <li class="item"><span class="title">个人介绍</span></li>
        </ul>
      </div>
      <div class="account-setting shadow" v-show="settingFlag == 2">
        <h1>账号设置</h1>
        <ul>
          <li class="item"><span class="title">邮箱</span></li>
          <li class="item"><span class="title">密码</span></li>
          <li class="item"><span class="title">账号注销</span></li>
        </ul>
      </div>
    </main>
  </div>
</template>

<script>
export default {
  data() {
    return {
      settingFlag: 1
    };
  },
  methods: {
    /**
     * @description: 返回个人主页
     * @Date: 2020-11-07 14:48:53
     * @Author: David
     */

    backHomePage() {
      this.$router.go(-1);
    },
    /**
     * @description: 编辑个人资料
     * @param {Object} e 鼠标对象
     * @Date: 2020-11-07 14:46:33
     * @Author: David
     */

    editProfile(e) {
      const _this = this;

      this.settingFlag = 1;

      let aList = document.getElementsByClassName("nav-item");
      for (let i = 0; i < aList.length; i++) {
        aList[i].classList.remove("active");
      }
      if (!e.target.classList.contains("active")) {
        e.target.classList.add("active");
      }
    },

    /**
     * @description: 账号设置
     * @param {Object} e
     * @return {*}
     * @Date: 2020-11-07 14:47:37
     * @Author: David
     */

    editAccount(e) {
      const _this = this;

      this.settingFlag = 2;

      let aList = document.getElementsByClassName("nav-item");
      for (let i = 0; i < aList.length; i++) {
        aList[i].classList.remove("active");
      }
      if (!e.target.classList.contains("active")) {
        e.target.classList.add("active");
      }
    }
  }
};
</script>

<style lang="scss" scoped>
.profile-wrap {
  position: relative;
  margin: 0 auto;
  width: 100%;
  max-width: 960px;
}
.active,
.nav-item:hover {
  color: #007fff !important;
}
.profile-header {
  position: fixed;
  top: 5rem;
  left: 0;
  width: calc(100%);
  box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.05);
  z-index: 100;
  background-color: #fff;
  > ul {
    display: flex;
    max-width: 960px;
    margin: auto;

    width: 100%;
    height: 3.833rem;

    transition: all 0.2s;
    transform: translateZ(0);
  }
}
.nav-item {
  display: flex;
  align-items: center;
  height: 100%;
  flex-shrink: 0;
  font-size: 1.16rem;
  line-height: 1.16rem;
  color: #71777c;
  padding: 0 1rem;
  cursor: pointer;
}
.view-content {
  margin-top: 4.67rem;
}
.setting-view {
  margin-bottom: 2rem;
  width: 58rem;
  max-width: 100%;
}
.profile-content,
.account-setting {
  position: relative;
  padding: 2.7rem 4rem 7rem;
  background-color: #fff;
  border-radius: 2px;
  text-align: left;

  > h1 {
    font-size: 2em;
    margin: 0.67em 0;
  }
}
.shadow {
  box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.05);
}

.item:first-child {
  padding: 1rem 0;
}
.item:last-child {
  border-bottom: 1px solid #f1f1f1;
}
.item {
  display: flex;
  align-items: center;
  padding: 2rem 0;
  border-top: 1px solid #f1f1f1;
}
.title {
  font-size: 1.2rem;
  color: #333;
  width: 10rem;
}
</style>
