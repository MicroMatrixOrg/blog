<!--
 * @Author: David
 * @Date: 2020-11-23 13:20:31
 * @LastEditTime: 2021-03-04 21:41:59
 * @LastEditors: David
 * @Description: 登录页面
 * @FilePath: /BlogVue/src/components/login_modal/LoginForm.vue
 * @可以输入预定的版权声明、个性签名、空行等
-->

<template>
  <div class="container">
    <div class="forms-container">
      <div class="signin-signup">
        <form onsubmit="return false" class="sign-in-form" name="loginForm">
          <h2 class="title">登录</h2>
          <div class="input-field">
            <i class="fas fa-envelope"></i>
            <input
              type="email"
              name="loginEmail"
              placeholder="电子邮箱"
              required
              pattern=".+@[qq|gmail|outlook|163|protonmail]+(\.[a-zA-Z0-9_-]+)+$"
              v-model="loginAccount.username"
              autocomplete="on"
            />
          </div>
          <div class="input-field">
            <i class="fas fa-lock"></i>
            <input
              type="password"
              placeholder="密码"
              v-model="loginAccount.password"
              autocomplete="on"
            />
            <div class="eye-wrap">
              <i
                class="fa fa-eye show"
                aria-hidden="true"
                @click="showpassword($event)"
              ></i>
              <i
                class="fa fa-eye-slash hide"
                aria-hidden="true"
                @click="showpassword($event)"
              ></i>
            </div>
          </div>
          <button class="btn solid" @click="login()">登录</button>
          <!-- <p class="social-text">Or Sign in with social platforms</p>
          <div class="social-media">
            <a href="#" class="social-icon">
              <i class="fab fa-facebook-f"></i>
            </a>
            <a href="#" class="social-icon">
              <i class="fab fa-twitter"></i>
            </a>
            <a href="#" class="social-icon">
              <i class="fab fa-google"></i>
            </a>
            <a href="#" class="social-icon">
              <i class="fab fa-linkedin-in"></i>
            </a>
          </div> -->
        </form>
        <form onsubmit="return false" class="sign-up-form" name="registerForm">
          <h2 class="title">
            暂时不接受注册,如有需要请联系davidmorgancreate@gmail.com
          </h2>
          <!-- <div class="input-field">
            <i class="fas fa-user"></i>
            <input
              type="text"
              placeholder="用户名"
              v-model="signUpAccount.username"
              disabled
            />
          </div>
          <div class="input-field">
            <i class="fas fa-envelope"></i>
            <input
              type="email"
              name="registerEmail"
              v-model="signUpAccount.email"
              placeholder="电子邮箱(qq/gmail/outlook/163)"
              required
              pattern=".+@[qq|gmail|outlook|163|protonmail]+(\.[a-zA-Z0-9_-]+)+$"
              disabled
            />
          </div>
          <div class="input-field">
            <i class="fas fa-lock"></i>
            <input
              type="password"
              placeholder="密码"
              v-model="signUpAccount.password"
              disabled
            />
            <div class="eye-wrap">
              <i
                class="fa fa-eye show"
                aria-hidden="true"
                @click="showpassword($event)"
              ></i>
              <i
                class="fa fa-eye-slash hide"
                aria-hidden="true"
                @click="showpassword($event)"
              ></i>
            </div>
          </div>
          <button class="btn" @click="register()" disabled>注册</button> -->
        </form>
      </div>
    </div>
    <slot></slot>
    <div class="panels-container">
      <div class="panel left-panel">
        <div class="content">
          <h3>没有帐号?</h3>
          <p>
            立即注册加入我们，和我们一起开始旅程吧
          </p>
          <button class="btn transparent" id="sign-up-btn">
            注册
          </button>
        </div>
        <img src="static/image/log.svg" class="image" alt="" />
        <!-- <embed
          src="../../../static/image/log.svg"
          class="image"
          type="image/svg+xml"
        /> -->
      </div>
      <div class="panel right-panel">
        <div class="content">
          <h3>已经有账号了 ?</h3>
          <p>
            请使用您的帐号进行登录
          </p>
          <button class="btn transparent" id="sign-in-btn">
            登录
          </button>
        </div>
        <img src="static/image/register.svg" class="image" alt="" />
        <!-- <embed
          src="../../../static/image/register.svg"
          class="image"
          type="image/svg+xml"
        /> -->
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      loginOrRegister: false,

      //账号登录
      loginAccount: {
        username: "",
        password: ""
      },

      //账号注册
      signUpAccount: {
        username: "",
        email: "",
        password: ""
      }
    };
  },
  mounted() {
    this.switch();
  },
  methods: {
    /**
     * @description: 注册
     * @Date: 2020-08-08 10:57:54
     * @Author: David
     */

    register() {
      const _this = this;
      // console.log(_this.form);
      let valid = _this.validateEmail("registerForm", "registerEmail");
      if (valid) {
        this.$axios
          .post(APIConfig.Base.Register, _this.signUpAccount)
          .then(res => {
            let resp = res.resp;
            let respData = res.respData;
            console.log(respData);
            if (respData.code == 200) {
              _this.dialogVisible = false;
              _this.sendHtmlMail();
            }
          });
      } else {
        console.log("注册失败");
        return false;
      }
    },

    /**
     * @description: 注册成功 发送邮件至注册邮箱
     * @Date: 2020-10-30 13:41:19
     * @Author: David
     */
    sendHtmlMail() {
      const _this = this;
      let params = {
        username: _this.signUpAccount.username,
        toMail: _this.signUpAccount.email,
        subject: "注册"
      };
      _this.$axios
        .post(APIConfig.Email.SendHtmlMail, params)
        .then(res => {
          let resp = res.resp;
          let respData = res.respData;
          if (respData.code == 200) {
            //发送注册邮件成功
            alert("感谢你的注册,请检查你的邮箱,激活你的账户");
          } else {
            alert("Opps!,发生了一些小问题，邮件没有发送成功,请稍后再试");
          }
        })
        .then(() => {
          _this.signUpAccount = {
            username: "",
            email: "",
            password: ""
          };
        });
    },

    /**
     * @description: 校验Email邮箱
     * @Date: 2020-10-29 10:02:21
     * @Author: David
     */

    validateEmail(formname, inputname) {
      const _this = this;
      const x = document.forms[formname][inputname].value;
      let patt = /.+@[qq|gmail|outlook|163|protonmail]+(\.[a-zA-Z0-9_-]+)+$/;
      if (!patt.test(x)) {
        alert("仅支持163/outlook/gmail/qq邮箱");
        return false;
      }
      return patt.test(x);
    },

    /**
     * @description: 登录
     * @Date: 2020-10-28 20:45:10
     * @Author: David
     */
    login() {
      const _this = this;
      let valid = _this.validateEmail("loginForm", "loginEmail");
      if (valid) {
        this.$axios.post(APIConfig.Base.Login, this.loginAccount).then(res => {
          let resp = res.resp;
          let respData = res.respData;
          if (respData.code == 200) {
            const token = resp.headers["authorization"];
            _this.$store.commit("SET_TOKEN", token);
            _this.$store.commit("SET_USERINFO", respData.data);
            let redirctPath = _this.$route.fullPath;
            if (redirctPath.lastIndexOf("redirct=") > -1) {
              redirctPath = redirctPath.substring(
                redirctPath.lastIndexOf("redirct=") + 8,
                redirctPath.length - 1
              );
              window.location.href = _this.$utils.replaceStr(redirctPath);
            } else {
              let path = this.routerCfg.options.pathById(2);
              this.$router.push(path);
            }
          }
        });
      } else {
        console.log("error submit!!");
        return false;
      }
    },

    /**
     * @description: 注册表单切换动画
     * @Date: 2020-10-28 16:57:13
     * @Author: David
     */

    switch() {
      const sign_in_btn = document.querySelector("#sign-in-btn");
      const sign_up_btn = document.querySelector("#sign-up-btn");
      const container = document.querySelector(".container");

      sign_up_btn.addEventListener("click", () => {
        container.classList.add("sign-up-mode");
      });

      sign_in_btn.addEventListener("click", () => {
        container.classList.remove("sign-up-mode");
      });
    },

    /**
     * @description: 显示或者关闭密码 非常依赖dom的结构
     * @param {Object} e
     * @Date: 2020-10-28 17:43:56
     * @Author: David
     */

    showpassword(e) {
      let parent = e.target.parentNode;
      let children = parent.children;
      if (parent.previousElementSibling.type == "password") {
        parent.previousElementSibling.type = "text";
      } else {
        parent.previousElementSibling.type = "password";
      }

      children[0].classList.toggle("show");
      children[0].classList.toggle("hide");

      children[1].classList.toggle("show");
      children[1].classList.toggle("hide");
      // $(e.target).hide();
      // $(e.target.nextElementSibling).show();
    }
  }
};
</script>

