<template>
  <div class="m-content">
    <h3>欢迎来到Bit博客</h3>
    <div class="block">
      <el-avatar :size="50" :src="user.avatar"></el-avatar>
      <div>{{ user.username }}</div>
    </div>
    <div class="maction">
      <el-link @click="goMain()" href="">主页</el-link>
      <el-divider direction="vertical"></el-divider>
      <span>
        <el-link type="success" @click="release" :disabled="!hasLogin"
          >发表文章</el-link
        >
      </span>
      <el-divider direction="vertical"></el-divider>
      <span v-show="!hasLogin">
        <el-link type="primary" @click="login">登陆</el-link>
      </span>
      <span v-show="!hasLogin">
        <el-link type="primary" @click="open">注册</el-link>
      </span>
      <span v-show="hasLogin">
        <el-link type="danger" @click="logout">退出</el-link>
      </span>
    </div>

    <el-dialog title="注册" :visible.sync="dialogVisible" width="30%">
      <el-form
        :model="form"
        label-position="left"
        ref="ruleForm"
        :rules="rules"
      >
        <el-form-item label="用户名" :label-width="formLabelWidth" prop="name">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" :label-width="formLabelWidth" prop="email">
          <el-input v-model="form.email" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item
          label="密码"
          :label-width="formLabelWidth"
          prop="password"
        >
          <el-input
            type="password"
            v-model="form.password"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="确认密码"
          :label-width="formLabelWidth"
          prop="checkPass"
        >
          <el-input
            type="password"
            v-model="form.checkPass"
            autocomplete="off"
          ></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="register()">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
export default {
  name: "Header",
  data() {
    var validateName = (rule, value, callback) => {
      if (value == "") {
        callback(new Error("请输入用户名"));
      }
      callback();
    };

    var validateEmail = (rule, value, callback) => {
      if (value == "") {
        callback(new Error("请输入邮箱"));
      } else {
        if (this.form.email !== "") {
          let pattern = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
          if (pattern.test(value)) {
            callback();
          } else {
            callback(new Error("请输入正确的邮箱格式"));
          }
        }
      }
    };

    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        if (this.form.checkPass !== "") {
          this.$refs.ruleForm.validateField("checkPass");
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.form.password) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      hasLogin: false,
      user: {
        username: "请先登录",
        avatar:
          "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png"
      },
      blogs: {},
      currentPage: 1,
      total: 0,
      dialogVisible: false,

      form: {
        name: "",
        email: "",
        password: "",
        checkPass: ""
      },
      rules: {
        name: [{ validator: validateName, trigger: "blur" }],
        password: [{ validator: validatePass, trigger: "blur" }],
        checkPass: [{ validator: validatePass2, trigger: "blur" }],
        email: [{ validator: validateEmail, trigger: "blur" }]
      },
      formLabelWidth: "120px"
    };
  },
  methods: {
    handleClose() {},
    open() {
      const _this = this;
      _this.dialogVisible = true;
    },

    /**
     * @description: 注册
     * @Date: 2020-08-08 10:57:54
     * @Author: David
     */

    register() {
      const _this = this;
      // console.log(_this.form);
      let params = {
        username: _this.form.name,
        password: _this.form.password,
        email: _this.form.email
      };

      this.$axios.post(APIConfig.Base.Register, params).then(res => {
        if (res.data.code == 200) {
          _this.$message({
            message: "恭喜你，注册成功",
            type: "success"
          });

          _this.dialogVisible = false;
          _this.form = {
            name: "",
            email: "",
            password: "",
            checkPass: ""
          };
        }
      });
    },
    /**
     * @description: 退出
     * @Date: 2020-08-08 10:57:47
     * @Author: David
     */

    logout() {
      const _this = this;
      this.$axios
        .get(APIConfig.Base.Logout, {
          headers: {
            Authorization: localStorage.getItem("token")
          }
        })
        .then(res => {
          _this.$store.commit("REMOVE_INFO");
          _this.$router.push("/loginPage");
        });
    },

    /**
     * @description: 发表文章
     * @Date: 2020-09-27 17:22:24
     * @Author: David
     */

    release() {
      let path = this.routerCfg.options.pathById(21);
      this.$router.push(path);
    },

    login() {
      let path = this.routerCfg.options.pathById(1);
      this.$router.push(path);
    },

    goMain() {
      let path = this.routerCfg.options.pathById(2);
      this.$router.push(path);
    }
  },
  created() {
    if (this.$store.getters.getUser.username) {
      this.user.username = this.$store.getters.getUser.username;
      this.user.avatar = this.$store.getters.getUser.avatar;
      this.hasLogin = true;
    }
  }
};
</script>
