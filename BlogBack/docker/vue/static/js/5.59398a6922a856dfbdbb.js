webpackJsonp([5],{"2IRY":function(t,e,i){"use strict";function n(t){i("cII5")}var a=i("UUCl"),s=i("Nrfy"),o=i("C7Lr"),r=n,c=o(a.a,s.a,!1,r,"data-v-6fdb92be",null);e.a=c.exports},MisZ:function(t,e,i){e=t.exports=i("UTlt")(!1),e.push([t.i,".input[data-v-6fdb92be]{-webkit-box-flex:1;-ms-flex:1;flex:1;display:inline-block;border:none;outline:none;color:#909090;font-size:1.3rem}.input-box[data-v-6fdb92be]{display:-webkit-box;display:-ms-flexbox;display:flex;-webkit-box-pack:end;-ms-flex-pack:end;justify-content:flex-end;-webkit-box-flex:1;-ms-flex:1;flex:1}.action-box[data-v-6fdb92be]{margin-left:1rem}.btn[data-v-6fdb92be]{background-color:transparent;color:#007fff;font-size:1.2rem;padding:0;white-space:nowrap}.edit-btn[data-v-6fdb92be]{-webkit-appearance:none;-moz-appearance:none;appearance:none;border-radius:2px;border:none;padding:.5rem 1.3rem;outline:none;-webkit-transition:background-color .3s,color .3s;transition:background-color .3s,color .3s;cursor:pointer}.confirm-btn[data-v-6fdb92be]{margin-right:10px}",""])},Nrfy:function(t,e,i){"use strict";var n=function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{staticClass:"input-box"},[i("input",{staticClass:"input",attrs:{id:"input"+t.time,spellcheck:"false",placeholder:t.placeholder},domProps:{value:t.inputValue},on:{focus:function(e){return t.inputChose()}}}),t._v(" "),i("div",{staticClass:"action-box"},[i("button",{staticClass:"btn edit-btn",attrs:{tabindex:"-1",id:"editbtn"+t.time},on:{click:function(e){return t.edit()}}},[i("i",{staticClass:"fa fa-pencil",attrs:{"aria-hidden":"true"}}),t._v(" "),i("span",[t._v("修改")])]),t._v(" "),i("button",{staticClass:"btn edit-btn confirm-btn",staticStyle:{display:"none"},attrs:{id:"savebtn"+t.time,tabindex:"-1"},on:{click:function(e){return t.save()}}},[t._v("\n      保存\n    ")]),t._v(" "),i("button",{staticClass:"btn edit-btn confirm-btn",staticStyle:{display:"none",color:"#000"},attrs:{id:"canclebtn"+t.time,tabindex:"-1"},on:{click:function(e){return t.cancle()}}},[t._v("\n      取消\n    ")])])])},a=[],s={render:n,staticRenderFns:a};e.a=s},PU7Y:function(t,e,i){"use strict";function n(t){i("kl2L")}Object.defineProperty(e,"__esModule",{value:!0});var a=i("PWI3"),s=i("RSP+"),o=i("C7Lr"),r=n,c=o(a.a,s.a,!1,r,"data-v-16075997",null);e.default=c.exports},PWI3:function(t,e,i){"use strict";var n=i("2IRY");e.a={data:function(){return{settingFlag:1,userInfo:{username:"",depiction:""},accountInfo:{mail:"",password:""}}},components:{EditInput:n.a},mounted:function(){var t=this;t.userInfo=t.$store.getters.GET_USER},methods:{signout:function(){var t=this;this.$axios.get(APIConfig.Base.Logout,{headers:{Authorization:this.$store.getters.GET_TOKEN}}).then(function(e){t.$store.commit("REMOVE_INFO"),t.$router.go(0)})},saveAccointPassword:function(t){var e=this,i={id:e.userInfo.id,username:e.userInfo.username,password:e.accountInfo.password};this.$axios.post(APIConfig.User.EditUserInfo,i).then(function(t){t.resp;200==t.respData.code?e.signout():alert("请稍后在重试吧")})},saveUserDepiction:function(t){var e=this,i={id:e.userInfo.id,username:e.userInfo.username,depiction:t};this.$axios.post(APIConfig.User.EditUserInfo,i).then(function(i){i.resp;200==i.respData.code?(e.userInfo.depiction=t,e.$storge.set("userInfo",e.userInfo)):alert("请稍后在重试吧")})},saveUserName:function(t){var e=this,i={id:e.userInfo.id,username:t};this.$axios.post(APIConfig.User.EditUserInfo,i).then(function(i){i.resp;200==i.respData.code?(e.userInfo.username=t,e.$storge.set("userInfo",e.userInfo)):alert("请稍后在重试吧")})},backHomePage:function(){this.$router.go(-1)},editProfile:function(t){this.settingFlag=1;for(var e=document.getElementsByClassName("nav-item"),i=0;i<e.length;i++)e[i].classList.remove("active");t.target.classList.contains("active")||t.target.classList.add("active")},editAccount:function(t){this.settingFlag=2;for(var e=document.getElementsByClassName("nav-item"),i=0;i<e.length;i++)e[i].classList.remove("active");t.target.classList.contains("active")||t.target.classList.add("active")}}}},"RSP+":function(t,e,i){"use strict";var n=function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{staticClass:"profile-wrap"},[i("header",{staticClass:"profile-header"},[i("ul",[i("li",{staticClass:"back-my-home-page nav-item",on:{click:function(e){return t.backHomePage()}}},[i("i",{staticClass:"fa fa-chevron-left",attrs:{"aria-hidden":"true"}}),t._v(" \n        "),i("span",[t._v("返回个人主页")])]),t._v(" "),i("li",{staticClass:"nav-item active",on:{click:function(e){return t.editProfile(e)}}},[t._v("个人资料")]),t._v(" "),i("li",{staticClass:"nav-item",on:{click:function(e){return t.editAccount(e)}}},[t._v("账号设置")])])]),t._v(" "),i("main",{staticClass:"view-content setting-view"},[i("div",{directives:[{name:"show",rawName:"v-show",value:1==t.settingFlag,expression:"settingFlag == 1"}],staticClass:"profile-content shadow"},[i("h1",[t._v("个人资料")]),t._v(" "),i("ul",[i("li",{staticClass:"item"},[i("span",{staticClass:"title"},[t._v("用户名")]),t._v(" "),i("edit-input",{attrs:{placeholder:"请输入你的用户名","input-value":t.userInfo.username},on:{inputVale:t.saveUserName}})],1),t._v(" "),i("li",{staticClass:"item"},[i("span",{staticClass:"title"},[t._v("个人介绍")]),i("edit-input",{attrs:{placeholder:"填写擅长、爱做的事情","input-value":t.userInfo.depiction},on:{inputVale:t.saveUserDepiction}})],1)])]),t._v(" "),i("div",{directives:[{name:"show",rawName:"v-show",value:2==t.settingFlag,expression:"settingFlag == 2"}],staticClass:"account-setting shadow"},[i("h1",[t._v("账号设置")]),t._v(" "),i("ul",[t._m(0),t._v(" "),i("li",{staticClass:"item"},[i("span",{staticClass:"title"},[t._v("密码")]),i("edit-input",{attrs:{placeholder:"修改你的密码","input-value":t.accountInfo.password},on:{inputVale:t.saveAccointPassword}})],1)])])])])},a=[function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("li",{staticClass:"item"},[i("span",{staticClass:"title"},[t._v("邮箱")])])}],s={render:n,staticRenderFns:a};e.a=s},UUCl:function(t,e,i){"use strict";e.a={props:{placeholder:{type:String,default:""},inputValue:{type:String,default:""}},data:function(){return{time:(new Date).getTime()}},mounted:function(){},methods:{inputChose:function(){this.showbtn()},edit:function(){this.showbtn()},save:function(){var t=document.getElementById("input"+this.time);this.$emit("inputVale",t.value),this.showbtn()},cancle:function(){document.getElementById("input"+this.time).value=this.inputValue,this.showbtn()},showbtn:function(){var t=document.getElementById("editbtn"+this.time),e=document.getElementById("savebtn"+this.time),i=document.getElementById("canclebtn"+this.time);this.$utils.hmst(t,e,i)}}}},cII5:function(t,e,i){var n=i("MisZ");"string"==typeof n&&(n=[[t.i,n,""]]),n.locals&&(t.exports=n.locals);i("FIqI")("49c20ce2",n,!0,{})},kl2L:function(t,e,i){var n=i("uOmZ");"string"==typeof n&&(n=[[t.i,n,""]]),n.locals&&(t.exports=n.locals);i("FIqI")("4507cddc",n,!0,{})},uOmZ:function(t,e,i){e=t.exports=i("UTlt")(!1),e.push([t.i,".profile-wrap[data-v-16075997]{position:relative;margin:0 auto;width:100%;max-width:960px}.active[data-v-16075997],.nav-item[data-v-16075997]:hover{color:#007fff!important}.profile-header[data-v-16075997]{position:fixed;top:5rem;left:0;width:100%;-webkit-box-shadow:0 1px 2px 0 rgba(0,0,0,.05);box-shadow:0 1px 2px 0 rgba(0,0,0,.05);z-index:100;background-color:#fff}.profile-header>ul[data-v-16075997]{display:-webkit-box;display:-ms-flexbox;display:flex;max-width:960px;margin:auto;width:100%;height:3.833rem;-webkit-transition:all .2s;transition:all .2s;-webkit-transform:translateZ(0);transform:translateZ(0)}.nav-item[data-v-16075997]{display:-webkit-box;display:-ms-flexbox;display:flex;-webkit-box-align:center;-ms-flex-align:center;align-items:center;height:100%;-ms-flex-negative:0;flex-shrink:0;font-size:1.16rem;line-height:1.16rem;color:#71777c;padding:0 1rem;cursor:pointer}.view-content[data-v-16075997]{margin-top:4.67rem}.setting-view[data-v-16075997]{margin-bottom:2rem;width:58rem;max-width:100%}.account-setting[data-v-16075997],.profile-content[data-v-16075997]{position:relative;padding:2.7rem 4rem 7rem;background-color:#fff;border-radius:2px;text-align:left}.account-setting>h1[data-v-16075997],.profile-content>h1[data-v-16075997]{font-size:2em;margin:.67em 0}.shadow[data-v-16075997]{-webkit-box-shadow:0 1px 2px 0 rgba(0,0,0,.05);box-shadow:0 1px 2px 0 rgba(0,0,0,.05)}.item[data-v-16075997]:first-child{padding:1rem 0}.item[data-v-16075997]:last-child{border-bottom:1px solid #f1f1f1}.item[data-v-16075997]{display:-webkit-box;display:-ms-flexbox;display:flex;-webkit-box-align:center;-ms-flex-align:center;align-items:center;padding:2rem 0;border-top:1px solid #f1f1f1}.title[data-v-16075997]{font-size:1.2rem;color:#333;width:10rem}",""])}});