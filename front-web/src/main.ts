import './assets/main.css'
import { createApp } from 'vue';
import App from './App.vue';
import { setupApp } from '@/config/app-setup';
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css'
import { ElMessage, ElNotification } from 'element-plus'

const app = createApp(App);
setupApp(app);
app.use(ElementPlus)
app.config.globalProperties.$message = ElMessage;
app.config.globalProperties.$notify = ElNotification;
app.mount('#app')

export default app.config.globalProperties;
