<template>
  <div class="m-container">
    <div class="block">
      <el-timeline>
        <el-timeline-item
          v-bind:timestamp="blog.created"
          placement="top"
          v-for="(blog, index) in blogs"
          :key="index"
        >
          <el-card>
            <h4>
              <router-link
                :to="{ name: 'BlogDetail', params: { blogId: blog.id } }"
                >{{ blog.title }}</router-link
              >
            </h4>
            <p>{{ blog.description }}</p>
          </el-card>
        </el-timeline-item>
      </el-timeline>
    </div>
    <el-pagination
      class="mpage"
      background
      layout="prev, pager, next"
      :current-page="currentPage"
      :page-size="pageSize"
      @current-change="page"
      :total="total"
    >
    </el-pagination>
  </div>
</template>
<script>
import Header from "@/components/Header";
export default {
  name: "Blogs",
  components: { Header },
  data() {
    return {
      blogs: {},
      currentPage: 1,
      total: 0,
      pageSize: 5
    };
  },
  methods: {
    page(currentPage) {
      const _this = this;
      console.log(APIConfig.Base.Blogs);
      this.$axios
        .get(`${APIConfig.Base.Blogs}?currentPage=` + currentPage)
        .then(res => {
          // console.log(res.data.data.records);
          _this.blogs = res.data.data.records;
          _this.currentPage = res.data.data.current;
          _this.total = res.data.data.total;
          _this.pageSize = res.data.data.size;
        });
    }
  },
  mounted() {
    window.addEventListener("message", e => {
      console.log(e);
    });
    this.page(1);
  }
};
</script>

<style lang="sass" scoped></style>
