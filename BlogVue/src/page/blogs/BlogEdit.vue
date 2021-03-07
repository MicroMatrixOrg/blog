<template>
  <div class="m-container">
    <div class="m-content">
      <el-form
        ref="editForm"
        status-icon
        :model="editForm"
        :rules="rules"
        label-width="80px"
      >
        <el-form-item label="标题" prop="title">
          <el-input v-model="editForm.title"></el-input>
        </el-form-item>
        <el-form-item label="摘要" prop="description">
          <el-input type="textarea" v-model="editForm.description"></el-input>
        </el-form-item>
        <el-form-item label="内容" prop="content">
          <mavon-editor
            ref="md"
            :ishljs="true"
            :toolbarsBackground="'#f9f9f9'"
            v-model="editForm.content"
            @imgAdd="$imgAdd"
            @imgDel="$imgDel"
            style="height: calc(100vh)"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm()">保存内容</el-button>
          <el-button @click="cancle()">取消</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>
<script>
export default {
  name: "BlogEdit",
  data() {
    return {
      editForm: {
        id: null,
        title: "",
        description: "",
        content: ""
      },
      rules: {
        title: [
          { required: true, message: "请输入标题", trigger: "blur" },
          { min: 3, max: 50, message: "长度在 3 到 50 个字符", trigger: "blur" }
        ],
        description: [
          { required: true, message: "请输入摘要", trigger: "blur" }
        ]
      },
      img_files: {}
    };
  },
  mounted() {
    this.initDate();
  },
  created() {},
  methods: {
    /**
     * @description: 初始化数据，只有带了Id表明他是编辑
     * @Date: 2020-11-03 10:47:30
     * @Author: David
     */

    initDate() {
      const _this = this;
      const blogId = this.$route.query.blogId;
      if (typeof blogId != "undefined") {
        this.$axios.get(`${window.conf.baseUrl}/blog/` + blogId).then(res => {
          let resp = res.resp;
          let respData = res.respData;

          const blog = respData.data;
          _this.editForm.id = blog.id;
          _this.editForm.title = blog.title;
          _this.editForm.description = blog.description;
          _this.editForm.content = blog.content;
        });
      }
    },

    /**
     * @description: 取消返回首页
     * @Date: 2020-11-03 10:21:21
     * @Author: David
     */

    cancle() {
      this.$router.push("/blogs");
    },

    /**
     * @description: 添加图片
     * @param {*} pos 图片的位置下标
     * @return {*} $file 图片文件
     * @Date: 2020-11-30 17:42:42
     * @Author: David
     */

    $imgAdd(pos, $file) {
      const _this = this;
      var formdata = new FormData();
      formdata.append("upfiles", $file);
      this.$axios
        .post(APIConfig.Base.BlogsImgUpload, formdata, {
          headers: {
            Authorization: this.$store.getters.GET_TOKEN
          }
        })
        .then(res => {
          let resp = res.resp;
          let respData = res.respData;
          _this.$refs.md.$img2Url(
            pos,
            window.conf.fileUrl + respData.data[0].filePath
          );
        });
    },

    /**
     * @description: 删除图片
     * @param {*} pos 图片的位置下标
     * @return {*}
     * @Date: 2020-11-30 17:43:34
     * @Author: David
     */

    $imgDel(pos) {
      delete this.img_files[pos];
    },

    /**
     * @description: 提交文章
     * @param {*}
     * @return {*}
     * @Date: 2020-11-30 17:43:45
     * @Author: David
     */

    submitForm() {
      const _this = this;
      this.$refs.editForm.validate(valid => {
        if (valid) {
          this.$axios
            .post(APIConfig.Base.BlogEdit, this.editForm, {
              headers: {
                Authorization: this.$store.getters.GET_TOKEN
              }
            })
            .then(res => {
              _this.$alert("操作成功", "提示", {
                confirmButtonText: "确定",
                callback: action => {
                  _this.$router.push("/blogs");
                }
              });
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    }
  }
};
</script>

<style lang="sass" scoped></style>
