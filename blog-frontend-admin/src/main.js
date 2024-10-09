import { createApp } from 'vue'
import App from './App.vue'

import 'element-plus/theme-chalk/dark/css-vars.css'
import 'element-plus/theme-chalk/display.css'
import router from "./router/index.js";
// import "./assets/css/base.scss"
import axios from "axios";

// 引入pinia仓库
import { createPinia } from 'pinia'
// 持久化存储pinia
import piniaPluginPersist from 'pinia-plugin-persist'


axios.defaults.baseURL = "http://localhost:8080";

const store = createPinia()
store.use(piniaPluginPersist)

const app = createApp(App)
app.use(store)
app.use(router)
app.mount('#app')