<style lang="scss" scoped>
.show {
  display: unset;
}
.hide {
  display: none;
}

.container {
  position: relative;
  width: 100%;
  background-color: #fff;
  min-height: 100vh;
  overflow: hidden;
}

.forms-container {
  position: absolute;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
}

.signin-signup {
  position: absolute;
  top: 50%;
  transform: translate(-50%, -50%);
  left: 75%;
  width: 50%;
  transition: 1s 0.7s ease-in-out;
  display: grid;
  grid-template-columns: 1fr;
  z-index: 5;
}

form {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  padding: 0rem 5rem;
  transition: all 0.2s 0.7s;
  overflow: hidden;
  grid-column: 1 / 2;
  grid-row: 1 / 2;
}

form.sign-up-form {
  opacity: 0;
  z-index: 1;
}

form.sign-in-form {
  z-index: 2;
}

.title {
  font-size: 2.2rem;
  color: #444;
  margin-bottom: 10px;
}

.input-field {
  max-width: 380px;
  width: 100%;
  background-color: #f0f0f0;
  margin: 10px 0;
  height: 55px;
  border-radius: 55px;
  display: grid;
  grid-template-columns: 15% 70% 15%;
  padding: 0 0.4rem;
  position: relative;
}

.input-field i {
  text-align: center;
  line-height: 55px;
  color: #acacac;
  transition: 0.5s;
  font-size: 1.1rem;
}

