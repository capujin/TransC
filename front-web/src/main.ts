import './assets/main.css'
import { createApp } from 'vue';
import { createPinia } from 'pinia'
import App from './App.vue';
import 'element-plus/dist/index.css'
import useElementPlus from '@/lin/plugin/element'
import { getSystemConfigPlugin as getSystemConfig } from '@/lin/plugin/getSystemConfig'

const app = createApp(App);
const pinia = createPinia()
setTimeout(async () => {
    const router = await getSystemConfig();
    app.use(router);
    app.use(pinia);
    // 注册Icon组件、注册$message.success/error/info/warning方法
    useElementPlus(app);    

    app.mount('#app');
}, 0);

export const proxy = app.config.globalProperties;