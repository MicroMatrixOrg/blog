/*
 * @Author: David
 * @Date: 2021-04-15 17:32:40
 * @LastEditTime: 2021-04-15 17:52:41
 * @LastEditors: David
 * @Description: 对所有对plugin 抛出
 * @FilePath: /BlogVue3/src/plugins/index.ts
 * 可以输入预定的版权声明、个性签名、空行等
 */

import { router } from './router'
import { store, key } from './store'
import { App } from 'vue'

export default {
  install(vue: App) {
    vue.use(router)
    vue.use(store, key)
  },
}
