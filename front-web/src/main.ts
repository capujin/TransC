import './assets/main.css'
import { createApp } from 'vue';
import App from './App.vue';
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css'
import { ElMessage, ElNotification } from 'element-plus'
import { getSystemConfigPlugin as getSystemConfig } from '@/lin/plugin/getSystemConfig'
const app = createApp(App);

setTimeout(async () => {
    const router = await getSystemConfig()
    app.use(router)
    app.use(ElementPlus);
    app.config.globalProperties.$message = ElMessage;
    app.config.globalProperties.$notify = ElNotification;
    app.mount('#app');
}, 0);


export default app.config.globalProperties;
