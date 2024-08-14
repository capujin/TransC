import { fileURLToPath, URL } from 'node:url'
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueDevTools from 'vite-plugin-vue-devtools'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    vueDevTools(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  css: {
    preprocessorOptions: {
      scss: {
        additionalData: `@import "@/assets/style/main.scss";`
      },
    },
  },
  server: {
    host: true, // 这样可以暴露到网络
    port: 5173,
    proxy: {
      // 选项实现: http://localhost:5173/api/bar 转发至 http://jsonplaceholder.typicode.com/bar
      '^/api': {
        target: 'http://localhost:2408',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, ''),
      },
    },
    hmr: true,
  },
})
