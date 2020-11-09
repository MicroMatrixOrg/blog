//接口配置

var APIConfig = function (_baseUrl, _fileUrl) {
  var BaseUrl = window.conf.baseUrl;
  var BlogdUrl = BaseUrl + "/blog";
  var BlogMail = BaseUrl + "/email";
  var BlogUser = BaseUrl + "/user";
  return {

    Base: {
      Login: BaseUrl + "/login",
      Logout: BaseUrl + "/logout",
      Register: BaseUrl + "/register",
      Blog: BaseUrl + "/blog",
      Blogs: BlogdUrl + "/blogs",
      BlogEdit: BlogdUrl + "/edit",
      BlogsImgUpload: BaseUrl + "/file/upload",
      BlogsByUserId: BlogdUrl + "/getMyBlogs"
    },

    Email: {
      SendHtmlMail: BlogMail + "/verifytemplate",
      VerifyMail: BlogMail + "/verifycode"
    },

    User: {
      GetUserBaseInfo: BlogUser + "/",//"/{id}"
    }

  }
}


export default {
  Init: function (_baseUrl, _fileUrl) {
    return APIConfig(_baseUrl, _fileUrl);
  }
}
