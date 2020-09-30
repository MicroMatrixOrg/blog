import { SAVE_LIST_PAGE_PARS,SAVE_SYS_PATH_LIST } from './mutation-types';
  
  export default {
    //保存分页信息
    SAVE_LIST_PAGE_PARS: (state,{ path,pars }) => {
        state.listPagePars.set(path,pars);
    },
    //保存模块权限信息
    SAVE_SYS_PATH_LIST: (state,{ paths,modIds }) => {
        state.lisPathSys.set('path',paths);
        state.lisPathSys.set('modId',modIds)
    },
  }