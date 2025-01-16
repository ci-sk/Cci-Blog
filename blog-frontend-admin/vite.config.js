import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'
import prismjs from 'vite-plugin-prismjs';

import { TDesignResolver } from 'unplugin-vue-components/resolvers';

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
      vue(),
    AutoImport({
      resolvers: [
          ElementPlusResolver(),
          TDesignResolver({
          library: 'vue-next',
          }),],

    }),
    Components({
      resolvers: [
          ElementPlusResolver(),
        TDesignResolver({
          library: 'vue-next'
        })
      ],
    }),
    prismjs({
      languages: ['javascript', 'CSS', 'html', 'vue','java','C'],
    }),
  ],
})
