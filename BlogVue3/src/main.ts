import { createApp } from 'vue'
import App from './App.vue'

import plugins from './plugins'
import './assets/css/index.css'

createApp(App).use(plugins).mount('#app')
