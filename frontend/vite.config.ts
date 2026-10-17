import { fileURLToPath, URL } from 'node:url'
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url)),
    },
  },
  server: {
    port: 5173,
    proxy: {
      // 开发模式：将 /api 转发到后端，避免 CORS
      '/api': {
        target: 'http://localhost:18080',
        changeOrigin: true,
      },
    },
  },
  // M4 单 jar 集成时，取消下方注释，让构建产物直接输出到后端 static：
  // build: {
  //   outDir: '../backend/src/main/resources/static',
  //   emptyOutDir: true,
  // },
})
