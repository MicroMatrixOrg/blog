//接口配置

var APIConfig = function (_baseUrl, _fileUrl) {
  let BaseUrl = window.conf.baseUrl;
  let BlogdUrl = BaseUrl + "/blog";
  let BlogMail = BaseUrl + "/email";
  let BlogUser = BaseUrl + "/user";
  let Thumb = BaseUrl + "/vote";
  let Comment = BaseUrl + "/comment"
  return {

    Base: {
      Login: BaseUrl + "/login",
      Logout: BaseUrl + "/logout",
      Register: BaseUrl + "/register",
      Blog: BaseUrl + "/blog",
      Blogs: BlogdUrl + "/blogs",
      BlogEdit: BlogdUrl + "/edit",
      BlogsImgUpload: BaseUrl + "/file/upload",
      BlogsByUserId: BlogdUrl + "/getMyBlogs",
      DeleteBlog: BlogdUrl + "/deleteBlog",
    },

    Email: {
      SendHtmlMail: BlogMail + "/verifytemplate",
      VerifyMail: BlogMail + "/verifycode"
    },

    User: {
      GetUserBaseInfo: BlogUser + "/",//"/{id}"
      EditUserInfo: BlogUser + "/editUserInfo"
    },

    Thumb: {
      Like: Thumb + "/like"
    },

    Comment: {
      ReleaseComment: Comment + "/review",//发布评论
      GetCommentList: Comment + "/getCommentList",//获取评论列表
      RemoveComment: Comment + "/deleteReview",//删除评论
    }


  }
}


export default {
  Init: function (_baseUrl, _fileUrl) {
    return APIConfig(_baseUrl, _fileUrl);
  }
}
