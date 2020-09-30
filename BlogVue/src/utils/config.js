//接口配置
// var BaseUrl="/api/";
// var FileUrl=FileUrl1;
// var FileUrl="http://localhost:49568/api/";

var APIConfig = function (_baseUrl, _fileUrl) {
  var BaseUrl = window.conf.baseUrl;
  var BlogdUrl = BaseUrl + "/blog"
  return {
    Base: {
      Login: BaseUrl + "/login",
      Logout: BaseUrl + "/logout",
      Register: BaseUrl + "/register",
      Blog: BaseUrl + "/blog",
      Blogs: BlogdUrl + "/blogs",
      BlogEdit: BlogdUrl + "/edit",
      BlogsImgUpload: BaseUrl + "/file/upload"
    },


    //小讨论

  }
}


export default {
  Init: function (_baseUrl, _fileUrl) {
    return APIConfig(_baseUrl, _fileUrl);
  }
}
