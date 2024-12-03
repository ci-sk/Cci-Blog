import { createApp } from 'vue'
import App from './App.vue'

import 'element-plus/theme-chalk/dark/css-vars.css'
import 'element-plus/theme-chalk/display.css'
import router from "./router/index.js";
import "./assets/css/base-admin.scss"
import axios from "axios";

// 引入pinia仓库
import { createPinia } from 'pinia'
// 持久化存储pinia
import piniaPluginPersist from 'pinia-plugin-persist'

import * as echarts from 'echarts'

axios.defaults.baseURL = "http://localhost:8080";

const store = createPinia()
store.use(piniaPluginPersist)


//vue3.0取消了Vue.prototype，官方文档推荐使用globalProperties


const app = createApp(App)
app.use(store)
app.use(router)
app.config.globalProperties.$echarts = echarts
app.mount('#app')
