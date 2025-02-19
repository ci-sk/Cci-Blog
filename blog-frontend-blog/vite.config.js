import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import { prismjsPlugin } from 'vite-plugin-prismjs'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
      vue(),
    prismjsPlugin({
      // languages: 'all', // 语言
      languages: ['java','c','html','css','javascript','json'],
      plugins: ['line-numbers','show-language','copy-to-clipboard','inline-color'],
      css: true,
    })
  ],
})