.input-field input {
  background: none;
  outline: none;
  border: none;
  line-height: 1;
  font-weight: 600;
  font-size: 1.1rem;
  color: #333;
}

.input-field input::placeholder {
  color: #aaa;
  font-weight: 500;
}

.social-text {
  padding: 0.7rem 0;
  font-size: 1rem;
}

.social-media {
  display: flex;
  justify-content: center;
}

.social-icon {
  height: 46px;
  width: 46px;
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 0 0.45rem;
  color: #333;
  border-radius: 50%;
  border: 1px solid #333;
  text-decoration: none;
  font-size: 1.1rem;
  transition: 0.3s;
}

.social-icon:hover {
  color: #4481eb;
  border-color: #4481eb;
}

.btn {
  width: 150px;
  background-color: #5995fd;
  border: none;
  outline: none;
  height: 49px;
  border-radius: 49px;
  color: #fff;
  text-transform: uppercase;
  font-weight: 600;
  margin: 10px 0;
  cursor: pointer;
  transition: 0.5s;
}

.btn:hover {
  background-color: #4d84e2;
}
.panels-container {
  position: absolute;
  height: 100%;
  width: 100%;
  top: 0;
  left: 0;
  display: grid;
  grid-template-columns: repeat(2, 1fr);
}

.container:before {
  content: "";
  position: absolute;
  height: 2000px;
  width: 2000px;
  top: -10%;
  right: 48%;
  transform: translateY(-50%);
  background-image: linear-gradient(-45deg, #4481eb 0%, #04befe 100%);
  transition: 1.8s ease-in-out;
  border-radius: 50%;
  z-index: 6;
}

.image {
  width: 100%;
  height: 80%;
  transition: transform 1.1s ease-in-out;
  transition-delay: 0.4s;
}

.panel {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-around;
  text-align: center;
  z-index: 6;
}

.left-panel {
  pointer-events: all;
  padding: 3rem 17% 2rem 12%;
}

.right-panel {
  pointer-events: none;
  padding: 3rem 12% 2rem 17%;
}

.panel .content {
  color: #fff;
  transition: transform 0.9s ease-in-out;
  transition-delay: 0.6s;
}

.panel h3 {
  font-weight: 600;
  line-height: 1;
  font-size: 1.5rem;
}

.panel p {
  font-size: 0.95rem;
  padding: 0.7rem 0;
}

.btn.transparent {
  margin: 0;
  background: none;
  border: 2px solid #fff;
  width: 130px;
  height: 41px;
  font-weight: 600;
  font-size: 0.8rem;
}

.right-panel .image,
.right-panel .content {
  transform: translateX(800px);
}

/* ANIMATION */

.container.sign-up-mode:before {
  transform: translate(100%, -50%);
  right: 52%;
}

.container.sign-up-mode .left-panel .image,
.container.sign-up-mode .left-panel .content {
  transform: translateX(-800px);
}

.container.sign-up-mode .signin-signup {
  left: 25%;
}

.container.sign-up-mode form.sign-up-form {
  opacity: 1;
  z-index: 2;
}

.container.sign-up-mode form.sign-in-form {
  opacity: 0;
  z-index: 1;
}

.container.sign-up-mode .right-panel .image,
.container.sign-up-mode .right-panel .content {
  transform: translateX(0%);
}

.container.sign-up-mode .left-panel {
  pointer-events: none;
}

.container.sign-up-mode .right-panel {
  pointer-events: all;
}

@media (max-width: 870px) {
  .container {
    // min-height: 800px;
    height: 100vh;
  }
  .signin-signup {
    width: 100%;
    top: 95%;
    transform: translate(-50%, -100%);
    transition: 1s 0.8s ease-in-out;
  }

  .signin-signup,
  .container.sign-up-mode .signin-signup {
    left: 50%;
  }

  .panels-container {
    grid-template-columns: 1fr;
    grid-template-rows: 1fr 2fr 1fr;
  }

  .panel {
    flex-direction: row;
    justify-content: space-around;
    align-items: center;
    padding: 2.5rem 8%;
    grid-column: 1 / 2;
  }

  .right-panel {
    grid-row: 3 / 4;
  }

  .left-panel {
    grid-row: 1 / 2;
  }

  .image {
    width: 200px;
    transition: transform 0.9s ease-in-out;
    transition-delay: 0.6s;
  }

  .panel .content {
    padding-right: 15%;
    transition: transform 0.9s ease-in-out;
    transition-delay: 0.8s;
  }

  .panel h3 {
    font-size: 1.2rem;
  }

  .panel p {
    font-size: 0.7rem;
    padding: 0.5rem 0;
  }

  .btn.transparent {
    width: 110px;
    height: 35px;
    font-size: 0.7rem;
  }

  .container:before {
    width: 1500px;
    height: 1500px;
    transform: translateX(-50%);
    left: 30%;
    bottom: 68%;
    right: initial;
    top: initial;
    transition: 2s ease-in-out;
  }

  .container.sign-up-mode:before {
    transform: translate(-50%, 100%);
    bottom: 32%;
    right: initial;
  }

  .container.sign-up-mode .left-panel .image,
  .container.sign-up-mode .left-panel .content {
    transform: translateY(-300px);
  }

  .container.sign-up-mode .right-panel .image,
  .container.sign-up-mode .right-panel .content {
    transform: translateY(0px);
  }

  .right-panel .image,
  .right-panel .content {
    transform: translateY(300px);
  }

  .container.sign-up-mode .signin-signup {
    top: 5%;
    transform: translate(-50%, 0);
  }
}

@media (max-width: 570px) {
  form {
    padding: 0 1.5rem;
  }

  .image {
    display: none;
  }
  .panel .content {
    padding: 0.5rem 1rem;
  }
  .container {
    padding: 1.5rem;
  }

  .container:before {
    bottom: 72%;
    left: 50%;
  }

  .container.sign-up-mode:before {
    bottom: 28%;
    left: 50%;
  }
}
</style>
