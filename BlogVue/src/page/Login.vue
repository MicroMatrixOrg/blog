<template>
  <div>
    <el-container>
      <el-header>
        <router-link to="/blogs">
          <!-- <img
            src="https://www.markerhub.com/dist/images/logo/markerhub-logo.png"
            style="height: 60%; margin-top: 10px;"
          /> -->
          <svg
            t="1601455241143"
            class="icon"
            viewBox="0 0 1024 1024"
            version="1.1"
            xmlns="http://www.w3.org/2000/svg"
            p-id="2745"
            width="50"
            height="60%"
            style="margin-top: 10px;margin-left:100px;"
          >
            <path
              d="M26.965333 23.466667v977.066666H97.28V1024H0V0h97.28v23.466667z m300.501334 309.76v49.365333h1.408a141.312 141.312 0 0 1 47.658666-43.690667c18.474667-10.453333 39.936-15.573333 64-15.573333 23.04 0 44.074667 4.565333 63.189334 13.397333 19.114667 8.874667 33.493333 24.832 43.52 47.274667 10.837333-15.957333 25.6-30.122667 44.117333-42.325333 18.517333-12.245333 40.533333-18.346667 65.962667-18.346667 19.328 0 37.205333 2.389333 53.76 7.125333 16.554667 4.693333 30.549333 12.202667 42.368 22.613334 11.776 10.453333 20.864 23.850667 27.562666 40.576 6.485333 16.725333 9.813333 36.821333 9.813334 60.458666v244.394667h-100.224V491.52c0-12.202667-0.426667-23.850667-1.365334-34.645333a74.88 74.88 0 0 0-7.68-28.16 47.189333 47.189333 0 0 0-18.688-19.114667c-8.277333-4.693333-19.498667-7.082667-33.493333-7.082667-14.165333 0-25.6 2.730667-34.261333 8.064a58.88 58.88 0 0 0-20.48 21.290667 83.029333 83.029333 0 0 0-9.856 29.696 237.226667 237.226667 0 0 0-2.56 33.493333v203.434667h-100.266667v-204.8c0-10.837333-0.170667-21.461333-0.768-32.085333a88.490667 88.490667 0 0 0-6.101333-29.354667 44.885333 44.885333 0 0 0-17.706667-21.461333c-8.277333-5.333333-20.309333-8.106667-36.437333-8.106667-4.736 0-11.050667 1.024-18.730667 3.157333-7.68 2.176-15.36 6.101333-22.613333 12.032a69.845333 69.845333 0 0 0-18.730667 25.386667c-5.12 11.050667-7.68 25.6-7.68 43.52v211.882667H232.96V333.226667z m669.568 667.306666V23.466667H926.72V0H1024v1024h-97.28v-23.466667z"
              p-id="2746"
              fill="#1296db"
            ></path>
          </svg>
        </router-link>
      </el-header>
      <el-main>
        <el-form
          :model="ruleForm"
          status-icon
          :rules="rules"
          ref="ruleForm"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-form-item label="邮箱" prop="username">
            <el-input type="text" v-model="ruleForm.username"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input
              type="password"
              v-model="ruleForm.password"
              autocomplete="off"
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')"
              >登录</el-button
            >
            <el-button @click="resetForm('ruleForm')">重置</el-button>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>
  </div>
</template>
<script>
export default {
  name: "Login",
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        callback();
      }
    };
    return {
      ruleForm: {
        password: "",
        username: ""
      },
      rules: {
        password: [{ validator: validatePass, trigger: "blur" }],
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" }
          // { min: 3, max: 12, message: "长度在 3 到 12 个字符", trigger: "blur" }
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      const _this = this;
      this.$refs[formName].validate(valid => {
        if (valid) {
          // 提交逻辑
          this.$axios.post(APIConfig.Base.Login, this.ruleForm).then(res => {
            console.log(res);
            if (res.data.code == 200) {
              const token = res.headers["authorization"];
              _this.$store.commit("SET_TOKEN", token);
              _this.$store.commit("SET_USERINFO", res.data.data);
              _this.$router.push("/blogs");
            }
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  },
  mounted() {
    // this.$notify({
    //   title: "看这里：",
    //   message: "",
    //   duration: 1500
    // });
  }
};
</script>

<style lang="sass" scoped></style>
