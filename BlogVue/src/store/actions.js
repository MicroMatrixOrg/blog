import { SAVE_LIST_PAGE_PARS,SAVE_SYS_PATH_LIST } from './mutation-types';
  //调用mutations的方法
  export default {
    //存储分页信息
    setPagePagr : ({ commit },{path,pars}) => {
        commit(SAVE_LIST_PAGE_PARS,{ path,pars });
    },
    //存储左侧模块数据
    setPathSys : ({ commit },{paths,modIds}) => {
        commit(SAVE_SYS_PATH_LIST,{ paths,modIds });
    },
  }