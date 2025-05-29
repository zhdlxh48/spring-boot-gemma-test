import { defineConfig } from 'vite';
import vue from '@vitejs/plugin-vue';
import { resolve } from 'path';

const __dirname = process.cwd();

// https://vite.dev/config/
export default defineConfig({
  plugins: [vue()],
  resolve: {
    alias: {
      '@': resolve(__dirname, './src'),
    },
  },
  server: {
    port: 8081,
    // proxy: {
    //   '/api': {
    //     target: 'http://localhost:8080',
    //     changeOrigin: true,
    //     ws: true
    //   }
    // },
    // cors: true
  }
});
