export default {
  /**
    * [dateDiff 算时间差]
    * @param  {[type=Number]} dateTimeStamp [历史时间戳，必传]
    * @return {[string]}         [string]
    */
  goodTime (dateTimeStamp) {
    var minute = 1000 * 60;      //把分，时，天，周，半个月，一个月用毫秒表示
    var hour = minute * 60;
    var day = hour * 24;
    var week = day * 7;
    var halfamonth = day * 15;
    var month = day * 30;
    var now = new Date().getTime();   //获取当前时间毫秒
    var diffValue = now - new Date().getTime(dateTimeStamp);//时间差
    if (diffValue < 0) {
      return;
    }
    var minC = diffValue / minute;  //计算时间差的分，时，天，周，月
    var hourC = diffValue / hour;
    var dayC = diffValue / day;
    var weekC = diffValue / week;
    var monthC = diffValue / month;

    var result = "";

    if (monthC >= 1 && monthC <= 3) {
      result = " " + parseInt(monthC) + "月前"
    } else if (weekC >= 1 && weekC <= 3) {
      result = " " + parseInt(weekC) + "周前"
    } else if (dayC >= 1 && dayC <= 6) {
      result = " " + parseInt(dayC) + "天前"
    } else if (hourC >= 1 && hourC <= 23) {
      result = " " + parseInt(hourC) + "小时前"
    } else if (minC >= 1 && minC <= 59) {
      result = " " + parseInt(minC) + "分钟前"
    } else if (diffValue >= 0 && diffValue <= minute) {
      result = "刚刚"
    } else {
      var datetime = new Date();
      datetime.setTime(dateTimeStamp);
      var Nyear = datetime.getFullYear();
      var Nmonth = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
      var Ndate = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
      var Nhour = datetime.getHours() < 10 ? "0" + datetime.getHours() : datetime.getHours();
      var Nminute = datetime.getMinutes() < 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
      var Nsecond = datetime.getSeconds() < 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
      result = Nyear + "-" + Nmonth + "-" + Ndate
    }
    return result;
  },

  dateFormat (_date, fmt) {
    var date = new Date(_date);
    var o = {
      "M+": date.getMonth() + 1, //月份 
      "d+": date.getDate(), //日 
      "H+": date.getHours(), //小时 
      "m+": date.getMinutes(), //分 
      "s+": date.getSeconds(), //秒 
      "q+": Math.floor((date.getMonth() + 3) / 3), //季度 
      "S": date.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
      if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
  },
  dataFilter (_key, arry) {
    for (var key in arry) {
      if (arry.hasOwnProperty(key)) {
        var element = arry[key];
        if (element.k == _key)
          return element.v;
      }
    }
    return '';
  },
  myUpload (url, fileObj, data, callback) {
    var FileController = "/api" + url;                    // 接收上传文件的后台地址
    if (!fileObj || fileObj.length == 0) {
      console.error('请选择上传文件'); return;
    }
    // FormData 对象
    var form = new FormData();
    //表单数据
    for (let key in data) {
      const element = data[key];
      form.append(key, element);
    }
    form.append("file", fileObj);// 文件对象
    // XMLHttpRequest 对象
    var xhr = new XMLHttpRequest();
    xhr.open("post", FileController, true);
    xhr.onload = function (resp) {
      var obj = JSON.parse(resp.currentTarget.response);
      if (callback)
        callback(obj);
    };
    xhr.send(form);
  },
  comSetting () {//公共插件基础配置，如有需要，覆盖即可
    const tree = {
      view: {
        dblClickExpand: false,
        showIcon: false,
      },
      check: {
        enable: false,
      },
      callback: {}
    }
    return {
      tree: tree,
    }
  },
  comSetting1 () {//公共插件基础配置，如有需要，覆盖即可
    const tree = {
      view: {
        dblClickExpand: false,
        showIcon: false,
      },
      edit: {
        enable: true,
        showRemoveBtn: false,
        showRenameBtn: false,
        drag: { prev: true, next: true, inner: false }
      },
      check: {
        enable: false
      },
      data: {
        simpleData: {
          enable: true
        }
      },
      callback: {

      }
    }
    return {
      tree: tree,
    }
  },

  openFistNode (_zNodes) {//展开一级节点
    if (_zNodes && _zNodes.length > 0) {
      for (var key in _zNodes) {
        if (_zNodes.hasOwnProperty(key)) {
          var element = _zNodes[key];
          element.open = true;
        }
      }
    }
    else {
      _zNodes.open = true;
    }
    return _zNodes;
  },
  editorFormat (val) {//富文本提取文字
    return val.replace(/<[^>]+>/g, "");
  },
  mathFormat (val, number = 2) {//小数点截取，默认取2位
    var num = Math.pow(10, number);
    return Math.floor(val * num) / num;
  },
  fileSize (size) {//返回文件大小
    if (!size) return '';
    if (size / 1024 < 1024) {
      size = Math.ceil(size / 1024);
      return size + 'KB';
    } else if (size / (1024 * 1024) < 1024) {
      size = Math.ceil(size / (1024 * 1024) * 100) / 100;
      return size + 'MB';
    } else if (size / (1024 * 1024 * 1024) < 1024) {
      size = Math.ceil(size / (1024 * 1024 * 1024) * 100) / 100;
      return size + 'GB';
    }
  },
  handleValidate (_this, name) {//表单验证
    let isValid = false;
    _this.$refs[name].validate(function (valid) {
      if (valid) {
        isValid = true;
      } else {
        // _this.$Message.info('表单验证失败!');

        _this.$Message.info('请填写红色星号内容！');
      }
    })
    return isValid;
  },
  handleReset (_this, resetName, name) {//表单重置
    if (name && _this.isModal && _this.isModal[name]) _this.isModal[name] = false;
    if (resetName) {
      _this.$refs[resetName].resetFields();
    }
  },
  downFile (id) {//下载文件
    location.href = APIConfig.Base.FileDown + '?id=' + id;
  },

  /**
   * @description: 操作localStorage 内存
   * @Date: 2020-11-04 09:39:34
   * @Author: David
   */
  localStorage: {
    get (key) {
      return localStorage.getItem(key);
    },
    set (key, val) {
      localStorage.setItem(key, val);
    }
  },

  /**
   * @description: 替换URL地址中的特殊字符
   * @param {String} str url地址
   * @return {*}
   * @Date: 2020-10-30 14:15:31
   * @Author: David
   */
  replaceStr (str) {
    str = str.replace(/%3A/g, ":");
    str = str.replace(/%2F/g, "/");
    str = str.replace(/%3F/g, "?");
    str = str.replace(/%3D/g, "=");
    str = str.replace(/%26/g, "&");
    str = str.replace(/%2B/g, "+");
    str = str.replace(/%20/g, " ");
    str = str.replace(/%23/g, "#");
    str = str.replace(/%40/g, "@");
    return str;
  },

  /**
   * @description: 让e1单元素与e2()元素或者单元素成相反的显示状态
   * @param {Object} e1 
   * @param {Object} e2 
   * @return {*}
   * @Date: 2020-11-12 09:20:48
   * @Author: David
   */
  hmst (e1, ...e2) {
    if (e1.style.display == "" || e1.style.display == "unset") {
      e1.style.display = "none";
      e2.forEach(ele => {
        ele.style.display = "unset";
      })
    } else {
      e1.style.display = "unset";
      e2.forEach(ele => {
        ele.style.display = "none";
      })
    }
  }
}
