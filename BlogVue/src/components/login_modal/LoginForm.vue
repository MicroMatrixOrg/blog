<template>
  <div class="dowebok" id="dowebok">
    <div class="form-container sign-up-container">
      <form onsubmit="return false" name="registerForm">
        <h1>注册</h1>
        <!-- <div class="social-container">
          <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
          <a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
          <a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
        </div> -->
        <!-- <span>或使用邮箱注册</span> -->
        <div class="form-group">
          <input
            type="text"
            placeholder="用户名"
            v-model="signUpAccount.username"
          />
        </div>

        <div class="form-group">
          <input
            type="email"
            name="registerEmail"
            v-model="signUpAccount.email"
            placeholder="电子邮箱(qq/gmail/outlook/163)"
            required
            pattern=".+@[qq|gmail|outlook|163|protonmail]+(\.[a-zA-Z0-9_-]+)+$"
          />
        </div>

        <div class="form-group">
          <input
            type="password"
            placeholder="密码"
            v-model="signUpAccount.password"
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
        <!-- <div class="form-group">
          <input type="text" placeholder="验证码" />
        </div> -->
        <button @click="register()">注册</button>
      </form>
    </div>
    <div class="form-container sign-in-container">
      <form onsubmit="return false" name="loginForm">
        <h1>登录</h1>
        <!-- <div class="social-container">
          <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
          <a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
          <a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
        </div> -->
        <!-- <span>或使用您的帐号</span> -->
        <div class="form-group">
          <input
            type="email"
            name="loginEmail"
            placeholder="电子邮箱"
            required
            pattern=".+@[qq|gmail|outlook|163|protonmail]+(\.[a-zA-Z0-9_-]+)+$"
            v-model="loginAccount.username"
          />
        </div>
        <div class="form-group">
          <input
            type="password"
            placeholder="密码"
            v-model="loginAccount.password"
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
        <!-- <a href="#">忘记密码？</a> -->
        <br />
        <button @click="login()">登录</button>
      </form>
    </div>
    <div class="overlay-container">
      <div class="overlay">
        <div class="overlay-panel overlay-left">
          <h1>已有帐号？</h1>
          <p>请使用您的帐号进行登录</p>
          <button class="ghost" id="signIn">登录</button>
        </div>
        <div class="overlay-panel overlay-right">
          <h1>没有帐号？</h1>
          <p>立即注册加入我们，和我们一起开始旅程吧</p>
          <button class="ghost" id="signUp">注册</button>
        </div>
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
            if (res.data.code == 200) {
              alert("感谢你的注册,请检查你的邮箱,激活你的账户");
              _this.dialogVisible = false;
              _this.signUpAccount = {
                username: "",
                email: "",
                password: ""
              };
            }
          });
      } else {
        console, log("注册失败");
        return false;
      }
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
          // console.log(res);
          if (res.data.code == 200) {
            const token = res.headers["authorization"];
            _this.$store.commit("SET_TOKEN", token);
            _this.$store.commit("SET_USERINFO", res.data.data);
            let path = this.routerCfg.options.pathById(2);
            console.log(path);
            this.$router.push(path);
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
      var signUpButton = document.getElementById("signUp");
      var signInButton = document.getElementById("signIn");
      var container = document.getElementById("dowebok");

      signUpButton.addEventListener("click", function() {
        container.classList.add("right-panel-active");
      });

      signInButton.addEventListener("click", function() {
        container.classList.remove("right-panel-active");
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

.login-container {
  background-color: #fff;
  height: 400px;
  width: 500px;
  margin: 0 auto;
  border-radius: 2px;
  display: flex;
  justify-content: space-evenly;
  > h3 {
    font-weight: 700;
    font-family: "Titillium Web", sans-serif;
    color: #2d385e;
    font-size: 1.75rem;
  }
}

h1 {
  font-weight: bold;
  margin: 0;
}

p {
  font-size: 14px;
  line-height: 20px;
  letter-spacing: 0.5px;
  margin: 20px 0 30px;
}

span {
  font-size: 12px;
}

a {
  color: #333;
  font-size: 14px;
  text-decoration: none;
  margin: 15px 0;
}

.dowebok {
  background: #fff;
  border-radius: 10px;
  box-shadow: 0 14px 28px rgba(0, 0, 0, 0.25), 0 10px 10px rgba(0, 0, 0, 0.22);
  position: relative;
  overflow: hidden;
  width: 768px;
  max-width: 100%;
  min-height: 480px;
}

.form-container form {
  background: #fff;
  display: flex;
  flex-direction: column;
  padding: 0 50px;
  height: 100%;
  justify-content: center;
  align-items: center;
  text-align: center;
}

.social-container {
  margin: 20px 0;
}

.social-container a {
  border: 1px solid #ddd;
  border-radius: 50%;
  display: inline-flex;
  justify-content: center;
  align-items: center;
  margin: 0 5px;
  height: 40px;
  width: 40px;
}

.social-container a:hover {
  background-color: #eee;
}

.eye-wrap {
  position: relative;
  width: 30px;
  height: 39px;
  background: #eee;
  display: flex;
  justify-content: center;

  > i {
    position: absolute;
    font-size: 16px;
    top: 50%;
    transform: translate(0, -50%);
    cursor: pointer;
  }
}

.form-container .form-group {
  width: 100%;
  display: flex;
  align-items: center;
}

.form-container input[type="password"] {
  background: #eee;
  border: none;
  padding: 12px 15px;
  margin: 8px 0;
  width: calc(100% - 30px);
  outline: none;
}

.form-container input {
  background: #eee;
  border: none;
  padding: 12px 15px;
  margin: 8px 0;
  width: 100%;
  outline: none;
}
button {
  border-radius: 20px;
  border: 1px solid #ff4b2b;
  background: #ff4b2b;
  color: #fff;
  font-size: 12px;
  font-weight: bold;
  padding: 12px 45px;
  letter-spacing: 1px;
  text-transform: uppercase;
  transition: transform 80ms ease-in;
  cursor: pointer;
}

button:active {
  transform: scale(0.95);
}

button:focus {
  outline: none;
}

button.ghost {
  background: transparent;
  border-color: #fff;
}

.form-container {
  position: absolute;
  top: 0;
  height: 100%;
  transition: all 0.6s ease-in-out;
}

.sign-in-container {
  left: 0;
  width: 50%;
  z-index: 2;
}

.sign-up-container {
  left: 0;
  width: 50%;
  z-index: 1;
  opacity: 0;
}

.overlay-container {
  position: absolute;
  top: 0;
  left: 50%;
  width: 50%;
  height: 100%;
  overflow: hidden;
  transition: transform 0.6s ease-in-out;
  z-index: 100;
}

.overlay {
  background: #ff416c;
  background: linear-gradient(to right, #ff4b2b, #ff416c) no-repeat 0 0 / cover;
  color: #fff;
  position: relative;
  left: -100%;
  height: 100%;
  width: 200%;
  transform: translateY(0);
  transition: transform 0.6s ease-in-out;
}

.overlay-panel {
  position: absolute;
  top: 0;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  // padding: 0 40px;
  height: 100%;
  width: 50%;
  text-align: center;
  transform: translateY(0);
  transition: transform 0.6s ease-in-out;
}

.overlay-right {
  right: 0;
  transform: translateY(0);
}

.overlay-left {
  transform: translateY(-20%);
}

/* Move signin to right */
.dowebok.right-panel-active .sign-in-container {
  transform: translateY(100%);
}

/* Move overlay to left */
.dowebok.right-panel-active .overlay-container {
  transform: translateX(-100%);
}

/* Bring signup over signin */
.dowebok.right-panel-active .sign-up-container {
  transform: translateX(100%);
  opacity: 1;
  z-index: 5;
}

/* Move overlay back to right */
.dowebok.right-panel-active .overlay {
  transform: translateX(50%);
}

/* Bring back the text to center */
.dowebok.right-panel-active .overlay-left {
  transform: translateY(0);
}

/* Same effect for right */
.dowebok.right-panel-active .overlay-right {
  transform: translateY(20%);
}

input[type="email"]:invalid {
  box-shadow: 0 0 5px 1px red;
}
</style>
